package core.HostsMods;

import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class Helpop implements Listener
{
    private static ArrayList<UUID> used = new ArrayList<UUID>();
    public String helpPref = "" + ChatColor.GRAY + "[" + ChatColor.RED + "HelpOp" + ChatColor.GRAY + "]";
    Main plugin = Main.getPlugin(Main.class);
    public void onHelp(Player p, String s)
    {
        if(used.contains(p.getUniqueId()))
        {
            p.sendMessage(helpPref + ChatColor.RED + " You must wait 30 seconds to use HelpOp again!");
        }
        else
        {
            used.add(p.getUniqueId());
            p.sendMessage(helpPref + ChatColor.GREEN + " Your message has been sent successfully.");

            for(Player hm : Main.online.getOnlinePlayers())
            {
                if(hm.hasPermission("HelpOp.see"))
                {
                    hm.sendMessage(helpPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.YELLOW + s);
                }
            }

            new BukkitRunnable()
            {
                int seconds = 30;

                public void run()
                {
                    if(seconds == 0)
                    {
                        used.remove(p.getUniqueId());
                        p.sendMessage(helpPref + ChatColor.GREEN + " You may use HelpOp again.");
                        cancel();
                    }

                    seconds--;
                }

            }.runTaskTimer(plugin, 0, 20);
        }
    }
}
