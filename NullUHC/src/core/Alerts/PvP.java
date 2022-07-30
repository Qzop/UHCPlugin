package core.Alerts;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.Scoreboard.Time;
import core.mainPackage.Main;
import javafx.scene.control.Alert;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PvP implements Listener
{
    public static ArrayList<UUID> currentlyFighting = new ArrayList<UUID>();
    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e)
    {
        if(Scatter.started)
        {
            if(Time.minutes < ConfigInventory.gracePeriod)
            {
                if(e.getEntity() instanceof Player)
                {
                    if(e.getDamager() instanceof Player)
                    {
                        e.setCancelled(true);
                    }
                }
            }
            else
            {
                if(e.getEntity() instanceof Player)
                {
                    if(e.getDamager() instanceof Player)
                    {
                        Player p1 = (Player) e.getEntity();
                        Player p2 = (Player) e.getDamager();

                        if(!currentlyFighting.contains(p1.getUniqueId()))
                        {
                            for(Player p : Main.online.getOnlinePlayers())
                            {
                                if(p.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(p.getUniqueId()) && !Alerts.pvpalerts.contains(p.getUniqueId()))
                                {
                                    p.sendMessage(Alerts.alertPref + ChatColor.GOLD + " " + p1.getDisplayName() + ChatColor.AQUA + " is fighting " + ChatColor.GOLD + p2.getDisplayName());
                                }
                            }

                            currentlyFighting.add(p1.getUniqueId());
                            coolDown(p1);

                            if(!currentlyFighting.contains(p2.getUniqueId()))
                            {
                                currentlyFighting.add(p2.getUniqueId());
                                coolDown(p2);
                            }
                        }
                    }
                }
            }
        }
    }

    public void coolDown(Player player)
    {
        new BukkitRunnable()
        {
            int seconds = 30;

            public void run()
            {
                if(seconds == 0)
                {
                    currentlyFighting.remove(player.getUniqueId());
                    cancel();
                }

                seconds--;
            }

        }.runTaskTimer(plugin, 0, 20);
    }
}
