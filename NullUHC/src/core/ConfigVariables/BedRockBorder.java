package core.ConfigVariables;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.mainPackage.Main;
import net.minecraft.server.v1_7_R4.BlockActionData;
import net.minecraft.server.v1_7_R4.Blocks;
import net.minecraft.server.v1_7_R4.IBlockAccess;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.block.CraftBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class BedRockBorder implements Listener
{
    Main plugin = Main.getPlugin(Main.class);
	public static HashMap<UUID, Location> teleLocationsFFA = new HashMap<UUID, Location>();
	public static ArrayList<UUID> offlineDuringTele = new ArrayList<UUID>();
    public static int currentBorderSize = ConfigInventory.borderSize;
	public static boolean first = false;
	public static boolean second = false;
	public static boolean third = false;
	public static boolean fourth = false;

    public void setUpBorder(int border, World world)
    {
    	getFirstWallBlocks(border, world);
		getSecondWallBlocks(border, world);
		getThirdWallBlocks(border, world);
		getFourthWallBlocks(border, world);
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
					first = true;
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
					second = true;
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
					third = true;
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
					fourth = true;
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

		if(ConfigInventory.teamSize == 1)
		{
			getTeleportLocationsFFA();
		}
		else
		{
			getTeleportLocationsTeams();
		}
	}

	public void getTeleportLocationsFFA()
	{
		World world = Bukkit.getWorld("uhc_world");

		if(currentBorderSize > 500)
		{
			new BukkitRunnable()
			{
				int count = 0;
				int x, z;

				public void run()
				{
					if(count == Scatter.allPlayers.size())
					{
						teleportPlayersFFA();
						cancel();
					}
					else
					{
						Player p = Bukkit.getPlayer(Scatter.allPlayers.get(count));

						if(p != null)
						{
							if(p.getLocation().getBlockX() > currentBorderSize)
							{
								if(p.getLocation().getBlockZ() > currentBorderSize)
								{
									x = currentBorderSize;
									z = currentBorderSize;
									Location loc = new Location(world, x - 1, world.getHighestBlockYAt(x - 1, z - 1) + 2, z - 1);
									Location check = new Location(world, x - 1, world.getHighestBlockYAt(x - 1, z - 1) - 1, z - 1);
									Block block = check.getBlock();
									int move = 2;

									while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
									{
										loc = new Location(world, (x - move), world.getHighestBlockYAt(x - move, z - move) + 2, z - move);
										check = new Location(world, (x - move), world.getHighestBlockYAt(x - move, z - move) - 1, z - move);
										block = check.getBlock();
										move++;
									}

									loc.getChunk().load(true);
									teleLocationsFFA.put(Scatter.allPlayers.get(count), loc);
								}
								else if(p.getLocation().getBlockZ() < -(currentBorderSize))
								{
									z = -(currentBorderSize);
									x = currentBorderSize;
									Location loc = new Location(world, x - 1, world.getHighestBlockYAt(currentBorderSize - 1, z + 1) + 2,  z + 1);
									Location check = new Location(world, x - 1, world.getHighestBlockYAt(currentBorderSize - 1, z + 1) - 1, z + 1);
									Block block = check.getBlock();
									int move = 2;

									while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
									{
										loc = new Location(world, x - move, world.getHighestBlockYAt(x - move, z + move) + 2,  z + move);
										check = new Location(world, x - move, world.getHighestBlockYAt(x - move, -z + move) - 1,  z + move);
										block = check.getBlock();
										move++;
									}

									loc.getChunk().load(true);
									teleLocationsFFA.put(Scatter.allPlayers.get(count), loc);
								}
								else
								{
									z = p.getLocation().getBlockZ();
									x = currentBorderSize;
									Location loc = new Location(world, x - 1, world.getHighestBlockYAt(x , z) + 2, z);
									Location check = new Location(world, x - 1, world.getHighestBlockYAt(x, z) - 1, z);
									Block block = check.getBlock();
									int move = 2;

									while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
									{
										loc = new Location(world, (x - move), world.getHighestBlockYAt(x - move, z) + 2, z);
										check = new Location(world, (x - move), world.getHighestBlockYAt(x - move, z) - 1, z);
										block = check.getBlock();
										move++;
									}

									loc.getChunk().load(true);
									teleLocationsFFA.put(Scatter.allPlayers.get(count), loc);
								}
							}
							else
							{
								if(p.getLocation().getBlockX() < -(currentBorderSize))
								{
									if(p.getLocation().getBlockZ() > currentBorderSize)
									{
										x = -(currentBorderSize);
										z = currentBorderSize;
										Location loc = new Location(world, x + 1, world.getHighestBlockYAt(x + 1, z - 1) + 2, z - 1);
										Location check = new Location(world, x + 1, world.getHighestBlockYAt(x + 1, z - 1) - 1, z - 1);
										Block block = check.getBlock();
										int move = 2;

										while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
										{
											loc = new Location(world, x + move, world.getHighestBlockYAt(x + move, z - move) + 2, z - move);
											check = new Location(world, x + move, world.getHighestBlockYAt(x + move, z - move) - 1, z - move);
											block = check.getBlock();
											move++;
										}

										loc.getChunk().load(true);
										teleLocationsFFA.put(Scatter.allPlayers.get(count), loc);
									}
									else if(p.getLocation().getBlockZ() < -(currentBorderSize))
									{
										x = -(currentBorderSize);
										z = -(currentBorderSize);
										Location loc = new Location(world, x + 1, world.getHighestBlockYAt(x + 1, z + 1) + 2, z + 1);
										Location check = new Location(world, x + 1, world.getHighestBlockYAt(x + 1, z + 1) - 1, z + 1);
										Block block = check.getBlock();
										int move = 2;

										while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
										{
											loc = new Location(world, x + move, world.getHighestBlockYAt(x + move, z + move) + 2, z + move);
											check = new Location(world, x + move, world.getHighestBlockYAt(x + move, z + move) - 1, z + move);
											block = check.getBlock();
											move++;
										}

										loc.getChunk().load(true);
										teleLocationsFFA.put(Scatter.allPlayers.get(count), loc);
									}
									else
									{
										x = -(currentBorderSize);
										z = p.getLocation().getBlockZ();
										Location loc = new Location(world, x + 1, world.getHighestBlockYAt(x + 1, z) + 2, z);
										Location check = new Location(world, x + 1, world.getHighestBlockYAt(x + 1, z) - 1, z);
										Block block = check.getBlock();
										int move = 2;

										while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
										{
											loc = new Location(world, x + move, world.getHighestBlockYAt(x + move, z) + 2, z);
											check = new Location(world, x + move, world.getHighestBlockYAt(x + move, z) - 1, z);
											block = check.getBlock();
											move++;
										}

										loc.getChunk().load(true);
										teleLocationsFFA.put(Scatter.allPlayers.get(count), loc);
									}
								}
								else
								{
									if(p.getLocation().getBlockZ() > currentBorderSize)
									{
										x = p.getLocation().getBlockX();
										z = currentBorderSize;
										Location loc = new Location(world, x, world.getHighestBlockYAt(x, z - 1) + 2, z - 1);
										Location check = new Location(world, x, world.getHighestBlockYAt(x, z - 1) - 1, z - 1);
										Block block = check.getBlock();
										int move = 2;

										while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
										{
											loc = new Location(world, x, world.getHighestBlockYAt(x, z - move) + 2, z - move);
											check = new Location(world, x, world.getHighestBlockYAt(x, z - move) - 1, z - move);
											block = check.getBlock();
											move++;
										}

										loc.getChunk().load(true);
										teleLocationsFFA.put(Scatter.allPlayers.get(count), loc);
									}
									else if(p.getLocation().getBlockZ() < -(currentBorderSize))
									{
										x = p.getLocation().getBlockX();
										z = -(currentBorderSize);
										Location loc = new Location(world, x, world.getHighestBlockYAt(x, z + 1) + 2, z + 1);
										Location check = new Location(world, x, world.getHighestBlockYAt(x, z + 1) - 1, z + 1);
										Block block = check.getBlock();
										int move = 2;

										while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
										{
											loc = new Location(world, x, world.getHighestBlockYAt(x, z + move) + 2, z + move);
											check = new Location(world, x, world.getHighestBlockYAt(x, z + move) - 1, z + move);
											block = check.getBlock();
											move++;
										}

										loc.getChunk().load(true);
										teleLocationsFFA.put(Scatter.allPlayers.get(count), loc);
									}
								}
							}
						}
						else
						{
							// do something about offline players in here
						}

						count++;
					}
				}

			}.runTaskTimer(plugin, 0, 1);
		}
		else
		{
			new BukkitRunnable()
			{
				int randomX, randomZ, count = 0;

				public void run()
				{
					randomX = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;
					randomZ = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;

					Location teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
					Location checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
					Block block = checkloc.getBlock();

					if(count == Scatter.allPlayers.size())
					{
						teleportPlayersFFA();
						cancel();
					}
					else
					{
						Player p = Bukkit.getPlayer(Scatter.allPlayers.get(count));

						if(p != null)
						{
							if(p.getLocation().getBlockX() > currentBorderSize)
							{
								while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
								{
									randomX = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;
									randomZ = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;

									teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
									checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
									block = checkloc.getBlock();
								}

								teleloc.getChunk().load(true);
								teleLocationsFFA.put(Scatter.allPlayers.get(count), teleloc);
							}
							else if(p.getLocation().getBlockX() < -(currentBorderSize))
							{
								while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
								{
									randomX = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;
									randomZ = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;

									teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
									checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
									block = checkloc.getBlock();
								}

								teleloc.getChunk().load(true);
								teleLocationsFFA.put(Scatter.allPlayers.get(count), teleloc);
							}
							else if(p.getLocation().getBlockZ() < -(currentBorderSize))
							{
								while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
								{
									randomX = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;
									randomZ = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;

									teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
									checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
									block = checkloc.getBlock();
								}

								teleloc.getChunk().load(true);
								teleLocationsFFA.put(Scatter.allPlayers.get(count), teleloc);
							}
							else if(p.getLocation().getBlockX() > currentBorderSize)
							{
								while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
								{
									randomX = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;
									randomZ = new Random().nextInt(currentBorderSize + currentBorderSize) - currentBorderSize;

									teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
									checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
									block = checkloc.getBlock();
								}

								teleloc.getChunk().load(true);
								world.save();
								teleLocationsFFA.put(Scatter.allPlayers.get(count), teleloc);
							}
						}
						else
						{
							// do something about offline players here
						}

						count++;
					}
				}

			}.runTaskTimer(plugin, 0, 1);
		}
	}

	public void getTeleportLocationsTeams()
	{
		if(currentBorderSize > 500)
		{
			new BukkitRunnable()
			{
				public void run()
				{

				}

			}.runTaskTimer(plugin, 0, 10);
		}
		else
		{
			new BukkitRunnable()
			{
				public void run()
				{

				}

			}.runTaskTimer(plugin, 0, 10);
		}
	}

	public void teleportPlayersFFA()
	{
		if(!teleLocationsFFA.isEmpty())
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
							if(teleLocationsFFA.containsKey(p.getUniqueId()))
							{
								teleLocationsFFA.get(p.getUniqueId()).getChunk().load(true);
								teleLocationsFFA.get(p.getUniqueId()).setPitch(p.getLocation().getPitch());
								teleLocationsFFA.get(p.getUniqueId()).setYaw(p.getLocation().getYaw());
								p.teleport(teleLocationsFFA.get(p.getUniqueId()));
								teleLocationsFFA.remove(p.getUniqueId());
							}
						}
						else
						{
							// do something about an offline player here
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