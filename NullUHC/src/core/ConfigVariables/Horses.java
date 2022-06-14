package core.ConfigVariables;

import core.Config.ConfigInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class Horses implements Listener
{
    @EventHandler
    public void onHorseSpawn(CreatureSpawnEvent e)
    {
        if(!ConfigInventory.horses)
        {
            if(e.getEntity() instanceof Horse && e.getEntity().getWorld().getName().equals("uhc_world"))
            {
                e.setCancelled(true);
            }
        }
    }

    public void killCurrentHorses()
    {
        for(Entity e : Bukkit.getWorld("uhc_world").getEntities())
        {
            if(e instanceof Horse)
            {
                e.remove();
            }
        }
    }
}
