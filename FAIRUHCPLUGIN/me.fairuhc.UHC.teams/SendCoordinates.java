package me.fairuhc.UHC.teams;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.fairuhc.UHC.Gamemanager;
import net.md_5.bungee.api.ChatColor;

public class SendCoordinates implements Listener
{
	public void getCoordinates(Player p)
	{
		if(Gamemanager.started == true)
		{
			if(CreateTeam.playerTeams.containsKey(p.getUniqueId()))
			{
				for(int i = 0; i < CreateTeam.playerTeams.get(p.getUniqueId()).size(); i++)
				{
					Bukkit.getPlayer(CreateTeam.playerTeams.get(p.getUniqueId()).get(i)).sendMessage(ChatColor.AQUA + p.getDisplayName() + "'s Coordinates " + 
									ChatColor.GRAY + " »" + ChatColor.WHITE + " X: " + p.getLocation().getX() + " Y: " + p.getLocation().getY() + "Z: " + p.getLocation().getZ());
				}
			}
			else 
			{
				for(Player player : Bukkit.getOnlinePlayers())
				{
					if(CreateTeam.playerTeams.containsKey(p.getUniqueId()))
					{
						if(CreateTeam.playerTeams.get(p.getUniqueId()).contains(player.getUniqueId()))
						{
							for(int i = 0; i < CreateTeam.playerTeams.get(p.getUniqueId()).size(); i++)
							{
								Bukkit.getPlayer(CreateTeam.playerTeams.get(p.getUniqueId()).get(i)).sendMessage(ChatColor.AQUA + p.getDisplayName() + "'s Coordinates " + 
										ChatColor.GRAY + " »" + ChatColor.WHITE + " X: " + p.getLocation().getX() + " Y: " + p.getLocation().getY() + "Z: " + p.getLocation().getZ());
							}
						}
					}
				}
			}
		}
		
		else if(Gamemanager.started == false)
		{
			p.sendMessage(ChatColor.RED + "The game has not started yet!");
		}
		else if(Gamemanager.teamsize == 1)
		{
			p.sendMessage(ChatColor.RED + "You cannot send coordinates in FFA!");
		}
	}
}
