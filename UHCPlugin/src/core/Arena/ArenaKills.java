package core.Arena;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.HashMap;
import java.util.UUID;

public class ArenaKills implements Listener
{
    public static HashMap<UUID, Integer> arenaKills = new HashMap<UUID, Integer>();

    @EventHandler
    public void onKill(PlayerDeathEvent e)
    {
        if(e.getEntity() instanceof Player)
        {
            Player p = (Player) e.getEntity();

            if(PracticeArena.playersInArena.contains(p.getUniqueId()))
            {
                if(p.getKiller() instanceof Player)
                {
                    UUID uuid = p.getKiller().getUniqueId();
                    arenaKills.put(uuid, arenaKills.get(uuid) + 1);
                }
            }
        }
    }
    
    public int getKills(Player p)
    {
    	return arenaKills.get(p.getUniqueId());
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e)
    {
        Player p = e.getPlayer();
        World world = Bukkit.getWorld("Arena");

        if(PracticeArena.playersInArena.contains(p.getUniqueId()))
        {
            e.setRespawnLocation(world.getBlockAt(0, 100, 0).getLocation());
            p.spigot().respawn();
        }
    }
}
