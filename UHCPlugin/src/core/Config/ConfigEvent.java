package core.Config;

import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ConfigEvent implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
    public static String Confprefix = ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "Config" + ChatColor.GRAY + "]";

    @EventHandler
    public void inventoryClick(InventoryClickEvent e)
    {
        Inventory inv = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();

        ClickType click = e.getClick();

        if(inv == null)
        {
            return;
        }
        else if(inv.getName().equals(ChatColor.AQUA + "Config"))
        {
            e.setCancelled(true);

            if(item == null || !item.hasItemMeta())
            {
                return;
            }
        }
        else if(inv.getName().equals(ChatColor.AQUA + "Config Admin"))
        {
            e.setCancelled(true);

            if(item == null || !item.hasItemMeta())
            {
                return;
            }

            if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Apple Rate: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.applerate + "%"))
            {
                if(click == ClickType.LEFT)
                {
                    if(ConfigInventory.applerate < 100.0)
                    {
                        ConfigInventory.applerate++;

                        for(Player player : Bukkit.getOnlinePlayers())
                        {
                            player.sendMessage(Confprefix + ChatColor.YELLOW + " Apple Rate is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.applerate + "%.");
                        }
                    }
                }
                else if(click == ClickType.RIGHT)
                {
                    if(ConfigInventory.applerate > 1.0)
                    {
                        ConfigInventory.applerate--;

                        for(Player player : Bukkit.getOnlinePlayers())
                        {
                            player.sendMessage(Confprefix + ChatColor.YELLOW + " Apple Rate is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.applerate + "%.");
                        }
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Teamsize: " + ChatColor.AQUA + ChatColor.BOLD + "FFA"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.teamSize++;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " TeamSize is now " + ChatColor.AQUA + ChatColor.BOLD + "Team of " + ConfigInventory.teamSize + ".");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Teamsize: " + ChatColor.AQUA + ChatColor.BOLD + "Team of " + ConfigInventory.teamSize))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.teamSize++;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " TeamSize is now " + ChatColor.AQUA + ChatColor.BOLD + "Team of " + ConfigInventory.teamSize + ".");
                    }
                }
                else if(click == ClickType.RIGHT)
                {
                    if(ConfigInventory.teamSize > 1)
                    {
                        ConfigInventory.teamSize--;

                        if(ConfigInventory.teamSize == 1)
                        {
                            for(Player player : Bukkit.getOnlinePlayers())
                            {
                                player.sendMessage(Confprefix + ChatColor.YELLOW + " TeamSize is now " + ChatColor.AQUA + ChatColor.BOLD + "FFA.");
                            }
                        }
                        else
                        {
                            for(Player player : Bukkit.getOnlinePlayers())
                            {
                                player.sendMessage(Confprefix + ChatColor.YELLOW + " TeamSize is now " + ChatColor.AQUA + ChatColor.BOLD + "Team of " + ConfigInventory.teamSize + ".");
                            }
                        }
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Nether: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.netherBoo = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Nether is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Nether: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.netherBoo = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Nether is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Border Size: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.borderSize))
            {
                if(click == ClickType.LEFT)
                {
                    if(ConfigInventory.borderSize == 25)
                    {
                        ConfigInventory.borderSize = 50;
                    }
                    else if(ConfigInventory.borderSize == 50)
                    {
                        ConfigInventory.borderSize = 100;
                    }
                    else if(ConfigInventory.borderSize == 100)
                    {
                        ConfigInventory.borderSize = 250;
                    }
                    else if(ConfigInventory.borderSize == 250)
                    {
                        ConfigInventory.borderSize = 500;
                    }
                    else if(ConfigInventory.borderSize == 500)
                    {
                        ConfigInventory.borderSize = 1000;
                    }
                    else if(ConfigInventory.borderSize == 1000)
                    {
                        ConfigInventory.borderSize = 1500;
                    }
                    else if(ConfigInventory.borderSize == 1500)
                    {
                        ConfigInventory.borderSize = 3000;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " World BorderSize is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.borderSize + "x" + ConfigInventory.borderSize + ".");
                    }
                }
                else if(click == ClickType.RIGHT)
                {
                    if(ConfigInventory.borderSize == 50)
                    {
                        ConfigInventory.borderSize = 25;
                    }
                    else if(ConfigInventory.borderSize == 100)
                    {
                        ConfigInventory.borderSize = 50;
                    }
                    else if(ConfigInventory.borderSize == 250)
                    {
                        ConfigInventory.borderSize = 100;
                    }
                    else if(ConfigInventory.borderSize == 500)
                    {
                        ConfigInventory.borderSize = 250;
                    }
                    else if(ConfigInventory.borderSize == 1000)
                    {
                        ConfigInventory.borderSize = 500;
                    }
                    else if(ConfigInventory.borderSize == 1500)
                    {
                        ConfigInventory.borderSize = 1000;
                    }
                    else if(ConfigInventory.borderSize == 3000)
                    {
                        ConfigInventory.borderSize = 1500;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " World BorderSize is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.borderSize + "x" + ConfigInventory.borderSize + ".");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Speed 1: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.sp1 = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Speed 1 is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Speed 1: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.sp1 = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Speed 1 is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Speed 2: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.sp2 = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Speed 2 is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Speed 2: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.sp2 = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Speed 2 is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Team Damage: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.teamDamage = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Team Damage is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Team Damage: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.teamDamage = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Team Damage is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Passive Mobs: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.passiveMobs = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Passive Mobs is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Passive Mobs: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.passiveMobs = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Passive Mobs is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals( ChatColor.GOLD + "Starter Food: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.sFood + " Steak"))
            {
                if(click == ClickType.LEFT)
                {
                    if(ConfigInventory.sFood < 60)
                    {
                        ConfigInventory.sFood += 5;
                    }
                    else if(ConfigInventory.sFood == 60)
                    {
                        ConfigInventory.sFood = 64;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Starter Food is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.sFood + " Steak.");
                    }
                }
                else if(click == ClickType.RIGHT)
                {
                    if(ConfigInventory.sFood > 20 && ConfigInventory.sFood <= 60)
                    {
                        ConfigInventory.sFood -= 5;
                    }
                    else if(ConfigInventory.sFood == 64)
                    {
                        ConfigInventory.sFood = 60;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Starter Food is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.sFood + " Steak.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Nether Border Size: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.nBorderSize))
            {
                if(click == ClickType.LEFT)
                {
                    if(ConfigInventory.nBorderSize >= 500 && ConfigInventory.nBorderSize < 2500)
                    {
                        ConfigInventory.nBorderSize += 500;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Nether BorderSize is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.nBorderSize + "x" + ConfigInventory.nBorderSize + ".");
                    }
                }
                if(click == ClickType.RIGHT)
                {
                    if(ConfigInventory.nBorderSize > 500 && ConfigInventory.nBorderSize <= 2500)
                    {
                        ConfigInventory.nBorderSize -= 500;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Nether BorderSize is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.nBorderSize + "x" + ConfigInventory.nBorderSize + ".");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Stalking during grace period: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.stalk = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Stalking during grace period is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Stalking during grace period: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.stalk = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Stalking during grace period is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Skybasing within 100x100: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.skybasing = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " SkyBasing within 100x100 is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Skybasing within 100x100: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.skybasing = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " SkyBasing within 100x100 is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Stealing during grace period: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.stealing = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Stealing during grace period is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Stealing during grace period: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.stealing = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Stealing during grace period is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Running at Meetup: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.runAtMeet = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Running at Meetup is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Running at Meetup: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.runAtMeet = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Running at Meetup is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "CrossTeaming: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.crossteaming = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " CrossTeaming is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "CrossTeaming: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.crossteaming = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " CrossTeaming is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Absorption: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.absorption + " Hearts"))
            {
                if(click == ClickType.LEFT)
                {
                    if(ConfigInventory.absorption >= 1 && ConfigInventory.absorption < 4)
                    {
                        ConfigInventory.absorption++;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Absorption is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.absorption + " Hearts.");
                    }
                }
                else if(click == ClickType.RIGHT)
                {
                    if(ConfigInventory.absorption <= 4 && ConfigInventory.absorption > 1)
                    {
                        ConfigInventory.absorption--;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Absorption is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.absorption + " Hearts.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "BookShelves: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.bookshelves = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " BookShelves is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "BookShelves: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.bookshelves = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " BookShelves is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "FireWeapons: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.fireweapons = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " FireWeapons is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "FireWeapons: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.fireweapons = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " FireWeapons is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Golden Heads: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.gHead = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Golden Heads is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Golden Heads: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.gHead = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Golden Heads is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "God Apples: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.godApp = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " God Apples is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "God Apples: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.godApp = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " God Apples is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Horses: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.horses = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Horses are now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Horses: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.horses = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Horses are now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Pearl Damage: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.pearlDmg = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Pearl Damage is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Pearl Damage: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.pearlDmg = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Pearl Damage is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Strip Mining: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.stripMining = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Strip Mining is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Strip Mining: " + ChatColor.RED+ ChatColor.BOLD + "Not Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.stripMining = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Strip Mining is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Poke Holing: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.pokeHoling = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Poke Holing is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Poke Holing: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.pokeHoling = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Poke Holing is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Roller-coasting: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.rollerCoasting = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Roller-coasting is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Roller-coasting: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.rollerCoasting = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Roller-coasting is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Grace Period: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.gracePeriod + " Minutes"))
            {
                if(click == ClickType.LEFT)
                {
                    if(ConfigInventory.gracePeriod >= 10 && ConfigInventory.gracePeriod < 30)
                    {
                        ConfigInventory.gracePeriod += 5;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Grace Period is now ending at " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.gracePeriod + " Minutes.");
                    }
                }
                else if(click == ClickType.RIGHT)
                {
                    if(ConfigInventory.gracePeriod > 10 && ConfigInventory.gracePeriod <= 30)
                    {
                        ConfigInventory.gracePeriod -= 5;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Grace Period is now ending at " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.gracePeriod + " Minutes.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Flint Rates: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.flintRate + "%"))
            {
                if(click == ClickType.LEFT)
                {
                    if(ConfigInventory.flintRate >= 25.0 && ConfigInventory.flintRate < 100.0)
                    {
                        ConfigInventory.flintRate += 5.0;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Flint Rate is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.flintRate + "%.");
                    }
                }
                else if(click == ClickType.RIGHT)
                {
                    if(ConfigInventory.flintRate > 25.0 && ConfigInventory.flintRate <= 100.0)
                    {
                        ConfigInventory.flintRate -= 5.0;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Flint Rate is now " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.flintRate + "%.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Final Heal: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.finalHeal + " Minutes"))
            {
                if(click == ClickType.LEFT)
                {
                    if(ConfigInventory.finalHeal >= 5 && ConfigInventory.finalHeal < 25)
                    {
                        ConfigInventory.finalHeal += 5;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Final Heal is now at " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.finalHeal + " Minutes.");
                    }
                }
                else if(click == ClickType.RIGHT)
                {
                    if(ConfigInventory.finalHeal > 5 && ConfigInventory.finalHeal <= 25)
                    {
                        ConfigInventory.finalHeal -= 5;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Final Heal is now at " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.finalHeal + " Minutes.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Meetup: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.meetUp + " Minutes"))
            {
                if(click == ClickType.LEFT)
                {
                    if(ConfigInventory.meetUp >= 35 && ConfigInventory.meetUp < 60)
                    {
                        ConfigInventory.meetUp += 5;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " MeetUp is now at " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.meetUp + " Minutes.");
                    }
                }
                else if(click == ClickType.RIGHT)
                {
                    if(ConfigInventory.meetUp > 35 && ConfigInventory.meetUp <= 60)
                    {
                        ConfigInventory.meetUp -= 5;
                    }

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " MeetUp is now at " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.meetUp + " Minutes.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Strength 1: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.strength1 = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Strength 1 is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Strength 1: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.strength1 = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Strength 1 is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Strength 2: " + ChatColor.GREEN + ChatColor.BOLD + "On"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.strength2 = false;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Strength 2 is now " + ChatColor.RED + ChatColor.BOLD + "Off.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Strength 2: " + ChatColor.RED + ChatColor.BOLD + "Off"))
            {
                if(click == ClickType.LEFT)
                {
                    ConfigInventory.strength2 = true;

                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        player.sendMessage(Confprefix + ChatColor.YELLOW + " Strength 2 is now " + ChatColor.GREEN + ChatColor.BOLD + "On.");
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "LateScatter: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.latescatter + " Minutes"))
            {
                if(click == ClickType.LEFT)
                {
                    if(ConfigInventory.latescatter != 30)
                    {
                        ConfigInventory.latescatter += 5;

                        for(Player player : Bukkit.getOnlinePlayers())
                        {
                            player.sendMessage(Confprefix + ChatColor.YELLOW + " LateScatter period is until " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.latescatter + " Minutes.");
                        }
                    }
                }
                else if(click == ClickType.RIGHT)
                {
                    if(ConfigInventory.latescatter != 10)
                    {
                        ConfigInventory.latescatter -= 5;

                        for(Player player : Bukkit.getOnlinePlayers())
                        {
                            player.sendMessage(Confprefix + ChatColor.YELLOW + " LateScatter period is until " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.latescatter + " Minutes.");
                        }
                    }
                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "First Shrink: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.firstShrink + " Minutes"))
            {
                if(click == ClickType.LEFT)
                {

                }
                else if(click == ClickType.RIGHT)
                {

                }
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Shrink Interval: " + ChatColor.AQUA + ChatColor.BOLD + ConfigInventory.shrinkInterval + " Minutes"))
            {
                if(click == ClickType.LEFT)
                {

                }
                else if(click == ClickType.RIGHT)
                {

                }
            }
        }
    }
}