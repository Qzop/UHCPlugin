package core.Alerts;

import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Scatter.Scatter;
import core.Scoreboard.Time;
import core.mainPackage.Commands;
import core.mainPackage.Main;
import javafx.scene.control.Alert;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
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
                    if(e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();

                        if(arrow.getShooter() instanceof Player)
                        {
                            e.setCancelled(true);
                        }
                    }
                    else if(e.getDamager() instanceof FishHook)
                    {
                        FishHook hook = (FishHook) e.getDamager();

                        if(hook.getShooter() instanceof Player)
                        {
                            e.setCancelled(true);
                        }
                    }
                    else if(e.getDamager() instanceof Player)
                    {
                        e.setCancelled(true);
                    }
                }
            }
            else
            {
                if(e.getEntity() instanceof Player)
                {
                    Player p = (Player) e.getEntity();

                    if(e.getDamager() instanceof Arrow)
                    {
                        Arrow arrow = (Arrow) e.getDamager();

                        if(arrow.getShooter() instanceof Player)
                        {
                            Player shooter = (Player) arrow.getShooter();

                            DecimalFormat df = new DecimalFormat("0.00");
                            double damage = Double.parseDouble(df.format((p.getHealth() - e.getFinalDamage()) / 2));

                            if(damage > 0)
                            {
                                shooter.sendMessage(ChatColor.YELLOW + p.getDisplayName() + "'s Health: " + ChatColor.AQUA + df.format((p.getHealth() - e.getFinalDamage()) / 2) + ChatColor.RED + " ♥");
                            }

                            if(!currentlyFighting.contains(shooter.getUniqueId()))
                            {
                                for(Player player : Main.online.getOnlinePlayers())
                                {
                                    if(player.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(player.getUniqueId()) && !Alerts.pvpalerts.contains(player.getUniqueId()))
                                    {
                                        player.sendMessage(Alerts.alertPref + ChatColor.GOLD + " " + shooter.getDisplayName() + ChatColor.AQUA + " is fighting " + ChatColor.GOLD + p.getDisplayName());
                                    }
                                }

                                currentlyFighting.add(shooter.getUniqueId());
                                coolDown(shooter);

                                if(!currentlyFighting.contains(p.getUniqueId()))
                                {
                                    currentlyFighting.add(p.getUniqueId());
                                    coolDown(p);
                                }
                            }
                        }
                    }
                    else if(e.getDamager() instanceof FishHook)
                    {
                        FishHook rod = (FishHook) e.getDamager();

                        if(rod.getShooter() instanceof Player)
                        {
                            Player shooter = (Player) rod.getShooter();
                            DecimalFormat df = new DecimalFormat("0.00");
                            shooter.sendMessage(ChatColor.YELLOW + p.getDisplayName() + "'s Health: " + ChatColor.AQUA + df.format((p.getHealth()) / 2) + ChatColor.RED + " ♥");

                            if(!currentlyFighting.contains(shooter.getUniqueId()))
                            {
                                for(Player player : Main.online.getOnlinePlayers())
                                {
                                    if(player.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(player.getUniqueId()) && !Alerts.pvpalerts.contains(player.getUniqueId()))
                                    {
                                        player.sendMessage(Alerts.alertPref + ChatColor.GOLD + " " + shooter.getDisplayName() + ChatColor.AQUA + " is fighting " + ChatColor.GOLD + p.getDisplayName());
                                    }
                                }

                                currentlyFighting.add(shooter.getUniqueId());
                                coolDown(shooter);

                                if(!currentlyFighting.contains(p.getUniqueId()))
                                {
                                    currentlyFighting.add(p.getUniqueId());
                                    coolDown(p);
                                }
                            }
                        }
                    }
                    else if(e.getDamager() instanceof Player)
                    {
                        Player p1 = (Player) e.getEntity();
                        Player p2 = (Player) e.getDamager();

                        if(HostsMods.hosts.contains(p1.getUniqueId()) || HostsMods.hosts.contains(p2.getUniqueId()) || HostsMods.mods.contains(p1.getUniqueId())
                                || HostsMods.mods.contains(p2.getUniqueId()) || PlayerKills.spectator.contains(p1.getUniqueId()) || PlayerKills.spectator.contains(p2.getUniqueId()))
                        {
                            e.setCancelled(true);
                        }
                        else
                        {
                            if(!currentlyFighting.contains(p1.getUniqueId()))
                            {
                                for(Player player : Main.online.getOnlinePlayers())
                                {
                                    if(player.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(player.getUniqueId()) && !Alerts.pvpalerts.contains(player.getUniqueId()))
                                    {
                                        player.sendMessage(Alerts.alertPref + ChatColor.GOLD + " " + p1.getDisplayName() + ChatColor.AQUA + " is fighting " + ChatColor.GOLD + p2.getDisplayName());
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
        else
        {
            if(e.getEntity() instanceof Player)
            {
                Player p = (Player) e.getEntity();

                if (e.getDamager() instanceof Arrow)
                {
                    Arrow arrow = (Arrow) e.getDamager();

                    if (arrow.getShooter() instanceof Player)
                    {
                        Player shooter = (Player) arrow.getShooter();

                        DecimalFormat df = new DecimalFormat("0.00");
                        double damage = Double.parseDouble(df.format((p.getHealth() - e.getFinalDamage()) / 2));

                        if (damage > 0) {
                            shooter.sendMessage(ChatColor.YELLOW + p.getDisplayName() + "'s Health: " + ChatColor.AQUA + df.format((p.getHealth() - e.getFinalDamage()) / 2) + ChatColor.RED + " ♥");
                        }
                    }
                }
                else if (e.getDamager() instanceof FishHook)
                {
                    FishHook rod = (FishHook) e.getDamager();

                    if (rod.getShooter() instanceof Player)
                    {
                        Player shooter = (Player) rod.getShooter();
                        DecimalFormat df = new DecimalFormat("0.00");
                        shooter.sendMessage(ChatColor.YELLOW + p.getDisplayName() + "'s Health: " + ChatColor.AQUA + df.format((p.getHealth()) / 2) + ChatColor.RED + " ♥");
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
