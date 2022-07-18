package core.Events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Scatter.Scatter;

public class BreakEvent implements Listener
{
	@EventHandler
	public void onBreak(BlockBreakEvent e)
	{
		Player p = e.getPlayer();

		if(Scatter.started)
		{
			if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()) || PlayerKills.spectator.contains(p.getUniqueId()))
			{
				e.setCancelled(true);
			}
		}
		else
		{
			if(!p.hasPermission("blockbreak.bypass"))
			{
				e.setCancelled(true);
			}
		}
	}
}
