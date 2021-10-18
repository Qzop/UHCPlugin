package me.fairuhc.UHC.scenarios;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Anonymous implements Listener
{
	@EventHandler
	public void anon(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		String owner = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Owner"+ ChatColor.GRAY + "]" + ChatColor.DARK_RED;
		String admin = ChatColor.GRAY + "[" + ChatColor.RED + "Admin"+ ChatColor.GRAY + "]" + ChatColor.RED;
		String srmod = ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + "Sr.Mod"+ ChatColor.GRAY + "]" + ChatColor.LIGHT_PURPLE;
		String mod = ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Mod"+ ChatColor.GRAY + "]" + ChatColor.AQUA;
		String trial = ChatColor.GRAY + "[" + ChatColor.YELLOW + "Trial"+ ChatColor.GRAY + "]" + ChatColor.YELLOW;
			
		if(ScenarioEvents.anon == true)
		{
			p.setDisplayName(ChatColor.MAGIC + p.getName());

			if(p.hasPermission("chat.owner"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.admin"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.srmod"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.mod"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.trial"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.default"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
		}
		else if(ScenarioEvents.anon == false)
		{
			p.setDisplayName(p.getName());
			
			if(p.hasPermission("chat.owner"))
			{
				e.setFormat(owner + " %s" + ChatColor.WHITE+ ": " + ChatColor.GOLD + "%s");
			}
			else if(p.hasPermission("chat.admin"))
			{
				e.setFormat(admin + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.srmod"))
			{
				e.setFormat(srmod + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.mod"))
			{
				e.setFormat(mod + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.trial"))
			{
				e.setFormat(trial + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.default"))
			{
				e.setFormat(ChatColor.WHITE + "%s: %s");
			}
		}
	}
}
