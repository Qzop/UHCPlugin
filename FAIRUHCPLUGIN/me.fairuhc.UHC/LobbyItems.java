package me.fairuhc.UHC;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.fairuhc.UHC.scenarios.Scenarios;
import me.fairuhc.UHC.stats.Stats;
import net.md_5.bungee.api.ChatColor;

public class LobbyItems implements Listener
{
	public HostModItems hostitems = new HostModItems();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		
		if(Gamemanager.started == false)
		{
			for(int i = 0; i < 40; i++)
			{
				p.getInventory().clear(i);
			}
			
			if(Host.host.contains(p.getUniqueId()) || Mod.mod.contains(p.getUniqueId()))
			{
				hostitems.getItems(p);
			}
			else
			{
				getItems(p);
			}
		}
	}
	
	public void getItems(Player p)
	{
		Inventory inv = p.getInventory();

		if(Gamemanager.started == false)
		{
			ItemStack stats = new ItemStack(Material.NETHER_STAR, 1, (byte) 0);
			ItemMeta statsmeta = stats.getItemMeta();
			statsmeta.setDisplayName(ChatColor.AQUA + "Stats");
			stats.setItemMeta(statsmeta);
			
			ItemStack config = new ItemStack(Material.BOOK, 1, (byte) 0);
			ItemMeta configmeta = config.getItemMeta();
			configmeta.setDisplayName(ChatColor.AQUA + "Config");
			config.setItemMeta(configmeta);
			
			ItemStack scenarios = new ItemStack(Material.COMPASS, 1, (byte) 0);
			ItemMeta scenariosmeta = scenarios.getItemMeta();
			scenariosmeta.setDisplayName(ChatColor.AQUA + "Scenarios");
			scenarios.setItemMeta(scenariosmeta);
			
			inv.setItem(0, scenarios);
			inv.setItem(4, config);
			inv.setItem(8, stats);
		}
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) 
	{
		Stats s = new Stats();
		Scenarios scen = new Scenarios();
		Config con = new Config();
        Player player = event.getPlayer();
        
        if(Gamemanager.started == false)
        {
        	if((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && (event.getAction() != Action.LEFT_CLICK_AIR || event.getAction() != Action.LEFT_CLICK_BLOCK))
        	{
        		if(event.hasItem() == true)
            	{
            		if(event.getItem().hasItemMeta() == true)
            		{
            			if(event.getItem().getItemMeta().getDisplayName() != null)
            			{
            				if(event.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Stats"))
            				{
            					s.stats(player);
            				}
            				else if(event.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Config"))
            				{
            					con.config(player);
            				}
            				else if(event.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Scenarios"))
            				{
            					scen.showScen(player);
            				}
            			}
            		}
            	}
        	}
        }
    }
}
