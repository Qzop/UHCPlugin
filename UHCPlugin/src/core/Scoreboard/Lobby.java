package core.Scoreboard;

import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.mainPackage.Commands;
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

public class Lobby implements Listener
{
    private Scat scat = new Scat();
    Scoreboard lobby = Bukkit.getScoreboardManager().getNewScoreboard();
    Main plugin = Main.getPlugin(Main.class);

    public void setLobby(Player p)
    {
        Objective obj = lobby.registerNewObjective("Lobby", "Scoreboard");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "FairUHC");

        Team playercount = lobby.registerNewTeam("Player Count");
        playercount.addEntry(ChatColor.AQUA + "Players " + ChatColor.GRAY + "» ");
        Team host = lobby.registerNewTeam("Host");
        host.addEntry(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
        Team teamsize = lobby.registerNewTeam("Team size");
        teamsize.addEntry(ChatColor.AQUA + "TeamSize " + ChatColor.GRAY + "» ");

        obj.getScore("§m--------------").setScore(5);
        obj.getScore(ChatColor.AQUA + "Players " + ChatColor.GRAY + "» ").setScore(4);
        obj.getScore(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ").setScore(3);
        obj.getScore(ChatColor.AQUA + "TeamSize " + ChatColor.GRAY + "» ").setScore(2);
        obj.getScore("§m---------------").setScore(1);
        obj.getScore(ChatColor.YELLOW + "Server IP").setScore(0);

        new BukkitRunnable()
        {
            public void run()
            {
                playercount.setSuffix("" + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size());

                if(HostsMods.hosts.isEmpty())
                {
                    host.setSuffix(ChatColor.RED + "None");
                }
                else
                {
                    host.setSuffix("" + ChatColor.YELLOW + Bukkit.getPlayer(HostsMods.hosts.get(0)));
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