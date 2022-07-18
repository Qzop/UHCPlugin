package core.Kills;

import core.Teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TeamKills implements Listener
{
    private TeamManager team = new TeamManager();

    public int getTeamKills(Player p)
    {
        int total = 0;

        if(team.findTeam(p))
        {
            int cap = team.getCaptain(p);

            total += PlayerKills.numKills.get(TeamManager.keys.get(cap));

            for(int i = 0; i < TeamManager.teams.get(TeamManager.keys.get(cap)).size(); i++)
            {
                total += PlayerKills.numKills.get(TeamManager.teams.get(TeamManager.keys.get(cap)).get(i));
            }

            return total;
        }

        return -1;
    }
}
