package core.Events;

import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Kills.Spectator;
import core.Scatter.Scatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.text.DecimalFormat;

public class DamageEvent implements Listener
{
    @EventHandler
    public void onDamage(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof Player)
        {
            Player p = (Player) e.getEntity();

            if(!Scatter.started)
            {
                if(p.getWorld().getName().equals("Arena"))
                {
                    if(e.getCause() == EntityDamageEvent.DamageCause.FALL)
                    {
                        e.setCancelled(true);
                    }
                }
                else
                {
                    e.setCancelled(true);
                }
            }

            if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()) || PlayerKills.spectator.contains(p.getUniqueId()))
            {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onProjectile(EntityDamageByEntityEvent e)
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
                }
            }
        }
    }
}
