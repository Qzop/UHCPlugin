package core.Events;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.Scoreboard.Time;
import core.mainPackage.Commands;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
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

	@EventHandler
	public void placeLava(PlayerBucketEmptyEvent e)
	{
		if(Scatter.started || Commands.scatter)
		{
			if(Time.minutes < ConfigInventory.gracePeriod)
			{
				if(e.getBucket().equals(Material.LAVA_BUCKET))
				{
					e.getPlayer().sendMessage(ChatColor.RED + "You may not place lava during grace period!");
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void fns(BlockIgniteEvent e)
	{
		if(Scatter.started || Commands.scatter)
		{
			if(Time.minutes < ConfigInventory.gracePeriod)
			{
				e.getPlayer().sendMessage(ChatColor.RED + "You may not use flint n steel during grace period!");
				e.setCancelled(true);
			}
		}
	}
}
