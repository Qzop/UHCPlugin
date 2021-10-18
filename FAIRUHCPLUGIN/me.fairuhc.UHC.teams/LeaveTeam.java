package me.fairuhc.UHC.teams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.fairuhc.UHC.Main;
import net.md_5.bungee.api.ChatColor;

public class LeaveTeam implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	public String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
	public TeamManager tm = new TeamManager(plugin);
	
	@SuppressWarnings("unused")
	public void leaveTeam(Player p)
	{
		boolean check = false;
		DisbandTeam dt = new DisbandTeam();
		
		if(CreateTeam.playerTeams.containsKey(p.getUniqueId()))
		{
			dt.disbandTeam(p);
		}
		else
		{
			if(tm.hasTeam(p))
			{
				for(Player owner : Bukkit.getOnlinePlayers())
				{
					if(CreateTeam.playerTeams.containsKey(owner.getUniqueId()))
					{
						if(CreateTeam.playerTeams.get(owner.getUniqueId()).contains(p.getUniqueId()))
						{
							CreateTeam.playerTeams.get(owner.getUniqueId()).remove(p.getUniqueId());
							
							p.sendMessage(prefix + ChatColor.GREEN + " You have successfully left the team.");
							
							for(int i = 0; i < CreateTeam.playerTeams.get(owner.getUniqueId()).size(); i++)
							{
								Bukkit.getPlayer(CreateTeam.playerTeams.get(owner.getUniqueId()).get(i)).sendMessage(prefix + " " + ChatColor.AQUA + p.getDisplayName() + ChatColor.RED + " has left the team.");
							}
						}
					}
				}
			}
			else
			{
				p.sendMessage(prefix + ChatColor.RED + " You are not on a team!");
			}
		}
	}
}
