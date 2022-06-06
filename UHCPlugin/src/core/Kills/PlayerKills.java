package core.Kills;

import core.Scatter.Scatter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerKills implements Listener
{
    public static HashMap<UUID, Integer> numKills = new HashMap<UUID, Integer>();
    public static ArrayList<UUID> dead = new ArrayList<UUID>();

    public void initializeKills(ArrayList<UUID> players)
    {
        for(int i = 0; i < players.size(); i++)
        {
            numKills.put(players.get(i), 0);
        }
    }

    public void latePlayer(Player p)
    {
        numKills.put(p.getUniqueId(), 0);
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e)
    {
        if(e.getEntity() instanceof Player)
        {
            Player p = (Player) e.getEntity();

            if(p.getWorld().getName().equals("uhcworld") && Scatter.started)
            {
                dead.add(p.getUniqueId());

                if(p.getKiller() instanceof Player)
                {
                    UUID uuid = p.getKiller().getUniqueId();

                    if(numKills.containsKey(uuid))
                    {
                        numKills.put(uuid, numKills.get(uuid) + 1);
                    }
                }
            }
        }
    }
}
