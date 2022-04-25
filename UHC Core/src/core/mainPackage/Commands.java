package core.mainPackage;

import core.Config.ConfigInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import core.Scatter.Scatter;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.CommandExecute;

public class Commands extends CommandExecute implements Listener, CommandExecutor
{
	// LIST OF ALL BOOLEANS USED:
	public static boolean scatter = false;
	public static boolean ffa = true;
	public static boolean teams = false;
	
	private Scatter scat = new Scatter();
	private ConfigInventory inv = new ConfigInventory();
	
	String uhc = "uhc";
	String config = "config";
	
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
					p.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + "UHC Commands: \n"
							+ ChatColor.AQUA + "- /uhc start \n - /uhc stop" );
					
				}
				else if(args.length == 1)
				{
					if(args[0].equals("start"))
					{
						scatter = true;
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
		
		return false;
	}
}
