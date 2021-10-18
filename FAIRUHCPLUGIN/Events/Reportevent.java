package Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.Report;
import net.md_5.bungee.api.ChatColor;

public class Reportevent implements Listener
{
	public Main plugin;
	public String prefix = ChatColor.WHITE + "[" + ChatColor.RED + "" + ChatColor.BOLD + "Report" + ChatColor.WHITE + "]";
	
	public Reportevent(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void inventoryClick (InventoryClickEvent event)
	{
		@SuppressWarnings("unused")
		Report rep = new Report(plugin);
		
		Player player = (Player) event.getWhoClicked();
		ClickType clicktype = event.getClick();
		
		Inventory open = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();
		
		if(open == null)
		{
			return;
		}		
		else if(open.getName().equals(ChatColor.AQUA + "Report")) // line 30 
		{
			event.setCancelled(true);
			
			if(item == null || !item.hasItemMeta())
			{
				return;
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Combat Hacks"))
			{
				player.closeInventory();
				player.sendMessage(prefix + ChatColor.AQUA + " Thank you for your report. Staff members will review the report accordingly.");
				
				for(Player staff : Bukkit.getOnlinePlayers())
				{
					if(staff.hasPermission("report.see"))
					{
						staff.sendMessage(prefix + ChatColor.RED + " " + Report.target.getDisplayName() + ChatColor.AQUA + " has been reported for Combat Hacks");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "X-ray/Cavefinder"))
			{
				player.closeInventory();
				player.sendMessage(prefix + ChatColor.AQUA + " Thank you for your report. Staff members will review the report accordingly.");
				
				for(Player staff : Bukkit.getOnlinePlayers())
				{
					if(staff.hasPermission("report.see"))
					{
						staff.sendMessage(prefix + ChatColor.RED + " " + Report.target.getDisplayName() + ChatColor.AQUA + " has been reported for X-Ray/Cavefinder");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Exploiting bugs"))
			{
				player.closeInventory();
				player.sendMessage(prefix + ChatColor.AQUA + " Thank you for your report. Staff members will review the report accordingly.");
				
				for(Player staff : Bukkit.getOnlinePlayers())
				{
					if(staff.hasPermission("report.see"))
					{
						staff.sendMessage(prefix + ChatColor.RED + " " + Report.target.getDisplayName() + ChatColor.AQUA + " has been reported for Exploting bugs");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Fly Hacks"))
			{
				player.closeInventory();
				player.sendMessage(prefix + ChatColor.AQUA + " Thank you for your report. Staff members will review the report accordingly.");
				
				for(Player staff : Bukkit.getOnlinePlayers())
				{
					if(staff.hasPermission("report.see"))
					{
						staff.sendMessage(prefix + ChatColor.RED + " " + Report.target.getDisplayName() + ChatColor.AQUA + " has been reported for Fly Hacks");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "F5 Abuse"))
			{
				player.closeInventory();
				player.sendMessage(prefix + ChatColor.AQUA + " Thank you for your report. Staff members will review the report accordingly.");
				
				for(Player staff : Bukkit.getOnlinePlayers())
				{
					if(staff.hasPermission("report.see"))
					{
						staff.sendMessage(prefix + ChatColor.RED + " " + Report.target.getDisplayName() + ChatColor.AQUA + " has been reported for F5 Abuse");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Teaming in FFA"))
			{
				player.closeInventory();
				
				if(Gamemanager.teamsize == 1)
				{
					player.sendMessage(prefix + ChatColor.AQUA + " Thank you for your report. Staff members will review the report accordingly.");
					
					for(Player staff : Bukkit.getOnlinePlayers())
					{
						if(staff.hasPermission("report.see"))
						{
							staff.sendMessage(prefix + ChatColor.RED + " " + Report.target.getDisplayName() + ChatColor.AQUA + " has been reported for Teaming in FFA");
						}
					}
				}
				else
				{
					player.sendMessage(prefix + ChatColor.RED + " You may only report for teaming in FFA!");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Tracers"))
			{
				player.closeInventory();

				player.sendMessage(prefix + ChatColor.AQUA + " Thank you for your report. Staff members will review the report accordingly.");
					
				for(Player staff : Bukkit.getOnlinePlayers())
				{
					if(staff.hasPermission("report.see"))
					{
						staff.sendMessage(prefix + ChatColor.RED + " " + Report.target.getDisplayName() + ChatColor.AQUA + " has been reported for Tracers");
					}
				}
			}
		}
	}
}
