package me.fairuhc.UHC.stats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import net.md_5.bungee.api.ChatColor;

public class StatsEvent implements Listener
{
	@EventHandler
	public void inventoryClick (InventoryClickEvent event)
	{	
		Player player = (Player) event.getWhoClicked();
		Inventory open = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();
		
		if(open == null)
		{
			return;
		}		
		else if(open.getName().equals(ChatColor.AQUA + player.getDisplayName() + "'s Stats"))
		{
			event.setCancelled(true);
			
			if(item == null || !item.hasItemMeta())
			{
				return;
			}
		}
		else if(open.getName().contains("Stats") && !open.getName().contains(player.getDisplayName()))
		{
			event.setCancelled(true);
			
			if(item == null || !item.hasItemMeta())
			{
				return;
			}
		}
	}
}
