package core.Kills;

import core.Teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;

public class TeamKills implements Listener
{
    private TeamManager team = new TeamManager();

    public int getTeamKills(Player p)
    {
        int total = 0;

        if(team.findTeam(p))
        {
            UUID cap = team.getCaptain(p);

            if(PlayerKills.numKills.get(cap) != null)
            {
                total += PlayerKills.numKills.get(cap);
            }

            for(int i = 0; i < TeamManager.teams.get(cap).size(); i++)
            {
                if(PlayerKills.numKills.get(TeamManager.teams.get(cap).get(i)) != null)
                {
                    total += PlayerKills.numKills.get(TeamManager.teams.get(cap).get(i));
                }
            }

            return total;
        }

        return -1;
    }
}
