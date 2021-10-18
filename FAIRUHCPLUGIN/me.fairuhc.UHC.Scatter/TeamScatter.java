package me.fairuhc.UHC.Scatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

import Events.Sidebar;
import Events.Timer;
import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Host;
import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.Mod;
import me.fairuhc.UHC.border;
import me.fairuhc.UHC.DeathManager.WinnerCheck;
import me.fairuhc.UHC.World.ChunkLoad;
import me.fairuhc.UHC.teams.CreateTeam;
import me.fairuhc.UHC.teams.TeamManager;
import net.md_5.bungee.api.ChatColor;

public class TeamScatter implements Listener
{
	public Main plugin;
	public static boolean scatter = false;
	public static ArrayList<Material> blocks = new ArrayList<>();
	public static ArrayList<UUID> scattered = new ArrayList<>();
	public static HashMap<UUID, ArrayList<UUID>> teams = new HashMap<UUID, ArrayList<UUID>>();
	public static int TeamScatCount = 0;
	public border bord;
	public static TeamManager tm;
	public CreateTeam ct;
	public Timer t;
	public WinnerCheck win;
	private String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
	
	public TeamScatter(Main plugin)
	{
		this.plugin = plugin;
		
		t = new Timer(plugin);
		ct = new CreateTeam(plugin);
		tm = new TeamManager(plugin);
		bord = new border(plugin);
		win = new WinnerCheck();

		blocks.add(Material.SAND);
		blocks.add(Material.SANDSTONE);
		blocks.add(Material.GRASS);
		blocks.add(Material.LONG_GRASS);
		blocks.add(Material.LEAVES);
		blocks.add(Material.LEAVES_2);
		blocks.add(Material.GRAVEL);
		blocks.add(Material.STONE);
	}
	
	public void latescatter(Player p)
	{
		ArrayList<UUID> teammembers = new ArrayList<UUID>();
		
		if(Timer.gametime <= (Gamemanager.latescattertime * 60))
		{
			ct.latescatterteam(p, teammembers);
			teams.put(p.getUniqueId(), teammembers);
			Latescatter(p.getUniqueId());
			
			for(int i = 0; i < 40; i++)
			{
				p.getInventory().clear(i);
			}
			
			p.sendMessage(ChatColor.GREEN + "You have been late scattered. Good luck!");
		}
	}
	
	public void Latescatter(UUID id)
	{
		Player p = Bukkit.getPlayer(id);
		
		Random rand = new Random();
			
		int x = rand.nextInt(border.bordersize);
		int z = rand.nextInt(border.bordersize);
		int y = Bukkit.getWorld("uhc_world").getHighestBlockYAt(x,z);

		Location loc = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y + 6, z);
			
