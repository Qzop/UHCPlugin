package core.ConfigVariables;

import core.Config.ConfigInventory;
import core.mainPackage.Main;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class BedRockBorder implements Listener
{
    Main plugin = Main.getPlugin(Main.class);

    public void setUpBorder()
    {
        ArrayList<Block> wall = new ArrayList<Block>();
        World world = Bukkit.getWorld("uhcworld");
        int z = ConfigInventory.borderSize;
        int x = ConfigInventory.borderSize;

        boolean posX = false;
        boolean posZ = false;
        boolean negX = false;
        boolean negZ = false;

        while(!posZ)
        {
            if(z < -ConfigInventory.borderSize)
            {
                posZ = true;
                z = -1500;
            }
            else
            {
                for(int y = 0; y < world.getHighestBlockYAt(ConfigInventory.borderSize, z) + 10; y++)
                {
                    wall.add(world.getBlockAt(ConfigInventory.borderSize, y, z));
                }

                world.loadChunk(ConfigInventory.borderSize, z);
                z--;
            }
        }

        while(!negZ && posZ);
        {
            if(z > ConfigInventory.borderSize)
            {
                negZ = true;
            }
            else
            {
                for(int y = 0; y < world.getHighestBlockYAt(-ConfigInventory.borderSize, z) + 10; y++)
                {
                    wall.add(world.getBlockAt(-ConfigInventory.borderSize, y, z));
                }

                world.loadChunk(-ConfigInventory.borderSize, z);
                z++;
            }
        }

        while(!posX && posZ && negZ)
        {
            if(x < -ConfigInventory.borderSize)
            {
                posX = true;
                x = -1500;
            }
            else
            {
                for(int y = 0; y < world.getHighestBlockYAt(x, ConfigInventory.borderSize) + 10; y++)
                {
                    wall.add(world.getBlockAt(x, y, ConfigInventory.borderSize));
                }

                world.loadChunk(x, ConfigInventory.borderSize);
                x--;
            }
        }

        while(!negX && posX && posZ && negZ)
        {
            if(x > ConfigInventory.borderSize)
            {
                negX = true;
            }
            else
            {
                for(int y = 0; y < world.getHighestBlockYAt(x, -ConfigInventory.borderSize) + 10; y++)
                {
                    wall.add(world.getBlockAt(x, y, -ConfigInventory.borderSize));
                }

                world.loadChunk(x, -ConfigInventory.borderSize);
                x++;
            }
        }

        if(negX && posX && posZ && negZ)
        {
            Bukkit.broadcastMessage(ChatColor.GREEN + "All border blocks added to array.");
        }


        new BukkitRunnable()
        {
            public void run()
            {
               for(int i = 0; i < 2000; i++)
               {
                   if(wall.isEmpty())
                   {
                       Bukkit.broadcastMessage(ChatColor.GREEN + "Done");
                       cancel();
                   }
                   else
                   {
                       wall.get(i).setType(Material.BEDROCK);
                   }
               }
            }

        }.runTaskTimer(plugin, 0, 40);
    }
}
