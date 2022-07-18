package core.mainPackage;

import core.Alerts.Alerts;
import core.Arena.PracticeArena;
import core.ChunkLoad.DeleteWorld;
import core.Config.ConfigInventory;
import core.ConfigVariables.BedRockBorder;
import core.ConfigVariables.Horses;
import core.HostsMods.Gamemode;
import core.HostsMods.Helpop;
import core.HostsMods.HostsMods;
import core.Kills.Respawn;
import core.ScenariosInventory.ScenariosInventory;
import core.Teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import core.Scatter.Scatter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.UUID;

import static core.Scatter.Scatter.started;

public class Commands implements Listener, CommandExecutor
{
	// LIST OF ALL BOOLEANS USED:
	public static boolean scatter = false;
	public static boolean chat = false;
	
	private Scatter scat = new Scatter();
	private ConfigInventory inv = new ConfigInventory();
	private HostsMods hosts = new HostsMods();
	private TeamManager tm = new TeamManager();
	private PracticeArena a = new PracticeArena();
	private BedRockBorder bord = new BedRockBorder();
	private Gamemode gamemode = new Gamemode();
	private Respawn res = new Respawn();
	private DeleteWorld delete = new DeleteWorld();
	private Alerts alert = new Alerts();
	private ScenariosInventory sceninv = new ScenariosInventory();

	private ArrayList<UUID> brightness = new ArrayList<UUID>();
	
	String uhc = "uhc";
	String config = "config";
	String host = "host";
	String mod = "mod";
	String team = "team";
	String help = "helpop";
	String tele = "tp";
	String arena = "arena";
	String border = "border";
	String gm = "gm";
	String test = "test";
	String respawn = "respawn";
	String world = "world";
	String spawn = "spawn";
	String feed = "feed";
	String heal = "heal";
	String alerts = "alerts";
	String bright = "bright";
	String scenarios = "scenarios";
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player p = (Player) sender;

