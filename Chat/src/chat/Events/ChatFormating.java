package chat.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import chat.mainPackage.Commands;
import chat.mainPackage.Poll;
import net.md_5.bungee.api.ChatColor;

public class ChatFormating implements Listener
{
	public static String devPrefix = ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "Dev" + ChatColor.GRAY + "] ";
	public static String ownerPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "Owner" + ChatColor.GRAY + "] ";
	public static String adminPrefix = ChatColor.GRAY + "[" + ChatColor.RED + "Admin" + ChatColor.GRAY + "] ";
	public static String srmodPrefix = ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "SrMod" + ChatColor.GRAY + "] ";
	public static String modPrefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Mod" + ChatColor.GRAY + "] ";
	public static String trialPrefix = ChatColor.GRAY + "[" + ChatColor.YELLOW + "Trial" + ChatColor.GRAY + "] ";
	
	@EventHandler
	public void ChatFormat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		
		if(p.hasPermission("chat.dev"))
		{
			e.setFormat(devPrefix + ChatColor.GOLD + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
		}
		else if(p.hasPermission("chat.owner"))
		{
			e.setFormat(ownerPrefix + ChatColor.DARK_RED + ChatColor.BOLD + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
		}
		else if(p.hasPermission("chat.admin"))
		{
			e.setFormat(adminPrefix + ChatColor.RED + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
		}
		else if(p.hasPermission("chat.srmod"))
		{
			e.setFormat(srmodPrefix + ChatColor.LIGHT_PURPLE + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
		}
		else if(p.hasPermission("chat.mod"))
		{
			e.setFormat(modPrefix + ChatColor.AQUA + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
		}
		else if(p.hasPermission("chat.trial"))
		{
			e.setFormat(trialPrefix + ChatColor.YELLOW + p.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE +  e.getMessage());
		}
		
		if(Commands.pollstarted == true)
		{
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Chat is muted for " + Poll.seconds + " more second(s)");
		}
	}
}
