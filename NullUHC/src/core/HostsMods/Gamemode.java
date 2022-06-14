package core.HostsMods;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;

public class Gamemode implements Listener
{
	public static String gamemodePrefix = ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + "GameMode" + ChatColor.GRAY + "]";
	
	public void setGamemode(Player p, int i)
	{
		if(i == 0)
		{
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage(gamemodePrefix + ChatColor.GREEN + " You are now in Survival Mode.");
		}
		else if(i == 1)
		{
			p.setGameMode(GameMode.CREATIVE);
			p.setAllowFlight(true);
			p.setFlying(true);
			p.sendMessage(gamemodePrefix + ChatColor.GREEN + " You are now in Creative Mode.");
		}
		else if(i == 2)
		{
			p.setGameMode(GameMode.ADVENTURE);
			p.sendMessage(gamemodePrefix + ChatColor.GREEN + " You are now in Adventure Mode.");
		}
		else if(i == 3)
		{
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage(gamemodePrefix + ChatColor.GREEN + " You are now in Spectator Mode.");
		}
		else 
		{
			p.sendMessage(gamemodePrefix + ChatColor.RED + " Usage: /gm (0/1/2/3)");
		}
	}
}
