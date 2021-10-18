package me.fairuhc.UHC.teams;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class InvitePlayer implements Listener
{
	Main plugin;
	public TeamManager tm;
	public String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
	
	public InvitePlayer(Main plugin)
	{
		this.plugin = plugin;
		tm = new TeamManager(plugin);
	}
	
	public void invitePlayer(Player p, Player target)
	{ 
		if(tm.hasTeam(p) == true && Gamemanager.started == false && tm.getTeamsize(p) != Gamemanager.teamsize)
		{
			if(p.getUniqueId() == target.getUniqueId())
			{
				p.sendMessage(prefix + ChatColor.RED + " You cannot invite yourself to a team!");
			}
			else
			{
				if(tm.hasTeam(target) == true)
				{
					p.sendMessage(prefix + " " + ChatColor.RED + target.getDisplayName() + " is already on a team!");
				}
				else
				{
					TeamManager.hasinvite.put(p.getDisplayName(), target.getDisplayName());
				}
				
				if(TeamManager.hasinvite.containsKey(p.getDisplayName()))
				{
					p.sendMessage(prefix + ChatColor.GREEN + " You have invited " + ChatColor.AQUA +  target.getDisplayName() + ChatColor.GREEN + " to your team!");
					
					target.sendMessage(ChatColor.WHITE + "§m------------------------------------------");
					target.sendMessage(ChatColor.AQUA + " You have been invited to " + p.getDisplayName() + "'s team!\n");
					
					TextComponent a = new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "                [Accept]");
					TextComponent d = new TextComponent(ChatColor.RED + "" + ChatColor.BOLD + "   [Deny]");
					
					d.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "Deny " + p.getDisplayName() 
					+ "'s invite\n").create()));
					d.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team deny " + p.getDisplayName()));
					
					a.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "Accept " + p.getDisplayName() 
					+ "'s invite\n").create()));
					a.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team accept " + p.getDisplayName()));
					
					target.spigot().sendMessage(a,d);
					target.sendMessage(ChatColor.AQUA + " You have 30 seconds until the invite expires.");
					
					target.sendMessage(ChatColor.WHITE + "§m------------------------------------------");
					
					new BukkitRunnable() 
					{ 
						private int seconds = 30;
						
						public void run()
						{
							seconds--;
							
							if(seconds == 0 && TeamManager.hasinvite.containsKey(p.getDisplayName()))
							{
								target.sendMessage(prefix + ChatColor.RED + " Your invite from " + ChatColor.AQUA + p.getDisplayName() + ChatColor.RED + " has expired.");
								p.sendMessage(prefix + " " + ChatColor.AQUA + target.getDisplayName() + ChatColor.RED +  " failed to accept your invite.");
								TeamManager.hasinvite.remove(p.getDisplayName());
								
								cancel();
								return;
							}
						}
						
					}.runTaskTimer(plugin, 0 , 20); 
				}
			}
		}
		else if(tm.getTeamsize(p) == Gamemanager.teamsize)
		{
			p.sendMessage(ChatColor.RED + "You cannot exceed the team limit!");
		}
		else if(Gamemanager.started == true)
		{
			p.sendMessage(ChatColor.RED + "You cannot invite players during the game!");
		}
		else if(tm.hasTeam(p) == false)
		{
			p.sendMessage(ChatColor.RED + "You must be on a team to invite players!");
		}
    }
	
	public void denyInvite(Player p, Player denyer)
	{
		if(TeamManager.hasinvite.containsKey(p.getDisplayName()))
		{
			TeamManager.hasinvite.remove(p.getDisplayName());
			
			denyer.sendMessage(prefix + ChatColor.AQUA + " You have " + ChatColor.RED + ChatColor.BOLD + "Denied " + ChatColor.AQUA + p.getDisplayName() + "'s invite.");
			p.sendMessage(prefix + " " + ChatColor.AQUA + denyer.getDisplayName() + " has " + ChatColor.RED + ChatColor.BOLD + "Denied" + ChatColor.AQUA + " your invite.");
		}
		else
		{
			p.sendMessage(prefix + ChatColor.RED + " Invite has expired.");
		}
	}
}
