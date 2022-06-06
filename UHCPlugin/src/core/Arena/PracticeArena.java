package core.Arena;

import core.Scatter.Scatter;
import core.mainPackage.Commands;
import core.mainPackage.Main;

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
    Main plugin = Main.getPlugin(Main.class);

    public void onArenaJoin(Player p)
    {
        if(!Commands.scatter && !Scatter.started)
        {
            World world = Bukkit.getWorld("Arena");
            ArenaKills.arenaKills.put(p.getUniqueId(), 0);
            playersInArena.add(p.getUniqueId());

            // Make it a random scatter
            
            p.getInventory().clear();
            p.teleport(world.getSpawnLocation());
            p.sendMessage(ChatColor.GREEN + "You are now in the arena.");
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
        	if(playersInArena.contains(p.getUniqueId()))
        	{
        		World world = Bukkit.getWorld("world");
                ArenaKills.arenaKills.remove(p.getUniqueId());
                playersInArena.remove(p.getUniqueId());
                
                p.getInventory().clear();

                p.teleport(world.getSpawnLocation());
                
                p.sendMessage(ChatColor.RED + "You have left the arena.");
        	}
        	else
        	{
        		p.sendMessage(ChatColor.RED + "You are not in the arena!");
        	}
        }
        else
        {
            p.sendMessage(ChatColor.RED + "You may not use this command now!");
        }
    }
}
