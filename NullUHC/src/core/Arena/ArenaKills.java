package core.Arena;

import core.GoldenHead.GoldenHead;
import core.Scatter.Scatter;
import core.mainPackage.Commands;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class ArenaKills implements Listener
{
    public static HashMap<UUID, Integer> arenaKills = new HashMap<UUID, Integer>();
    private ArenaKit kit = new ArenaKit();
    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onKill(PlayerDeathEvent e)
    {
        Player p = e.getEntity();

        if (!Scatter.started && !Scatter.ended && !Commands.scatter)
        {
            if (e.getEntity().getWorld().getName().equals("Arena"))
            {
                if(PracticeArena.playersInArena.contains(p.getUniqueId()))
                {
                    e.getDrops().clear();
                    UUID uuid = p.getKiller().getUniqueId();
                    arenaKills.put(uuid, arenaKills.get(uuid) + 1);

                    GoldenHead gh = new GoldenHead();
                    gh.giveGoldenHead(p.getKiller());
                }

                int randomX = new Random().nextInt(45 - 1) - 45;
                int randomZ = new Random().nextInt(45 - 1) - 45;

                Location loc = new Location(Bukkit.getWorld("Arena"), randomX, 50, randomZ);

                new BukkitRunnable()
                {
                    public void run()
                    {
                        kit.setArenaKit(p);
                        cancel();
                    }

                }.runTaskTimer(plugin, 0, 5);

                p.spigot().respawn();
                p.teleport(loc);
            }
        }
    }
    
    public int getKills(Player p)
    {
    	return arenaKills.get(p.getUniqueId());
    }
}
