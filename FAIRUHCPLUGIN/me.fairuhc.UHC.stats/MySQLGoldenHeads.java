package me.fairuhc.UHC.stats;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.fairuhc.UHC.Main;

public class MySQLGoldenHeads implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	
	public void createTableHeadsEaten()
	{
		PreparedStatement ps;
		
		try
		{
			ps = plugin.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS heads " 
							+ "(NAME VARCHAR(100),UUID VARCHAR(100), HEADS INT(100),PRIMARY KEY (NAME))");
			
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
				PreparedStatement ps1 = plugin.getConnection().prepareStatement("INSERT IGNORE INTO heads" 
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
			ps = plugin.getConnection().prepareStatement("SELECT * FROM heads WHERE UUID=?");
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
	
	public void addHeads(UUID uuid, int eaten)
	{		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("UPDATE heads SET HEADS=? WHERE UUID=?");
			ps.setInt(1, getHeads(uuid) + eaten);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public int getHeads(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT HEADS FROM heads WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			int gaps = 0;
			
			if(rs.next())
			{
				gaps = rs.getInt("HEADS");
				return gaps;
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
			PreparedStatement ps = plugin.getConnection().prepareStatement("TRUNCATE heads");
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
			PreparedStatement ps = plugin.getConnection().prepareStatement("DELETE FROM heads WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
