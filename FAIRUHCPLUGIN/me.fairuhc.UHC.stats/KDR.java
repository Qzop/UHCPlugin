package me.fairuhc.UHC.stats;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
 
public class KDR implements Listener
{
	MySQLDeaths death = new MySQLDeaths();
	MySQLKills kill = new MySQLKills();
	MySQLkdr ratio = new MySQLkdr();
	
	public void updateKDR(Player p)
	{
		double deaths = (double) death.getDeaths(p.getUniqueId());
		double kills = (double) kill.getKills(p.getUniqueId());
		
		if(deaths > 0)
		{
			double kdr = (kills/deaths);
			ratio.addKdr(p.getUniqueId(), kdr);
		}
		else
		{
			double kdr = (kills / 1);
			ratio.addKdr(p.getUniqueId(), kdr);
		}
	}
}
