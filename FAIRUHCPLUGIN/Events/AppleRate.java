package Events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

import me.fairuhc.UHC.Config;

public class AppleRate implements Listener
{
	@EventHandler
	public void applerate_player(BlockBreakEvent e)
	{
		Block b = e.getBlock();
		
		if(b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2)
		{
			Random rand = new Random();
			
			if(rand.nextInt(100) <= Config.applerate && e.getPlayer().getItemInHand().getType() == Material.SHEARS)
			{
				b.getDrops().clear();
				b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.APPLE, 1));
			}
		}
	}
	
	@EventHandler
	public void applerate_decay(LeavesDecayEvent e)
	{
		Block b = e.getBlock();
		
		if(b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2)
		{
			Random rand = new Random();
			
			if(rand.nextInt(100) <= Config.applerate)
			{
				b.getDrops().clear();
				b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.APPLE, 1));
			}
		}
	}
}
