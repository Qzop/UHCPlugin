package core.ScenariosInventory;

import core.Config.ConfigInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Hastey Boys");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.hastey = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Hastey Boys is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Hastey Boys: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Hastey Boys");
                temp.setItemMeta(itemmeta);

                if(ScenariosInventory.enabledScenarios.size() < 7)
                {
                    ScenariosInventory.enabledScenarios.add(temp);
                    ScenariosInventory.hastey = true;

                    Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Hastey Boys is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "SuperHeroes: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "SuperHeroes");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.superheroes = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " SuperHeroes is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "SuperHeroes: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(ConfigInventory.teamSize > 1)
                {
                    ItemStack temp = item;
                    ItemMeta itemmeta = temp.getItemMeta();
                    itemmeta.setDisplayName(ChatColor.YELLOW + "SuperHeroes");
                    temp.setItemMeta(itemmeta);

                    if(ScenariosInventory.enabledScenarios.size() < 7)
                    {
                        ScenariosInventory.enabledScenarios.add(temp);
                        ScenariosInventory.superheroes = true;

                        Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " SuperHeroes is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                    }
                    else
                    {
                        p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                    }
                }
                else
                {
                    p.sendMessage(scenariosPref + ChatColor.RED + " You must turn teams on for Superheroes!");
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "CutClean: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "CutClean");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.cutclean = false;

                ConfigInventory.flintRate = 50.0;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " CutClean is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "CutClean: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(ScenariosInventory.tripleores || ScenariosInventory.doubleores || ScenariosInventory.veinminer)
                {
                    p.closeInventory();
                    p.sendMessage(scenariosPref + ChatColor.RED + " You must disable one of the following scenarios: " + ChatColor.YELLOW + "TripleOres, DoublesOres, VeinMiner"
                            + ChatColor.RED + " before turning on CutClean.");
                }
                else
                {
                    ItemStack temp = item;
                    ItemMeta itemmeta = temp.getItemMeta();
                    itemmeta.setDisplayName(ChatColor.YELLOW + "CutClean");
                    temp.setItemMeta(itemmeta);

                    if(ScenariosInventory.enabledScenarios.size() < 7)
                    {
                        ScenariosInventory.enabledScenarios.add(temp);
                        ScenariosInventory.cutclean = true;
                        ConfigInventory.flintRate = 100.0;
                        Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " CutClean is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                    }
                    else
                    {
                        p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Lucky Ores: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Lucky Ores");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.lucky = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Lucky Ores is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Lucky Ores: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Lucky Ores");
                temp.setItemMeta(itemmeta);

                if(ScenariosInventory.enabledScenarios.size() < 7)
                {
                    ScenariosInventory.enabledScenarios.add(temp);
                    ScenariosInventory.lucky = true;

                    Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Lucky Ores is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Timber: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Timber");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.timber = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Timber is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Timber: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Timber");
                temp.setItemMeta(itemmeta);

                if(ScenariosInventory.enabledScenarios.size() < 7)
                {
                    ScenariosInventory.enabledScenarios.add(temp);
                    ScenariosInventory.timber = true;

                    Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Timber is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "TimeBomb: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "TimeBomb");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.timebomb = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " TimeBomb is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "TimeBomb: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "TimeBomb");
                temp.setItemMeta(itemmeta);

                if(ScenariosInventory.enabledScenarios.size() < 7)
                {
                    ScenariosInventory.enabledScenarios.add(temp);
                    ScenariosInventory.timebomb = true;

                    Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " TimeBomb is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "TripleOres: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "TripleOres");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.tripleores = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " TripleOres is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "TripleOres: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(ScenariosInventory.cutclean || ScenariosInventory.doubleores || ScenariosInventory.veinminer)
                {
                    p.closeInventory();
                    p.sendMessage(scenariosPref + ChatColor.RED + " You must disable one of the following scenarios: " + ChatColor.YELLOW + "CutClean, DoublesOres, VeinMiner"
                            + ChatColor.RED + " before turning on TripleOres.");
                }
                else
                {
                    ItemStack temp = item;
                    ItemMeta itemmeta = temp.getItemMeta();
                    itemmeta.setDisplayName(ChatColor.YELLOW + "TripleOres");
                    temp.setItemMeta(itemmeta);

                    if(ScenariosInventory.enabledScenarios.size() < 7)
                    {
                        ScenariosInventory.enabledScenarios.add(temp);
                        ScenariosInventory.tripleores = true;

                        Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " TripleOres is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                    }
                    else
                    {
                        p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "DoubleOres: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "DoubleOres");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.doubleores = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " DoubleOres is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "DoubleOres: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(ScenariosInventory.cutclean || ScenariosInventory.tripleores || ScenariosInventory.veinminer)
                {
                    p.closeInventory();
                    p.sendMessage(scenariosPref + ChatColor.RED + " You must disable one of the following scenarios: " + ChatColor.YELLOW + "CutClean, TripleOres, VeinMiner"
                            + ChatColor.RED + " before turning on DoubleOres.");
                }
                else
                {
                    ItemStack temp = item;
                    ItemMeta itemmeta = temp.getItemMeta();
                    itemmeta.setDisplayName(ChatColor.YELLOW + "DoubleOres");
                    temp.setItemMeta(itemmeta);

                    if(ScenariosInventory.enabledScenarios.size() < 7)
                    {
                        ScenariosInventory.enabledScenarios.add(temp);
                        ScenariosInventory.doubleores = true;

                        Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " DoubleOres is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                    }
                    else
                    {
                        p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Goldless: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Goldless");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.goldless = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Goldless is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Goldless: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Goldless");
                temp.setItemMeta(itemmeta);

                if(ScenariosInventory.enabledScenarios.size() < 7)
                {
                    ScenariosInventory.enabledScenarios.add(temp);
                    ScenariosInventory.goldless = true;

                    Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Goldless is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Diamondless: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Diamondless");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.diamondless = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Diamondless is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Diamondless: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Diamondless");
                temp.setItemMeta(itemmeta);

                if(ScenariosInventory.enabledScenarios.size() < 7)
                {
                    ScenariosInventory.enabledScenarios.add(temp);
                    ScenariosInventory.diamondless = true;

                    Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Diamondless is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "VeinMiner: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "VeinMiner");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.veinminer = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " VeinMiner is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "VeinMiner: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(ScenariosInventory.cutclean || ScenariosInventory.tripleores || ScenariosInventory.veinminer)
                {
                    p.closeInventory();
                    p.sendMessage(scenariosPref + ChatColor.RED + " You must disable one of the following scenarios: " + ChatColor.YELLOW + "CutClean, TripleOres, DoubleOres"
                            + ChatColor.RED + " before turning on VeinMiner.");
                }
                else
                {
                    ItemStack temp = item;
                    ItemMeta itemmeta = temp.getItemMeta();
                    itemmeta.setDisplayName(ChatColor.YELLOW + "VeinMiner");
                    temp.setItemMeta(itemmeta);

                    if(ScenariosInventory.enabledScenarios.size() < 7)
                    {
                        ScenariosInventory.enabledScenarios.add(temp);
                        ScenariosInventory.veinminer = true;

                        Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " VeinMiner is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                    }
                    else
                    {
                        p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "NoFall: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "NoFall");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.nofall = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " NoFall is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "NoFall: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "NoFall");
                temp.setItemMeta(itemmeta);

                if(ScenariosInventory.enabledScenarios.size() < 7)
                {
                    ScenariosInventory.enabledScenarios.add(temp);
                    ScenariosInventory.nofall = true;

                    Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " NoFall is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Fireless: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Fireless");
                temp.setItemMeta(itemmeta);

                ScenariosInventory.enabledScenarios.remove(temp);
                ScenariosInventory.fireless = false;

                Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Fireless is now " + ChatColor.RED + ChatColor.BOLD + "Off");
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Fireless: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                ItemStack temp = item;
                ItemMeta itemmeta = temp.getItemMeta();
                itemmeta.setDisplayName(ChatColor.YELLOW + "Fireless");
                temp.setItemMeta(itemmeta);

                if(ScenariosInventory.enabledScenarios.size() < 7)
                {
                    ScenariosInventory.enabledScenarios.add(temp);
                    ScenariosInventory.fireless = true;

                    Bukkit.broadcastMessage(scenariosPref + ChatColor.YELLOW + " Fireless is now " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "You can only have 7 scenarios at once!");
                }
            }
        }
    }
}