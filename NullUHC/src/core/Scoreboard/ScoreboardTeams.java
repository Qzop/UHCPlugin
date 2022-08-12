package core.Scoreboard;

import core.Teams.TeamManager;
import core.mainPackage.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class ScoreboardTeams implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
    private static ArrayList<Team> teams = new ArrayList<Team>();
    private static ArrayList<ChatColor> colors = new ArrayList<ChatColor>();
    private static HashMap<UUID, Scoreboard> scoreboards = new HashMap<UUID, Scoreboard>();
    private Lobby lob;

    public void setScoreboard(Player p, Scoreboard scoreboard)
    {
        Objective objective = scoreboard.getObjective("Teams");
        Objective below = scoreboard.getObjective(ChatColor.RED + "♥");

        if(objective == null)
        {
            objective = scoreboard.registerNewObjective("Teams", "health");
            objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        }

        if(below == null)
        {
            below = scoreboard.registerNewObjective(ChatColor.RED + "♥", "health");
            below.setDisplaySlot(DisplaySlot.BELOW_NAME);
        }

        if(!teams.isEmpty())
        {
            for(Team team : teams)
            {
                if(scoreboard.getTeam(team.getName()) == null)
                {
                    Team temp = scoreboard.registerNewTeam(team.getName());

                    for(String name : team.getEntries())
                    {
                        temp.addEntry(name);
                    }

                    temp.setPrefix(team.getPrefix());
                }
            }
        }

        if(scoreboards.containsKey(p.getUniqueId()))
        {
            p.setScoreboard(scoreboards.get(p.getUniqueId()));
        }
        else
        {
            scoreboards.put(p.getUniqueId(), scoreboard);
            p.setScoreboard(scoreboard);
        }
    }

    public Scoreboard getScoreBoard(Player p)
    {
        if(scoreboards.containsKey(p.getUniqueId()))
        {
            return scoreboards.get(p.getUniqueId());
        }

        return null;
    }

    public void onTeamCreate(Player owner)
    {
        // getScoreBoard(owner) might be null for some people who late scatter.
        Team team = getScoreBoard(owner).registerNewTeam(owner.getDisplayName());
        team.addEntry(owner.getDisplayName());
        ChatColor color = getColors();
        team.setPrefix("" + color);
        teams.add(team);

        for(UUID key : scoreboards.keySet())
        {
            if(!key.equals(owner.getUniqueId()))
            {
                if(scoreboards.get(key).getTeam(owner.getDisplayName()) != null)
                {
                    scoreboards.get(key).getTeam(owner.getDisplayName()).unregister();
                }

                team = scoreboards.get(key).registerNewTeam(owner.getDisplayName());
                team.addEntry(owner.getDisplayName());
                team.setPrefix("" + color);
            }
        }
    }

    public void onTeamJoin(Player owner, Player target)
    {
        if(!teams.isEmpty())
        {
            for(Team team : teams)
            {
                if(team.hasEntry(owner.getDisplayName()))
                {
                    team.addEntry(target.getDisplayName());
                    break;
                }
            }
        }
    }

    public void setColors()
    {
        colors.add(ChatColor.AQUA);
        colors.add(ChatColor.RED);
        colors.add(ChatColor.YELLOW);
        colors.add(ChatColor.GRAY);
        colors.add(ChatColor.GREEN);
        colors.add(ChatColor.WHITE);
        colors.add(ChatColor.DARK_BLUE);
        colors.add(ChatColor.BLUE);
        colors.add(ChatColor.DARK_PURPLE);
        colors.add(ChatColor.LIGHT_PURPLE);
        colors.add(ChatColor.GOLD);
        colors.add(ChatColor.DARK_GREEN);
        colors.add(ChatColor.DARK_RED);
        colors.add(ChatColor.DARK_AQUA);
        colors.add(ChatColor.BLACK);
        colors.add(ChatColor.DARK_GRAY);
        colors.add(ChatColor.BOLD);
        colors.add(ChatColor.ITALIC);
        colors.add(ChatColor.UNDERLINE);
        colors.add(ChatColor.STRIKETHROUGH);
    }

    public void removeTeams()
    {
        if(!teams.isEmpty())
        {
            for(UUID key : scoreboards.keySet())
            {
                for(Team team : scoreboards.get(key).getTeams())
                {
                    team.unregister();
                }
            }
        }
    }

    public void removePlayerFromTeam(Player owner, Player target)
    {
        if(!teams.isEmpty())
        {
            for(Team team : teams)
            {
                if(team.getDisplayName().equals(owner.getDisplayName()))
                {
                    team.removeEntry(target.getDisplayName());
                }
            }
        }
    }

    public void disbandTeam(Player p)
    {
        if(!teams.isEmpty())
        {
            for(Team team : teams)
            {
                if(team.getDisplayName().equals(p.getDisplayName()))
                {
                    teams.remove(team);
                    getScoreBoard(p).getTeam(team.getDisplayName()).unregister();
                    break;
                }
            }
        }
    }

    public ChatColor getColors()
    {
        Random rn = new Random();
        int num = rn.nextInt((colors.size()));
        return colors.get(num);
    }

    public void updateTeams(Player p)
    {
        for(Team team : teams)
        {
            if(p.getScoreboard().getTeam(team.getName()) == null)
            {
                p.getScoreboard().registerNewTeam(team.getName());
            }
        }
    }
}