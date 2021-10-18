package me.fairuhc.UHC.DeathManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Host;
import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.Mod;
import me.fairuhc.UHC.Scatter.TeamScatter;
import me.fairuhc.UHC.stats.MySQLWins;
import me.fairuhc.UHC.teams.CreateTeam;
import me.fairuhc.UHC.teams.TeamManager;
import net.md_5.bungee.api.ChatColor;

public class TeamsWinner implements Listener
{
	public static boolean winnerdeclared = false;
	public static ArrayList<String> deadplayers = new ArrayList<String>();
	public MySQLWins win = new MySQLWins();
	Main plugin;
	
	public TeamsWinner(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		Spectator spec = new Spectator(plugin);
		
		Player p = e.getEntity();
		
		if(Gamemanager.started == true && Gamemanager.teamsize > 1 && e.getEntity().getType() == EntityType.PLAYER)
		{
			p = e.getEntity();
			
			e.setDeathMessage(ChatColor.AQUA + p.getDisplayName() + ChatColor.RED + " was slain by " + ChatColor.AQUA + e.getEntity().getKiller().getDisplayName());
			
			deadplayers.add(p.getDisplayName());
			p.spigot().respawn();
			spec.setSpec(p);
		}
		
		removeTeams();
	}
	
	public void removeTeams()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(!Host.host.contains(p.getUniqueId()) || !Mod.mod.contains(p.getUniqueId()))
			{
				if(Spectator.spectators.contains(p.getUniqueId()) && TeamScatter.teams.containsKey(p.getUniqueId()))
				{
					TeamScatter.teams.get(p.getUniqueId()).remove(p.getUniqueId());
					
					if(TeamScatter.teams.get(p.getUniqueId()).isEmpty())
					{
						TeamScatter.teams.remove(p.getUniqueId());
					}
				}
				else if(Spectator.spectators.contains(p.getUniqueId()) && !TeamScatter.teams.containsKey(p.getUniqueId()))
				{
					for(Player owner : Bukkit.getOnlinePlayers())
					{
						if(TeamScatter.teams.containsKey(owner.getUniqueId()))
						{
							if(TeamScatter.teams.get(owner.getUniqueId()).contains(p.getUniqueId()))
							{
								TeamScatter.teams.get(owner.getUniqueId()).remove(p.getUniqueId());
								
								if(TeamScatter.teams.get(owner.getUniqueId()).isEmpty())
								{
									TeamScatter.teams.remove(owner.getUniqueId());
								}
							}
						}
					}
				}
			}
		}
		
		for(OfflinePlayer p : Bukkit.getOfflinePlayers())
		{
			if(!Host.host.contains(p.getUniqueId()) || !Mod.mod.contains(p.getUniqueId()))
			{
				if(Spectator.spectators.contains(p.getUniqueId()) && TeamScatter.teams.containsKey(p.getUniqueId()))
				{
					TeamScatter.teams.get(p.getUniqueId()).remove(p.getUniqueId());
					
					if(TeamScatter.teams.get(p.getUniqueId()).isEmpty())
					{
						TeamScatter.teams.remove(p.getUniqueId());
					}
				}
				else if(Spectator.spectators.contains(p.getUniqueId()) && !TeamScatter.teams.containsKey(p.getUniqueId()))
				{
					for(OfflinePlayer owner : Bukkit.getOnlinePlayers())
					{
						if(TeamScatter.teams.containsKey(owner.getUniqueId()))
						{
							if(TeamScatter.teams.get(owner.getUniqueId()).contains(p.getUniqueId()))
							{
								TeamScatter.teams.get(owner.getUniqueId()).remove(p.getUniqueId());
								
								if(TeamScatter.teams.get(owner.getUniqueId()).isEmpty())
								{
									TeamScatter.teams.remove(owner.getUniqueId());
								}
							}
						}
					}
				}
			}
		}
	}
	
	public boolean isWinner()
	{
		boolean check = false;
		
		if(TeamScatter.teams.size() == 1)
		{
			check = true;
		}
		else
		{
			check = false;
		}
		
		return check;
		
	}
	
	public void getWinners()
	{
		boolean check = false;
		ArrayList<String> winners = new ArrayList<String>();
		String winner = "";
		
		if(isWinner() == true)
		{
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(TeamScatter.teams.containsKey(p.getUniqueId()))
				{
					for(int i = 0; i < TeamScatter.teams.get(p.getUniqueId()).size(); i++)
					{
						if(Bukkit.getPlayer(TeamScatter.teams.get(p.getUniqueId()).get(i)) == null)
						{
							winners.add(Bukkit.getOfflinePlayer(TeamScatter.teams.get(p.getUniqueId()).get(i)).getName());
						}
						else
						{
							winners.add(Bukkit.getPlayer(TeamScatter.teams.get(p.getUniqueId()).get(i)).getName());
						}
					}
					
					check = true;
					
					for(int i = 0; i < winners.size(); i++)
					{
						if(winners.size() == 1)
						{
							winner+= winners.get(i);
						}
						else
						{
							if(winners.size() == 2)
							{
								if(i == 0)
								{
									winner += winners.get(i);
								}
								else
								{
									winner += " & " + winners.get(i);
								}
							}
							else
							{
								if(i == winners.size() - 1)
								{
									winner += " & " + winners.get(i);
								}
								else
								{
									winner += winners.get(i) + ", ";
								}
							}
						}
					}
					
					Bukkit.broadcastMessage(ChatColor.GREEN + "Congratulations to " + ChatColor.WHITE + "\u00BB" + " " + ChatColor.AQUA + ChatColor.BOLD + winner + " " + ChatColor.WHITE + "\u00AB" +  ChatColor.GREEN + " on winning the UHC!");
					Bukkit.broadcastMessage(ChatColor.GREEN + "Winners have been given their winner ranks.");
				}
			}
			
			if(check == false)
			{
				for(OfflinePlayer p : Bukkit.getOfflinePlayers())
				{
					if(TeamScatter.teams.containsKey(p.getUniqueId()))
					{
						for(int i = 0; i < TeamScatter.teams.get(p.getUniqueId()).size(); i++)
						{
							if(Bukkit.getPlayer(TeamScatter.teams.get(p.getUniqueId()).get(i)) == null)
							{
								winners.add(Bukkit.getOfflinePlayer(TeamScatter.teams.get(p.getUniqueId()).get(i)).getName());
							}
							else
							{
								winners.add(Bukkit.getPlayer(TeamScatter.teams.get(p.getUniqueId()).get(i)).getName());
							}
						}
						
						for(int i = 0; i < winners.size(); i++)
						{
							if(winners.size() == 1)
							{
								winner+= winners.get(i);
							}
							else
							{
								if(winners.size() == 2)
								{
									if(i == 0)
									{
										winner += winners.get(i);
									}
									else
									{
										winner += " and " + winners.get(i);
									}
								}
								else
								{
									if(i == winners.size() - 1)
									{
										winner += " and " + winners.get(i);
									}
									else
									{
										winner += winners.get(i) + ", ";
									}
								}
							}
						}
						
						Bukkit.broadcastMessage(ChatColor.GREEN + "Congratulations to " + ChatColor.GRAY + "\u00BB" + " " + ChatColor.AQUA + ChatColor.BOLD + winner + " " + ChatColor.GRAY + "\u00AB" +  ChatColor.GREEN + " on winning the UHC!");
						Bukkit.broadcastMessage(ChatColor.GREEN + "Winners have been given their winner ranks.");
						
						for(int i = 0; i < winners.size(); i++)
						{
							win.addwins(Bukkit.getPlayer(winners.get(i)).getUniqueId(), 1);
						}
					}
				}
			}
		}
	}
	
	public void setFireWorks(boolean check)
	{
		
	}
}
