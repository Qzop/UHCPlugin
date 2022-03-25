package notes.Events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import notes.Database.NotesTable;
import notes.Inventories.NotesInventory;
import notes.mainPackage.Main;

public class ReadNotes implements Listener
{
	private NotesTable notes = new NotesTable();
	public static Player OnlineGetNotes;
	public static OfflinePlayer OfflineGetNotes;
	public static int currentPage;
	Main plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void inventoryClick(InventoryClickEvent e)
	{
		ArrayList<UUID> uuids = new ArrayList<UUID>();
		uuids = notes.GetAllUUIDs();
		
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getClickedInventory();
		ItemStack item = e.getCurrentItem();
		
		if(inv == null)
		{
			return;
		}
		else if(inv.getName().equals(ChatColor.RED + "Notes"))
		{
			e.setCancelled(true);
			
			if(item == null || !item.hasItemMeta())
			{
				return;
			}
			
			if(OfflineGetNotes != null)
			{
				if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + OfflineGetNotes.getName()))
				{
						// Leave this empty
				}
				
				OfflineGetNotes = null;
			}
			else
			{
				if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + OnlineGetNotes.getName()))
				{
						// Leave this empty
				}
				
				OnlineGetNotes = null;
			}
		}
		else if(inv.getName().equals(ChatColor.RED + "Player Notes (Multiple Pages)"))
		{
			e.setCancelled(true);
			
			if(item == null || !item.hasItemMeta())
			{
				return;
			}	
			
			for(int i = 0; i < uuids.size(); i++)
			{
				Player target = Bukkit.getPlayer(uuids.get(i));
				
				if(target == null)
				{
					OfflinePlayer t = Bukkit.getOfflinePlayer(uuids.get(i));
					
					if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + t.getName()))
					{
						// Leave this empty
					}
				}
				else
				{
					if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + target.getName()))
					{
						// Leave this empty
					}
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(" "))
			{
				// Nothing needs to be done in here, this is just for the filler slots so that players cannot take the stained glass out
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Next page"))
			{
				currentPage--;
				p.openInventory(NotesInventory.inventories.get(currentPage));
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Previous page"))
			{
				currentPage++;
				p.openInventory(NotesInventory.inventories.get(currentPage));
			}
		}
		else if(inv.getName().equals(ChatColor.RED + "Player Notes"))
		{
			e.setCancelled(true);
			
			if(item == null || !item.hasItemMeta())
			{
				return;
			}
			
			for(int i = 0; i < uuids.size(); i++)
			{
				Player target = Bukkit.getPlayer(uuids.get(i));
				
				if(target == null)
				{
					OfflinePlayer t = Bukkit.getOfflinePlayer(uuids.get(i));
					
					if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + t.getName()))
					{
						// Leave this empty
					}
				}
				else
				{
					if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + target.getName()))
					{
						// Leave this empty
					}
				}
			}
		}
		else if(inv.getName().equals(ChatColor.YELLOW + "Add Notes"))
		{
			e.setCancelled(true);
			
			if(item == null || !item.hasItemMeta())
			{
				return;
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Possible Xray/CaveFinder."))
			{
				if(ChatNotes.Offlinetarget != null)
				{
					if(!notes.Targetexists(ChatNotes.Offlinetarget.getUniqueId()))
					{
						notes.CreateNotesOfflinePlayer(ChatNotes.Offlinetarget, p);
						notes.AddNotes(ChatNotes.Offlinetarget.getUniqueId(), "Possible Xray/CaveFinder");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Offlinetarget.getName() + "'.");
					}
					else
					{
						notes.AddNotes(ChatNotes.Offlinetarget.getUniqueId(), "Possible Xray/CaveFinder");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Offlinetarget.getName() + "'.");
					}
				}
				else
				{
					if(!notes.Targetexists(ChatNotes.Onlinetarget.getUniqueId()))
					{
						notes.CreateNotesOnlinePlayer(ChatNotes.Onlinetarget, p);
						notes.AddNotes(ChatNotes.Onlinetarget.getUniqueId(), "Possible Xray/CaveFinder");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Onlinetarget.getName() + "'.");
					}
					else
					{
						notes.AddNotes(ChatNotes.Onlinetarget.getUniqueId(), "Possible Xray/CaveFinder");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Onlinetarget.getName() + "'.");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Possibly abusing a bug."))
			{
				if(ChatNotes.Offlinetarget != null)
				{
					if(!notes.Targetexists(ChatNotes.Offlinetarget.getUniqueId()))
					{
						notes.CreateNotesOfflinePlayer(ChatNotes.Offlinetarget, p);
						notes.AddNotes(ChatNotes.Offlinetarget.getUniqueId(), "Possibly abusing a bug");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Offlinetarget.getName() + "'.");
					}
					else
					{
						notes.AddNotes(ChatNotes.Offlinetarget.getUniqueId(), "Possibly abusing a bug");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Offlinetarget.getName() + "'.");
					}
				}
				else
				{
					if(!notes.Targetexists(ChatNotes.Onlinetarget.getUniqueId()))
					{
						notes.CreateNotesOnlinePlayer(ChatNotes.Onlinetarget, p);
						notes.AddNotes(ChatNotes.Onlinetarget.getUniqueId(), "Possibly abusing a bug");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Onlinetarget.getName() + "'.");
					}
					else
					{
						notes.AddNotes(ChatNotes.Onlinetarget.getUniqueId(), "Possibly abusing a bug");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Onlinetarget.getName() + "'.");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Possibly using reach."))
			{
				if(ChatNotes.Offlinetarget != null)
				{
					if(!notes.Targetexists(ChatNotes.Offlinetarget.getUniqueId()))
					{
						notes.CreateNotesOfflinePlayer(ChatNotes.Offlinetarget, p);
						notes.AddNotes(ChatNotes.Offlinetarget.getUniqueId(), "Possibly using reach.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Offlinetarget.getName() + "'.");
					}
					else
					{
						notes.AddNotes(ChatNotes.Offlinetarget.getUniqueId(), "Possibly using reach.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Offlinetarget.getName() + "'.");
					}
				}
				else
				{
					if(!notes.Targetexists(ChatNotes.Onlinetarget.getUniqueId()))
					{
						notes.CreateNotesOnlinePlayer(ChatNotes.Onlinetarget, p);
						notes.AddNotes(ChatNotes.Onlinetarget.getUniqueId(), "Possibly using reach.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Onlinetarget.getName() + "'.");
					}
					else
					{
						notes.AddNotes(ChatNotes.Onlinetarget.getUniqueId(), "Possibly using reach.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Onlinetarget.getName() + "'.");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Possibly using tracers."))
			{
				if(ChatNotes.Offlinetarget != null)
				{
					if(!notes.Targetexists(ChatNotes.Offlinetarget.getUniqueId()))
					{
						notes.CreateNotesOfflinePlayer(ChatNotes.Offlinetarget, p);
						notes.AddNotes(ChatNotes.Offlinetarget.getUniqueId(), "Possibly using tracers.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Offlinetarget.getName() + "'.");
					}
					else
					{
						notes.AddNotes(ChatNotes.Offlinetarget.getUniqueId(), "Possibly using tracers.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Offlinetarget.getName() + "'.");
					}
				}
				else
				{
					if(!notes.Targetexists(ChatNotes.Onlinetarget.getUniqueId()))
					{
						notes.CreateNotesOnlinePlayer(ChatNotes.Onlinetarget, p);
						notes.AddNotes(ChatNotes.Onlinetarget.getUniqueId(), "Possibly using tracers.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Onlinetarget.getName() + "'.");
					}
					else
					{
						notes.AddNotes(ChatNotes.Onlinetarget.getUniqueId(), "Possibly using tracers.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Onlinetarget.getName() + "'.");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Possibly using an autoclicker."))
			{
				if(ChatNotes.Offlinetarget != null)
				{
					if(!notes.Targetexists(ChatNotes.Offlinetarget.getUniqueId()))
					{
						notes.CreateNotesOfflinePlayer(ChatNotes.Offlinetarget, p);
						notes.AddNotes(ChatNotes.Offlinetarget.getUniqueId(), "Possibly using an autoclicker.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Offlinetarget.getName() + "'.");
					}
					else
					{
						notes.AddNotes(ChatNotes.Offlinetarget.getUniqueId(), "Possibly using an autoclicker.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Offlinetarget.getName() + "'.");
					}
				}
				else
				{
					if(!notes.Targetexists(ChatNotes.Onlinetarget.getUniqueId()))
					{
						notes.CreateNotesOnlinePlayer(ChatNotes.Onlinetarget, p);
						notes.AddNotes(ChatNotes.Onlinetarget.getUniqueId(), "Possibly using an autoclicker.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Onlinetarget.getName() + "'.");
					}
					else
					{
						notes.AddNotes(ChatNotes.Onlinetarget.getUniqueId(), "Possibly using an autoclicker.");
						p.closeInventory();
						p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + ChatNotes.Onlinetarget.getName() + "'.");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Cancel notes"))
			{
				ChatNotes.Offlinetarget = null;
				ChatNotes.Onlinetarget = null;
				
				p.closeInventory();
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Write your own notes"))
			{
				p.closeInventory();
				
				p.sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Type in what you want to say about the player.");
				
				ChatNotes.notesPlayer = p;
			}
			else if(item.getItemMeta().getDisplayName().equals(" "))
			{
				// Nothing needs to be done in here, this is just for the filler slots so that players cannot take the stained glass out
			}
		}
	}
}