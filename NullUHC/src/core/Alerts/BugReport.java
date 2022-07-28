package core.Alerts;

import core.mainPackage.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class BugReport implements Listener
{
    public static String bugreportPref = ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "BugReport" + ChatColor.GRAY + "]";
    public static ArrayList<UUID> bugreportWait = new ArrayList<UUID>();
    Main plugin = Main.getPlugin(Main.class);

    public void sendBugreport(Player p, String message)
    {
        // This will be our bug report system for now. Later ill add reports to a database.

        for(Player player : Main.online.getOnlinePlayers())
        {
            if(player.hasPermission("uhc.bugreports"))
            {
                player.sendMessage(bugreportPref + ChatColor.LIGHT_PURPLE + " " + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.YELLOW + message);
            }
        }

        onWait(p);
    }

    public void onWait(Player p)
    {
        bugreportWait.add(p.getUniqueId());

        new BukkitRunnable()
        {
            int seconds = 30;

            public void run()
            {
                if(seconds == 0)
                {
                    p.sendMessage(bugreportPref + ChatColor.GREEN + " You may now use /bugreport.");
                    bugreportWait.remove(p.getUniqueId());
                    cancel();
                }

                seconds--;
            }

        }.runTaskTimer(plugin, 0, 20);
    }
}