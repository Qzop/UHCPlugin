package core.Scoreboard;

import core.Scatter.Scatter;
import core.mainPackage.Main;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class Time implements Listener
{
    private static int time;
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

                time++;
            }

        }.runTaskTimer(plugin, 0, 20);
    }

    public String getTime()
    {
        String sTime = "";
        int seconds = 0;

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
}
