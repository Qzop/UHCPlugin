package chat.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import chat.mainPackage.Commands;
import net.md_5.bungee.api.ChatColor;

public class StaffChat implements Listener
{
	@EventHandler
	public void staffChat(AsyncPlayerChatEvent e)
	{
		if(e.getMessage().charAt(0) == '@')
		{			
			Player player = e.getPlayer();
			
			if(player.hasPermission("staffchat"))
			{
				for(Player p : Bukkit.getOnlinePlayers())
				{
					if(p.hasPermission("staffchat") && !Commands.off.contains(p))
					{
						String message = "";
						
						for(int i = 1; i < e.getMessage().length(); i++)
						{
							message += e.getMessage().charAt(i);
						}
						
						if(player.hasPermission("chat.dev"))
						{
							p.sendMessage(ChatColor.LIGHT_PURPLE + "[StaffChat] " + ChatColor.GOLD +  ChatColor.BOLD + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + message);
						}
						else if(player.hasPermission("chat.owner"))
						{
							p.sendMessage(ChatColor.LIGHT_PURPLE + "[StaffChat] " + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + message);
						}
						else if(player.hasPermission("chat.admin"))
						{
							p.sendMessage(ChatColor.LIGHT_PURPLE + "[StaffChat] " + ChatColor.RED + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + message);
						}
						else if(player.hasPermission("chat.srmod"))
						{
							p.sendMessage(ChatColor.LIGHT_PURPLE + "[StaffChat] " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + message);
						}
						else if(player.hasPermission("chat.mod"))
						{
							p.sendMessage(ChatColor.LIGHT_PURPLE + "[StaffChat] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + message);
						}
						else if(player.hasPermission("chat.trial"))
						{
							p.sendMessage(ChatColor.LIGHT_PURPLE + "[StaffChat] " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.AQUA + message);
						}
					}
				}
				
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void globalChat(AsyncPlayerChatEvent e)
	{
		Player player = e.getPlayer();
		
		if(Commands.globalChat == true)
		{
			if(!player.hasPermission("globalchat.bypass"))
			{
				e.setCancelled(true);
				
				player.sendMessage(ChatColor.GREEN + "[GlobalChat] " + ChatColor.RED + "Chat is currently disabled.");
			}
		}
	}
}
