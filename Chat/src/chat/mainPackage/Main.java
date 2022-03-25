package chat.mainPackage;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import chat.Events.ChatFormating;
import chat.Events.StaffChat;

public class Main extends JavaPlugin implements Listener
{
	public void onEnable()
	{
		Commands command = new Commands();
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "  CHAT PLUGIN ENABLED :D  ");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");
		
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new StaffChat(), this);
		this.getServer().getPluginManager().registerEvents(new ChatFormating(), this);
		
		getCommand(command.sc).setExecutor(command);
		getCommand(command.gc).setExecutor(command);
		getCommand(command.poll).setExecutor(command);
		getCommand(command.vote).setExecutor(command);
	}
	
	public void onDisable()
	{
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "  CHAT PLUGIN DISABLED D: ");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
	}
}
