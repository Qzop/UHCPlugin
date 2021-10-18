package me.fairuhc.UHC;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;

public class Heal implements Listener
{
	public void onHeal(Player p)
	{
		p.setHealth(p.getHealth() + (20.0 - p.getHealth()));
		
		p.sendMessage(ChatColor.GREEN + "You have been healed.");
	}
}
