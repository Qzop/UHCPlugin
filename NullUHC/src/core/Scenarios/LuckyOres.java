package core.Scenarios;

import core.ScenariosInventory.ScenariosInventory;
import core.mainPackage.Main;
import net.minecraft.server.v1_7_R4.MojangsonParseException;
import net.minecraft.server.v1_7_R4.MojangsonParser;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LuckyOres implements Listener
{
    /**
     * A Event with HIGH priority
     */
    Main plugin = Main.getPlugin(Main.class);

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {
        int randNum = (int)Math.round(Math.random()*100);
        Player p = event.getPlayer();
        DoScenario doScen = new DoScenario();

        if(ScenariosInventory.lucky && doScen.doScenario(p))
        {
            Block block = event.getBlock();
            ItemStack pickInHand = event.getPlayer().getInventory().getItemInHand();
            Location blockLoc = new Location(block.getLocation().getWorld(), block.getLocation().getBlockX() + .5, block.getLocation().getBlockY() + .5, block.getLocation().getBlockZ() + .5);

            if(block.getType().equals(Material.COAL) &&
                    (pickInHand.getType().equals(Material.STONE_PICKAXE) || pickInHand.getType().equals(Material.WOOD_PICKAXE)
                            || pickInHand.getType().equals(Material.IRON_PICKAXE) || pickInHand.getType().equals(Material.DIAMOND_PICKAXE) || pickInHand.getType().equals(Material.GOLD_PICKAXE)))
            {
                if(randNum < 10)
                {
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(blockLoc, new ItemStack(Material.IRON_INGOT, 1));
                }
                else if(randNum >= 10 && randNum < 20){
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    p.setHealth(p.getHealth() - 1);
                }
            }

            if(block.getType().equals(Material.IRON_ORE) &&
                    (pickInHand.getType().equals(Material.STONE_PICKAXE) || pickInHand.getType().equals(Material.IRON_PICKAXE) || pickInHand.getType().equals(Material.DIAMOND_PICKAXE)))
            {
                if(randNum < 25)
                {
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    blockLoc.getWorld().spawnEntity(blockLoc, EntityType.ZOMBIE);
                }
                else if(randNum >= 25 && randNum < 40)
                {
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(blockLoc, new ItemStack(Material.GOLD_NUGGET, 3));
                }
                else
                {
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(blockLoc, new ItemStack(Material.IRON_INGOT, 1));
                    block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience((int) 1.0);
                }
            }
            if(block.getType().equals(Material.EMERALD_ORE) &&
                    (pickInHand.getType().equals(Material.IRON_PICKAXE) || pickInHand.getType().equals(Material.DIAMOND_PICKAXE)))
            {
                if(randNum < 20)
                {
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(blockLoc, new ItemStack(Material.GOLDEN_APPLE, 1));
                }
                else if(randNum >= 20 && randNum < 40)
                {
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    blockLoc.getWorld().spawnEntity(blockLoc, EntityType.SKELETON);
                    blockLoc.getWorld().spawnEntity(blockLoc, EntityType.SKELETON);
                }
                else if(randNum >= 40 && randNum < 70)
                {
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 1));
                }
            }
            if(block.getType().equals(Material.GOLD_ORE) &&
                    (pickInHand.getType().equals(Material.IRON_PICKAXE) || pickInHand.getType().equals(Material.DIAMOND_PICKAXE)))
            {
                if(randNum < 10){
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(blockLoc, new ItemStack(Material.GOLDEN_APPLE, 1));;
                }
                else if(randNum >= 10 && randNum < 35)
                {
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    blockLoc.getWorld().spawnEntity(blockLoc, EntityType.SKELETON);
                }
                else if(randNum >= 35 && randNum < 50)
                {
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    blockLoc.getWorld().spawnEntity(blockLoc, EntityType.CREEPER);
                }
                else
                {
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(blockLoc, new ItemStack(Material.GOLD_INGOT, 1));;
                    block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience((int) 1.0);
                }
            }
            if(block.getType().equals(Material.DIAMOND_ORE) &&
                    (pickInHand.getType().equals(Material.IRON_PICKAXE) || pickInHand.getType().equals(Material.DIAMOND_PICKAXE)))
            {
                if(randNum < 15){
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    Creeper creeper = (Creeper) blockLoc.getWorld().spawnEntity(blockLoc, EntityType.CREEPER);
                    creeper.setPowered(true);
                }
                else if(randNum >= 15 && randNum < 25)
                {
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    Entity e = blockLoc.getWorld().spawnEntity(blockLoc, EntityType.CREEPER);
                    //Creeper creeper = (Creeper) blockLoc.getWorld().spawnEntity(blockLoc, EntityType.CREEPER);
                    //CraftCreeper ccreeper = (CraftCreeper) creeper;

                    net.minecraft.server.v1_7_R4.Entity nmsCreeper = ((CraftEntity) e).getHandle();
                    NBTTagCompound tag = new NBTTagCompound();
                    nmsCreeper.c(tag);

                    NBTTagCompound nbtv = (NBTTagCompound) MojangsonParser.parse("{ignited:1}");
                    tag.b(nbtv.toString());
                    nmsCreeper.f(tag);
                }

                //if(p.isOp()){
                //p.performCommand("summon Creeper " + blockLoc.getBlockX() + " " + blockLoc.getBlockY() + " " + blockLoc.getBlockZ() + " {ignited:1}");
                //}
                //else{
                //p.setOp(true);
                //p.performCommand("summon Creeper " + blockLoc.getBlockX() + " " + blockLoc.getBlockY() + " " + blockLoc.getBlockZ() + " {ignited:1}");
                //p.setOp(false);
                //}
                else if(randNum == 25)
                {
                    pickInHand.setDurability((pickInHand.getDurability()));
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(blockLoc, new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1));;
                }
                else if(randNum > 25 && randNum < 35)
                {
                    block.getWorld().dropItemNaturally(blockLoc, new ItemStack(Material.GOLDEN_APPLE, 1));
                }
            }
        }
    }

}
