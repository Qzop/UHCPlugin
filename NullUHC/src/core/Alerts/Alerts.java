package core.Alerts;

import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class Alerts implements Listener
{
    public static ArrayList<UUID> allalerts = new ArrayList<UUID>();
    public static ArrayList<UUID> xrayalerts = new ArrayList<UUID>();
    public static ArrayList<UUID> pvpalerts = new ArrayList<UUID>();
    public static ArrayList<UUID> reportalerts = new ArrayList<UUID>();
    public static String alertPref = ChatColor.GRAY + "[" + ChatColor.RED + "Alerts" + ChatColor.GRAY + "]";
    Main plugin = Main.getPlugin(Main.class);

    public void turnAlertsOn(Player p)
    {
        if(allalerts.contains(p.getUniqueId()))
        {
            allalerts.remove(p.getUniqueId());
            p.sendMessage(alertPref + ChatColor.YELLOW + " You have turned all Alerts " + ChatColor.GREEN + "On" + ChatColor.YELLOW + ".");
        }
        else
        {
            p.sendMessage(alertPref + ChatColor.RED + " You already have alerts on.");
        }
    }

    public void turnAlertsOff(Player p)
    {
        if(!allalerts.contains(p.getUniqueId()))
        {
            allalerts.add(p.getUniqueId());
            p.sendMessage(alertPref + ChatColor.YELLOW + " You have turned all Alerts " + ChatColor.RED + "Off" + ChatColor.YELLOW + ".");
        }
        else
        {
            p.sendMessage(alertPref + ChatColor.RED + " You already have alerts off.");
        }
    }

    public void turnSpecificAlertsOnAndOff(Player player)
    {
        Inventory inventory = Bukkit.getServer().createInventory(null, 27, ChatColor.RED + "Alerts");

        new BukkitRunnable()
        {
            public void run()
            {

                ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE);
                ItemMeta fillermeta = filler.getItemMeta();
                fillermeta.setDisplayName(" ");
                filler.setItemMeta(fillermeta);

                ItemStack xray = new ItemStack(Material.DIAMOND_PICKAXE);
                ItemMeta xraymeta = xray.getItemMeta();

                if(xrayalerts.contains(player.getUniqueId()) || allalerts.contains(player.getUniqueId()))
                {
                    xraymeta.setDisplayName(ChatColor.YELLOW + "Xray Alerts: " + ChatColor.RED + "Off");
                }
                else
                {
                    xraymeta.setDisplayName(ChatColor.YELLOW + "Xray Alerts: " + ChatColor.GREEN + "On");
                }
                xray.setItemMeta(xraymeta);

                ItemStack pvp = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta pvpmeta = pvp.getItemMeta();

                if(pvpalerts.contains(player.getUniqueId()) || allalerts.contains(player.getUniqueId()))
                {
                    pvpmeta.setDisplayName(ChatColor.YELLOW + "PvP Alerts: " + ChatColor.RED + "Off");
                }
                else
                {
                    pvpmeta.setDisplayName(ChatColor.YELLOW + "PvP Alerts: " + ChatColor.GREEN + "On");
                }
                pvp.setItemMeta(pvpmeta);

                ItemStack report = new ItemStack(Material.BOOK_AND_QUILL);
                ItemMeta reportmeta = report.getItemMeta();

                if(reportalerts.contains(player.getUniqueId()) || allalerts.contains(player.getUniqueId()))
                {
                    reportmeta.setDisplayName(ChatColor.YELLOW + "Report Alerts: " + ChatColor.RED + "Off");
                }
                else
                {
                    reportmeta.setDisplayName(ChatColor.YELLOW + "Report Alerts: " + ChatColor.GREEN + "On");
                }
                report.setItemMeta(reportmeta);

                inventory.setItem(0, filler);
                inventory.setItem(1, filler);
                inventory.setItem(2, filler);
                inventory.setItem(3, filler);
                inventory.setItem(4, filler);
                inventory.setItem(5, filler);
                inventory.setItem(6, filler);
                inventory.setItem(7, filler);
                inventory.setItem(8, filler);
                inventory.setItem(9, filler);

                inventory.setItem(12, xray);
                inventory.setItem(13, pvp);
                inventory.setItem(14, report);

                inventory.setItem(17, filler);
                inventory.setItem(18, filler);
                inventory.setItem(19, filler);
                inventory.setItem(20, filler);
                inventory.setItem(21, filler);
                inventory.setItem(22, filler);
                inventory.setItem(23, filler);
                inventory.setItem(24, filler);
                inventory.setItem(25, filler);
                inventory.setItem(26, filler);
            }

        }.runTaskTimer(plugin, 0, 1);

        player.openInventory(inventory);
    }
}
