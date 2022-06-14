package core.HostsMods;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class HostModsItems implements Listener
{
	public void hostmodItems(Player p)
	{
		p.getInventory().clear();
		
		ItemStack compass = new ItemStack(Material.COMPASS, 1, (byte) 0);
		ItemMeta compassMeta = compass.getItemMeta();
		compassMeta.setDisplayName(ChatColor.YELLOW + "Pass through/Teleport");
		compass.setItemMeta(compassMeta);
		
		ItemStack feather = new ItemStack(Material.FEATHER, 1, (byte) 0);
		ItemMeta featherMeta = feather.getItemMeta();
		featherMeta.setDisplayName(ChatColor.YELLOW + "Random Teleport to Player");
		feather.setItemMeta(featherMeta);
		
		ItemStack emerald = new ItemStack(Material.EMERALD, 1, (byte) 0);
		ItemMeta emeraldMeta = emerald.getItemMeta();
		emeraldMeta.setDisplayName(ChatColor.YELLOW + "Teleport to 0,0");
		emerald.setItemMeta(emeraldMeta);
		
		ItemStack chest = new ItemStack(Material.CHEST, 1, (byte) 0);
		ItemMeta chestMeta = chest.getItemMeta();
		chestMeta.setDisplayName(ChatColor.YELLOW + "Player List");
		chest.setItemMeta(chestMeta);
		
		ItemStack redstone = new ItemStack(Material.REDSTONE, 1, (byte) 0);
		ItemMeta redstoneMeta = redstone.getItemMeta();
		redstoneMeta.setDisplayName(ChatColor.YELLOW + "Vanish/Unvanish");
		redstone.setItemMeta(redstoneMeta);
		
		p.getInventory().setItem(0, compass);
		p.getInventory().setItem(1, feather);
		p.getInventory().setItem(4, emerald);
		p.getInventory().setItem(7, chest);
		p.getInventory().setItem(8, redstone);
	}
	
	@EventHandler
	public void itemDrop(PlayerDropItemEvent e)
	{
		Player p = e.getPlayer();
		
		if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()))
		{
			if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Pass through/Teleport"))
			{
				e.setCancelled(true);
			}
			else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Random Teleport to Player"))
			{
				e.setCancelled(true);
			}
			else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Teleport to 0,0"))
			{
				e.setCancelled(true);
			}
			else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Player List"))
			{
				e.setCancelled(true);
			}
			else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Vanish/Unvanish"))
			{
				e.setCancelled(true);
			}
		}
	}
}
