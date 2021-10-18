package me.fairuhc.UHC.Scatter;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import Events.Timer;
import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Host;
import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.Mod;
import me.fairuhc.UHC.border;
import me.fairuhc.UHC.DeathManager.WinnerCheck;
import me.fairuhc.UHC.World.ChunkLoad;
import me.fairuhc.UHC.scenarios.ScenarioEvents;
import me.fairuhc.UHC.scenarios.SuperHeros;
import net.md_5.bungee.api.ChatColor;

public class FFAScatter implements Listener
{
	public static boolean scatter = false;
	public static ArrayList<UUID> joinedgame = new ArrayList<>();
	public static ArrayList<UUID> ffa = new ArrayList<>();
	public static ArrayList<UUID> scattered = new ArrayList<>();
	public static ArrayList<Material> blocks = new ArrayList<>();
	public Timer t;
	public Main plugin;
	public static int FFAScatCount = 0;
	public border bord;
	public WinnerCheck win;
	private String prefix = ChatColor.WHITE + "" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "" + ChatColor.GRAY + " » ";
	
	public FFAScatter(Main plugin)
	{
		this.plugin = plugin;
		t = new Timer(plugin);
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
		if(Timer.gametime <= (Gamemanager.latescattertime * 60))
		{
			joinedgame.add(p.getUniqueId());
			ffa.add(p.getUniqueId());
			Latescatter(p.getUniqueId());
			
			if(ScenarioEvents.supheroes == true)
			{
				SuperHeros sup = new SuperHeros();
				
				sup.superheroes(p);
			}
			
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
			if(!Host.host.contains(p.getUniqueId()) && !Mod.mod.contains(p.getUniqueId()))
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
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(!Host.host.contains(p.getUniqueId()) && !Mod.mod.contains(p.getUniqueId()))
			{
				joinedgame.add(p.getUniqueId());
				ffa.add(p.getUniqueId());
			}
		}
		
		new BukkitRunnable()
		{
			private int delay = 5;
			private int seconddelay = ffa.size() - 1;
			private int thirddelay = 30;
			private int tpsdelay = 10;
			
			public void run()
			{
				if(delay == 5)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The scatter is starting. This may take a few minutes.");
				}
				
				if(delay <= 0 && !(seconddelay < 0))
				{
					Location loc = scatter(Bukkit.getPlayer(ffa.get(seconddelay)).getUniqueId());
					loc.getWorld().loadChunk(loc.getChunk().getX() , loc.getChunk().getZ());
					
					if(loc.getChunk().isLoaded())
					{
						Bukkit.getPlayer(ffa.get(seconddelay)).teleport(loc);
					}
					else
					{
						loc.getChunk().load(true);
						Bukkit.getPlayer(ffa.get(seconddelay)).teleport(loc);
					}
				
					seconddelay--;
				}
				
				if(scattered.size() == ffa.size() && seconddelay < 0)
				{
					if(tpsdelay <= 0)
					{
						if(thirddelay == 30)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Scatter period has ended. The game will now start in 30 seconds!");
						}
						else if(thirddelay == 20)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 20 seconds!");
						}
						else if(thirddelay == 10)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 10 seconds!");
						}
						else if(thirddelay == 5)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 5 seconds!");
						}
						else if(thirddelay == 4)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 4 seconds!");
						}
						else if(thirddelay == 3)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 3 seconds!");
						}
						else if(thirddelay == 2)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 2 seconds!");
						}
						else if(thirddelay == 1)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The game will start in 1 second!");
						}
						else if(thirddelay == 0)
						{
							Bukkit.broadcastMessage(prefix + ChatColor.GREEN + " The game has started! Good luck and have fun.");
							
							if(ScenarioEvents.supheroes == true)
							{
								SuperHeros sup = new SuperHeros();
								
								for(int i = 0; i < ffa.size(); i++)
								{
									sup.superheroes(Bukkit.getPlayer(ffa.get(i)));
								}
							}
							
							Gamemanager.started = true;
							scatter = false;
							win.checkWinner();
							t.GameTime();
							removePotions();
							cancel();
							return;
						}
						
						thirddelay--;
					}
					
					tpsdelay--;
				}
				
				delay--;
			}
			
		}.runTaskTimer(plugin, 0, 20);
	}
	
	public Location scatter(UUID id)
	{
		Player p = Bukkit.getPlayer(id);
		
		Random rand = new Random();
		
		int x = rand.nextInt(border.bordersize + border.bordersize ) - border.bordersize;
		int z = rand.nextInt(border.bordersize + border.bordersize ) - border.bordersize;
		int y = Bukkit.getWorld("uhc_world").getHighestBlockYAt(x,z);

		Location loc = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y + 6, z);
		Location test = Bukkit.getWorld("uhc_world").getSpawnLocation().add(x, y - 10, z); 
		
		if(!blocks.contains(test.getBlock().getType()))
		{
			scatter(id);
		}
		else
		{
			ChunkLoad.loadedChunks.add(loc.getChunk());
			scattered.add(p.getUniqueId());
		}
		
		return loc;
	}
	
	public void setPotions()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(!Host.host.contains(p.getUniqueId()) && !Mod.mod.contains(p.getUniqueId()))
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
			if(!Host.host.contains(p.getUniqueId()) && !Mod.mod.contains(p.getUniqueId()))
			{
				p.setVelocity(new Vector().zero());
				p.removePotionEffect(PotionEffectType.BLINDNESS);
				p.removePotionEffect(PotionEffectType.SLOW);
				p.removePotionEffect(PotionEffectType.JUMP);
			}
		}
	}
}
