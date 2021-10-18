package Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;

public class ChatEvent implements Listener
{
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		
		if(Timer.chat == false)
		{
			if(p.hasPermission("scatter.bypass"))
			{
				e.setCancelled(false);
			}
			else
			{
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "Chat is muted. Use /helpop if you have a question.");
			}
		}
	}
}
