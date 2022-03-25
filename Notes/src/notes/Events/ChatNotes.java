package notes.Events;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;
import notes.Database.NotesTable;

public class ChatNotes implements Listener
{
	public static Player notesPlayer;
	public static Player Onlinetarget;
	public static OfflinePlayer Offlinetarget;
	private NotesTable data = new NotesTable();
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		String notes = "";
		
		if(e.getPlayer() == notesPlayer)
		{			
			notes = e.getMessage();
			e.setCancelled(true);
					
			//Database stuff here
			
			if(Onlinetarget == null)
			{
				if(data.Targetexists(Offlinetarget.getUniqueId()))
				{
					data.CreateNotesOfflinePlayer(Offlinetarget, notesPlayer);
					data.AddNotes(Offlinetarget.getUniqueId(), notes);
				}
				else
				{
					data.AddNotes(Offlinetarget.getUniqueId(), notes);
				}
				
				e.getPlayer().sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + Offlinetarget.getName() + "'.");
				Offlinetarget = null;
			}
			else
			{
				if(data.Targetexists(Onlinetarget.getUniqueId()))
				{
					data.CreateNotesOnlinePlayer(Onlinetarget, notesPlayer);
					data.AddNotes(Onlinetarget.getUniqueId(), notes);
				}
				else
				{
					data.AddNotes(Onlinetarget.getUniqueId(), notes);
				}
				
				e.getPlayer().sendMessage(ChatColor.YELLOW + "[Notes]" + ChatColor.AQUA + " Successfully added notes to player '" + Onlinetarget.getDisplayName() + "'.");
				Onlinetarget = null;
			}
			
			notesPlayer = null;
		}
	}
}
