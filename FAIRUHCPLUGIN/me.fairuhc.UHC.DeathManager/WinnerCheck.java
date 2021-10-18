package me.fairuhc.UHC.DeathManager;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Main;
import net.md_5.bungee.api.ChatColor;

public class WinnerCheck implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	public FFAWinner ffawin = new FFAWinner(plugin);
	public TeamsWinner teamwin = new TeamsWinner(plugin);
	
	public void checkWinner()
	{
		new BukkitRunnable()
		{
			public void run()
			{
				if(Gamemanager.teamsize == 1)
				{
					if(ffawin.isWinner() == true)
					{
						Gamemanager.ended = true;
						ffawin.getWinner();
						cancel();
						return;
					}
				}
				else if(Gamemanager.teamsize > 1)
				{
					if(teamwin.isWinner() == true)
					{
						Gamemanager.ended = true;
						teamwin.getWinners();
						cancel();
						return;
					}
				}
			}
			
		}.runTaskTimer(plugin, 0, 20);
	}
}
