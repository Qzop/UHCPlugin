package Events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.LobbyItems;
import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.Practiceitems;

public class Practice implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	public static ArrayList<UUID> prac = new ArrayList<UUID>();
	
	public void removeItems(Player p)
	{
		Practiceitems items = new Practiceitems();
		
		for(int i = 0; i < 40; i++)
		{
			p.getInventory().clear(i);
		}
		
		items.getItems(p);
		practice(p);
	}
	
	public void practice(Player p)
	{
		World world = Bukkit.getWorld("uhc_practice");		
		p.teleport(world.getSpawnLocation().add(0,100,0));
	}
	
	public void removePrac(Player p)
	{
		LobbyItems items = new LobbyItems();
		World world = Bukkit.getWorld("uhc_lobby");
		prac.remove(p.getUniqueId());
		
		for(int i = 0; i < 40; i++)
		{
			p.getInventory().clear(i);
		}
		
		items.getItems(p);
		p.teleport(world.getSpawnLocation().add(0,100,0));
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e)
	{
		if(e.getEntity() instanceof Player && Gamemanager.started == false)
		{
			Player p = (Player) e.getEntity();
			
			if(prac.contains(p.getUniqueId()) && p.getWorld().getName().equals("uhc_practice"))
			{
				e.getDrops().clear();
				p.spigot().respawn();
			}
		}
	}
	
	@EventHandler
	public void respawn(PlayerRespawnEvent e)
	{
		Practiceitems items = new Practiceitems();
		World world = Bukkit.getWorld("uhc_practice");
		
		e.setRespawnLocation(world.getSpawnLocation().add(0, 100, 0));
		
		new BukkitRunnable()
		{
			int i = 1;
			
			public void run()
			{
				if(i == 0)
				{
					if(prac.contains(e.getPlayer().getUniqueId()))
					{
						items.getItems(e.getPlayer());
						
						cancel();
						return;
					}
				}
				
				i--;
			}
		}.runTaskTimer(Main.plugin, 0, 1);
		
	}
}