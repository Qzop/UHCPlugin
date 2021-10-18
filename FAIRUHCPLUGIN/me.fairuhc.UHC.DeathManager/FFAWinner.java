package me.fairuhc.UHC.DeathManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.Scatter.FFAScatter;
import me.fairuhc.UHC.stats.MySQLWins;
import net.md_5.bungee.api.ChatColor;

public class FFAWinner implements Listener
{
	public Main plugin;
	public MySQLWins win = new MySQLWins();
	public static HashMap<UUID, ArrayList<ItemStack>> inventories = new HashMap<UUID, ArrayList<ItemStack>>();
	public static HashMap<UUID, Location> deathlocation = new HashMap<UUID, Location>();
	
	public FFAWinner(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		Spectator spec = new Spectator(plugin);
		
		Player p = e.getEntity();
		
		if(Gamemanager.started == true && Gamemanager.teamsize == 1 && FFAScatter.ffa.contains(p.getUniqueId()))
		{
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			
			for(int i = 0; i < e.getDrops().size(); i++)
			{
				p.getWorld().dropItemNaturally(p.getLocation(), e.getDrops().get(i));
				items.add(e.getDrops().get(i));
			}
			
			inventories.put(p.getUniqueId(), items);
			deathlocation.put(p.getUniqueId(), p.getLocation());
			
			if(e.getEntity().getKiller() instanceof Player)
			{
				e.setDeathMessage(ChatColor.AQUA + p.getDisplayName() + ChatColor.RED + " was slain by " + ChatColor.AQUA + e.getEntity().getKiller().getDisplayName());
			}
			
			FFAScatter.ffa.remove(p.getUniqueId());
			p.spigot().respawn();
			spec.setSpec(p);
		}
	}
	
	public boolean isWinner()
	{
		boolean check = false;
		
		if(Gamemanager.teamsize == 1 && FFAScatter.ffa.size() == 1)
		{
			check = true;
			return check;
		}
		
		return check;
	}
	
	public void getWinner()
	{
		String winner = "";
		
		if(FFAScatter.ffa.size() == 1)
		{
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(FFAScatter.ffa.contains(p.getUniqueId()))
				{
					winner = p.getDisplayName();
				}
			}
			
			for(Player p : Bukkit.getOnlinePlayers())
			{
				p.sendMessage(ChatColor.YELLOW + "Congrats to " + ChatColor.AQUA + winner + ChatColor.YELLOW +" for winning the UHC!");
			}
			
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex user "+ winner + " group set winner");
			win.addwins(Bukkit.getPlayer(winner).getUniqueId(), 1);
		}
	}
}
