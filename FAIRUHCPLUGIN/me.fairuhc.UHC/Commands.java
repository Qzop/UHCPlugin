package me.fairuhc.UHC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Team;

import Events.Practice;
import Events.Sidebar;
import Events.Timer;
import me.fairuhc.UHC.Scatter.FFAScatter;
import me.fairuhc.UHC.Scatter.TeamScatter;
import me.fairuhc.UHC.World.ChunkLoad;
import me.fairuhc.UHC.World.worldGen;
import me.fairuhc.UHC.scenarios.Scenarios;
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
import me.fairuhc.UHC.stats.Stats;
import me.fairuhc.UHC.teams.CreateTeam;
import me.fairuhc.UHC.teams.DisbandTeam;
import me.fairuhc.UHC.teams.InvitePlayer;
import me.fairuhc.UHC.teams.LeaveTeam;
import me.fairuhc.UHC.teams.RemovePlayer;
import me.fairuhc.UHC.teams.SendCoordinates;
import me.fairuhc.UHC.teams.SetTeam;
import me.fairuhc.UHC.teams.TeamManager;
import net.minecraft.server.v1_8_R3.CommandExecute;

public class Commands extends CommandExecute implements Listener, CommandExecutor
{
	public Main plugin;
	public static HashMap<UUID, Integer> helpopdelay = new HashMap<UUID, Integer>();
	private Scenarios scen = new Scenarios();
	private Gamemanager manager;	
	private TeamManager tm;
	private CreateTeam ct;
	private DisbandTeam dt;
	private InvitePlayer ip;
	private LeaveTeam lt;
	private RemovePlayer rp;
	private SendCoordinates sc;
	private SetTeam sett;
	private Practice pra = new Practice();
	private Config con = new Config();
	private Kills kills = new Kills();
	private Host h = new Host();
	private worldGen gen;
	private border bord;
	private Respawn resp = new Respawn();
	private Heal he = new Heal();
	private Give g = new Give();
	
	//Command List
	public String scenarios = "scenarios";
	public String scenadmin = "sa";
	public String uhc = "uhc";
	public String config = "config"; 
	public String conadmin = "ca";
	public String team = "team";
	public String health = "health";
	public String topkill = "kt";
	public String numteams = "numteams";
	public String coords = "sc";
	public String host = "sethost";
	public String removehost = "removehost";
	public String regeneratemap = "regeneratemap";
	public String prac = "practice";
	public String gt = "gt";
	public String loadchunks = "loadchunks";
	public String reloadconfig = "sdbreload";
	public String stats = "stats";
	public String helpop = "helpop";
	public String report = "report";
	public String mod = "mod";
	public String BORDER = "border";
	public String respawn = "respawn";
	public String heal = "heal";
	public String give = "give";
	
	public Team teams;
	
	public Commands(Main pl)
	{
		this.plugin = pl;
		manager = new Gamemanager(plugin);
		tm = new TeamManager(plugin);
		gen = new worldGen(plugin);
		ct = new CreateTeam(plugin);
		dt = new DisbandTeam();
		ip = new InvitePlayer(plugin);
		lt = new LeaveTeam();
		rp = new RemovePlayer(plugin);
		sc = new SendCoordinates();
		sett = new SetTeam();
		bord = new border(plugin);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Stats" + ChatColor.GRAY + "]";
		Player player = (Player) sender;
	
		if(label.equalsIgnoreCase(give))
		{
			if(player.hasPermission("uhc.give"))
			{
				if(args.length == 0 || args.length == 1 || args.length == 2 || args.length > 3)
				{
					player.sendMessage(ChatColor.RED + "Usage: /give (player) (item) (amount)");
				}
				else if(args.length == 3)
				{
					Player t = Bukkit.getPlayer(args[0]);
					
					if(t != null)
					{
						String item = args[1];
						int amount = 0;
						
						try
						{
							amount = Integer.parseInt(args[2]);
							
							g.onGive(player, t, item, amount);
						}
						catch(NumberFormatException e)
						{
							e.getStackTrace();
							player.sendMessage(ChatColor.RED + "The amount must be an integer!");
						}
					}
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You do not have permission.");
			}
		}
		
		if(label.equalsIgnoreCase(heal))
		{
			if(player.hasPermission("uhc.heal"))
			{
				if(args.length == 0 || args.length > 1)
				{
					player.sendMessage(ChatColor.RED + "Usage: /heal (player)");
				}
				
				if(args.length == 1)
				{
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target != null)
					{
						he.onHeal(target);
					}
					else
					{
						player.sendMessage(ChatColor.RED + "That player is not online!");
					}
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You do not have permission.");
			}
		}
		
		if(label.equalsIgnoreCase(respawn))
		{
			if(player.hasPermission("uhc.mod"))
			{
				if(args.length > 1 || args.length == 0)
				{
					player.sendMessage(ChatColor.RED + "Usage: /respawn (Player)");
				}
				
				if(args.length == 1)
				{
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target == null)
					{
						player.sendMessage(ChatColor.RED + "That player is not online!");
					}
					else
					{
						resp.respawn(player, target);
					}
				}
			}
		}
		
