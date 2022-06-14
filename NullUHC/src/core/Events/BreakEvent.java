package core.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import core.HostsMods.HostsMods;
import core.Scatter.Scatter;

public class BreakEvent implements Listener
{
	@EventHandler
	public void onBreak(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		
		if(!Scatter.started && !p.hasPermission("blockbreak.bypass"))
		{
			e.setCancelled(true);
		}
		
		if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()))
		{
			e.setCancelled(true);
		}
	}
}
