package core.Arena;

import core.Scatter.Scatter;
import core.mainPackage.Commands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.UUID;

public class PracticeArena implements Listener
{
    public static ArrayList<UUID> playersInArena = new ArrayList<UUID>();

    public void onArenaJoin(Player p)
    {
        if(!Commands.scatter && !Scatter.started)
        {
            World world = Bukkit.getWorld("Arena");
            ArenaKills.arenaKills.put(p.getUniqueId(), 0);
            playersInArena.add(p.getUniqueId());

            // Make it a random scatter
            p.teleport(world.getSpawnLocation());
        }
        else
        {
            p.sendMessage(ChatColor.RED + "You may not use this command now!");
        }
    }

    public void onArenaLeave(Player p)
    {
        if(!Commands.scatter && !Scatter.started)
        {
            World world = Bukkit.getWorld("world");
            ArenaKills.arenaKills.remove(p.getUniqueId());
            playersInArena.remove(p.getUniqueId());

            p.teleport(world.getSpawnLocation());
        }
        else
        {
            p.sendMessage(ChatColor.RED + "You may not use this command now!");
        }
    }
}
