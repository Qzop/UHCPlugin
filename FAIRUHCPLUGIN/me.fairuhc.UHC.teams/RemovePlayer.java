package me.fairuhc.UHC.teams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.fairuhc.UHC.Main;
import net.md_5.bungee.api.ChatColor;

public class RemovePlayer implements Listener
{
	Main plugin;
	public TeamManager tm;
	
	public String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
	
	public RemovePlayer(Main plugin)
	{
		this.plugin = plugin;
		tm = new TeamManager(plugin);
	}
	
	public void removePlayer(Player p, Player target)
	{
		if(p.getUniqueId() == target.getUniqueId())
		{
			p.sendMessage(prefix + ChatColor.RED + " You cannot kick yourself from your own team!");
		}
		else
		{
			if(tm.hasTeam(p) == true && CreateTeam.playerTeams.get(p.getUniqueId()).contains(target.getUniqueId()))
			{
				CreateTeam.playerTeams.get(p.getUniqueId()).remove(target.getUniqueId());
				target.sendMessage(prefix + ChatColor.RED + " You have been removed from " + ChatColor.AQUA + p.getDisplayName() + "'s " + ChatColor.RED + " team.");
				
				for(int i = 0; i < CreateTeam.playerTeams.get(p.getUniqueId()).size(); i++)
				{
					Bukkit.getPlayer(CreateTeam.playerTeams.get(p.getUniqueId()).get(i)).sendMessage(prefix + " " + ChatColor.AQUA + target.getDisplayName() + ChatColor.RED + " has been removed from your team!");
				}
			}
			else if(tm.hasTeam(p) == false)
			{
				p.sendMessage(prefix + ChatColor.RED + " You do not own a team!");
			}
			else if(!CreateTeam.playerTeams.get(p.getUniqueId()).contains(target.getUniqueId()))
			{
				p.sendMessage(ChatColor.RED + target.getDisplayName() + " is not on your team!");
			}
		}
	}
}
