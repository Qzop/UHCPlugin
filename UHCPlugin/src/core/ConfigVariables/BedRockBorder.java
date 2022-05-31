package core.ConfigVariables;

import core.Config.ConfigInventory;
import core.mainPackage.Main;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class BedRockBorder implements Listener
{
    Main plugin = Main.getPlugin(Main.class);

    public void setUpBorder()
    {
        new BukkitRunnable()
        {
            World world = Bukkit.getWorld("uhcworld");
            int z = ConfigInventory.borderSize;
            int x = ConfigInventory.borderSize;

            boolean posX = false;
            boolean posZ = false;
            boolean negX = false;
            boolean negZ = false;

            public void run()
            {
                if(z < -ConfigInventory.borderSize)
                {
                    posZ = true;
                    z = -1500;
                }
                else if(!posZ)
                {
                    for(int y = 0; y < world.getHighestBlockYAt(ConfigInventory.borderSize, z) + 10; y++)
                    {
                        world.getBlockAt(ConfigInventory.borderSize, y, z).setType(Material.BEDROCK);
                    }

                    world.loadChunk(ConfigInventory.borderSize, z);
                    z--;
                }
                else if(!negZ && posZ)
                {
                    if(z > ConfigInventory.borderSize)
                    {
                        negZ = true;
                    }
                    else
                    {
                        for(int y = 0; y < world.getHighestBlockYAt(-ConfigInventory.borderSize, z) + 10; y++)
                        {
                            world.getBlockAt(-ConfigInventory.borderSize, y, z).setType(Material.BEDROCK);
                        }

                        world.loadChunk(-ConfigInventory.borderSize, z);
                        z++;
                    }
                }

                if(x < -ConfigInventory.borderSize)
                {
                    posX = true;
                    x = -1500;
                }
                else if(!posX)
                {
                    for(int y = 0; y < world.getHighestBlockYAt(x, ConfigInventory.borderSize) + 10; y++)
                    {
                        world.getBlockAt(x, y, ConfigInventory.borderSize).setType(Material.BEDROCK);
                    }

                    world.loadChunk(x, ConfigInventory.borderSize);
                    x--;
                }
                else if(!negX && posX)
                {
                    if(x > ConfigInventory.borderSize)
                    {
                        negX = true;
                    }
                    else
                    {
                        for(int y = 0; y < world.getHighestBlockYAt(x, -ConfigInventory.borderSize) + 10; y++)
                        {
                            world.getBlockAt(x, y, -ConfigInventory.borderSize).setType(Material.BEDROCK);
                        }

                        world.loadChunk(x, -ConfigInventory.borderSize);
                        x++;
                    }
                }

                if(posX && posZ && negX && negZ)
                {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Border finished.");
                    cancel();
                }
            }

        }.runTaskTimer(plugin, 0, 1);
    }
}
