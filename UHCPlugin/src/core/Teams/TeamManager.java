package core.Teams;

import core.Config.ConfigInventory;
import core.mainPackage.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TeamManager implements Listener
{
    public static String Teamprefix = ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Team" + ChatColor.GRAY + "]";
    public static String devPrefix = ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "Dev" + ChatColor.GRAY + "] ";
    public static String ownerPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "Owner" + ChatColor.GRAY + "] ";
    public static String adminPrefix = ChatColor.GRAY + "[" + ChatColor.RED + "Admin" + ChatColor.GRAY + "] ";
    public static String srmodPrefix = ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "SrMod" + ChatColor.GRAY + "] ";
    public static String modPrefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Mod" + ChatColor.GRAY + "] ";
    public static String trialPrefix = ChatColor.GRAY + "[" + ChatColor.YELLOW + "Trial" + ChatColor.GRAY + "] ";

    public static ArrayList<UUID> keys = new ArrayList<UUID>();
    public static HashMap<UUID, ArrayList<UUID>> teams = new HashMap<UUID, ArrayList<UUID>>();
    public HashMap<UUID, UUID> pendingInv = new HashMap<UUID, UUID>();
    Main plugin = Main.getPlugin(Main.class);

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

    public void removePlayer(Player p, Player target)
    {
        if(teams.containsKey(p.getUniqueId()))
        {
            if(teams.get(p.getUniqueId()).contains(target.getUniqueId()))
            {
                p.sendMessage(Teamprefix + ChatColor.GREEN + " Successfully removed '" + target.getDisplayName() + "' from your team.");
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
            keys.remove(p.getUniqueId());
            teams.remove(p.getUniqueId());
            p.sendMessage(Teamprefix + ChatColor.GREEN + " You have successfully disbanded the team!");
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

                            for (UUID uuid : keys)
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
                                TextComponent comp1 = new TextComponent(" " + ChatColor.GREEN + "to join " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + p.getName() + "'s " + ChatColor.GREEN + "team! " + ChatColor.GREEN + "\n(You have 20 seconds to accept).");
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

                    for (UUID uuid : keys)
                    {
                        if (teams.get(uuid).contains(p))
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
                                for (UUID uuid : keys)
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
                                    TextComponent comp1 = new TextComponent(" " + ChatColor.GREEN + "to join " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + p.getDisplayName() + "'s " + ChatColor.GREEN + "team! " + ChatColor.GREEN + "\n(You have 20 seconds to accept).");
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
            owner.sendMessage(Teamprefix + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + p.getDisplayName() + ChatColor.GREEN + ChatColor.BOLD + " has joined your team!");
            pendingInv.remove(p.getUniqueId());
        }
        else
        {
            for(UUID uuid : keys)
            {
                if(teams.get(uuid).contains(pendingInv.get(p.getUniqueId())))
                {
                    teams.get(uuid).add(p.getUniqueId());
                    p.sendMessage(Teamprefix + ChatColor.GREEN + ChatColor.BOLD + " You have successfully joined " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + Bukkit.getPlayer(pendingInv.get(p.getUniqueId())).getDisplayName() + "'s " + ChatColor.GREEN + " team!");
                    owner.sendMessage(Teamprefix + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + p.getDisplayName() + ChatColor.GREEN + ChatColor.BOLD + " has joined your team!");
                    pendingInv.remove(p.getUniqueId());
                }
            }
        }
    }

    public void teamList(Player p)
    {
        UUID temp = p.getUniqueId();

        if(teams.containsKey(temp))
        {
            p.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + " Team List:");
            p.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(temp).getDisplayName());

            for(UUID uuid : teams.get(temp))
            {
                p.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(uuid).getDisplayName());
            }
        }
        else
        {
            p.sendMessage(Teamprefix + ChatColor.RED + " You do not have a team!");
        }
    }

    public void teamListTarg(Player p, Player t)
    {
        UUID temp = t.getUniqueId();

        if(teams.containsKey(temp))
        {
            p.sendMessage(Teamprefix + ChatColor.GOLD + ChatColor.BOLD + " Team List:");
            p.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(temp).getDisplayName());

            for(UUID uuid : teams.get(temp))
            {
                p.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + "- " + ChatColor.LIGHT_PURPLE + Bukkit.getPlayer(uuid).getDisplayName());
            }
        }
        else
        {
            p.sendMessage(Teamprefix + ChatColor.RED + " You do not have a team!");
        }
    }

    @EventHandler
    public void onChatTeams(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();
        String message = e.getMessage();

        if(keys.contains(p.getUniqueId()))
        {
            for(int i = 0; i < keys.size(); i++)
            {
                if(keys.get(i) == p.getUniqueId())
                {
                    if(p.hasPermission("chat.dev"))
                    {
                        e.setFormat(ChatColor.LIGHT_PURPLE + "#" + i + devPrefix + ChatColor.GOLD + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                    }
                    else if(p.hasPermission("chat.owner"))
                    {
                        e.setFormat(ChatColor.LIGHT_PURPLE + "#" + i + ownerPrefix + ChatColor.DARK_RED + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                    }
                    else if(p.hasPermission("chat.admin"))
                    {
                        e.setFormat(ChatColor.LIGHT_PURPLE + "#" + i + adminPrefix + ChatColor.RED + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                    }
                    else if(p.hasPermission("chat.srmod"))
                    {
                        e.setFormat(ChatColor.LIGHT_PURPLE + "#" + i + srmodPrefix + ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                    }
                    else if(p.hasPermission("chat.mod"))
                    {
                        e.setFormat(ChatColor.LIGHT_PURPLE + "#" + i + modPrefix + ChatColor.AQUA + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                    }
                    else if(p.hasPermission("chat.trial"))
                    {
                        e.setFormat(ChatColor.LIGHT_PURPLE + "#" + i + trialPrefix + ChatColor.YELLOW + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                    }
                    else
                    {
                        e.setFormat(ChatColor.LIGHT_PURPLE + "#" + i + ChatColor.WHITE +  p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + e.getMessage());
                    }
                }
            }
        }
    }
}