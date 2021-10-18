package me.fairuhc.UHC.stats;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OresMined implements Listener
{
	
	@EventHandler
	public void getBlock(BlockBreakEvent e)
	{
		Block b = e.getBlock();
		Player p = e.getPlayer();
		MySQLOresMined ores = new MySQLOresMined();
		
		if(p.getWorld().getName().equals("uhc"))
		{
			if(b.getType() == Material.DIAMOND_ORE)
			{
				ores.addDiamond(p.getUniqueId(), 1);
			}
			else if(b.getType() == Material.GOLD_ORE)
			{
				ores.addGold(p.getUniqueId(), 1);
			}
			else if(b.getType() == Material.IRON_ORE)
			{
				ores.addIron(p.getUniqueId(), 1);
			}
			else if(b.getType() == Material.REDSTONE_ORE)
			{
				ores.addRedstone(p.getUniqueId(), 1);
			}
			else if(b.getType() == Material.LAPIS_ORE)
			{
				ores.addLapis(p.getUniqueId(), 1);
			}
			else if(b.getType() == Material.COAL_ORE)
			{
				ores.addCoal(p.getUniqueId(), 1);
			}
			else if(b.getType() == Material.QUARTZ_ORE)
			{
				ores.addQuartz(p.getUniqueId(), 1);
			}
		}
	}
}
