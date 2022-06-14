package core.Chat;

import core.HostsMods.HostsMods;
import core.Teams.TeamManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener
{
    public static String devPrefix = ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "Dev" + ChatColor.GRAY + "] ";
    public static String ownerPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "Owner" + ChatColor.GRAY + "] ";
    public static String adminPrefix = ChatColor.GRAY + "[" + ChatColor.RED + "Admin" + ChatColor.GRAY + "] ";
    public static String srmodPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_BLUE + "SrMod" + ChatColor.GRAY + "] ";
    public static String modPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + "Mod" + ChatColor.GRAY + "] ";
    public static String trialPrefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "Trial" + ChatColor.GRAY + "] ";

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();

        if(TeamManager.keys.contains(p.getUniqueId()))
        {
            for(int i = 0; i < TeamManager.keys.size(); i++)
            {
                if(TeamManager.keys.get(i) == p.getUniqueId())
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
            if(p.hasPermission("chat.dev"))
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
