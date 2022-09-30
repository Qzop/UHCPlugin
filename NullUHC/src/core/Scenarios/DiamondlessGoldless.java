package core.Scenarios;

import core.ScenariosInventory.ScenariosInventory;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class DiamondlessGoldless implements Listener
{
    @EventHandler
    public void onBreak(BlockBreakEvent e)
    {
        if(e.getBlock().getType() == Material.GOLD_ORE)
        {
            if(ScenariosInventory.goldless)
            {
                e.getBlock().setType(Material.AIR);
                e.getPlayer().giveExp(1);
            }
        }

        if(e.getBlock().getType() == Material.DIAMOND_ORE)
        {
            if(ScenariosInventory.diamondless)
            {
                e.getBlock().setType(Material.AIR);
                e.getPlayer().giveExp(new Random().nextInt(7) + 3);
            }
        }
    }
}
