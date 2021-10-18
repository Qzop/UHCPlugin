package Events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.scenarios.ScenarioEvents;
import net.md_5.bungee.api.ChatColor;

public class scenariosadmin implements Listener
{
	public static ArrayList<ItemStack> items = new ArrayList<ItemStack>();

	@SuppressWarnings("static-access")
	@EventHandler
	public void inventoryClick (InventoryClickEvent event)
	{
		@SuppressWarnings("unused")
		Player player = (Player) event.getWhoClicked();
		String prefix = ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Scenarios" + ChatColor.GRAY + " » ";
		Inventory open = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();
		
		if(open == null)
		{
			return;
		}
		else if(open.getName().equals(ChatColor.AQUA + "Scenarios"))
		{
			event.setCancelled(true);
			
			if(item == null || !item.hasItemMeta())
			{
				return;
			}
		}
		else if(open.getName().equals(ChatColor.AQUA + "Scenarios Admin")) // line 30 
		{
			event.setCancelled(true);
			
			if(item == null || !item.hasItemMeta())
			{
				return;
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "CutClean") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					ScenarioEvents.cutclean = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + CutClean");
					}
					
					items.add(item);
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "CutClean") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.cutclean = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - CutClean");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Timber") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.timber = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Timber");
					}
				}
				
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Timber") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.timber = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Timber");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "HasteyBoys") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.hastey = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + HasteyBoys");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "HasteyBoys") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.hastey = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - HasteyBoys");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Fireless") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.fireless = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Fireless");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Fireless") && items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.fireless = false;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.RED + " - Fireless");
					}
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "NoFall") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.nofall = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + NoFall");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "NoFall") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.nofall = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - NoFall");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "TimeBomb") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.timebomb = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + TimeBomb");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "TimeBomb") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.timebomb = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - TimeBomb");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Diamondless") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.diamondless = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Diamondless");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Diamondless") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.diamondless = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Diamondless");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Goldless") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.goldless = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Goldless");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Goldless") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.goldless = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Goldless");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Veinminer") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.veinminer = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Veinminer");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Veinminer") && items.contains(item))
			{
				items.remove(item);
				
				ScenarioEvents.veinminer = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Veinminer");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Golden Retriever") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.goldenretriever = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Golden Retriever");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Golden Retriever") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.goldenretriever = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Golden Retriever");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "NoClean") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.noclean = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + NoClean");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "NoClean") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.noclean = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - NoClean");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "BloodGold") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.bloodgold = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + BloodGold");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "BloodGold") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.bloodgold = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - BloodGold");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "BloodDiamonds") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.blooddia = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + BloodDiamonds");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "BloodDiamonds") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.blooddia = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - BloodDiamonds");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Chest") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.chest = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Chest");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Chest") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.chest = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Chest");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Safeloot") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.safeloot = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Safeloot");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Safeloot") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.safeloot = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Safeloot");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Triple Ores") && !items.contains(item))
			{
				
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.triple = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Triple Ores");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Triple Ores") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.triple = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Triple Ores");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Double Ores") && !items.contains(item))
			{
				
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.doubl = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Double Ores");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Double Ores") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.doubl = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Double Ores");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Anonymous") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.anon = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Anonymous");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Anonymous") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.anon = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Anonymous");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Bowless") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.bowless = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Bowless");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Bowless") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.bowless = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Bowless");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Rodless") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.rodless = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + Rodless");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Rodless") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.rodless = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - Rodless");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "SuperHeroes") && !items.contains(item))
			{
				if(items.size() == 9)
				{
					player.sendMessage(ChatColor.RED + "You cannot add more than 9 scenarios!");
					player.closeInventory();
				}
				else
				{
					items.add(item);
					
					ScenarioEvents.supheroes = true;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " + SuperHeroes");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "SuperHeroes") && items.contains(item))
			{
				items.remove(item);
				ScenarioEvents.supheroes = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.RED + " - SuperHeroes");
				}
			}
		}	
	}
}