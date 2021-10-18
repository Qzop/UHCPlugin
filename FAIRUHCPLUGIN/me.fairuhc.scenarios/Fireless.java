package me.fairuhc.UHC.scenarios;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Fireless implements Listener
{
	@EventHandler
	public void fireless(EntityDamageEvent e)
	{
		if(ScenarioEvents.fireless == true)
		{
			if(e.getCause().equals(DamageCause.FIRE_TICK) || e.getCause().equals(DamageCause.FIRE) || e.getCause().equals(DamageCause.LAVA))
			{
				e.setCancelled(true);
			}
		}
	}
}
