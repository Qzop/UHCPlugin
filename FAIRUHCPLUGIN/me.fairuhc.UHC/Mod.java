package me.fairuhc.UHC;

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

import net.md_5.bungee.api.ChatColor;

public class Mod implements Listener
{
	public static ArrayList<UUID> mod = new ArrayList<UUID>();
	public String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
	public void setMod(Player p)
	{
		HostModItems items = new HostModItems();
		
		World world = Bukkit.getWorld("uhc_world");
		
		items.getItems(p);
		
		if(mod.contains(p.getUniqueId()))
		{
			p.setGameMode(GameMode.ADVENTURE);
			p.sendMessage(prefix + ChatColor.GREEN + " You are now mod!");
			
			p.setAllowFlight(true);
			p.setFlying(true);
			p.teleport(world.getSpawnLocation().add(0, 100, 0));
			
			for(Player player : Bukkit.getOnlinePlayers())
			{
				player.hidePlayer(p);
			}
		}
		else
		{
			p.setGameMode(GameMode.ADVENTURE);
			p.sendMessage(prefix + ChatColor.GREEN + " You are now mod!");
			mod.add(p.getUniqueId());
			
			p.setAllowFlight(true);
			p.setFlying(true);
			p.teleport(world.getSpawnLocation().add(0, 100, 0));
			
			for(Player player : Bukkit.getOnlinePlayers())
			{
				player.hidePlayer(p);
			}
		}
	}
	
	public void removeMod(Player p)
	{
		LobbyItems items = new LobbyItems();
		World world = Bukkit.getWorld("uhc_lobby");
		
		if(p.hasPermission("uhc.host"))
		{
			p.setGameMode(GameMode.SURVIVAL);
			mod.remove(p.getUniqueId());
			p.sendMessage(prefix + ChatColor.RED + " You are no longer mod.");
			
			p.setAllowFlight(false);
			p.setFlying(false);
			
			for(Player player : Bukkit.getOnlinePlayers())
			{
				player.showPlayer(p);
			}
			
			if(Gamemanager.started == false)
			{
				p.getInventory().clear();
				items.getItems(p);
			}
			else
			{
				p.teleport(world.getSpawnLocation().add(0, 100, 0));
				p.getInventory().clear();
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		
		if(mod.contains(p.getUniqueId()))
		{
			setMod(p);
		}
	}             
	
	@EventHandler
	public void hunger(FoodLevelChangeEvent e)
	{
		Player p;
		
		if(e.getEntity().getType() == EntityType.PLAYER)
		{
			p = (Player) e.getEntity();
			
			if(mod.contains(p.getUniqueId()))
			{
				e.setCancelled(true);
			}
		}
	}
}
