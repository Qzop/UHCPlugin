package core.Scoreboard;

import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Kills.Spectator;
import core.Kills.TeamKills;
import core.Scatter.Scatter;
import core.Teams.TeamManager;
import core.mainPackage.Main;
import net.minecraft.server.v1_7_R4.ScoreboardTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import javax.persistence.Lob;
import java.util.UUID;

public class Game implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
    Time t = new Time();
    private Lobby lob;
    private ScoreboardTeams teams;
    private End end;

    public void setGameFFA(Player p)
    {
        teams = new ScoreboardTeams();
        end = new End();

        Scoreboard scoreboard;

        if(teams.getScoreBoard(p) == null)
        {
            scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        }
        else
        {
            scoreboard = teams.getScoreBoard(p);
        }

        Team host =  scoreboard.getTeam("host");
        Team time = scoreboard.getTeam("time");
        Team alive = scoreboard.getTeam("alive");
        Team kills = scoreboard.getTeam("kills");

        if(host == null && time == null && alive == null && kills == null)
        {
            host = scoreboard.registerNewTeam("host");
            host.addEntry(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");

            time = scoreboard.registerNewTeam("time");
            time.addEntry(ChatColor.AQUA + "Time " + ChatColor.GRAY + "» ");

            alive = scoreboard.registerNewTeam("alive");
            alive.addEntry(ChatColor.AQUA + "Alive " + ChatColor.GRAY + "» ");

            kills = scoreboard.registerNewTeam("kills");
            kills.addEntry(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ");
        }

        Objective objective = scoreboard.getObjective("FFAGame");

        if(objective == null)
        {
            objective = scoreboard.registerNewObjective("FFAGame", "Scoreboard");
            objective.setDisplayName("" + ChatColor.YELLOW + ChatColor.BOLD + "NullUHC");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }

        if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()) || PlayerKills.spectator.contains(p.getUniqueId()))
        {
            Score score = objective.getScore(ChatColor.AQUA + "Time " + ChatColor.GRAY + "» ");
            score.setScore(5);
            Score score1 = objective.getScore(ChatColor.AQUA + "Alive " + ChatColor.GRAY + "» ");
            score1.setScore(4);
            Score score2 = objective.getScore(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
            score2.setScore(3);
            Score score3 = objective.getScore("");
            score3.setScore(2);
            Score score4 = objective.getScore(ChatColor.YELLOW + "nulluhc.com");
            score4.setScore(1);
        }
        else
        {
            Score score = objective.getScore(ChatColor.AQUA + "Time " + ChatColor.GRAY + "» ");
            score.setScore(6);
            Score score1 = objective.getScore(ChatColor.AQUA + "Alive " + ChatColor.GRAY + "» ");
            score1.setScore(5);
            Score score2 = objective.getScore(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
            score2.setScore(4);
            Score score3 = objective.getScore(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ");
            score3.setScore(3);
            Score score4 = objective.getScore("");
            score4.setScore(2);
            Score score5 = objective.getScore(ChatColor.YELLOW + "nulluhc.com");
            score5.setScore(1);
        }

        Team finalTime = time;
        Team finalKills = kills;
        Team finalAlive = alive;
        Team finalHost = host;

        new BukkitRunnable()
        {
            public void run()
            {
                finalTime.setSuffix(ChatColor.YELLOW + t.getTime());
                finalKills.setSuffix(ChatColor.YELLOW + "" + PlayerKills.numKills.get(p.getUniqueId()));
                finalAlive.setSuffix(ChatColor.YELLOW + "" + Scatter.allPlayers.size());

                if(HostsMods.hosts.isEmpty())
                {
                    finalHost.setSuffix(ChatColor.RED + "None");
                }
                else
                {
                    String name = "";
                    Player h = Bukkit.getPlayer(HostsMods.hosts.get(0));

                    if(h != null)
                    {
                        if(h.getDisplayName().length() > 12)
                        {
                            name += h.getDisplayName().substring(0, 8);
                            finalHost.setSuffix("" + ChatColor.YELLOW + name + "...");
                        }
                        else
                        {
                            finalHost.setSuffix("" + ChatColor.YELLOW + h.getDisplayName());
                        }
                    }
                    else
                    {
                        OfflinePlayer temph = Bukkit.getOfflinePlayer(HostsMods.hosts.get(0));

                        if(temph.getName().length() > 12)
                        {
                            name += temph.getName().substring(0, 8);
                            finalHost.setSuffix("" + ChatColor.YELLOW + name + "...");
                        }
                        else
                        {
                            finalHost.setSuffix("" + ChatColor.YELLOW + temph.getName());
                        }
                    }
                }

                if(Scatter.ended)
                {
                    end.onWinner(p);
                    cancel();
                }
            }

        }.runTaskTimer(plugin, 0 , 1);

        teams.setScoreboard(p, scoreboard);
    }

    public void setGameTeams(Player p)
    {
        TeamKills tk = new TeamKills();
        teams = new ScoreboardTeams();
        end = new End();

        Scoreboard scoreboard;

        if(teams.getScoreBoard(p) == null)
        {
            scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        }
        else
        {
            scoreboard = teams.getScoreBoard(p);
        }

        Team host = scoreboard.getTeam("h");
        Team time = scoreboard.getTeam("t");
        Team teamkills = scoreboard.getTeam("tk");
        Team kills = scoreboard.getTeam("k");
        Team teamsize = scoreboard.getTeam("ts");

        if(host == null && time == null && teamkills == null && kills == null && teamsize == null)
        {
            host = scoreboard.registerNewTeam("h");
            host.addEntry(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");

            time = scoreboard.registerNewTeam("t");
            time.addEntry(ChatColor.AQUA + "Time " + ChatColor.GRAY + "» ");

            teamkills = scoreboard.registerNewTeam("tk");
            teamkills.addEntry(ChatColor.AQUA + "TeamKills " + ChatColor.GRAY + "» ");

            kills = scoreboard.registerNewTeam("k");
            kills.addEntry(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ");

            teamsize = scoreboard.registerNewTeam("ts");
            teamsize.addEntry(ChatColor.AQUA + "TeamSize " + ChatColor.GRAY + "» ");
        }

        Objective objective = scoreboard.getObjective("TeamGame");

        if(objective == null)
        {
            objective = scoreboard.registerNewObjective("TeamGame", "Scoreboard");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName("" + ChatColor.YELLOW + ChatColor.BOLD + "NullUHC");
        }

        if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()) || PlayerKills.spectator.contains(p.getUniqueId()))
        {
            Score score = objective.getScore(ChatColor.AQUA + "Time " + ChatColor.GRAY + "» ");
            score.setScore(5);
            Score score1 = objective.getScore(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
            score1.setScore(4);
            Score score3 = objective.getScore(ChatColor.AQUA + "TeamSize " + ChatColor.GRAY + "» ");
            score3.setScore(3);
            Score score6 = objective.getScore("");
            score6.setScore(2);
            Score score7 = objective.getScore(ChatColor.YELLOW + "nulluhc.com");
            score7.setScore(1);
        }
        else
        {
            Score score = objective.getScore(ChatColor.AQUA + "Time " + ChatColor.GRAY + "» ");
            score.setScore(7);
            Score score1 = objective.getScore(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
            score1.setScore(6);
            Score score3 = objective.getScore(ChatColor.AQUA + "TeamSize " + ChatColor.GRAY + "» ");
            score3.setScore(5);
            Score score4 = objective.getScore(ChatColor.AQUA + "TeamKills " + ChatColor.GRAY + "» ");
            score4.setScore(4);
            Score score5 = objective.getScore(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ");
            score5.setScore(3);
            Score score6 = objective.getScore("");
            score6.setScore(2);
            Score score7 = objective.getScore(ChatColor.YELLOW + "nulluhc.com");
            score7.setScore(1);
        }

        Team finalTime = time;
        Team finalKills = kills;
        Team finalTeamkills = teamkills;
        Team finalTeamsize = teamsize;
        Team finalHost = host;

        new BukkitRunnable()
        {
            public void run()
            {
                finalTime.setSuffix(ChatColor.YELLOW + t.getTime());
                finalKills.setSuffix(ChatColor.YELLOW + "" + PlayerKills.numKills.get(p.getUniqueId()));
                finalTeamkills.setSuffix(ChatColor.YELLOW + "" + tk.getTeamKills(p));
                finalTeamsize.setSuffix(ChatColor.YELLOW + "To" + ConfigInventory.teamSize);

                if(HostsMods.hosts.isEmpty())
                {
                    finalHost.setSuffix(ChatColor.RED + "None");
                }
                else
                {
                    String name = "";
                    Player h = Bukkit.getPlayer(HostsMods.hosts.get(0));

                    if(h == null)
                    {
                        OfflinePlayer host1 = Bukkit.getOfflinePlayer(HostsMods.hosts.get(0));

                        name+= host1.getName().substring(0,8);
                        finalHost.setSuffix("" + ChatColor.YELLOW + name + "...");
                    }
                    else
                    {
                        if(h.getDisplayName().length() > 12)
                        {
                            name += h.getDisplayName().substring(0, 8);
                            finalHost.setSuffix("" + ChatColor.YELLOW + name + "...");
                        }
                        else
                        {
                            finalHost.setSuffix("" + ChatColor.YELLOW + h.getDisplayName());
                        }
                    }

                    if(Scatter.ended)
                    {
                        end.onWinner(p);
                        cancel();
                    }
                }
            }

        }.runTaskTimer(plugin, 0 , 1);

        teams.setScoreboard(p, scoreboard);
    }
}
