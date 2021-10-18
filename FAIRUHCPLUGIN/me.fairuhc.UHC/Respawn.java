package me.fairuhc.UHC;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.fairuhc.UHC.DeathManager.FFAWinner;
import me.fairuhc.UHC.DeathManager.Spectator;
import me.fairuhc.UHC.Scatter.FFAScatter;
import me.fairuhc.UHC.scenarios.ScenarioEvents;
import me.fairuhc.UHC.scenarios.SuperHeros;
import net.md_5.bungee.api.ChatColor;

public class Respawn implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	
	Spectator spec = new Spectator(plugin);
	private String prefix = ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" +  ChatColor.WHITE + "" + ChatColor.BOLD + "UHC " + ChatColor.GRAY + "» ";
	
	public void respawn(Player p, Player t)
	{
		if(Gamemanager.teamsize == 1)
		{
			if(!FFAScatter.ffa.contains(t.getUniqueId()) && FFAWinner.inventories.containsKey(t.getUniqueId()) && FFAWinner.deathlocation.containsKey(t.getUniqueId()) && Spectator.spectators.contains(t.getUniqueId()))
			{
				if(FFAWinner.deathlocation.get(t.getUniqueId()).getBlockX() >= 0 && FFAWinner.deathlocation.get(t.getUniqueId()).getBlockZ() >= 0)
				{
					if(FFAWinner.deathlocation.get(t.getUniqueId()).getBlockX() <= border.bordersize && FFAWinner.deathlocation.get(t.getUniqueId()).getBlockZ() <= border.bordersize)
					{
						t.teleport(FFAWinner.deathlocation.get(t.getUniqueId()));
						
						for(int i = 0; i < FFAWinner.inventories.get(t.getUniqueId()).size(); i++)
						{
							if(FFAWinner.inventories.get(t.getUniqueId()).get(i) != null)
							{
								t.getInventory().addItem(FFAWinner.inventories.get(t.getUniqueId()).get(i));
							}
						}
						
						t.setGameMode(GameMode.SURVIVAL);
						spec.removeSpec(t);
						FFAScatter.ffa.add(t.getUniqueId());
						
						FFAWinner.inventories.remove(t.getUniqueId());
						
						if(ScenarioEvents.supheroes == true)
						{
							if(SuperHeros.potions.containsKey(t.getUniqueId()))	
							{
								if(SuperHeros.speed.containsKey(t.getUniqueId()))
								{
									t.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SuperHeros.speed.get(t.getUniqueId())), true);
								}
								else if(SuperHeros.jump.containsKey(t.getUniqueId()))
								{
									t.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SuperHeros.jump.get(t.getUniqueId())), true);
								}
								else
								{
									t.addPotionEffect(new PotionEffect(SuperHeros.potions.get(t.getUniqueId()), Integer.MAX_VALUE, 1), true);
								}
							}
						}
						
						t.sendMessage(prefix + ChatColor.GREEN + "You have been respawned. Good luck.");
					}
					else
					{
						if(FFAWinner.deathlocation.get(t.getUniqueId()).getBlockX() > border.bordersize && FFAWinner.deathlocation.get(t.getUniqueId()).getBlockZ() > border.bordersize)
						{
							double x = FFAWinner.deathlocation.get(t.getUniqueId()).getX() - (FFAWinner.deathlocation.get(t.getUniqueId()).getX() - border.bordersize);
							double z = FFAWinner.deathlocation.get(t.getUniqueId()).getZ() - (FFAWinner.deathlocation.get(t.getUniqueId()).getZ() - border.bordersize);
							double y = Bukkit.getWorld("uhc_world").getHighestBlockYAt((int) x, (int) z);
							
							t.teleport(Bukkit.getWorld("uhc_world").getSpawnLocation().add(x , y, z));
							
							for(int i = 0; i < FFAWinner.inventories.get(t.getUniqueId()).size(); i++)
							{
								if(FFAWinner.inventories.get(t.getUniqueId()).get(i) != null)
								{
									t.getInventory().addItem(FFAWinner.inventories.get(t.getUniqueId()).get(i));
								}
							}
							
							t.setGameMode(GameMode.SURVIVAL);
							spec.removeSpec(t);
							FFAScatter.ffa.add(t.getUniqueId());
							
							FFAWinner.inventories.remove(t.getUniqueId());
							
							if(ScenarioEvents.supheroes == true)
							{
								if(SuperHeros.potions.containsKey(t.getUniqueId()))	
								{
									if(SuperHeros.speed.containsKey(t.getUniqueId()))
									{
										t.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SuperHeros.speed.get(t.getUniqueId())), true);
									}
									else if(SuperHeros.jump.containsKey(t.getUniqueId()))
									{
										t.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SuperHeros.jump.get(t.getUniqueId())), true);
									}
									else
									{
										t.addPotionEffect(new PotionEffect(SuperHeros.potions.get(t.getUniqueId()), Integer.MAX_VALUE, 1), true);
									}
								}
							}
							
							t.sendMessage(prefix + ChatColor.GREEN + "You have been respawned. Good luck.");
						}
						else
						{
							if(FFAWinner.deathlocation.get(t.getUniqueId()).getBlockX() > border.bordersize && !(FFAWinner.deathlocation.get(t.getUniqueId()).getBlockZ() > border.bordersize))
							{
								double x = FFAWinner.deathlocation.get(t.getUniqueId()).getX() - (FFAWinner.deathlocation.get(t.getUniqueId()).getX() - border.bordersize);
								double z = FFAWinner.deathlocation.get(t.getUniqueId()).getZ();
								double y = Bukkit.getWorld("uhc_world").getHighestBlockYAt((int) x, (int) z);
								
								t.teleport(Bukkit.getWorld("uhc_world").getSpawnLocation().add(x , y, z));
								
								for(int i = 0; i < FFAWinner.inventories.get(t.getUniqueId()).size(); i++)
								{
									if(FFAWinner.inventories.get(t.getUniqueId()).get(i) != null)
									{
										t.getInventory().addItem(FFAWinner.inventories.get(t.getUniqueId()).get(i));
									}
								}
								
								t.setGameMode(GameMode.SURVIVAL);
								spec.removeSpec(t);
								FFAScatter.ffa.add(t.getUniqueId());
								
								FFAWinner.inventories.remove(t.getUniqueId());
								
								if(ScenarioEvents.supheroes == true)
								{
									if(SuperHeros.potions.containsKey(t.getUniqueId()))	
									{
										if(SuperHeros.speed.containsKey(t.getUniqueId()))
										{
											t.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SuperHeros.speed.get(t.getUniqueId())), true);
										}
										else if(SuperHeros.jump.containsKey(t.getUniqueId()))
										{
											t.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SuperHeros.jump.get(t.getUniqueId())), true);
										}
										else
										{
											t.addPotionEffect(new PotionEffect(SuperHeros.potions.get(t.getUniqueId()), Integer.MAX_VALUE, 1), true);
										}
									}
								}
								
								t.sendMessage(prefix + ChatColor.GREEN + "You have been respawned. Good luck.");
							}
							else if(!(FFAWinner.deathlocation.get(t.getUniqueId()).getBlockX() > border.bordersize) && FFAWinner.deathlocation.get(t.getUniqueId()).getBlockZ() > border.bordersize)
							{
								double x = FFAWinner.deathlocation.get(t.getUniqueId()).getX();
								double z = FFAWinner.deathlocation.get(t.getUniqueId()).getZ() - (FFAWinner.deathlocation.get(t.getUniqueId()).getZ() - border.bordersize);
								double y = Bukkit.getWorld("uhc_world").getHighestBlockYAt((int) x, (int) z);
								
								t.teleport(Bukkit.getWorld("uhc_world").getSpawnLocation().add(x , y, z));
								
								for(int i = 0; i < FFAWinner.inventories.get(t.getUniqueId()).size(); i++)
								{
									if(FFAWinner.inventories.get(t.getUniqueId()).get(i) != null)
									{
										t.getInventory().addItem(FFAWinner.inventories.get(t.getUniqueId()).get(i));
									}
								}
								
								t.setGameMode(GameMode.SURVIVAL);
								spec.removeSpec(t);
								FFAScatter.ffa.add(t.getUniqueId());
								
								FFAWinner.inventories.remove(t.getUniqueId());
								
								if(ScenarioEvents.supheroes == true)
								{
									if(SuperHeros.potions.containsKey(t.getUniqueId()))	
									{
										if(SuperHeros.speed.containsKey(t.getUniqueId()))
										{
											t.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SuperHeros.speed.get(t.getUniqueId())), true);
										}
										else if(SuperHeros.jump.containsKey(t.getUniqueId()))
										{
											t.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SuperHeros.jump.get(t.getUniqueId())), true);
										}
										else
										{
											t.addPotionEffect(new PotionEffect(SuperHeros.potions.get(t.getUniqueId()), Integer.MAX_VALUE, 1), true);
										}
									}
								}
								
								t.sendMessage(prefix + ChatColor.GREEN + "You have been respawned. Good luck.");
							}
						}
					}
				}
				else
				{
					if(FFAWinner.deathlocation.get(t.getUniqueId()).getBlockX() >= -border.bordersize && FFAWinner.deathlocation.get(t.getUniqueId()).getBlockZ() >= -border.bordersize)
					{
						t.teleport(FFAWinner.deathlocation.get(t.getUniqueId()));
						
						for(int i = 0; i < FFAWinner.inventories.get(t.getUniqueId()).size(); i++)
						{
							if(FFAWinner.inventories.get(t.getUniqueId()).get(i) != null)
							{
								t.getInventory().addItem(FFAWinner.inventories.get(t.getUniqueId()).get(i));
							}
						}
						
						t.setGameMode(GameMode.SURVIVAL);
						spec.removeSpec(t);
						FFAScatter.ffa.add(t.getUniqueId());
						
						FFAWinner.inventories.remove(t.getUniqueId());
						
						if(ScenarioEvents.supheroes == true)
						{
							if(SuperHeros.potions.containsKey(t.getUniqueId()))	
							{
								if(SuperHeros.speed.containsKey(t.getUniqueId()))
								{
									t.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SuperHeros.speed.get(t.getUniqueId())), true);
								}
								else if(SuperHeros.jump.containsKey(t.getUniqueId()))
								{
									t.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SuperHeros.jump.get(t.getUniqueId())), true);
								}
								else
								{
									t.addPotionEffect(new PotionEffect(SuperHeros.potions.get(t.getUniqueId()), Integer.MAX_VALUE, 1), true);
								}
							}
						}
						
						t.sendMessage(prefix + ChatColor.GREEN + "You have been respawned. Good luck.");
					}
					else
					{
						if(FFAWinner.deathlocation.get(t.getUniqueId()).getBlockX() < -border.bordersize && FFAWinner.deathlocation.get(t.getUniqueId()).getBlockZ() < -border.bordersize)
						{
							double x = FFAWinner.deathlocation.get(t.getUniqueId()).getX() - (FFAWinner.deathlocation.get(t.getUniqueId()).getX() + border.bordersize);
							double z = FFAWinner.deathlocation.get(t.getUniqueId()).getZ() - (FFAWinner.deathlocation.get(t.getUniqueId()).getZ() + border.bordersize);
							double y = Bukkit.getWorld("uhc_world").getHighestBlockYAt((int) x, (int) z);
							
							t.teleport(Bukkit.getWorld("uhc_world").getSpawnLocation().add(x , y, z));
							
							for(int i = 0; i < FFAWinner.inventories.get(t.getUniqueId()).size(); i++)
							{
								if(FFAWinner.inventories.get(t.getUniqueId()).get(i) != null)
								{
									t.getInventory().addItem(FFAWinner.inventories.get(t.getUniqueId()).get(i));
								}
							}
							
							t.setGameMode(GameMode.SURVIVAL);
							spec.removeSpec(t);
							FFAScatter.ffa.add(t.getUniqueId());
							
							FFAWinner.inventories.remove(t.getUniqueId());
							
							if(ScenarioEvents.supheroes == true)
							{
								if(SuperHeros.potions.containsKey(t.getUniqueId()))	
								{
									if(SuperHeros.speed.containsKey(t.getUniqueId()))
									{
										t.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SuperHeros.speed.get(t.getUniqueId())), true);
									}
									else if(SuperHeros.jump.containsKey(t.getUniqueId()))
									{
										t.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SuperHeros.jump.get(t.getUniqueId())), true);
									}
									else
									{
										t.addPotionEffect(new PotionEffect(SuperHeros.potions.get(t.getUniqueId()), Integer.MAX_VALUE, 1), true);
									}
								}
							}
							
							t.sendMessage(prefix + ChatColor.GREEN + "You have been respawned. Good luck.");
						}
						else
						{
							if(FFAWinner.deathlocation.get(t.getUniqueId()).getBlockX() < -border.bordersize && !(FFAWinner.deathlocation.get(t.getUniqueId()).getBlockZ() < -border.bordersize))
							{
								double x = FFAWinner.deathlocation.get(t.getUniqueId()).getX() - (FFAWinner.deathlocation.get(t.getUniqueId()).getX() + border.bordersize);
								double z = FFAWinner.deathlocation.get(t.getUniqueId()).getZ();
								double y = Bukkit.getWorld("uhc_world").getHighestBlockYAt((int) x, (int) z);
								
								t.teleport(Bukkit.getWorld("uhc_world").getSpawnLocation().add(x , y, z));
								
								for(int i = 0; i < FFAWinner.inventories.get(t.getUniqueId()).size(); i++)
								{
									if(FFAWinner.inventories.get(t.getUniqueId()).get(i) != null)
									{
										t.getInventory().addItem(FFAWinner.inventories.get(t.getUniqueId()).get(i));
									}
								}
								
								t.setGameMode(GameMode.SURVIVAL);
								spec.removeSpec(t);
								FFAScatter.ffa.add(t.getUniqueId());
								
								FFAWinner.inventories.remove(t.getUniqueId());
								
								if(ScenarioEvents.supheroes == true)
								{
									if(SuperHeros.potions.containsKey(t.getUniqueId()))	
									{
										if(SuperHeros.speed.containsKey(t.getUniqueId()))
										{
											t.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SuperHeros.speed.get(t.getUniqueId())), true);
										}
										else if(SuperHeros.jump.containsKey(t.getUniqueId()))
										{
											t.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SuperHeros.jump.get(t.getUniqueId())), true);
										}
										else
										{
											t.addPotionEffect(new PotionEffect(SuperHeros.potions.get(t.getUniqueId()), Integer.MAX_VALUE, 0), true);
										}
									}
								}
								
								t.sendMessage(prefix + ChatColor.GREEN + "You have been respawned. Good luck.");
							}
							else if(!(FFAWinner.deathlocation.get(t.getUniqueId()).getBlockX() < -border.bordersize) && FFAWinner.deathlocation.get(t.getUniqueId()).getBlockZ() < -border.bordersize)
							{
								double x = FFAWinner.deathlocation.get(t.getUniqueId()).getX();
								double z = FFAWinner.deathlocation.get(t.getUniqueId()).getZ() - (FFAWinner.deathlocation.get(t.getUniqueId()).getZ() + border.bordersize);
								double y = Bukkit.getWorld("uhc_world").getHighestBlockYAt((int) x, (int) z);
								
								t.teleport(Bukkit.getWorld("uhc_world").getSpawnLocation().add(x , y, z));
								
								for(int i = 0; i < FFAWinner.inventories.get(t.getUniqueId()).size(); i++)
								{
									if(FFAWinner.inventories.get(t.getUniqueId()).get(i) != null)
									{
										t.getInventory().addItem(FFAWinner.inventories.get(t.getUniqueId()).get(i));
									}
								}
								
								t.setGameMode(GameMode.SURVIVAL);
								spec.removeSpec(t);
								FFAScatter.ffa.add(t.getUniqueId());
								
								FFAWinner.inventories.remove(t.getUniqueId());
								
								if(ScenarioEvents.supheroes == true)
								{
									if(SuperHeros.potions.containsKey(t.getUniqueId()))	
									{
										if(SuperHeros.speed.containsKey(t.getUniqueId()))
										{
											t.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SuperHeros.speed.get(t.getUniqueId())), true);
										}
										else if(SuperHeros.jump.containsKey(t.getUniqueId()))
										{
											t.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SuperHeros.jump.get(t.getUniqueId())), true);
										}
										else
										{
											t.addPotionEffect(new PotionEffect(SuperHeros.potions.get(t.getUniqueId()), Integer.MAX_VALUE, 1), true);
										}
									}
								}
								
								t.sendMessage(prefix + ChatColor.GREEN + "You have been respawned. Good luck.");
							}
						}
					}
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "You cannot respawn that player.");
			}
		}
		else
		{
			
		}
	}
}