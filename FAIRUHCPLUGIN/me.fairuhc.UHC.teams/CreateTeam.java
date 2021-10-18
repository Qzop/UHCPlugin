package me.fairuhc.UHC.teams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Team;

import Events.Sidebar;
import me.fairuhc.UHC.Main;
import net.md_5.bungee.api.ChatColor;

public class CreateTeam implements Listener
{
	Main plugin;
	public TeamManager tm;
	public Sidebar sb;
	public static HashMap<UUID, ArrayList<UUID>> playerTeams = new HashMap<UUID, ArrayList<UUID>>();
	public static ArrayList<ChatColor> color = new ArrayList<>();
	public String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
	
	public CreateTeam(Main plugin)
	{
		this.plugin = plugin;
		tm = new TeamManager(plugin);
		sb = new Sidebar(plugin);
		
		color.add(ChatColor.WHITE);
		color.add(ChatColor.YELLOW);
		color.add(ChatColor.LIGHT_PURPLE);
		color.add(ChatColor.RED);
		color.add(ChatColor.AQUA);
		color.add(ChatColor.GREEN);
		color.add(ChatColor.BLUE);
		color.add(ChatColor.DARK_GRAY);
		color.add(ChatColor.GRAY);
		color.add(ChatColor.GOLD);
		color.add(ChatColor.DARK_PURPLE);
		color.add(ChatColor.DARK_RED);
		color.add(ChatColor.DARK_AQUA);
		color.add(ChatColor.DARK_GREEN);
		color.add(ChatColor.DARK_BLUE);
		color.add(ChatColor.BLACK);
		color.add(ChatColor.ITALIC);
		color.add(ChatColor.BOLD);
		color.add(ChatColor.STRIKETHROUGH);
		color.add(ChatColor.UNDERLINE);
	}
	
	public void createTeam(Player p, ArrayList<UUID> teammembers)
	{
		if(tm.hasTeam(p) == true)
		{
			p.sendMessage(ChatColor.RED + "You are already on a team!");
		}
		else
		{
			playerTeams.put(p.getUniqueId(), teammembers);
			playerTeams.get(p.getUniqueId()).add(p.getUniqueId());
			
			p.sendMessage(prefix + ChatColor.GREEN + " You are now on a team. Use " + ChatColor.AQUA + "/team invite " + ChatColor.GREEN + "to invite players.");
		}
	}
	
	public void latescatterteam(Player p, ArrayList<UUID> teammembers)
	{
		playerTeams.put(p.getUniqueId(), teammembers);
		playerTeams.get(p.getUniqueId()).add(p.getUniqueId());
		p.sendMessage(ChatColor.GREEN + "You are now on your own team.\nUse"+ ChatColor.AQUA + " /helpop " + ChatColor.GREEN + " if this is an error or if you want to put someone on your team.");
		
	}
}
