package core.Events;

import core.Scatter.Scatter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleport implements Listener
{
    @EventHandler
    public void onTeleport(PlayerTeleportEvent e)
    {
        Player p = e.getPlayer();

        if(e.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN && Scatter.started)
        {
            p.setNoDamageTicks(40);
        }
    }
}
