package me.fairuhc.UHC.teams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;

public class DisbandTeam implements Listener
{
	public String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
	
	public void disbandTeam(Player p)
	{
		if(CreateTeam.playerTeams.containsKey(p.getUniqueId()))
		{
			if(CreateTeam.playerTeams.get(p.getUniqueId()).size() > 1)
			{
				for(int i = 0; i < CreateTeam.playerTeams.get(p.getUniqueId()).size(); i++)
				{
					Bukkit.getPlayer(CreateTeam.playerTeams.get(p.getUniqueId()).get(i)).sendMessage(prefix + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.RED + " has disbanded the team.");
				}
			}
				
			CreateTeam.playerTeams.remove(p.getUniqueId());
			
			p.sendMessage(prefix + ChatColor.GREEN + " You have successfully disbanded the team.");
		}
		else
		{
			p.sendMessage(prefix + ChatColor.RED + " You cannot disband a team that you have not created.");
		}
		
	}
}
