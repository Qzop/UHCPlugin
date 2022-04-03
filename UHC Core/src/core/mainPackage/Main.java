package core.mainPackage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	public void onEnable()
	{
		Commands command = new Commands();
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "    UHC CORE ENABLED :D    ");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------");
		
		this.getServer().getPluginManager().registerEvents(this, this);
	
		getCommand(command.uhc).setExecutor(command);
	}
	
	public void onDisable()
	{
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "    UHC CORE ENABLED D:    ");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "---------------------------");
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		
		World world = Bukkit.getWorld("world");
		
		p.teleport(world.getSpawnLocation());
	}
}
