package notes.mainPackage;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.CommandExecute;
import notes.Database.NotesTable;
import notes.Events.ChatNotes;
import notes.Events.ReadNotes;
import notes.Inventories.NotesInventory;

public class Commands extends CommandExecute implements Listener, CommandExecutor
{
	// ---------------[PERMISSION LIST]---------------
	//      - notes.addremove
	//      - notes.view
	//      - notes.clear
	// -----------------------------------------------
	
	
	@SuppressWarnings("unused")
	Main plugin = Main.getPlugin(Main.class);
	
	private NotesTable data = new NotesTable();
	private NotesInventory inv = new NotesInventory();
	public String notes = "notes";
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{ 
		Player player = (Player) sender;
		
		if(label.equalsIgnoreCase(notes))
		{
			if(player.hasPermission("notes.addremove"))
			{
				if(args.length >= 3)
				{
					player.sendMessage(ChatColor.RED + "Usage: /notes (add/get/remove/clear) (player)");
				}
				else if(args.length == 0)
				{
					if(player.hasPermission("notes.view"))
					{
						// Show all player notes in a GUI
						
						inv.notesInv(player);
						
						return true;
					}
					else
					{
						player.sendMessage(ChatColor.RED + "No Permission.");
						
						return false;
					}
				}
				else if(args.length == 1)
				{
					if(args[0].equals("clear"))
					{
						if(player.hasPermission("notes.clear"))
						{
							data.EmptyTable();
							player.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully cleared notes.");
							plugin.MySQLSetup();
						}
						else
						{
							player.sendMessage(ChatColor.RED + "No permission.");
						}
					}
					else
					{
						player.sendMessage(ChatColor.RED + args[0] + " is not an argument!");
						player.sendMessage(ChatColor.RED + "Usage: /notes (add/get/remove/clear) (player)");
					}
				}
				else if(args.length == 2)
				{
					Player t1 = Bukkit.getPlayer(args[1]);
					OfflinePlayer t2;
					
					if(t1 == null)
					{
						t2 = Bukkit.getOfflinePlayer(args[1]);
						
						if(args[0].equals("remove"))
						{
							// remove the unique id from the database and the message
						
							if(data.Targetexists(t2.getUniqueId()))
							{
								data.RemoveNotes(t2.getUniqueId());
								player.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully removed notes from player '" + t2.getName() + "'.");
							}
							else
							{
								player.sendMessage(ChatColor.RED + "Player '" + args[1] + "' does not exist in the database!");
							}
						
							return true;
						}
						else if(args[0].equals("add"))
						{
							// add the unique id to the database and the message
							
							if(data.Targetexists(t2.getUniqueId()))
							{
								ChatNotes.Offlinetarget = t2; 
								inv.addNotesInv(player);
							}
							else
							{
								ChatNotes.Offlinetarget = t2; 
								inv.addNotesInv(player);
							}
							
							return true;
						}
						else if(args[0].equals("get"))
						{
							if(data.Targetexists(t2.getUniqueId()))
							{
								inv.getNotesInv(player, t2.getUniqueId());
								ReadNotes.OfflineGetNotes = t2;
							}
							else
							{
								player.sendMessage(ChatColor.RED + "Player '" + args[1] + "' does not exist in the database!");
							}
						}
						else
						{
							player.sendMessage(ChatColor.RED + args[0] + " is not an argument!");
							player.sendMessage(ChatColor.RED + "Usage: /notes (add/get/remove/clear) (player)");
							
							return false;
						}
					}
					else
					{
						if(args[0].equals("remove"))
						{
							// remove the unique id from the database and the message
							
							if(data.Targetexists(t1.getUniqueId()))
							{
								data.RemoveNotes(t1.getUniqueId());
								player.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully removed notes from player '" + t1.getDisplayName() + "'.");
							}
							else
							{
								player.sendMessage(ChatColor.RED + "Player '" + args[1] + "' does not exist in the database!");
							}
							
							return true;
						}
						else if(args[0].equals("add"))
						{
							// add the unique id to the database and the message
							
							if(data.Targetexists(t1.getUniqueId()))
							{
								ChatNotes.Onlinetarget = t1;
								inv.addNotesInv(player);
							}
							else
							{
								ChatNotes.Onlinetarget = t1;
								inv.addNotesInv(player);
							}
							
							return true;
						}
						else if(args[0].equals("get"))
						{
							if(data.Targetexists(t1.getUniqueId()))
							{
								inv.getNotesInv(player, t1.getUniqueId());
								ReadNotes.OnlineGetNotes = t1;
							}
							else
							{
								player.sendMessage(ChatColor.RED + "Player '" + args[1] + "' does not exist in the database!");
							}
						}
						else
						{
							player.sendMessage(ChatColor.RED + args[0] + " is not an argument!");
							player.sendMessage(ChatColor.RED + "Usage: /notes (add/get/remove/clear) (player)");
							
							return false;
						}
					}
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "No Permission.");
				
				return false;
			}
			
			return true;
		}
		
		return false;
	}
}