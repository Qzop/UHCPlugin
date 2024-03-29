package core.ConfigVariables;

import core.Config.ConfigInventory;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class SpeedStrength implements Listener
{
    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e)
    {
        Player p = e.getPlayer();

        ItemStack godApple = new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);

        if(e.getItem().equals(godApple))
        {
            if(!ConfigInventory.godApp)
            {
                e.setCancelled(true);
            }
        }
        else
        {
            try
            {
                ItemStack stack = e.getItem();
                Potion potion = Potion.fromItemStack(stack);

                if(!ConfigInventory.strength1)
                {
                    if(potion.getType().equals(PotionType.STRENGTH) && potion.getLevel() == 1)
                    {
                        p.sendMessage(ChatColor.RED + "Strength 1 is off!");
                        e.setCancelled(true);
                    }
                }

                if(!ConfigInventory.strength2)
                {
                    if(potion.getType().equals(PotionType.STRENGTH) && potion.getLevel() == 2)
                    {
                        p.sendMessage(ChatColor.RED + "Strength 2 is off!");
                        e.setCancelled(true);
                    }
                }

                if(!ConfigInventory.sp1)
                {
                    if(potion.getType().equals(PotionType.SPEED) && potion.getLevel() == 1)
                    {
                        p.sendMessage(ChatColor.RED + "Speed 1 is off!");
                        e.setCancelled(true);
                    }
                }

                if(!ConfigInventory.sp2)
                {
                    if(potion.getType() == PotionType.SPEED && potion.getLevel() == 2)
                    {
                        p.sendMessage(ChatColor.RED + "Speed 2 is off!");
                        e.setCancelled(true);
                    }
                }
            }
            catch(IllegalArgumentException ex)
            {
                // do nothing, just catch the exception.
            }
        }
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent e)
    {
        ItemStack godApple = new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);

        if(e.getRecipe().getResult().equals(godApple))
        {
            if(!ConfigInventory.godApp)
            {
                e.getInventory().setResult(new ItemStack(Material.AIR));
            }
        }
    }
}