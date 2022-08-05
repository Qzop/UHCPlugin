package core.Events;

import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Kills.Spectator;
import core.Scatter.Scatter;
import core.Scoreboard.Time;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
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
            else
            {
                if(p.getWorld().getName().equals("world"))
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
}
