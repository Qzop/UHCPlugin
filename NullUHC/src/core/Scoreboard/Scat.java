package core.Scoreboard;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class Scat implements Listener
{
    private Game game = new Game();
    Main plugin = Main.getPlugin(Main.class);

    public void setScatter(Player p)
    {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scat = manager.getNewScoreboard();

        Team scattered = scat.registerNewTeam("Scattered");
        scattered.addEntry(ChatColor.AQUA + "Scattered " + ChatColor.GRAY + "» ");

        Objective objective = scat.registerNewObjective("Scatter", "Scoreboard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("" + ChatColor.YELLOW + ChatColor.BOLD + "NullUHC");

        Score score1 = objective.getScore(ChatColor.AQUA + "Scattered " + ChatColor.GRAY + "» ");
        score1.setScore(2);
        Score score2 = objective.getScore("");
        score2.setScore(1);
        Score score3 = objective.getScore(ChatColor.YELLOW + "Server IP");
        score3.setScore(0);

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

                if(Scatter.started)
                {
                    if (ConfigInventory.teamSize == 1)
                    {
                        game.setGameFFA(p);
                    }
                    else
                    {
                        game.setGameTeams(p);
                    }

                    cancel();
                }
            }

        }.runTaskTimer(plugin, 0, 1);

        p.setScoreboard(scat);
    }
}
