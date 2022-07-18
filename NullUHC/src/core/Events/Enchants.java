package core.Events;

import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;
import org.bukkit.scheduler.BukkitRunnable;

public class Enchants implements Listener
{
    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onEnchantmentTable(InventoryOpenEvent e)
    {
        if(e.getInventory() instanceof EnchantingInventory)
        {
            EnchantingInventory inv = (EnchantingInventory) e.getInventory();
            Dye d = new Dye();
            d.setColor(DyeColor.BLUE);
            ItemStack i = d.toItemStack();
            i.setAmount(64);
            inv.setItem(1, i);
        }
        else if(e.getInventory() instanceof AnvilInventory)
        {
            AnvilInventory inv = (AnvilInventory) e.getInventory();

        }
    }

    @EventHandler
    public void onCloseEnchantmentTable(InventoryCloseEvent e)
    {
        if(e.getInventory() instanceof EnchantingInventory)
        {
            EnchantingInventory inv = (EnchantingInventory) e.getInventory();
            Dye d = new Dye();
            d.setColor(DyeColor.BLUE);
            ItemStack i = d.toItemStack();
            inv.setItem(1, null);
        }
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent e)
    {
        if(e.getInventory() instanceof EnchantingInventory)
        {
            EnchantingInventory inv = (EnchantingInventory) e.getInventory();
            Dye d = new Dye();
            d.setColor(DyeColor.BLUE);
            ItemStack i = d.toItemStack();
            i.setAmount(64);
            inv.setItem(1, i);
        }
    }
}
