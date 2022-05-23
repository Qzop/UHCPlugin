package core.mainPackage;

import core.Config.ConfigEvent;
import core.ConfigVariables.AppleRate;
import core.ConfigVariables.Horses;
import core.ConfigVariables.Portals;
import core.ConfigVariables.SpeedStrength;
import core.Scatter.ChatEvent;
import core.Scatter.Scatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

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
		this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
	
		getCommand(command.uhc).setExecutor(command);
		getCommand(command.config).setExecutor(command);
		getCommand(command.host).setExecutor(command);
		getCommand(command.mod).setExecutor(command);
		getCommand(command.team).setExecutor(command);
		getCommand(command.test).setExecutor(command);
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

		if(!Scatter.started && !Commands.scatter)
		{
			World world = Bukkit.getWorld("world");

			p.teleport(world.getSpawnLocation());
			p.removePotionEffect(PotionEffectType.BLINDNESS);
			p.removePotionEffect(PotionEffectType.JUMP);
			p.removePotionEffect(PotionEffectType.SPEED);
			p.removePotionEffect(PotionEffectType.SLOW);
			p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			p.removePotionEffect(PotionEffectType.ABSORPTION);
			p.removePotionEffect(PotionEffectType.CONFUSION);
			p.removePotionEffect(PotionEffectType.FAST_DIGGING);
			p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
			p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			p.removePotionEffect(PotionEffectType.HARM);
			p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
			p.removePotionEffect(PotionEffectType.HEAL);
			p.removePotionEffect(PotionEffectType.INVISIBILITY);
			p.removePotionEffect(PotionEffectType.POISON);
			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
			p.removePotionEffect(PotionEffectType.WATER_BREATHING);
			p.removePotionEffect(PotionEffectType.WEAKNESS);
			p.removePotionEffect(PotionEffectType.REGENERATION);
			p.removePotionEffect(PotionEffectType.HUNGER);
			p.removePotionEffect(PotionEffectType.WITHER);
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
			p.removePotionEffect(PotionEffectType.SATURATION);
		}
		else if(Commands.scatter)
		{
			p.kickPlayer(ChatColor.RED + "Scatter has started, you cannot join now.");
		}
	}
}
