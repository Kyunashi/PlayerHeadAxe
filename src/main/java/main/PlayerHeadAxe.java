package main;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerHeadAxe extends JavaPlugin implements Listener {

	@EventHandler
	public void interactWithPlayer(PlayerInteractEntityEvent event) {

		Entity rightClicked = event.getRightClicked();
		Player player = event.getPlayer();
		ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

		if (itemInMainHand.getType() == Material.IRON_AXE && 
				rightClicked.getType() == EntityType.PLAYER && 
				itemInMainHand.containsEnchantment(Enchantment.SILK_TOUCH)) {

			ItemStack head = new ItemStack(Material.PLAYER_HEAD);
			SkullMeta headMeta = (SkullMeta) head.getItemMeta();
			
			headMeta.setDisplayName(player.getName() + "\'s Kopf");
			headMeta.setOwningPlayer((OfflinePlayer) player);

			ArrayList<String> lore = new ArrayList<String>();
			lore.add("Der abgeschlagene Kopf von " + player.getName());
			headMeta.setLore(lore);
			
			head.setItemMeta(headMeta);
			player.getInventory().remove(itemInMainHand);
			player.getInventory().addItem(head);
		}
	}

	@Override
	public void onEnable() {

		this.getServer().getPluginManager().registerEvents(this, this);

	}

	@Override
	public void onDisable() {
		// Shutdown logic
	}

}
