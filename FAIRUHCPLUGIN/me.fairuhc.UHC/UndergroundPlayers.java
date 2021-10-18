package me.fairuhc.UHC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;


public class UndergroundPlayers implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	private ArrayList<UUID> under = new ArrayList<UUID>();
	private ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	
	public void getUndergroundPlayers(Player host)
	{
		if(Gamemanager.started == true)
		{
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if((!Host.host.contains(p.getUniqueId()) || !Mod.mod.contains(p.getUniqueId())) && p.getWorld().getName().equals("uhc_world"))
				{
					if(p.getLocation().getBlockY() <= 32)
					{
						under.add(p.getUniqueId());
					}
					else if(under.contains(p.getUniqueId()) && p.getLocation().getY() > 32)
					{
						under.remove(p.getUniqueId());
					}
				}
			}
		}
		
		createInventory(host);
	}
	
	public void createInventory(Player host)
	{
		int numplayers = 0;
			
		for(int i = 0; i < under.size(); i++)
		{
			items.add(new ItemStack(Material.SKULL_ITEM, 1 , (byte) 0));
		}
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if((!Host.host.contains(p.getUniqueId()) || !Mod.mod.contains(p.getUniqueId())) && p.getWorld().getName().equals("uhc_world"))
			{
				numplayers++;
			}
		}
		
		if(numplayers % 9 != 0)
		{
			numplayers = numplayers + (numplayers % 9);
			System.out.println(numplayers);
		}
		else
		{
			return;
		}
		
		Inventory i = plugin.getServer().createInventory(null, 45, ChatColor.AQUA + "Underground Players");
		
		new BukkitRunnable()
		{
			public void run()
			{
				for(int j = 0; j < under.size(); j++)
				{
					ItemMeta pmeta = items.get(j).getItemMeta();
					pmeta.setDisplayName(ChatColor.AQUA + "" + Bukkit.getPlayer(under.get(j)).getDisplayName());
					List<String> plore = Arrays.asList(ChatColor.AQUA + "Click to teleport");
					pmeta.setLore(plore);
					items.get(j).setItemMeta(pmeta);
					
					i.setItem(j, items.get(j));
				}
			}
			
		}.runTaskTimer(plugin, 0, 20);
		
		host.openInventory(i);
	}
}

