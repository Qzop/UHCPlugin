package core.ConfigVariables;

import core.Config.ConfigInventory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class Portals implements Listener
{
    @EventHandler
    public void onTeleport(PlayerPortalEvent e)
    {
        World world = Bukkit.getWorld("world_nether");

        if(e.getCause().toString().equals("NETHER_PORTAL") && e.getPlayer().getWorld().getName().equals("uhc_world"))
        {
            if(!ConfigInventory.netherBoo)
            {
                e.setCancelled(true);
            }
            else
            {
                e.setFrom(e.getPlayer().getLocation());
                Location netherLoc = new Location(Bukkit.getWorld("uhc_nether"), (e.getPlayer().getLocation().getX() / 8) , e.getPlayer().getLocation().getY(), (e.getPlayer().getLocation().getZ() / 8));
                e.setTo(netherLoc);
            }
        }
        else if(e.getCause().toString().equals("END_PORTAL") && e.getPlayer().getWorld().getName().equals("uhc_world"))
        {
            e.setCancelled(true);
        }
    }
}
