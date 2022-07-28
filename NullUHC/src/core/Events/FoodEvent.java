package core.Events;

import core.HostsMods.HostsMods;
import core.Scatter.Scatter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodEvent implements Listener
{
    @EventHandler
    public void foodEvent(FoodLevelChangeEvent e)
    {
        Player p = (Player) e.getEntity();

        if(!Scatter.started)
        {
            e.setCancelled(true);
        }
        else if(HostsMods.mods.contains(p.getUniqueId()) || HostsMods.hosts.contains(p.getUniqueId()))
        {
            e.setCancelled(true);
        }
    }
}
