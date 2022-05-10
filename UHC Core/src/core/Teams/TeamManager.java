package core.Teams;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TeamManager implements Listener
{
    public static String Teamprefix = ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Team" + ChatColor.GRAY + "]";

    public ArrayList<UUID> keys = new ArrayList<UUID>();
    public HashMap<UUID, ArrayList<UUID>> teams = new HashMap<UUID, ArrayList<UUID>>();

    public void createTeam(Player p)
    {
        if(!teams.containsKey(p.getUniqueId()))
        {
            boolean check = false;

            if(teams.isEmpty())
            {
                teams.put(p.getUniqueId(), new ArrayList<UUID>());
                keys.add(p.getUniqueId());

                p.sendMessage(Teamprefix + ChatColor.GREEN + " You have successfully created a team!");
            }
            else
            {
                for(UUID uuid : keys)
                {
                    if(teams.get(uuid).contains(p.getUniqueId()))
                    {
                        check = true;
                    }
                }

                if(check)
                {
                    p.sendMessage(Teamprefix + ChatColor.RED + " You are already on a team!");
                }
                else
                {
                    teams.put(p.getUniqueId(), new ArrayList<UUID>());
                    keys.add(p.getUniqueId());

                    p.sendMessage(Teamprefix + ChatColor.GREEN + " You have successfully created a team!");
                }
            }
        }
        else
        {
            p.sendMessage(Teamprefix + ChatColor.RED + " You are already on a team!");
        }
    }

    public void invitePlayer(Player p, Player target)
    {
        if(teams.isEmpty())
        {
            p.sendMessage(Teamprefix + ChatColor.RED + " You are not on a team!");
        }
        else
        {
            if(teams.containsKey(p.getUniqueId()))
            {
                // Invite target in here


            }
            else
            {
                boolean check = false;
                // Search if p is in the Array Lists. If not, then tell p to get on a team

                for(UUID uuid : keys)
                {
                    if(teams.get(uuid).contains(p))
                    {
                        check = true;
                    }
                }

                if(check)
                {
                    // Invite target in here
                }
                else
                {
                    p.sendMessage(Teamprefix + ChatColor.RED + " You are not on a team!");
                }
            }
        }
    }

    public void addPlayer(Player p, Player target)
    {

    }
}
