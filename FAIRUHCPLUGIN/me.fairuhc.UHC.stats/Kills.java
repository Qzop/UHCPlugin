package me.fairuhc.UHC.stats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Kills implements Listener
{
	MySQLKills kills = new MySQLKills();
	MySQLDeaths deaths = new MySQLDeaths();
	KDR kdr = new KDR();
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		Player killer;
		Player killed;
		
		if(e.getEntity() instanceof Player && e.getEntity().getWorld().getName().equals("uhc_world"))
		{
			killed = e.getEntity();
			deaths.addDeaths(killed.getUniqueId(), 1);
			kdr.updateKDR(killed);
			
			if(killed.getKiller() instanceof Player && killed.getKiller().getWorld().getName().equals("uhc_world"))
			{
				killer = killed.getKiller();
					
				kills.addKills(killer.getUniqueId(), 1);
				
				kdr.updateKDR(killer);
			}
			else if(killed.getKiller() == null)
			{
				if(killed.getLastDamageCause() instanceof EntityDamageByEntityEvent)
				{
					EntityDamageByEntityEvent dmgEvent = (EntityDamageByEntityEvent) killed.getLastDamageCause();
					
					if(dmgEvent.getDamager() instanceof Player)
					{
						killer = (Player) dmgEvent.getDamager();
						kills.addKills(killer.getUniqueId(), 1);
					}
				}
			}
			
			killed.spigot().respawn();
		}
	}
}
