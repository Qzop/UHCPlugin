package notes.mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import notes.Database.NotesTable;
import notes.Events.ChatNotes;
import notes.Events.ReadNotes;

public class Main extends JavaPlugin implements Listener
{
	private Connection conn;
	private NotesTable notes;
	
	public void onEnable()
	{
		Commands command = new Commands();
		notes = new NotesTable();
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "  NOTES PLUGIN ENABLED :D  ");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");
		
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new ReadNotes(), this);
		this.getServer().getPluginManager().registerEvents(new ChatNotes(), this);
		
		getCommand(command.notes).setExecutor(command);
		
		MySQLSetup();
	}
	
	public void onDisable()
	{
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "  NOTES PLUGIN DISABLED D: ");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
	}
	
	public void MySQLSetup()
	{	
		
		try
		{
			synchronized(this)
			{
				if(getConnection() != null && !getConnection().isClosed())
				{
					return;
				}
				
				Class.forName("com.mysql.jdbc.Driver");
				setConnection(DriverManager.getConnection("jdbc:mysql://104.219.236.107:3306/s59900_Notes?autoReconnect=true", "u59900_OJ9dgO7JsY", "+DlD@@ieDApxjKI1RjiSJixP"));
				
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");
		        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "      MYSQL CONNECTED      ");
			    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");
							
			    notes.CreateNotesTable();
			}
		}
		catch(SQLException e)
		{
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "    MYSQL NOT CONNECTED    ");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
					
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "    MYSQL NOT CONNECTED    ");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
					
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return conn;
	}
	
	public void setConnection(Connection conn)
	{
		this.conn = conn;
	}
}