package me.fairuhc.UHC;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

import net.md_5.bungee.api.ChatColor;

public class Nether implements Listener
{
	private Main plugin;
	
	public Nether(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void nether(PlayerPortalEvent e)
	{
		World world = Bukkit.getWorld("world_nether");
		
		if(Config.nether == true)
		{
			e.setCancelled(false);
			e.getPlayer().teleport(world.getSpawnLocation());
		}
		else if(Config.nether == false)
		{
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "The nether has been turned off!");
		}
	}
}
