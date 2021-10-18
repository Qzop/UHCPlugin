package me.fairuhc.UHC.stats;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import net.md_5.bungee.api.ChatColor;

public class GapsEaten implements Listener
{
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e)
	{
		Player p = e.getPlayer();
		MySQLGaps gaps = new MySQLGaps();
		MySQLGoldenHeads heads = new MySQLGoldenHeads();
		String name = e.getItem().getItemMeta().getDisplayName();
		
		if(p.getWorld().getName().equals("uhc"))
		{
			if(e.getItem().getType() == Material.GOLDEN_APPLE && name == null)
			{
				gaps.addGaps(p.getUniqueId(), 1);
			}
			else if(e.getItem().getType() == Material.GOLDEN_APPLE && name.equals(ChatColor.GOLD + "Golden Head"))
			{
				heads.addHeads(p.getUniqueId(), 1);
			}
		}
	}
}
