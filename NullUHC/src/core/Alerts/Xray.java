package core.Alerts;

import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.UUID;

public class Xray implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
    private ArrayList<Location> blocksMined = new ArrayList<Location>();

    @EventHandler
    public void onBreak(BlockBreakEvent e)
    {
        if(e.getBlock().getType() == Material.DIAMOND_ORE && e.getBlock().getWorld().getName().equals("uhc_world") && !blocksMined.contains(e.getBlock().getLocation()))
        {
            Player breaker = e.getPlayer();
            Block block = e.getBlock();
            int x = block.getX();
            int z = block.getZ();
            int y = block.getY();
            int count = 1;

            blocksMined.add(block.getLocation());

           if(block.getWorld().getBlockAt(x, y + 1, z).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x, y + 1, z).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x + 1, y + 1, z).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x + 1, y + 1, z).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x, y + 1, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x, y + 1, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x, y + 1, z - 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x, y + 1, z - 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y + 1, z).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y + 1, z).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x + 1, y + 1, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x + 1, y + 1, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y + 1, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y + 1, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x + 1, y + 1, z - 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x + 1, y + 1, z - 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y + 1, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y + 1, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y + 1, z - 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y + 1, z - 1).getLocation());
               count++;
           }

           if(block.getWorld().getBlockAt(x, y - 1, z).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x, y - 1, z).getLocation());
               count++;
           }

           if(block.getWorld().getBlockAt(x + 1, y - 1, z).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x + 1, y - 1, z).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y - 1, z).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y - 1, z).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x, y - 1, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x, y - 1, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x, y - 1, z - 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x, y - 1, z - 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x + 1, y - 1, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x + 1, y - 1, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y - 1, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y - 1, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x + 1, y - 1, z - 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x + 1, y - 1, z - 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y - 1, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y - 1, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y - 1, z - 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y - 1, z - 1).getLocation());
               count++;
           }

           if(block.getWorld().getBlockAt(x + 1, y, z).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x + 1, y, z).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y, z).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y, z).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x, y, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x, y, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x, y, z - 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x, y, z - 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x + 1, y, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x + 1, y, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x + 1, y, z - 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x + 1, y, z - 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y, z + 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y, z + 1).getLocation());
               count++;
           }
           if(block.getWorld().getBlockAt(x - 1, y, z - 1).getType() == Material.DIAMOND_ORE)
           {
               blocksMined.add(block.getWorld().getBlockAt(x - 1, y, z - 1).getLocation());
               count++;
           }

            for(Player p : Main.online.getOnlinePlayers())
            {
                if(p.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(p.getUniqueId()) && !Alerts.xrayalerts.contains(p.getUniqueId()))
                {
                    if(count > 1)
                    {
                        p.sendMessage(Alerts.alertPref + ChatColor.YELLOW + " " + breaker.getDisplayName() + " mined " + ChatColor.AQUA + count + " Diamonds.");
                    }
                    else
                    {
                        p.sendMessage(Alerts.alertPref + ChatColor.YELLOW + " " + breaker.getDisplayName() + " mined " + ChatColor.AQUA + count + " Diamond.");
                    }
                }
            }
        }

        if(e.getBlock().getType() == Material.GOLD_ORE && e.getBlock().getWorld().getName().equals("uhc_world") && !blocksMined.contains(e.getBlock().getLocation()))
        {
            Player breaker = e.getPlayer();
            Block block = e.getBlock();
            int x = block.getX();
            int z = block.getZ();
            int y = block.getY();
            int count = 1;

            blocksMined.add(block.getLocation());

            if(block.getWorld().getBlockAt(x, y + 1, z).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x, y + 1, z).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x + 1, y + 1, z).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x + 1, y + 1, z).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x, y + 1, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x, y + 1, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x, y + 1, z - 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x, y + 1, z - 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y + 1, z).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y + 1, z).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x + 1, y + 1, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x + 1, y + 1, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y + 1, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y + 1, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x + 1, y + 1, z - 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x + 1, y + 1, z - 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y + 1, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y + 1, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y + 1, z - 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y + 1, z - 1).getLocation());
                count++;
            }

            if(block.getWorld().getBlockAt(x, y - 1, z).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x, y - 1, z).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x + 1, y - 1, z).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x + 1, y - 1, z).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y - 1, z).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y - 1, z).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x, y - 1, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x, y - 1, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x, y - 1, z - 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x, y - 1, z - 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x + 1, y - 1, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x + 1, y - 1, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y - 1, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y - 1, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x + 1, y - 1, z - 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x + 1, y - 1, z - 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y - 1, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y - 1, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y - 1, z - 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y - 1, z - 1).getLocation());
                count++;
            }

            if(block.getWorld().getBlockAt(x + 1, y, z).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x + 1, y, z).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y, z).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y, z).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x, y, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x, y, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x, y, z - 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x, y, z - 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x + 1, y, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x + 1, y, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x + 1, y, z - 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x + 1, y, z - 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y, z + 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y, z + 1).getLocation());
                count++;
            }
            if(block.getWorld().getBlockAt(x - 1, y, z - 1).getType() == Material.GOLD_ORE)
            {
                blocksMined.add(block.getWorld().getBlockAt(x - 1, y, z - 1).getLocation());
                count++;
            }

            for(Player p : Main.online.getOnlinePlayers())
            {
                if(p.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(p.getUniqueId()) && !Alerts.xrayalerts.contains(p.getUniqueId()))
                {
                    p.sendMessage(Alerts.alertPref + ChatColor.YELLOW + " " + breaker.getDisplayName() + " mined " + ChatColor.GOLD + count + " Gold.");
                }
            }
        }
    }
}