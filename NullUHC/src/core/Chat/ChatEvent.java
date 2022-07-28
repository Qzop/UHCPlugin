package core.Chat;

import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Teams.TeamManager;
import core.mainPackage.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.UUID;

public class ChatEvent implements Listener
{
    public static String devPrefix = ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "Dev" + ChatColor.GRAY + "] ";
    public static String ownerPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "Owner" + ChatColor.GRAY + "] ";
    public static String adminPrefix = ChatColor.GRAY + "[" + ChatColor.RED + "Admin" + ChatColor.GRAY + "] ";
    public static String srmodPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_BLUE + "SrMod" + ChatColor.GRAY + "] ";
    public static String modPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + "Mod" + ChatColor.GRAY + "] ";
    public static String trialPrefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "Trial" + ChatColor.GRAY + "] ";
    public static String specPref = ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "SpecChat" + ChatColor.GRAY + "]";
    public static String staffchatPref = ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "StaffChat" + ChatColor.GRAY + "]";

    public static ArrayList<UUID> specchat = new ArrayList<UUID>();
    public static ArrayList<UUID> staffchat = new ArrayList<UUID>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();
        ArrayList<UUID> tempKeys = new ArrayList<UUID>(TeamManager.teams.keySet());

        if(PlayerKills.spectator.contains(p.getUniqueId()) || specchat.contains(p.getUniqueId()))
        {
            String msg = e.getMessage();
            e.setCancelled(true);

            for(Player players : Main.online.getOnlinePlayers())
            {
                if(PlayerKills.spectator.contains(players.getUniqueId()) || specchat.contains(players.getUniqueId()))
                {
                    if(p.hasPermission("chat.owner"))
                    {
                        players.sendMessage(specPref + " " + ChatColor.DARK_RED + ChatColor.BOLD + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + msg);
                    }
                    else if(p.hasPermission("chat.dev"))
                    {
                        players.sendMessage(specPref + " " + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + msg);
                    }
                    else if(p.hasPermission("chat.admin"))
                    {
                        players.sendMessage(specPref + " " + ChatColor.RED + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + msg);
                    }
                    else if(p.hasPermission("chat.srmod"))
                    {
                        players.sendMessage(specPref + " " + ChatColor.DARK_BLUE + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + msg);
                    }
                    else if(p.hasPermission("chat.mod"))
                    {
                        players.sendMessage(specPref + " " + ChatColor.DARK_GREEN + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + msg);
                    }
                    else if(p.hasPermission("chat.trial"))
                    {
                        players.sendMessage(specPref + " " + ChatColor.GREEN + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + msg);
                    }
                    else
                    {
                        players.sendMessage(specPref + " " + ChatColor.WHITE + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + msg);
                    }
                }
            }
        }
        else if(staffchat.contains(p.getUniqueId()))
        {
            String msg = e.getMessage();
            e.setCancelled(true);

            for(Player player : Main.online.getOnlinePlayers())
            {
                if(staffchat.contains(player.getUniqueId()))
                {
                    if(p.hasPermission("chat.owner"))
                    {
                        player.sendMessage(staffchatPref + " " + ChatColor.DARK_RED + ChatColor.BOLD + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + msg);
                    }
                    else if(p.hasPermission("chat.dev"))
                    {
                        player.sendMessage(staffchatPref + " " + ChatColor.GOLD + ChatColor.BOLD + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + msg);
                    }
                    else if(p.hasPermission("chat.admin"))
                    {
                        player.sendMessage(staffchatPref + " " + ChatColor.RED + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + msg);
                    }
                    else if(p.hasPermission("chat.srmod"))
                    {
                        player.sendMessage(staffchatPref + " " + ChatColor.DARK_BLUE + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + msg);
                    }
                    else if(p.hasPermission("chat.mod"))
                    {
                        player.sendMessage(staffchatPref + " " + ChatColor.DARK_GREEN + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + msg);
                    }
                    else if(p.hasPermission("chat.trial"))
                    {
                        player.sendMessage(staffchatPref + " " + ChatColor.GREEN + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + msg);
                    }
                }
            }
        }
        else
        {
            if(tempKeys.contains(p.getUniqueId()))
            {
                for(int i = 0; i < tempKeys.size(); i++)
                {
                    if(tempKeys.get(i).equals(p.getUniqueId()))
                    {
                        if(p.hasPermission("chat.owner"))
                        {
                            e.setFormat(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "#" + (i + 1) + ChatColor.GRAY + "] " + ownerPrefix + ChatColor.DARK_RED + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                        }
                        else if(p.hasPermission("chat.dev"))
                        {
                            e.setFormat(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "#" + (i + 1) + ChatColor.GRAY + "] " + devPrefix + ChatColor.GOLD + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                        }
                        else if(p.hasPermission("chat.admin"))
                        {
                            e.setFormat(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "#" + (i + 1) + ChatColor.GRAY + "] " + adminPrefix + ChatColor.RED + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                        }
                        else if(p.hasPermission("chat.srmod"))
                        {
                            e.setFormat(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "#" + (i + 1) + ChatColor.GRAY + "] " + srmodPrefix + ChatColor.DARK_BLUE + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                        }
                        else if(p.hasPermission("chat.mod"))
                        {
                            e.setFormat(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "#" + (i + 1) + ChatColor.GRAY + "] " + modPrefix + ChatColor.DARK_GREEN + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                        }
                        else if(p.hasPermission("chat.trial"))
                        {
                            e.setFormat(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "#" + (i + 1) + ChatColor.GRAY + "] " + trialPrefix + ChatColor.GREEN + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                        }
                        else
                        {
                            e.setFormat(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "#" + (i + 1) + ChatColor.GRAY + "] " + ChatColor.WHITE +  p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + e.getMessage());
                        }
                    }
                }
            }
            else if(HostsMods.hosts.contains(p.getUniqueId()))
            {
                if(p.hasPermission("chat.owner"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.RED + "Host" + ChatColor.GRAY + "] " + ownerPrefix + ChatColor.DARK_RED + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.dev"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.RED + "Host" + ChatColor.GRAY + "] " + devPrefix + ChatColor.GOLD + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.admin"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.RED + "Host" + ChatColor.GRAY + "] " + adminPrefix + ChatColor.RED + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.srmod"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.RED + "Host" + ChatColor.GRAY + "] " + srmodPrefix + ChatColor.DARK_BLUE + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.mod"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.RED + "Host" + ChatColor.GRAY + "] " + modPrefix + ChatColor.DARK_GREEN + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.trial"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.RED + "Host" + ChatColor.GRAY + "] " + trialPrefix + ChatColor.GREEN + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
            }
            else if(HostsMods.mods.contains(p.getUniqueId()))
            {
                if(p.hasPermission("chat.owner"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.AQUA + "Mod" + ChatColor.GRAY + "] " + ownerPrefix + ChatColor.DARK_RED + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.dev"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.AQUA + "Mod" + ChatColor.GRAY + "] " + devPrefix + ChatColor.GOLD + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.admin"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.AQUA + "Mod" + ChatColor.GRAY + "] " + adminPrefix + ChatColor.RED + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.srmod"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.AQUA + "Mod" + ChatColor.GRAY + "] " + srmodPrefix + ChatColor.DARK_BLUE + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.mod"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.AQUA + "Mod" + ChatColor.GRAY + "] " + modPrefix + ChatColor.DARK_GREEN + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.trial"))
                {
                    e.setFormat(ChatColor.GRAY + "[" + ChatColor.AQUA + "Mod" + ChatColor.GRAY + "] " + trialPrefix + ChatColor.GREEN + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
            }
            else
            {
                if(p.hasPermission("chat.owner"))
                {
                    e.setFormat(ownerPrefix + ChatColor.DARK_RED + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.dev"))
                {
                    e.setFormat(devPrefix + ChatColor.GOLD + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.admin"))
                {
                    e.setFormat(adminPrefix + ChatColor.RED + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.srmod"))
                {
                    e.setFormat(srmodPrefix + ChatColor.DARK_BLUE + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.mod"))
                {
                    e.setFormat(modPrefix + ChatColor.DARK_GREEN + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else if(p.hasPermission("chat.trial"))
                {
                    e.setFormat(trialPrefix + ChatColor.GREEN + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
                }
                else
                {
                    e.setFormat(ChatColor.WHITE +  p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + e.getMessage());
                }
            }
        }
    }
}
