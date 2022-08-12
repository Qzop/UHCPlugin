package core.Scoreboard;

import core.Arena.PracticeArena;
import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.mainPackage.Commands;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class Lobby implements Listener
{
	private Practice prac = new Practice();
    private Scat scat = new Scat();
    private ScoreboardTeams teams = new ScoreboardTeams();
    Main plugin = Main.getPlugin(Main.class);

    public void setLobby(Player p)
    {
        Scoreboard scoreboard;

        if(teams.getScoreBoard(p) == null)
        {
            scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        }
        else
        {
            scoreboard = teams.getScoreBoard(p);
        }

        Team playercount = scoreboard.getTeam("Player Count");
        Team host = scoreboard.getTeam("Host");
        Team teamsize = scoreboard.getTeam("Team size");

        if(playercount == null && host == null && teamsize == null)
        {
            playercount = scoreboard.registerNewTeam("Player Count");
            playercount.addEntry(ChatColor.AQUA + "Players " + ChatColor.GRAY + "» ");

            host = scoreboard.registerNewTeam("Host");
            host.addEntry(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");

            teamsize = scoreboard.registerNewTeam("Team size");
            teamsize.addEntry(ChatColor.AQUA + "TeamSize " + ChatColor.GRAY + "» ");
        }


        Objective objective = scoreboard.getObjective("Sidebar");

        if(objective == null)
        {
            objective = scoreboard.registerNewObjective("Sidebar", "Scoreboard");
            objective.setDisplayName("" + ChatColor.YELLOW + ChatColor.BOLD + "NullUHC");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }

        Score score1 = objective.getScore(ChatColor.AQUA + "Players " + ChatColor.GRAY + "» ");
        score1.setScore(4);
        Score score2 = objective.getScore(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
        score2.setScore(3);
        Score score3 = objective.getScore(ChatColor.AQUA + "TeamSize " + ChatColor.GRAY + "» ");
        score3.setScore(2);
        Score score4 = objective.getScore("");
        score4.setScore(1);
        Score score5 = objective.getScore(ChatColor.YELLOW + "nulluhc.com");
        score5.setScore(0);

        Team finalHost = host;
        Team finalPlayercount = playercount;
        Team finalTeamsize = teamsize;

        new BukkitRunnable()
        {
            public void run()
            {
                if(ConfigInventory.teamSize > 1)
                {
                    teams.updateTeams(p);
                }

                finalPlayercount.setSuffix("" + ChatColor.YELLOW + "" + Main.online.getOnlinePlayers().size());

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

                if(ConfigInventory.teamSize == 1)
                {
                    finalTeamsize.setSuffix("" + ChatColor.YELLOW + "FFA");
                }
                else
                {
                    if(p.getScoreboard().getObjective("Teams") != null)
                    {

                    }

                    finalTeamsize.setSuffix("" + ChatColor.YELLOW + "To" + ConfigInventory.teamSize);
                }

                if(Commands.scatter)
                {
                    scat.setScatter(p);
                    cancel();
                }
                
                
                if(PracticeArena.playersInArena.contains(p.getUniqueId()))
                {
                	prac.setPractice(p);
                   	cancel();
                }
            }

        }.runTaskTimer(plugin, 0, 1);

        teams.setScoreboard(p, scoreboard);
    }
}