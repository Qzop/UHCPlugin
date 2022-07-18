package core.Alerts;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.Scoreboard.Time;
import core.mainPackage.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PvP implements Listener
{
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e)
    {
        if(Scatter.started)
        {
            /*if(Time.minutes < ConfigInventory.gracePeriod)
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

                        for(Player p : Main.online.getOnlinePlayers())
                        {
                            if(p.hasPermission("uhc.alerts") && Alerts.allalerts.contains(p.getUniqueId()) && Alerts.pvpalerts.contains(p.getUniqueId()))
                            {
                                p.sendMessage(Alerts.alertPref + ChatColor.BOLD + ChatColor.GOLD + " " + p1.getDisplayName() + ChatColor.AQUA + " is fighting " + ChatColor.BOLD + ChatColor.GOLD + p2.getDisplayName() + ChatColor.AQUA + ".");
                            }
                        }
                    }
                }
            }*/
        }
    }
}
