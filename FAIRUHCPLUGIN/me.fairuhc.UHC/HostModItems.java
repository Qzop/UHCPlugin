package me.fairuhc.UHC;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.fairuhc.UHC.Scatter.FFAScatter;
import me.fairuhc.UHC.teams.TeamManager;
import net.md_5.bungee.api.ChatColor;

public class HostModItems implements Listener
{
	public void getItems(Player p)
	{
		Inventory inv = p.getInventory();
		
		if(Gamemanager.started == false && p.getWorld().getName().equals("uhc_world") && Host.host.contains(p.getUniqueId()))
		{
			ItemStack rtele = new ItemStack(Material.PAPER, 1, (byte) 0);
			ItemMeta rtelemeta = rtele.getItemMeta();
			rtelemeta.setDisplayName(ChatColor.AQUA + "Random Telport");
			rtele.setItemMeta(rtelemeta);
			
			ItemStack zero = new ItemStack(Material.NETHER_STAR, 1, (byte) 0);
			ItemMeta zerometa = zero.getItemMeta();
			zerometa.setDisplayName(ChatColor.AQUA + "Teleport to Center");
			zero.setItemMeta(zerometa);
			
			ItemStack underground = new ItemStack(Material.CHEST, 1, (byte) 0);
			ItemMeta undergroundmeta = underground.getItemMeta();
			undergroundmeta.setDisplayName(ChatColor.AQUA + "Underground Players");
			underground.setItemMeta(undergroundmeta);
			
			inv.setItem(0, rtele);
			inv.setItem(4, zero);
			inv.setItem(8, underground);
		}
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) 
	{
        Player player = event.getPlayer();
        World world = Bukkit.getWorld("uhc_world");
        ArrayList<Player> random = new ArrayList<>();
        
        if((Host.host.contains(player.getUniqueId())) || Mod.mod.contains(player.getUniqueId()))
        {
        	 if((event.getAction() == Action.RIGHT_CLICK_BLOCK ||  event.getAction() == Action.RIGHT_CLICK_AIR) && (event.getAction() != Action.LEFT_CLICK_AIR || event.getAction() != Action.LEFT_CLICK_BLOCK))
         	{
         		if(event.hasItem() == true)
             	{
             		if(event.getItem().hasItemMeta() == true)
             		{
             			if(event.getItem().getItemMeta().getDisplayName() != null)
             			{
             				if(event.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Random Telport"))
             				{
             					if(Gamemanager.teamsize == 1)
            	            	{
            	            		Random rand = new Random(FFAScatter.ffa.size());
            	            		
            	            		player.teleport(Bukkit.getPlayer(FFAScatter.ffa.get(rand.nextInt())).getLocation());
            	            	}
            	            	else
            	            	{
            	            		TeamManager manager = new TeamManager(Main.plugin);
            	            		Random rand;
            	            		
            	            		for(Player p : Bukkit.getOnlinePlayers())
            	            		{
            	            			if(manager.hasTeam(p))
            	            			{
            	            				random.add(p);
            	            			}
            	            		}
            	            		
            	            		rand = new Random(random.size());
            	            		
            	            		player.teleport(random.get(rand.nextInt()).getLocation());
            	            	}
             				}
             				else if(event.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Teleport to Center"))
             				{
             					player.teleport(world.getSpawnLocation().add(0, 100, 0));
      
             				}
             				else if(event.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Underground Players"))
             				{
             					UndergroundPlayers under = new UndergroundPlayers();
             					
             					if(Gamemanager.started == false)
             					{
             						player.sendMessage(ChatColor.RED + "The game has not started yet!");
             					}
             					else
             					{
             						under.getUndergroundPlayers(player);
             					}
             				}
             			}
             		}
             	}
         	}
        }
    }
}
