package me.fairuhc.UHC.stats;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.fairuhc.UHC.Main;

public class MySQLPotions implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	
	public void createTablePotionsConsumed()
	{
		PreparedStatement ps;
		
		try
		{
			ps = plugin.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS potions " 
							+ "(NAME VARCHAR(100),UUID VARCHAR(100), POTIONS INT(100),PRIMARY KEY (NAME))");
			
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
				PreparedStatement ps1 = plugin.getConnection().prepareStatement("INSERT IGNORE INTO potions" 
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
			ps = plugin.getConnection().prepareStatement("SELECT * FROM potions WHERE UUID=?");
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
	
	public void addPotions(UUID uuid, int potions)
	{		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("UPDATE potions SET POTIONS=? WHERE UUID=?");
			ps.setInt(1, getPotions(uuid) + potions);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public int getPotions(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT POTIONS FROM potions WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			int potions = 0;
			
			if(rs.next())
			{
				potions = rs.getInt("POTIONS");
				return potions;
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
			PreparedStatement ps = plugin.getConnection().prepareStatement("TRUNCATE potions");
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
			PreparedStatement ps = plugin.getConnection().prepareStatement("DELETE FROM potions WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
