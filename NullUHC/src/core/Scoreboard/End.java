package core.Scoreboard;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

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

        Team winner;

        if(ConfigInventory.teamSize == 1)
        {
            winner = scoreboard.getTeam("Winner");

            if(winner == null)
            {
                winner = scoreboard.registerNewTeam("Winner");
                winner.addEntry(ChatColor.AQUA + "Winner " + ChatColor.GRAY + "» ");
            }
        }
        else
        {
            winner = scoreboard.getTeam("Winners");

            if(winner == null)
            {
                winner = scoreboard.registerNewTeam("Winners");
                winner.addEntry(ChatColor.AQUA + "Winners " + ChatColor.GRAY + "» ");
            }
        }


        Team restart = scoreboard.getTeam("Restart");

        if(restart == null)
        {
            restart = scoreboard.registerNewTeam("Restart");
            restart.addEntry(ChatColor.RED + "Restarting in " + ChatColor.GRAY + "» ");
        }

        Objective objective = scoreboard.getObjective("GameEnding");

        if(objective == null)
        {
            objective = scoreboard.registerNewObjective("GameEnding", "Scoreboard");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName("" + ChatColor.YELLOW + ChatColor.BOLD + "NullUHC");
        }

        if(ConfigInventory.teamSize == 1)
        {
            Score score = objective.getScore(ChatColor.AQUA + "Winner " + ChatColor.GRAY + "» ");
            score.setScore(4);
        }
        else
        {
            Score score = objective.getScore(ChatColor.AQUA + "Winners " + ChatColor.GRAY + "» ");
            score.setScore(4);
        }

        Score score1 = objective.getScore(ChatColor.RED + "Restarting in " + ChatColor.GRAY + "» ");
        score1.setScore(3);
        Score score3 = objective.getScore("");
        score3.setScore(2);
        Score score4 = objective.getScore(ChatColor.YELLOW + "nulluhc.com");
        score4.setScore(1);

        Team finalRestart = restart;
        Team finalWinner = winner;

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

                if(ConfigInventory.teamSize == 1)
                {
                    Player win = Bukkit.getPlayer(Scatter.allPlayers.get(0));

                    if(win != null)
                    {
                        finalWinner.setSuffix(ChatColor.YELLOW + "" + win.getDisplayName());
                    }
                    else
                    {
                        OfflinePlayer win1 = Bukkit.getOfflinePlayer(Scatter.allPlayers.get(0));
                        finalWinner.setSuffix(ChatColor.YELLOW + "" + win1.getName());
                    }
                }
                else
                {

                }


                seconds--;
            }

        }.runTaskTimer(plugin, 0 , 20);

        teams.setScoreboard(p, scoreboard);
    }
}
