package me.fairuhc.UHC.scenarios;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Main;

public class Veinminer implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void veinMiner(BlockBreakEvent e)
	{
		@SuppressWarnings("unused")
		Block b = e.getBlock();
		Player p = e.getPlayer();
		
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
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && ScenarioEvents.veinminer == true)
		{
			if(e.getBlock().getTypeId() == 15 && iron.contains(p.getItemInHand().getType()))
			{	
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				e.getBlock().breakNaturally();
				Inventory inv = p.getInventory();
				
				if(p.getInventory().firstEmpty() != -1)
				{
					p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
				}
				else
				{
					inv.addItem(new ItemStack(Material.IRON_INGOT));
				}
				
				p.giveExp(3);
				
				World w = b.getWorld();
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x + 1, y, z).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x + 1, y, z).setType(Material.AIR);
							w.getBlockAt(x + 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							x++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x - 1, y, z).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x - 1, y, z).setType(Material.AIR);
							w.getBlockAt(x - 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							x--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z + 1).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z + 1).setType(Material.AIR);
							w.getBlockAt(x, y, z + 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							z++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							x++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x - 1, y, z).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x - 1, y, z).setType(Material.AIR);
							w.getBlockAt(x - 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							x--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z + 1).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z + 1).setType(Material.AIR);
							w.getBlockAt(x, y, z + 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							z++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY()- 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							x++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x - 1, y, z).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x - 1, y, z).setType(Material.AIR);
							w.getBlockAt(x - 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							x--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z + 1).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z + 1).setType(Material.AIR);
							w.getBlockAt(x, y, z + 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							z++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() + 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() + 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.IRON_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();

								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
								}
								else
								{
									inv.addItem(new ItemStack(Material.IRON_INGOT));
								}
								
								p.giveExp(3);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() - 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() - 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.IRON_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();
								
								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
								}
								else
								{
									inv.addItem(new ItemStack(Material.IRON_INGOT));
								}
								
								p.giveExp(3);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() + 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() - 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.IRON_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();
								
								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
								}
								else
								{
									inv.addItem(new ItemStack(Material.IRON_INGOT));
								}
								
								p.giveExp(3);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() - 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() + 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.IRON_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.IRON_INGOT));
							}
							
							p.giveExp(3);
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.IRON_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();
								
								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
								}
								else
								{
									inv.addItem(new ItemStack(Material.IRON_INGOT));
								}
								
								p.giveExp(3);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
			}
			else if(e.getBlock().getTypeId() == 56 && diamond.contains(p.getItemInHand().getType()))
			{
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				e.getBlock().breakNaturally();
				Inventory inv = p.getInventory();
				
				if(p.getInventory().firstEmpty() != -1)
				{
					p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
				}
				else
				{
					inv.addItem(new ItemStack(Material.DIAMOND));
				}
				
				p.giveExp(7);
				
				World w = b.getWorld();
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x + 1, y, z).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x + 1, y, z).setType(Material.AIR);
							w.getBlockAt(x + 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							x++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x - 1, y, z).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x - 1, y, z).setType(Material.AIR);
							w.getBlockAt(x - 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							x--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z + 1).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z + 1).setType(Material.AIR);
							w.getBlockAt(x, y, z + 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							z++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							x++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x - 1, y, z).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x - 1, y, z).setType(Material.AIR);
							w.getBlockAt(x - 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							x--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z + 1).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z + 1).setType(Material.AIR);
							w.getBlockAt(x, y, z + 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							z++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY()- 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							x++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x - 1, y, z).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x - 1, y, z).setType(Material.AIR);
							w.getBlockAt(x - 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							x--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z + 1).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z + 1).setType(Material.AIR);
							w.getBlockAt(x, y, z + 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							z++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() + 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() + 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							inv.addItem(new ItemStack(Material.DIAMOND));
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.DIAMOND_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();
								
								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
								}
								else
								{
									inv.addItem(new ItemStack(Material.DIAMOND));
								}
								
								p.giveExp(7);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() - 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() - 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.DIAMOND_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();
								
								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
								}
								else
								{
									inv.addItem(new ItemStack(Material.DIAMOND));
								}
								
								p.giveExp(7);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() + 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() - 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.DIAMOND_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();
								
								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
								}
								else
								{
									inv.addItem(new ItemStack(Material.DIAMOND));
								}
								
								p.giveExp(7);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() - 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() + 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.DIAMOND_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
							}
							else
							{
								inv.addItem(new ItemStack(Material.DIAMOND));
							}
							
							p.giveExp(7);
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.DIAMOND_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();
								
								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
								}
								else
								{
									inv.addItem(new ItemStack(Material.DIAMOND));
								}
								
								p.giveExp(7);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
			}
			else if(e.getBlock().getTypeId() == 14)
			{
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				e.getBlock().breakNaturally();
				Inventory inv = p.getInventory();
				
				if(p.getInventory().firstEmpty() != -1)
				{
					p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
				}
				else
				{
					inv.addItem(new ItemStack(Material.GOLD_INGOT));
				}
				
				p.giveExp(3);
				
				World w = b.getWorld();
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x + 1, y, z).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x + 1, y, z).setType(Material.AIR);
							w.getBlockAt(x + 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							x++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x - 1, y, z).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x - 1, y, z).setType(Material.AIR);
							w.getBlockAt(x - 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							x--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z + 1).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z + 1).setType(Material.AIR);
							w.getBlockAt(x, y, z + 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							z++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							x++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x - 1, y, z).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x - 1, y, z).setType(Material.AIR);
							w.getBlockAt(x - 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							x--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z + 1).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z + 1).setType(Material.AIR);
							w.getBlockAt(x, y, z + 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							z++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() + 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY()- 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							x++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x - 1, y, z).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x - 1, y, z).setType(Material.AIR);
							w.getBlockAt(x - 1, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							x--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z + 1).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z + 1).setType(Material.AIR);
							w.getBlockAt(x, y, z + 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							z++;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX();
					int y = e.getBlock().getY() - 1;
					int z = e.getBlock().getZ();
					
					public void run()
					{
						if(w.getBlockAt(x, y, z - 1).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z - 1).setType(Material.AIR);
							w.getBlockAt(x, y, z - 1).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							z--;
						}
						else
						{
							cancel();
							return;
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() + 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() + 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.GOLD_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();
								
								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
								}
								else
								{
									inv.addItem(new ItemStack(Material.GOLD_INGOT));
								}
								
								p.giveExp(3);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() - 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() - 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.GOLD_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();
								
								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
								}
								else
								{
									inv.addItem(new ItemStack(Material.GOLD_INGOT));
								}
								
								p.giveExp(3);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() + 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() - 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.GOLD_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();
								
								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
								}
								else
								{
									inv.addItem(new ItemStack(Material.GOLD_INGOT));
								}
								
								p.giveExp(3);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
				
				new BukkitRunnable()
				{
					int x = e.getBlock().getX() - 1;
					int y = e.getBlock().getY();
					int z = e.getBlock().getZ() + 1;
					
					public void run()
					{
						if(w.getBlockAt(x, y, z).getType() == Material.GOLD_ORE)
						{
							e.setCancelled(true);
							w.getBlockAt(x, y, z).setType(Material.AIR);
							w.getBlockAt(x, y, z).breakNaturally();
							Inventory inv = p.getInventory();
							
							if(p.getInventory().firstEmpty() != -1)
							{
								p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
							}
							else
							{
								inv.addItem(new ItemStack(Material.GOLD_INGOT));
							}
							
							p.giveExp(3);
							
							y++;
						}
						else
						{
							if(w.getBlockAt(x, y - 2, z).getType() == Material.GOLD_ORE)
							{
								e.setCancelled(true);
								w.getBlockAt(x, y - 2, z).setType(Material.AIR);
								w.getBlockAt(x, y - 2, z).breakNaturally();
								Inventory inv = p.getInventory();
								
								if(p.getInventory().firstEmpty() != -1)
								{
									p.getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
								}
								else
								{
									inv.addItem(new ItemStack(Material.GOLD_INGOT));
								}
								
								p.giveExp(3);
								
								y--;
							}
							else
							{
								cancel();
								return;
							}
						}
					}
					
				}.runTaskTimer(plugin, 0, 1);
			}
		}
	}
}
