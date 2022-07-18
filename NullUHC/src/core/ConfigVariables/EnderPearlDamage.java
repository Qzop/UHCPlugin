package core.ConfigVariables;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EnderPearlDamage implements Listener
{
    @EventHandler
    public void onPearlDamage(EntityDamageByEntityEvent e)
    {
        if(Scatter.started)
        {
            if(e.getEntity() instanceof Player)
            {
                if(e.getDamager() instanceof EnderPearl)
                {
                    if(!ConfigInventory.pearlDmg)
                    {
                        e.setCancelled(true);
                    }
                }
            }
        }
        else
        {
            if(e.getEntity() instanceof Player)
            {
                if(e.getDamager() instanceof EnderPearl)
                {
                    e.setCancelled(true);
                }
            }
        }
    }
}
