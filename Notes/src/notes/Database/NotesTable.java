package notes.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;
import notes.mainPackage.Main;

public class NotesTable implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	
	public void CreateNotesTable()
	{
		PreparedStatement ps;
		
		try
		{
			ps = plugin.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS notes " 
							+ "(ISSUER VARCHAR(100), ISSUERUUID VARCHAR(100), NAME VARCHAR(100), UUID VARCHAR(100), MESSAGE VARCHAR(100), PRIMARY KEY (UUID))");
			
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void CreateNotesOfflinePlayer(OfflinePlayer target, Player p)
	{
		try
		{
			UUID tuuid = target.getUniqueId();
			UUID puuid = p.getUniqueId();
			
			if(!Targetexists(tuuid))
			{
				PreparedStatement ps = plugin.getConnection().prepareStatement("INSERT IGNORE INTO notes"
						+ " (ISSUER,ISSUERUUID,NAME,UUID) VALUES (?,?,?,?)");
				
				ps.setString(1, p.getDisplayName());
				ps.setString(2, puuid.toString());
				ps.setString(3, target.getName());
				ps.setString(4, tuuid.toString());
				ps.executeUpdate();
				
				return;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void CreateNotesOnlinePlayer(Player target, Player p)
	{
		try
		{
			UUID puuid = p.getUniqueId();
			UUID tuuid = target.getUniqueId();
			
			if(!Targetexists(tuuid))
			{
				PreparedStatement ps = plugin.getConnection().prepareStatement("INSERT IGNORE INTO notes"
						+ " (ISSUER,ISSUERUUID,NAME,UUID) VALUES (?,?,?,?)");
				
				ps.setString(1, p.getDisplayName());
				ps.setString(2, puuid.toString());
				ps.setString(3, target.getDisplayName());
				ps.setString(4, tuuid.toString());
				ps.executeUpdate();
				
				return;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean Targetexists(UUID uuid)
	{
		PreparedStatement ps;
		
		try
		{
			ps = plugin.getConnection().prepareStatement("SELECT * FROM notes WHERE UUID=?");
			ps.setString(1, uuid.toString());
			
			ResultSet results = ps.executeQuery();
			
			if(results.next())
			{
				return true;
			}
			
			return false;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean Playerexists(UUID uuid)
	{
		PreparedStatement ps;
		
		try
		{
			ps = plugin.getConnection().prepareStatement("SELECT * FROM notes WHERE ISSUERUUID=?");
			ps.setString(1, uuid.toString());
			
			ResultSet results = ps.executeQuery();
			
			if(results.next())
			{
				return true;
			}
			
			return false;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void AddNotes(UUID uuid, String message)
	{	
		if(GetNotes(uuid) != null)
		{
			message += "; " + GetNotes(uuid);
		}
	
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("UPDATE notes SET MESSAGE=? WHERE UUID=?");
			ps.setString(1, message);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public UUID getIssuerUUID(UUID uuid)
	{
		UUID issuer;
		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT ISSUERUUID FROM notes WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
	
			if(rs.next())
			{
				issuer = UUID.fromString(rs.getString("ISSUERUUID"));
				
				return issuer;
			}
			
			return null;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getIssuerName(UUID uuid)
	{
		String issuer;
		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT ISSUER FROM notes WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
	
			if(rs.next())
			{
				issuer = rs.getString("ISSUER");
				
				return issuer;
			}
			
			return null;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getPlayerName(UUID uuid)
	{
		String playername;
		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT NAME FROM notes WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
	
			if(rs.next())
			{
				playername = rs.getString("NAME");
				
				return playername;
			}
			
			return null;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<UUID> GetAllUUIDs()
	{
		ArrayList<UUID> uuids = new ArrayList<UUID>();
		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT UUID FROM notes");
			ResultSet rs = ps.executeQuery();
			UUID uuid;
			
			while(rs.next())
			{
				uuid = UUID.fromString(rs.getString("UUID"));
				uuids.add(uuid);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return uuids;
	}
	
	public String GetNotes(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT MESSAGE FROM notes WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			String message = "";
			
			if(rs.next())
			{
				message = rs.getString("MESSAGE");
				return message;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
			return ChatColor.RED + "Error.";
		}
		
		return ChatColor.RED + "No notes on player '" + Bukkit.getPlayer(uuid).toString() + "'.";
	}

	public void EmptyTable()
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("TRUNCATE notes");
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void RemoveNotes(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("DELETE FROM notes WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}