package me.fairuhc.UHC.stats;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PotionEvent implements Listener
{
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e)
	{
		Player p = e.getPlayer();
		MySQLPotions pot = new MySQLPotions();
		
		if(p.getWorld().getName().equals("uhc"))
		{
			if(e.getItem().getType() == Material.POTION)
			{
				pot.addPotions(p.getUniqueId(), 1);
			}
		}
	}
}
