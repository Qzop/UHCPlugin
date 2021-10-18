package me.fairuhc.UHC.teams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Team;

import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.DeathManager.TeamsWinner;
import net.md_5.bungee.api.ChatColor;


public class TeamManager implements Listener
{
	public Main plugin;
	public static boolean invited = false;
	public static ArrayList<Team> teams = new ArrayList<Team>();
	public String teamColor;
	public static HashMap<String, String> hasinvite = new HashMap<String, String>();
	public static HashMap<String, Integer> teamsize = new HashMap<String, Integer>();
	
	public TeamManager(Main plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean hasTeam(Player p)
	{	
		boolean check = false;
	
		if(!CreateTeam.playerTeams.containsKey(p.getUniqueId()))
		{	
			for(Player t : Bukkit.getOnlinePlayers())
			{
				if(CreateTeam.playerTeams.containsKey(t.getUniqueId()))
				{
					if(CreateTeam.playerTeams.get(t.getUniqueId()).contains(p.getUniqueId()))
					{
						return true;
					}
				}
			}
		}
		else
		{
			check = CreateTeam.playerTeams.containsKey(p.getUniqueId());
		}
		
		return check;
	}
	
	public void teamList(Player p, Player target)
	{
		if(hasTeam(target) == true && CreateTeam.playerTeams.containsKey(target.getUniqueId()))
		{
			p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + target.getDisplayName() + "'s Team\n");
			
			for(int i = 0; i < CreateTeam.playerTeams.get(target.getUniqueId()).size(); i++)
			{
				p.sendMessage(ChatColor.AQUA + "- " + Bukkit.getPlayer(CreateTeam.playerTeams.get(target.getUniqueId()).get(i)).getDisplayName());
			}
		}
		else if(hasTeam(target) == true && !CreateTeam.playerTeams.containsKey(target.getUniqueId()))
		{
			for(Player owner : Bukkit.getOnlinePlayers())
			{
				if(CreateTeam.playerTeams.containsKey(owner.getUniqueId()))
				{
					if(CreateTeam.playerTeams.get(owner.getUniqueId()).contains(target.getUniqueId()))
					{
						p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + owner.getDisplayName() + "'s Team\n");
						
						for(int i = 0; i < CreateTeam.playerTeams.get(owner.getUniqueId()).size(); i++)
						{
							p.sendMessage(ChatColor.AQUA + "- " + Bukkit.getPlayer(CreateTeam.playerTeams.get(owner.getUniqueId()).get(i)).getDisplayName());
						}
					}
				}
			}
		}
		else if(hasTeam(target) == false)
		{
			p.sendMessage(ChatColor.RED + "That player does not have a team!");
		}
	}
	
	public int getTeamSize()
	{
		int size = CreateTeam.playerTeams.size();
		
		return size;
	}
	
	public int getTeamsize(Player p)
	{
		int teamsize = 0;
		
		if(hasTeam(p) == true && CreateTeam.playerTeams.containsKey(p.getUniqueId()))
		{
			teamsize = CreateTeam.playerTeams.get(p.getUniqueId()).size();
		}
		else if(hasTeam(p) == true && !CreateTeam.playerTeams.containsKey(p.getUniqueId()))
		{
			for(Player player : Bukkit.getOnlinePlayers())
			{
				if(CreateTeam.playerTeams.containsKey(player.getUniqueId()))
				{
					if(CreateTeam.playerTeams.get(player.getUniqueId()).contains(p.getUniqueId()))
					{
						teamsize = CreateTeam.playerTeams.get(player.getUniqueId()).size();
						break;
					}
				}
			}
		}
		
		return teamsize;
	}
}