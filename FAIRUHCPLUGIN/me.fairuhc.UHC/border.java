package me.fairuhc.UHC;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import Events.Timer;
import net.md_5.bungee.api.ChatColor;

public class border implements Listener
{
	public Main plugin;
	private World world = Bukkit.getWorld("uhc_world");
	public static int bordersize = 1500;
	public static int startingbordersize = 0;
	public static int bordershrink = 25;
	public static int bordershrinkinterval = 5;
	public static int eachshrink = 0;
	public static boolean bordercreated = false;
	public static ArrayList<Block> wall1 = new ArrayList<Block>();
	public static ArrayList<Block> wall2 = new ArrayList<Block>();
	public static ArrayList<Block> wall3 = new ArrayList<Block>();
	public static ArrayList<Block> wall4 = new ArrayList<Block>();
	
	public border(Main plugin)
	{
		this.plugin = plugin;
	}
	
	public void beforecreateBorder()
	{
		int x = bordersize + 1;
		int z = bordersize + 1;
		int nx = -x;
		int nz = -z;
		
		World world = Bukkit.getWorld("uhc_world");
		int radius = bordersize;
		
		Location loc = new Location(world, 0, world.getHighestBlockYAt(0,0), 0);
		
		
		// For all Z values where X is the bordersize + 1
		
		while(z != nz)
		{
			int y = world.getHighestBlockYAt(x, z) + 6;
			
			for(y = world.getHighestBlockYAt(x, z) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(x, y, z).getChunk().load();
				Block block = loc.getWorld().getBlockAt(x, y, z);
				wall1.add(block);
			}
			
			z--;
		}
		
		z = bordersize + 1;
		
		
		//For all X values where Z is nz
		
		while(x != nx)
		{
			int y = world.getHighestBlockYAt(x, nz) + 6;
			
			for(y = world.getHighestBlockYAt(x, nz) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(x, y, nz).getChunk().load();
				Block block = loc.getWorld().getBlockAt(x, y, nz);
				wall2.add(block);
			}
			
			x--;
		}
		
		x = bordersize + 1;
		
		//For all Z values where X is nx
		
		while(nz != z)
		{
			int y = world.getHighestBlockYAt(nx, nz) + 6;
			
			for(y = world.getHighestBlockYAt(nx, nz) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(nx, y, nz).getChunk().load();
				Block block = loc.getWorld().getBlockAt(nx, y, nz);
				wall3.add(block);
			}
			
			nz++;
		}
		
		while(nx != x)
		{
			int y = world.getHighestBlockYAt(nx, z) + 6;
			
			for(y = world.getHighestBlockYAt(nx, z) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(nx, y, z).getChunk().load();
				Block block = loc.getWorld().getBlockAt(nx, y, z);
				wall4.add(block);
			}
			
			nx++;
		}
		
		createBorder();
	}
	
	public void createBorder()
	{
		new BukkitRunnable()
		{
			int delay = 3;
			
			public void run()
			{
				if(delay == 3)
				{
					for(int i = 0; i < wall1.size(); i++)
					{
						wall1.get(i).setType(Material.BEDROCK);
					}
						
					delay--;
				}
				else if(delay == 2)
				{
					for(int i = 0; i < wall2.size(); i++)
					{
						wall2.get(i).setType(Material.BEDROCK);
					}
					
					delay--;
				}
				else if(delay == 1)
				{
					for(int i = 0; i < wall3.size(); i++)
					{
						wall3.get(i).setType(Material.BEDROCK);
					}
					
					delay--;
				}
				else if(delay == 0)
				{
					for(int i = 0; i < wall4.size(); i++)
					{
						wall4.get(i).setType(Material.BEDROCK);
					}
					
					wall1.clear();
					wall2.clear();
					wall3.clear();
					wall4.clear();
					
					cancel();
					return;
				}
			}
			
		}.runTaskTimer(plugin, 0, 5);
		
		bordercreated = true;
	}
	
