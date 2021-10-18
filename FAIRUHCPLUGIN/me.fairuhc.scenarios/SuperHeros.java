package me.fairuhc.UHC.scenarios;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.fairuhc.UHC.Host;
import me.fairuhc.UHC.Mod;

public class SuperHeros implements Listener
{
	public static HashMap<UUID, PotionEffectType> potions = new HashMap<UUID, PotionEffectType>();
	public static HashMap<UUID, Integer> speed = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> jump = new HashMap<UUID, Integer>();
	
	public void superheroes(Player p)
	{	
		Random rand = new Random();
		
		if(!Host.host.contains(p.getUniqueId()) && !Mod.mod.contains(p.getUniqueId()))
		{
			int chance = rand.nextInt(60);
			
			if(chance <= 10)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0), true);
				potions.put(p.getUniqueId(), PotionEffectType.SPEED);
				speed.put(p.getUniqueId(), 0);
			}

			if(chance > 10 && chance <= 20)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 0), true);
				potions.put(p.getUniqueId(), PotionEffectType.JUMP);
				jump.put(p.getUniqueId(), 0);
			}
			
			if(chance > 20 && chance <= 30)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0), true);
				potions.put(p.getUniqueId(), PotionEffectType.DAMAGE_RESISTANCE);
			}
			
			if(chance > 30 && chance <= 40)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0), true);
				potions.put(p.getUniqueId(), PotionEffectType.FIRE_RESISTANCE);
			}
			
			if(chance > 40 && chance <= 50)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0), true);
				potions.put(p.getUniqueId(), PotionEffectType.INCREASE_DAMAGE);
			}
			
			if(chance > 50 && chance <= 60)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1), true);
				potions.put(p.getUniqueId(), PotionEffectType.SPEED);
				speed.put(p.getUniqueId(), 1);
			}
			
			if(chance > 60 && chance <= 70)
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1), true);
				potions.put(p.getUniqueId(), PotionEffectType.JUMP);
				jump.put(p.getUniqueId(), 1);
			}
		}
	}
}
