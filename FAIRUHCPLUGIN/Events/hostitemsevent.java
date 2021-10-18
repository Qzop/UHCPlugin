package Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.fairuhc.UHC.UndergroundPlayers;
import net.md_5.bungee.api.ChatColor;

public class hostitemsevent implements Listener
{
	@EventHandler
	public void inventoryClick (InventoryClickEvent event)
	{
		@SuppressWarnings("unused")
		Player player = (Player) event.getWhoClicked();
		ClickType clicktype = event.getClick();
		
		Inventory open = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();
		UndergroundPlayers under = new UndergroundPlayers();
		
		if(open == null)
		{
			return;
		}		
		else if(open.getName().equals(ChatColor.AQUA + "Underground Players")) 
		{
			event.setCancelled(true);
			
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(item == null || !item.hasItemMeta())
				{
						return;
				}
				else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + p.getDisplayName()))
				{
						player.teleport(p.getLocation());
						player.closeInventory();
				}
			}
		}
		else
		{
			return;
		}
	}
}

