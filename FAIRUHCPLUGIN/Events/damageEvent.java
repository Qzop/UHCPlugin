package Events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Host;
import me.fairuhc.UHC.Scatter.FFAScatter;
import me.fairuhc.UHC.Scatter.TeamScatter;
import net.md_5.bungee.api.ChatColor;

public class damageEvent implements Listener
{
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e)
	{
		Player damaged;
		Player damager;
		
		if(e.getEntity().getType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER)
		{
			damaged = (Player) e.getEntity();
			damager = (Player) e.getDamager();
			
			if(Host.host.contains(damager.getUniqueId()))
			{
				e.setCancelled(true);
			}
		}
		else if((FFAScatter.scatter == true || TeamScatter.scatter == true))
		{
			e.setCancelled(true);
		}
	}
}
