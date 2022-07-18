package core.Kills;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import net.md_5.bungee.api.ChatColor;

public class Respawn implements Listener
{
	public void onRespawn(Player p, Player target)
	{
		Spectator spec = new Spectator();
		
		if(PlayerKills.spectator.contains(target.getUniqueId()))
		{
			if(PlayerKills.deathLocations.containsKey(target.getUniqueId()))
			{
				target.teleport(PlayerKills.deathLocations.get(target.getUniqueId()));
				spec.removeSpectator(target);
				
				p.sendMessage(ChatColor.GREEN + target.getDisplayName() + " has been respawned!");
				target.sendMessage(ChatColor.GREEN + "You have been respawned.");
				
				if(ConfigInventory.teamSize == 1)
				{
					Scatter.allPlayers.add(p.getUniqueId());
				}
				else
				{
					
				}
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You cannot respawn this player!");
		}
	}
}
