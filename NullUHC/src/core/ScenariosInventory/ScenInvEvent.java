package core.ScenariosInventory;

import core.Config.ConfigInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ScenInvEvent implements Listener
{
    private String scenariosPref = ChatColor.GRAY + "[" + ChatColor.AQUA + "Scenarios" + ChatColor.GRAY + "]";

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        Inventory inv = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        ClickType click = e.getClick();
        Player p = (Player) e.getWhoClicked();

        if(inv == null)
        {
            return;
        }
        else if(inv.getName().equals(ChatColor.AQUA + "Scenarios List"))
        {
            e.setCancelled(true);

            if(item == null || !item.hasItemMeta())
            {
                return;
            }
        }
        else if(inv.getName().equals(ChatColor.AQUA + "Scenarios Admin"))
        {
            e.setCancelled(true);

            if (item == null || !item.hasItemMeta())
            {
                return;
            }

            if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Hastey Boys: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Hastey Boys");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(item);
                ScenariosInventory.hastey = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Hastey Boys is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Hastey Boys: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Hastey Boys");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.add(item);
                ScenariosInventory.hastey = true;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Hastey Boys is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "SuperHeroes: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "SuperHeroes");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(item);
                ScenariosInventory.superheroes = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " SuperHeroes is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "SuperHeroes: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "SuperHeroes");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.add(item);
                ScenariosInventory.superheroes = true;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " SuperHeroes is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "CutClean: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "CutClean");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(item);
                ScenariosInventory.cutclean = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " CutClean is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "CutClean: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "CutClean");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.add(item);
                ScenariosInventory.cutclean = true;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " CutClean is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Lucky Ores: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Lucky Ores");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(item);
                ScenariosInventory.lucky = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Lucky Ores is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Lucky Ores: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Lucky Ores");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.add(item);
                ScenariosInventory.lucky = true;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Lucky Ores is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Timber: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Timber");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(item);
                ScenariosInventory.timber = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Timber is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Timber: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Timber");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.add(item);
                ScenariosInventory.timber = true;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Timber is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "TimeBomb: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "TimeBomb");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(item);
                ScenariosInventory.timebomb = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " TimeBomb is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "TimeBomb: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "TimeBomb");
                item.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.add(item);
                ScenariosInventory.timebomb = true;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " TimeBomb is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
            }
        }
    }
}
