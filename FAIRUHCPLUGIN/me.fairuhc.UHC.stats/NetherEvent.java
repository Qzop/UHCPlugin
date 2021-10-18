package me.fairuhc.UHC.stats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class NetherEvent implements Listener
{
	@EventHandler
	public void onTeleport(PlayerPortalEvent e)
	{
		Player p = e.getPlayer();
		MySQLNether nether = new MySQLNether();
		
		nether.addEntries(p.getUniqueId(), 1);
	}
}
