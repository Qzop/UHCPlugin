package core.Scoreboard;

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
    private Scat scat = new Scat();
    Main plugin = Main.getPlugin(Main.class);

    public void setLobby(Player p)
    {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard lobby = manager.getNewScoreboard();

        Team playercount = lobby.registerNewTeam("Player Count");
        playercount.addEntry(ChatColor.AQUA + "Players " + ChatColor.GRAY + "» ");
        Team host = lobby.registerNewTeam("Host");
        host.addEntry(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
        Team teamsize = lobby.registerNewTeam("Team size");
        teamsize.addEntry(ChatColor.AQUA + "TeamSize " + ChatColor.GRAY + "» ");

        Objective objective = lobby.registerNewObjective("Lobby", "Scoreboard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("" + ChatColor.YELLOW + ChatColor.BOLD + "FairUHC");

        Score score1 = objective.getScore(ChatColor.AQUA + "Players " + ChatColor.GRAY + "» ");
        score1.setScore(4);
        Score score2 = objective.getScore(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
        score2.setScore(3);
        Score score3 = objective.getScore(ChatColor.AQUA + "TeamSize " + ChatColor.GRAY + "» ");
        score3.setScore(2);
        Score score4 = objective.getScore("");
        score4.setScore(1);
        Score score5 = objective.getScore(ChatColor.YELLOW + "Server IP");
        score5.setScore(0);

        new BukkitRunnable()
        {
            public void run()
            {
                playercount.setSuffix("" + ChatColor.YELLOW + "" + Bukkit.getOnlinePlayers().size());

                if(HostsMods.hosts.isEmpty())
                {
                    host.setSuffix(ChatColor.RED + "None");
                }
                else
                {
                    String name = "";
                    Player h = Bukkit.getPlayer(HostsMods.hosts.get(0));

                    if(h == null)
                    {
                        OfflinePlayer host1 = Bukkit.getOfflinePlayer(HostsMods.hosts.get(0));

                        name+= host1.getName().substring(0,8);
                        host.setSuffix("" + ChatColor.YELLOW + name + "...");
                    }
                    else
                    {
                        if(h.getDisplayName().length() > 12)
                        {
                            name += h.getDisplayName().substring(0, 8);
                            host.setSuffix("" + ChatColor.YELLOW + name + "...");
                        }
                        else
                        {
                            host.setSuffix("" + ChatColor.YELLOW + h.getDisplayName());
                        }
                    }
                }

                if(ConfigInventory.teamSize == 1)
                {
                    teamsize.setSuffix("" + ChatColor.YELLOW + "FFA");
                }
                else
                {
                    teamsize.setSuffix("" + ChatColor.YELLOW + "To" + ConfigInventory.teamSize);
                }

                if(Commands.scatter)
                {
                    scat.setScatter(p);
                    cancel();
                }
            }

        }.runTaskTimer(plugin, 0, 20);


        p.setScoreboard(lobby);
    }
}