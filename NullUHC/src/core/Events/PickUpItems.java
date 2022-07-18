package core.Events;

import core.Scatter.Scatter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;

public class PickUpItems implements Listener
{
	@EventHandler
	public void onPickUp(PlayerPickupItemEvent e)
	{
		Player p = e.getPlayer();
		
		if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()) || PlayerKills.spectator.contains(p.getUniqueId()))
		{
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e)
	{
		Player p = e.getPlayer();

		if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()) || PlayerKills.spectator.contains(p.getUniqueId()) || (!Scatter.started && !p.hasPermission("blockbreak.bypass")))
		{
			e.setCancelled(true);
		}
	}
}
