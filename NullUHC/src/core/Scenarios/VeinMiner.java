package core.Scenarios;

import core.Alerts.Alerts;
import core.ScenariosInventory.ScenariosInventory;
import core.mainPackage.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class VeinMiner implements Listener
{
    @EventHandler
    public void onBreak(BlockBreakEvent e)
    {
        if(e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.GOLD_ORE || e.getBlock().getType() == Material.IRON_ORE || e.getBlock().getType() == Material.EMERALD_ORE
            || e.getBlock().getType() == Material.REDSTONE_ORE || e.getBlock().getType() == Material.LAPIS_ORE || e.getBlock().getType() == Material.COAL_ORE)
        {
            if(ScenariosInventory.veinminer)
            {
                Player breaker = e.getPlayer();
                Block block = e.getBlock();
                Material type = block.getType();
                getBlocks(block, breaker, type);
            }
        }
    }

    public void getBlocks(Block block, Player player, Material type)
    {
        if(block.getType() != type)
        {
            return;
        }


        player.getInventory().addItem(new ItemStack(type, 1));

        getBlocks(block.getRelative(BlockFace.NORTH), player, type);
        getBlocks(block.getRelative(BlockFace.SOUTH), player, type);
        getBlocks(block.getRelative(BlockFace.EAST), player, type);
        getBlocks(block.getRelative(BlockFace.WEST), player, type);
        getBlocks(block.getRelative(BlockFace.DOWN), player, type);
        getBlocks(block.getRelative(BlockFace.UP), player, type);
        getBlocks(block.getRelative(BlockFace.NORTH_EAST), player, type);
        getBlocks(block.getRelative(BlockFace.NORTH_WEST), player, type);
        getBlocks(block.getRelative(BlockFace.SOUTH_EAST), player, type);
        getBlocks(block.getRelative(BlockFace.SOUTH_WEST), player, type);
    }
}
