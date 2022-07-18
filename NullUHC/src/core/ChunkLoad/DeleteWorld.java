package core.ChunkLoad;

import core.mainPackage.Main;
import net.minecraft.util.org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DeleteWorld implements Listener
{
    Main plugin = Main.getPlugin(Main.class);

    public void deleteWorld(Player p, String worldName)
    {
        if(FileUtils.getFile(worldName).exists())
        {
            new BukkitRunnable()
            {
                int seconds = 5;

                public void run()
                {
                    if (seconds == 0)
                    {
                        try
                        {
                            FileUtils.forceDelete(new File(worldName));
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                        p.sendMessage(ChatColor.RED + "Restarting the server.");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
                        cancel();
                    }

                    Bukkit.broadcastMessage(ChatColor.RED + "Restarting the server in " + seconds + " seconds");

                    seconds--;
                }
            }.runTaskTimer(plugin, 0, 20);
        }
        else
        {
            p.sendMessage(ChatColor.RED + "The world '" + worldName + "' does not exist!");
        }
    }

    public void teleportToWorld(Player p, String worldName)
    {
        if(FileUtils.getFile(worldName).exists())
        {
            World world = Bukkit.getWorld(worldName);
            p.teleport(new Location(world, 1500, 80, 1500));
            p.sendMessage(ChatColor.GREEN + "Teleported to world '" + worldName + "'");
        }
        else
        {
            p.sendMessage(ChatColor.RED + "The world '" + worldName + "' does not exist!");
        }
    }
}