		if(label.equalsIgnoreCase(BORDER))
		{
			if(player.hasPermission("border.shrink"))
			{
				player.sendMessage(ChatColor.RED + "In development.");
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You do not have permission.");
			}
		}
		
		if(label.equalsIgnoreCase(reloadconfig))
		{
			if(player.hasPermission("admin.reloadconfig"))
			{
				plugin.saveConfig();
				
				player.sendMessage(prefix + ChatColor.GREEN + " Config was reloaded.");
			}
		}
		
		if(label.equalsIgnoreCase(stats))
		{
			Stats s = new Stats();
			MySQLDeaths death = new MySQLDeaths();
			MySQLGaps gaps = new MySQLGaps();
			MySQLGoldenHeads heads = new MySQLGoldenHeads();
			MySQLkdr kdr = new MySQLkdr();
			MySQLKills kills = new MySQLKills();
			MySQLNether nether = new MySQLNether();
			MySQLOresMined ores = new MySQLOresMined();
			MySQLPlayTime time = new MySQLPlayTime();
			MySQLPotions potions = new MySQLPotions();
			MySQLWins wins = new MySQLWins();
			
			Player target;
			OfflinePlayer t;
			
			if(args.length == 1)
			{
				if(args[0].equals("reset"))
				{
					if(player.hasPermission("skript.op"))
					{
						s.statsReset(player);
					}
					else
					{
						player.sendMessage(ChatColor.RED + "No permission");
					}
				}
				else if(args[0].equals("add"))
				{
					if(player.hasPermission("skript.op"))
					{
						player.sendMessage(ChatColor.RED + "Usage: /stats add (deaths/kills/wins) (player) (number)");
					}
					else
					{
						player.sendMessage(ChatColor.RED + "No permission");
					}
					
				}
				else if(args[0].equals("clear"))
				{
					if(player.hasPermission("skript.op"))
					{
						player.sendMessage(ChatColor.RED + "Usage: /stats clear (player)");
					}
					else
					{
						player.sendMessage(ChatColor.RED + "No permission");
					}
				}
				else
				{
					target =  Bukkit.getPlayer(args[0]);
					
					if(target == null)
					{
						t = Bukkit.getOfflinePlayer(args[0]);
						
						if(t == null)
						{
							player.sendMessage(prefix + ChatColor.RED + " That player does not exist!");
						}
						else
						{
							if(death.exists(t.getUniqueId()) && wins.exists(t.getUniqueId()) && gaps.exists(t.getUniqueId()) && heads.exists(t.getUniqueId()) && kdr.exists(t.getUniqueId()) && kills.exists(t.getUniqueId()) && nether.exists(t.getUniqueId()) && ores.exists(t.getUniqueId()) && time.exists(t.getUniqueId()) && potions.exists(t.getUniqueId()))
							{
								s.statsOfflinePlayer(player, t);
							}
							else
							{
								player.sendMessage(prefix + ChatColor.RED + " That player does not have any stats recorded.");
							}
						}
					}
					else
					{
						if(death.exists(target.getUniqueId()) && wins.exists(target.getUniqueId()) && gaps.exists(target.getUniqueId()) && heads.exists(target.getUniqueId()) && kdr.exists(target.getUniqueId()) && kills.exists(target.getUniqueId()) && nether.exists(target.getUniqueId()) && ores.exists(target.getUniqueId()) && time.exists(target.getUniqueId()) && potions.exists(target.getUniqueId()))
						{
							s.statsPlayer(player, target);
						}
						else
						{
							player.sendMessage(prefix + ChatColor.RED + " That player does not have any stats recorded.");
						}
					}
				}
			}
			else if(args.length > 1)
			{
				int num = 0;
					
				if(args[0].equals("add"))
				{
					if(args[1].equals("deaths"))
					{
						if(args[2].equals(""))
						{
							player.sendMessage(ChatColor.RED + "Usage: /stats add deaths (player) (number)");
						}
						else
						{
							target = Bukkit.getPlayer(args[2]);
								
							if(target == null)
							{
								t = Bukkit.getOfflinePlayer(args[2]);
									
								if(death.exists(t.getUniqueId()))
								{
									num = Integer.parseInt(args[3]);
										
									if(args[3] == null)
									{
										player.sendMessage(ChatColor.RED + "Usage: /stats add deaths (player) (number)");
									}
									else
									{
										death.addDeaths(t.getUniqueId(), num);
									}
								}
								else
								{
									player.sendMessage(prefix + ChatColor.RED + " That player does not exist!");
								}
							}
							else
							{
								if(death.exists(target.getUniqueId()))
								{
									num = Integer.parseInt(args[3]);
										
									if(args[3] == null)
									{
										player.sendMessage(ChatColor.RED + "Usage: /stats add deaths (player) (number)");
									}
									else
									{
										death.addDeaths(target.getUniqueId(), num);
									}
								}
								else
								{
									player.sendMessage(prefix + ChatColor.RED + " That player does not exist!");
								}
							}
						}
					}
					else if(args[1].equals("kills"))
					{
						if(args[2] == null)
						{
							player.sendMessage(ChatColor.RED + "Usage: /stats add kills (player) (number)");
						}
						else
						{
							target = Bukkit.getPlayer(args[2]);
								
							if(target == null)
							{
								t = Bukkit.getOfflinePlayer(args[2]);
									
								if(kills.exists(t.getUniqueId()))
								{
									num = Integer.parseInt(args[3]);
										
									if(args[3] == null)
									{
										player.sendMessage(ChatColor.RED + "Usage: /stats add kills (player) (number)");
									}
									else
									{
										kills.addKills(t.getUniqueId(), num);
									}
								}
								else
								{
									player.sendMessage(prefix + ChatColor.RED + " That player does not exist!");
								}
							}
							else
							{
								if(kills.exists(target.getUniqueId()))
								{
									num = Integer.parseInt(args[3]);
										
									if(args[3] == null)
									{
										player.sendMessage(ChatColor.RED + "Usage: /stats add kills (player) (number)");
									}
									else
									{
										kills.addKills(target.getUniqueId(), num);
									}
								}
								else
								{
									player.sendMessage(prefix + ChatColor.RED + " That player does not exist!");
								}
							}
						}
					}
					else if(args[1].equals("wins"))
					{
						if(args[2] == null)
						{
							player.sendMessage(ChatColor.RED + "Usage: /stats add wins (player) (number)");
						}
						else
						{
							target = Bukkit.getPlayer(args[2]);
								
							if(target == null)
							{
								t = Bukkit.getOfflinePlayer(args[2]);
									
								if(wins.exists(t.getUniqueId()))
								{
									num = Integer.parseInt(args[3]);
										
									if(args[3] == null)
									{
										player.sendMessage(ChatColor.RED + "Usage: /stats add wins (player) (number)");
									}
									else
									{
										wins.addwins(t.getUniqueId(), num);
									}
								}
								else
								{
									player.sendMessage(prefix + ChatColor.RED + " That player does not exist!");
								}
							}
							else
							{
								if(wins.exists(target.getUniqueId()))
								{
									num = Integer.parseInt(args[3]);
										
									if(args[3] == null)
									{
										player.sendMessage(ChatColor.RED + "Usage: /stats add wins (player) (number)");
									}
									else
									{
										wins.addwins(target.getUniqueId(), num);
									}
								}
								else
								{
									player.sendMessage(prefix + ChatColor.RED + " That player does not exist!");
								}
							}
						}
					}
					else
					{
						player.sendMessage(ChatColor.RED + "Usage: /stats add (deaths/kills/wins) (player) (number)");
					}
				}
				else if(args[0].equals("clear"))
				{
					target = Bukkit.getPlayer(args[1]);
						
					if(target == null)
					{
						t = Bukkit.getOfflinePlayer(args[1]);
							
						if(death.exists(t.getUniqueId()) && wins.exists(t.getUniqueId()) && gaps.exists(t.getUniqueId()) && heads.exists(t.getUniqueId()) && kdr.exists(t.getUniqueId()) && kills.exists(t.getUniqueId()) && nether.exists(t.getUniqueId()) && ores.exists(t.getUniqueId()) && time.exists(t.getUniqueId()) && potions.exists(t.getUniqueId()))
						{
							death.remove(t.getUniqueId());
							wins.remove(t.getUniqueId());
							gaps.remove(t.getUniqueId());
							heads.remove(t.getUniqueId());
							kdr.remove(t.getUniqueId());
							kills.remove(t.getUniqueId());
							nether.remove(t.getUniqueId());
							ores.remove(t.getUniqueId());
							time.remove(t.getUniqueId());
							potions.remove(t.getUniqueId());
								
							player.sendMessage(prefix + ChatColor.GREEN + " Player stats successfully cleared.");
						}
						else
						{
							player.sendMessage(prefix + ChatColor.RED + " That player does not exist!");
						}
							
					}
					else
					{
						if(death.exists(target.getUniqueId()) && wins.exists(target.getUniqueId()) && gaps.exists(target.getUniqueId()) && heads.exists(target.getUniqueId()) && kdr.exists(target.getUniqueId()) && kills.exists(target.getUniqueId()) && nether.exists(target.getUniqueId()) && ores.exists(target.getUniqueId()) && time.exists(target.getUniqueId()) && potions.exists(target.getUniqueId()))
						{
							death.remove(target.getUniqueId());
							wins.remove(target.getUniqueId());
							gaps.remove(target.getUniqueId());
							heads.remove(target.getUniqueId());
							kdr.remove(target.getUniqueId());
							kills.remove(target.getUniqueId());
							nether.remove(target.getUniqueId());
							ores.remove(target.getUniqueId());
							time.remove(target.getUniqueId());
							potions.remove(target.getUniqueId());
								
							player.sendMessage(prefix + ChatColor.GREEN + " Player stats successfully cleared.");
						}
						else
						{
							player.sendMessage(prefix + ChatColor.RED + " That player does not exist!");
						}
					}
				}
			}
			else
			{
				s.stats(player);
			}
		}
		