		Location test = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y - 7, z); 
		Location test1 = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y - 6, z); 
			
		if(!blocks.contains(test.getBlock().getType()) && !blocks.contains(test1.getBlock().getType()))
		{
			Latescatter(id);
		}
		else
		{
			loc.getWorld().loadChunk(loc.getBlockX(), loc.getBlockZ());
			p.teleport(loc);
			loc.getWorld().loadChunk(loc.getBlockX(), loc.getBlockZ());
		}
	}
	
	public void bord()
	{
		scatter = true;
		setPotions();
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(!Host.host.contains(p.getUniqueId()) || !Mod.mod.contains(p.getUniqueId()))
			{
				for(int i = 0; i < 40; i++)
				{
					p.getInventory().clear(i);
				}
				
				p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 20));
			}
		}
		
		bord.beforecreateBorder();
		
		if(border.bordercreated == true)
		{
			new BukkitRunnable()
			{
				int delay = 6;
				
				public void run()
				{
					if(delay == 0)
					{
						scat();
					}
					
					delay--;
				}
				
			}.runTaskTimer(plugin, 0, 20);
		}
	}
	
	public void scat()
	{
		new BukkitRunnable()
		{
			private int delay = 5;
			private int seconddelay = 31;
			
			public void run()
			{
				if(delay == 5)
				{
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.AQUA + " The scatter is starting. This may take a few moments.\n "
								+ "If you have any questions make sure to use /helpop.");
					}
				}
				else if(delay == 0)
				{
					for(Player p : Bukkit.getOnlinePlayers())
					{
						if(p.isOnline() && (!Host.host.contains(p.getUniqueId()) || !Mod.mod.contains(p.getUniqueId())) && CreateTeam.playerTeams.containsKey(p.getUniqueId()))
						{
							teams.put(p.getUniqueId(), CreateTeam.playerTeams.get(p.getUniqueId()));
							
							p.teleport(scatter(p.getUniqueId()));
							
							if(CreateTeam.playerTeams.get(p.getUniqueId()).size() >= 2)
							{
								for(int i = 0; i < CreateTeam.playerTeams.get(p.getUniqueId()).size(); i++)
								{
									Bukkit.getPlayer(teams.get(p.getUniqueId()).get(i)).teleport(p.getLocation());
									System.out.print("gets here");
								}
							}
						}
						else if(p.isOnline() && !tm.hasTeam(p) && (!Host.host.contains(p.getUniqueId()) || !Mod.mod.contains(p.getUniqueId())))
						{
							ArrayList<UUID> teammembers = new ArrayList<UUID>();
							ct.createTeam(p, teammembers);
							teams.put(p.getUniqueId(), teammembers);
							p.teleport(scatter(p.getUniqueId()));
						}
					}
				}
				else if(delay < 0)
				{
					if(scattered.size() == teams.size())
					{
						if(seconddelay == 30)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 30 seconds!");
						}
						else if(seconddelay == 20)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 20 seconds!");
						}
						else if(seconddelay == 10)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 10 seconds!");
						}
						else if(seconddelay == 5)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 5 seconds!");
						}
						else if(seconddelay == 4)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 4 seconds!");
						}
						else if(seconddelay == 3)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 3 seconds!");
						}
						else if(seconddelay == 2)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 2 seconds!");
						}
						else if(seconddelay == 1)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 1 seconds!");
						}
						else if(seconddelay == 0)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.GREEN + " The game has started! Good luck and have fun!");
							
							Gamemanager.started = true;
							scatter = false;
							removePotions();
							t.GameTime();
							win.checkWinner();
							
							cancel();
							return;
						}
					}
					
					seconddelay--;
				}
				
				delay--;
			}
			
		}.runTaskTimer(plugin, 0, 20);
	}
	
	public static Location scatter(UUID id)
	{
		Player p = Bukkit.getPlayer(id);
		
		Random rand = new Random();
		
		int x = rand.nextInt(border.bordersize);
		int z = rand.nextInt(border.bordersize);
		int y = Bukkit.getWorld("uhc_world").getHighestBlockYAt(x,z);

		Location loc = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y + 6, z);
		
		Location test = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y - 10, z); 
		Location test1 = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y - 8, z); 
		Location test2 = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y - 6, z); 
		Location test3 = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y - 4, z); 
		Location test4 = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y - 2, z); 
		Location test5 = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y, z);
		Location test6 = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y + 2, z); 
		Location test7 = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y + 4, z); 
		
		if(!blocks.contains(test.getBlock().getType()) && !blocks.contains(test1.getBlock().getType()) && !blocks.contains(test2.getBlock().getType()) && !blocks.contains(test3.getBlock().getType()) && !blocks.contains(test4.getBlock().getType()) && !blocks.contains(test5.getBlock().getType()) && !blocks.contains(test6.getBlock().getType()) && !blocks.contains(test7.getBlock().getType()))
		{
			scatter(id);
		}
		else
		{
			loc.getWorld().loadChunk(loc.getBlockX(), loc.getBlockZ());
			loc.getWorld().loadChunk(loc.getBlockX(), loc.getBlockZ());
			
			loc.getChunk().load();
			
			ChunkLoad.loadedChunks.add(loc.getChunk());
			scattered.add(p.getUniqueId());
		}
		
		return loc;
	}
	
	public static boolean isLocationSafe(Location location)
	{
		return true;
	}
	
	public void setPotions()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(!Host.host.contains(p.getUniqueId()) || !Mod.mod.contains(p.getUniqueId()))
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 200), true);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 200), true);
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 200));
			}
		}
	}
	
	public void removePotions()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(!Host.host.contains(p.getUniqueId()) || !Mod.mod.contains(p.getUniqueId()))
			{
				p.setVelocity(new Vector().zero());
				p.removePotionEffect(PotionEffectType.BLINDNESS);
				p.removePotionEffect(PotionEffectType.SLOW);
				p.removePotionEffect(PotionEffectType.JUMP);
			}
		}
	}
}
