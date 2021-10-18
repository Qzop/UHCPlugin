package me.fairuhc.UHC.scenarios;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;

public class RodLess implements Listener
{
	@EventHandler
	public void rodless(PrepareItemCraftEvent e)
	{
		if(ScenarioEvents.rodless == true)
		{
			String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "Rodless" + ChatColor.GRAY + "]";
			
			Material itemType = e.getRecipe().getResult().getType();
			@SuppressWarnings({ "unused", "deprecation" })
			Byte itemData = e.getRecipe().getResult().getData().getData();
		
			if(itemType == Material.FISHING_ROD)
			{
				e.getInventory().setResult(new ItemStack(Material.AIR));
				for(HumanEntity he: e.getViewers())
				{
					if(he instanceof Player)
					{
						((Player)he).sendMessage(prefix + ChatColor.RED + " You cannot craft fishing rods!");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void rod(PlayerFishEvent e)
	{
		Player p = e.getPlayer();
		String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "Rodless" + ChatColor.GRAY + "]";
		
		if(ScenarioEvents.rodless == true)
		{
			if(e.getState() == State.FISHING)
			{
				e.setCancelled(true);
			
				p.sendMessage(prefix + ChatColor.GOLD + " You cannot use fishing rods!");
			}
		}
	}
}
