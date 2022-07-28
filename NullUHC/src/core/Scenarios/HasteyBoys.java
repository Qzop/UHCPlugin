package core.Scenarios;

import core.ScenariosInventory.ScenariosInventory;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.logging.Logger;

public class HasteyBoys implements Listener
{
    DoScenario doScen = new DoScenario();

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event)
    {
        if (ScenariosInventory.hastey)
        {
            Material result = event.getInventory().getResult().getType();
            ItemStack change;
            ItemMeta meta;

            switch (result)
            {
                case WOOD_PICKAXE:
                    change = new ItemStack(Material.STONE_PICKAXE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case STONE_PICKAXE:
                    change = new ItemStack(Material.STONE_PICKAXE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case IRON_PICKAXE:
                    change = new ItemStack(Material.IRON_PICKAXE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case DIAMOND_PICKAXE:
                    change = new ItemStack(Material.DIAMOND_PICKAXE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case GOLD_PICKAXE:
                    change = new ItemStack(Material.GOLD_PICKAXE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case WOOD_AXE:
                    change = new ItemStack(Material.STONE_AXE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case STONE_AXE:
                    change = new ItemStack(Material.STONE_AXE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case IRON_AXE:
                    change = new ItemStack(Material.IRON_AXE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case DIAMOND_AXE:
                    change = new ItemStack(Material.DIAMOND_AXE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case GOLD_AXE:
                    change = new ItemStack(Material.GOLD_AXE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case WOOD_SPADE:
                    change = new ItemStack(Material.STONE_SPADE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case STONE_SPADE:
                    change = new ItemStack(Material.STONE_SPADE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case IRON_SPADE:
                    change = new ItemStack(Material.IRON_SPADE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case DIAMOND_SPADE:
                    change = new ItemStack(Material.DIAMOND_SPADE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;
                case GOLD_SPADE:
                    change = new ItemStack(Material.GOLD_SPADE);
                    meta = change.getItemMeta();
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DURABILITY, 3, true);
                    change.setItemMeta(meta);
                    event.getInventory().setResult(change);
                    break;

            }
        }
    }
    public void OnInvClose(InventoryCloseEvent e)
    {
        Player p = (Player) e.getPlayer();

        if(doScen.doScenario(p) && ScenariosInventory.hastey)
        {
            for(int i = 0; i < e.getInventory().getSize(); i++)
            {
                Material item = e.getInventory().getItem(i).getType();

                if(e.getInventory().getItem(i).getItemMeta().hasEnchant(Enchantment.DIG_SPEED) && e.getInventory().getItem(i).getItemMeta().hasEnchant(Enchantment.DURABILITY))
                {
                    continue;
                }
                ItemStack change;
                ItemMeta meta;

                switch (item)
                {
                    case WOOD_PICKAXE:
                        change = new ItemStack(Material.STONE_PICKAXE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case STONE_PICKAXE:
                        change = new ItemStack(Material.STONE_PICKAXE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case IRON_PICKAXE:
                        change = new ItemStack(Material.IRON_PICKAXE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case DIAMOND_PICKAXE:
                        change = new ItemStack(Material.DIAMOND_PICKAXE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case GOLD_PICKAXE:
                        change = new ItemStack(Material.GOLD_PICKAXE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case WOOD_AXE:
                        change = new ItemStack(Material.STONE_AXE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case STONE_AXE:
                        change = new ItemStack(Material.STONE_AXE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case IRON_AXE:
                        change = new ItemStack(Material.IRON_AXE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case DIAMOND_AXE:
                        change = new ItemStack(Material.DIAMOND_AXE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case GOLD_AXE:
                        change = new ItemStack(Material.GOLD_AXE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case WOOD_SPADE:
                        change = new ItemStack(Material.STONE_SPADE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case STONE_SPADE:
                        change = new ItemStack(Material.STONE_SPADE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case IRON_SPADE:
                        change = new ItemStack(Material.IRON_SPADE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case DIAMOND_SPADE:
                        change = new ItemStack(Material.DIAMOND_SPADE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;
                    case GOLD_SPADE:
                        change = new ItemStack(Material.GOLD_SPADE);
                        meta = change.getItemMeta();
                        meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                        meta.addEnchant(Enchantment.DURABILITY, 3, true);
                        change.setItemMeta(meta);
                        e.getInventory().setItem(i, change);
                        break;

                }
            }
        }
    }
}