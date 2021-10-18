package me.fairuhc.UHC.teams;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;

public class SetTeam implements Listener
{
	public String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
	
	public void setTeam(Player p, Player target, Team team)
	{
		if(TeamManager.hasinvite.get(target.getDisplayName()).equalsIgnoreCase(p.getDisplayName()))
		{
			TeamManager.hasinvite.remove(target.getDisplayName());
			
			if(CreateTeam.playerTeams.containsKey(target.getUniqueId()))
			{
				CreateTeam.playerTeams.get(target.getUniqueId()).add(p.getUniqueId()); // add to team
				p.sendMessage(prefix + ChatColor.GREEN + " You are now on " + ChatColor.AQUA + target.getDisplayName() + ChatColor.GREEN + "'s team!");
				target.sendMessage(prefix + " " + ChatColor.AQUA + p.getDisplayName() + ChatColor.GREEN + " has joined your team!");
			}
		}
		else
		{
			p.sendMessage(prefix + ChatColor.RED + " You do not have an invite from that player!");
		}
	}
}
