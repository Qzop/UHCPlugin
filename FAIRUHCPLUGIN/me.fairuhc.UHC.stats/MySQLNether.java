package me.fairuhc.UHC.stats;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.fairuhc.UHC.Main;

public class MySQLNether implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	
	public void createTableEntries()
	{
		PreparedStatement ps;
		
		try
		{
			ps = plugin.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS nether " 
							+ "(NAME VARCHAR(100),UUID VARCHAR(100), ENTRYS INT(100),PRIMARY KEY (NAME))");
			
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void createPlayer(Player p)
	{
		try
		{
			UUID uuid = p.getUniqueId();
			
			if(!exists(uuid))
			{
				PreparedStatement ps1 = plugin.getConnection().prepareStatement("INSERT IGNORE INTO nether" 
						+ " (NAME,UUID) VALUES (?,?)");
				ps1.setString(1, p.getDisplayName());
				ps1.setString(2, uuid.toString());
				ps1.executeUpdate();
				
				return;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean exists(UUID uuid)
	{
		PreparedStatement ps;
		
		try
		{
			ps = plugin.getConnection().prepareStatement("SELECT * FROM nether WHERE UUID=?");
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
	
	public void addEntries(UUID uuid, int entries)
	{		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("UPDATE nether SET ENTRYS=? WHERE UUID=?");
			ps.setInt(1, getEntries(uuid) + entries);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public int getEntries(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT ENTRYS FROM nether WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			int entries = 0;
			
			if(rs.next())
			{
				entries = rs.getInt("ENTRYS");
				return entries;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void emptytable()
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("TRUNCATE nether");
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void remove(UUID uuid)
	{
		try 
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("DELETE FROM nether WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}

