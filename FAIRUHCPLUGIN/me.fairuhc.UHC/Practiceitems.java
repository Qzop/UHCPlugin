package me.fairuhc.UHC;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Practiceitems implements Listener
{
	public void onDeath(Player p)
	{
		for(int i = 0; i < 40; i++)
		{
			p.getInventory().clear(i);
		}
		
		getItems(p);
	}
	
	public void getItems(Player p)
	{
		Inventory inv = p.getInventory();
		
		if(Gamemanager.started == false)
		{
			inv.setItem(0, new ItemStack(Material.DIAMOND_SWORD, 1));
			inv.getItem(0).addEnchantment(Enchantment.DAMAGE_ALL, 3);
			
			inv.setItem(1, new ItemStack(Material.FISHING_ROD, 1));
			
			inv.setItem(2, new ItemStack(Material.BOW, 1));
			inv.getItem(2).addEnchantment(Enchantment.ARROW_DAMAGE, 2);
			
			inv.setItem(8, new ItemStack(Material.ARROW, 10));
			
			p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
			p.getInventory().getBoots().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			
			p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
			p.getInventory().getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			
			p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			p.getInventory().getChestplate().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			
			p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
			p.getInventory().getHelmet().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		}
	}
}
