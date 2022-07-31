package core.Events;

import core.mainPackage.Commands;
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

		if(Commands.scatter)
		{
			e.setCancelled(true);
		}
		else if(Scatter.started)
		{
			if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()) || PlayerKills.spectator.contains(p.getUniqueId()) || p.getWorld().getName().equals("world") || p.getWorld().getName().equals("Arena"))
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