	public void bordershrinkCheck()
	{
		String prefix = ChatColor.DARK_AQUA + "Border" + ChatColor.GRAY + " » ";
		
		if(Gamemanager.ended == false)
		{
			if(bordersize > 500 && Timer.gametime <= (bordershrink * 60))
			{
				if(Timer.gametime == (bordershrink * 60) - 300)
				{
					beforeBorderGreaterThan500();
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 5 minutes!");
				}
				
				if(Timer.gametime == (bordershrink * 60) - 120)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 2 minutes!");
				}
				
				if(Timer.gametime == (bordershrink * 60) - 60)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 1 minute!");
				}
				
				if(Timer.gametime == (bordershrink * 60) - 30)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 30 seconds!");
				}
				
				if(Timer.gametime == (bordershrink * 60) - 15)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 15 seconds!");
				}
				
				if(Timer.gametime == (bordershrink * 60) - 10)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 10 seconds!");
				}
				
				if(Timer.gametime == (bordershrink * 60) - 5)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 5 seconds!");
				}
				
				if(Timer.gametime == (bordershrink * 60) - 4)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 4 seconds!");
				}
				
				if(Timer.gametime == (bordershrink * 60) - 3)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 3 seconds!");
				}
				
				if(Timer.gametime == (bordershrink * 60) - 2)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 2 seconds!");
				}
				
				if(Timer.gametime == (bordershrink * 60) - 1)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 1 second!");
				}
				
				if(Timer.gametime == (bordershrink * 60))
				{
					eachshrink = Timer.gametime;
					borderShrinkGreaterThan500();
				}
			}
			else if(bordersize > 500 && Timer.gametime > (bordershrink * 60))
			{
				if(Timer.gametime == eachshrink + 180)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 2 minutes!");
					beforeBorderGreaterThan500();
				}
				
				if(Timer.gametime == eachshrink + 240)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 1 minute!");
				}
				
				if(Timer.gametime == eachshrink + 270)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 30 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 285)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 15 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 290)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 10 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 295)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 5 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 296)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 4 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 297)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 3 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 298)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 2 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 299)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 500) + " in 1 second!");
				}
				
				if(Timer.gametime == eachshrink + 300)
				{
					eachshrink = Timer.gametime;
					borderShrinkGreaterThan500();
				}
			}
			else if(bordersize == 500)
			{
				if(Timer.gametime == eachshrink + 180)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 400) + " in 2 minutes!");
					beforeBorderAt500();
				}
				
				if(Timer.gametime == eachshrink + 240)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 400) + " in 1 minute!");
				}
				
				if(Timer.gametime == eachshrink + 270)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 400) + " in 30 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 285)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 400) + " in 15 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 290)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 400) + " in 10 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 295)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 400) + " in 5 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 296)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 400) + " in 4 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 297)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 400) + " in 3 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 298)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 400) + " in 2 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 299)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 400) + " in 1 second!");
				}
				
				if(Timer.gametime == eachshrink + 300)
				{
					eachshrink = Timer.gametime;
					borderShrinkAt500();
				}		
			}
			else if(bordersize == 100)
			{
				if(Timer.gametime == eachshrink + 180)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 50) + " in 2 minutes!");
					beforeBorderAt100();
				}
				
				if(Timer.gametime == eachshrink + 240)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 50) + " in 1 minute!");
				}
				
				if(Timer.gametime == eachshrink + 270)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 50) + " in 30 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 285)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 50) + " in 15 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 290)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 50) + " in 10 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 295)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 50) + " in 5 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 296)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 50) + " in 4 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 297)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 50) + " in 3 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 298)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 50) + " in 2 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 299)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 50) + " in 1 second!");
				}
				
				if(Timer.gametime == eachshrink + 300)
				{
					eachshrink = Timer.gametime;
					borderShrinkAt100();
				}	
			}
			else if(bordersize == 50)
			{			
				if(Timer.gametime == eachshrink + 180)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 25) + " in 2 minutes!");
					beforeBorderAt50();
				}
				
				if(Timer.gametime == eachshrink + 240)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 25) + " in 1 minute!");
				}
				
				if(Timer.gametime == eachshrink + 270)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 25) + " in 30 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 285)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 25) + " in 15 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 290)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 25) + " in 10 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 295)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 25) + " in 5 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 296)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 25) + " in 4 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 297)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 25) + " in 3 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 298)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 25) + " in 2 seconds!");
				}
				
				if(Timer.gametime == eachshrink + 299)
				{
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " The border will shrink to " + (bordersize - 25) + " in 1 second!");
				}
				
				if(Timer.gametime == eachshrink + 300)
				{
					eachshrink = Timer.gametime;
					borderShrinkAt50();
				}		
			}
		}
	}
	
	public void beforeBorderGreaterThan500()
	{
		World world = Bukkit.getWorld("uhc_world");
		int x = (bordersize - 500) + 1;
		int z = (bordersize - 500) + 1;
		int nx = -x;
		int nz = -z;
		
		Location loc = new Location(world, 0, world.getHighestBlockYAt(0,0), 0);
		
		// For all Z values where X is the bordersize + 1
		
		while(z != nz)
		{
			int y = world.getHighestBlockYAt(x, z) + 6;
			
			for(y = world.getHighestBlockYAt(x, z) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(x, y, z).getChunk().load();
				Block block = loc.getWorld().getBlockAt(x, y, z);
				wall1.add(block);
			}
			
			z--;
		}
		
		z = (bordersize - 500) + 1; 
		
		
		//For all X values where Z is nz
		
		while(x != nx)
		{
			int y = world.getHighestBlockYAt(x, nz) + 6;
			
			for(y = world.getHighestBlockYAt(x, nz) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(x, y, nz).getChunk().load();
				Block block = loc.getWorld().getBlockAt(x, y, nz);
				wall2.add(block);
			}
			
			x--;
		}
		
		x = (bordersize - 500) + 1;
		
		//For all Z values where X is nx
		
		while(nz != z)
		{
			int y = world.getHighestBlockYAt(nx, nz) + 6;
			
			for(y = world.getHighestBlockYAt(nx, nz) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(nx, y, nz).getChunk().load();
				Block block = loc.getWorld().getBlockAt(nx, y, nz);
				wall3.add(block);
			}
			
			nz++;
		}
		
		while(nx != x)
		{
			int y = world.getHighestBlockYAt(nx, z) + 6;
			
			for(y = world.getHighestBlockYAt(nx, z) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(nx, y, z).getChunk().load();
				Block block = loc.getWorld().getBlockAt(nx, y, z);
				wall4.add(block);
			}
			
			nx++;
		}
		
		System.out.print(wall1.size());
		System.out.print(wall2.size());
		System.out.print(wall3.size());
		System.out.print(wall4.size());
	}
	
	public void borderShrinkGreaterThan500()
	{
		String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Border" + ChatColor.GRAY + "]";

		new BukkitRunnable()
		{
			int delay = 3;
			
			public void run()
			{
				if(delay == 3)
				{
					for(int i = 0; i < wall1.size(); i++)
					{
						wall1.get(i).setType(Material.BEDROCK);
					}
						
					delay--;
				}
				else if(delay == 2)
				{
					for(int i = 0; i < wall2.size(); i++)
					{
						wall2.get(i).setType(Material.BEDROCK);
					}
					
					delay--;
				}
				else if(delay == 1)
				{
					for(int i = 0; i < wall3.size(); i++)
					{
						wall3.get(i).setType(Material.BEDROCK);
					}
					
					delay--;
				}
				else if(delay == 0)
				{
					for(int i = 0; i < wall4.size(); i++)
					{
						wall4.get(i).setType(Material.BEDROCK);
					}
					
					wall1.clear();
					wall2.clear();
					wall3.clear();
					wall4.clear();
					
					cancel();
					return;
				}
			}
			
		}.runTaskTimer(plugin, 0, 5);
		
		bordersize = bordersize - 500;
		
		for(Player player : Bukkit.getOnlinePlayers())
		{
			int x = player.getLocation().getBlockX();
			int y = player.getLocation().getBlockY();
			int z = player.getLocation().getBlockZ();
			
			teleport(player, x, y, z);
			
			if(bordersize > 500)
			{
				player.sendMessage(prefix + ChatColor.AQUA + " The next border will shrink to " + (bordersize - 500) + " in 5 minutes!");
			}
			else if(bordersize == 500)
			{
				player.sendMessage(prefix + ChatColor.AQUA + " The next border will shrink to " + (bordersize - 400) + " in 5 minutes!");
			}
		}
	}
	
	public void beforeBorderAt500()
	{
		World world = Bukkit.getWorld("uhc_world");
		int x = (bordersize - 400) + 1;
		int z = (bordersize - 400) + 1;
		int nx = -x;
		int nz = -z;
		
		Location loc = new Location(world, 0, world.getHighestBlockYAt(0,0), 0);
		
		// For all Z values where X is the bordersize + 1
		
		while(z != nz)
		{
			int y = world.getHighestBlockYAt(x, z) + 6;
			
			for(y = world.getHighestBlockYAt(x, z) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(x, y, z).getChunk().load();
				Block block = loc.getWorld().getBlockAt(x, y, z);
				wall1.add(block);
			}
			
			z--;
		}
		
		z = (bordersize - 400) + 1; 
		
		
		//For all X values where Z is nz
		
		while(x != nx)
		{
			int y = world.getHighestBlockYAt(x, nz) + 6;
			
			for(y = world.getHighestBlockYAt(x, nz) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(x, y, nz).getChunk().load();
				Block block = loc.getWorld().getBlockAt(x, y, nz);
				wall2.add(block);
			}
			
			x--;
		}
		
		x = (bordersize - 400) + 1;
		
		//For all Z values where X is nx
		
		while(nz != z)
		{
			int y = world.getHighestBlockYAt(nx, nz) + 6;
			
			for(y = world.getHighestBlockYAt(nx, nz) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(nx, y, nz).getChunk().load();
				Block block = loc.getWorld().getBlockAt(nx, y, nz);
				wall3.add(block);
			}
			
			nz++;
		}
		
		while(nx != x)
		{
			int y = world.getHighestBlockYAt(nx, z) + 6;
			
			for(y = world.getHighestBlockYAt(nx, z) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(nx, y, z).getChunk().load();
				Block block = loc.getWorld().getBlockAt(nx, y, z);
				wall4.add(block);
			}
			
			nx++;
		}
	}
	
	public void borderShrinkAt500()
	{
		String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Border" + ChatColor.GRAY + "]";
		
		new BukkitRunnable()
		{
			int delay = 3;
			
			public void run()
			{
				if(delay == 3)
				{
					for(int i = 0; i < wall1.size(); i++)
					{
						wall1.get(i).setType(Material.BEDROCK);
					}
						
					delay--;
				}
				else if(delay == 2)
				{
					for(int i = 0; i < wall2.size(); i++)
					{
						wall2.get(i).setType(Material.BEDROCK);
					}
					
					delay--;
				}
				else if(delay == 1)
				{
					for(int i = 0; i < wall3.size(); i++)
					{
						wall3.get(i).setType(Material.BEDROCK);
					}
					
					delay--;
				}
				else if(delay == 0)
				{
					for(int i = 0; i < wall4.size(); i++)
					{
						wall4.get(i).setType(Material.BEDROCK);
					}
					
					wall1.clear();
					wall2.clear();
					wall3.clear();
					wall4.clear();
					
					cancel();
					return;
				}
			}
			
		}.runTaskTimer(plugin, 0, 5);
		
		bordersize = bordersize - 400;
		
		for(Player player : Bukkit.getOnlinePlayers())
		{
			int x = player.getLocation().getBlockX();
			int y = player.getLocation().getBlockY();
			int z = player.getLocation().getBlockZ();
			
			teleport(player, x, y, z);
			
			player.sendMessage(prefix + ChatColor.AQUA + " The next border will shrink to " + (bordersize - 50) + " in 5 minutes!");
		}
	}
	
	public void beforeBorderAt100()
	{
		World world = Bukkit.getWorld("uhc_world");
		int x = (bordersize - 50) + 1;
		int z = (bordersize - 50) + 1;
		int nx = -x;
		int nz = -z;
		
		Location loc = new Location(world, 0, world.getHighestBlockYAt(0,0), 0);
		
		// For all Z values where X is the bordersize + 1
		
		while(z != nz)
		{
			int y = world.getHighestBlockYAt(x, z) + 6;
			
			for(y = world.getHighestBlockYAt(x, z) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(x, y, z).getChunk().load();
				Block block = loc.getWorld().getBlockAt(x, y, z);
				wall1.add(block);
			}
			
			z--;
		}
		
		z = (bordersize - 50) + 1; 
		
		
		//For all X values where Z is nz
		
		while(x != nx)
		{
			int y = world.getHighestBlockYAt(x, nz) + 6;
			
			for(y = world.getHighestBlockYAt(x, nz) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(x, y, nz).getChunk().load();
				Block block = loc.getWorld().getBlockAt(x, y, nz);
				wall2.add(block);
			}
			
			x--;
		}
		
		x = (bordersize - 50) + 1;
		
		//For all Z values where X is nx
		
		while(nz != z)
		{
			int y = world.getHighestBlockYAt(nx, nz) + 6;
			
			for(y = world.getHighestBlockYAt(nx, nz) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(nx, y, nz).getChunk().load();
				Block block = loc.getWorld().getBlockAt(nx, y, nz);
				wall3.add(block);
			}
			
			nz++;
		}
		
		while(nx != x)
		{
			int y = world.getHighestBlockYAt(nx, z) + 6;
			
			for(y = world.getHighestBlockYAt(nx, z) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(nx, y, z).getChunk().load();
				Block block = loc.getWorld().getBlockAt(nx, y, z);
				wall4.add(block);
			}
			
			nx++;
		}
	}
	
	public void borderShrinkAt100()
	{
		String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Border" + ChatColor.GRAY + "]";
		
		new BukkitRunnable()
		{
			int delay = 3;
			
			public void run()
			{
				if(delay == 3)
				{
					for(int i = 0; i < wall1.size(); i++)
					{
						wall1.get(i).setType(Material.BEDROCK);
					}
						
					delay--;
				}
				else if(delay == 2)
				{
					for(int i = 0; i < wall2.size(); i++)
					{
						wall2.get(i).setType(Material.BEDROCK);
					}
					
					delay--;
				}
				else if(delay == 1)
				{
					for(int i = 0; i < wall3.size(); i++)
					{
						wall3.get(i).setType(Material.BEDROCK);
					}
					
					delay--;
				}
				else if(delay == 0)
				{
					for(int i = 0; i < wall4.size(); i++)
					{
						wall4.get(i).setType(Material.BEDROCK);
					}
					
					wall1.clear();
					wall2.clear();
					wall3.clear();
					wall4.clear();
					
					cancel();
					return;
				}
			}
			
		}.runTaskTimer(plugin, 0, 5);
		
		bordersize = bordersize - 50;
		
		for(Player player : Bukkit.getOnlinePlayers())
		{
			int x = player.getLocation().getBlockX();
			int y = player.getLocation().getBlockY();
			int z = player.getLocation().getBlockZ();
			
			teleport(player, x, y, z);
			
			player.sendMessage(prefix + ChatColor.AQUA + " The next border will shrink to " + (bordersize - 25) + " in 5 minutes!");
		}
	}
	
	public void beforeBorderAt50()
	{
		World world = Bukkit.getWorld("uhc_world");
		int x = (bordersize - 25) + 1;
		int z = (bordersize - 25) + 1;
		int nx = -x;
		int nz = -z;
		
		Location loc = new Location(world, 0, world.getHighestBlockYAt(0,0), 0);
		
		// For all Z values where X is the bordersize + 1
		
		while(z != nz)
		{
			int y = world.getHighestBlockYAt(x, z) + 6;
			
			for(y = world.getHighestBlockYAt(x, z) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(x, y, z).getChunk().load();
				Block block = loc.getWorld().getBlockAt(x, y, z);
				wall1.add(block);
			}
			
			z--;
		}
		
		z = (bordersize - 25) + 1; 
		
		
		//For all X values where Z is nz
		
		while(x != nx)
		{
			int y = world.getHighestBlockYAt(x, nz) + 6;
			
			for(y = world.getHighestBlockYAt(x, nz) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(x, y, nz).getChunk().load();
				Block block = loc.getWorld().getBlockAt(x, y, nz);
				wall2.add(block);
			}
			
			x--;
		}
		
		x = (bordersize - 25) + 1;
		
		//For all Z values where X is nx
		
		while(nz != z)
		{
			int y = world.getHighestBlockYAt(nx, nz) + 6;
			
			for(y = world.getHighestBlockYAt(nx, nz) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(nx, y, nz).getChunk().load();
				Block block = loc.getWorld().getBlockAt(nx, y, nz);
				wall3.add(block);
			}
			
			nz++;
		}
		
		while(nx != x)
		{
			int y = world.getHighestBlockYAt(nx, z) + 6;
			
			for(y = world.getHighestBlockYAt(nx, z) + 6; y > 0; y--)
			{
				loc.getWorld().getBlockAt(nx, y, z).getChunk().load();
				Block block = loc.getWorld().getBlockAt(nx, y, z);
				wall4.add(block);
			}
			
			nx++;
		}
	}
	
	public void borderShrinkAt50()
	{
		String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Border" + ChatColor.GRAY + "]";
		
		new BukkitRunnable()
		{
			int delay = 3;
			
			public void run()
			{
				if(delay == 3)
				{
					for(int i = 0; i < wall1.size(); i++)
					{
						wall1.get(i).setType(Material.BEDROCK);
					}
						
					delay--;
				}
				else if(delay == 2)
				{
					for(int i = 0; i < wall2.size(); i++)
					{
						wall2.get(i).setType(Material.BEDROCK);
					}
					
					delay--;
				}
				else if(delay == 1)
				{
					for(int i = 0; i < wall3.size(); i++)
					{
						wall3.get(i).setType(Material.BEDROCK);
					}
					
					delay--;
				}
				else if(delay == 0)
				{
					for(int i = 0; i < wall4.size(); i++)
					{
						wall4.get(i).setType(Material.BEDROCK);
					}
					
					wall1.clear();
					wall2.clear();
					wall3.clear();
					wall4.clear();
					
					cancel();
					return;
				}
			}
			
		}.runTaskTimer(plugin, 0, 5);
		
		bordersize = bordersize - 25;
		
		for(Player player : Bukkit.getOnlinePlayers())
		{
			int x = player.getLocation().getBlockX();
			int y = player.getLocation().getBlockY();
			int z = player.getLocation().getBlockZ();
			
			teleport(player, x, y, z);
			
			player.sendMessage(prefix + ChatColor.AQUA + " The border has shrunk to " + (bordersize));
		}
	}
	
	public void teleport(Player p, int x, int y, int z)
	{
		if(x > 0)
		{
			if(z > 0)
			{
				if(x > bordersize && z > bordersize)
				{
					int nx = x - (x - bordersize) - 5; 
					int nz = z - (z - bordersize) - 5;
					int ny = world.getHighestBlockYAt(nx, nz);
					
					world.getSpawnLocation().add(nx, ny, nz).getChunk().load();
					p.teleport(world.getSpawnLocation().add(nx, ny, nz));
				}
				else if(x > bordersize && z < bordersize)
				{
					int nx = x - (x - bordersize) - 5;
					z = z - 5;
					int ny = world.getHighestBlockYAt(nx, z);
					
					world.getSpawnLocation().add(nx, ny, z).getChunk().load();
					p.teleport(world.getSpawnLocation().add(nx, ny, z));
				}
				else if(x < bordersize && z > bordersize)
				{
					int nz = z - (z - bordersize) - 5;
					x = x - 5;
					int ny = world.getHighestBlockYAt(x, nz);
					
					world.getSpawnLocation().add(x, ny, nz).getChunk().load();
					p.teleport(world.getSpawnLocation().add(x, ny, nz));
				}
			}
			else
			{
				if(x > bordersize && z < -bordersize)
				{
					int nx = x - (x - bordersize) - 5;
					int nz = z - (z + bordersize) + 5; 
					int ny = world.getHighestBlockYAt(nx, nz);
					
					world.getSpawnLocation().add(nx, ny, nz).getChunk().load();
					p.teleport(world.getSpawnLocation().add(nx, ny, nz));
				}
				else if(x > bordersize && z > -bordersize)
				{
					int nx = x - (x - bordersize) - 5;
					z = z + 5;
					int ny = world.getHighestBlockYAt(nx, z);
					
					world.getSpawnLocation().add(nx, ny, z).getChunk().load();
					p.teleport(world.getSpawnLocation().add(nx, ny, z));
				}
				else if(x < bordersize && z < -bordersize)
				{
					int nz = z - (z + bordersize) + 5;
					x = x - 5;
					int ny = world.getHighestBlockYAt(x, nz);
					
					world.getSpawnLocation().add(x, ny, nz).getChunk().load();
					p.teleport(world.getSpawnLocation().add(x, ny, nz));
				}
			}
		}
		else
		{
			if(z > 0)
			{
				if(x < -bordersize && z > bordersize)
				{
					int nx = x - (x + bordersize) + 5;
					int nz = z - (z - bordersize) - 5; 
					int ny = world.getHighestBlockYAt(nx, nz);
					
					world.getSpawnLocation().add(nx, ny, nz).getChunk().load();
					p.teleport(world.getSpawnLocation().add(nx, ny, nz));
				}
				else if(x < -bordersize && z < bordersize)
				{
					int nx = x - (x + bordersize) + 5;
					z = z - 5;
					int ny = world.getHighestBlockYAt(nx, z);
					
					world.getSpawnLocation().add(nx, ny, z).getChunk().load();
					p.teleport(world.getSpawnLocation().add(nx, ny, z));
				}
				else if(x > -bordersize && z > bordersize)
				{
					int nz = z - (z - bordersize) - 5;
					x = x + 5;
					int ny = world.getHighestBlockYAt(x, nz);
					
					world.getSpawnLocation().add(x, ny, nz).getChunk().load();
					p.teleport(world.getSpawnLocation().add(x, ny, nz));
				}
			}
			else
			{
				if(x < -bordersize && z < -bordersize)
				{
					int nx = x - (x + bordersize) + 5;
					int nz = z - (z + bordersize) + 5; 
					int ny = world.getHighestBlockYAt(nx, nz);
					
					world.getSpawnLocation().add(nx, ny, nz).getChunk().load();
					p.teleport(world.getSpawnLocation().add(nx, ny, nz));
				}
				else if(x < -bordersize && z > -bordersize)
				{
					int nx = x - (x + bordersize) + 5;
					z = z + 5;
					int ny = world.getHighestBlockYAt(nx, z);
					
					world.getSpawnLocation().add(nx, ny, z).getChunk().load();
					p.teleport(world.getSpawnLocation().add(nx, ny, z));
				}
				else if(x > -bordersize && z < -bordersize)
				{
					int nz = z - (z + bordersize) + 5;
					x = x + 5;
					int ny = world.getHighestBlockYAt(x, nz);
					
					world.getSpawnLocation().add(x, ny, nz).getChunk().load();
					p.teleport(world.getSpawnLocation().add(x, ny, nz));
				}
			}
		}

	}
}