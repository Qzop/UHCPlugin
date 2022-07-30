package core.ChunkLoad;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import core.Config.ConfigInventory;
import core.ConfigVariables.BedRockBorder;
import net.minecraft.server.v1_7_R4.WorldGenNether;
import net.minecraft.util.org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import core.Scatter.Scatter;
import core.mainPackage.Main;
import net.md_5.bungee.api.ChatColor;

public class Chunks implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	public static boolean check = false;
	private static ArrayList<Chunk> totalchunks = new ArrayList<Chunk>();
	private BedRockBorder bord = new BedRockBorder();
	
	public void checkZeroZero()
	{
		World world = Bukkit.getWorld("uhc_world");
		
		try
		{
			if(!world.getBiome(0, 0).equals(Biome.PLAINS) && !world.getBiome(0, 0).equals(Biome.DESERT) && !world.getBiome(0, 0).equals(Biome.SAVANNA)
					&& !world.getBiome(0, 0).equals(Biome.RIVER) && !world.getBiome(0, 0).equals(Biome.SUNFLOWER_PLAINS))
			{
				check = true;
				plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "NOT PLAINS, DESERT, SAVANNAH, OR RIVER.");
				
				new BukkitRunnable()
				{
					int seconds = 60;
					
					public void run()
					{
						if(seconds % 5 == 0)
						{
							Bukkit.broadcastMessage(ChatColor.RED + "Restarting the server in " + seconds + " seconds.");
						}
						
						if(seconds == 0)
						{
							try
							{
								FileUtils.forceDelete(new File("uhc_world"));
								FileUtils.forceDelete(new File("uhc_nether"));
							}
							catch(IOException e)
							{
								e.printStackTrace();
							}

							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
							cancel();
						}
						
						seconds--;
					}
					
					
				}.runTaskTimer(plugin, 0, 20);
			}
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadUHCWorldChunks()
	{
		World world = Bukkit.getWorld("uhc_world");

		bord.setUpBorder(ConfigInventory.borderSize, world);

		new BukkitRunnable()
		{
			int index = 0;
			int count = 0;

			public void run()
			{
				if(index == 0)
				{
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb shape square");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb set 2500 2000 0 0");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb uhc_world fill 600");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb fill confirm");
				}

				if(count == 11)
				{
					restartServer();
					cancel();
				}
				else
				{
					if(index % 60 == 0 && index != 0)
					{
						index = 1;
						count++;

						int minutes = (11 - count);

						if(minutes > 1)
						{
							Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.DARK_AQUA + " Chunks are loading. The server will restart in " + minutes + " minutes.");
						}
						else if(minutes == 1)
						{
							Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.DARK_AQUA + " Chunks are loading. The server will restart in " + minutes + " minute.");
						}
					}
					else
					{
						index++;
					}
				}
			}

		}.runTaskTimer(plugin, 0, 20);
	}
	
	public void restartServer()
	{
		new BukkitRunnable()
		{
			int seconds = 10;
			
			public void run()
			{
				if(seconds == 0)
				{
					Bukkit.broadcastMessage(ChatColor.RED + "Restarting the server.");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
					cancel();
				}
				else
				{
					Bukkit.broadcastMessage(ChatColor.RED + "Restarting the server in " + seconds + " seconds.");
				}

				seconds--;
			}
			
		}.runTaskTimer(plugin, 0, 20);
	}
}
