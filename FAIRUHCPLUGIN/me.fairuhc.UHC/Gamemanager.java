package me.fairuhc.UHC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import Events.Sidebar;
import Events.Timer;
import me.fairuhc.UHC.teams.CreateTeam;
import me.fairuhc.UHC.teams.TeamManager;
import net.md_5.bungee.api.ChatColor;

public class Gamemanager implements Listener
{
	public static boolean started = false;
	public static boolean ended = false;
	public static boolean invited = false;
	public static boolean wincheck = false;
	public static HashMap<String, ArrayList<String>> teams = new HashMap<String, ArrayList<String>>();
	public static int teamsize = 1;
	public static boolean changeTeamSize;
	public static int latescattertime = 10000;
	public static int graceperiod = 20;
	public static int finalheal = 10;
	
	public final Main plugin;
	
	public Gamemanager(Main plugin)
	{
		this.plugin = plugin;

	}
	
	public void getTeams(Player p)
	{
		String t = "";
		
		if(teamsize > 1)
		{
			for(Player player : Bukkit.getOnlinePlayers())
			{
				if(teams.containsKey(player.getDisplayName()))
				{
					for(int i = 0; i < teams.get(player.getDisplayName()).size(); i++)
					{
						t += " " + teams.get(player.getDisplayName()).get(i);
					}
					
					p.sendMessage(player.getDisplayName() + "-" + t);
				}
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "It's ffa...");
		}
	}
	
	public void getHealth(Player p, Player target)
	{
		p.sendMessage(ChatColor.YELLOW + target.getDisplayName() + "'s health: " + target.getHealth());
	}
}