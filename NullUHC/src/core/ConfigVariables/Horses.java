package core.ConfigVariables;

import core.Config.ConfigInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
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

        if(e.getEntity().getWorld().getName().equals("Arena"))
        {
            e.setCancelled(true);
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

    public void killAllEntitiesArena()
    {
        for(Entity e : Bukkit.getWorld("Arena").getEntities())
        {
            if(!(e instanceof Player))
            {
                e.remove();
            }
        }

        Bukkit.broadcastMessage(ChatColor.GREEN + "All entities removed from arena.");
    }
}
