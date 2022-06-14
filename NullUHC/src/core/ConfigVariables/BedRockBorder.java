package core.ConfigVariables;

import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Scatter.Scatter;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class BedRockBorder implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
    public static int currentBorderSize = ConfigInventory.borderSize;

    public void setUpBorder()
    {
    	boolean first = setFirstWall();
    	boolean second = setSecondWall();
    	boolean third = setThirdWall();
    	boolean fourth = setFourthWall();
    	
    	if(first && second && third && fourth)
    	{
    		
    	}
    }
    
    public boolean setFirstWall()
    {
    	World world = Bukkit.getWorld("uhc_world");
    	ArrayList<Block> wall = new ArrayList<Block>();
    	int z = ConfigInventory.borderSize;
    	boolean check = false;
    	
    	
    	while(!check)
        {
        	if(z < -ConfigInventory.borderSize)
            {
        		check = true;
            }
        	else
        	{
        		for(int y = 0; y < world.getHighestBlockYAt(ConfigInventory.borderSize, z) + 10; y++)
        		{
        			wall.add(world.getBlockAt(ConfigInventory.borderSize, y, z));
        		}
        		
        		z--;
        	}
        }
        
        new BukkitRunnable()
        {
        	int count = 0;
        	int size = wall.size() / 10;
        	
        	public void run()
        	{
        		if(wall.isEmpty())
        		{
        			cancel();
        		}
        		
        		for(int i = 0; i < wall.size(); i++)
        		{
    				if(count == size)
    				{
    					count = 0;
    					break;
    				}
    				
    				wall.get(i).setType(Material.BEDROCK);
    				world.loadChunk(wall.get(i).getChunk());
    				wall.remove(i);
    				
    				count++;
        		}
        	}
        	
        }.runTaskTimer(plugin, 0, 60);
        
        return true;
    }
    
    public boolean setSecondWall()
    {
    	World world = Bukkit.getWorld("uhc_world");
    	ArrayList<Block> wall = new ArrayList<Block>();
    	int z = -ConfigInventory.borderSize;
    	boolean check = false;
    	
    	
    	while(!check)
        {
        	if(z > ConfigInventory.borderSize)
            {
        		check = true;
            }
        	else
        	{
        		for(int y = 0; y < world.getHighestBlockYAt(-ConfigInventory.borderSize, z) + 10; y++)
        		{
        			wall.add(world.getBlockAt(-ConfigInventory.borderSize, y, z));
        		}
        		
        		z++;
        	}
        }
        
        new BukkitRunnable()
        {
        	int count = 0;
        	int size = wall.size() / 10;
        	
        	public void run()
        	{
        		if(wall.isEmpty())
        		{
        			cancel();
        		}
        		
        		for(int i = 0; i < wall.size(); i++)
        		{
    				if(count == size)
    				{
    					count = 0;
    					break;
    				}
    				
    				wall.get(i).setType(Material.BEDROCK);
    				world.loadChunk(wall.get(i).getChunk());
    				wall.remove(i);
    				
    				count++;
        		}
        	}
        	
        }.runTaskTimer(plugin, 0, 60);
    	
    	return true;
    }
    
    public boolean setThirdWall()
    {
    	World world = Bukkit.getWorld("uhc_world");
    	ArrayList<Block> wall = new ArrayList<Block>();
    	int x = ConfigInventory.borderSize;
    	boolean check = false;
    	
    	while(!check)
        {
        	if(x < -ConfigInventory.borderSize)
            {
        		check = true;
            }
        	else
        	{
        		for(int y = 0; y < world.getHighestBlockYAt(x, ConfigInventory.borderSize) + 10; y++)
        		{
        			wall.add(world.getBlockAt(x, y, ConfigInventory.borderSize));
        		}
        		
        		x--;
        	}
        }
        
        new BukkitRunnable()
        {
        	int count = 0;
        	int size = wall.size() / 10;
        	
        	public void run()
        	{
        		if(wall.isEmpty())
        		{
        			cancel();
        		}
        		
        		for(int i = 0; i < wall.size(); i++)
        		{
    				if(count == size)
    				{
    					count = 0;
    					break;
    				}
    				
    				wall.get(i).setType(Material.BEDROCK);
    				world.loadChunk(wall.get(i).getChunk());
    				wall.remove(i);
    				
    				count++;
        		}
        	}
        	
        }.runTaskTimer(plugin, 0, 60);
    	
    	return true;
    }
    
    public boolean setFourthWall()
    {
    	World world = Bukkit.getWorld("uhc_world");
    	ArrayList<Block> wall = new ArrayList<Block>();
    	int x = -ConfigInventory.borderSize;
    	boolean check = false;
    	
    	while(!check)
        {
        	if(x > ConfigInventory.borderSize)
            {
        		check = true;
            }
        	else
        	{
        		for(int y = 0; y < world.getHighestBlockYAt(x, -ConfigInventory.borderSize) + 10; y++)
        		{
        			wall.add(world.getBlockAt(x, y, -ConfigInventory.borderSize));
        		}
        		
        		x++;
        	}
        }
        
        new BukkitRunnable()
        {
        	int count = 0;
        	int size = wall.size() / 10;
        	
        	public void run()
        	{
        		if(wall.isEmpty())
        		{
        			cancel();
        		}
        		
        		for(int i = 0; i < wall.size(); i++)
        		{
    				if(count == size)
    				{
    					count = 0;
    					break;
    				}
    				
    				wall.get(i).setType(Material.BEDROCK);
    				world.loadChunk(wall.get(i).getChunk());
    				wall.remove(i);
    				
    				count++;
        		}
        	}
        	
        }.runTaskTimer(plugin, 0, 60);
    	
    	return true;
    }
    
    public void setShrink()
    {
    	if(currentBorderSize == 3000)
    	{
    		currentBorderSize = 1500;
    	}
    	else if(currentBorderSize == 1500)
    	{
    		currentBorderSize = 1000;
    	}
    	else if(currentBorderSize == 1000)
    	{
    		currentBorderSize = 500;
    	}
    	else if(currentBorderSize == 500)
    	{
    		currentBorderSize = 250;
    	}
    	else if(currentBorderSize == 250)
    	{
    		currentBorderSize = 100;
    	}
    	else if(currentBorderSize == 100)
    	{
    		currentBorderSize = 50;
    	}
    	else if(currentBorderSize == 50)
    	{
    		currentBorderSize = 25;
    	}
    	
    	teleportPlayers();
    	
    	boolean first = ShrinkFirstWall();
    	boolean second = ShrinkSecondWall();
    	boolean third = ShrinkThirdWall();
    	boolean fourth = ShrinkFourthWall();
    	
    	if(first && second && third && fourth)
    	{
    		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.YELLOW + " The Border has shrunk to " + currentBorderSize + "x" + currentBorderSize + ".");
    	}
    }
    
    public boolean ShrinkFirstWall()
    {
    	World world = Bukkit.getWorld("uhc_world");
    	ArrayList<Block> wall = new ArrayList<Block>();
    	int z = currentBorderSize;
    	boolean check = false;
    	
    	
    	while(!check)
        {
        	if(z < -currentBorderSize)
            {
        		check = true;
            }
        	else
        	{
        		for(int y = 0; y < world.getHighestBlockYAt(currentBorderSize, z) + 10; y++)
        		{
        			wall.add(world.getBlockAt(currentBorderSize, y, z));
        		}
        		
        		z--;
        	}
        }
        
        new BukkitRunnable()
        {
        	int count = 0;
        	int size = 0;
        	boolean check = false;
        	
        	public void run()
        	{
        		if(currentBorderSize >= 1000 && !check)
            	{
            		size = wall.size() / 20;
            		check = true;
            	}
        		else if(currentBorderSize == 500 && !check)
            	{
            		size = wall.size() / 10;
            		check = true;
            	}
            	else if(currentBorderSize < 500 && !check)
            	{
            		size = wall.size() / 2;
            		check = true;
            	}
        		
        		if(wall.isEmpty())
        		{
        			cancel();
        		}
        		
        		for(int i = 0; i < wall.size(); i++)
        		{
    				if(count == size)
    				{
    					count = 0;
    					break;
    				}
    				
    				wall.get(i).setType(Material.BEDROCK);
    				world.loadChunk(wall.get(i).getChunk());
    				wall.remove(i);
    				
    				count++;
        		}
        	}
        	
        }.runTaskTimer(plugin, 0, 10);
        
    	return true;
    }
    
    public boolean ShrinkSecondWall()
    {
    	World world = Bukkit.getWorld("uhc_world");
    	ArrayList<Block> wall = new ArrayList<Block>();
    	int z = -currentBorderSize;
    	boolean check = false;
    	
    	
    	while(!check)
        {
        	if(z > currentBorderSize)
            {
        		check = true;
            }
        	else
        	{
        		for(int y = 0; y < world.getHighestBlockYAt(-currentBorderSize, z) + 10; y++)
        		{
        			wall.add(world.getBlockAt(-currentBorderSize, y, z));
        		}
        		
        		z++;
        	}
        }
        
        new BukkitRunnable()
        {
        	int count = 0;
        	int size = 0;
        	boolean check = false;
        	
        	public void run()
        	{
        		if(currentBorderSize >= 1000 && !check)
            	{
            		size = wall.size() / 20;
            		check = true;
            	}
        		else if(currentBorderSize == 500 && !check)
            	{
            		size = wall.size() / 10;
            		check = true;
            	}
            	else if(currentBorderSize < 500 && !check)
            	{
            		size = wall.size() / 2;
            		check = true;
            	}
        		
        		if(wall.isEmpty())
        		{
        			cancel();
        		}
        		
        		for(int i = 0; i < wall.size(); i++)
        		{
    				if(count == size)
    				{
    					count = 0;
    					break;
    				}
    				
    				wall.get(i).setType(Material.BEDROCK);
    				world.loadChunk(wall.get(i).getChunk());
    				wall.remove(i);
    				
    				count++;
        		}
        	}
        	
        }.runTaskTimer(plugin, 0, 10);
    	
    	return true;
    }
    
    public boolean ShrinkThirdWall()
    {
    	World world = Bukkit.getWorld("uhc_world");
    	ArrayList<Block> wall = new ArrayList<Block>();
    	int x = currentBorderSize;
    	boolean check = false;
    	
    	while(!check)
        {
        	if(x < -currentBorderSize)
            {
        		check = true;
            }
        	else
        	{
        		for(int y = 0; y < world.getHighestBlockYAt(x, currentBorderSize) + 10; y++)
        		{
        			wall.add(world.getBlockAt(x, y, currentBorderSize));
        		}
        		
        		x--;
        	}
        }
        
        new BukkitRunnable()
        {
        	int count = 0;
        	int size = 0;
        	boolean check = false;
        	
        	public void run()
        	{
        		if(currentBorderSize >= 1000 && !check)
            	{
            		size = wall.size() / 20;
            		check = true;
            	}
        		else if(currentBorderSize == 500 && !check)
            	{
            		size = wall.size() / 10;
            		check = true;
            	}
            	else if(currentBorderSize < 500 && !check)
            	{
            		size = wall.size() / 2;
            		check = true;
            	}
        		
        		if(wall.isEmpty())
        		{
        			cancel();
        		}
        		
        		for(int i = 0; i < wall.size(); i++)
        		{
    				if(count == size)
    				{
    					count = 0;
    					break;
    				}
    				
    				wall.get(i).setType(Material.BEDROCK);
    				world.loadChunk(wall.get(i).getChunk());
    				wall.remove(i);
    				
    				count++;
        		}
        	}
        	
        }.runTaskTimer(plugin, 0, 10);
        
    	return true;
    }
    
    public boolean ShrinkFourthWall()
    {
    	World world = Bukkit.getWorld("uhc_world");
    	ArrayList<Block> wall = new ArrayList<Block>();
    	int x = -currentBorderSize;
    	boolean check = false;
    	
    	while(!check)
        {
        	if(x > currentBorderSize)
            {
        		check = true;
            }
        	else
        	{
        		for(int y = 0; y < world.getHighestBlockYAt(x, -currentBorderSize) + 10; y++)
        		{
        			wall.add(world.getBlockAt(x, y, -currentBorderSize));
        		}
        		
        		x++;
        	}
        }
        
        new BukkitRunnable()
        {
        	int count = 0;
        	int size = 0;
        	boolean check = false;
        	
        	public void run()
        	{
        		if(currentBorderSize >= 1000 && !check)
            	{
            		size = wall.size() / 20;
            		check = true;
            	}
        		else if(currentBorderSize == 500 && !check)
            	{
            		size = wall.size() / 10;
            		check = true;
            	}
            	else if(currentBorderSize < 500 && !check)
            	{
            		size = wall.size() / 2;
            		check = true;
            	}
        		
        		if(wall.isEmpty())
        		{
        			cancel();
        		}
        		
        		for(int i = 0; i < wall.size(); i++)
        		{
    				if(count == size)
    				{
    					count = 0;
    					break;
    				}
    				
    				wall.get(i).setType(Material.BEDROCK);
    				world.loadChunk(wall.get(i).getChunk());
    				wall.remove(i);
    				
    				count++;
        		}
        	}
        	
        }.runTaskTimer(plugin, 0, 10);
        
    	return true;
    }
    
    public void teleportPlayers()
    {
    	World world = Bukkit.getWorld("uhc_world");
    	
    	for(Player p : Bukkit.getOnlinePlayers())
    	{
    		if(!PlayerKills.dead.contains(p.getUniqueId()) && !HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
    		{
    			if(p.getLocation().getX() > currentBorderSize)
    			{
    				// Just check Z if we get in here
    				
    				if(p.getLocation().getZ() > currentBorderSize)
					{
						Location loc = new Location(world, currentBorderSize - 1, world.getHighestBlockYAt(currentBorderSize - 1, currentBorderSize - 1) , currentBorderSize - 1);
						world.loadChunk(loc.getChunk());
						p.teleport(loc);
					}
					else if(p.getLocation().getZ() < currentBorderSize)
					{
						if(p.getLocation().getZ() < -currentBorderSize)
						{
							Location loc = new Location(world, currentBorderSize - 1, world.getHighestBlockYAt(currentBorderSize - 1, -currentBorderSize + 1) , -currentBorderSize + 1);
							world.loadChunk(loc.getChunk());
							p.teleport(loc);
						}
						else
						{
							//Z is good if we get in here, we just need to change X
							
							Location loc = new Location(world, currentBorderSize - 1, world.getHighestBlockYAt(currentBorderSize - 1, (int) p.getLocation().getZ()) , p.getLocation().getZ());
							world.loadChunk(loc.getChunk());
							p.teleport(loc);
						}
					}
    			}
    			else if(p.getLocation().getX() < currentBorderSize)
    			{
    				if(p.getLocation().getX() < -currentBorderSize)
    				{
    					if(p.getLocation().getZ() > currentBorderSize)
    					{
    						Location loc = new Location(world, -currentBorderSize + 1, world.getHighestBlockYAt(-currentBorderSize + 1, currentBorderSize - 1) , currentBorderSize - 1);
    						world.loadChunk(loc.getChunk());
							p.teleport(loc);
    					}
    					else if(p.getLocation().getZ() < currentBorderSize)
    					{
    						if(p.getLocation().getZ() < -currentBorderSize)
    						{
    							Location loc = new Location(world, -currentBorderSize + 1, world.getHighestBlockYAt(-currentBorderSize + 1, -currentBorderSize + 1) , -currentBorderSize + 1);
    							world.loadChunk(loc.getChunk());
    							p.teleport(loc);
    						}
    						else
    						{
    							//Z is good if we get in here, we just need to change X
    							
    							Location loc = new Location(world, -currentBorderSize + 1, world.getHighestBlockYAt(-currentBorderSize + 1, (int) p.getLocation().getZ()) , p.getLocation().getZ());
    							world.loadChunk(loc.getChunk());
    							p.teleport(loc);
    						}
    					}
    				}
    				else
    				{
    					// X is good if we get here, we just need to check Z
    					
    					if(p.getLocation().getZ() > currentBorderSize)
    					{
    						Location loc = new Location(world, p.getLocation().getX(), world.getHighestBlockYAt((int) p.getLocation().getX(), currentBorderSize - 1) , currentBorderSize - 1);
    						world.loadChunk(loc.getChunk());
							p.teleport(loc);
    					}
    					else if(p.getLocation().getZ() < currentBorderSize)
    					{
    						if(p.getLocation().getZ() < -currentBorderSize)
    						{
    							Location loc = new Location(world, p.getLocation().getX(), world.getHighestBlockYAt((int) p.getLocation().getX(), -currentBorderSize + 1) , -currentBorderSize + 1);
    							world.loadChunk(loc.getChunk());
    							p.teleport(loc);
    						}
    					}
    				}
    			}
    		}
    	}
    }
}