		if(label.equalsIgnoreCase(scenarios) || label.equalsIgnoreCase("scen"))
		{
			if(sender instanceof Player)
			{
				scen.showScen(player);
				
				return true;
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "You must be a player to use that command!");
			}
		}
		else if(label.equalsIgnoreCase(scenadmin))
		{
			if(sender instanceof Player)
			{
				scen.selectScen(player);
				
				return true;
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "You must be a player to use that command!");
			}
		}
		
		if(label.equalsIgnoreCase(uhc))
		{
			FFAScatter ffascat = new FFAScatter(plugin);
			TeamScatter teamscat = new TeamScatter(plugin);
			
			if(sender instanceof Player)
			{
				if(args[0].equals("start") || args[0].equals("Start"))
				{
					if(player.hasPermission("uhc.start"))
					{
						if(Gamemanager.started == true)
						{
							player.sendMessage(ChatColor.RED + "The UHC has already started!");
						}
						else
						{
							if(Gamemanager.teamsize == 1)
							{
								Timer.chat = false;
								ffascat.bord();
							}
							else
							{
								teamscat.bord();
							}
						}
					}
					else if(Main.pregen == true)
					{
						if(player.hasPermission("uhc.start"))
						{
							player.sendMessage(ChatColor.RED + "You cannot start the game until pregen is finished.");
						}
					}
					else
					{
						player.sendMessage(ChatColor.RED + "You do not have permission.");
					}
				}
				
				return true;
			}
		}
		
		if(label.equalsIgnoreCase(config))
		{
			if(sender instanceof Player)
			{
				con.config(player);
			}
			
			return true;
		}
		
		if(label.equalsIgnoreCase(conadmin))
		{
			if(sender instanceof Player)
			{
				con.configAdmin(player);
			}
			
			return true;
		}
		
		if(label.equalsIgnoreCase(team))
		{
			ArrayList<UUID> teammembers = new ArrayList<UUID>();
			
			if(sender instanceof Player)
			{
				if(args.length == 0)
				{
					player.sendMessage(ChatColor.YELLOW + "Team Commands: \n /team create \n /team invite (name) \n /team accept (name) \n /team leave \n /team disband \n /team remove (name)");
				}
				else if(args[0].equals("create") && Gamemanager.teamsize > 1)
				{
					ct.createTeam(player, teammembers);
				}
				else if(args[0].equals("create") && Gamemanager.teamsize == 1)
				{
					player.sendMessage(ChatColor.RED + "Teams are not enabled!");
				}
				else if(args[0].equals("invite"))
				{
					if(args.length == 1)
					{
						player.sendMessage(ChatColor.RED + "Usage: /team invite (player)");
					}
					else if(args.length > 1)
					{
						Player target = Bukkit.getPlayer(args[1]);
						
						if(target == null)
						{
							player.sendMessage(ChatColor.RED + "That player is not online!");
						}
						else
						{
							ip.invitePlayer(player, target);
						}
					}
				}
				else if(args[0].equals("deny"))
				{
					if(args.length == 1)
					{
						player.sendMessage(ChatColor.RED + "Usage: /team accept (player)");
					}
					else if(args.length > 1)
					{
						Player target = Bukkit.getPlayer(args[1]);
						
						if(target == null)
						{
							player.sendMessage(ChatColor.RED + "That player is not online!");
						}
						else
						{
							if(TeamManager.hasinvite.containsKey(target.getDisplayName()) && TeamManager.hasinvite.get(target.getDisplayName()).equalsIgnoreCase(player.getDisplayName()))
							{
								ip.denyInvite(target, player);
							}
							else
							{
								player.sendMessage(ChatColor.RED + "You do not have an invite or your invite expired!");
							}
						}
					}
				}
				else if(args[0].equals("accept"))
				{
					if(args.length == 1)
					{
						player.sendMessage(ChatColor.RED + "Usage: /team accept (player)");
					}
					else if(args.length > 1)
					{
						Player target = Bukkit.getPlayer(args[1]);
						
						if(target == null)
						{
							player.sendMessage(ChatColor.RED + "That player is not online!");
						}
						else
						{
							if(TeamManager.hasinvite.containsKey(target.getDisplayName()) && TeamManager.hasinvite.get(target.getDisplayName()).equalsIgnoreCase(player.getDisplayName()))
							{
								sett.setTeam(player, target, teams);
							}
							else
							{
								player.sendMessage(ChatColor.RED + "You do not have an invite or your invite expired!");
							}
						}
					}
				}
				else if(args[0].equals("disband"))
				{
					dt.disbandTeam(player);
				}
				else if(args[0].equals("leave"))
				{
					lt.leaveTeam(player);
				}
				else if(args[0].equals("list"))
				{
					if(args.length == 1)
					{
						player.sendMessage(ChatColor.RED + "Usage: /team list (player)");
					}
					else
					{
						Player target = Bukkit.getPlayer(args[1]);
						
						if(target == null)
						{
							player.sendMessage(ChatColor.RED + "That player is not online!");
						}
						else
						{
							tm.teamList(player, target);
						}
					}
				}
				else if(args[0].equals("remove"))
				{
					if(args.length == 1)
					{
						player.sendMessage(ChatColor.RED + "Usage: /team remove (player)");
					}
					else
					{
						Player target = Bukkit.getPlayer(args[1]);
						
						if(target == null)
						{
							player.sendMessage(ChatColor.RED + "That player is not online!");
						}
						else
						{
							rp.removePlayer(player, target);
						}
					}
				}
			}
		}
		
		if(label.equalsIgnoreCase(health))
		{
			if(args.length == 0 || args.length > 1)
			{
				player.sendMessage(ChatColor.RED + "Usage: /health (player)");
			}
			else if(args.length == 1)
			{
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null)
				{
					player.sendMessage(ChatColor.RED + "That player is not online!");
				}
				else
				{
					manager.getHealth(player, target);
				}
			}
		}
		
		if(label.equalsIgnoreCase(topkill))
		{
			if(args.length > 0)
			{
				player.sendMessage(ChatColor.RED + "Usage: /kt");
			}
			else
			{
				kills.topkill(player);
			}
		}
		
		if(label.equalsIgnoreCase(numteams))
		{
			TeamManager manager = new TeamManager(plugin);
			
			player.sendMessage(manager.getTeamSize() + "");
			
		}
		
		if(label.equalsIgnoreCase(coords))
		{
			if(args.length > 0)
			{
				player.sendMessage(ChatColor.RED + "Usage: /sc");
			}
			else
			{
				sc.getCoordinates(player);
			}
		}
		
		if(label.equalsIgnoreCase(host))
		{
			if(args.length > 0)
			{
				player.sendMessage(ChatColor.RED + "Usage: /sethost");
			}
			else
			{
				h.clearItemsSetHost(player);
			}
		}
		
		if(label.equalsIgnoreCase(removehost))
		{
			if(args.length > 0)
			{
				player.sendMessage(ChatColor.RED + "Usage: /removehost");
			}
			else
			{
				h.clearItemsRemoveHost(player);
			}
		}
		
		if(label.equalsIgnoreCase(regeneratemap))
		{
			if(args.length > 0)
			{
				player.sendMessage(ChatColor.RED + "Usage: /regeneratemap");
			}
			else
			{
				gen.regenerateMap(player);
			}
		}
		
		if(label.equalsIgnoreCase(prac) || label.equalsIgnoreCase("prac"))
		{
			if(Gamemanager.started == false || (FFAScatter.scatter == false && TeamScatter.scatter == false))
			{
				if(args.length > 0)
				{
					player.sendMessage(ChatColor.RED + "Usage: /practice");
				}
				else
				{
					if(Practice.prac.contains(player.getUniqueId()))
					{
						pra.removePrac(player);
					}
					else
					{
						Practice.prac.add(player.getUniqueId());
						pra.removeItems(player);
					}
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You cannot join practice while a game is running!");
			}
		}
		
		if(label.equalsIgnoreCase(gt))
		{
			if(player.hasPermission("dev.getteams"))
			{
				Gamemanager gm = new Gamemanager(plugin);
				
				gm.getTeams(player);
			}
		}
		
		if(label.equalsIgnoreCase(loadchunks))
		{
			if(player.hasPermission("uhc.mod"))
			{
				if(Gamemanager.started == false)
				{
					ChunkLoad c = new ChunkLoad();
					
					if(ChunkLoad.arechunksloaded == false)
					{
						c.getChunksToLoad();
					}
				}
				else
				{
					player.sendMessage(prefix + ChatColor.RED + " You cannot load chunks while the game is starting!");
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You do not have permission.");
			}
		}
		
		if(label.equalsIgnoreCase(helpop))
		{
			Helpop help = new Helpop(plugin);
			String pr = ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "HelpOp" + ChatColor.WHITE + "]";
			String msg = "";
			
			if(!helpopdelay.containsKey(player.getUniqueId()))
			{
				if(args.length >= 1)
				{
					StringBuilder sb = new StringBuilder(); 
					
					for(int i = 0; i < args.length; i++)
					{
						sb.append(args[i] + " ");
					}
					
					msg = sb.toString().trim();
				
					help.sendMessage(player, msg);
					player.sendMessage(pr + ChatColor.GREEN + " Your message has been sent.");
					helpopdelay.put(player.getUniqueId(), 30);
					help.delay(player);
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Usage: /helpop (message)");
				}
			}
			else
			{
				player.sendMessage(pr + ChatColor.RED + " You must wait " + helpopdelay.get(player.getUniqueId()) + " second(s) before using helpop again.");
			}
		}
		
		if(label.equalsIgnoreCase(report))
		{
			Player t;
			
			if(args.length == 1)
			{
				Report r = new Report(plugin);
				
				t = Bukkit.getPlayer(args[0]);
				
				if(t != null)
				{
					if(t.getUniqueId() == player.getUniqueId())
					{
						player.sendMessage("You cannot report yourself!");
					}
					else
					{
						r.report(player, t);
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "That player is not online!");
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "Usage: /report (player)");
			}
		}
		
		if(label.equalsIgnoreCase(mod))
		{
			Mod m = new Mod();
			Player t;
			
			if(player.hasPermission("uhc.mod"))
			{
				if(args.length < 1)
				{
					m.setMod(player);
				}
				else if(args.length == 1)
				{
					if(args[0].equals("remove"))
					{
						m.removeMod(player);
					}
					else
					{
						t = Bukkit.getPlayer(args[0]);
						
						if(t == null)
						{
							player.sendMessage(ChatColor.RED + "That player is not online!");
						}
						else
						{
							m.setMod(t);
						}
					}
				}
				else if(args.length == 2)
				{
					if(args[0].equals("remove"))
					{
						t = Bukkit.getPlayer(args[1]);
						
						if(t == null)
						{
							player.sendMessage(ChatColor.RED + "That player is not online!");
						}
						else
						{
							if(Mod.mod.contains(t.getDisplayName()))
							{
								m.removeMod(t);
							}
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Usage: /mod (remove/player)");
				}
				
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You do not have permission.");
			}
		}
		
		return false;
	}
}