package core.Events;

import core.Scatter.Scatter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ItemDespawnEvent implements Listener
{
    @EventHandler
    public void arrowDespawn(ProjectileHitEvent e)
    {
        Projectile p = e.getEntity();

        if(!Scatter.started)
        {
            if(p.getWorld().getName().equals("Arena"))
            {
                if(p instanceof Arrow)
                {
                    p.remove();
                }
            }
        }
    }
}
