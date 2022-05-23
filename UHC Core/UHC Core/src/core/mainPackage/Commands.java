package core.mainPackage;

import core.Config.ConfigInventory;
import core.HostsMods.HostsMods;
import core.Teams.TeamManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import core.Scatter.Scatter;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.CommandExecute;

import static core.Scatter.Scatter.started;

public class Commands extends CommandExecute implements Listener, CommandExecutor
{
	// LIST OF ALL BOOLEANS USED:
	public static boolean scatter = false;
	public static boolean chat = false;
	
	private Scatter scat = new Scatter();
	private ConfigInventory inv = new ConfigInventory();
	private HostsMods hosts = new HostsMods();
	private TeamManager tm = new TeamManager();
	
	String uhc = "uhc";
	String config = "config";
	String host = "host";
	String mod = "mod";
	String team = "team";
	String test = "test";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player p = (Player) sender;
		
		if(label.equalsIgnoreCase(uhc))
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
					else
					{
						p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " Invalid argument '" + args[0] + "'.");
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
		else if(label.equalsIgnoreCase(test))
		{
			// stuff to test in here
		}
		
		return false;
	}
}