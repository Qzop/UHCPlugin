package me.fairuhc.UHC;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class Config 
{
	public Plugin plugin = Main.getPlugin(Main.class);
	public static boolean nether = true;
	public static boolean speedone = true;
	public static boolean speedtwo = true;
	public static boolean strengthone = true;
	public static boolean strengthtwo = true;
	public static boolean mobs = true;
	public static boolean tk = true;
	public static boolean heads = true;
	public static boolean enchantedapple = false;
	public static int applerate = 1;
	
	public void config(Player p)
	{
		Inventory i = plugin.getServer().createInventory(null, 45, ChatColor.AQUA + "Config");;
		
		new BukkitRunnable()
		{
			public void run()
			{
				ItemStack net = new ItemStack(Material.NETHERRACK, 1 , (byte) 15);
				ItemMeta nethermeta = net.getItemMeta();
				if(nether == true)
				{
					nethermeta.setDisplayName(ChatColor.AQUA + "Nether" +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
				else
				{
					nethermeta.setDisplayName(ChatColor.AQUA + "Nether" +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
				}

				net.setItemMeta(nethermeta);
				
				ItemStack bord = new ItemStack(Material.BEDROCK, 1 , (byte) 15);
				ItemMeta bordmeta = bord.getItemMeta();
				bordmeta.setDisplayName(ChatColor.AQUA + "Border Size " + ChatColor.GRAY + "» " + ChatColor.WHITE + border.bordersize);
				bord.setItemMeta(bordmeta);
				
				ItemStack team = new ItemStack(Material.DIAMOND_HELMET, 1 , (byte) 0);
				ItemMeta teammeta = team.getItemMeta();
				if(Gamemanager.teamsize == 1)
				{
					teammeta.setDisplayName(ChatColor.AQUA + "Team Size " + ChatColor.GRAY + "» " + ChatColor.WHITE + " FFA");
				}
				else
				{
					teammeta.setDisplayName(ChatColor.AQUA + "Team Size " + ChatColor.GRAY + "» " + ChatColor.WHITE + Gamemanager.teamsize);
				}
				team.setItemMeta(teammeta);
				
				Potion sI = new Potion(PotionType.SPEED, 1);
				ItemStack speedI = sI.toItemStack(1);
				ItemMeta sImeta = speedI.getItemMeta();
				
				if(speedone == true)
				{
					sImeta.setDisplayName(ChatColor.AQUA + "Speed I " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
				else
				{
					sImeta.setDisplayName(ChatColor.AQUA + "Speed I " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
				}
				
				speedI.setItemMeta(sImeta);
				
				Potion sII  = new Potion(PotionType.SPEED, 2);
				ItemStack speedII = sII.toItemStack(1);
				ItemMeta sIImeta = speedII.getItemMeta();
				
				if(speedtwo == true)
				{
					sIImeta.setDisplayName(ChatColor.AQUA + "Speed II " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
				else
				{
					sIImeta.setDisplayName(ChatColor.AQUA + "Speed II " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
				}
				
				speedII.setItemMeta(sIImeta);
				
				Potion stI = new Potion(PotionType.STRENGTH, 1);
				ItemStack strengthI = stI.toItemStack(1);
				ItemMeta stImeta = strengthI.getItemMeta();
				
				if(strengthone == true)
				{
					stImeta.setDisplayName(ChatColor.AQUA + "Strength I " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
				else
				{
					stImeta.setDisplayName(ChatColor.AQUA + "Strength I " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
				}
				
				strengthI.setItemMeta(stImeta);
				
				Potion stII  = new Potion(PotionType.STRENGTH, 2);
				ItemStack strengthII = stII.toItemStack(1);
				ItemMeta stIImeta = strengthII.getItemMeta();
				
				if(strengthtwo == true)
				{
					stIImeta.setDisplayName(ChatColor.AQUA + "Strength II " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
				else
				{
					stIImeta.setDisplayName(ChatColor.AQUA + "Strength II " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
				}
				
				strengthII.setItemMeta(stIImeta);
				
				ItemStack passivemobs = new ItemStack(Material.SKULL_ITEM, 1, (byte) 1);
				ItemMeta mobmeta = passivemobs.getItemMeta();
				
				if(mobs == true)
				{
					mobmeta.setDisplayName(ChatColor.AQUA + "Passive Mobs " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
				}
				else
				{
					mobmeta.setDisplayName(ChatColor.AQUA + "Passive Mobs " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
				}
				
				passivemobs.setItemMeta(mobmeta);
				
				ItemStack apple = new ItemStack(Material.APPLE, 1, (byte) 1);
				ItemMeta applemeta = apple.getItemMeta();
				applemeta.setDisplayName(ChatColor.AQUA + "Apple Rate " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + Config.applerate + "%");
				apple.setItemMeta(applemeta);
				
				ItemStack teamkill = new ItemStack(Material.DIAMOND_SWORD, 1);
				ItemMeta tkmeta = teamkill.getItemMeta();
				
				if(tk == true)
				{
					tkmeta.setDisplayName(ChatColor.AQUA + "Team Damage " +  ChatColor.GRAY + "» " + ChatColor.BOLD + ChatColor.GREEN + "ON");
				}
				else
				{
					tkmeta.setDisplayName(ChatColor.AQUA + "Team Damage " +  ChatColor.GRAY + "» " + ChatColor.BOLD + ChatColor.RED + "OFF");
				}
				
				teamkill.setItemMeta(tkmeta);
				
				i.setItem(0, team);
				i.setItem(1, teamkill);
				i.setItem(9, net);
				i.setItem(10, bord);
				i.setItem(18, speedI);
				i.setItem(19, speedII);
				i.setItem(20, strengthI);
				i.setItem(21, strengthII);
				i.setItem(27, passivemobs);
				i.setItem(28, apple);
				
				
				
			}
			
		}.runTaskTimer(plugin, 0, 1);
		
		p.openInventory(i);
	}
	
	public void configAdmin(Player p)
	{
		if(p.hasPermission("config.edit"))
		{
			Inventory i = plugin.getServer().createInventory(null, 45, ChatColor.AQUA + "Config Admin");
			
			new BukkitRunnable()
			{
				public void run()
				{
					ItemStack net = new ItemStack(Material.NETHERRACK, 1 , (byte) 15);
					ItemMeta nethermeta = net.getItemMeta();
					
					if(nether == true)
					{
						nethermeta.setDisplayName(ChatColor.AQUA + "Nether " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
					}
					else
					{
						nethermeta.setDisplayName(ChatColor.AQUA + "Nether " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
					}
					List<String> netlore = Arrays.asList(ChatColor.AQUA + "Click to turn nether on or off.");
					nethermeta.setLore(netlore);
					net.setItemMeta(nethermeta);
					
					ItemStack bord = new ItemStack(Material.BEDROCK, 1 , (byte) 15);
					ItemMeta bordmeta = bord.getItemMeta();
					bordmeta.setDisplayName(ChatColor.AQUA + "Border Size " + ChatColor.GRAY + "» " + ChatColor.WHITE + border.bordersize);
					List<String> bordlore = Arrays.asList(ChatColor.AQUA + "Right click decreases and Left click increases");
					bordmeta.setLore(bordlore);
					bord.setItemMeta(bordmeta);
					
					ItemStack team = new ItemStack(Material.DIAMOND_HELMET, 1 , (byte) 0);
					ItemMeta teammeta = team.getItemMeta();
					if(Gamemanager.teamsize == 1)
					{
						teammeta.setDisplayName(ChatColor.AQUA + "Team Size " + ChatColor.GRAY + "» " + ChatColor.WHITE + " FFA");
					}
					else
					{
						teammeta.setDisplayName(ChatColor.AQUA + "Team Size " + ChatColor.GRAY + "» " + ChatColor.WHITE + Gamemanager.teamsize);
					}
					List<String> teamlore = Arrays.asList(ChatColor.AQUA + "Right click decreases and Left click increases");
					teammeta.setLore(teamlore);
					team.setItemMeta(teammeta);
					
					Potion sI = new Potion(PotionType.SPEED, 1);
					ItemStack speedI = sI.toItemStack(1);
					ItemMeta sImeta = speedI.getItemMeta();
					
					if(speedone == true)
					{
						sImeta.setDisplayName(ChatColor.AQUA + "Speed I " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
					}
					else
					{
						sImeta.setDisplayName(ChatColor.AQUA + "Speed I " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
					}
					List<String> sIlore = Arrays.asList(ChatColor.AQUA + "Click to turn Speed I on or off.");
					sImeta.setLore(sIlore);
					speedI.setItemMeta(sImeta);
					
					Potion sII  = new Potion(PotionType.SPEED, 2);
					ItemStack speedII = sII.toItemStack(1);
					ItemMeta sIImeta = speedII.getItemMeta();
					
					if(speedtwo == true)
					{
						sIImeta.setDisplayName(ChatColor.AQUA + "Speed II " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
					}
					else
					{
						sIImeta.setDisplayName(ChatColor.AQUA + "Speed II " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
					}
					List<String> sIIlore = Arrays.asList(ChatColor.AQUA + "Click to turn Speed II on or off.");
					sIImeta.setLore(sIIlore);
					speedII.setItemMeta(sIImeta);
					
					Potion stI = new Potion(PotionType.STRENGTH, 1);
					ItemStack strengthI = stI.toItemStack(1);
					ItemMeta stImeta = strengthI.getItemMeta();
					
					if(strengthone == true)
					{
						stImeta.setDisplayName(ChatColor.AQUA + "Strength I " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
					}
					else
					{
						stImeta.setDisplayName(ChatColor.AQUA + "Strength I " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
					}
					List<String> stIlore = Arrays.asList(ChatColor.AQUA + "Click to turn Strength I on or off.");
					stImeta.setLore(stIlore);
					strengthI.setItemMeta(stImeta);
					
					Potion stII  = new Potion(PotionType.STRENGTH, 2);
					ItemStack strengthII = stII.toItemStack(1);
					ItemMeta stIImeta = strengthII.getItemMeta();
					
					if(strengthtwo == true)
					{
						stIImeta.setDisplayName(ChatColor.AQUA + "Strength II " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
					}
					else
					{
						stIImeta.setDisplayName(ChatColor.AQUA + "Strength II " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
					}
					List<String> stIIlore = Arrays.asList(ChatColor.AQUA + "Click to turn Strength II on or off.");
					stIImeta.setLore(stIIlore);
					strengthII.setItemMeta(stIImeta);
					
					ItemStack passivemobs = new ItemStack(Material.SKULL_ITEM, 1, (byte) 1);
					ItemMeta mobmeta = passivemobs.getItemMeta();
					
					if(mobs == true)
					{
						mobmeta.setDisplayName(ChatColor.AQUA + "Passive Mobs " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + ChatColor.BOLD + "On");
					}
					else
					{
						mobmeta.setDisplayName(ChatColor.AQUA + "Passive Mobs " +  ChatColor.GRAY + "» " + ChatColor.RED + "" + ChatColor.BOLD + "Off");
					}
					
					List<String> pmobslore = Arrays.asList(ChatColor.AQUA + "Click to turn Passive Mobs on or off.");
					mobmeta.setLore(pmobslore);
					passivemobs.setItemMeta(mobmeta);
					
					ItemStack apple = new ItemStack(Material.APPLE, 1, (byte) 1);
					ItemMeta applemeta = apple.getItemMeta();
					applemeta.setDisplayName(ChatColor.AQUA + "Apple Rate " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "" + Config.applerate + "%");
					List<String> applelore = Arrays.asList(ChatColor.AQUA + "Left click to increase Right click to decrease");
					applemeta.setLore(applelore);
					apple.setItemMeta(applemeta);
					
					ItemStack teamkill = new ItemStack(Material.DIAMOND_SWORD, 1);
					ItemMeta tkmeta = teamkill.getItemMeta();
					
					if(tk == true)
					{
						tkmeta.setDisplayName(ChatColor.AQUA + "Team Damage " +  ChatColor.GRAY + "» " + ChatColor.BOLD + ChatColor.GREEN + "ON");
					}
					else
					{
						tkmeta.setDisplayName(ChatColor.AQUA + "Team Damage " +  ChatColor.GRAY + "» " + ChatColor.BOLD + ChatColor.RED + "OFF");
					}
					
					List<String> tklore = Arrays.asList(ChatColor.AQUA + "Click to turn Team Damage on or off.");
					tkmeta.setLore(tklore);
					teamkill.setItemMeta(tkmeta);
					
					ItemStack goldenheads = GoldenHead.head;
					ItemMeta gheadmeta = goldenheads.getItemMeta();
					
					if(heads == true)
					{
						gheadmeta.setDisplayName(ChatColor.AQUA + "Golden Heads " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "ON");
					}
					else
					{
						gheadmeta.setDisplayName(ChatColor.AQUA + "Golden Heads " +  ChatColor.GRAY + "» " + ChatColor.RED + "OFF");
					}
					
					List<String> gheadlore = Arrays.asList(ChatColor.AQUA + "Click to turn ON or OFF");
					gheadmeta.setLore(gheadlore);
					goldenheads.setItemMeta(gheadmeta);
					
					ItemStack enchantedghead = new ItemStack(Material.GOLDEN_APPLE, 1);
					ItemMeta enchantedmeta = enchantedghead.getItemMeta();
					
					if(enchantedapple == true)
					{
						enchantedmeta.setDisplayName(ChatColor.AQUA + "Enchanted Golden Apples " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "ON");
					}
					else
					{
						enchantedmeta.setDisplayName(ChatColor.AQUA + "Enchanted Golden Apples " +  ChatColor.GRAY + "» " + ChatColor.RED + "OFF");
					}
					
					List<String> enchantedlore = Arrays.asList(ChatColor.AQUA + "Click to turn ON or OFF");
					enchantedmeta.setLore(enchantedlore);
					enchantedghead.setItemMeta(enchantedmeta);
					
					/*ItemStack enchantedghead = new ItemStack(Material.GOLDEN_APPLE, 1);
					ItemMeta enchantedmeta = enchantedghead.getItemMeta();
					
					if(enchantedapple == true)
					{
						enchantedmeta.setDisplayName(ChatColor.AQUA + "Enchanted Golden Apples " +  ChatColor.GRAY + "» " + ChatColor.GREEN + "ON");
					}
					else
					{
						enchantedmeta.setDisplayName(ChatColor.AQUA + "Enchanted Golden Apples " +  ChatColor.GRAY + "» " + ChatColor.RED + "OFF");
					}
					
					List<String> enchantedlore = Arrays.asList(ChatColor.AQUA + "Click to turn ON or OFF");
					enchantedmeta.setLore(enchantedlore);
					enchantedghead.setItemMeta(enchantedmeta);*/
					
					i.setItem(0, team);
					i.setItem(1, teamkill);
					i.setItem(9, net);
					i.setItem(10, bord);
					i.setItem(18, speedI);
					i.setItem(19, speedII);
					i.setItem(20, strengthI);
					i.setItem(21, strengthII);
					i.setItem(22, goldenheads);
					i.setItem(23, enchantedghead);
					i.setItem(27, passivemobs);
					i.setItem(28, apple);
					
				}
				
			}.runTaskTimer(plugin, 0, 1);
			
			p.openInventory(i);
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You do not have permission.");
		}
	}
}
