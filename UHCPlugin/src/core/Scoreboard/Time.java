package core.Scoreboard;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.mainPackage.Commands;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class Time implements Listener
{
    private static int time = 0;
    public static int minutes = 0;
    public static int hours = 0;
    Main plugin = Main.getPlugin(Main.class);

    public void setTime()
    {
        new BukkitRunnable()
        {
            public void run()
            {
                if(Scatter.ended)
                {
                    cancel();
                }
                
                check();

                time++;
            }

        }.runTaskTimer(plugin, 0, 20);
    }

    public String getTime()
    {
        String sTime = "";

        if(time % 60 == 0 && time != 0)
        {
            time = 0;
            minutes++;
        }

        if(minutes % 60 == 0 && minutes != 0)
        {
            minutes = 0;
            hours++;
        }

        sTime += hours + "h " + minutes + "m " + time + "s";

        return sTime;
    }
    
    public void check()
    {
    	if(minutes == 5 && time == 0)
        {
        	Commands.chat = false;
        	Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.GREEN + " Chat is now enabled.");
        }
        
        if(minutes == ConfigInventory.finalHeal && time == 0)
        {
        	for(Player player : Bukkit.getOnlinePlayers())
        	{
        		player.setHealth(player.getHealth() + (20.0 - player.getHealth()));
        	}
        	
        	Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.GREEN + " Final heal has been given. DO NOT ask for another.");
        }
        
        if(minutes == ConfigInventory.gracePeriod && time == 0)
        {
        	Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.GREEN + " Grace period has ended. The first shrink will occur in (variable) minutes.");
        }
    }
}
