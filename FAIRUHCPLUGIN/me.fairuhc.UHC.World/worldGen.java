package me.fairuhc.UHC.World;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Main;
import net.md_5.bungee.api.ChatColor;

public class worldGen implements Listener
{
	public Main plugin;
	
	public worldGen(Main plugin)
	{
		this.plugin = plugin;
	}
	
	public void deleteWorld()
	{
		try 
		{
			FileUtils.forceDelete(new File("uhc_world"));
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
		 plugin.getServer().shutdown();
		
	}
	
	public void regenerateMap(Player p)
	{
		if(Gamemanager.ended == true)
		{
			ChunkLoad.loadedChunks.clear();
			
			for(Player player : Bukkit.getOnlinePlayers())
			{
				player.kickPlayer(ChatColor.RED + "Reloading world.");
			}
			
			Bukkit.unloadWorld("uhc_world", false);
			p.sendMessage(ChatColor.GREEN + "Regenerating the map...");
			deleteWorld();
		}
		
		if(p.hasPermission("uhc.regenerate") && Gamemanager.started != true)
		{
			ChunkLoad.loadedChunks.clear();
			
			for(Player player : Bukkit.getOnlinePlayers())
			{
				player.kickPlayer(ChatColor.RED + "Reloading world.");
			}
			
			Bukkit.unloadWorld("uhc_world", false);
			p.sendMessage(ChatColor.GREEN + "Regenerating the map...");
			deleteWorld();
			
		}
		else if(p.hasPermission("uhc.regenerate") && Gamemanager.started == true)
		{
			p.sendMessage(ChatColor.RED + "You cannot regenerate the world while a game is in progress!");
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You do not have permission.");
		}
	}
	
	public void regenerateMapZeroZero()
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			player.kickPlayer(ChatColor.RED + "Reloading world.");
		}
		
		Bukkit.unloadWorld("uhc_world", false);
		deleteWorld();
	}
}