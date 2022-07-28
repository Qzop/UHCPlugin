package core.Alerts;

import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.UUID;

public class Xray implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
    private ArrayList<Block> blocksMined = new ArrayList<Block>();
    private int size = 0;

    @EventHandler
    public void onBreak(BlockBreakEvent e)
    {
        if((e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.GOLD_ORE) && e.getBlock().getWorld().getName().equals("uhc_world") && !blocksMined.contains(e.getBlock()))
        {
            Player breaker = e.getPlayer();
            Block block = e.getBlock();
            getBlocks(block);

            for(Player p : Main.online.getOnlinePlayers())
            {
                if(p.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(p.getUniqueId()) && !Alerts.xrayalerts.contains(p.getUniqueId()))
                {
                    if(block.getType() == Material.DIAMOND_ORE)
                    {
                        p.sendMessage(Alerts.alertPref + " " + ChatColor.LIGHT_PURPLE + breaker.getDisplayName() + ChatColor.YELLOW + " mined " + ChatColor.AQUA + size + " Diamond(s).");
                    }
                    else if(block.getType() == Material.GOLD_ORE)
                    {
                        p.sendMessage(Alerts.alertPref + " " + ChatColor.LIGHT_PURPLE + breaker.getDisplayName() + ChatColor.YELLOW + " mined " + ChatColor.GOLD + size + " Gold.");
                    }

                    if(blocksMined.size() > 100)
                    {
                        blocksMined.clear();
                    }
                }
            }
        }
    }

    public void getBlocks(Block block)
    {
        if(block.getType() != Material.DIAMOND_ORE && block.getType() != Material.GOLD_ORE)
        {
            return;
        }

        if(blocksMined.contains(block))
        {
            return;
        }

        blocksMined.add(block);

        getBlocks(block.getRelative(BlockFace.NORTH));
        getBlocks(block.getRelative(BlockFace.SOUTH));
        getBlocks(block.getRelative(BlockFace.EAST));
        getBlocks(block.getRelative(BlockFace.WEST));
        getBlocks(block.getRelative(BlockFace.DOWN));
        getBlocks(block.getRelative(BlockFace.UP));
        getBlocks(block.getRelative(BlockFace.NORTH_EAST));
        getBlocks(block.getRelative(BlockFace.NORTH_WEST));
        getBlocks(block.getRelative(BlockFace.SOUTH_EAST));
        getBlocks(block.getRelative(BlockFace.SOUTH_WEST));

        size++;
    }
}