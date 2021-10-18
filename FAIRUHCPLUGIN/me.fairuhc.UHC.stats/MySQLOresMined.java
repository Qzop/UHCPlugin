package me.fairuhc.UHC.stats;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.fairuhc.UHC.Main;

public class MySQLOresMined implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	
	public void createTableOres()
	{
		PreparedStatement ps;
		
		try
		{
			ps = plugin.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS ores " 
							+ "(NAME VARCHAR(100),UUID VARCHAR(100), DIAMOND INT(100), GOLD INT(100), IRON INT(100), REDSTONE INT(100), LAPIS INT(100),"
							+ " COAL INT(100), QUARTZ INT(100),PRIMARY KEY (NAME))");
			
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
				PreparedStatement ps1 = plugin.getConnection().prepareStatement("INSERT IGNORE INTO ores" 
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
			ps = plugin.getConnection().prepareStatement("SELECT * FROM ores WHERE UUID=?");
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
	
	public void addDiamond(UUID uuid, int diamond)
	{		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("UPDATE ores SET DIAMOND=? WHERE UUID=?");
			ps.setInt(1, getDiamond(uuid) + diamond);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addGold(UUID uuid, int gold)
	{		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("UPDATE ores SET GOLD=? WHERE UUID=?");
			ps.setInt(1, getGold(uuid) + gold);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addIron(UUID uuid, int iron)
	{		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("UPDATE ores SET IRON=? WHERE UUID=?");
			ps.setInt(1, getIron(uuid) + iron);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addRedstone(UUID uuid, int redstone)
	{		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("UPDATE ores SET REDSTONE=? WHERE UUID=?");
			ps.setInt(1, getRedstone(uuid) + redstone);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addLapis(UUID uuid, int lapis)
	{		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("UPDATE ores SET LAPIS=? WHERE UUID=?");
			ps.setInt(1, getLapis(uuid) + lapis);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addCoal(UUID uuid, int coal)
	{		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("UPDATE ores SET COAL=? WHERE UUID=?");
			ps.setInt(1, getCoal(uuid) + coal);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addQuartz(UUID uuid, int quartz)
	{		
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("UPDATE ores SET QUARTZ=? WHERE UUID=?");
			ps.setInt(1, getQuartz(uuid) + quartz);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public int getDiamond(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT DIAMOND FROM ores WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			int entries = 0;
			
			if(rs.next())
			{
				entries = rs.getInt("DIAMOND");
				return entries;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getGold(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT GOLD FROM ores WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			int entries = 0;
			
			if(rs.next())
			{
				entries = rs.getInt("GOLD");
				return entries;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getIron(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT IRON FROM ores WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			int entries = 0;
			
			if(rs.next())
			{
				entries = rs.getInt("IRON");
				return entries;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getRedstone(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT REDSTONE FROM ores WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			int entries = 0;
			
			if(rs.next())
			{
				entries = rs.getInt("REDSTONE");
				return entries;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getLapis(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT LAPIS FROM ores WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			int entries = 0;
			
			if(rs.next())
			{
				entries = rs.getInt("LAPIS");
				return entries;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getCoal(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT COAL FROM ores WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			int entries = 0;
			
			if(rs.next())
			{
				entries = rs.getInt("COAL");
				return entries;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getQuartz(UUID uuid)
	{
		try
		{
			PreparedStatement ps = plugin.getConnection().prepareStatement("SELECT QUARTZ FROM ores WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			int entries = 0;
			
			if(rs.next())
			{
				entries = rs.getInt("QUARTZ");
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
			PreparedStatement ps = plugin.getConnection().prepareStatement("TRUNCATE ores");
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
			PreparedStatement ps = plugin.getConnection().prepareStatement("DELETE FROM ores WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}