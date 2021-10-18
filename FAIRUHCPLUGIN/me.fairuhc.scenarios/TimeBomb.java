package me.fairuhc.UHC.scenarios;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Main;

public class TimeBomb implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void timeBomb(PlayerDeathEvent e)
	{
		String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]";
		Player p = e.getEntity();
		Location loc = p.getLocation().clone();
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && ScenarioEvents.timebomb == true)
		{
			Block b = loc.getBlock();
			
			b = b.getRelative(BlockFace.DOWN);
			b.setType(Material.CHEST);
			
			Chest c = (Chest) b.getState();
			
			b = b.getRelative(BlockFace.NORTH);
			b.setType(Material.CHEST);
			
			for(ItemStack item : e.getDrops())
			{
				if(item == null || item.getType() == Material.AIR)
				{
					continue;
				}
				
				c.getInventory().addItem(item);
			}
			
			e.getDrops().clear();
			
			final ArmorStand stand = p.getWorld().spawn(c.getLocation().clone().add(0.5, 1, 0), ArmorStand.class);
			
			stand.setCustomNameVisible(true);
			stand.setSmall(true);
			
			stand.setGravity(false);
			stand.setVisible(false);
			
			stand.setMarker(true);
			
			new BukkitRunnable() 
			{
				private int time = 30;
				
				public void run()
				{
					time--;
					
					if(time == 0)
					{
						plugin.broadcast(prefix + " " + ChatColor.GOLD + p.getName() + ChatColor.WHITE + " 's corpse has exploded!" );
						loc.getBlock().setType(Material.AIR);
						
						loc.getWorld().createExplosion(loc.getBlockX() + 0.5, loc.getBlockY() + 0.5, loc.getBlockZ() + 0.5, 5, false, true);
						loc.getWorld().strikeLightning(loc);
						
						stand.remove();
						cancel();
						return;
					}
					else if(time == 1)
					{
						stand.setCustomName("§4" + time + "s");
					}
					else if(time == 2)
					{
						stand.setCustomName("§c" + time + "s");
					}
					else if(time == 3)
					{
						stand.setCustomName("§6" + time + "s");
					}
					else if(time <= 15)
					{
						stand.setCustomName("§e" + time + "s");
					}
					else 
					{
						stand.setCustomName("§a" + time + "s");
					}
				}
			}.runTaskTimer(plugin, 0 , 20); 
		}
	}
}
