package core.Scoreboard;

import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Scatter.Scatter;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class Game implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
    Time t = new Time();

    public void setGameFFA(Player p)
    {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard game = manager.getNewScoreboard();

        Team host = game.registerNewTeam("Host");
        host.addEntry(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
        Team time = game.registerNewTeam("Time");
        time.addEntry(ChatColor.AQUA + "Time " + ChatColor.GRAY + "» ");
        Team kills = game.registerNewTeam("Kills");
        kills.addEntry(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ");

        Objective objective = game.registerNewObjective("FFAGame", "Scoreboard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("" + ChatColor.YELLOW + ChatColor.BOLD + "FairUHC");

        Score score = objective.getScore(ChatColor.AQUA + "Time " + ChatColor.GRAY + "» ");
        score.setScore(4);
        Score score1 = objective.getScore(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
        score1.setScore(3);
        Score score2 = objective.getScore(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ");
        score2.setScore(2);
        Score score3 = objective.getScore("");
        score3.setScore(1);
        Score score4 = objective.getScore(ChatColor.YELLOW + "Server IP");
        score4.setScore(0);

        new BukkitRunnable()
        {
            public void run()
            {
                time.setSuffix(ChatColor.YELLOW + t.getTime());
                kills.setSuffix(ChatColor.YELLOW + "" + PlayerKills.numKills.get(p.getUniqueId()));

                if(HostsMods.hosts.isEmpty())
                {
                    host.setSuffix(ChatColor.RED + "None");
                }
                else
                {
                    String name = "";

                    if(Bukkit.getPlayer(HostsMods.hosts.get(0)).getDisplayName().length() > 12)
                    {
                        name += Bukkit.getPlayer(HostsMods.hosts.get(0)).getDisplayName().substring(0, 8);
                        host.setSuffix("" + ChatColor.YELLOW + name + "...");
                    }
                    else
                    {
                        host.setSuffix("" + ChatColor.YELLOW + Bukkit.getPlayer(HostsMods.hosts.get(0)).getDisplayName());
                    }
                }

                if(Scatter.ended)
                {
                    cancel();
                }
            }

        }.runTaskTimer(plugin, 0 , 1);

        p.setScoreboard(game);
    }

    public void setGameTeams(Player p)
    {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard game = manager.getNewScoreboard();

        Team host = game.registerNewTeam("Host");
        host.addEntry(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
        Team time = game.registerNewTeam("Time");
        time.addEntry(ChatColor.AQUA + "Time " + ChatColor.GRAY + "» ");
        Team teamkills = game.registerNewTeam("TeamKills");
        teamkills.addEntry(ChatColor.AQUA + "TeamKills " + ChatColor.GRAY + "» ");

        Objective objective = game.registerNewObjective("TeamGame", "Scoreboard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("" + ChatColor.YELLOW + ChatColor.BOLD + "FairUHC");

        Score score = objective.getScore(ChatColor.AQUA + "Time " + ChatColor.GRAY + "» ");
        score.setScore(6);
        Score score1 = objective.getScore(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
        score1.setScore(5);
        Score score3 = objective.getScore(ChatColor.AQUA + "TeamSize " + ChatColor.GRAY + "» " + ChatColor.YELLOW + "To" + ConfigInventory.teamSize);
        score3.setScore(4);
        Score score4 = objective.getScore(ChatColor.AQUA + "TeamKills " + ChatColor.GRAY + "» ");
        score4.setScore(3);
        Score score5 = objective.getScore(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ");
        score5.setScore(2);
        Score score6 = objective.getScore("");
        score6.setScore(1);
        Score score7 = objective.getScore(ChatColor.YELLOW + "Server IP");
        score7.setScore(0);

        new BukkitRunnable()
        {
            public void run()
            {
                time.addEntry(ChatColor.YELLOW + t.getTime());

                if(HostsMods.hosts.isEmpty())
                {
                    host.setSuffix(ChatColor.RED + "None");
                }
                else
                {
                    String name = "";

                    if(Bukkit.getPlayer(HostsMods.hosts.get(0)).getDisplayName().length() > 12)
                    {
                        name += Bukkit.getPlayer(HostsMods.hosts.get(0)).getDisplayName().substring(0, 8);
                        host.setSuffix("" + ChatColor.YELLOW + name + "...");
                    }
                    else
                    {
                        host.setSuffix("" + ChatColor.YELLOW + Bukkit.getPlayer(HostsMods.hosts.get(0)).getDisplayName());
                    }
                }
            }

        }.runTaskTimer(plugin, 0 , 1);

        p.setScoreboard(game);
    }
}
