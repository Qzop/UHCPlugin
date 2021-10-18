package me.fairuhc.UHC.scenarios;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Chest implements Listener
{
	@EventHandler
	public void chest(PlayerDeathEvent e)
	{
		Player p = e.getEntity();
		Location loc = p.getLocation().clone();
		
		ItemStack playerhead = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta m = (SkullMeta) playerhead.getItemMeta();
		m.setOwner(p.getName());
		m.setDisplayName(ChatColor.GOLD + p.getName() + "'s Head");
		playerhead.setItemMeta(m);
		
		ItemStack head = new ItemStack(Material.GOLDEN_APPLE);;
		ItemMeta meta = head.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Golden Head");
		head.setItemMeta(meta);
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && ScenarioEvents.chest == true)
		{
			Block b = loc.getBlock();
			
			b = b.getRelative(BlockFace.DOWN);
			b.setType(Material.CHEST);
			
			org.bukkit.block.Chest c = (org.bukkit.block.Chest) b.getState();
			
			b = b.getRelative(BlockFace.NORTH);
			b.setType(Material.CHEST);
			
			if(ScenarioEvents.goldenretriever == false)
			{
				c.getInventory().addItem(new ItemStack(playerhead));
			}
			else
			{
				c.getInventory().addItem(new ItemStack(head));
			}
			
			for(ItemStack item : e.getDrops())
			{
				if(item == null || item.getType() == Material.AIR)
				{
					continue;
				}
				c.getInventory().addItem(item);
			}
			
			e.getDrops().clear();
		}
	}
}
