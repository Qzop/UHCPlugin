package me.fairuhc.UHC.World;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Main;
import net.md_5.bungee.api.ChatColor;

public class ChunkLoad implements Listener
{
	public static ArrayList<Chunk> loadedChunks = new ArrayList<Chunk>();
	public static boolean arechunksloaded = false;
	Main plugin = Main.getPlugin(Main.class);
	private String prefix = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Pregen" + ChatColor.GRAY + " » ";
	
	public void getChunksToLoad()
	{
		World world = Bukkit.getWorld("uhc_world");
		
		if(Main.pregen == true)
		{
			Bukkit.broadcastMessage(prefix + ChatColor.RED + "The pregen is starting. This may take a few minutes.");
			
			new BukkitRunnable()
			{
				int x = 2500;
				int z = 2500;
				
				public void run()
				{
					if(x != -2500)
					{
						world.loadChunk(x, z);
						loadedChunks.add(world.getChunkAt(x, z));
						x--;
					}
					else
					{
						cancel();
						return;
					}
				}
				
			}.runTaskTimer(plugin, 0, 10);
			
			new BukkitRunnable()
			{
				int x = 2500;
				int z = 2500;
				
				public void run()
				{
					if(z != -2500)
					{
						world.loadChunk(x, z);
						loadedChunks.add(world.getChunkAt(x, z));
						z--;
					}
					else
					{
						Bukkit.broadcastMessage(prefix + ChatColor.GREEN + "Pregen has finished.");
						Main.pregen = false;
						arechunksloaded = true;
						cancel();
						return;
					}
				}
				
			}.runTaskTimer(plugin, 0, 1);
			
			
		}
	}
	
	@EventHandler
	public void chunksUnloadEvent(ChunkUnloadEvent e)
	{
		if(loadedChunks.contains(e.getChunk()))
		{
			e.getChunk().load();
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void chunksLoadEvent(ChunkLoadEvent e)
	{
		e.getChunk().load();
	}
}