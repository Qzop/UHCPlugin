package core.Events;

import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Scatter.Scatter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Events implements Listener
{
    @EventHandler
    public void foodEvent(FoodLevelChangeEvent e)
    {
        if(!Scatter.started)
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void damageEvent(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof Player)
        {
        	Player p = (Player) e.getEntity();
        	
            if(!Scatter.started && !p.getWorld().getName().equals("Arena"))
            {
                if(e.getCause().equals(EntityDamageEvent.DamageCause.CONTACT))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.LAVA))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.WITHER))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.FALLING_BLOCK))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.MELTING))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.POISON))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.SUICIDE))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.THORNS))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.MAGIC))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.VOID))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.STARVATION))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.SUFFOCATION))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.CUSTOM))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.FIRE))
                {
                    e.setCancelled(true);
                }
            }
            
            if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()) || PlayerKills.spectator.contains(p.getUniqueId()))
            {
            	if(e.getCause().equals(EntityDamageEvent.DamageCause.CONTACT))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.LAVA))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.WITHER))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.FALLING_BLOCK))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.MELTING))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.POISON))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.SUICIDE))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.THORNS))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.MAGIC))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.VOID))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.STARVATION))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.SUFFOCATION))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.CUSTOM))
                {
                    e.setCancelled(true);
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.FIRE))
                {
                    e.setCancelled(true);
                }
            }
        }
    }
}
