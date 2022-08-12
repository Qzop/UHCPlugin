package core.Scenarios;

import core.Scatter.Scatter;
import core.ScenariosInventory.ScenariosInventory;
import core.mainPackage.Commands;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FirelessNoFall implements Listener
{
    @EventHandler
    public void onDamage(EntityDamageEvent e)
    {
        if(Commands.scatter || Scatter.started)
        {
            if(ScenariosInventory.nofall)
            {
                if(e.getEntity() instanceof Player)
                {
                    if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL))
                    {
                        e.setCancelled(true);
                    }
                }
            }
            else if(ScenariosInventory.fireless)
            {
                if(e.getEntity() instanceof Player)
                {
                    if(e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || e.getCause().equals(EntityDamageEvent.DamageCause.LAVA) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK))
                    {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
