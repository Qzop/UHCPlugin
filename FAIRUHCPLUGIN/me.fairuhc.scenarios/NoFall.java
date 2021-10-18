package me.fairuhc.UHC.scenarios;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class NoFall implements Listener
{
	@EventHandler
	public void noFall(EntityDamageEvent e)
	{
		if(ScenarioEvents.nofall == true)
		{
			if(e.getCause().equals(DamageCause.FALL))
			{
				e.setCancelled(true);
			}
		}
	}
}
