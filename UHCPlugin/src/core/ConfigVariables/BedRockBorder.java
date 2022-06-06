package core.ConfigVariables;

import core.Config.ConfigInventory;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class BedRockBorder implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
    private static int currentBorderSize = ConfigInventory.borderSize;

    public void setUpBorder()
    {
    	boolean first = setFirstWall();
    	boolean second = setSecondWall();
    	boolean third = setThirdWall();
    	boolean fourth = setFourthWall();
    	
    	if(first && second && third && fourth)
    	{
    		Bukkit.broadcastMessage(ChatColor.GREEN + "Wall is done.");
    	}
    }
    
    public boolean setFirstWall()
    {
    	World world = Bukkit.getWorld("uhcworld");
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
    	World world = Bukkit.getWorld("uhcworld");
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
    	World world = Bukkit.getWorld("uhcworld");
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
    	World world = Bukkit.getWorld("uhcworld");
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
    	
    	boolean first = ShrinkFirstWall();
    	boolean second = ShrinkSecondWall();
    	boolean third = ShrinkThirdWall();
    	boolean fourth = ShrinkFourthWall();
    	
    	if(first && second && third && fourth)
    	{
    		Bukkit.broadcastMessage(ChatColor.GREEN + "The wall has shrunk to " + currentBorderSize + "x" + currentBorderSize + ".");
    	}
    }
    
    public boolean ShrinkFirstWall()
    {
    	World world = Bukkit.getWorld("uhcworld");
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
        	
        }.runTaskTimer(plugin, 0, 20);
        
    	return true;
    }
    
    public boolean ShrinkSecondWall()
    {
    	World world = Bukkit.getWorld("uhcworld");
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
        	
        }.runTaskTimer(plugin, 0, 20);
    	
    	return true;
    }
    
    public boolean ShrinkThirdWall()
    {
    	World world = Bukkit.getWorld("uhcworld");
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
        	
        }.runTaskTimer(plugin, 0, 20);
        
    	return true;
    }
    
    public boolean ShrinkFourthWall()
    {
    	World world = Bukkit.getWorld("uhcworld");
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
        	
        }.runTaskTimer(plugin, 0, 20);
        
    	return true;
    }
}