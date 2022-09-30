package core.Scenarios;

import core.Alerts.Alerts;
import core.ScenariosInventory.ScenariosInventory;
import core.mainPackage.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class VeinMiner implements Listener
{
    private int count = 0;

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
                Material type1 = null;
                boolean broke = false;

                if(block.getType() == Material.DIAMOND_ORE && (breaker.getInventory().getItemInHand().getType() == Material.IRON_PICKAXE
                        || breaker.getInventory().getItemInHand().getType() == Material.DIAMOND_PICKAXE))
                {
                    getBlocks(block, breaker, type, Material.DIAMOND);
                    type1 = Material.DIAMOND;
                    broke = true;
                }
                else if(block.getType() == Material.IRON_ORE && (breaker.getInventory().getItemInHand().getType() == Material.IRON_PICKAXE
                        || breaker.getInventory().getItemInHand().getType() == Material.DIAMOND_PICKAXE || breaker.getInventory().getItemInHand().getType() == Material.STONE_PICKAXE))
                {
                    getBlocks(block, breaker, type, Material.IRON_INGOT);
                    type1 = Material.IRON_ORE;
                    broke = true;
                }
                else if(block.getType() == Material.GOLD_ORE && (breaker.getInventory().getItemInHand().getType() == Material.IRON_PICKAXE
                        || breaker.getInventory().getItemInHand().getType() == Material.DIAMOND_PICKAXE))
                {
                    getBlocks(block, breaker, type, Material.GOLD_INGOT);
                    type1 = Material.GOLD_INGOT;
                    broke = true;
                }

                if(broke)
                {
                    boolean canEnterInv = false;

                    for (ItemStack it : breaker.getInventory().getContents())
                    {
                        if (it == null || it.getType() == Material.AIR || (it.getType() == type && it.getAmount() < 64))
                        {
                            canEnterInv = true;
                            break;
                        }
                    }

                    if(canEnterInv)
                    {
                        if(type == Material.GOLD_ORE && !ScenariosInventory.goldless)
                        {
                            if(ScenariosInventory.tripleores)
                            {
                                breaker.getInventory().addItem(new ItemStack(type1, (count * 3)));
                            }
                            else if(ScenariosInventory.doubleores)
                            {
                                breaker.getInventory().addItem(new ItemStack(type1, (count * 2)));
                            }
                            else
                            {
                                breaker.getInventory().addItem(new ItemStack(type1, count));
                            }
                        }
                        else if(type == Material.DIAMOND_ORE && !ScenariosInventory.diamondless)
                        {
                            if(ScenariosInventory.tripleores)
                            {
                                breaker.getInventory().addItem(new ItemStack(type1, (count * 3)));
                            }
                            else if(ScenariosInventory.doubleores)
                            {
                                breaker.getInventory().addItem(new ItemStack(type1, (count * 2)));
                            }
                            else
                            {
                                breaker.getInventory().addItem(new ItemStack(type1, count));
                            }
                        }
                        else
                        {
                            if(ScenariosInventory.tripleores)
                            {
                                breaker.getInventory().addItem(new ItemStack(type1, (count * 3)));
                            }
                            else if(ScenariosInventory.doubleores)
                            {
                                breaker.getInventory().addItem(new ItemStack(type1, (count * 2)));
                            }
                            else
                            {
                                breaker.getInventory().addItem(new ItemStack(type1, count));
                            }
                        }
                    }
                    else
                    {
                        Location adLoc = new Location(block.getWorld(), block.getX() + .5, block.getY() + .5, block.getZ() + .5);
                        World world = block.getWorld();

                        if(type == Material.GOLD_ORE && !ScenariosInventory.goldless)
                        {
                            if(ScenariosInventory.tripleores)
                            {
                                world.dropItemNaturally(adLoc, new ItemStack(type1, (count * 3)));
                            }
                            else if(ScenariosInventory.doubleores)
                            {
                                world.dropItemNaturally(adLoc, new ItemStack(type1, (count * 2)));
                            }
                            else
                            {
                                world.dropItemNaturally(adLoc, new ItemStack(type1, count));
                            }
                        }
                        else if(type == Material.DIAMOND_ORE && !ScenariosInventory.diamondless)
                        {
                            if(ScenariosInventory.tripleores)
                            {
                                world.dropItemNaturally(adLoc, new ItemStack(type1, (count * 3)));
                            }
                            else if(ScenariosInventory.doubleores)
                            {
                                world.dropItemNaturally(adLoc, new ItemStack(type1, (count * 2)));
                            }
                            else
                            {
                                world.dropItemNaturally(adLoc, new ItemStack(type1, count));
                            }
                        }
                        else
                        {
                            if(ScenariosInventory.tripleores)
                            {
                                world.dropItemNaturally(adLoc, new ItemStack(type1, (count * 3)));
                            }
                            else if(ScenariosInventory.doubleores)
                            {
                                world.dropItemNaturally(adLoc, new ItemStack(type1, (count * 2)));
                            }
                            else
                            {
                                world.dropItemNaturally(adLoc, new ItemStack(type1, count));
                            }
                        }
                    }
                }
            }

            count = 0;
        }
    }

    public void getBlocks(Block block, Player player, Material type, Material type1)
    {
        if(block.getType() != type)
        {
            return;
        }

        if(type1 != null)
        {
            if(type == Material.GOLD_ORE || type == Material.IRON_ORE)
            {
                player.giveExp(1);
            }
            else if(type == Material.DIAMOND_ORE)
            {
                player.giveExp(new Random().nextInt(7) + 3);
            }

            count++;
            block.setType(Material.AIR);
        }

        getBlocks(block.getRelative(BlockFace.NORTH), player, type, type1);
        getBlocks(block.getRelative(BlockFace.SOUTH), player, type, type1);
        getBlocks(block.getRelative(BlockFace.EAST), player, type, type1);
        getBlocks(block.getRelative(BlockFace.WEST), player, type, type1);
        getBlocks(block.getRelative(BlockFace.DOWN), player, type, type1);
        getBlocks(block.getRelative(BlockFace.UP), player, type, type1);
        getBlocks(block.getRelative(BlockFace.NORTH_EAST), player, type, type1);
        getBlocks(block.getRelative(BlockFace.NORTH_WEST), player, type, type1);
        getBlocks(block.getRelative(BlockFace.SOUTH_EAST), player, type, type1);
        getBlocks(block.getRelative(BlockFace.SOUTH_WEST), player, type, type1);
    }
}
