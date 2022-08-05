package core.ConfigVariables;

import core.Config.ConfigInventory;
import core.Events.NPCEvent;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Scatter.Scatter;
import core.mainPackage.Main;
import net.minecraft.server.v1_7_R4.BlockActionData;
import net.minecraft.server.v1_7_R4.Blocks;
import net.minecraft.server.v1_7_R4.IBlockAccess;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.block.CraftBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class BedRockBorder implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
	public static HashMap<UUID, Location> teleLocations = new HashMap<UUID, Location>();
	public static HashMap<UUID, Location> offlineDuringTele = new HashMap<UUID, Location>();
    public static int currentBorderSize = ConfigInventory.borderSize;
    public void setUpBorder(int border, World world)
    {
    	getFirstWallBlocks(border, world);
		getSecondWallBlocks(border, world);
		getThirdWallBlocks(border, world);
		getFourthWallBlocks(border, world);

		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.GOLD + " Border has shrunk to " + BedRockBorder.currentBorderSize + "x" + BedRockBorder.currentBorderSize + ".");
		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 5 minutes.");
    }
    
    public void getFirstWallBlocks(int border, World world)
    {
    	ArrayList<Block> wall = new ArrayList<Block>();
		int z = border;
		boolean check = false;

		while(!check)
		{
			if(z < -border)
			{
				check = true;
			}
			else
			{
				for(int y = 0; y < world.getHighestBlockYAt(border, z) + 10; y++)
				{
					if(y == world.getHighestBlockYAt(border, z) + 10)
					{
						break;
					}
					else
					{
						world.loadChunk(world.getBlockAt(border, y, z).getChunk());
						wall.add(world.getBlockAt(border, y, z));
					}
				}

				z--;
			}
		}

		setFirstWall(wall);
    }

	public void setFirstWall(ArrayList<Block> wall)
	{
		World world = Bukkit.getWorld("uhc_world");

		new BukkitRunnable()
		{
			int count = 0;

			public void run()
			{
				if(count == wall.size())
				{
					cancel();
				}
				else
				{
					for (Block block : wall)
					{
						setBlocks(block.getX(), block.getY(), block.getZ(), world);
						count++;
					}
				}
			}

		}.runTaskTimer(plugin, 0, 20);
	}

	public void getSecondWallBlocks(int border, World world)
	{
		ArrayList<Block> wall = new ArrayList<Block>();
		int z = -border;
		boolean check = false;

		while(!check)
		{
			if(z > border)
			{
				check = true;
			}
			else
			{
				for(int y = 0; y < world.getHighestBlockYAt(-border, z) + 10; y++)
				{
					wall.add(world.getBlockAt(-border, y, z));
					world.loadChunk(world.getBlockAt(-border, y, z).getChunk());
				}

				z++;
			}
		}

		setSecondWall(wall);
	}
    
    public void setSecondWall(ArrayList<Block> wall)
    {
		World world = Bukkit.getWorld("uhc_world");

		new BukkitRunnable()
		{
			int count = 0;

			public void run()
			{
				if(count == wall.size())
				{
					cancel();
				}
				else
				{
					for (Block block : wall)
					{
						setBlocks(block.getX(), block.getY(), block.getZ(), world);
						count++;
					}
				}
			}

		}.runTaskTimer(plugin, 0, 20);
    }

	public void getThirdWallBlocks(int border, World world)
	{
		ArrayList<Block> wall = new ArrayList<Block>();
		int x = border;
		boolean check = false;

		while(!check)
		{
			if(x < -border)
			{
				check = true;
			}
			else
			{
				for(int y = 0; y < world.getHighestBlockYAt(x, border) + 10; y++)
				{
					wall.add(world.getBlockAt(x, y, border));
					world.loadChunk(world.getBlockAt(x, y, border).getChunk());
				}

				x--;
			}
		}

		setThirdWall(wall);
	}

    public void setThirdWall(ArrayList<Block> wall)
    {
    	World world = Bukkit.getWorld("uhc_world");

		new BukkitRunnable()
		{
			int count = 0;

			public void run()
			{
				if(count == wall.size())
				{
					cancel();
				}
				else
				{
					for (Block block : wall)
					{
						setBlocks(block.getX(), block.getY(), block.getZ(), world);
						count++;
					}
				}
			}

		}.runTaskTimer(plugin, 0, 20);
    }

	public void getFourthWallBlocks(int border, World world)
	{
		ArrayList<Block> wall = new ArrayList<Block>();
		int x = -border;
		boolean check = false;

		while(!check)
		{
			if(x > border)
			{
				check = true;
			}
			else
			{
				for(int y = 0; y < world.getHighestBlockYAt(x, -border) + 10; y++)
				{
					wall.add(world.getBlockAt(x, y, -border));
					world.loadChunk(world.getBlockAt(x, y, -border).getChunk());
				}

				x++;
			}
		}

		setFourthWall(wall);
	}

    
    public void setFourthWall(ArrayList<Block> wall)
    {
    	World world = Bukkit.getWorld("uhc_world");

		new BukkitRunnable()
		{
			int count = 0;

			public void run()
			{
				if(count == wall.size())
				{
					cancel();
				}
				else
				{
					for (Block block : wall)
					{
						setBlocks(block.getX(), block.getY(), block.getZ(), world);
						count++;
					}
				}
			}

		}.runTaskTimer(plugin, 0, 20);
    }

	public void setUpShrink()
	{
		switch (currentBorderSize)
		{
			case 1500:
				currentBorderSize = 1000;
				break;

			case 1000:
				currentBorderSize = 500;
				break;

			case 500:
				currentBorderSize = 250;
				break;

			case 250:
				currentBorderSize = 100;
				break;

			case 100:
				currentBorderSize = 50;
				break;

			case 50:
				currentBorderSize = 25;
				break;
		}

		getTeleportLocations();
	}

	public void getTeleportLocations()
	{
		World world = Bukkit.getWorld("uhc_world");

		if(currentBorderSize > 500)
		{
			for(int i = 0; i < Scatter.allPlayers.size(); i++)
			{
				boolean posx = false, negx = false, posz = false, negz = false;
				int x = 0, z = 0;
				Player p = Bukkit.getPlayer(Scatter.allPlayers.get(i));

				if(p != null)
				{
					if(p.getLocation().getBlockX() > currentBorderSize)
					{
						posx = true;
					}
					else if(p.getLocation().getBlockX() < -(currentBorderSize))
					{
						negx = true;
					}

					if(p.getLocation().getBlockZ() > currentBorderSize)
					{
						posz = true;
					}
					else if(p.getLocation().getBlockZ() < -(currentBorderSize))
					{
						negz = true;
					}

					if(posx)
					{
						if(posz)
						{
							x = currentBorderSize - 2;
							z = currentBorderSize - 2;
						}
						else if(negz)
						{
							x = currentBorderSize - 2;
							z = -(currentBorderSize) + 2;
						}
						else
						{
							x = currentBorderSize - 2;
							z = p.getLocation().getBlockZ();
						}
					}
					else if(negx)
					{
						if(posz)
						{
							x = -(currentBorderSize) + 2;
							z = currentBorderSize;
						}
						else if(negz)
						{
							x = -(currentBorderSize) + 2;
							z = -(currentBorderSize) + 2;
						}
						else
						{
							x = -(currentBorderSize) + 2;
							z = p.getLocation().getBlockZ();
						}
					}
					else
					{
						if(posz)
						{
							x = p.getLocation().getBlockX();
							z = currentBorderSize - 2;
						}
						else if(negz)
						{
							x = p.getLocation().getBlockX();
							z = -(currentBorderSize) + 2;
						}
					}

					if(x != 0 && z != 0)
					{
						Location loc = new Location(world, x, world.getHighestBlockYAt(x, z) + 1, z);
						teleLocations.put(p.getUniqueId(), loc);
					}
				}
				else
				{
					if(NPCEvent.npcList.containsKey(Scatter.allPlayers.get(i)))
					{
						OfflinePlayer offp = Bukkit.getOfflinePlayer(Scatter.allPlayers.get(i));

						if(NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockX() > currentBorderSize)
						{
							posx = true;
						}
						else if(NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockX() < -(currentBorderSize))
						{
							negx = true;
						}

						if(NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockZ() > currentBorderSize)
						{
							posz = true;
						}
						else if(NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockZ() < -(currentBorderSize))
						{
							negz = true;
						}

						if(posx)
						{
							if(posz)
							{
								x = currentBorderSize - 2;
								z = currentBorderSize - 2;
							}
							else if(negz)
							{
								x = currentBorderSize - 2;
								z = -(currentBorderSize) + 2;
							}
							else
							{
								x = currentBorderSize - 2;
								z = NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockZ();
							}
						}
						else if(negx)
						{
							if(posz)
							{
								x = -(currentBorderSize) + 2;
								z = currentBorderSize;
							}
							else if(negz)
							{
								x = -(currentBorderSize) + 2;
								z = -(currentBorderSize) + 2;
							}
							else
							{
								x = -(currentBorderSize) + 2;
								z = NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockZ();
							}
						}
						else
						{
							if(posz)
							{
								x = NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockX();
								z = currentBorderSize - 2;
							}
							else if(negz)
							{
								x = NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockX();
								z = -(currentBorderSize) + 2;
							}
						}

						if(x != 0 && z != 0)
						{
							Location loc = new Location(world, x, world.getHighestBlockYAt(x, z) + 1, z);
							loc.getChunk().load(true);
							offlineDuringTele.put(Scatter.allPlayers.get(i), loc);
						}
					}
				}
			}
		}
		else
		{
			for(int i = 0; i < Scatter.allPlayers.size(); i++)
			{
				int x = 0, z = 0;
				Player p = Bukkit.getPlayer(Scatter.allPlayers.get(i));

				if(p != null)
				{
					if(p.getLocation().getBlockX() > currentBorderSize || p.getLocation().getBlockX() < -(currentBorderSize) || p.getLocation().getBlockX() > currentBorderSize || p.getLocation().getBlockZ() > currentBorderSize || p.getLocation().getBlockZ() < -(currentBorderSize))
					{
						x = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;
						z = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;

						Location loc = new Location(world, x, world.getHighestBlockYAt(x, z) + 1, z);
						loc.getChunk().load(true);
						teleLocations.put(p.getUniqueId(), loc);
					}
				}
				else
				{
					if(NPCEvent.npcList.containsKey(Scatter.allPlayers.get(i)))
					{
						OfflinePlayer offp = Bukkit.getOfflinePlayer(Scatter.allPlayers.get(i));

						if(NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockX() > currentBorderSize || NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockX() < -(currentBorderSize) ||
								NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockZ() > currentBorderSize || NPCEvent.npcList.get(offp.getUniqueId()).getStoredLocation().getBlockZ() < -(currentBorderSize))
						{
							x = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;
							z = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;

							Location loc = new Location(world, x, world.getHighestBlockYAt(x, z) + 1, z);
							loc.getChunk().load(true);
							offlineDuringTele.put(Scatter.allPlayers.get(i), loc);
						}
					}
				}
			}
		}

		teleportPlayers();
	}

	public void teleportPlayers()
	{
		if(!teleLocations.isEmpty())
		{
			new BukkitRunnable()
			{
				int count = 0;

				public void run()
				{
					if(count == Scatter.allPlayers.size())
					{
						setUpBorder(currentBorderSize, Bukkit.getWorld("uhc_world"));
						cancel();
					}
					else
					{
						Player p = Bukkit.getPlayer(Scatter.allPlayers.get(count));

						if(p != null)
						{
							if(teleLocations.containsKey(p.getUniqueId()))
							{
								teleLocations.get(p.getUniqueId()).setPitch(p.getLocation().getPitch());
								teleLocations.get(p.getUniqueId()).setYaw(p.getLocation().getYaw());
								p.teleport(teleLocations.get(p.getUniqueId()));

								for(Player player : Main.online.getOnlinePlayers())
								{
									player.hidePlayer(p);
								}

								for(Player player : Main.online.getOnlinePlayers())
								{
									player.showPlayer(p);
								}

								teleLocations.get(p.getUniqueId()).getChunk().load(true);
								teleLocations.remove(p.getUniqueId());
							}
						}

						count++;
					}
				}

			}.runTaskTimer(plugin, 0, 1);
		}
	}

	public void teleportPlayersTeams()
	{

	}
    
  	public void setBlocks(int x, int y, int z, World world)
	{
		net.minecraft.server.v1_7_R4.World nmsWorld = ((CraftWorld) world).getHandle();
		nmsWorld.setTypeAndData(x, y ,z, Blocks.BEDROCK, 0, 2);
	}
}