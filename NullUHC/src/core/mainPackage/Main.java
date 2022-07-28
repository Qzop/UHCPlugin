package core.mainPackage;

import core.Alerts.PvP;
import core.Alerts.ReportInv;
import core.Alerts.ReportInvEvent;
import core.Alerts.Xray;
import core.Arena.ArenaKills;
import core.Chat.ChatEvent;
import core.ChunkLoad.Chunks;
import core.Config.ConfigEvent;
import core.ConfigVariables.*;
import core.Events.*;
import core.GoldenHead.GoldenHead;
import core.HostsMods.HostModsItems;
import core.Kills.PlayerKills;
import core.Kills.Spectator;
import core.Kills.TeamKills;
import core.Scatter.ChatEventScatter;
import core.Scatter.Scatter;
import core.Scenarios.*;
import core.ScenariosInventory.ScenInvEvent;
import core.ScenariosInventory.ScenariosInventory;
import core.Scoreboard.Game;
import core.Scoreboard.Lobby;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import core.Scoreboard.ScoreboardTeams;
import net.minecraft.util.org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.entity.NPC;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	Lobby lob;
	Scatter scat;
	Game game;
	PlayerKills kills;
	GoldenHead head;
	public static OnlinePlayers online;
	public static boolean loading = false;
	ScoreboardTeams teams;

	public void onEnable()
	{
		Commands command = new Commands();
		lob = new Lobby();
		scat = new Scatter();
		game = new Game();
		kills = new PlayerKills();
		head = new GoldenHead();
		online = new OnlinePlayers();
		teams = new ScoreboardTeams();
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "    UHC CORE ENABLED :D    ");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");

		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new ConfigEvent(), this);
		this.getServer().getPluginManager().registerEvents(new AppleRate(), this);
		this.getServer().getPluginManager().registerEvents(new Horses(), this);
		this.getServer().getPluginManager().registerEvents(new SpeedStrength(), this);
		this.getServer().getPluginManager().registerEvents(new Portals(), this);
		this.getServer().getPluginManager().registerEvents(new ChatEventScatter(), this);
		this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
		this.getServer().getPluginManager().registerEvents(new TeamKills(), this);
		this.getServer().getPluginManager().registerEvents(new ArenaKills(), this);
		this.getServer().getPluginManager().registerEvents(new FoodEvent(), this);
		this.getServer().getPluginManager().registerEvents(new Spectator(), this);
		this.getServer().getPluginManager().registerEvents(new BreakEvent(), this);
		this.getServer().getPluginManager().registerEvents(new HostModsItems(), this);
		this.getServer().getPluginManager().registerEvents(new GoldenHead(), this);
		this.getServer().getPluginManager().registerEvents(new PickUpItems(), this);
		this.getServer().getPluginManager().registerEvents(new PassiveMobs(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerKills(), this);
		this.getServer().getPluginManager().registerEvents(new Xray(), this);
		this.getServer().getPluginManager().registerEvents(new PvP(), this);
		this.getServer().getPluginManager().registerEvents(new ItemDespawnEvent(), this);
		this.getServer().getPluginManager().registerEvents(new DamageEvent(), this);
		this.getServer().getPluginManager().registerEvents(new Join(), this);
		this.getServer().getPluginManager().registerEvents(new Quit(), this);
		this.getServer().getPluginManager().registerEvents(new NPCEvent(), this);
		this.getServer().getPluginManager().registerEvents(new EnderPearlDamage(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerTeleport(), this);
		this.getServer().getPluginManager().registerEvents(new ScenInvEvent(), this);
		this.getServer().getPluginManager().registerEvents(new CutClean(), this);
		this.getServer().getPluginManager().registerEvents(new HasteyBoys(), this);
		this.getServer().getPluginManager().registerEvents(new LuckyOres(), this);
		this.getServer().getPluginManager().registerEvents(new Timber(), this);
		this.getServer().getPluginManager().registerEvents(new Timebomb(), this);
		this.getServer().getPluginManager().registerEvents(new Superheroes(), this);
		this.getServer().getPluginManager().registerEvents(new ReportInvEvent(), this);
	
		getCommand(command.uhc).setExecutor(command);
		getCommand(command.config).setExecutor(command);
		getCommand(command.host).setExecutor(command);
		getCommand(command.mod).setExecutor(command);
		getCommand(command.team).setExecutor(command);
		getCommand(command.test).setExecutor(command);
		getCommand(command.help).setExecutor(command);
		getCommand(command.tele).setExecutor(command);
		getCommand(command.arena).setExecutor(command);
		getCommand(command.border).setExecutor(command);
		getCommand(command.gm).setExecutor(command);
		getCommand(command.respawn).setExecutor(command);
		getCommand(command.world).setExecutor(command);
		getCommand(command.spawn).setExecutor(command);
		getCommand(command.heal).setExecutor(command);
		getCommand(command.feed).setExecutor(command);
		getCommand(command.alerts).setExecutor(command);
		getCommand(command.bright).setExecutor(command);
		getCommand(command.scenarios).setExecutor(command);
		getCommand(command.regenerate).setExecutor(command);
		getCommand(command.report).setExecutor(command);
		getCommand(command.bugreport).setExecutor(command);
		getCommand(command.spawn).setExecutor(command);
		getCommand(command.discord).setExecutor(command);
		getCommand(command.ping).setExecutor(command);
		getCommand(command.specchat).setExecutor(command);
		getCommand(command.staffchat).setExecutor(command);

		World world = Bukkit.getWorld("world");
		world.setGameRuleValue("doDaylightCycle", "false");
		world.setGameRuleValue("doMobSpawning", "false");
		world.setTime(0);
		world.setDifficulty(Difficulty.PEACEFUL);

		createArenaUHCWorld();
		head.createGoldenHead();
		teams.setColors();
	}
	
	public void onDisable()
	{
		teams.removeTeams();

		if(loading)
		{
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb fill cancel");
		}

		if(Scatter.ended)
		{
			Bukkit.unloadWorld("uhc_world", false);
			Bukkit.unloadWorld("uhc_nether", false);
			
			try
			{
				FileUtils.forceDelete(new File("uhc_world"));
				FileUtils.forceDelete(new File("uhc_nether"));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}

		if(!NPCEvent.npcList.isEmpty())
		{
			for(UUID keys : NPCEvent.npcList.keySet())
			{
				NPCEvent.npcList.get(keys).destroy();
			}
		}
		
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "    UHC CORE DISABLED D:    ");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
	}

	public void createArenaUHCWorld()
	{
		Chunks chunks = new Chunks();
		
		if(!FileUtils.getFile("Arena").exists())
		{
			World world = Bukkit.createWorld(new WorldCreator("Arena").environment(World.Environment.NORMAL).type(WorldType.FLAT).generateStructures(false));
			world.setGameRuleValue("doDaylightCycle", "false");
			world.setTime(12000);
			world.setGameRuleValue("naturalRegeneration", "false");
			world.setGameRuleValue("doMobSpawning", "false");
			world.setGameRuleValue("doWeatherCycle", "false");
		}

		if(!FileUtils.getFile("uhc_nether").exists())
		{
			World world = Bukkit.createWorld(new WorldCreator("uhc_nether").environment(World.Environment.NETHER));
		}
		
		if(!FileUtils.getFile("uhc_world").exists())
		{
			loading = true;

			World world = Bukkit.createWorld(new WorldCreator("uhc_world").environment(World.Environment.NORMAL).type(WorldType.NORMAL));
			world.setGameRuleValue("doDaylightCycle", "false");
			world.setTime(12000);
			world.setGameRuleValue("naturalRegeneration", "false");
			world.setGameRuleValue("doWeatherCycle", "false");
			
			chunks.checkZeroZero();
			
			if(!Chunks.check)
			{
				chunks.loadUHCWorldChunks();
			}
		}
	}
}
