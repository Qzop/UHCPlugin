package me.fairuhc.UHC.scenarios;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Main;

public class SafeLoot implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void safeloot(PlayerDeathEvent e)
	{
		Player p = e.getEntity();
		Player k = p.getKiller();
		Location loc = p.getLocation().clone();
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && ScenarioEvents.safeloot == true)
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
						loc.getBlock().setType(Material.AIR);
						
						stand.remove();
						cancel();
						return;
					}
					else if(time > 0)
					{
						stand.setCustomName("§a" + k.getName() + "'s loot:" + time + "s");
					}
				}
			}.runTaskTimer(plugin, 0 , 20); 
		}
	}
}
