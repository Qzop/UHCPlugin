package me.fairuhc.UHC.scenarios;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class HasteyBoys implements Listener
{
	@EventHandler
	public void hasteyboys(PrepareItemCraftEvent e)
	{
		if(ScenarioEvents.hastey == true)
		{
			ItemStack result = e.getInventory().getResult();
			
			if(result.getType() == Material.DIAMOND_PICKAXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.WOOD_PICKAXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.STONE_PICKAXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.IRON_PICKAXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.GOLD_PICKAXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			
			if(result.getType() == Material.WOOD_AXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.STONE_AXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.IRON_AXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.GOLD_AXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.DIAMOND_AXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			
			if(result.getType() == Material.WOOD_SPADE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.STONE_SPADE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.IRON_SPADE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.GOLD_SPADE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.DIAMOND_SPADE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
		}
	}
}
