package me.fairuhc.UHC.scenarios;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class Bowless implements Listener
{
	@EventHandler
	public void bowless(PrepareItemCraftEvent e)
	{
		if(ScenarioEvents.bowless == true)
		{
			String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "Bowless" + ChatColor.GRAY + "]";
			
			Material itemType = e.getRecipe().getResult().getType();
			@SuppressWarnings({ "unused", "deprecation" })
			Byte itemData = e.getRecipe().getResult().getData().getData();
		
			if(itemType == Material.BOW)
			{
				e.getInventory().setResult(new ItemStack(Material.AIR));
				for(HumanEntity he: e.getViewers())
				{
					if(he instanceof Player)
					{
						((Player)he).sendMessage(prefix + ChatColor.GOLD + " You cannot craft bows!");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onClick(EntityShootBowEvent e)
	{
		String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "Bowless" + ChatColor.GRAY + "]";
		
		if(e.getEntity().getType() == EntityType.PLAYER)
		{
			Player p = (Player) e.getEntity();
			
			if(ScenarioEvents.bowless == true)
			{
				if(e.getEntityType() == EntityType.PLAYER)
				{ 
					e.setCancelled(true);
					
					p.sendMessage(prefix + ChatColor.GOLD + " You cannot use bows!");
				}
			}
		}
	}	
}
