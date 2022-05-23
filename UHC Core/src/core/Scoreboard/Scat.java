package core.Scoreboard;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Scat implements Listener
{
    private Game game = new Game();
    Scoreboard scat = Bukkit.getScoreboardManager().getNewScoreboard();
    Main plugin = Main.getPlugin(Main.class);

    public void setScatter(Player p)
    {
        Objective obj = scat.registerNewObjective("Scatter", "Scoreboard");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "Scatter");

        Team scattered = scat.registerNewTeam("Scattered");
        scattered.addEntry(ChatColor.AQUA + "Scattered " + ChatColor.GRAY + "» ");

        obj.getScore("§m--------------").setScore(3);
        obj.getScore(ChatColor.AQUA + "Scattered " + ChatColor.GRAY + "» ").setScore(2);
        obj.getScore("§m--------------").setScore(1);
        obj.getScore(ChatColor.YELLOW + "Server IP").setScore(0);

        new BukkitRunnable()
        {
            public void run()
            {
                if(ConfigInventory.teamSize == 1)
                {
                    scattered.setSuffix("" + ChatColor.YELLOW + Scatter.ffaScattered);
                }
                else
                {
                    scattered.setSuffix("" + ChatColor.YELLOW + Scatter.teamsScattered);
                }
            }

        }.runTaskTimer(plugin, 0, 20);
    }
}
