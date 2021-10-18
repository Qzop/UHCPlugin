package me.fairuhc.UHC.scenarios;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NoClean implements Listener
{
	@EventHandler
	public void noClean(PlayerDeathEvent e)
	{
		Player p = e.getEntity();
		Player k = p.getKiller();
		
		if(ScenarioEvents.noclean == true)
		{
			k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 1));
			k.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "NoClean" + ChatColor.GRAY + "]" + ChatColor.GREEN + "You are now invincible for 20 seconds.");
		}
	}
}
