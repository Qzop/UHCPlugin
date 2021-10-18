package me.fairuhc.UHC.scenarios;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.fairuhc.UHC.Gamemanager;

public class GoldlessandDiamondless implements Listener
{
	@EventHandler
	public void golddialess(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block block = e.getBlock();
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && ScenarioEvents.goldless == true && Gamemanager.started == true)
		{
			if(block.getType().equals(Material.GOLD_ORE))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				p.giveExp(3);
			}
		}
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && ScenarioEvents.diamondless == true && Gamemanager.started == true)
		{
			if(block.getType().equals(Material.DIAMOND_ORE))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				p.giveExp(7);
			}
		}
	}
}
