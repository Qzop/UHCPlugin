package core.HostsMods;

import core.Arena.PracticeArena;
import core.Scatter.Scatter;
import core.mainPackage.Commands;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.material.Dye;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class HostModsItems implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	public static ArrayList<UUID> vanished = new ArrayList<UUID>();

	public void hostmodItemsInGame(Player p)
	{
		new BukkitRunnable()
		{
			public void run()
			{
				p.getInventory().clear();
				p.getInventory().setBoots(null);
				p.getInventory().setLeggings(null);
				p.getInventory().setChestplate(null);
				p.getInventory().setHelmet(null);

				ItemStack compass = new ItemStack(Material.COMPASS, 1, (byte) 0);
				ItemMeta compassMeta = compass.getItemMeta();
				compassMeta.setDisplayName(ChatColor.YELLOW + "Pass through/Teleport");
				compass.setItemMeta(compassMeta);

				ItemStack feather = new ItemStack(Material.FEATHER, 1, (byte) 0);
				ItemMeta featherMeta = feather.getItemMeta();
				featherMeta.setDisplayName(ChatColor.YELLOW + "Random Teleport to Player");
				feather.setItemMeta(featherMeta);

				ItemStack emerald = new ItemStack(Material.EMERALD, 1, (byte) 0);
				ItemMeta emeraldMeta = emerald.getItemMeta();
				emeraldMeta.setDisplayName(ChatColor.YELLOW + "Teleport to 0,0");
				emerald.setItemMeta(emeraldMeta);

				ItemStack chest = new ItemStack(Material.CHEST, 1, (byte) 0);
				ItemMeta chestMeta = chest.getItemMeta();
				chestMeta.setDisplayName(ChatColor.YELLOW + "Player List");
				chest.setItemMeta(chestMeta);

				ItemStack redstone = new ItemStack(Material.REDSTONE, 1, (byte) 0);
				ItemMeta redstoneMeta = redstone.getItemMeta();
				redstoneMeta.setDisplayName(ChatColor.YELLOW + "Vanish/Unvanish");
				redstone.setItemMeta(redstoneMeta);

				Dye dye = new Dye();
				dye.setColor(DyeColor.GREEN);
				ItemStack i = dye.toItemStack();
				ItemMeta meta = i.getItemMeta();
				meta.setDisplayName(ChatColor.YELLOW + "Right Click to Unvanish");
				i.setItemMeta(meta);

				p.getInventory().setItem(0, compass);
				p.getInventory().setItem(1, feather);
				p.getInventory().setItem(4, emerald);
				p.getInventory().setItem(7, chest);
				p.getInventory().setItem(8, i);

				cancel();
			}

		}.runTaskTimer(plugin, 0, 1);
	}

	public void staffLobbyItems(Player p)
	{
		new BukkitRunnable()
		{
			public void run()
			{
				p.getInventory().clear();
				p.getInventory().setBoots(null);
				p.getInventory().setLeggings(null);
				p.getInventory().setChestplate(null);
				p.getInventory().setHelmet(null);

				ItemStack clock = new ItemStack(Material.WATCH, 1, (byte) 0);
				ItemMeta clockMeta = clock.getItemMeta();
				clockMeta.setDisplayName(ChatColor.YELLOW + "UHC Configuration");
				clock.setItemMeta(clockMeta);

				ItemStack arena = new ItemStack(Material.DIAMOND_SWORD);
				ItemMeta arenameta = arena.getItemMeta();
				arenameta.setDisplayName(org.bukkit.ChatColor.YELLOW + "Practice Arena");
				arena.setItemMeta(arenameta);

				ItemStack mod = new ItemStack(Material.ENDER_PEARL);
				ItemMeta modmeta = mod.getItemMeta();
				modmeta.setDisplayName(ChatColor.YELLOW + "UHC Mod");
				mod.setItemMeta(modmeta);

				ItemStack host = new ItemStack(Material.ENDER_CHEST);
				ItemMeta hostmeta = host.getItemMeta();
				hostmeta.setDisplayName(ChatColor.YELLOW + "UHC Host");
				host.setItemMeta(hostmeta);

				p.getInventory().setItem(0, arena);
				p.getInventory().setItem(3, mod);
				p.getInventory().setItem(5, host);
				p.getInventory().setItem(8, clock);

				cancel();
			}

		}.runTaskTimer(plugin, 0, 1);
	}
	
	@EventHandler
	public void itemDrop(PlayerDropItemEvent e)
	{
		Player p = e.getPlayer();

		Dye dye1 = new Dye();
		dye1.setColor(DyeColor.GREEN);
		ItemStack i1 = dye1.toItemStack();
		ItemMeta meta1 = i1.getItemMeta();
		meta1.setDisplayName(ChatColor.YELLOW + "Right Click to Unvanish");
		i1.setItemMeta(meta1);

		Dye dye2 = new Dye();
		dye2.setColor(DyeColor.GREEN);
		ItemStack i2 = dye2.toItemStack();
		ItemMeta meta2 = i2.getItemMeta();
		meta2.setDisplayName(ChatColor.YELLOW + "Right Click to Vanish");
		i2.setItemMeta(meta2);

		if(e.getItemDrop().getItemStack().getType() == Material.DIAMOND_SWORD || e.getItemDrop().getItemStack().getType() == Material.CHEST || e.getItemDrop().getItemStack().getType() == Material.DIAMOND
		|| e.getItemDrop().getItemStack().getType() == Material.COMPASS || e.getItemDrop().getItemStack().getType() == Material.FEATHER || e.getItemDrop().getItemStack().getType() == Material.EMERALD
		|| e.getItemDrop().getItemStack().getType() == Material.CHEST || e.getItemDrop().getItemStack().getType() == Material.WATCH || e.getItemDrop().getItemStack().getType() == i2.getType() || e.getItemDrop().getItemStack().getType() == i1.getType()
		|| e.getItemDrop().getItemStack().getType() == Material.ENDER_PEARL || e.getItemDrop().getItemStack().getType() == Material.ENDER_CHEST)
		{
			if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName() == null)
			{
				e.setCancelled(false);
			}
			else
			{
				if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(org.bukkit.ChatColor.YELLOW + "Practice Arena"))
				{
					e.setCancelled(true);
				}
				else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(org.bukkit.ChatColor.YELLOW + "Config"))
				{
					e.setCancelled(true);
				}
				else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(org.bukkit.ChatColor.YELLOW + "Scenarios"))
				{
					e.setCancelled(true);
				}
				if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Pass through/Teleport"))
				{
					e.setCancelled(true);
				}
				else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Random Teleport to Player"))
				{
					e.setCancelled(true);
				}
				else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Teleport to 0,0"))
				{
					e.setCancelled(true);
				}
				else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Player List"))
				{
					e.setCancelled(true);
				}
				else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Right Click to Unvanish"))
				{
					e.setCancelled(true);
				}
				else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Right Click to Vanish"))
				{
					e.setCancelled(true);
				}
				else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "UHC Configuration"))
				{
					e.setCancelled(true);
				}
				else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "UHC Mod"))
				{
					e.setCancelled(true);
				}
				else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "UHC Host"))
				{
					e.setCancelled(true);
				}
				else if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Pass through/Teleport"))
				{
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onUse(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();

		Dye dye1 = new Dye();
		dye1.setColor(DyeColor.GREEN);
		ItemStack i1 = dye1.toItemStack();
		ItemMeta meta1 = i1.getItemMeta();
		meta1.setDisplayName(ChatColor.YELLOW + "Right Click to Unvanish");
		i1.setItemMeta(meta1);

		Dye dye2 = new Dye();
		dye2.setColor(DyeColor.GREEN);
		ItemStack i2 = dye2.toItemStack();
		ItemMeta meta2 = i2.getItemMeta();
		meta2.setDisplayName(ChatColor.YELLOW + "Right Click to Vanish");
		i2.setItemMeta(meta2);

		if(p.getItemInHand() != null)
		{
			if (e.getAction() != null && (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
			{
				if(p.getItemInHand().getItemMeta() == null)
				{
					e.setCancelled(false);
				}
				else
				{
					if(p.getItemInHand().getItemMeta().getDisplayName() == null)
					{
						e.setCancelled(false);
					}
					else
					{
						if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Random Teleport to Player") && p.getItemInHand().getType() == Material.FEATHER)
						{
							if (Scatter.started)
							{
								int random = new Random().nextInt(Scatter.allPlayers.size());
								Player target = Bukkit.getPlayer(Scatter.allPlayers.get(random));

								while(target == null)
								{
									random = new Random().nextInt(Scatter.allPlayers.size());
									target = Bukkit.getPlayer(Scatter.allPlayers.get(random));
								}

								p.teleport(Bukkit.getPlayer(Scatter.allPlayers.get(random)));
								p.sendMessage(ChatColor.GREEN + "Teleported to " + Bukkit.getPlayer(Scatter.allPlayers.get(random)).getDisplayName() + ".");
							}
							else
							{
								p.sendMessage(ChatColor.RED + "You may not use this now!");
							}
						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Right Click to Unvanish") && p.getItemInHand().getType() == i1.getType())
						{
							for (Player players : Main.online.getOnlinePlayers())
							{
								players.showPlayer(p);
							}

							vanished.remove(p.getUniqueId());

							Dye dye = new Dye();
							dye.setColor(DyeColor.RED);
							ItemStack i = dye.toItemStack();
							ItemMeta meta = i.getItemMeta();
							meta.setDisplayName(ChatColor.YELLOW + "Right Click to Vanish");
							i.setItemMeta(meta);

							new BukkitRunnable()
							{
								public void run()
								{
									p.getInventory().setItem(8, i);
									cancel();
								}

							}.runTaskTimer(plugin, 0, 1);

							p.sendMessage(ChatColor.GREEN + "You are now Unvanished!");
						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Right Click to Vanish") && p.getItemInHand().getType() == i2.getType())
						{
							for (Player players : Main.online.getOnlinePlayers())
							{
								players.hidePlayer(p);
							}

							Dye dye = new Dye();
							dye.setColor(DyeColor.GREEN);
							ItemStack i = dye.toItemStack();
							ItemMeta meta = i.getItemMeta();
							meta.setDisplayName(ChatColor.YELLOW + "Right Click to Unvanish");
							i.setItemMeta(meta);

							vanished.add(p.getUniqueId());

							new BukkitRunnable()
							{
								public void run()
								{
									p.getInventory().setItem(8, i);
									cancel();
								}

							}.runTaskTimer(plugin, 0, 1);

							p.sendMessage(ChatColor.GREEN + "You are now Vanished!");
						}
						else if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Teleport to 0,0") && p.getItemInHand().getType() == Material.EMERALD)
						{
							Location loc = new Location(Bukkit.getWorld("uhc_world"), 0, 100, 0);
							p.teleport(loc);
						}
						else if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Player List") && p.getItemInHand().getType() == Material.CHEST)
						{
							if (Scatter.started)
							{
								PlayerList(p);
							}
							else
							{
								p.sendMessage(ChatColor.RED + "You may not use this now!");
							}
						}
						else if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "UHC Configuration") && p.getItemInHand().getType() == Material.WATCH)
						{
							uhcConfiguration(p);
						}
						else if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Practice Arena") && p.getItemInHand().getType() == Material.DIAMOND_SWORD)
						{
							p.performCommand("arena");
						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Config") && p.getItemInHand().getType() == Material.CHEST)
						{
							p.performCommand("config");
						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Scenarios") && p.getItemInHand().getType() == Material.DIAMOND)
						{
							p.performCommand("scenarios");
						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "UHC Mod") && p.getItemInHand().getType() == Material.ENDER_PEARL)
						{
							p.performCommand("mod " + p.getDisplayName());
						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "UHC Host") && p.getItemInHand().getType() == Material.ENDER_CHEST)
						{
							p.performCommand("host " + p.getDisplayName());
						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Pass through/Teleport") && p.getItemInHand().getType() == Material.COMPASS)
						{
							e.setCancelled(true);
						}
					}
				}
			}
			else if(e.getAction() != null && (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)))
			{
				if(p.getItemInHand().getItemMeta() == null)
				{
					e.setCancelled(false);
				}
				else
				{
					if(p.getItemInHand().getItemMeta().getDisplayName() == null)
					{
						e.setCancelled(false);
					}
					else
					{
						if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Right Click to Unvanish") && p.getItemInHand().getType() == i1.getType())
						{
							e.setCancelled(true);
						}
						else if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Right Click to Vanish") && p.getItemInHand().getType() == i2.getType())
						{
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		Inventory inv = e.getInventory();
		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();

		Dye dye1 = new Dye();
		dye1.setColor(DyeColor.GREEN);
		ItemStack i1 = dye1.toItemStack();
		ItemMeta meta1 = i1.getItemMeta();
		meta1.setDisplayName(ChatColor.YELLOW + "Right Click to Unvanish");
		i1.setItemMeta(meta1);

		Dye dye2 = new Dye();
		dye2.setColor(DyeColor.GREEN);
		ItemStack i2 = dye2.toItemStack();
		ItemMeta meta2 = i2.getItemMeta();
		meta2.setDisplayName(ChatColor.YELLOW + "Right Click to Vanish");
		i2.setItemMeta(meta2);

		if(item != null && item.hasItemMeta() && item.getItemMeta().getDisplayName() != null)
		{
			if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Random Teleport to Player") && item.getType() == Material.FEATHER)
			{
				e.setCancelled(true);
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Right Click to Unvanish") && item.getType() == i1.getType())
			{
				e.setCancelled(true);
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Right Click to Vanish") && item.getType() == i2.getType())
			{
				e.setCancelled(true);
			}
			else if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Teleport to 0,0") && item.getType() == Material.EMERALD)
			{
				e.setCancelled(true);
			}
			else if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Player List") && item.getType() == Material.CHEST)
			{
				e.setCancelled(true);
			}
			else if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "UHC Configuration") && item.getType() == Material.WATCH)
			{
				e.setCancelled(true);
			}
			else if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Practice Arena") && item.getType() == Material.DIAMOND_SWORD)
			{
				e.setCancelled(true);
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Config") && item.getType() == Material.CHEST)
			{
				e.setCancelled(true);
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Scenarios") && item.getType() == Material.DIAMOND)
			{
				e.setCancelled(true);
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "UHC Mod") && item.getType() == Material.ENDER_PEARL)
			{
				e.setCancelled(true);
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "UHC Host") && item.getType() == Material.ENDER_CHEST)
			{
				e.setCancelled(true);
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Pass through/Teleport") && item.getType() == Material.COMPASS)
			{
				e.setCancelled(true);
			}
		}
		else
		{
			e.setCancelled(false);
		}
	}

	public void PlayerList(Player p)
	{

	}

	public void uhcConfiguration(Player p)
	{
		Inventory inv = Bukkit.getServer().createInventory(null, 27, ChatColor.YELLOW + "UHC Configuration");

		new BukkitRunnable()
		{
			public void run()
			{
				ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE);
				ItemMeta fmeta = filler.getItemMeta();
				fmeta.setDisplayName(" ");
				filler.setItemMeta(fmeta);

				ItemStack config = new ItemStack(Material.CHEST);
				ItemMeta configmeta = config.getItemMeta();
				configmeta.setDisplayName(ChatColor.YELLOW + "Change Config");
				config.setItemMeta(configmeta);

				ItemStack scenarios = new ItemStack(Material.ENDER_CHEST);
				ItemMeta scenariosmeta = scenarios.getItemMeta();
				scenariosmeta.setDisplayName(ChatColor.YELLOW + "Change Scenarios");
				scenarios.setItemMeta(scenariosmeta);

				ItemStack arena = new ItemStack(Material.DIAMOND_SWORD);
				ItemMeta arenameta = arena.getItemMeta();

				if(PracticeArena.arena)
				{
					arenameta.setDisplayName(ChatColor.YELLOW + "Click to turn practice arena: " + ChatColor.RED + "Off");
				}
				else
				{
					arenameta.setDisplayName(ChatColor.YELLOW + "Click to turn practice arena: " + ChatColor.GREEN + "On");
				}

				arena.setItemMeta(arenameta);

				inv.setItem(0, filler);
				inv.setItem(1, filler);
				inv.setItem(2, filler);
				inv.setItem(3, filler);
				inv.setItem(4, filler);
				inv.setItem(5, filler);
				inv.setItem(6, filler);
				inv.setItem(7, filler);
				inv.setItem(8, filler);
				inv.setItem(9, filler);

				inv.setItem(12, config);
				inv.setItem(13, arena);
				inv.setItem(14, scenarios);

				inv.setItem(17, filler);
				inv.setItem(18, filler);
				inv.setItem(19, filler);
				inv.setItem(20, filler);
				inv.setItem(21, filler);
				inv.setItem(22, filler);
				inv.setItem(23, filler);
				inv.setItem(24, filler);
				inv.setItem(25, filler);
				inv.setItem(26, filler);
			}

		}.runTaskTimer(plugin, 0, 1);

		p.openInventory(inv);
	}
}
