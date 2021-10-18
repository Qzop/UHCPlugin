package me.fairuhc.UHC.scenarios;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BloodDiamond implements Listener
{
	@EventHandler
	public void bloodDiamond(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		
		if(ScenarioEvents.blooddia == true && p.getGameMode().equals(GameMode.SURVIVAL))
		{
			if(b.getType().equals(Material.DIAMOND_ORE))
			{
				p.setHealth(p.getHealth() - 1);
			}
		}
	}
}
