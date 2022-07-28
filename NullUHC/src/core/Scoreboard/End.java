package core.Scoreboard;

import com.sun.deploy.security.SelectableSecurityManager;
import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.Scatter.Scatter;
import core.Teams.TeamManager;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class End implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
    private Lobby lob;
    private ScoreboardTeams teams;

    public void onWinner(Player p)
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

        Team restart = scoreboard.getTeam("Restart");

        if(restart == null)
        {
            restart = scoreboard.registerNewTeam("Restart");
            restart.addEntry(ChatColor.RED + "Restart " + ChatColor.GRAY + "» ");
        }

        Objective objective = scoreboard.getObjective("GameEnding");

        if(objective == null)
        {
            objective = scoreboard.registerNewObjective("GameEnding", "Scoreboard");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName("" + ChatColor.YELLOW + ChatColor.BOLD + "NullUHC");
        }

        Score score1 = objective.getScore(ChatColor.RED + "Restart " + ChatColor.GRAY + "» ");
        score1.setScore(3);
        Score score3 = objective.getScore("");
        score3.setScore(2);
        Score score4 = objective.getScore(ChatColor.YELLOW + "nulluhc.com");
        score4.setScore(1);

        Team finalRestart = restart;

        new BukkitRunnable()
        {
            int seconds = 30;

            public void run()
            {
                finalRestart.setSuffix(ChatColor.RED + "" + seconds);

                if(seconds == 0)
                {
                    Bukkit.broadcastMessage(net.md_5.bungee.api.ChatColor.RED + "Restarting the server.");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
                    cancel();
                }

                seconds--;
            }

        }.runTaskTimer(plugin, 0 , 20);

        teams.setScoreboard(p, scoreboard);
    }
}
