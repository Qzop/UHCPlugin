package core.ConfigVariables;

import core.Config.ConfigInventory;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class AppleRate implements Listener
{
    @EventHandler
    public void breakLeaves(BlockBreakEvent e)
    {
        Block b = e.getBlock();
        Player p = e.getPlayer();

        if((b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2) && b.getWorld().getName().equals("uhcworld"))
        {
            Random rand = new Random();

            if(rand.nextInt(100) <= ConfigInventory.applerate)
            {
                if(p.getItemInHand().getType() == Material.SHEARS)
                {
                    b.getDrops().clear();
                    b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.APPLE, 1));
                }
                else
                {
                    b.getDrops().clear();
                    b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.APPLE, 1));
                }
            }
        }
    }

    @EventHandler
    public void decayLeaves(LeavesDecayEvent e)
    {
        Block b = e.getBlock();

        if((b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2) && b.getWorld().getName().equals("uhcworld"))
        {
            Random rand = new Random();

            if(rand.nextInt(100) <= ConfigInventory.applerate)
            {
                b.getDrops().clear();
                b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.APPLE, 1));
            }
        }
    }
}
