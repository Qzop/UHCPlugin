package Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.fairuhc.UHC.Config;
import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.border;
import net.md_5.bungee.api.ChatColor;

public class configadmin implements Listener
{
	public Main plugin;
	public Config c = new Config();
	public AsyncPlayerChatEvent e;
	String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC" + ChatColor.WHITE + "]";
	
	public configadmin(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void inventoryClick (InventoryClickEvent event)
	{
		@SuppressWarnings("unused")
		Player player = (Player) event.getWhoClicked();
		ClickType clicktype = event.getClick();
		
		Inventory open = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();
		
		if(open == null)
		{
			return;
		}		
		else if(open.getName().equals(ChatColor.AQUA + "Config Admin")) // line 30 
		{
			event.setCancelled(true);
			
			if(item == null || !item.hasItemMeta())
			{
				return;
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Nether " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off") && c.nether != true)
			{
				c.nether = true;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Nether has been turned " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Nether " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On") && c.nether == true)
			{
				c.nether = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Nether has been turned" + ChatColor.RED + "" + ChatColor.BOLD + " Off");
				}
			}
			
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Border Size " + ChatColor.GRAY + "» " + ChatColor.WHITE + border.bordersize) && Gamemanager.started != true)
			{
				if(clicktype == ClickType.LEFT)
				{
					if(border.bordersize >= 500)
					{
						border.bordersize = (border.bordersize + 500);
					}
					else if(border.bordersize == 100)
					{
						border.bordersize = border.bordersize + 400;
					}
					else if(border.bordersize < 100)
					{
						border.bordersize = border.bordersize * 2;
					}
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " Border size has been changed to " + ChatColor.AQUA +  border.bordersize);
					}
				}
				else if(clicktype == ClickType.RIGHT)
				{
					if(border.bordersize >= 1000)
					{
						border.bordersize = (border.bordersize - 500);
						
						for(Player p : Bukkit.getOnlinePlayers())
						{
							p.sendMessage(prefix + ChatColor.GREEN + " Border size has been changed to " + ChatColor.AQUA +  border.bordersize);
						}
					}
					else if(border.bordersize == 500)
					{
						border.bordersize = (border.bordersize - 400);
						
						for(Player p : Bukkit.getOnlinePlayers())
						{
							p.sendMessage(prefix + ChatColor.GREEN + " Border size has been changed to " + ChatColor.AQUA +  border.bordersize);
						}
					}
					else if(border.bordersize <= 100 && border.bordersize != 25)
					{
						border.bordersize = (border.bordersize / 2);
						
						for(Player p : Bukkit.getOnlinePlayers())
						{
							p.sendMessage(prefix + ChatColor.GREEN + " Border size has been changed to " + ChatColor.AQUA +  border.bordersize);
						}
					}
					else if(border.bordersize == 25)
					{
						player.sendMessage(ChatColor.RED + "You cannot go lower than 25!");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Border Size " + ChatColor.GRAY + "» " + border.bordersize) && Gamemanager.started == true)
			{
				player.closeInventory();
				player.sendMessage(ChatColor.RED + "You cannot change the border size when the game starts!");
			}
			if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Team Size " + ChatColor.GRAY + "» " + ChatColor.WHITE + " FFA") && Gamemanager.started != true)
			{
				if(clicktype == ClickType.LEFT)
				{
					Gamemanager.teamsize++;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " Team size has been changed to " + ChatColor.AQUA + Gamemanager.teamsize);
					}
				}
				else if(clicktype == ClickType.RIGHT)
				{
					player.sendMessage(ChatColor.RED + "You cannot go lower than FFA!");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Team Size " + ChatColor.GRAY + "» " + ChatColor.WHITE + Gamemanager.teamsize) && Gamemanager.started != true)
			{
				if(clicktype == ClickType.LEFT)
				{
					Gamemanager.teamsize++;
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " Team size has been changed to " + ChatColor.AQUA +  Gamemanager.teamsize);
					}
				}
				else if(clicktype == ClickType.RIGHT)
				{
					if(Gamemanager.teamsize == 1)
					{
						player.sendMessage(ChatColor.RED + "You cannot go lower than FFA!");
					}
					else
					{
						Gamemanager.teamsize--;
					}
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						p.sendMessage(prefix + ChatColor.GREEN + " Team size has been changed to " + ChatColor.AQUA +  Gamemanager.teamsize);
					}
					
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Speed I " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On"))
			{
				Config.speedone = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Speed I has been turned " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Speed I " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off"))
			{
				Config.speedone = true;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Speed I has been turned " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Speed II " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On"))
			{
				Config.speedtwo = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Speed II has been turned " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Speed II " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off"))
			{
				Config.speedtwo = true;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Speed II has been turned " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Strength I " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On"))
			{
				Config.strengthone = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Strength I has been turned " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Strength I " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off"))
			{
				Config.strengthone = true;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Strength I has been turned " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Strength II " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On"))
			{
				Config.strengthtwo = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Strength II has been turned " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Strength II " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off"))
			{
				Config.strengthtwo = true;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Strength II has been turned " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Passive Mobs " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On"))
			{
				Config.mobs = false;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Passive mobs have been turned " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Passive Mobs " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off"))
			{
				Config.mobs = true;
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					p.sendMessage(prefix + ChatColor.AQUA + " Passive mobs have been turned " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Apple Rate " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + Config.applerate + "%"))
			{
				if(clicktype == ClickType.LEFT)
				{
					if(Config.applerate == 10)
					{
						player.closeInventory();
						player.sendMessage(ChatColor.RED + "You cannot make apple rates higher than 10%!");
					}
					else
					{
						Config.applerate++;
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Apple rate is now " + ChatColor.GREEN + Config.applerate + "%");
					}
				}
				else if(clicktype == ClickType.RIGHT)
				{
					if(Config.applerate == 1)
					{
						player.closeInventory();
						player.sendMessage(ChatColor.RED + "You cannot make apple rates lower than 1%!");
					}
					else
					{
						Config.applerate--;
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Apple rate is now " + ChatColor.GREEN + Config.applerate + "%");
					}
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Team Damage " +  ChatColor.GRAY + "» " + ChatColor.BOLD + ChatColor.GREEN + "ON"))
			{
				if(clicktype == ClickType.LEFT)
				{
					player.closeInventory();
					Config.tk = false;
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Team Damage is now " + ChatColor.BOLD + ChatColor.RED + "OFF");
				}
				else if(clicktype == ClickType.RIGHT)
				{
					player.closeInventory();
					Config.tk = false;
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Team Damage is now " + ChatColor.BOLD + ChatColor.RED + "OFF");
				}
			}
			else if(item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Team Damage " +  ChatColor.GRAY + "» " + ChatColor.BOLD + ChatColor.RED + "OFF"))
			{
				if(clicktype == ClickType.LEFT)
				{
					player.closeInventory();
					Config.tk = true;
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Team Damage is now " + ChatColor.BOLD + ChatColor.GREEN + "ON");
				}
				else if(clicktype == ClickType.RIGHT)
				{
					player.closeInventory();
					Config.tk = true;
					Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Team Damage is now " + ChatColor.BOLD + ChatColor.GREEN + "ON");
				}
			}
		}
		else if(open.getName().equals(ChatColor.AQUA + "Config")) // line 30 
		{
			event.setCancelled(true);
			
			if(item == null || !item.hasItemMeta())
			{
				return;
			}
		}
	}
}