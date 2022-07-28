package core.Alerts;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.Scoreboard.Time;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class PvP implements Listener
{
    public static HashMap<UUID, UUID> currentlyFighting = new HashMap<UUID, UUID>();
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

                        if(currentlyFighting.containsKey(p2.getUniqueId()))
                        {
                            if(!currentlyFighting.get(p2.getUniqueId()).equals(p1.getUniqueId()))
                            {
                                currentlyFighting.put(p2.getUniqueId(), p1.getUniqueId());

                                for(Player p : Main.online.getOnlinePlayers())
                                {
                                    if(p.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(p.getUniqueId()) && !Alerts.pvpalerts.contains(p.getUniqueId()))
                                    {
                                        p.sendMessage(Alerts.alertPref + ChatColor.BOLD + ChatColor.GOLD + " " + p2.getDisplayName() + ChatColor.AQUA + " is fighting " + ChatColor.BOLD + ChatColor.GOLD + p1.getDisplayName() + ChatColor.AQUA + ".");
                                    }
                                }

                                coolDown(p2);
                            }
                        }
                        else
                        {
                            if(!currentlyFighting.isEmpty())
                            {
                                for(UUID keys : currentlyFighting.keySet())
                                {
                                    if(!currentlyFighting.get(keys).equals(p2.getUniqueId()))
                                    {
                                        for(Player p : Main.online.getOnlinePlayers())
                                        {
                                            if(p.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(p.getUniqueId()) && !Alerts.pvpalerts.contains(p.getUniqueId()))
                                            {
                                                p.sendMessage(Alerts.alertPref + ChatColor.BOLD + ChatColor.GOLD + " " + p2.getDisplayName() + ChatColor.AQUA + " is fighting " + ChatColor.BOLD + ChatColor.GOLD + p1.getDisplayName() + ChatColor.AQUA + ".");
                                            }
                                        }
                                    }
                                }
                            }
                            else
                            {
                                currentlyFighting.put(p2.getUniqueId(), p1.getUniqueId());

                                for(Player p : Main.online.getOnlinePlayers())
                                {
                                    if(p.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(p.getUniqueId()) && !Alerts.pvpalerts.contains(p.getUniqueId()))
                                    {
                                        p.sendMessage(Alerts.alertPref + ChatColor.BOLD + ChatColor.GOLD + " " + p2.getDisplayName() + ChatColor.AQUA + " is fighting " + ChatColor.BOLD + ChatColor.GOLD + p1.getDisplayName() + ChatColor.AQUA + ".");
                                    }
                                }

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
