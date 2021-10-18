package me.fairuhc.UHC.DeathManager;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Main;
import net.md_5.bungee.api.ChatColor;

public class Spectator implements Listener
{
	public static ArrayList<UUID> spectators = new ArrayList<UUID>();
	public Main plugin; 
	
	public Spectator(Main plugin)
	{
		this.plugin = plugin;
	}
	
	public void setSpec(Player p)
	{
		World world = Bukkit.getWorld("uhc_world");
		spectators.add(p.getUniqueId());
		
		if(spectators.contains(p.getUniqueId()))
		{
			p.setGameMode(GameMode.ADVENTURE);
			p.sendMessage(ChatColor.GREEN + "You are now a spectator.");
			
			p.setAllowFlight(true);
			p.setFlying(true);
			p.teleport(world.getSpawnLocation().add(0, 100, 0));
			
			for(Player player : Bukkit.getOnlinePlayers())
			{
				player.hidePlayer(p);
			}
		}
	}
	

	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		
		if(spectators.contains(p.getUniqueId()))
		{
			setSpec(p);
		}
	}
	
	@EventHandler
	public void hunger(FoodLevelChangeEvent e)
	{
		Player p;
		
		if(e.getEntity().getType() == EntityType.PLAYER)
		{
			p = (Player) e.getEntity();
			
			if(spectators.contains(p.getUniqueId()))
			{
				e.setCancelled(true);
			}
		}
		else if(Gamemanager.started == false)
		{
			e.setCancelled(true);
			
			if(e.getEntity().getType() == EntityType.PLAYER)
			{
				p = (Player) e.getEntity();
				
				p.setFoodLevel(15);
			}
		}
		else if(Gamemanager.started == true)
		{
			e.setCancelled(false);
		}
	}
	
	public void removeSpec(Player p)
	{
		if(spectators.contains(p.getUniqueId()))
		{
			spectators.remove(p.getUniqueId());
			
			p.setAllowFlight(false);
			p.setFlying(false);
			p.sendMessage(ChatColor.RED + "You are no longer a spectator.");
			
			for(Player player : Bukkit.getOnlinePlayers())
			{
				player.showPlayer(p);
			}
		}
	}
}
