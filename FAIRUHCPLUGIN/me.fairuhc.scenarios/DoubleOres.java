package me.fairuhc.UHC.scenarios;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DoubleOres implements Listener
{
	@EventHandler
	public void doubleores(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Inventory inv = p.getInventory();
		
		if(ScenarioEvents.doubl == true && p.getGameMode().equals(GameMode.SURVIVAL))
		{
			if(b.getType().equals(Material.DIAMOND_ORE))
			{
				e.setCancelled(true);
				b.setType(Material.AIR);
				b.breakNaturally();
				
				for(int i = 0; i < 2; i++)
				{
					inv.addItem(new ItemStack(Material.DIAMOND));
				}
				
				p.giveExp(7);
			}
			else if(b.getType().equals(Material.GOLD_ORE))
			{
				e.setCancelled(true);
				b.setType(Material.AIR);
				b.breakNaturally();
				
				for(int i = 0; i < 2; i++)
				{
					inv.addItem(new ItemStack(Material.GOLD_INGOT));
				}
				
				p.giveExp(3);
			}
			else if(b.getType().equals(Material.IRON_ORE))
			{
				e.setCancelled(true);
				b.setType(Material.AIR);
				b.breakNaturally();
				
				for(int i = 0; i < 2; i++)
				{
					inv.addItem(new ItemStack(Material.IRON_INGOT));
				}
				
				p.giveExp(3);
			}
		}
	}
}	
