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
import net.minecraft.server.v1_8_R3.Item;

public class Host implements Listener
{
	public static ArrayList<UUID> host = new ArrayList<UUID>();
	public String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
	
	public void clearItemsSetHost(Player p)
	{
		HostModItems items = new HostModItems();
	
		for(int i = 0; i < 40; i++)
		{
			p.getInventory().clear(i);
		}
		
		if(p.hasPermission("uhc.host"))
		{
			setHost(p);
			
			items.getItems(p);
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You do not have permission.");
		}
	}
	
	public void clearItemsRemoveHost(Player p)
	{
		LobbyItems items = new LobbyItems();
		World world = Bukkit.getWorld("uhc_lobby");
	
		for(int i = 0; i < p.getInventory().getSize(); i++)
		{
			p.getInventory().clear(i);
		}
		
		if(p.hasPermission("uhc.host"))
		{
			if(Gamemanager.started == false)
			{
				removeHost(p);
				p.teleport(world.getSpawnLocation().add(0,100 ,0));
				
				items.getItems(p);
			}
			else
			{
				removeHost(p);
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You do not have permission.");
		}
	}
	
	public void setHost(Player p)
	{
		World world = Bukkit.getWorld("uhc_world");
		
		if(Mod.mod.contains(p.getDisplayName()))
		{
			p.sendMessage(prefix + ChatColor.RED + " You are already mod!");
		}
		else
		{
			if(host.contains(p.getUniqueId()))
			{
				p.setGameMode(GameMode.ADVENTURE);
				p.sendMessage(prefix + ChatColor.GREEN + " You are now host!");
					
				p.setAllowFlight(true);
				p.setFlying(true);
					
				for(Player player : Bukkit.getOnlinePlayers())
				{
					player.hidePlayer(p);
				}
			}
			else if(host.size() != 1)
			{
				p.setGameMode(GameMode.ADVENTURE);
				p.sendMessage(prefix + ChatColor.GREEN + " You are now host!");
				host.add(p.getUniqueId());
					
				p.setAllowFlight(true);
				p.setFlying(true);
				p.teleport(world.getSpawnLocation().add(0, 100, 0));
					
				for(Player player : Bukkit.getOnlinePlayers())
				{
					player.hidePlayer(p);
				}
			}
			else if(host.size() == 1)
			{
				p.sendMessage(prefix + ChatColor.RED + " There can only be one host!");
			}
		}
	}
	
	public void removeHost(Player p)
	{
		if(p.hasPermission("uhc.host"))
		{
			p.setGameMode(GameMode.SURVIVAL);
			host.remove(p.getUniqueId());
			p.sendMessage(prefix + ChatColor.RED + " You are no longer host.");
			
			p.setAllowFlight(false);
			p.setFlying(false);
			
			for(Player player : Bukkit.getOnlinePlayers())
			{
				player.showPlayer(p);
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You do not have permission.");
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		
		if(host.contains(p.getUniqueId()))
		{
			setHost(p);
		}
	}
	
	@EventHandler
	public void hunger(FoodLevelChangeEvent e)
	{
		Player p;
		
		if(e.getEntity().getType() == EntityType.PLAYER)
		{
			p = (Player) e.getEntity();
			
			if(host.contains(p.getUniqueId()))
			{
				e.setCancelled(true);
			}
		}
	}
}
