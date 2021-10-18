package Events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Host;
import me.fairuhc.UHC.Kills;
import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.border;
import me.fairuhc.UHC.Scatter.FFAScatter;
import me.fairuhc.UHC.Scatter.TeamScatter;
import me.fairuhc.UHC.World.ChunkLoad;
import me.fairuhc.UHC.World.worldGen;
import me.fairuhc.UHC.teams.CreateTeam;
import me.fairuhc.UHC.teams.TeamManager;

public class Sidebar implements Listener
{
	public Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();;
	public static int time = 0;
	public TeamManager tm;
	public static ArrayList<Team> teams = new ArrayList<Team>();
	private String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
	private static int gp = 0;
	private static int fh = 0;
	Kills k = new Kills();
	
	Main plugin;
	
	public worldGen gen;
	
	public Sidebar(Main plugin)
	{
		this.plugin = plugin;
		tm = new TeamManager(plugin);
		gen = new worldGen(plugin);
	}
	
	public void TabList(Player p)
	{
		Objective obj = scoreboard.registerNewObjective("Dummy", "Tab");
		obj.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				Team owner = scoreboard.getTeam("Owner");
				Team admin = scoreboard.getTeam("Admin");
				Team srmod = scoreboard.getTeam("SrMod");
				Team mod = scoreboard.getTeam("Mod");
				Team trial = scoreboard.getTeam("Trial");
				Team def = scoreboard.getTeam("d");
				
				if(owner == null)
				{
					owner = scoreboard.registerNewTeam("Owner");
					owner.setPrefix(ChatColor.DARK_RED + "");
				}
				else if(admin == null)
				{
					admin = scoreboard.registerNewTeam("Admin");
					admin.setPrefix(ChatColor.RED + "");
				}
				else if(srmod == null)
				{
					srmod = scoreboard.registerNewTeam("SrMod");
					srmod.setPrefix(ChatColor.LIGHT_PURPLE + "");
				}
				else if(mod == null)
				{
					mod = scoreboard.registerNewTeam("Mod");
					mod.setPrefix(ChatColor.AQUA + "");
				}
				else if(trial == null)
				{
					trial = scoreboard.registerNewTeam("Trial");
					trial.setPrefix(ChatColor.YELLOW + "");
				}
				else if(def == null)
				{
					def = scoreboard.registerNewTeam("d");
					def.setPrefix(ChatColor.WHITE + "");
				}
				
				for(Player player : Bukkit.getServer().getOnlinePlayers())
				{
					if(player.hasPermission("tab.owner"))
					{
						owner.addEntry(player.getDisplayName());
						owner.setPrefix(ChatColor.DARK_RED + "");
					}
					else if(player.hasPermission("tab.admin"))
					{
						admin.addEntry(player.getDisplayName());
						admin.setPrefix(ChatColor.RED + "");
					}
					else if(player.hasPermission("tab.srmod"))
					{
						srmod.addEntry(player.getDisplayName());
					}
					else if(player.hasPermission("tab.mod"))
					{
						mod.addEntry(player.getDisplayName());
					}
					else if(player.hasPermission("tab.trial"))
					{
						trial.addEntry(player.getDisplayName());
					}
					else if(player.hasPermission("tab.default"))
					{
						def.addEntry(player.getDisplayName());
					}
				}
			}
			
		}.runTaskTimer(plugin, 0, 20);
	}
	
	public void LobbyScoreboard(Player p)
	{
		Objective obj = scoreboard.registerNewObjective("FairUHC", "LobbyScoreboard");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC");
	
		Team online = scoreboard.registerNewTeam("onlinecounter");
		online.addEntry(ChatColor.AQUA + "Online " + ChatColor.GRAY + "» ");
		Team bordersize = scoreboard.registerNewTeam("border");
		bordersize.addEntry(ChatColor.AQUA + "Border " + ChatColor.GRAY + "» ");
		Team host = scoreboard.registerNewTeam("host");
		host.addEntry(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ");
		Team size = scoreboard.registerNewTeam("size");
		size.addEntry(ChatColor.AQUA + "Size " + ChatColor.GRAY + "» ");
		Team rank = scoreboard.registerNewTeam("rank");
		rank.addEntry(ChatColor.AQUA + "Rank " + ChatColor.GRAY + "» ");
		
		obj.getScore(ChatColor.WHITE + "§m------------------").setScore(12);
		obj.getScore(ChatColor.AQUA + "Online " + ChatColor.GRAY + "» ").setScore(11);
		obj.getScore("").setScore(10);
		obj.getScore(ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ").setScore(9);
		obj.getScore(ChatColor.AQUA + "Rank " + ChatColor.GRAY + "» ").setScore(8);
		obj.getScore(" ").setScore(7);
		obj.getScore(ChatColor.AQUA + "Border " + ChatColor.GRAY + "» ").setScore(6);
		obj.getScore(ChatColor.AQUA + "Size " + ChatColor.GRAY + "» ").setScore(5);
		obj.getScore("  ").setScore(4);
		obj.getScore(ChatColor.AQUA + "Commands" + ChatColor.GRAY + ": ").setScore(3);
		obj.getScore(ChatColor.WHITE + "/scen /config /helpop").setScore(2);
		obj.getScore(ChatColor.WHITE + "§m-------------------").setScore(1);
		obj.getScore(ChatColor.AQUA + "fairuhc.us").setScore(0);

		new BukkitRunnable()
		{
			public void run()
			{
				online.setSuffix(ChatColor.WHITE + "" + Bukkit.getOnlinePlayers().size());
				bordersize.setSuffix(ChatColor.WHITE + "" + border.bordersize);
				
				if(Gamemanager.teamsize == 1)
				{
					size.setSuffix(ChatColor.WHITE + "FFA");
				}
				else
				{
					size.setSuffix(ChatColor.WHITE + "To" + Gamemanager.teamsize);
				}
				
				if(Host.host.isEmpty())
				{
					host.setSuffix(ChatColor.RED + "None");
				}
				else
				{
					String score = ChatColor.AQUA + "Host " + ChatColor.GRAY + "» ";
					
					if(Bukkit.getPlayer(Host.host.get(0)).getDisplayName().length() + score.length() <= 24)
					{
						host.setSuffix(ChatColor.RED + "" + Bukkit.getPlayer(Host.host.get(0)).getDisplayName());
					}
					else if(Bukkit.getPlayer(Host.host.get(0)).getDisplayName().length() + score.length() > 24)
					{
						int difference = (Bukkit.getPlayer(Host.host.get(0)).getDisplayName().length() + score.length()) - 24;
						
						String h = Bukkit.getPlayer(Host.host.get(0)).getDisplayName().substring(0,(16 - difference));
						
						host.setSuffix(ChatColor.RED + h);
					}
				}
				
				if(p.hasPermission("rank.owner"))
				{
					rank.setSuffix(ChatColor.DARK_RED + "Owner");
				}
				else if(p.hasPermission("rank.admin"))
				{
					rank.setSuffix(ChatColor.RED + "Admin");
				}
				else if(p.hasPermission("rank.srmod"))
				{
					rank.setSuffix(ChatColor.LIGHT_PURPLE + "Sr. Mod");
				}
				else if(p.hasPermission("rank.mod"))
				{
					rank.setSuffix(ChatColor.AQUA + "Mod");
				}
				else if(p.hasPermission("rank.trial"))
				{
					rank.setSuffix(ChatColor.YELLOW + "Trial");
				}
				else if(p.hasPermission("rank.default"))
				{
					rank.setSuffix(ChatColor.WHITE+ "Default");
				}
				
				if(Gamemanager.started == false)
				{
					if(Practice.prac.contains(p.getUniqueId()))
					{
						PracticeScoreboard(p);
						cancel();
						return;
					}
					
					if((FFAScatter.scatter == true || TeamScatter.scatter == true))
					{
						if(Gamemanager.teamsize == 1)
						{
							FFAScatterScoreboard(p);
							cancel();
							return;
						}
						else if(Gamemanager.teamsize > 1)
						{
							TeamScatterScoreboard(p);
							cancel();
							return;
						}
					}
				}
				else if(Gamemanager.started == true)
				{
					if(Gamemanager.teamsize == 1)
					{
						FFAGameScoreboard(p);
						cancel();
						return;
					}
					else
					{
						TeamGameScoreboard(p);
						cancel();
						return;
					}
				}
			}
			
		}.runTaskTimer(plugin, 0, 1);
		
		p.setScoreboard(scoreboard);
	}
	
	public void PracticeScoreboard(Player p)
	{
		ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
	    scoreboard = scoreboardManager.getNewScoreboard();
	        
		Objective objective = scoreboard.registerNewObjective("Reddit", "fairuc");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC");
		
		Team online = scoreboard.registerNewTeam("onlinecounter");
		online.addEntry(ChatColor.AQUA + "Online " + ChatColor.GRAY + "» "); 
		Team kill = scoreboard.registerNewTeam("kills");
		kill.addEntry(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ");
		
		objective.getScore(ChatColor.WHITE + "§m------------------").setScore(5);
		objective.getScore(ChatColor.AQUA + "Online " + ChatColor.GRAY + "» ").setScore(4);
		objective.getScore("").setScore(3);
		objective.getScore(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ").setScore(2);
		objective.getScore(ChatColor.WHITE + "§m-------------------").setScore(1);
		objective.getScore(ChatColor.AQUA + "fairuhc.us").setScore(0);
		
		new BukkitRunnable()
		{
			public void run()
			{
				online.setSuffix(ChatColor.WHITE + "" + Bukkit.getOnlinePlayers().size());
				
				if(!Practice.prac.contains(p.getUniqueId()) && Gamemanager.started == false && (FFAScatter.scatter == false || TeamScatter.scatter == true))
				{
					LobbyScoreboard(p);
					cancel();
					return;
				}
				
				if((FFAScatter.scatter == true || TeamScatter.scatter == true))
				{
					for(Player player : Bukkit.getOnlinePlayers())
					{
						if(Practice.prac.contains(player.getUniqueId()))
						{
							Practice.prac.remove(player.getUniqueId());
						}
					}
					
					if(Gamemanager.teamsize == 1)
					{
						FFAScatterScoreboard(p);
						cancel();
						return;
					}
					else if(Gamemanager.teamsize > 1)
					{
						TeamScatterScoreboard(p);
						cancel();
						return;
					}
				}
			}
			
		}.runTaskTimer(plugin, 0, 1);
		
		p.setScoreboard(scoreboard);
	}
	
	public void FFAScatterScoreboard(Player p)
	{
		ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
	    scoreboard = scoreboardManager.getNewScoreboard();
		
		Objective obj = scoreboard.registerNewObjective("Scatter", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC");
		
		Team numplayers = scoreboard.registerNewTeam("numplayers");
		numplayers.addEntry(ChatColor.AQUA + "Scattered " + ChatColor.GRAY + "» ");
		
		obj.getScore(ChatColor.WHITE + "§m------------------").setScore(5);
		obj.getScore("").setScore(4);
		obj.getScore(ChatColor.AQUA + "Scattered " + ChatColor.GRAY + "» ").setScore(3);
		obj.getScore(" ").setScore(2);
		obj.getScore(ChatColor.WHITE + "§m-------------------").setScore(1);
		obj.getScore(ChatColor.AQUA + "fairuhc.us").setScore(0);
		
		new BukkitRunnable()
		{
			public void run()
			{
				if((FFAScatter.scatter == true || TeamScatter.scatter == true))
				{
					numplayers.setSuffix(ChatColor.WHITE + "" + FFAScatter.scattered.size());
				}
				else if(Gamemanager.started == true)
				{
					FFAGameScoreboard(p);
					cancel();
					return;
				}
			}
			
		}.runTaskTimer(plugin, 0, 1);
		
		p.setScoreboard(scoreboard);
	}
	
	public void TeamScatterScoreboard(Player p)
	{
		ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
	    scoreboard = scoreboardManager.getNewScoreboard();
		
		Objective obj = scoreboard.registerNewObjective("TeamScatter", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC");
		
		Team numplayers = scoreboard.registerNewTeam("numplayers");
		numplayers.addEntry(ChatColor.AQUA + "Teams Scattered " + ChatColor.GRAY + "» ");
		
		obj.getScore(ChatColor.WHITE + "§m------------------").setScore(5);
		obj.getScore("").setScore(4);
		obj.getScore(ChatColor.AQUA + "Teams Scattered " + ChatColor.GRAY + "» ").setScore(3);
		obj.getScore(" ").setScore(2);
		obj.getScore(ChatColor.WHITE + "§m-------------------").setScore(1);
		obj.getScore(ChatColor.AQUA + "fairuhc.us").setScore(0);
		
		new BukkitRunnable()
		{
			public void run()
			{
				if((FFAScatter.scatter == true || TeamScatter.scatter == true))
				{
					numplayers.setSuffix(ChatColor.WHITE + "" + TeamScatter.scattered.size());
				}
				else if(Gamemanager.started == true)
				{
					TeamGameScoreboard(p);
					cancel();
					return;
				}
			}
			
		}.runTaskTimer(plugin, 0, 1);
		
		p.setScoreboard(scoreboard);
	}
	
	public void FFAGameScoreboard(Player p)
	{
		ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
	    scoreboard = scoreboardManager.getNewScoreboard();
	        
		Objective objective = scoreboard.registerNewObjective("FFAScoreboard", "fairuh");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC");
		
		Team gametime = scoreboard.registerNewTeam("gametime");
		gametime.addEntry(ChatColor.AQUA + "Game Time " + ChatColor.GRAY + "» "); 
		Team playersleft = scoreboard.registerNewTeam("peopleleft");
		playersleft.addEntry(ChatColor.AQUA + "Alive " + ChatColor.GRAY + "» ");
		Team graceperiod = scoreboard.registerNewTeam("grace");
		graceperiod.addEntry(ChatColor.AQUA + "Grace " + ChatColor.GRAY + "» ");
		Team finalheal = scoreboard.registerNewTeam("final");
		finalheal.addEntry(ChatColor.AQUA + "Heal " + ChatColor.GRAY + "» ");
		Team bord = scoreboard.registerNewTeam("bord");
		bord.addEntry(ChatColor.AQUA + "Border " + ChatColor.GRAY + "» ");
		Team tk = scoreboard.registerNewTeam("tkill");
		tk.addEntry(ChatColor.AQUA + "Top Kill " + ChatColor.GRAY + "» ");
		Team pk = scoreboard.registerNewTeam("pkill");
		pk.addEntry(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ");
		
		objective.getScore(ChatColor.WHITE + "§m------------------").setScore(9);
		objective.getScore(ChatColor.AQUA + "Game Time " + ChatColor.GRAY + "» ").setScore(8);
		objective.getScore(ChatColor.AQUA + "Alive " + ChatColor.GRAY + "» ").setScore(7);
		objective.getScore("").setScore(6);
		objective.getScore(" ").setScore(3);
		objective.getScore(ChatColor.AQUA + "Border " + ChatColor.GRAY + "» ").setScore(2);
		objective.getScore(ChatColor.WHITE + "§m-------------------").setScore(1);
		objective.getScore(ChatColor.AQUA + "fairuhc.us").setScore(0);
		
		new BukkitRunnable()
		{
			public void run()
			{
				bord.setSuffix(ChatColor.WHITE + "" + border.bordersize);
				
				if(Timer.gperiod == false)
				{
					if(!objective.getScore(ChatColor.AQUA + "Grace " + ChatColor.GRAY + "» ").isScoreSet())
					{
						objective.getScore(ChatColor.AQUA + "Grace " + ChatColor.GRAY + "» ").setScore(5);
					}
					
					if(Timer.gametime < 60)
					{
						graceperiod.setSuffix(ChatColor.WHITE + "" + Gamemanager.graceperiod + " min");
					}
					else
					{
						if(Timer.gametime % 60 == 0)
						{
							gp = ((Gamemanager.graceperiod * 60 - (Timer.gametime)) / 60);
							
							graceperiod.setSuffix(ChatColor.WHITE + "" + gp + " min");
						}
						else
						{
							if(gp < 1)
							{
								gp = ((Gamemanager.graceperiod * 60 - (Timer.gametime)) / 60);
								
								graceperiod.setSuffix(ChatColor.WHITE + "" + gp + " sec");
							}
							else
							{
								graceperiod.setSuffix(ChatColor.WHITE + "" + gp + " min");
							}
						}
					}
				}
				else
				{
					if(objective.getScore(ChatColor.AQUA + "Grace " + ChatColor.GRAY + "» ").isScoreSet())
					{
						scoreboard.resetScores(ChatColor.AQUA + "Grace " + ChatColor.GRAY + "» ");
					}
					
					if(!objective.getScore(ChatColor.AQUA + "Top Kill " + ChatColor.GRAY + "» ").isScoreSet())
					{
						objective.getScore(ChatColor.AQUA + "Top Kill " + ChatColor.GRAY + "» ").setScore(5);
					}
					else
					{
						if(Kills.topkill.isEmpty())
						{
							tk.setSuffix(ChatColor.RED + "None");
						}
						else
						{
							tk.setSuffix(ChatColor.WHITE + "" + k.getTopKiller());
						}
					}
					
					if(!objective.getScore(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ").isScoreSet())
					{
						objective.getScore(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» ").setScore(4);
					}
					else
					{
						if(Kills.topkill.containsKey(p.getUniqueId()))
						{
							pk.setSuffix(ChatColor.WHITE + "" + Kills.topkill.get(p.getUniqueId()));
						}
						else
						{
							pk.setSuffix(ChatColor.WHITE + "0");
						}
					}
				}
				
				
				if(Timer.fheal == false)
				{
					if(!objective.getScore(ChatColor.AQUA + "Heal " + ChatColor.GRAY + "» ").isScoreSet())
					{
						objective.getScore(ChatColor.AQUA + "Heal " + ChatColor.GRAY + "» ").setScore(4);
					}
						
					if(Timer.gametime < 60)
					{
						finalheal.setSuffix(ChatColor.WHITE + "" + Gamemanager.finalheal+ " min");
					}
					else 
					{
						if(Timer.gametime % 60 == 0)
						{
							fh = ((Gamemanager.finalheal * 60 - (Timer.gametime)) / 60);
							
							finalheal.setSuffix(ChatColor.WHITE + "" + fh + " min");
						}
						else
						{
							if(fh < 1)
							{
								fh = ((Gamemanager.finalheal * 60 - (Timer.gametime)) / 60);
								
								finalheal.setSuffix(ChatColor.WHITE + "" + fh + " sec");
							}
							else
							{
								finalheal.setSuffix(ChatColor.WHITE + "" + fh + " min");
							}
						}
					}
				}
				else
				{
					if(objective.getScore(ChatColor.AQUA + "Heal " + ChatColor.GRAY + "» ").isScoreSet())
					{
						scoreboard.resetScores(ChatColor.AQUA + "Heal " + ChatColor.GRAY + "» ");
					}
				}
				
				
				gametime.setSuffix(ChatColor.WHITE + Timer.time);
				playersleft.setSuffix(ChatColor.WHITE + "" + FFAScatter.ffa.size());
				
				if(Gamemanager.ended == true)
				{
					endGame(p);
					cancel();
					return;
				}
			}
			
		}.runTaskTimer(plugin, 0, 1);
		
		p.setScoreboard(scoreboard);
	}
	
	public void TeamGameScoreboard(Player p)	
	{
		ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
		scoreboard = scoreboardManager.getNewScoreboard();
		
		Objective objective = scoreboard.registerNewObjective("TeamScoreboard" , "fairuhc");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC");
		
		Team gametime = scoreboard.registerNewTeam("gametime");
		gametime.addEntry(ChatColor.AQUA + "Game Time " + ChatColor.GRAY + "» ");
		Team teamsleft = scoreboard.registerNewTeam("teamsleft");
		teamsleft.addEntry(ChatColor.AQUA + "Teams " + ChatColor.GRAY + "» ");
		
		objective.getScore(ChatColor.WHITE + "§m------------------").setScore(4);
		objective.getScore(ChatColor.AQUA + "Game Time " + ChatColor.GRAY + "» ").setScore(3);
		objective.getScore(ChatColor.AQUA + "Teams " + ChatColor.GRAY + "» ").setScore(2);
		objective.getScore(ChatColor.WHITE + "§m-------------------").setScore(1);
		objective.getScore(ChatColor.AQUA + "fairuhc.us").setScore(0);
		
		new BukkitRunnable()
		{
			public void run()
			{
				gametime.setSuffix(ChatColor.WHITE + Timer.time);
				teamsleft.setSuffix(ChatColor.WHITE + "" + CreateTeam.playerTeams.size());
				
				if(Gamemanager.ended == true)
				{
					endGame(p);
					cancel();
					return;
				}
			}
			
		}.runTaskTimer(plugin, 0, 1);
		
		p.setScoreboard(scoreboard);
	}
	
	public void endGame(Player p)
	{
		ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
		scoreboard = scoreboardManager.getNewScoreboard();
		
		Objective objective = scoreboard.registerNewObjective("EndScoreboard" , "fairuhc");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC");
		
		Team restart = scoreboard.registerNewTeam("restart");
		restart.addEntry(ChatColor.AQUA + "Restart " + ChatColor.GRAY + "» ");
		
		objective.getScore(ChatColor.WHITE + "§m------------------").setScore(3);
		objective.getScore(ChatColor.AQUA + "Restart " + ChatColor.GRAY + "» ").setScore(2);
		objective.getScore(ChatColor.WHITE + "§m-------------------").setScore(1);
		objective.getScore(ChatColor.AQUA + "fairuhc.us").setScore(0);
		
		new BukkitRunnable()
		{
			int restarttime = 20;
			
			public void run()
			{
				restart.setSuffix(ChatColor.WHITE + "" + restarttime);
				
				if(restarttime == 0)
				{
					plugin.getServer().shutdown();
					gen.regenerateMap(p);
					cancel();
					return;
				}
				
				restarttime--;
			}
			
		}.runTaskTimer(plugin, 0, 20);
		
		p.setScoreboard(scoreboard);
	}
}
