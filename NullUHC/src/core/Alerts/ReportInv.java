package core.Alerts;

import core.mainPackage.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ReportInv implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
    public static HashMap<UUID, UUID> report = new HashMap<UUID, UUID>();
    public static ArrayList<UUID> reportWait = new ArrayList<UUID>();
    public static String reportPref = ChatColor.GRAY + "[" + ChatColor.RED + "Report" + ChatColor.GRAY + "]";

    public void onReport(Player p)
    {
        Inventory i = plugin.getServer().createInventory(null, 36, ChatColor.RED + "Report");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta fillermeta = filler.getItemMeta();
        fillermeta.setDisplayName(" ");
        filler.setItemMeta(fillermeta);

        ItemStack xray = new ItemStack(Material.DIAMOND_ORE);
        ItemMeta xraymeta = xray.getItemMeta();
        xraymeta.setDisplayName(ChatColor.GOLD + "Xray/CaveFinder");
        xray.setItemMeta(xraymeta);

        ItemStack tracers = new ItemStack(Material.BOOK);
        ItemMeta tracersmeta = tracers.getItemMeta();
        tracersmeta.setDisplayName(ChatColor.GOLD + "Tracers");
        tracers.setItemMeta(tracersmeta);

        ItemStack combathacks = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta combathacksmeta = combathacks.getItemMeta();
        combathacksmeta.setDisplayName(ChatColor.GOLD + "Combat Cheats");
        combathacks.setItemMeta(combathacksmeta);

        ItemStack bugabuse = new ItemStack(Material.COMMAND);
        ItemMeta bugabusemeta = bugabuse.getItemMeta();
        bugabusemeta.setDisplayName(ChatColor.GOLD + "Bug Abuse");
        bugabuse.setItemMeta(bugabusemeta);

        ItemStack harassment = new ItemStack(Material.WATCH);
        ItemMeta harassmentmeta = harassment.getItemMeta();
        harassmentmeta.setDisplayName(ChatColor.GOLD + "Harassment/Disrespectful Behavior");
        harassment.setItemMeta(harassmentmeta);

        ItemStack spam = new ItemStack(Material.COMPASS);
        ItemMeta spammeta = spam.getItemMeta();
        spammeta.setDisplayName(ChatColor.GOLD + "Spam");
        spam.setItemMeta(spammeta);

        i.setItem(0, filler);
        i.setItem(1, filler);
        i.setItem(2, filler);
        i.setItem(3, filler);
        i.setItem(4, filler);
        i.setItem(5, filler);
        i.setItem(6, filler);
        i.setItem(7, filler);
        i.setItem(8, filler);
        i.setItem(9, filler);

        i.setItem(12, xray);
        i.setItem(13, tracers);
        i.setItem(14, combathacks);

        i.setItem(17, filler);
        i.setItem(18, filler);

        i.setItem(21, bugabuse);
        i.setItem(22, harassment);
        i.setItem(23, spam);

        i.setItem(26, filler);
        i.setItem(27, filler);
        i.setItem(28, filler);
        i.setItem(29, filler);
        i.setItem(30, filler);
        i.setItem(31, filler);
        i.setItem(32, filler);
        i.setItem(33, filler);
        i.setItem(34, filler);
        i.setItem(35, filler);

        p.openInventory(i);
    }

    public void onWait(Player p)
    {
        reportWait.add(p.getUniqueId());
        report.remove(p.getUniqueId());

        new BukkitRunnable()
        {
            int seconds = 30;

            public void run()
            {
                if(seconds == 0)
                {
                    reportWait.remove(p.getUniqueId());
                    p.sendMessage(reportPref + ChatColor.GREEN + " You may use /report now.");
                    cancel();
                }

                seconds--;
            }

        }.runTaskTimer(plugin, 0, 20);
    }
}
