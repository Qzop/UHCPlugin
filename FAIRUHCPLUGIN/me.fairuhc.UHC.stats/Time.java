package me.fairuhc.UHC.stats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Main;

public class Time implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	
	public void Timer(Player p)
	{
		MySQLPlayTime time = new MySQLPlayTime();
		
		new BukkitRunnable()
		{
			public void run()
			{	
				if(!Bukkit.getOnlinePlayers().contains(p))
				{
					cancel();
					return;
				}
				else
				{
					time.addTime(p.getUniqueId(), 1);
				}
			}
			
		}.runTaskTimer(plugin, 0, 20);
	}
}