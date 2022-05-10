package core.mainPackage;

import core.Config.ConfigEvent;
import core.ConfigVariables.AppleRate;
import core.ConfigVariables.Horses;
import core.ConfigVariables.Portals;
import core.ConfigVariables.SpeedStrength;
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
		this.getServer().getPluginManager().registerEvents(new ConfigEvent(), this);
		this.getServer().getPluginManager().registerEvents(new AppleRate(), this);
		this.getServer().getPluginManager().registerEvents(new Horses(), this);
		this.getServer().getPluginManager().registerEvents(new SpeedStrength(), this);
		this.getServer().getPluginManager().registerEvents(new Portals(), this);
	
		getCommand(command.uhc).setExecutor(command);
		getCommand(command.config).setExecutor(command);
		getCommand(command.host).setExecutor(command);
		getCommand(command.mod).setExecutor(command);
		getCommand(command.team).setExecutor(command);
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
