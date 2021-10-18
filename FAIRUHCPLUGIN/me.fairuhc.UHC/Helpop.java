package me.fairuhc.UHC;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class Helpop implements Listener
{
	public Main plugin;
	
	public Helpop(Main plugin)
	{
		this.plugin = plugin;
	}
	
	public void sendMessage(Player p, String msg)
	{
		String prefix = ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "HelpOp" + ChatColor.WHITE + "]";
		
		for(Player staff : Bukkit.getOnlinePlayers())
		{
			if(staff.hasPermission("helpop.see"))
			{
				staff.sendMessage(ChatColor.WHITE + "§m-----------------" + prefix + "§m-----------------");
				staff.sendMessage(ChatColor.AQUA + " From " + ChatColor.GRAY + "» " + ChatColor.LIGHT_PURPLE + p.getDisplayName());
				staff.sendMessage(ChatColor.AQUA + " Message " + ChatColor.GRAY + "» " + ChatColor.LIGHT_PURPLE + msg);
				staff.sendMessage(ChatColor.WHITE + "§m------------------------------------------");
			}
		}
	}
	
	public void delay(Player p)
	{
		new BukkitRunnable()
		{
			int delay = Commands.helpopdelay.get(p.getUniqueId());
			
			public void run()
			{
				if(delay == 0)
				{
					Commands.helpopdelay.remove(p.getUniqueId());
					cancel();
					return;
				}
				
				delay--;
				Commands.helpopdelay.replace(p.getUniqueId(), delay);
			}
			
		}.runTaskTimer(plugin, 0, 20);
	}
}
