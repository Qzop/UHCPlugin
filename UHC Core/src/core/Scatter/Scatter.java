package core.Scatter;

import java.util.ArrayList;
import java.util.Random;

import core.Config.ConfigInventory;
import core.ConfigVariables.Horses;
import core.HostsMods.HostsMods;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import core.mainPackage.Commands;
import core.mainPackage.Main;
import net.md_5.bungee.api.ChatColor;

public class Scatter implements Listener
{
	private Horses horse = new Horses();
	public static String UHCprefix = ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "UHC" + ChatColor.GRAY + "]";
	Main plugin = Main.getPlugin(Main.class);
	
	public void onStart()
	{
		// Temporary variables 
		ArrayList<Player> ffa = new ArrayList<Player>();

		ArrayList<Location> locations = new ArrayList<Location>();
		World world = Bukkit.getWorld("uhcworld");

		if(!ConfigInventory.horses)
		{
			horse.killCurrentHorses();
		}

		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(Commands.ffa == true)
			{
				// Make sure to add if and else statements for hosts and mods.

				if(!HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
				{
					ffa.add(p);
				}
			}
			else if(Commands.teams == true)
			{
				// Do stuff for teams in here
			}
			
			p.sendMessage(UHCprefix + ChatColor.AQUA + " Scatter is starting.");
		}
		
		if(Commands.ffa == true)
		{
			int seconds = 0;
			
			if(ffa.size() <= 20)
			{
				seconds = 60;
			}
			else if(ffa.size() > 20 && ffa.size() <= 40)
			{
				seconds = 80;
			}
			else if(ffa.size() > 40 && ffa.size() <= 60)
			{
				seconds = 100;
			}
			else if(ffa.size() > 60 && ffa.size() <= 80)
			{
				seconds = 120;
			}
			else if(ffa.size() > 80)
			{
				seconds = 200;
			}

			new BukkitRunnable()
			{
				int randomX;
				int randomZ;
				int index = 0;

				public void run()
				{
					randomX = new Random().nextInt(1000);
					randomZ = new Random().nextInt(1000);

					if(index == ffa.size())
					{
						cancel();
					}
					else
					{
						Location teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 3, randomZ);
						Location checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
						Block block = checkloc.getBlock();

						while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
						{
							randomX = new Random().nextInt(1000);
							randomZ = new Random().nextInt(1000);

							teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 1, randomZ);
							checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
							block = checkloc.getBlock();
						}

						world.loadChunk(randomX, randomZ);
						locations.add(teleloc);
					}

					index++;
				}

			}.runTaskTimer(plugin, 0, 20);

			// Scatter the players randomly
			new BukkitRunnable()
			{
				int index = 0;

				public void run()
				{
					 if(index == ffa.size())
					 {
						 Bukkit.broadcastMessage(UHCprefix + ChatColor.GREEN + " All players have been scatterd.");
						 cancel();
					 }
					 else
					 {
						 ffa.get(index).teleport(locations.get(index));
					 }

					 index++;
				}
				
			}.runTaskTimer(plugin, 0, seconds);
		}
		else if(Commands.teams == true)
		{
			
		}
	}
}