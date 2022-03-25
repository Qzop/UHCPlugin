package chat.mainPackage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Poll implements Listener
{
	public static int seconds;
	Main plugin = Main.getPlugin(Main.class);
	
	public void startPoll(String topic)
	{
		TextComponent yes = new TextComponent(TextComponent.fromLegacyText("" + ChatColor.GREEN + ChatColor.BOLD + "[Yes]"));
		TextComponent space = new TextComponent(TextComponent.fromLegacyText("      "));
		TextComponent no = new TextComponent(TextComponent.fromLegacyText("" + ChatColor.RED + ChatColor.BOLD + "[No]"));
		
		yes.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/vote yes"));
		yes.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "Vote Yes").create()));
		no.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/vote no"));
		no.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "Vote No").create()));
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "Poll" + ChatColor.GRAY + "]" + ChatColor.RED + " Chat is disabled for 15 seconds for the poll.");
			p.sendMessage(ChatColor.GRAY + "§m------------------------------\n" + ChatColor.GOLD + ChatColor.BOLD + "     NEW POLL TOPIC: \n" + " " + "\n" + ChatColor.WHITE + "\""
					+ ChatColor.AQUA + topic + ChatColor.WHITE + "\"" + "\n" + " ");
			p.spigot().sendMessage(space, yes, space ,no);
			p.sendMessage("\n"  + ChatColor.GRAY + "§m------------------------------");	
		}
		
		new BukkitRunnable()
		{
			int seconds = 15;
			
			public void run()
			{
				Poll.seconds = seconds;
				
				if(seconds == 0)
				{
					if((Commands.yes.size() + Commands.no.size()) == 0)
					{
						for(Player p : Bukkit.getOnlinePlayers())
						{
							p.sendMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "Poll" + ChatColor.GRAY + "]" + ChatColor.GREEN + " Chat is now enabled.");
							p.sendMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "Poll" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " Results: " + ChatColor.RED + "No votes were counted.");
						}
					}
					else
					{
						double yespercent = (Commands.yes.size()) / (Commands.yes.size() + Commands.no.size());
						double nopercent = (Commands.no.size()) / (Commands.yes.size() + Commands.no.size());
						
						String results;
						
						if(yespercent == 1.0)
						{
							results = "" + ChatColor.GREEN + "100% of votes were 'Yes', " + ChatColor.RED + "0% of votes were 'No'.";
						}
						else if(nopercent == 1.0)
						{
							results = "" + ChatColor.GREEN + "0% of votes were 'Yes', " + ChatColor.RED + "100% of votes were 'No'.";
						}
						else
						{
							results = "" + ChatColor.GREEN + yespercent + "% of votes were 'Yes', " + ChatColor.RED + nopercent + "% of votes were 'No'.";
						}
						
						for(Player p : Bukkit.getOnlinePlayers())
						{
							p.sendMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "Poll" + ChatColor.GRAY + "]" + ChatColor.GREEN + " Chat is now enabled.");
							p.sendMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "Poll" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " Results: " + results);
						}
					}
					
					Commands.pollstarted = false;
					seconds = 15;
					
					cancel();
				}
				
				seconds--;
			}
			
		}.runTaskTimer(plugin, 0, 20);
	}
}
