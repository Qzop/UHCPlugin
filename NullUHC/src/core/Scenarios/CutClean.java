package core.Scenarios;

import core.ScenariosInventory.ScenariosInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class CutClean implements Listener
{
    DoScenario doScen = new DoScenario();

    /**
     * A Event with HIGH priority
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event)
    {
        Block block = event.getBlock();
        ItemStack pickInHand = event.getPlayer().getInventory().getItemInHand();
        Player p = event.getPlayer();

        if(block.getType() == Material.GOLD_ORE && ScenariosInventory.cutclean &&
                (p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE) && doScen.doScenario(p))
        {
            int dropsNum = block.getDrops().size();
            pickInHand.setDurability((pickInHand.getDurability()));
            block.setType(Material.AIR);
            boolean canEnterInv = false;

            for (ItemStack it : p.getInventory().getContents())
            {
                if (it == null || it.getType() == Material.AIR || (it.getType() == Material.GOLD_INGOT && it.getAmount() < 64))
                {
                    canEnterInv = true;
                    break;
                }
            }

            if(canEnterInv)
            {
                p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
                p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, dropsNum));
            }
            else
            {
                Location adLoc = new Location(block.getWorld(), block.getX() + .5, block.getY() + .5, block.getZ() + .5);
                block.getWorld().dropItemNaturally(adLoc, new ItemStack(Material.GOLD_INGOT, dropsNum));
            }
            p.giveExp(1);
        }

        if(block.getType() == Material.IRON_ORE && ScenariosInventory.cutclean &&
                (p.getItemInHand().getType() == Material.IRON_PICKAXE || p.getItemInHand().getType() == Material.DIAMOND_PICKAXE || p.getItemInHand().getType() == Material.STONE_PICKAXE) && doScen.doScenario(p))
        {
            int dropsNum = block.getDrops().size();
            pickInHand.setDurability((pickInHand.getDurability()));
            block.setType(Material.AIR);
            boolean canEnterInv = false;

            for (ItemStack it : p.getInventory().getContents())
            {
                if (it == null || it.getType() == Material.AIR || (it.getType() == Material.IRON_ORE& it.getAmount() < 64))
                {
                    canEnterInv = true;
                    break;
                }
            }

            if(canEnterInv)
            {
                p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, dropsNum));
            }
            else
            {
                Location adLoc = new Location(block.getWorld(), block.getX() + .5, block.getY() + .5, block.getZ() + .5);
                block.getWorld().dropItemNaturally(adLoc, new ItemStack(Material.IRON_INGOT, dropsNum));
            }

            p.giveExp(1);
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onAnimalDeath(EntityDeathEvent event)
    {
        if (event.getEntity().getKiller() instanceof Player)
        {
            Entity entity = event.getEntity();
            Player p = event.getEntity().getKiller();

            if (ScenariosInventory.cutclean && doScen.doScenario(p))
            {
                if (entity.getType() == EntityType.COW)
                {
                    event.getDrops().clear();
                    entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.COOKED_BEEF, 2));
                    entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.LEATHER, 2));
                }
                if (entity.getType() == EntityType.CHICKEN)
                {
                    event.getDrops().clear();
                    entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.COOKED_CHICKEN, 1));
                    entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.FEATHER, 2));
                }

                if (entity.getType() == EntityType.PIG)
                {
                    event.getDrops().clear();
                    entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.GRILLED_PORK, 2));
                }
            }
        }
    }
}
