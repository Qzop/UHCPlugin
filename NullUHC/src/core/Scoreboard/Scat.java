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
    private Lobby lob;
    private ScoreboardTeams teams;

    public void setScatter(Player p)
    {
        teams = new ScoreboardTeams();

        Scoreboard scoreboard;

        if(teams.getScoreBoard(p) == null)
        {
            scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        }
        else
        {
            scoreboard = teams.getScoreBoard(p);
        }

        Team scattered = scoreboard.getTeam("Scattered");

        if(scattered == null)
        {
            scattered = scoreboard.registerNewTeam("Scattered");
            scattered.addEntry(ChatColor.AQUA + "Scattered " + ChatColor.GRAY + "» ");
        }

        Objective objective = scoreboard.getObjective("Scatter");

        if(objective == null)
        {
            objective = scoreboard.registerNewObjective("Scatter", "Scoreboard");
            objective.setDisplayName("" + ChatColor.YELLOW + ChatColor.BOLD + "NullUHC");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }

        Score score1 = objective.getScore(ChatColor.AQUA + "Scattered " + ChatColor.GRAY + "» ");
        score1.setScore(3);
        Score score2 = objective.getScore(ChatColor.YELLOW + "");
        score2.setScore(2);
        Score score3 = objective.getScore(ChatColor.YELLOW + "nulluhc.com");
        score3.setScore(1);

        Team finalScattered = scattered;

        new BukkitRunnable()
        {
            public void run()
            {
                if(ConfigInventory.teamSize == 1)
                {
                    finalScattered.setSuffix("" + ChatColor.YELLOW + Scatter.ffaScattered);
                }
                else
                {
                    teams.updateTeams(p);
                    finalScattered.setSuffix("" + ChatColor.YELLOW + Scatter.teamsScattered);
                }

                if(Scatter.started)
                {
                    if(ConfigInventory.teamSize == 1)
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

        teams.setScoreboard(p, scoreboard);
    }
}
