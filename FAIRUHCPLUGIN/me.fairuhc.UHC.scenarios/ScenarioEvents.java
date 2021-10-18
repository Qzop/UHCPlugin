package me.fairuhc.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import Events.Sidebar;
import me.fairuhc.UHC.Main;

public class ScenarioEvents implements Listener
{
	private final Main plugin;
	public static ItemStack ghead;
	
	public String owner = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Owner"+ ChatColor.GRAY + "]" + ChatColor.DARK_RED;
	public String admin = ChatColor.GRAY + "[" + ChatColor.RED + "Admin"+ ChatColor.GRAY + "]" + ChatColor.RED;
	public String srmod = ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + "Sr.Mod"+ ChatColor.GRAY + "]" + ChatColor.LIGHT_PURPLE;
	public String mod = ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Mod"+ ChatColor.GRAY + "]" + ChatColor.AQUA;
	public String trial = ChatColor.GRAY + "[" + ChatColor.YELLOW + "Trial"+ ChatColor.GRAY + "]" + ChatColor.YELLOW;
	public String winner = ChatColor.GRAY + "[" + ChatColor.AQUA + "Winner" + ChatColor.GRAY + "]";
	
	public static boolean cutclean;
	public static boolean timber;
	public static boolean hastey;
	public static boolean fireless;
	public static boolean nofall;
	public static boolean timebomb;
	public static boolean diamondless;
	public static boolean goldless;
	public static boolean veinminer;
	public static boolean goldenretriever;
	public static boolean noclean;
	public static boolean blooddia;
	public static boolean bloodgold;
	public static boolean chest;
	public static boolean safeloot;
	public static boolean triple;
	public static boolean doubl; //start from rodless and down
	public static boolean rodless;
	public static boolean bowless;
	public static boolean doublexp;
	public static boolean triplexp;
	public static boolean luckyleaves;
	public static boolean horseless;
	public static boolean skyhigh;
	public static boolean switcheroo;
	public static boolean backpacks;
	public static boolean hypixel;
	public static boolean noenchant;
	public static boolean infenchant;
	public static boolean cupid;
	public static boolean lafs;
	public static boolean anon;
	public static boolean supheroes;
	
	public ScenarioEvents(Main plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void golddialess(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block block = e.getBlock();
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && goldless == true)
		{
			if(block.getType().equals(Material.GOLD_ORE))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				p.giveExp(3);
			}
		}
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && diamondless == true)
		{
			if(block.getType().equals(Material.DIAMOND_ORE))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				p.giveExp(7);
			}
		}
	}
	
	@EventHandler
	public void cutclean(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block block = e.getBlock();
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && cutclean == true)
		{
			if(block.getType().equals(Material.IRON_ORE))
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				Inventory inv = p.getInventory();
				inv.addItem(new ItemStack(Material.IRON_INGOT));
				p.giveExp(3);
			}
			if(block.getType().equals(Material.GOLD_ORE) && goldless == false)
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				Inventory inv = p.getInventory();
				inv.addItem(new ItemStack(Material.GOLD_INGOT));
				p.giveExp(3);
			}
			else if(goldless == true)
			{
				if(block.getType().equals(Material.GOLD_ORE))
				{
					e.setCancelled(true);
					block.setType(Material.AIR);
					block.breakNaturally();
					p.giveExp(3);
				}
			}
			
			if(block.getType().equals(Material.DIAMOND_ORE) && diamondless == false)
			{
				e.setCancelled(true);
				block.setType(Material.AIR);
				block.breakNaturally();
				Inventory inv = p.getInventory();
				inv.addItem(new ItemStack(Material.DIAMOND));
				p.giveExp(7);
			}
			else if(diamondless == true)
			{
				if(block.getType().equals(Material.DIAMOND_ORE))
				{
					e.setCancelled(true);
					block.setType(Material.AIR);
					block.breakNaturally();
					p.giveExp(7);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void Timber(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && timber == true)
		{
			World tw = e.getPlayer().getWorld();
			int x = e.getBlock().getX();
			int y = e.getBlock().getY();
			int z = e.getBlock().getZ();
			
			if(e.getBlock().getTypeId() == 17)
			{	
				breakChain(tw, x, y, z, e);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private Integer gb(World world, int x, int y, int z, Block b)
	{
		return Integer.valueOf(world.getBlockTypeIdAt(x,y,z));
	}
	
	public void breakChain(World w, int x, int y, int z, BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Inventory inv = p.getInventory();
		
		e.setCancelled(true);
		b.setType(Material.AIR);
		w.getBlockAt(x, y, z).setType(Material.AIR);
		inv.addItem(new ItemStack(Material.LOG));

		
		if(gb(w, x, y + 1, z, b).intValue() == 17)
		{
			breakChain(w, x, y + 1, z, e);
		}
		else if(gb(w, x, y - 1, z, b).intValue() == 17)
		{
			breakChain(w, x, y - 1, z, e);
		}
	}
	
	@EventHandler
	public void fireless(EntityDamageEvent e)
	{
		if(fireless == true)
		{
			if(e.getCause().equals(DamageCause.FIRE_TICK) || e.getCause().equals(DamageCause.FIRE) || e.getCause().equals(DamageCause.LAVA))
			{
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void hasteyboys(PrepareItemCraftEvent e)
	{
		if(hastey == true)
		{
			ItemStack result = e.getInventory().getResult();
			
			if(result.getType() == Material.DIAMOND_PICKAXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.WOOD_PICKAXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.STONE_PICKAXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.IRON_PICKAXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.GOLD_PICKAXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			
			if(result.getType() == Material.WOOD_AXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.STONE_AXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.IRON_AXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.GOLD_AXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.DIAMOND_AXE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			
			if(result.getType() == Material.WOOD_SPADE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.STONE_SPADE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.IRON_SPADE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.GOLD_SPADE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
			else if(result.getType() == Material.DIAMOND_SPADE)
			{
				result.addEnchantment(Enchantment.DIG_SPEED, 3);
				result.addEnchantment(Enchantment.DURABILITY, 3);
			}
		}
	}
	
	@EventHandler
	public void noFall(EntityDamageEvent e)
	{
		if(nofall == true)
		{
			if(e.getCause().equals(DamageCause.FALL))
			{
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void timeBomb(PlayerDeathEvent e)
	{
		String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]";
		Player p = e.getEntity();
		Location loc = p.getLocation().clone();
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && timebomb == true)
		{
			Block b = loc.getBlock();
			
			b = b.getRelative(BlockFace.DOWN);
			b.setType(Material.CHEST);
			
			Chest c = (Chest) b.getState();
			
			b = b.getRelative(BlockFace.NORTH);
			b.setType(Material.CHEST);
			
			for(ItemStack item : e.getDrops())
			{
				if(item == null || item.getType() == Material.AIR)
				{
					continue;
				}
				
				c.getInventory().addItem(item);
			}
			
			e.getDrops().clear();
			
			final ArmorStand stand = p.getWorld().spawn(c.getLocation().clone().add(0.5, 1, 0), ArmorStand.class);
			
			stand.setCustomNameVisible(true);
			stand.setSmall(true);
			
			stand.setGravity(false);
			stand.setVisible(false);
			
			stand.setMarker(true);
			
			new BukkitRunnable() 
			{
				private int time = 30;
				
				public void run()
				{
					time--;
					
					if(time == 0)
					{
						plugin.broadcast(prefix + " " + ChatColor.GOLD + p.getName() + ChatColor.WHITE + " 's corpse has exploded!" );
						loc.getBlock().setType(Material.AIR);
						
						loc.getWorld().createExplosion(loc.getBlockX() + 0.5, loc.getBlockY() + 0.5, loc.getBlockZ() + 0.5, 5, false, true);
						loc.getWorld().strikeLightning(loc);
						
						stand.remove();
						cancel();
						return;
					}
					else if(time == 1)
					{
						stand.setCustomName("§4" + time + "s");
					}
					else if(time == 2)
					{
						stand.setCustomName("§c" + time + "s");
					}
					else if(time == 3)
					{
						stand.setCustomName("§6" + time + "s");
					}
					else if(time <= 15)
					{
						stand.setCustomName("§e" + time + "s");
					}
					else 
					{
						stand.setCustomName("§a" + time + "s");
					}
				}
			}.runTaskTimer(plugin, 0 , 20); 
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void veinMiner(BlockBreakEvent e)
	{
		@SuppressWarnings("unused")
		Block b = e.getBlock();
		Player p = e.getPlayer();
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && veinminer == true)
		{
			World tw = e.getPlayer().getWorld();
			int x = e.getBlock().getX();
			int y = e.getBlock().getY();
			int z = e.getBlock().getZ();
			
			if(e.getBlock().getTypeId() == 15)
			{	
				ironOre(tw, x, y, z, e);
			}
			else if(e.getBlock().getTypeId() == 56)
			{
				diaOre(tw, x, y, z, e);
			}
			else if(e.getBlock().getTypeId() == 14)
			{
				goldOre(tw, x, y, z, e);
			}
		}
	}
	
	public void ironOre(World w, int x, int y, int z, BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Inventory inv = p.getInventory();
		
		e.setCancelled(true);
		b.setType(Material.AIR);
		w.getBlockAt(x, y, z).setType(Material.AIR);
		inv.addItem(new ItemStack(Material.IRON_INGOT));
		p.giveExp(3);
		
		if(gb(w, x + 1, y + 1, z + 1, b).intValue() == 15)
		{
			ironOre(w, x + 1, y + 1, z + 1, e);
		}
		else if(gb(w, x - 1, y + 1, z + 1, b).intValue() == 15)
		{
			ironOre(w, x - 1, y + 1, z + 1, e);
		}
		else if(gb(w, x - 1, y - 1, z + 1, b).intValue() == 15)
		{
			ironOre(w, x - 1, y - 1, z + 1, e);
		}
		else if(gb(w, x - 1, y - 1, z - 1, b).intValue() == 15)
		{
			ironOre(w, x - 1, y - 1, z - 1, e);
		}
		else if(gb(w, x + 1, y - 1, z - 1, b).intValue() == 15)
		{
			ironOre(w, x + 1, y - 1, z - 1, e);
		}
		else if(gb(w, x + 1, y + 1, z - 1, b).intValue() == 15)
		{
			ironOre(w, x + 1, y + 1, z - 1, e);
		}
		else if(gb(w, x - 1, y + 1, z - 1, b).intValue() == 15)
		{
			ironOre(w, x - 1, y + 1, z - 1, e);
		}
		else if(gb(w, x + 1, y , z , b).intValue() == 15)
		{
			ironOre(w, x + 1, y , z , e);
		}
		else if(gb(w, x , y + 1, z , b).intValue() == 15)
		{
			ironOre(w, x , y + 1, z , e);
		}
		else if(gb(w, x , y , z + 1, b).intValue() == 15)
		{
			ironOre(w, x , y , z + 1, e);
		}
		else if(gb(w, x , y , z - 1, b).intValue() == 15)
		{
			ironOre(w, x, y , z - 1, e);
		}
		else if(gb(w, x , y - 1, z , b).intValue() == 15)
		{
			ironOre(w, x , y - 1, z, e);
		}
		else if(gb(w, x - 1, y , z , b).intValue() == 15)
		{
			ironOre(w, x - 1, y , z , e);
		}
	}
	
	public void diaOre(World w, int x, int y, int z, BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Inventory inv = p.getInventory();
		
		e.setCancelled(true);
		b.setType(Material.AIR);
		w.getBlockAt(x, y, z).setType(Material.AIR);
		inv.addItem(new ItemStack(Material.DIAMOND));
		p.giveExp(7);
		
		if(gb(w, x + 1, y + 1, z + 1, b).intValue() == 56)
		{
			diaOre(w, x + 1, y + 1, z + 1, e);
		}
		else if(gb(w, x - 1, y + 1, z + 1, b).intValue() == 56)
		{
			diaOre(w, x - 1, y + 1, z + 1, e);
		}
		else if(gb(w, x - 1, y - 1, z + 1, b).intValue() == 56)
		{
			diaOre(w, x - 1, y - 1, z + 1, e);
		}
		else if(gb(w, x - 1, y - 1, z - 1, b).intValue() == 56)
		{
			diaOre(w, x - 1, y - 1, z - 1, e);
		}
		else if(gb(w, x + 1, y - 1, z - 1, b).intValue() == 56)
		{
			diaOre(w, x + 1, y - 1, z - 1, e);
		}
		else if(gb(w, x + 1, y + 1, z - 1, b).intValue() == 56)
		{
			diaOre(w, x + 1, y + 1, z - 1, e);
		}
		else if(gb(w, x - 1, y + 1, z - 1, b).intValue() == 56)
		{
			diaOre(w, x - 1, y + 1, z - 1, e);
		}
		else if(gb(w, x + 1, y , z , b).intValue() == 56)
		{
			diaOre(w, x + 1, y , z , e);
		}
		else if(gb(w, x , y + 1, z , b).intValue() == 56)
		{
			diaOre(w, x , y + 1, z , e);
		}
		else if(gb(w, x , y , z + 1, b).intValue() == 56)
		{
			diaOre(w, x , y , z + 1, e);
		}
		else if(gb(w, x , y , z - 1, b).intValue() == 56)
		{
			diaOre(w, x, y , z - 1, e);
		}
		else if(gb(w, x , y - 1, z , b).intValue() == 56)
		{
			diaOre(w, x , y - 1, z, e);
		}
		else if(gb(w, x - 1, y , z , b).intValue() == 56)
		{
			diaOre(w, x - 1, y , z , e);
		}
	}
	
	public void goldOre(World w, int x, int y, int z, BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Inventory inv = p.getInventory();
		
		e.setCancelled(true);
		b.setType(Material.AIR);
		w.getBlockAt(x, y, z).setType(Material.AIR);
		inv.addItem(new ItemStack(Material.GOLD_INGOT));
		p.giveExp(3);
		
		if(gb(w, x + 1, y + 1, z + 1, b).intValue() == 14)
		{
			goldOre(w, x + 1, y + 1, z + 1, e);
		}
		else if(gb(w, x - 1, y + 1, z + 1, b).intValue() == 14)
		{
			goldOre(w, x - 1, y + 1, z + 1, e);
		}
		else if(gb(w, x - 1, y - 1, z + 1, b).intValue() == 14)
		{
			goldOre(w, x - 1, y - 1, z + 1, e);
		}
		else if(gb(w, x - 1, y - 1, z - 1, b).intValue() == 14)
		{
			goldOre(w, x - 1, y - 1, z - 1, e);
		}
		else if(gb(w, x + 1, y - 1, z - 1, b).intValue() == 14)
		{
			goldOre(w, x + 1, y - 1, z - 1, e);
		}
		else if(gb(w, x + 1, y + 1, z - 1, b).intValue() == 14)
		{
			goldOre(w, x + 1, y + 1, z - 1, e);
		}
		else if(gb(w, x - 1, y + 1, z - 1, b).intValue() == 14)
		{
			goldOre(w, x - 1, y + 1, z - 1, e);
		}
		else if(gb(w, x + 1, y , z , b).intValue() == 14)
		{
			goldOre(w, x + 1, y , z , e);
		}
		else if(gb(w, x , y + 1, z , b).intValue() == 14)
		{
			goldOre(w, x , y + 1, z , e);
		}
		else if(gb(w, x , y , z + 1, b).intValue() == 14)
		{
			goldOre(w, x , y , z + 1, e);
		}
		else if(gb(w, x , y , z - 1, b).intValue() == 14)
		{
			goldOre(w, x, y , z - 1, e);
		}
		else if(gb(w, x , y - 1, z , b).intValue() == 14)
		{
			goldOre(w, x , y - 1, z, e);
		}
		else if(gb(w, x - 1, y , z , b).intValue() == 14)
		{
			goldOre(w, x - 1, y , z , e);
		}
	}
	
	@EventHandler
	public void noClean(PlayerDeathEvent e)
	{
		Player p = e.getEntity();
		Player k = p.getKiller();
		
		if(noclean == true)
		{
			k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 1));
			k.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "NoClean" + ChatColor.GRAY + "]" + ChatColor.GREEN + "You are now invincible for 20 seconds.");
		}
	}
	
	@EventHandler
	public void bloodDiamond(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		
		if(blooddia == true && p.getGameMode().equals(GameMode.SURVIVAL))
		{
			if(b.getType().equals(Material.DIAMOND_ORE))
			{
				p.setHealth(p.getHealth() - 1);
			}
		}
	}
	
	@EventHandler
	public void bloodGold(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		
		if(bloodgold == true && p.getGameMode().equals(GameMode.SURVIVAL))
		{
			if(b.getType().equals(Material.GOLD_ORE))
			{
				p.setHealth(p.getHealth() - 1);
			}
		}
	}
	
	@EventHandler
	public void chest(PlayerDeathEvent e)
	{
		Player p = e.getEntity();
		Location loc = p.getLocation().clone();
		
		ItemStack playerhead = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta m = (SkullMeta) playerhead.getItemMeta();
		m.setOwner(p.getName());
		m.setDisplayName(ChatColor.GOLD + p.getName() + "'s Head");
		playerhead.setItemMeta(m);
		
		ItemStack head = new ItemStack(Material.GOLDEN_APPLE);;
		ItemMeta meta = head.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Golden Head");
		head.setItemMeta(meta);
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && chest == true)
		{
			Block b = loc.getBlock();
			
			b = b.getRelative(BlockFace.DOWN);
			b.setType(Material.CHEST);
			
			Chest c = (Chest) b.getState();
			
			b = b.getRelative(BlockFace.NORTH);
			b.setType(Material.CHEST);
			
			if(goldenretriever == false)
			{
				c.getInventory().addItem(new ItemStack(playerhead));
			}
			else
			{
				c.getInventory().addItem(new ItemStack(head));
			}
			
			for(ItemStack item : e.getDrops())
			{
				if(item == null || item.getType() == Material.AIR)
				{
					continue;
				}
				c.getInventory().addItem(item);
			}
			
			e.getDrops().clear();
		}
	}
	
	@EventHandler
	public void safeloot(PlayerDeathEvent e)
	{
		Player p = e.getEntity();
		Player k = p.getKiller();
		String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "Safeloot" + ChatColor.GRAY + "]";
		Location loc = p.getLocation().clone();
		
		if(p.getGameMode().equals(GameMode.SURVIVAL) && safeloot == true)
		{
			Block b = loc.getBlock();
			
			b = b.getRelative(BlockFace.DOWN);
			b.setType(Material.CHEST);
			
			Chest c = (Chest) b.getState();
		
			b = b.getRelative(BlockFace.NORTH);
			b.setType(Material.CHEST);
			
			for(ItemStack item : e.getDrops())
			{
				if(item == null || item.getType() == Material.AIR)
				{
					continue;
				}
				
				c.getInventory().addItem(item);
			}
			
			e.getDrops().clear();
			
			final ArmorStand stand = p.getWorld().spawn(c.getLocation().clone().add(0.5, 1, 0), ArmorStand.class);
			
			stand.setCustomNameVisible(true);
			stand.setSmall(true);
			
			stand.setGravity(false);
			stand.setVisible(false);
			
			stand.setMarker(true);
			
			new BukkitRunnable() 
			{
				private int time = 30;
				
				public void run()
				{
					time--;
					
					if(time == 0)
					{
						loc.getBlock().setType(Material.AIR);
						
						stand.remove();
						cancel();
						return;
					}
					else if(time > 0)
					{
						stand.setCustomName("§a" + k.getName() + "'s loot:" + time + "s");
					}
				}
			}.runTaskTimer(plugin, 0 , 20); 
		}
	}
	
	@EventHandler
	public void tripleores(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Inventory inv = p.getInventory();
		
		if(triple == true && p.getGameMode().equals(GameMode.SURVIVAL))
		{
			if(b.getType().equals(Material.DIAMOND_ORE))
			{
				e.setCancelled(true);
				b.setType(Material.AIR);
				b.breakNaturally();
				
				for(int i = 0; i < 3; i++)
				{
					inv.addItem(new ItemStack(Material.DIAMOND));
				}
				
				p.giveExp(7);
			}
			else if(b.getType().equals(Material.GOLD_ORE))
			{
				e.setCancelled(true);
				b.setType(Material.AIR);
				b.breakNaturally();
				
				for(int i = 0; i < 3; i++)
				{
					inv.addItem(new ItemStack(Material.GOLD_INGOT));
				}
				
				p.giveExp(3);
			}
			else if(b.getType().equals(Material.IRON_ORE))
			{
				e.setCancelled(true);
				b.setType(Material.AIR);
				b.breakNaturally();
				
				for(int i = 0; i < 3; i++)
				{
					inv.addItem(new ItemStack(Material.IRON_INGOT));
				}
				
				p.giveExp(3);
			}
		}
	}
	
	@EventHandler
	public void doubleores(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Inventory inv = p.getInventory();
		
		if(doubl == true && p.getGameMode().equals(GameMode.SURVIVAL))
		{
			if(b.getType().equals(Material.DIAMOND_ORE))
			{
				e.setCancelled(true);
				b.setType(Material.AIR);
				b.breakNaturally();
				
				for(int i = 0; i < 2; i++)
				{
					inv.addItem(new ItemStack(Material.DIAMOND));
				}
				
				p.giveExp(7);
			}
			else if(b.getType().equals(Material.GOLD_ORE))
			{
				e.setCancelled(true);
				b.setType(Material.AIR);
				b.breakNaturally();
				
				for(int i = 0; i < 2; i++)
				{
					inv.addItem(new ItemStack(Material.GOLD_INGOT));
				}
				
				p.giveExp(3);
			}
			else if(b.getType().equals(Material.IRON_ORE))
			{
				e.setCancelled(true);
				b.setType(Material.AIR);
				b.breakNaturally();
				
				for(int i = 0; i < 2; i++)
				{
					inv.addItem(new ItemStack(Material.IRON_INGOT));
				}
				
				p.giveExp(3);
			}
		}
	}
	
	@EventHandler
	public void anon(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		
			
		if(anon == true)
		{
			p.setDisplayName(ChatColor.MAGIC + p.getName());

			if(p.hasPermission("chat.owner"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.admin"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.srmod"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.mod"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.trial"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.default"))
			{
				e.setFormat(ChatColor.MAGIC + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
		}
		else if(anon == false)
		{
			p.setDisplayName(p.getName());
			
			if(p.hasPermission("chat.owner"))
			{
				e.setFormat(owner + " %s" + ChatColor.WHITE+ ": " + ChatColor.GOLD + "%s");
			}
			else if(p.hasPermission("chat.admin"))
			{
				e.setFormat(admin + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.srmod"))
			{
				e.setFormat(srmod + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.mod"))
			{
				e.setFormat(mod + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.trial"))
			{
				e.setFormat(trial + " %s" + ChatColor.WHITE + ": " + ChatColor.WHITE + "%s");
			}
			else if(p.hasPermission("chat.default"))
			{
				e.setFormat(ChatColor.WHITE + "%s: %s");
			}
		}
	}
	
	@EventHandler
	public void rodless(PrepareItemCraftEvent e)
	{
		if(rodless == true)
		{
			String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "Rodless" + ChatColor.GRAY + "]";
			
			Material itemType = e.getRecipe().getResult().getType();
			Byte itemData = e.getRecipe().getResult().getData().getData();
		
			if(itemType == Material.FISHING_ROD)
			{
				e.getInventory().setResult(new ItemStack(Material.AIR));
				for(HumanEntity he: e.getViewers())
				{
					if(he instanceof Player)
					{
						((Player)he).sendMessage(prefix + ChatColor.RED + " You cannot craft fishing rods!");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void bowless(PrepareItemCraftEvent e)
	{
		if(bowless == true)
		{
			String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "Bowless" + ChatColor.GRAY + "]";
			
			Material itemType = e.getRecipe().getResult().getType();
			Byte itemData = e.getRecipe().getResult().getData().getData();
		
			if(itemType == Material.BOW)
			{
				e.getInventory().setResult(new ItemStack(Material.AIR));
				for(HumanEntity he: e.getViewers())
				{
					if(he instanceof Player)
					{
						((Player)he).sendMessage(prefix + ChatColor.GOLD + " You cannot craft bows!");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onClick(EntityShootBowEvent e)
	{
		String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "Bowless" + ChatColor.GRAY + "]";
		
		if(e.getEntity().getType() == EntityType.PLAYER)
		{
			Player p = (Player) e.getEntity();
			
			if(bowless == true)
			{
				if(e.getEntityType() == EntityType.PLAYER)
				{ 
					e.setCancelled(true);
					
					p.sendMessage(prefix + ChatColor.GOLD + " You cannot use bows!");
				}
			}
		}
	}
	
	@EventHandler
	public void rod(PlayerFishEvent e)
	{
		Player p = e.getPlayer();
		String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "Rodless" + ChatColor.GRAY + "]";
		
		if(rodless == true)
		{
			if(e.getState() == State.FISHING)
			{
				e.setCancelled(true);
			
				p.sendMessage(prefix + ChatColor.GOLD + " You cannot use fishing rods!");
			}
		}
	}
	
	public void superheroes()
	{	
		if(supheroes == true)
		{
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(p.getGameMode() == GameMode.SURVIVAL)
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0), true);
				}
			}
		}
	}
}
