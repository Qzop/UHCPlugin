package core.ConfigVariables;

import core.Config.ConfigInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class Portals implements Listener
{
    @EventHandler
    public void onTeleport(PlayerPortalEvent e)
    {
        if(e.getCause().toString().equals("NETHER_PORTAL"))
        {
            if(!ConfigInventory.netherBoo)
            {
                e.setCancelled(true);
            }
        }
        else if(e.getCause().toString().equals("END_PORTAL"))
        {
            e.setCancelled(true);
        }
    }
}
