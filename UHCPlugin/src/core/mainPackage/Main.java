package core.mainPackage;

import core.Arena.ArenaKills;
import core.Chat.ChatEvent;
import core.Config.ConfigEvent;
import core.Config.ConfigInventory;
import core.ConfigVariables.AppleRate;
import core.ConfigVariables.Horses;
import core.ConfigVariables.Portals;
import core.ConfigVariables.SpeedStrength;
import core.Events.Events;
import core.Kills.PlayerKills;
import core.Kills.Spectator;
import core.Kills.TeamKills;
import core.Scatter.ChatEventScatter;
import core.Scatter.Scatter;
import core.Scoreboard.Game;
import core.Scoreboard.Lobby;
import core.Scoreboard.Time;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener
{
	Lobby lob;
	Scatter scat;
	Game game;
	PlayerKills kills;

	public void onEnable()
	{
		Commands command = new Commands();
		lob = new Lobby();
		scat = new Scatter();
		game = new Game();
		kills = new PlayerKills();
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "    UHC CORE ENABLED :D    ");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");

		createArena();

		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new ConfigEvent(), this);
		this.getServer().getPluginManager().registerEvents(new AppleRate(), this);
		this.getServer().getPluginManager().registerEvents(new Horses(), this);
		this.getServer().getPluginManager().registerEvents(new SpeedStrength(), this);
		this.getServer().getPluginManager().registerEvents(new Portals(), this);
		this.getServer().getPluginManager().registerEvents(new ChatEventScatter(), this);
		this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerKills(), this);
		this.getServer().getPluginManager().registerEvents(new TeamKills(), this);
		this.getServer().getPluginManager().registerEvents(new ArenaKills(), this);
		this.getServer().getPluginManager().registerEvents(new Events(), this);
		this.getServer().getPluginManager().registerEvents(new Spectator(), this);
	
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
	}
	
	public void onDisable()
	{
		Bukkit.unloadWorld("uhcworld", false);
		
		try
		{
			FileUtils.forceDelete(new File("uhcworld"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "    UHC CORE DISABLED D:    ");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
	}

	public void createArena()
	{
		World world = Bukkit.getWorld("Arena");

		if(world == null)
		{
			WorldCreator wc = new WorldCreator("Arena");

			wc.environment(World.Environment.NORMAL);
			wc.type(WorldType.FLAT);

			wc.createWorld();
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();

		if(!Scatter.started && !Commands.scatter)
		{
			World world = Bukkit.getWorld("world");

			if(p.hasPermission("chat.owner"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.DARK_RED + ChatColor.BOLD +  " " + p.getDisplayName());
			}
			else if(p.hasPermission("chat.dev"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.GOLD + ChatColor.BOLD + " " + p.getDisplayName());
			}
			else if(p.hasPermission("chat.admin"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.RED + " " + p.getDisplayName());
			}
			else if(p.hasPermission("chat.srmod"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.DARK_BLUE + " " + p.getDisplayName());
			}
			else if(p.hasPermission("chat.mod"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.DARK_GREEN + " " + p.getDisplayName());
			}
			else if(p.hasPermission("chat.trial"))
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.GREEN + " " + p.getDisplayName());
			}
			else
			{
				e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.WHITE + " " + p.getDisplayName());
			}

			p.teleport(world.getSpawnLocation());
			p.removePotionEffect(PotionEffectType.BLINDNESS);
			p.removePotionEffect(PotionEffectType.JUMP);
			p.removePotionEffect(PotionEffectType.SPEED);
			p.removePotionEffect(PotionEffectType.SLOW);
			p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			p.removePotionEffect(PotionEffectType.ABSORPTION);
			p.removePotionEffect(PotionEffectType.CONFUSION);
			p.removePotionEffect(PotionEffectType.FAST_DIGGING);
			p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
			p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			p.removePotionEffect(PotionEffectType.HARM);
			p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
			p.removePotionEffect(PotionEffectType.HEAL);
			p.removePotionEffect(PotionEffectType.INVISIBILITY);
			p.removePotionEffect(PotionEffectType.POISON);
			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
			p.removePotionEffect(PotionEffectType.WATER_BREATHING);
			p.removePotionEffect(PotionEffectType.WEAKNESS);
			p.removePotionEffect(PotionEffectType.REGENERATION);
			p.removePotionEffect(PotionEffectType.HUNGER);
			p.removePotionEffect(PotionEffectType.WITHER);
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
			p.removePotionEffect(PotionEffectType.SATURATION);
			
			p.getInventory().clear();
			
			p.setHealth(p.getHealth() + (20.0 - p.getHealth()));
			p.setFoodLevel(20);
			p.setLevel(0);
			p.setExp(0);
			
			p.setGameMode(GameMode.SURVIVAL);

			lob.setLobby(p);
		}
		else if(Commands.scatter)
		{
			p.kickPlayer(ChatColor.RED + "Scatter has started, you cannot join now.");
		}
		else if(Scatter.started)
		{
			if(PlayerKills.dead.contains(p.getUniqueId()))
			{
				if(p.hasPermission("death.bypass"))
				{
					World world = Bukkit.getWorld("world");
					p.teleport(world.getSpawnLocation());
				}
				else
				{
					p.kickPlayer(ChatColor.RED + "You died, unlucky :(");
				}
			}
			else
			{
				if(Scatter.allPlayers.contains(p.getUniqueId()))
				{
					if(ConfigInventory.teamSize > 1)
					{
						game.setGameTeams(p);
					}
					else
					{
						game.setGameFFA(p);
					}
				}
				else
				{
					if(Time.minutes < ConfigInventory.latescatter)
					{
						p.removePotionEffect(PotionEffectType.BLINDNESS);
						p.removePotionEffect(PotionEffectType.JUMP);
						p.removePotionEffect(PotionEffectType.SPEED);
						p.removePotionEffect(PotionEffectType.SLOW);
						p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
						p.removePotionEffect(PotionEffectType.ABSORPTION);
						p.removePotionEffect(PotionEffectType.CONFUSION);
						p.removePotionEffect(PotionEffectType.FAST_DIGGING);
						p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
						p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
						p.removePotionEffect(PotionEffectType.HARM);
						p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
						p.removePotionEffect(PotionEffectType.HEAL);
						p.removePotionEffect(PotionEffectType.INVISIBILITY);
						p.removePotionEffect(PotionEffectType.POISON);
						p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
						p.removePotionEffect(PotionEffectType.WATER_BREATHING);
						p.removePotionEffect(PotionEffectType.WEAKNESS);
						p.removePotionEffect(PotionEffectType.REGENERATION);
						p.removePotionEffect(PotionEffectType.HUNGER);
						p.removePotionEffect(PotionEffectType.WITHER);
						p.removePotionEffect(PotionEffectType.NIGHT_VISION);
						p.removePotionEffect(PotionEffectType.SATURATION);
						
						p.getInventory().clear();
						
						p.setHealth(p.getHealth() + (20.0 - p.getHealth()));
						p.setFoodLevel(20);
						p.setLevel(0);
						p.setExp(0);
						
						p.setGameMode(GameMode.SURVIVAL);
						
						if(ConfigInventory.teamSize > 1)
						{
							scat.lateScatterTeams(p);
							game.setGameTeams(p);
							kills.latePlayer(p);
						}
						else
						{
							scat.lateScatterFFA(p);
							game.setGameFFA(p);
							kills.latePlayer(p);
						}
					}
					else
					{
						p.kickPlayer(ChatColor.RED + "Late Scatter period has ended.");
					}
				}
			}
		}
	}
	
	public void onLeave(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		
		if(p.hasPermission("chat.owner"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.DARK_RED + ChatColor.BOLD +  " " + p.getDisplayName());
		}
		else if(p.hasPermission("chat.dev"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.GOLD + ChatColor.BOLD + " " + p.getDisplayName());
		}
		else if(p.hasPermission("chat.admin"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.RED + " " + p.getDisplayName());
		}
		else if(p.hasPermission("chat.srmod"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.DARK_BLUE + " " + p.getDisplayName());
		}
		else if(p.hasPermission("chat.mod"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.DARK_GREEN + " " + p.getDisplayName());
		}
		else if(p.hasPermission("chat.trial"))
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.GREEN + " " + p.getDisplayName());
		}
		else
		{
			e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.WHITE + " " + p.getDisplayName());
		}
	}
}