		if (label.equalsIgnoreCase(scenarios))
		{
			if(args.length < 1)
			{
				sceninv.scenarioList(p);
			}
			if(args.length == 1)
			{
				if (p.hasPermission("uhc.scenarios"))
				{
					sceninv.scenarioAdmin(p);
				}
				else
				{
					p.sendMessage(ChatColor.RED + "No Permission.");
				}
			}
		}
		else if(label.equalsIgnoreCase(uhc))
		{
			if(p.hasPermission("uhc"))
			{
				if(args.length == 0)
				{
					p.sendMessage(Scatter.UHCprefix + " " + ChatColor.GOLD + ChatColor.BOLD + "UHC Commands: \n"
							+ ChatColor.AQUA + "- /uhc start \n - /uhc stop" );
					
				}
				else if(args.length == 1)
				{
					if(args[0].equals("start"))
					{
						chat = true;
						scatter = true;
						Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.RED + " Chat is now disabled.");
						scat.onStart();
					}
					else if(args[0].equals("stop"))
					{

					}
					else 
					{
						p.sendMessage(ChatColor.RED + "'" + args[0] + "' is an invalid argument!");
					}
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "No permission.");
			}
		}
		else if(label.equalsIgnoreCase(config))
		{
			if(args.length == 0)
			{
				inv.createConfig(p);
			}
			else if(args.length == 1)
			{
				if(args[0].equals("admin"))
				{
					if(p.hasPermission("Config.admin"))
					{
						inv.createConfigAdmin(p);
					}
					else
					{
						p.sendMessage(ChatColor.RED + "No Permission.");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "'" + args[0] + "' is an invalid argument!");
					p.sendMessage(ChatColor.RED + "Usage: /config (admin)");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "Usage: /config (admin)");
			}
		}
		else if(label.equalsIgnoreCase(mod))
		{
			if(p.hasPermission("uhc.mod"))
			{
				if(args.length == 2)
				{
					if(args[0].equals("add"))
					{
						Player target = Bukkit.getPlayer(args[1]);

						if(target != null)
						{
							hosts.setMod(target);
						}
						else
						{
							p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " That player is not online!");
						}
					}
					else if(args[0].equals("remove"))
					{
						Player target = Bukkit.getPlayer(args[1]);

						if(target != null)
						{
							hosts.removeMod(target);
						}
						else
						{
							p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " That player is not online!");
						}
					}
					else
					{
						p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " Invalid argument '" + args[0] + "'.");
					}
				}
				else
				{
					p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " Usage: /mod (add/remove) (player)");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "No Permission.");
			}
		}
		else if(label.equalsIgnoreCase(host))
		{
			if(p.hasPermission("uhc.host"))
			{
				if(args.length == 2)
				{
					if(args[0].equals("add"))
					{
						Player target = Bukkit.getPlayer(args[1]);

						if(target != null)
						{
							hosts.setHost(target);
						}
						else
						{
							p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " That player is not online!");
						}
					}
					else if(args[0].equals("remove"))
					{
						Player target = Bukkit.getPlayer(args[1]);

						if(target != null)
						{
							hosts.removeHost(target);
						}
						else
						{
							p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " That player is not online!");
						}
					}
					else
					{
						p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " Invalid argument '" + args[0] + "'.");
					}

				}
				else
				{
					p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " Usage: /host (add/remove) (player)");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "No Permission.");
			}
		}
		else if(label.equalsIgnoreCase(team))
		{
			if(ConfigInventory.teamSize > 1)
			{
				if(args.length == 1)
				{
					if(args[0].equals("create"))
					{
						tm.createTeam(p);
					}
					else if(args[0].equals("list"))
					{
						tm.teamList(p);
					}
					else if(args[0].equals("disband"))
					{
						if(!scatter && !started)
						{
							tm.disbandTeam(p);
						}
						else
						{
							p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You cannot use that command now!");
						}
					}
					else if(args[0].equals("invite"))
					{
						p.sendMessage(ChatColor.RED + "Usage: /team invite (player)");
					}
					else if(args[0].equals("remove"))
					{
						p.sendMessage(ChatColor.RED + "Usage: /team remove (player)");
					}
					else
					{
						p.sendMessage(TeamManager.Teamprefix + ChatColor.RED + " Invalid argument '" + args[0] + "'.");
					}
				}
				else if(args.length >= 2)
				{
					if(args[0].equals("invite"))
					{
						if(!scatter && !started)
						{
							Player target = Bukkit.getPlayer(args[1]);

							if(target != null)
							{
								if(target.getUniqueId().equals(p.getUniqueId()))
								{
									p.sendMessage(TeamManager.Teamprefix + ChatColor.RED + " You cannot invite yourself to a team!");
								}
								else
								{
									tm.invitePlayer(p, target);
								}
							}
							else
							{
								p.sendMessage(TeamManager.Teamprefix + ChatColor.RED + " That player does not exist!");
							}
						}
						else
						{
							p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You cannot use that command now!");
						}
					}
					else if(args[0].equals("accept"))
					{
						if(!scatter && !started)
						{
							if(tm.pendingInv.containsKey(p.getUniqueId()))
							{
								tm.addPlayer(p);
							}
							else
							{
								p.sendMessage(TeamManager.Teamprefix + ChatColor.RED + " You do not currently have an invite!");
							}
						}
						else
						{
							p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You cannot use that command now!");
						}
					}
					else if(args[0].equals("remove"))
					{
						if(!scatter && !started)
						{
							Player target = Bukkit.getPlayer(args[1]);

							if(target != null)
							{
								tm.removePlayer(p, target);
							}
							else
							{
								OfflinePlayer targ = Bukkit.getOfflinePlayer(args[1]);

								if(targ.getName().equals(args[1]))
								{
									tm.removeOfflinePlayer(p, targ);
								}
								else
								{
									p.sendMessage(TeamManager.Teamprefix + ChatColor.RED + " Player '" + args[1] + "' does not exist!");
								}
							}
						}
						else
						{
							p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You cannot use that command now!");
						}
					}
					else if(args[0].equals("list"))
					{
						Player t = Bukkit.getPlayer(args[1]);

						if(t == null)
						{
							p.sendMessage(TeamManager.Teamprefix + ChatColor.RED + " Player '" + args[1] + "' does not exist!");
						}
						else
						{
							tm.teamListTarg(p, t);
						}
					}
					else
					{
						p.sendMessage(TeamManager.Teamprefix + ChatColor.RED + " Invalid argument '" + args[0] + "'.");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Usage: /team (create/disband) \nUsage: /team (invite/remove/list) (player)");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "Teams are not enabled!");
			}
		}
		else if(label.equalsIgnoreCase(help))
		{
			if(args.length == 0)
			{
				p.sendMessage(ChatColor.RED + "Usage: /helpop (message)");
			}
			else
			{
				String message = "";
				Helpop h = new Helpop();

				for(int i = 0; i < args.length; i++)
				{
					message += args[i] + " ";
				}

				h.onHelp(p, message);
			}
		}
		else if(label.equalsIgnoreCase(tele))
		{
			if(p.hasPermission("uhc.tp"))
			{
				if(args.length == 0 || args.length > 2)
				{
					p.sendMessage(ChatColor.RED + "Usage: /tp (player) (player)");
				}
				else if(args.length == 1)
				{
					Player target = Bukkit.getPlayer(args[0]);

					if(target == null)
					{
						p.sendMessage(ChatColor.RED + "That player is not online!");
					}
					else
					{
						p.teleport(target);
						p.sendMessage(ChatColor.GREEN + "Teleported to " + target.getDisplayName() + ".");
					}
				}
				else if(args.length == 2)
				{
					Player targ1 = Bukkit.getPlayer(args[0]);
					Player targ2 = Bukkit.getPlayer(args[1]);

					if(targ1 == null)
					{
						p.sendMessage(ChatColor.RED + "Player '" + args[0] + "' is not online!");
					}
					else if(targ2 == null)
					{
						p.sendMessage(ChatColor.RED + "Player '" + args[1] + "' is not online!");
					}
					else
					{
						targ1.teleport(targ2);
						p.sendMessage(ChatColor.GREEN + "Successfully teleported '" + targ1.getName() + "' to '" + targ2.getName() + "'.");
					}
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "No permission.");
			}
		}
		else if(label.equalsIgnoreCase(arena))
		{
			if(args.length == 0)
			{
				if(!PracticeArena.playersInArena.contains(p.getUniqueId()))
				{
					a.onArenaJoin(p);
				}
				else
				{
					p.sendMessage(ChatColor.RED + "You are already in the arena! Use /arena leave to leave.");
				}
			}
			else if(args.length == 1)
			{
				if(args[0].equals("leave"))
				{
					if(!PracticeArena.playersInArena.contains(p.getUniqueId()))
					{
						p.sendMessage(ChatColor.RED + "You are not in the arena! Use /arena to join.");
					}
					else
					{
						a.onArenaLeave(p);
					}
				}
				else if(args[0].equals("kill"))
				{
					if(p.hasPermission("arena.kill"))
					{
						Horses horse = new Horses();
						horse.killAllEntitiesArena();
					}
					else
					{
						p.sendMessage(ChatColor.RED + "No Permission.");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Usage: /arena (leave)");
				}
			}
		}
		else if(label.equalsIgnoreCase(border))
		{
			if(p.hasPermission("border.shrink"))
			{
				bord.setUpShrink();
			}
			else
			{
				p.sendMessage(ChatColor.RED + "No permission.");
			}
		}
		else if(label.equalsIgnoreCase(gm))
		{
			if(p.hasPermission("uhc.Gamemode"))
			{
				if(args.length == 1)
				{
					int i = Integer.parseInt(args[0]);
					
					gamemode.setGamemode(p, i);
				}
				else
				{
					p.sendMessage(Gamemode.gamemodePrefix + ChatColor.RED + " /gm (0/1/2/3)");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "No permission.");
			}
		}
		else if(label.equalsIgnoreCase(respawn))
		{
			if(p.hasPermission("uhc.Respawn"))
			{
				if(args.length == 1)
				{
					Player target = Bukkit.getPlayer(args[0]);
					
					if(target == null)
					{
						p.sendMessage(ChatColor.RED + "The player '" + args[0] + "' is not online!");
					}
					else
					{
						res.onRespawn(p, target);
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Usage: /respawn (player)");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "No Permission");
			}
		}
		else if(label.equalsIgnoreCase(world))
		{
			if(p.hasPermission("world.delete")  || p.hasPermission("world.teleport"))
			{
				if(args.length == 2)
				{
					if(args[0].equals("delete"))
					{
						delete.deleteWorld(p, args[1]);
					}
					else if(args[0].equals("tp"))
					{
						delete.teleportToWorld(p, args[1]);
					}
					else
					{
						p.sendMessage(ChatColor.RED + "Usage: /world delete (world name)");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Usage: /world delete (world name)");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "No Permission.");
			}
		}
		else if(label.equalsIgnoreCase(spawn))
		{
			if(p.hasPermission("spawn.set"))
			{
				if(args.length == 1)
				{
					if(args[0].equals("set"))
					{
						World world = p.getWorld();

						world.setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
						world.getSpawnLocation().setYaw(p.getLocation().getYaw());
						world.getSpawnLocation().setPitch(p.getLocation().getPitch());

						p.sendMessage(ChatColor.GREEN + "Spawn Location for the World '" + world.getName() + "' set successfully.");
					}
					else
					{
						p.sendMessage(ChatColor.RED + "Usage: /spawn set");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Usage: /spawn set");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "No Permission.");
			}
		}
		else if(label.equalsIgnoreCase(feed))
		{
			if(p.hasPermission("uhc.feed"))
			{
				if(args.length == 0)
				{
					p.setFoodLevel(20);
					p.sendMessage(ChatColor.GREEN + "You have been fed successfully!");
				}
				else if(args.length == 1)
				{
					Player target = Bukkit.getPlayer(args[0]);

					if(target != null)
					{
						target.setFoodLevel(20);
						target.setExhaustion(0.5f);
						p.sendMessage(ChatColor.GREEN + "You have fed '" + target.getDisplayName() + "' successfully!");
						target.sendMessage(ChatColor.GREEN + "You have been fed.");
					}
					else
					{
						p.sendMessage(ChatColor.RED + "That player is not online!");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Usage: /feed (player)");
				}
			}
		}
		else if(label.equalsIgnoreCase(heal))
		{
			if(p.hasPermission("uhc.heal"))
			{
				if(args.length == 0)
				{
					p.setHealth(p.getHealth() + (20.0 - p.getHealth()));
					p.sendMessage(ChatColor.GREEN + "You have been healed successfully.");
				}
				else if(args.length == 1)
				{
					Player target = Bukkit.getPlayer(args[0]);

					if(target != null)
					{
						target.setHealth(target.getHealth() + (20.0 - target.getHealth()));
						p.sendMessage(ChatColor.GREEN + "You have healed '" + target.getDisplayName() + "' successfully.");
						target.sendMessage(ChatColor.GREEN + "You have been healed.");
					}
					else
					{
						p.sendMessage(ChatColor.RED + "That player is not online!");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Usage: /heal (player)");
				}
			}
		}
		else if(label.equalsIgnoreCase(alerts))
		{
			if(p.hasPermission("uhc.alerts"))
			{
				if(args.length == 0)
				{
					alert.turnSpecificAlertsOnAndOff(p);
				}
				else if(args.length == 1)
				{
					if(args[0].equals("on"))
					{
						alert.turnAlertsOn(p);
					}
					else if(args[0].equals("off"))
					{
						alert.turnAlertsOff(p);
					}
					else
					{
						p.sendMessage(ChatColor.RED + "Usage: /alerts (on/off)");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Usage: /alerts (on/off)");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "No Permission.");
			}
		}
		else if(label.equalsIgnoreCase(bright))
		{
			if(args.length == 0)
			{
				if(!brightness.contains(p.getUniqueId()))
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
					brightness.add(p.getUniqueId());
					p.sendMessage(Scatter.UHCprefix + ChatColor.YELLOW + " Full brightness is now " + ChatColor.GREEN + "Enabled" + ChatColor.YELLOW + ".");
				}
				else
				{
					p.removePotionEffect(PotionEffectType.NIGHT_VISION);
					brightness.remove(p.getUniqueId());
					p.sendMessage(Scatter.UHCprefix + ChatColor.YELLOW + " Full brightness is now " + ChatColor.RED + "Disabled" + ChatColor.YELLOW + ".");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "Usage: /bright");
			}
		}
		else if(label.equalsIgnoreCase(test))
		{
			if(p.hasPermission("dev.test"))
			{

			}
		}
		
		return false;
	}
}