package core.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import core.Arena.ArenaKills;
import core.Arena.PracticeArena;
import core.mainPackage.Commands;
import core.mainPackage.Main;

public class Practice implements Listener
{
	private Scat scat = new Scat();
	private ArenaKills ak = new ArenaKills();
	Main plugin = Main.getPlugin(Main.class);
    private Lobby lob;
    private ScoreboardTeams teams;
	
	public void setPractice(Player p)
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

        Team kills = scoreboard.registerNewTeam("Kill Count");
        kills.addEntry(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ");

        Objective objective = scoreboard.getObjective("Practice");

        if(objective == null)
        {
            objective = scoreboard.registerNewObjective("Practice", "Scoreboard");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName("" + ChatColor.YELLOW + ChatColor.BOLD + "NullUHC");
        }

        Score score1 = objective.getScore(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ");
        score1.setScore(2);
        Score score2 = objective.getScore("");
        score2.setScore(1);
        Score score3 = objective.getScore(ChatColor.YELLOW + "nulluhc.com");
        score3.setScore(0);

        new BukkitRunnable()
        {
            public void run()
            {
            	if(!PracticeArena.playersInArena.contains(p.getUniqueId()))
                {
                    lob.setLobby(p);
                	cancel();
                }
                else
                {
                	kills.setSuffix("" + ChatColor.YELLOW + ak.getKills(p));
                }
            	
                if(Commands.scatter)
                {
                    scat.setScatter(p);
                    cancel();
                }

            }

        }.runTaskTimer(plugin, 0, 1);

        teams.setScoreboard(p, scoreboard);
	}
}
