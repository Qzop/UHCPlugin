package me.fairuhc.UHC.scenarios;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Main;

public class Timber implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void timber(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		World w = b.getWorld();

		if(p.getGameMode().equals(GameMode.SURVIVAL) && ScenarioEvents.timber == true && Gamemanager.started == true && (b.getType() == Material.LOG || b.getType() == Material.LOG_2))
		{
			e.setCancelled(true);
			w.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
			w.getBlockAt(b.getX(), b.getY(), b.getZ()).breakNaturally();
			Inventory inv = p.getInventory();
			inv.addItem(new ItemStack(Material.LOG));
			
			// Going up
			new BukkitRunnable()
			{
				int x = b.getX();
				int y = b.getY() + 1;
				int z = b.getZ();
					
				public void run()
				{
					if(w.getBlockAt(x, y, z).getType() == Material.LOG || w.getBlockAt(x, y, z).getType() == Material.LOG_2)
					{
						e.setCancelled(true);
						w.getBlockAt(x, y, z).setType(Material.AIR);
						w.getBlockAt(x, y, z).breakNaturally();
						Inventory inv = p.getInventory();
						inv.addItem(new ItemStack(Material.LOG));
						
						y++;
					}
					else
					{
						cancel();
						return;
					}
				}
					
			}.runTaskTimer(plugin, 0, 1);
			
			// Going down
			new BukkitRunnable()
			{
				int x = b.getX();
				int y = b.getY() - 1;
				int z = b.getZ();
					
				public void run()
				{
					if(w.getBlockAt(x, y, z).getType() == Material.LOG || w.getBlockAt(x, y, z).getType() == Material.LOG_2)
					{
						e.setCancelled(true);
						w.getBlockAt(x, y, z).setType(Material.AIR);
						w.getBlockAt(x, y, z).breakNaturally();
						Inventory inv = p.getInventory();
						inv.addItem(new ItemStack(Material.LOG));
						
						y--;
					}
					else
					{
						cancel();
						return;
					}
				}
					
			}.runTaskTimer(plugin, 0, 1);	
			
			// From plus x
			
			new BukkitRunnable()
			{
				int x = b.getX() + 1;
				int y = b.getY();
				int z = b.getZ();
					
				public void run()
				{
					if(w.getBlockAt(x, y, z).getType() == Material.LOG || w.getBlockAt(x, y, z).getType() == Material.LOG_2)
					{
						e.setCancelled(true);
						w.getBlockAt(x, y, z).setType(Material.AIR);
						w.getBlockAt(x, y, z).breakNaturally();
						Inventory inv = p.getInventory();
						inv.addItem(new ItemStack(Material.LOG));
						
						x++;
					}
					else
					{
						cancel();
						return;
					}
				}
					
			}.runTaskTimer(plugin, 0, 1);
			
			// From minus X
			
			new BukkitRunnable()
			{
				int x = b.getX() - 1;
				int y = b.getY();
				int z = b.getZ();
					
				public void run()
				{
					if(w.getBlockAt(x, y, z).getType() == Material.LOG || w.getBlockAt(x, y, z).getType() == Material.LOG_2)
					{
						e.setCancelled(true);
						w.getBlockAt(x, y, z).setType(Material.AIR);
						w.getBlockAt(x, y, z).breakNaturally();
						Inventory inv = p.getInventory();
						inv.addItem(new ItemStack(Material.LOG));
						
						x--;
					}
					else
					{
						cancel();
						return;
					}
				}
					
			}.runTaskTimer(plugin, 0, 1);
			
			// From plus z
			
			new BukkitRunnable()
			{
				int x = b.getX();
				int y = b.getY();
				int z = b.getZ() + 1;
					
				public void run()
				{
					if(w.getBlockAt(x, y, z).getType() == Material.LOG || w.getBlockAt(x, y, z).getType() == Material.LOG_2)
					{
						e.setCancelled(true);
						w.getBlockAt(x, y, z).setType(Material.AIR);
						w.getBlockAt(x, y, z).breakNaturally();
						Inventory inv = p.getInventory();
						inv.addItem(new ItemStack(Material.LOG));
						
						z++;
					}
					else
					{
						cancel();
						return;
					}
				}
					
			}.runTaskTimer(plugin, 0, 1);
						
			// From minus Z
			
			new BukkitRunnable()
			{
				int x = b.getX();
				int y = b.getY();
				int z = b.getZ() - 1;
					
				public void run()
				{
					if(w.getBlockAt(x, y, z).getType() == Material.LOG || w.getBlockAt(x, y, z).getType() == Material.LOG_2)
					{
						e.setCancelled(true);
						w.getBlockAt(x, y, z).setType(Material.AIR);
						w.getBlockAt(x, y, z).breakNaturally();
						Inventory inv = p.getInventory();
						inv.addItem(new ItemStack(Material.LOG));
						
						z--;
					}
					else
					{
						cancel();
						return;
					}
				}
					
			}.runTaskTimer(plugin, 0, 1);
		}
	}
}
