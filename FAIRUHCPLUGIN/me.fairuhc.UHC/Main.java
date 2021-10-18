package me.fairuhc.UHC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.command.defaults.GameRuleCommand;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import Events.AppleRate;
import Events.ChatEvent;
import Events.Practice;
import Events.Reportevent;
import Events.Sidebar;
import Events.Timer;
import Events.WeatherEvent;
import Events.configadmin;
import Events.damageEvent;
import Events.hostitemsevent;
import Events.scenariosadmin;
import me.fairuhc.UHC.DeathManager.FFAWinner;
import me.fairuhc.UHC.DeathManager.Spectator;
import me.fairuhc.UHC.DeathManager.TeamsWinner;
import me.fairuhc.UHC.Scatter.FFAScatter;
import me.fairuhc.UHC.Scatter.TeamScatter;
import me.fairuhc.UHC.World.ChunkLoad;
import me.fairuhc.UHC.World.worldGen;
import me.fairuhc.UHC.scenarios.Anonymous;
import me.fairuhc.UHC.scenarios.BloodDiamond;
import me.fairuhc.UHC.scenarios.Bowless;
import me.fairuhc.UHC.scenarios.Chest;
import me.fairuhc.UHC.scenarios.Cutclean;
import me.fairuhc.UHC.scenarios.DoubleOres;
import me.fairuhc.UHC.scenarios.Fireless;
import me.fairuhc.UHC.scenarios.GoldlessandDiamondless;
import me.fairuhc.UHC.scenarios.HasteyBoys;
import me.fairuhc.UHC.scenarios.NoClean;
import me.fairuhc.UHC.scenarios.NoFall;
import me.fairuhc.UHC.scenarios.RodLess;
import me.fairuhc.UHC.scenarios.SafeLoot;
import me.fairuhc.UHC.scenarios.ScenarioEvents;
import me.fairuhc.UHC.scenarios.SuperHeros;
import me.fairuhc.UHC.scenarios.Timber;
import me.fairuhc.UHC.scenarios.TimeBomb;
import me.fairuhc.UHC.scenarios.TripleOres;
import me.fairuhc.UHC.scenarios.Veinminer;
import me.fairuhc.UHC.stats.GapsEaten;
import me.fairuhc.UHC.stats.MySQLDeaths;
import me.fairuhc.UHC.stats.MySQLGaps;
import me.fairuhc.UHC.stats.MySQLGoldenHeads;
import me.fairuhc.UHC.stats.MySQLKills;
import me.fairuhc.UHC.stats.MySQLNether;
import me.fairuhc.UHC.stats.MySQLOresMined;
import me.fairuhc.UHC.stats.MySQLPlayTime;
import me.fairuhc.UHC.stats.MySQLPotions;
import me.fairuhc.UHC.stats.MySQLWins;
import me.fairuhc.UHC.stats.MySQLkdr;
import me.fairuhc.UHC.stats.NetherEvent;
import me.fairuhc.UHC.stats.OresMined;
import me.fairuhc.UHC.stats.PotionEvent;
import me.fairuhc.UHC.stats.StatsEvent;
import me.fairuhc.UHC.stats.Time;
import me.fairuhc.UHC.teams.CreateTeam;
import me.fairuhc.UHC.teams.TeamManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin implements Listener
{
	@SuppressWarnings("unused")
	private Connection connection;
	public String host, database, username, password;
	public int port;
	public static Main plugin;
	public static ArrayList<Team> teams = new ArrayList<>();
	public World uhcw, uhcp, uhcl;
	public WorldCreator uhcworld, uhcprac, uhclobby;
	public MySQLKills kills;
	public MySQLDeaths deaths;
	public MySQLGaps gaps;
	public MySQLkdr kdr;
	public MySQLGoldenHeads heads;
	public MySQLNether nether;
	public MySQLOresMined ores;
	public MySQLPlayTime time;
	public MySQLPotions pot;
	public MySQLWins win;
	public Time t;
	public worldGen gen;
	public ChunkLoad load;
	public static boolean pregen = false;
	
	public void onEnable()
	{
		if(FileUtils.getFile("uhc_world").exists() == false)
		{
			pregen = true;
		}
		
		Commands command = new Commands(this);
		GoldenHead head = new GoldenHead(this);
		
		CreateTeam.playerTeams.clear();
		TeamManager.hasinvite.clear();
		TeamsWinner.deadplayers.clear();
		TeamManager.teamsize.clear();
		Host.host.clear();
		
		kills = new MySQLKills();
		deaths = new MySQLDeaths();
		gaps = new MySQLGaps();
		kdr = new MySQLkdr();
		heads = new MySQLGoldenHeads();
		nether = new MySQLNether();
		ores = new MySQLOresMined();
		time = new MySQLPlayTime();
		t = new Time();
		win = new MySQLWins();
		pot = new MySQLPotions();
		gen = new worldGen(this);
		load = new ChunkLoad();

		head.Recipe();
		
		
		plugin = this;
		
		createWorld();
		
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new scenariosadmin(), this);
		this.getServer().getPluginManager().registerEvents(new GoldenHead(this), this);
		this.getServer().getPluginManager().registerEvents(new configadmin(this), this);
		this.getServer().getPluginManager().registerEvents(new TeamManager(this),this);
		this.getServer().getPluginManager().registerEvents(new FFAWinner(this), this);
		this.getServer().getPluginManager().registerEvents(new TeamsWinner(this), this);
		this.getServer().getPluginManager().registerEvents(new Nether(this), this);
		this.getServer().getPluginManager().registerEvents(new Kills(), this);
		this.getServer().getPluginManager().registerEvents(new Host(), this);
		this.getServer().getPluginManager().registerEvents(new Gamemanager(this), this);
		this.getServer().getPluginManager().registerEvents(new damageEvent(), this);
		this.getServer().getPluginManager().registerEvents(new Sidebar(this), this);
		this.getServer().getPluginManager().registerEvents(new Practice(), this);
		this.getServer().getPluginManager().registerEvents(new Kills(), this);
		this.getServer().getPluginManager().registerEvents(new OresMined(), this);
		this.getServer().getPluginManager().registerEvents(new GapsEaten(), this);
		this.getServer().getPluginManager().registerEvents(new NetherEvent(), this);
		this.getServer().getPluginManager().registerEvents(new PotionEvent(), this);
		this.getServer().getPluginManager().registerEvents(new StatsEvent(), this);
		this.getServer().getPluginManager().registerEvents(new Reportevent(this), this);
		this.getServer().getPluginManager().registerEvents(new Mod(), this);
		this.getServer().getPluginManager().registerEvents(new LobbyItems(), this);
		this.getServer().getPluginManager().registerEvents(new HostModItems(), this);
		this.getServer().getPluginManager().registerEvents(new hostitemsevent(), this);
		this.getServer().getPluginManager().registerEvents(new FFAScatter(this), this);
		this.getServer().getPluginManager().registerEvents(new ChunkLoad(), this);
		this.getServer().getPluginManager().registerEvents(new AppleRate(), this);
		this.getServer().getPluginManager().registerEvents(new WeatherEvent(), this);
		this.getServer().getPluginManager().registerEvents(new Timber(), this);
		this.getServer().getPluginManager().registerEvents(new Cutclean(), this);
		this.getServer().getPluginManager().registerEvents(new Anonymous(), this);
		this.getServer().getPluginManager().registerEvents(new BloodDiamond(), this);
		this.getServer().getPluginManager().registerEvents(new Bowless(), this);
		this.getServer().getPluginManager().registerEvents(new Chest(), this);
		this.getServer().getPluginManager().registerEvents(new DoubleOres(), this);
		this.getServer().getPluginManager().registerEvents(new Fireless(), this);
		this.getServer().getPluginManager().registerEvents(new GoldlessandDiamondless(), this);
		this.getServer().getPluginManager().registerEvents(new HasteyBoys(), this);
		this.getServer().getPluginManager().registerEvents(new NoClean(), this);
		this.getServer().getPluginManager().registerEvents(new NoFall(), this);
		this.getServer().getPluginManager().registerEvents(new RodLess(), this);
		this.getServer().getPluginManager().registerEvents(new SafeLoot(), this);
		this.getServer().getPluginManager().registerEvents(new SuperHeros(), this);
		this.getServer().getPluginManager().registerEvents(new TimeBomb(), this);
		this.getServer().getPluginManager().registerEvents(new TripleOres(), this);
		this.getServer().getPluginManager().registerEvents(new Veinminer(), this);
		this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
		
		getCommand(command.mod).setExecutor(command);
		getCommand(command.loadchunks).setExecutor(command);
		getCommand(command.gt).setExecutor(command);
		getCommand(command.prac).setExecutor(command);
		getCommand(command.regeneratemap).setExecutor(command);
		getCommand(command.removehost).setExecutor(command);
		getCommand(command.host).setExecutor(command);
		getCommand(command.numteams).setExecutor(command);
		getCommand(command.topkill).setExecutor(command);
		getCommand(command.scenarios).setExecutor(command);
		getCommand("scen").setExecutor(command);
		getCommand(command.scenadmin).setExecutor(command);
		getCommand(command.uhc).setExecutor(command);
		getCommand(command.conadmin).setExecutor(command);
		getCommand(command.config).setExecutor(command);
		getCommand(command.team).setExecutor(command);
		getCommand(command.health).setExecutor(command);
		getCommand(command.coords).setExecutor(command);
		getCommand(command.reloadconfig).setExecutor(command);
		getCommand(command.stats).setExecutor(command);
		getCommand(command.helpop).setExecutor(command);
		getCommand(command.report).setExecutor(command);
		getCommand(command.BORDER).setExecutor(command);
		getCommand(command.respawn).setExecutor(command);
		getCommand(command.heal).setExecutor(command);
		getCommand(command.give).setExecutor(command);
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\n\nUHC Plugin enabled.\n\n\n");
		
		loadConfig();
		SQLSetup();
		
		if(pregen == true)
		{
			load.getChunksToLoad();
		}
	}
	
	public void loadConfig()
	{
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public void SQLSetup()
	{
		host = getConfig().getString("Host: ");
		database = getConfig().getString("Database: ");
		username = getConfig().getString("Username: ");
		password = getConfig().getString("Password: ");
		port = Integer.parseInt(getConfig().getString("Port: "));
		
		try
		{
			synchronized(this)
			{
				if(getConnection() != null && !getConnection().isClosed())
				{
					return;
				}
				
				Class.forName("com.mysql.jdbc.Driver");
				setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));
				
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nMySQL Database connected.\n\n");
				
				kills.createTableKills();
				deaths.createTableDeaths();
				gaps.createTableGapsEaten();
				kdr.createTableKDR();
				heads.createTableHeadsEaten();
				nether.createTableEntries();
				ores.createTableOres();
				time.createTableTime();
				pot.createTablePotionsConsumed();
				win.createTableWins();
			}
		}
		catch(SQLException e)
		{
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "\n\nMySQL Database not connected.\n\n");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "\n\nMySQL Database not connected.\n\n");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}
	
	public void onDisable()
	{
		CreateTeam.playerTeams.clear();
		TeamManager.hasinvite.clear();
		TeamsWinner.deadplayers.clear();
		FFAScatter.ffa.clear();
		FFAScatter.scattered.clear();
		TeamManager.teamsize.clear();
		Kills.topkill.clear();
		
		border.eachshrink = 0;
		border.bordercreated = false;
		
		worldGen gen = new worldGen(plugin);
		
		if(Gamemanager.ended == true)
		{
			Bukkit.unloadWorld("uhc_world", false);
			gen.deleteWorld();
			ChunkLoad.arechunksloaded = false;
			ChunkLoad.loadedChunks.clear();
		}
		
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\n\nUHC Plugin disabled\n\n\n");
	}
	
	public void createWorld()
	{
		ChunkLoad c = new ChunkLoad();
		
		uhcworld = new WorldCreator("uhc_world");
		uhcworld.type(WorldType.CUSTOMIZED);
		uhcworld.generateStructures(true);
		uhcw = uhcworld.createWorld();
		
		uhcprac = new WorldCreator("uhc_practice");
		uhcprac.type(WorldType.FLAT);
		uhcprac.generateStructures(true);
		uhcp = uhcprac.createWorld();
		
		uhclobby = new WorldCreator("uhc_lobby");
		uhclobby.type(WorldType.FLAT);
		uhclobby.generateStructures(false);
		uhcl = uhclobby.createWorld();
	}
	
	public void broadcast(String msg)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.sendMessage(msg);
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		CreateTeam ct = new CreateTeam(plugin);
		TeamManager tm = new TeamManager(plugin);
		Sidebar sb = new Sidebar(plugin);
        Player player = e.getPlayer();
        World world = Bukkit.getWorld("uhc_lobby");
        String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
        
        sb.TabList(player);
        
        kills.createPlayer(player);
		deaths.createPlayer(player);
		gaps.createPlayer(player);
		kdr.createPlayer(player);
		heads.createPlayer(player);
		nether.createPlayer(player);
		ores.createPlayer(player);
		time.createPlayer(player);
		pot.createPlayer(player);
		win.createPlayer(player);
		
		t.Timer(player);
		player.setHealth(player.getHealth() + (20.0 - player.getHealth()));
		
		if(Gamemanager.started == false)
		{
			String pregenprefix = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Pregen" + ChatColor.GRAY + " » ";
			
			player.sendMessage(ChatColor.GRAY + "§m----------------------------------\n" 
					+ ChatColor.AQUA + ChatColor.BOLD + "                     Fair" + ChatColor.WHITE + ChatColor.BOLD + "UHC");  
					
					player.sendMessage("  \n"
					+ ChatColor.AQUA + ChatColor.BOLD + "Commands " + ChatColor.GRAY + "» " + ChatColor.WHITE + "/helpop, /scenarios, /config");
					player.sendMessage("\n" + ChatColor.AQUA + ChatColor.BOLD + "Discord " + ChatColor.GRAY + "» " + ChatColor.WHITE + "https://discord.gg/M4xBST3");	
					player.sendMessage(ChatColor.GRAY + "§m----------------------------------");
					
			if(pregen == true)
			{
				if(player.hasPermission("uhc.pregen"))
				{
					player.sendMessage(pregenprefix + ChatColor.RED + "Pregen has started. This may take a few minutes." );
				}
			}
			
			if(ChunkLoad.arechunksloaded == true)
			{
				if(player.hasPermission("uhc.pregen"))
				{
					player.sendMessage(pregenprefix + ChatColor.GREEN + "Pregen has finished." );
				}
			}
					
			if((FFAScatter.scatter == true || TeamScatter.scatter == true) && (!Host.host.contains(player.getUniqueId()) || !Mod.mod.contains(player.getUniqueId())))
			{
				player.kickPlayer(ChatColor.RED + "Scatter period has started. Please rejoin in a few minutes.");
			}
			else
			{
				for(Player p : Bukkit.getOnlinePlayers())
				{
					if(!p.hasPermission("gamemode.bypass"))
					{
						p.setGameMode(GameMode.SURVIVAL);
					}

					p.removePotionEffect(PotionEffectType.BLINDNESS);
					p.removePotionEffect(PotionEffectType.SLOW);
					p.removePotionEffect(PotionEffectType.ABSORPTION);
					p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					p.removePotionEffect(PotionEffectType.CONFUSION);
					p.removePotionEffect(PotionEffectType.FAST_DIGGING);
					p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
					p.removePotionEffect(PotionEffectType.HARM);
					p.removePotionEffect(PotionEffectType.HEAL);
					p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
					p.removePotionEffect(PotionEffectType.HUNGER);
					p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					p.removePotionEffect(PotionEffectType.INVISIBILITY);
					p.removePotionEffect(PotionEffectType.JUMP);
					p.removePotionEffect(PotionEffectType.NIGHT_VISION);
					p.removePotionEffect(PotionEffectType.POISON);
					p.removePotionEffect(PotionEffectType.REGENERATION);
					p.removePotionEffect(PotionEffectType.SATURATION);
					p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
					p.removePotionEffect(PotionEffectType.SPEED);
					p.removePotionEffect(PotionEffectType.WATER_BREATHING);
					p.removePotionEffect(PotionEffectType.WEAKNESS);
					p.removePotionEffect(PotionEffectType.WITHER);
				}
				
				if(player.hasPermission("tab.owner"))
				{
					e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.DARK_RED + player.getDisplayName());
				}
				else if(player.hasPermission("tab.admin"))
				{
					e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.RED + player.getDisplayName());
				}
				else if(player.hasPermission("tab.srmod"))
				{
					e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE + player.getDisplayName());
				}
				else if(player.hasPermission("tab.mod"))
				{
					e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.AQUA+ player.getDisplayName());
				}
				else if(player.hasPermission("tab.trial"))
				{
					e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.YELLOW + player.getDisplayName());
				}
				else if(player.hasPermission("tab.default"))
				{
					e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.WHITE+ player.getDisplayName());
				}
			}
			
			sb.LobbyScoreboard(player);
			player.setFoodLevel(20);
			player.setLevel(0);
			player.setExp(0);
			player.teleport(world.getSpawnLocation().add(0, 100, 0));
		}
		if(Gamemanager.started == true)
		{
			if(player.hasPermission("tab.owner"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.DARK_RED + player.getDisplayName());
			}
			else if(player.hasPermission("tab.admin"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.RED + player.getDisplayName());
			}
			else if(player.hasPermission("tab.srmod"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE + player.getDisplayName());
			}
			else if(player.hasPermission("tab.mod"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.AQUA+ player.getDisplayName());
			}
			else if(player.hasPermission("tab.trial"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.YELLOW + player.getDisplayName());
			}
			else if(player.hasPermission("tab.default"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.WHITE + player.getDisplayName());
			}
			
			if(Gamemanager.teamsize == 1)
			{
				FFAScatter ffascat = new FFAScatter(plugin);
				sb.FFAGameScoreboard(player);
				
				if(!FFAScatter.joinedgame.contains(player.getUniqueId()) && Timer.gametime <= (Gamemanager.latescattertime * 60) && (!Host.host.contains(player.getUniqueId()) || !Mod.mod.contains(player.getUniqueId())))
				{
					ffascat.latescatter(player);
				}
				else if(!FFAScatter.joinedgame.contains(player.getUniqueId()) && Timer.gametime > (Gamemanager.latescattertime * 60) && (!Host.host.contains(player.getUniqueId()) || !Mod.mod.contains(player.getUniqueId())))
				{
					player.kickPlayer(ChatColor.RED + "The late scatter period has ended.");
				}
			}
			else
			{
				sb.TeamGameScoreboard(player);
				ArrayList<UUID> teammembers = new ArrayList<UUID>();
				
				if(Gamemanager.started == true && tm.hasTeam(e.getPlayer()) == false && (!Host.host.contains(player.getUniqueId()) || !Mod.mod.contains(player.getUniqueId())) && Timer.gametime <= (Gamemanager.latescattertime * 60))
				{
					TeamScatter ts = new TeamScatter(plugin);
					
					ct.latescatterteam(player, teammembers);
					ts.latescatter(player);
				}
				else if(Gamemanager.started == true && tm.hasTeam(e.getPlayer()) == false && (!Host.host.contains(player.getUniqueId()) || !Mod.mod.contains(player.getUniqueId())) && Timer.gametime > (Gamemanager.latescattertime * 60))
				{
					player.kickPlayer(ChatColor.RED + "The late scatter period has ended.");
				}
			}
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e)
	{
		Player player = e.getPlayer();
		
		if(player.hasPermission("tab.owner"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.DARK_RED + player.getDisplayName());
		}
		else if(player.hasPermission("tab.admin"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.RED + player.getDisplayName());
		}
		else if(player.hasPermission("tab.srmod"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.DARK_PURPLE + player.getDisplayName());
		}
		else if(player.hasPermission("tab.mod"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.AQUA+ player.getDisplayName());
		}
		else if(player.hasPermission("tab.trial"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.YELLOW + player.getDisplayName());
		}
		else if(player.hasPermission("tab.default"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.WHITE + player.getDisplayName());
		}
	}
	
	@EventHandler
	public void dmgEvent(EntityDamageEvent e)
	{
		ArrayList<DamageCause> dmgcause = new ArrayList<DamageCause>();
		
		dmgcause.add(DamageCause.FALL);
		dmgcause.add(DamageCause.BLOCK_EXPLOSION);
		dmgcause.add(DamageCause.DROWNING);
		dmgcause.add(DamageCause.FALLING_BLOCK);
		dmgcause.add(DamageCause.FIRE);
		dmgcause.add(DamageCause.FIRE_TICK);
		dmgcause.add(DamageCause.LAVA);
		dmgcause.add(DamageCause.MELTING);
		dmgcause.add(DamageCause.MAGIC);
		dmgcause.add(DamageCause.POISON);
		
		if(e.getEntity() instanceof Player)
		{
			if(e.getEntity().getWorld().getName().equals("uhc_lobby"))
			{
				e.setCancelled(true);
			}
			else if(e.getEntity().getWorld().getName().equals("uhc_practice"))
			{
				if(e.getCause() == DamageCause.FALL)
				{
					e.setCancelled(true);
				}
				else
				{
					e.setCancelled(false);
				}
			}
			
			if(Timer.gperiod == false && e.getEntity().getWorld().getName().equals("uhc_world"))
			{
				if(FFAScatter.scatter == true || TeamScatter.scatter == true)
				{
					e.setCancelled(true);
				}
				else
				{
					if(dmgcause.contains(e.getCause()))
					{
						e.setCancelled(false);
					}
					else
					{
						e.setCancelled(true);
					}
				}
			}
			else if(Timer.gperiod == true && e.getEntity().getWorld().getName().equals("uhc_world"))
			{
				e.setCancelled(false);
			}
			
		}
	}
	
	@EventHandler
	public void passiveMobs(EntityTargetEvent e)
	{
		if(Config.mobs == true)
		{
			if(e.getEntity() instanceof Monster)
			{
				if(e.getTarget() instanceof Player)
				{
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onDamageByMob(EntityDamageByEntityEvent e)
	{
		if(Config.mobs == true && e.getEntity() instanceof Player)
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void hunger(FoodLevelChangeEvent e)
	{
		Player p = (Player) e.getEntity();
		
		if(Gamemanager.started == false)
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void breakblock(BlockBreakEvent e)
	{
		if(Gamemanager.started == false)
		{
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(p.getGameMode().equals(GameMode.CREATIVE))
				{
					e.setCancelled(false);
				}
				else
				{
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void LobbyItemDrop(PlayerDropItemEvent e)
	{
		Player p = e.getPlayer();
		
		if(Gamemanager.started == true || p.getGameMode().equals(GameMode.CREATIVE))
		{
			e.setCancelled(false);
		}
		else if(Gamemanager.started == false && !p.getGameMode().equals(GameMode.CREATIVE))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onMobSpawn(EntitySpawnEvent e)
	{
		Entity entity = e.getEntity();
		World world = entity.getWorld();
		
		if(world.getTime() > 0)
		{
			world.setTime(0);
		}
		
		if(entity.getWorld().getName().equals("uhc_lobby") || entity.getWorld().getName().equals("uhc_practice"))
		{
			if(!(entity instanceof Player))
			{
				entity.remove();
				
			}
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		
		p.getLocation().getChunk().load();
		
		if(Gamemanager.started == true && p.getWorld().getName().equals("uhc_world") && !(Host.host.contains(p.getUniqueId()) || Mod.mod.contains(p.getUniqueId())))
		{
			if(p.getLocation().getBlockX() > border.bordersize)
			{
				p.teleport(p.getWorld().getSpawnLocation().add(p.getLocation().getBlockX() - 1, p.getLocation().getBlockY(), p.getLocation().getBlockZ()));
				p.sendMessage(ChatColor.RED + "You cannot pass the border!");
			}
			else if(p.getLocation().getBlockZ() > border.bordersize)
			{
				p.teleport(p.getWorld().getSpawnLocation().add(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ() - 1));
				p.sendMessage(ChatColor.RED + "You cannot pass the border!");
			}
			else if(p.getLocation().getBlockZ() > border.bordersize && p.getLocation().getBlockX() > border.bordersize)
			{
				p.teleport(p.getWorld().getSpawnLocation().add(p.getLocation().getBlockX() - 1, p.getLocation().getBlockY(), p.getLocation().getBlockZ() - 1));
				p.sendMessage(ChatColor.RED + "You cannot pass the border!");
			}
			
			if(p.getLocation().getBlockX() < -border.bordersize)
			{
				p.teleport(p.getWorld().getSpawnLocation().add(p.getLocation().getBlockX() + 1, p.getLocation().getBlockY(), p.getLocation().getBlockZ()));
				p.sendMessage(ChatColor.RED + "You cannot pass the border!");
			}
			else if(p.getLocation().getBlockZ() < -border.bordersize)
			{
				p.teleport(p.getWorld().getSpawnLocation().add(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ() + 1));
				p.sendMessage(ChatColor.RED + "You cannot pass the border!");
			}
			else if(p.getLocation().getBlockZ() < -border.bordersize && p.getLocation().getBlockX() < -border.bordersize)
			{
				p.teleport(p.getWorld().getSpawnLocation().add(p.getLocation().getBlockX() + 1, p.getLocation().getBlockY(), p.getLocation().getBlockZ() + 1));
				p.sendMessage(ChatColor.RED + "You cannot pass the border!");
			}
		}
	}
	
	@EventHandler
	public void regen(EntityRegainHealthEvent e)
	{
		e.setCancelled(true);
	}	
	
	@EventHandler
	public void pickup(PlayerPickupItemEvent e)
	{
		Player p = e.getPlayer();
		
		if((Spectator.spectators.contains(p.getUniqueId()) || Host.host.contains(p.getUniqueId()) || Mod.mod.contains(p.getUniqueId())))
		{
			if(p.getGameMode() == GameMode.CREATIVE)
			{
				e.setCancelled(false);
			}
			else
			{
				e.setCancelled(true);
			}
		}
	}
}