package core.Teams;

import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Kills.Spectator;
import core.Scatter.Scatter;
import core.Scoreboard.ScoreboardTeams;
import core.Scoreboard.Time;
import core.mainPackage.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TeamManager implements Listener
{
    public static String Teamprefix = ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Team" + ChatColor.GRAY + "]";
    public static HashMap<UUID, ArrayList<UUID>> teams = new HashMap<UUID, ArrayList<UUID>>();
    public static int aliveTeams = 0;
    public HashMap<UUID, UUID> pendingInv = new HashMap<UUID, UUID>();
    private static ScoreboardTeams scoreboardTeams = new ScoreboardTeams();
    Main plugin = Main.getPlugin(Main.class);

    public void createTeam(Player p)
    {
        if(!teams.containsKey(p.getUniqueId()))
        {
            boolean check = false;

            if(teams.isEmpty())
            {
                teams.put(p.getUniqueId(), new ArrayList<UUID>());
                scoreboardTeams.onTeamCreate(p);
                p.sendMessage(Teamprefix + ChatColor.GREEN + " You have successfully created a team!");
                aliveTeams++;
            }
            else
            {
                for(UUID uuid : teams.keySet())
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
                    scoreboardTeams.onTeamCreate(p);
                    p.sendMessage(Teamprefix + ChatColor.GREEN + " You have successfully created a team!");
                    aliveTeams++;
                }
            }
        }
        else
        {
            p.sendMessage(Teamprefix + ChatColor.RED + " You are already on a team!");
        }
    }

    public void setUpTeamsDuringGame()
    {

    }

    public void removePlayer(Player p, Player target)
    {
        if(teams.containsKey(p.getUniqueId()))
        {
            if(teams.get(p.getUniqueId()).contains(target.getUniqueId()))
            {
                p.sendMessage(Teamprefix + ChatColor.GREEN + " Successfully removed '" + target.getDisplayName() + "' from your team.");
                teams.get(p.getUniqueId()).remove(target.getUniqueId());
                scoreboardTeams.removePlayerFromTeam(p, target);
            }
            else
            {
                p.sendMessage(Teamprefix + ChatColor.RED + " That player is not on your team!");
            }
        }
        else
        {
            p.sendMessage(Teamprefix + ChatColor.RED + " You must be the owner of the team to remove!");
        }
    }

    public void removeOfflinePlayer(Player p, OfflinePlayer target)
    {
        if(teams.containsKey(p.getUniqueId()))
        {
            if(teams.get(p.getUniqueId()).contains(target.getUniqueId()))
            {
                teams.get(p.getUniqueId()).remove(target.getUniqueId());
            }
            else
            {
                p.sendMessage(Teamprefix + ChatColor.RED + " That player is not on your team!");
            }
        }
        else
        {
            p.sendMessage(Teamprefix + ChatColor.RED + " You must be the owner of the team to remove!");
        }
    }

    public void disbandTeam(Player p)
    {
        if(teams.containsKey(p.getUniqueId()))
        {
            teams.remove(p.getUniqueId());
            scoreboardTeams.disbandTeam(p);
            p.sendMessage(Teamprefix + ChatColor.GREEN + " You have successfully disbanded the team!");
            aliveTeams--;
        }
        else
        {
            p.sendMessage(Teamprefix + ChatColor.RED + " You must be the owner of the team to disband!");
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
            if(!pendingInv.containsKey(target.getUniqueId()))
            {
                if(teams.containsKey(p.getUniqueId()))
                {
                    // Invite target in here

                    if(teams.get(p.getUniqueId()).size() < (ConfigInventory.teamSize - 1))
                    {
                        if (teams.containsKey(target.getUniqueId()))
                        {
                            p.sendMessage(Teamprefix + ChatColor.RED + "'" + target.getDisplayName() + "' is already on a team!");
                        }
                        else
                        {
                            boolean check = false;

                            for (UUID uuid : teams.keySet())
                            {
                                if (teams.get(uuid).contains(target.getUniqueId()))
                                {
                                    check = true;
                                }
                            }

                            if (!check)
                            {
                                TextComponent pref = new TextComponent(Teamprefix);
                                TextComponent comp = new TextComponent(" " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Click Here");
                                TextComponent comp1 = new TextComponent(" " + ChatColor.GREEN + "to join " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + p.getName() + "'s " + ChatColor.GREEN + "team! " + ChatColor.GREEN + "(You have " + ChatColor.GREEN + "20 seconds to accept).");
                                comp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team accept " + p.getName()));
                                comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "Accept " + p.getDisplayName() + "'s Invite!").create()));
                                target.spigot().sendMessage(pref, comp, comp1);
                                p.sendMessage(Teamprefix + ChatColor.GREEN + " You have invited " + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.GREEN + " to your team!");
                                pendingInv.put(target.getUniqueId(), p.getUniqueId());

                                new BukkitRunnable()
                                {
                                    int seconds = 20;

                                    public void run()
                                    {
                                        if (seconds == 0 && pendingInv.containsKey(target.getUniqueId()))
                                        {
                                            target.sendMessage(Teamprefix + ChatColor.RED + " Your invite from " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + p.getName() + ChatColor.RED + " has expired.");
                                            pendingInv.remove(target.getUniqueId());
                                            cancel();
                                        }

                                        if(!pendingInv.containsKey(target.getUniqueId()))
                                        {
                                            cancel();
                                        }

                                        seconds--;
                                    }

                                }.runTaskTimer(plugin, 0, 20);
                            }
                            else
                            {
                                p.sendMessage(Teamprefix + ChatColor.RED + "'" + target.getDisplayName() + "' is already on a team!");
                            }
                        }
                    }
                    else
                    {
                        p.sendMessage(Teamprefix + ChatColor.RED + " Your team is full!");
                    }
                }
                else
                {
                    UUID temp = UUID.randomUUID();
                    boolean check = false;
                    // Search if p is in the Array Lists. If not, then tell p to get on a team

                    for (UUID uuid : teams.keySet())
                    {
                        if (teams.get(uuid).contains(p.getUniqueId()))
                        {
                            temp = uuid;
                            check = true;
                        }
                    }

                    if (check)
                    {
                        // Check if that player is on a team already then Invite the target player

                        if(teams.get(temp).size() < (ConfigInventory.teamSize - 1))
                        {
                            boolean check2 = false;

                            if (teams.containsKey(target.getUniqueId()))
                            {
                                p.sendMessage(Teamprefix + ChatColor.RED + "'" + target.getDisplayName() + "' is already on a team!");
                            }
                            else
                            {
                                for (UUID uuid : teams.keySet())
                                {
                                    if (teams.get(uuid).contains(target.getUniqueId()))
                                    {
                                        check2 = true;
                                    }
                                }

                                if (!check2)
                                {
                                    TextComponent pref = new TextComponent(Teamprefix);
                                    TextComponent comp = new TextComponent(" " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Click Here");
                                    TextComponent comp1 = new TextComponent(" " + ChatColor.GREEN + "to join " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + p.getDisplayName() + "'s " + ChatColor.GREEN + "team! " + ChatColor.GREEN + "(You have " + ChatColor.GREEN + "20 seconds to accept).");
                                    comp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team accept " + p.getName()));
                                    comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "Accept " + p.getDisplayName() + "'s Invite!").create()));
                                    target.spigot().sendMessage(pref, comp, comp1);
                                    p.sendMessage(Teamprefix + ChatColor.GREEN + " You have invited " + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.GREEN +  " to your team!");
                                    pendingInv.put(target.getUniqueId(), p.getUniqueId());

                                    new BukkitRunnable()
                                    {
                                        int seconds = 20;

                                        public void run()
                                        {
                                            if (seconds == 0 && pendingInv.containsKey(target.getUniqueId()))
                                            {
                                                target.sendMessage(Teamprefix + ChatColor.RED + " Your invite from " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + p.getName() + ChatColor.RED + " has expired.");
                                                pendingInv.remove(target.getUniqueId());
                                                cancel();
                                            }

                                            if(!pendingInv.containsKey(target.getUniqueId()))
                                            {
                                                cancel();
                                            }

                                            seconds--;
                                        }

                                    }.runTaskTimer(plugin, 0, 20);
                                }
                                else
                                {
                                    p.sendMessage(Teamprefix + ChatColor.RED + "'" + target.getDisplayName() + "' is already on a team!");
                                }
                            }
                        }
                        else
                        {
                            p.sendMessage(Teamprefix + ChatColor.RED + " Your team is full!");
                        }
                    }
                    else
                    {
                        p.sendMessage(Teamprefix + ChatColor.RED + " You are not on a team!");
                    }
                }
            }
            else
            {
                p.sendMessage(Teamprefix + ChatColor.RED + " That player already has a pending invite!");
            }
        }
    }

    public void addPlayer(Player p)
    {
        Player owner = Bukkit.getPlayer(pendingInv.get(p.getUniqueId()));

        if(teams.containsKey(pendingInv.get(p.getUniqueId())))
        {
            teams.get(pendingInv.get(p.getUniqueId())).add(p.getUniqueId());
            p.sendMessage(Teamprefix + ChatColor.GREEN + ChatColor.BOLD + " You have successfully joined " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + Bukkit.getPlayer(pendingInv.get(p.getUniqueId())).getDisplayName() + "'s " + ChatColor.GREEN + " team!");
            owner.sendMessage(Teamprefix + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + " " + p.getDisplayName() + ChatColor.GREEN + ChatColor.BOLD + " has joined your team!");
            pendingInv.remove(p.getUniqueId());
            scoreboardTeams.onTeamJoin(owner, p);
        }
        else
        {
            for(UUID uuid : teams.keySet())
            {
                if(teams.get(uuid).contains(pendingInv.get(p.getUniqueId())))
                {
                    teams.get(uuid).add(p.getUniqueId());
                    p.sendMessage(Teamprefix + ChatColor.GREEN + ChatColor.BOLD + " You have successfully joined " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + Bukkit.getPlayer(pendingInv.get(p.getUniqueId())).getDisplayName() + "'s " + ChatColor.GREEN + " team!");
                    owner.sendMessage(Teamprefix + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + " " + p.getDisplayName() + ChatColor.GREEN + ChatColor.BOLD + " has joined your team!");
                    pendingInv.remove(p.getUniqueId());
                    scoreboardTeams.onTeamJoin(owner, p);
                }
            }
        }
    }

    public void teamList(Player p)
    {
        UUID temp = p.getUniqueId();
        String message = "" + ChatColor.GOLD + ChatColor.BOLD + "Team List: \n";

        if(teams.containsKey(temp))
        {
            if(Scatter.allPlayers.contains(temp) || !Scatter.started)
            {
                message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(temp).getDisplayName() + "\n";
            }
            else
            {
                message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.RED + ChatColor.STRIKETHROUGH + Bukkit.getPlayer(temp).getDisplayName() + "\n";
            }

            for(UUID uuid : teams.get(temp))
            {
                if (Scatter.allPlayers.contains(uuid) || !Scatter.started)
                {
                    message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(uuid).getDisplayName() + "\n";
                }
                else
                {
                    message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.RED + ChatColor.STRIKETHROUGH + Bukkit.getPlayer(uuid).getDisplayName() + "\n";
                }
            }
        }
        else
        {
            UUID cap = getCaptain(p);

            if(Scatter.allPlayers.contains(cap) || !Scatter.started)
            {
                message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(cap).getDisplayName() + "\n";
            }
            else
            {
                message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.RED + ChatColor.STRIKETHROUGH + Bukkit.getPlayer(cap).getDisplayName() + "\n";
            }

            for(UUID uuid : teams.get(cap))
            {
                if(Scatter.allPlayers.contains(uuid) || !Scatter.started)
                {
                    message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(uuid).getDisplayName() + "\n";
                }
                else
                {
                    message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.RED + ChatColor.STRIKETHROUGH + Bukkit.getPlayer(uuid).getDisplayName() + "\n";
                }
            }
        }

        p.sendMessage(message);
    }

    public void teamListTarg(Player p, Player t)
    {
        UUID temp = t.getUniqueId();
        String message = "" + ChatColor.GOLD + ChatColor.BOLD + "Team List:" + "\n";

        if(teams.containsKey(temp))
        {
            if(Scatter.allPlayers.contains(t.getUniqueId()) || !Scatter.started)
            {
                message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(temp).getDisplayName() + "\n";
            }
            else
            {
                message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.RED + ChatColor.STRIKETHROUGH + Bukkit.getPlayer(temp).getDisplayName() + "\n";
            }

            for(UUID uuid : teams.get(temp))
            {
                if(Scatter.allPlayers.contains(uuid) || !Scatter.started)
                {
                    message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(uuid).getDisplayName() + "\n";
                }
                else
                {
                    message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.RED + ChatColor.STRIKETHROUGH + Bukkit.getPlayer(uuid).getDisplayName() + "\n";
                }
            }
        }
        else
        {
            UUID cap = getCaptain(t);

            if(Scatter.allPlayers.contains(cap) || !Scatter.started)
            {
                message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(cap).getDisplayName() + "\n";
            }
            else
            {
                message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.RED + ChatColor.STRIKETHROUGH + Bukkit.getPlayer(cap).getDisplayName() + "\n";
            }

            for(UUID uuid : teams.get(cap))
            {
                if(Scatter.allPlayers.contains(uuid) || !Scatter.started)
                {
                    message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(uuid).getDisplayName() + "\n";
                }
                else
                {
                    message += "" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.RED + ChatColor.STRIKETHROUGH + Bukkit.getPlayer(uuid).getDisplayName() + "\n";
                }
            }
        }

        p.sendMessage(message);
    }

    public boolean findTeam(Player p)
    {
        if(teams.containsKey(p.getUniqueId()))
        {
            return true;
        }
        else
        {
            for(UUID key : teams.keySet())
            {
                if(teams.get(key).contains(p.getUniqueId()))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean findTeamOffline(OfflinePlayer p)
    {
        if(teams.containsKey(p.getUniqueId()))
        {
            return true;
        }
        else
        {
            for(UUID key : teams.keySet())
            {
                if(teams.get(key).contains(p.getUniqueId()))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public UUID getCaptain(Player p)
    {
        if(findTeam(p))
        {
            for(UUID keys : teams.keySet())
            {
                if(keys.equals(p.getUniqueId()))
                {
                    return keys;
                }
                else if(teams.get(keys).contains(p.getUniqueId()))
                {
                    return keys;
                }
            }
        }

        return null;
    }

    public UUID getCaptainOffline(OfflinePlayer p)
    {
        if(findTeamOffline(p))
        {
            for(UUID keys : teams.keySet())
            {
                if(keys.equals(p.getUniqueId()))
                {
                    return keys;
                }
                else if(teams.get(keys).contains(p.getUniqueId()))
                {
                    return keys;
                }
            }
        }

        return null;
    }

    public void lateScatterCreateTeam(Player p)
    {
        scoreboardTeams.onTeamCreate(p);
        teams.put(p.getUniqueId(), new ArrayList<UUID>());
        p.sendMessage(Teamprefix + ChatColor.GREEN + " You have been put on a team.");
        aliveTeams++;
    }

    public void putPlayersOnTeam(Player sender, Player p, Player teamOwner)
    {
        if(Time.minutes > ConfigInventory.latescatter)
        {

        }
        else
        {
            if(!PlayerKills.spectator.contains(p.getUniqueId()) && !HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
            {
                if(findTeam(p))
                {
                    if(getCaptain(p) == p.getUniqueId())
                    {
                        if(teams.get(p.getUniqueId()).isEmpty())
                        {
                            if((teams.get(teamOwner.getUniqueId()).size() + 1) == ConfigInventory.teamSize)
                            {
                                UUID temp = teams.get(teamOwner.getUniqueId()).get(0);
                                teams.get(teamOwner.getUniqueId()).remove(0);
                                ArrayList<UUID> temparray = teams.get(teamOwner.getUniqueId());

                                disbandTeam(teamOwner);

                                createTeam(teamOwner);
                            }
                            else
                            {
                                disbandTeam(p);
                                teams.get(teamOwner.getUniqueId()).add(p.getUniqueId());
                                p.setGameMode(GameMode.SURVIVAL);
                                p.setFlying(false);
                                p.setAllowFlight(false);

                                sender.sendMessage(Teamprefix + ChatColor.GREEN + " You have successfully put " + ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.GREEN + " onto " + ChatColor.LIGHT_PURPLE + teamOwner.getDisplayName() + ChatColor.GREEN + "'s team!");
                            }
                        }
                        else
                        {
                            if((teams.get(teamOwner.getUniqueId()).size() + 1) == ConfigInventory.teamSize)
                            {
                                UUID temp = teams.get(teamOwner.getUniqueId()).get(0);
                                boolean check = false;

                                for(int i = 0; i < teams.get(teamOwner.getUniqueId()).size(); i++)
                                {
                                    if(Bukkit.getPlayer(teams.get(teamOwner.getUniqueId()).get(i)) != null)
                                    {
                                        temp = teams.get(teamOwner.getUniqueId()).get(i);
                                        check = true;
                                        break;
                                    }
                                }

                                if(check)
                                {
                                    Player t = Bukkit.getPlayer(temp);
                                    teams.get(teamOwner.getUniqueId()).remove(t.getUniqueId());
                                    ArrayList<UUID> temparray = teams.get(teamOwner.getUniqueId());
                                    disbandTeam(teamOwner);
                                    createTeam(teamOwner);
                                    createTeam(t);
                                    teams.put(t.getUniqueId(), temparray);

                                    ArrayList<UUID> newTeam = new ArrayList<UUID>();
                                    newTeam.add(p.getUniqueId());
                                    teams.put(teamOwner.getUniqueId(), newTeam);
                                }
                                else
                                {

                                }
                            }
                            else
                            {
                                disbandTeam(p);
                                teams.get(teamOwner.getUniqueId()).add(p.getUniqueId());
                                p.setGameMode(GameMode.SURVIVAL);
                                p.setFlying(false);
                                p.setAllowFlight(false);

                                sender.sendMessage(Teamprefix + ChatColor.GREEN + " You have successfully put " + ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.GREEN + " onto " + ChatColor.LIGHT_PURPLE + teamOwner.getDisplayName() + ChatColor.GREEN + "'s team!");
                            }
                        }
                    }
                    else
                    {

                    }
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "That player is not on a team!");
                }
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "That player is a host/mod/spectator!");
            }
        }
    }
}