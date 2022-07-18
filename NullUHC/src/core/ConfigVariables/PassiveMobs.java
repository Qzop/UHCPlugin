package core.ConfigVariables;

import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Scatter.Scatter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class PassiveMobs implements Listener
{
    @EventHandler
    public void onAttack(EntityTargetLivingEntityEvent e)
    {
        if(e.getTarget() instanceof Player)
        {
            if(ConfigInventory.passiveMobs)
            {
                e.setCancelled(true);
            }
            else
            {
                Player p = (Player) e.getTarget();

                if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()) || PlayerKills.spectator.contains(p.getUniqueId()))
                {
                    e.setCancelled(true);
                }
            }
        }
    }
}
