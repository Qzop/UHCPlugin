package me.fairuhc.UHC.scenarios;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.fairuhc.UHC.Gamemanager;

public class Cutclean implements Listener
{
	@EventHandler
	public void cutclean(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block block = e.getBlock();
		
		Random rand = new Random();
		
		ArrayList<Material> coal = new ArrayList<Material>();
		ArrayList<Material> iron = new ArrayList<Material>();
		ArrayList<Material> gold = new ArrayList<Material>();
		ArrayList<Material> diamond = new ArrayList<Material>();
		ArrayList<Material> redstone = new ArrayList<Material>();
		ArrayList<Material> emerald = new ArrayList<Material>();
		ArrayList<Material> lapis = new ArrayList<Material>();
		
		coal.add(Material.WOOD_PICKAXE);
		coal.add(Material.STONE_PICKAXE);
		coal.add(Material.GOLD_PICKAXE);
		coal.add(Material.IRON_PICKAXE);
		coal.add(Material.DIAMOND_PICKAXE);
		
		iron.add(Material.STONE_PICKAXE);
		iron.add(Material.GOLD_PICKAXE);
		iron.add(Material.IRON_PICKAXE);
		iron.add(Material.DIAMOND_PICKAXE);
		
		gold.add(Material.GOLD_PICKAXE);
		gold.add(Material.IRON_PICKAXE);
		gold.add(Material.DIAMOND_PICKAXE);
		
		diamond.add(Material.GOLD_PICKAXE);
		diamond.add(Material.IRON_PICKAXE);
		diamond.add(Material.DIAMOND_PICKAXE);
		
		redstone.add(Material.GOLD_PICKAXE);
		redstone.add(Material.IRON_PICKAXE);
		redstone.add(Material.DIAMOND_PICKAXE);
		
		emerald.add(Material.GOLD_PICKAXE);
		emerald.add(Material.IRON_PICKAXE);
		emerald.add(Material.DIAMOND_PICKAXE);
		
		lapis.add(Material.STONE_PICKAXE);
		lapis.add(Material.GOLD_PICKAXE);
		lapis.add(Material.IRON_PICKAXE);
		lapis.add(Material.DIAMOND_PICKAXE);
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && ScenarioEvents.cutclean == true && Gamemanager.started == true)
		{
			if(block.getType().equals(Material.IRON_ORE) && iron.contains(p.getItemInHand().getType()))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				Inventory inv = p.getInventory();
				
				if(p.getInventory().firstEmpty() != -1)
				{
					p.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT));
				}
				else
				{
					inv.addItem(new ItemStack(Material.IRON_INGOT));
				}
				
				p.giveExp(3);
			}
			if(block.getType().equals(Material.GOLD_ORE) && ScenarioEvents.goldless == false && gold.contains(p.getItemInHand().getType()))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				Inventory inv = p.getInventory();
				
				if(p.getInventory().firstEmpty() != -1)
				{
					p.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT));
				}
				else
				{
					inv.addItem(new ItemStack(Material.GOLD_INGOT));
				}
				p.giveExp(3);
			}
			else if(ScenarioEvents.goldless == true)
			{
				if(block.getType().equals(Material.GOLD_ORE) && gold.contains(p.getItemInHand().getType()))
				{
					e.setCancelled(true);
					block.setType(Material.AIR);
					block.breakNaturally();
					p.giveExp(3);
				}
			}
			
			if(block.getType().equals(Material.DIAMOND_ORE) && ScenarioEvents.diamondless == false && diamond.contains(p.getItemInHand().getType()))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				Inventory inv = p.getInventory();
				
				if(p.getInventory().firstEmpty() != -1)
				{
					p.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.DIAMOND));
				}
				else
				{
					inv.addItem(new ItemStack(Material.DIAMOND));
				}
				
				p.giveExp(7);
			}
			else if(ScenarioEvents.diamondless == true)
			{
				if(block.getType().equals(Material.DIAMOND_ORE) && diamond.contains(p.getItemInHand().getType()))
				{
					e.setCancelled(true);
					block.setType(Material.AIR);
					block.breakNaturally();
					p.giveExp(7);
				}
			}
			
			if(block.getType().equals(Material.GRAVEL))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				Inventory inv = p.getInventory();
				
				if(p.getInventory().firstEmpty() != -1)
				{
					p.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.FLINT));
				}
				else
				{
					inv.addItem(new ItemStack(Material.FLINT));
				}
			}
			
			if(block.getType().equals(Material.COAL_ORE) && coal.contains(p.getItemInHand().getType()))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				Inventory inv = p.getInventory();
				
				if(p.getInventory().firstEmpty() != -1)
				{
					p.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.COAL));
				}
				else
				{
					inv.addItem(new ItemStack(Material.COAL));
				}
				
				p.giveExp(2);
			}
			
			if(block.getType().equals(Material.EMERALD_ORE) && emerald.contains(p.getItemInHand().getType()))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				Inventory inv = p.getInventory();
				
				if(p.getInventory().firstEmpty() != -1)
				{
					p.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.EMERALD));
				}
				else
				{
					inv.addItem(new ItemStack(Material.EMERALD));
				}
				
				p.giveExp(7);
			}
			
			if(block.getType().equals(Material.REDSTONE_ORE) && redstone.contains(p.getItemInHand().getType()))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				Inventory inv = p.getInventory();
				
				if(p.getInventory().firstEmpty() != -1)
				{
					p.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.REDSTONE, rand.nextInt(1) + 4, (short) 4));
				}
				else
				{
					inv.addItem(new ItemStack(Material.REDSTONE, rand.nextInt(1) + 4));
				}
				
				p.giveExp(5);
			}
			
			if(block.getType().equals(Material.LAPIS_ORE) && lapis.contains(p.getItemInHand().getType()))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				Inventory inv = p.getInventory();
				
				if(p.getInventory().firstEmpty() != -1)
				{
					p.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.INK_SACK, rand.nextInt(4) + 4, (short) 4));
				}
				else
				{
					inv.addItem(new ItemStack(Material.INK_SACK, rand.nextInt(4) + 4, (short) 4));
				}
				
				p.giveExp(5);
			}
		}
	}
	
	@EventHandler
	public void cutcleanformobs(EntityDeathEvent e)
	{
		Random rand = new Random();
		
		if(ScenarioEvents.cutclean == true && Gamemanager.started == true)
		{
			if(e.getEntity().getType() == EntityType.COW)
			{
				int food = rand.nextInt(3) + 1;
				int leather = rand.nextInt(3) + 1;
				
				e.getDrops().clear();
				e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.COOKED_BEEF, food));
				e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.LEATHER, leather));
			}
			else if(e.getEntity().getType() == EntityType.PIG)
			{
				int num = rand.nextInt(3) + 1;
				
				e.getDrops().clear();
				e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.GRILLED_PORK, num));
			}
			else if(e.getEntity().getType() == EntityType.CHICKEN)
			{
				int feather = rand.nextInt(3) + 1;
				
				e.getDrops().clear();
				e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.COOKED_CHICKEN, 1));
				e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.FEATHER, feather));
			}
			else if(e.getEntity().getType() == EntityType.RABBIT)
			{
				int num = rand.nextInt(3) + 1;
				
				e.getDrops().clear();
				e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.COOKED_RABBIT, num));
			}
		}
	}
}
