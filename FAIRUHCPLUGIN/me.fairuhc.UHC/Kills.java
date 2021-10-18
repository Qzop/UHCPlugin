package me.fairuhc.UHC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Kills implements Listener
{
	public static HashMap<UUID, Integer> topkill = new HashMap<UUID, Integer>();
	
	@EventHandler  (priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onDeath(PlayerDeathEvent e)
	{
		if(e.getEntity().getType() == EntityType.PLAYER)
		{
			Player killed = (Player) e.getEntity();
			
			if(killed.getLastDamageCause() instanceof EntityDamageByEntityEvent) 
			{
				EntityDamageByEntityEvent dmgEvent = (EntityDamageByEntityEvent) killed.getLastDamageCause();
				
				if(dmgEvent.getDamager() instanceof Player)
				{
					Player killer = (Player) dmgEvent.getDamager();
					
					if(topkill.containsKey(killer.getUniqueId()))
					{
						int val = topkill.get(killer.getUniqueId()) + 1;
							
						topkill.replace(killer.getUniqueId(), val);
					}
					else
					{
						topkill.put(killer.getUniqueId(), 1);
					}
				}
			}
		}
	}
	
	public void topkill(Player p)
	{
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<Integer> kills = new ArrayList<Integer>();
		
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(topkill.containsKey(player.getUniqueId()))
			{
				kills.add(topkill.get(player.getUniqueId()));
			}
		}
		
		String header = "Top 10 Kills: \n";
		String top = "";
		
		Collections.sort(kills, Collections.reverseOrder());
			
		if(topkill.isEmpty())
		{
			p.sendMessage(ChatColor.YELLOW + header);
		}
		
		else
		{
			p.sendMessage(ChatColor.YELLOW + "" + header);
			
			for(Player player : Bukkit.getOnlinePlayers())
			{
				if(topkill.containsKey(player.getUniqueId()))
				{
					int kill = 0;
					
					if(kills.size() < 10)
					{
						for(int i = 0; i < kills.size(); i++)
						{
							if(topkill.get(player.getUniqueId()) == kills.get(i))
							{
								if(!players.contains(player.getDisplayName()))
								{
									p.sendMessage(ChatColor.YELLOW + player.getDisplayName() + "- " + kills.get(i));
									players.add(player.getDisplayName());
								}
							}
						}
					}
					else if(kills.size() == 10)
					{
						for(int i = 0; i < 10; i++)
						{
							if(topkill.get(player.getUniqueId()) == kills.get(i))
							{
								if(!players.contains(player.getDisplayName()))
								{
									p.sendMessage(ChatColor.YELLOW + player.getDisplayName() + "- " + kills.get(i));
									players.add(player.getDisplayName());
								}
							}
						}
					}
				}
			}
			
			
		}
		
		for(int i = 0; i < kills.size(); i++)
		{
			kills.remove(i);
			players.remove(i);
		}
	}
	
	public String getTopKiller()
	{
		ArrayList<Integer> gettopkiller = new ArrayList<Integer>();
		String top = "";
		boolean check = false;
		
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(topkill.containsKey(player.getUniqueId()))
			{
				gettopkiller.add(topkill.get(player.getUniqueId()));
			}
		}
		
		Collections.sort(gettopkiller, Collections.reverseOrder());
		
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(topkill.get(p.getUniqueId()) == gettopkiller.get(0) && check == false)
			{
				top = p.getDisplayName();
				check = true;
			}
		}
		
		if(check == false)
		{
			for(OfflinePlayer p : Bukkit.getOfflinePlayers())
			{
				if(topkill.get(p.getUniqueId()) == gettopkiller.get(0) && check == false)
				{
					top = Bukkit.getOfflinePlayer(p.getUniqueId()).getName();
					check = true;
				}
			}
		}
		
		return top;
	}
}