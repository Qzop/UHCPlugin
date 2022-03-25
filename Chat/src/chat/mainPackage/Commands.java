package chat.mainPackage;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.CommandExecute;

public class Commands extends CommandExecute implements Listener, CommandExecutor
{
	// ---------[PERMISSION LIST]---------
 	//      - staffchat
	//      - chat.trial
	//      - chat.mod
	//      - chat.srmod
	//      - chat.admin
	//      - chat.owner
	//      - chat.dev
	//      - globalchat
	//      - globalchat.bypass
	// -----------------------------------
	
	
	//Arraylists
	public static ArrayList<Player> off = new ArrayList<Player>();
	public static ArrayList<UUID> yes = new ArrayList<UUID>();
	public static ArrayList<UUID> no = new ArrayList<UUID>();
	
	//global booleans
	public static boolean globalChat = false;
	public static boolean pollstarted = false;
	
	// Commands
	public String sc = "sc";
	public String gc = "gc";
	public String poll = "poll";
	public String vote = "vote";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		Player player = (Player) sender;
		
		if(label.equalsIgnoreCase(sc))
		{
			if(player.hasPermission("staffchat"))
			{
				if(args.length == 1)
				{
					if(args[0].equals("on"))
					{
						if(off.contains(player))
						{
							off.remove(player);
						}
						else
						{
							player.sendMessage(ChatColor.LIGHT_PURPLE + "[StaffChat] " + ChatColor.RED + "You already have staffchat on.");
						}
					}
					else if(args[0].equals("off"))
					{
						if(off.contains(player))
						{
							player.sendMessage(ChatColor.LIGHT_PURPLE + "[StaffChat] " + ChatColor.RED + "You already have staffchat off.");
						}
						else
						{
							off.add(player);
						}
					}
					else
					{
						player.sendMessage(ChatColor.LIGHT_PURPLE + "[StaffChat] " + ChatColor.RED + args[0] + " is not an argument!");
						player.sendMessage(ChatColor.RED + "Usage: /sc (on/off)");
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Usage: /sc (on/off)");
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "No permission.");
			}
		}
		else if(label.equalsIgnoreCase(gc))
		{
			if(player.hasPermission("globalchat"))
			{
				if(args.length == 1)
				{
					if(args[0].equals("on"))
					{
						if(globalChat == true)
						{
							globalChat = false;
							
							for(Player p : Bukkit.getOnlinePlayers())
							{
								p.sendMessage(ChatColor.GREEN + "[GlobalChat] " + ChatColor.GREEN + "Global chat has been turned on.");
							}
						}
						else
						{
							player.sendMessage(ChatColor.GREEN + "[GlobalChat] " + ChatColor.RED + "Global chat is already on!");
						}
					}
					else if(args[0].equals("off"))
					{
						if(globalChat == false)
						{
							globalChat = true;
							
							for(Player p : Bukkit.getOnlinePlayers())
							{
								p.sendMessage(ChatColor.GREEN + "[GlobalChat] " + ChatColor.RED + "Global chat has been turned off.");
							}
						}
						else
						{
							player.sendMessage(ChatColor.GREEN + "[GlobalChat] " + ChatColor.RED + "Global chat is already off!");
						}
					}
					else
					{
						player.sendMessage(ChatColor.GREEN + "[GlobalChat] " + ChatColor.RED + args[0] + " is not an argument!");
						player.sendMessage(ChatColor.RED + "Usage: /gc (on/off)");
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Usage: /gc (on/off)");
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "No permission.");
			}
		}
		else if(label.equalsIgnoreCase(poll))
		{
			if(player.hasPermission("poll.start"))
			{
				if(args.length == 0)
				{
					player.sendMessage(ChatColor.RED + "Usage: /poll (start/end) (topic)");
					player.sendMessage(ChatColor.RED + "Note: Make sure the topic is a yes or no question.");
				}
				else if(args.length == 1)
				{
					if(args[0].equals("start"))
					{	
						player.sendMessage(ChatColor.RED + "Usage: /poll (start/end) (topic)");
						player.sendMessage(ChatColor.RED + "Note: Make sure the topic is a yes or no question.");
					}
					else if(args[0].equals("end"))
					{
						if(pollstarted == true)
						{
							pollstarted = false;
						}
						else
						{
							player.sendMessage(ChatColor.RED + "There isn't an ongoing poll!");
						}
					}
					else
					{
						player.sendMessage(ChatColor.RED + "'" + args[0] + "'" + " is not a valid argument!");
						player.sendMessage(ChatColor.RED + "Usage: /poll (start/end) (topic)");
					}
				}
				else if(args.length >= 2)
				{
					if(args[0].equals("start"))
					{	
						if(pollstarted == true)
						{
							player.sendMessage(ChatColor.RED + "There is already an ongoing poll!");
							player.sendMessage(ChatColor.RED + "Do '/poll end' to end the current poll.");
						}
						else
						{
							pollstarted = true;		
							Poll po = new Poll();
							String topic = "";
							
							for(int i = 1; i < args.length; i++)
							{
								topic += args[i] + " ";
							}
							
							po.startPoll(topic);
						}
					}
					else if(args[0].equals("end"))
					{
						player.sendMessage(ChatColor.RED + "Usage: /poll (start/end) (topic)");
						player.sendMessage(ChatColor.RED + "Note: Make sure the topic is a yes or no question.");
					}
					else
					{
						player.sendMessage(ChatColor.RED + "'" + args[0] + "'" + " is not a valid argument!");
						player.sendMessage(ChatColor.RED + "Usage: /poll (start/end) (topic)");
					}
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "No Permission.");
			}
		}
		else if(label.equalsIgnoreCase(vote))
		{
			if(pollstarted == true)
			{
				if(args.length == 1)
				{
					if(args[0].equals("yes"))
					{
						if(yes.contains(player.getUniqueId()) || no.contains(player.getUniqueId()))
						{
							player.sendMessage(ChatColor.RED + "You have already voted!");
						}
						else
						{
							yes.add(player.getUniqueId());
							player.sendMessage(ChatColor.GREEN + "Your vote has been recorded.");
						}
					}
					else if(args[0].equals("no"))
					{
						if(yes.contains(player.getUniqueId()) || no.contains(player.getUniqueId()))
						{
							player.sendMessage(ChatColor.RED + "You have already voted!");
						}
						else
						{
							no.add(player.getUniqueId());
							player.sendMessage(ChatColor.GREEN + "Your vote has been recorded.");
						}
					}
					else
					{
						player.sendMessage(ChatColor.RED + "'" + args[0] + "'" + " is not a valid argument!");
						player.sendMessage(ChatColor.RED + "Usage: /vote (yes/no)");
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Usage: /vote (yes/no)");
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "There is no ongoing poll!");
			}
		}
		
		return false;
	}
}
