package core.Kills;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

public class HealthEvent implements Listener
{
	@EventHandler
	public void onHealthRegeneration(EntityRegainHealthEvent e)
	{
		if(e.getEntity() instanceof Player)
		{
			if(e.getRegainReason() == RegainReason.SATIATED)
			{
				e.setCancelled(true);
			}
		}
	}
}
