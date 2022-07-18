package core.Scenarios;

import core.ScenariosInventory.ScenariosInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

public class Superheroes implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onDamageByHero(EntityDamageByEntityEvent e)
    {
        //nerfs strength and resistance
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player)
        {
            if(ScenariosInventory.superheroes)
            {
                Player Attacker = (Player) e.getDamager();
                Player Victim = (Player) e.getEntity();

                if(Attacker.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE) && (!Victim.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)))
                {
                    e.setDamage(e.getFinalDamage() * 0.80D);
                }
                else if(!Attacker.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE) && (Victim.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)))
                {
                    e.setDamage(e.getFinalDamage() * 1.3D);
                }
            }
        }

    }
}
