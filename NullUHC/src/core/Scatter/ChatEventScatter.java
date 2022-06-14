package core.Scatter;

import core.mainPackage.Commands;
import core.mainPackage.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEventScatter implements Listener
{
    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onChatDuringScat(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();

        if(Commands.chat)
        {
            if(!p.hasPermission("chat.bypass.five.min"))
            {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You may not chat now!");
            }
        }
    }
}
