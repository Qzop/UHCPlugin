package me.fairuhc.UHC.stats;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Main;
import net.md_5.bungee.api.ChatColor;

public class Stats implements Listener
{
	Main plugin = Main.getPlugin(Main.class);
	MySQLKills k = new MySQLKills();
	MySQLDeaths d = new MySQLDeaths();
	MySQLGaps g = new MySQLGaps();
	MySQLGoldenHeads h = new MySQLGoldenHeads();
	MySQLkdr kdr = new MySQLkdr();
	MySQLNether n = new MySQLNether();
	MySQLOresMined ore = new MySQLOresMined();
	MySQLPlayTime t = new MySQLPlayTime();
	MySQLPotions pot = new MySQLPotions();
	MySQLWins w = new MySQLWins();
	
	public void stats(Player p)
	{
		Inventory i = plugin.getServer().createInventory(null, 27, ChatColor.AQUA + p.getDisplayName() + "'s Stats");
		DecimalFormat format = new DecimalFormat("0.00");
			
		new BukkitRunnable()
		{
			public void run()
			{
				ItemStack kills = new ItemStack(Material.DIAMOND_SWORD, 1 , (byte) 0);
				ItemMeta killsmeta = kills.getItemMeta();
				killsmeta.setDisplayName(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» " + ChatColor.AQUA +  k.getKills(p.getUniqueId()));
				kills.setItemMeta(killsmeta);
				
				ItemStack wins = new ItemStack(Material.NETHER_STAR, 1 , (byte) 0);
				ItemMeta winsmeta = wins.getItemMeta();
				winsmeta.setDisplayName(ChatColor.AQUA + "Wins " + ChatColor.GRAY + "» " + ChatColor.AQUA + w.getwins(p.getUniqueId()));
				wins.setItemMeta(winsmeta);
				
				ItemStack death = new ItemStack(Material.SKULL_ITEM, 1 , (byte) 0);
				ItemMeta deathmeta = death.getItemMeta();
				deathmeta.setDisplayName(ChatColor.AQUA + "Deaths " + ChatColor.GRAY + "» " + ChatColor.AQUA + d.getDeaths(p.getUniqueId()));
				death.setItemMeta(deathmeta);
				
				ItemStack gaps = new ItemStack(Material.GOLDEN_APPLE, 1 , (byte) 0);
				ItemMeta gapsmeta = gaps.getItemMeta();
				gapsmeta.setDisplayName(ChatColor.AQUA + "Golden Apples Eaten " + ChatColor.GRAY + "» " + ChatColor.AQUA + g.getGaps(p.getUniqueId()));
				gaps.setItemMeta(gapsmeta);
				
				ItemStack heads = new ItemStack(Material.GOLDEN_APPLE, 1 , (byte) 0);
				ItemMeta headsmeta = heads.getItemMeta();
				headsmeta.setDisplayName(ChatColor.AQUA + "Golden Heads Eaten " + ChatColor.GRAY + "» " + ChatColor.AQUA + h.getHeads(p.getUniqueId()));
				heads.setItemMeta(headsmeta);
				
				ItemStack kd = new ItemStack(Material.PAPER, 1 , (byte) 0);
				ItemMeta kdmeta = kd.getItemMeta();
				String KDR =  format.format(kdr.getKdr(p.getUniqueId()));
				kdmeta.setDisplayName(ChatColor.AQUA + "KDR " + ChatColor.GRAY + "» " + ChatColor.AQUA + KDR);
				kd.setItemMeta(kdmeta);
				
				ItemStack nether = new ItemStack(Material.NETHERRACK, 1 , (byte) 0);
				ItemMeta nethermeta = nether.getItemMeta();
				nethermeta.setDisplayName(ChatColor.AQUA + "Nether Entries " + ChatColor.GRAY + "» " + ChatColor.AQUA + n.getEntries(p.getUniqueId()));
				nether.setItemMeta(nethermeta);
				
				ItemStack ores = new ItemStack(Material.DIAMOND_ORE, 1 , (byte) 0);
				ItemMeta oresmeta = ores.getItemMeta();
				oresmeta.setDisplayName(ChatColor.AQUA + "Ores Mined: ");
				oresmeta.setLore(Arrays.asList(ChatColor.AQUA + "Diamond " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getDiamond(p.getUniqueId()),ChatColor.AQUA + "Gold " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getGold(p.getUniqueId()),
						ChatColor.AQUA + "Iron Ore " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getIron(p.getUniqueId()),ChatColor.AQUA + "Coal " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getCoal(p.getUniqueId()),ChatColor.AQUA + "Lapis " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getLapis(p.getUniqueId()),
						ChatColor.AQUA + "Redstone " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getRedstone(p.getUniqueId()), ChatColor.AQUA + "Quartz " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getQuartz(p.getUniqueId())));
				ores.setItemMeta(oresmeta);
				
				ItemStack time = new ItemStack(Material.WATCH, 1 , (byte) 0);
				ItemMeta timemeta = time.getItemMeta();
				
				int hours = t.getTime(p.getUniqueId()) / 3600;
				
				if(hours < 1)
				{
					timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + "<1h");
				}
				else if(hours >= 1)
				{
					timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + hours + "h");
					
					if(hours >= 24)
					{
						int days = hours / 24;
						int temph = hours - (days * 24);
						
						timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + days + "d" + temph + "h");
						
						if(days >= 7)
						{
							int weeks = days / 7;
							int tempd = days - (weeks * 7);
							
							timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + weeks + "w" + tempd + "d" + temph + "h");
							
							if(weeks >= 4)
							{
								int months = weeks / 4;
								int tempw = weeks - (months * 4);
								
								timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + months + "m" + tempw + "w" + tempd + "d" + temph + "h");
							}
						}
					}
				}

				time.setItemMeta(timemeta);
				
				ItemStack potions = new ItemStack(Material.POTION, 1 , (byte) 0);
				ItemMeta potionsmeta = potions.getItemMeta();
				potionsmeta.setDisplayName(ChatColor.AQUA + "Potions Consumed " + ChatColor.GRAY + "» " + ChatColor.AQUA + pot.getPotions(p.getUniqueId()));
				potions.setItemMeta(potionsmeta);
				
				
				i.setItem(0, kills);
				i.setItem(2, kd);
				i.setItem(4, death);
				i.setItem(6, wins);
				i.setItem(8, gaps);
				i.setItem(18, heads);
				i.setItem(20, nether);
				i.setItem(22, ores);
				i.setItem(24, time);
				i.setItem(26, potions);
				
				if(!p.getInventory().getName().equals(ChatColor.AQUA + p.getDisplayName() + "'s Stats"))
				{
					cancel();
					return;
				}
			}
				
		}.runTaskTimer(plugin, 0, 20);
			
		p.openInventory(i);
	}
	
	public void statsPlayer(Player p, Player target)
	{
		Inventory i = plugin.getServer().createInventory(null, 27, ChatColor.AQUA + target.getDisplayName() + "'s Stats");
		DecimalFormat format = new DecimalFormat("0.00");
		
		new BukkitRunnable()
		{
			public void run()
			{
				ItemStack kills = new ItemStack(Material.DIAMOND_SWORD, 1 , (byte) 0);
				ItemMeta killsmeta = kills.getItemMeta();
				killsmeta.setDisplayName(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» " + ChatColor.AQUA +  k.getKills(target.getUniqueId()));
				kills.setItemMeta(killsmeta);
				
				ItemStack wins = new ItemStack(Material.NETHER_STAR, 1 , (byte) 0);
				ItemMeta winsmeta = wins.getItemMeta();
				winsmeta.setDisplayName(ChatColor.AQUA + "Wins " + ChatColor.GRAY + "» " + ChatColor.AQUA + w.getwins(target.getUniqueId()));
				wins.setItemMeta(winsmeta);
				
				ItemStack death = new ItemStack(Material.SKULL_ITEM, 1 , (byte) 0);
				ItemMeta deathmeta = death.getItemMeta();
				deathmeta.setDisplayName(ChatColor.AQUA + "Deaths " + ChatColor.GRAY + "» " + ChatColor.AQUA + d.getDeaths(target.getUniqueId()));
				death.setItemMeta(deathmeta);
				
				ItemStack gaps = new ItemStack(Material.GOLDEN_APPLE, 1 , (byte) 0);
				ItemMeta gapsmeta = gaps.getItemMeta();
				gapsmeta.setDisplayName(ChatColor.AQUA + "Golden Apples Eaten " + ChatColor.GRAY + "» " + ChatColor.AQUA + g.getGaps(target.getUniqueId()));
				gaps.setItemMeta(gapsmeta);
				
				ItemStack heads = new ItemStack(Material.GOLDEN_APPLE, 1 , (byte) 0);
				ItemMeta headsmeta = heads.getItemMeta();
				headsmeta.setDisplayName(ChatColor.AQUA + "Golden Heads Eaten " + ChatColor.GRAY + "» " + ChatColor.AQUA + h.getHeads(target.getUniqueId()));
				heads.setItemMeta(headsmeta);
				
				ItemStack kd = new ItemStack(Material.PAPER, 1 , (byte) 0);
				ItemMeta kdmeta = kd.getItemMeta();
				String KDR = format.format(kdr.getKdr(target.getUniqueId()));
				kdmeta.setDisplayName(ChatColor.AQUA + "KDR " + ChatColor.GRAY + "» " + ChatColor.AQUA + KDR);
				kd.setItemMeta(kdmeta);
				
				ItemStack nether = new ItemStack(Material.NETHERRACK, 1 , (byte) 0);
				ItemMeta nethermeta = nether.getItemMeta();
				nethermeta.setDisplayName(ChatColor.AQUA + "Nether Entries " + ChatColor.GRAY + "» " + ChatColor.AQUA + n.getEntries(target.getUniqueId()));
				nether.setItemMeta(nethermeta);
				
				ItemStack ores = new ItemStack(Material.DIAMOND_ORE, 1 , (byte) 0);
				ItemMeta oresmeta = ores.getItemMeta();
				oresmeta.setDisplayName(ChatColor.AQUA + "Ores Mined: ");
				oresmeta.setLore(Arrays.asList(ChatColor.AQUA + "Diamond " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getDiamond(target.getUniqueId()),ChatColor.AQUA + "Gold " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getGold(target.getUniqueId()),
						ChatColor.AQUA + "Iron Ore " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getIron(target.getUniqueId()),ChatColor.AQUA + "Coal " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getCoal(target.getUniqueId()),ChatColor.AQUA + "Lapis " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getLapis(target.getUniqueId()),
						ChatColor.AQUA + "Redstone " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getRedstone(target.getUniqueId()), ChatColor.AQUA + "Quartz " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getQuartz(target.getUniqueId())));
				ores.setItemMeta(oresmeta);
				
				ItemStack time = new ItemStack(Material.WATCH, 1 , (byte) 0);
				ItemMeta timemeta = time.getItemMeta();
				
				int hours = t.getTime(p.getUniqueId()) / 3600;
				
				if(hours < 1)
				{
					timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + "<1h");
				}
				else if(hours >= 1)
				{
					timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + hours + "h");
					
					if(hours >= 24)
					{
						int days = hours / 24;
						int temph = hours - (days * 24);
						
						timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + days + "d" + temph + "h");
						
						if(days >= 7)
						{
							int weeks = days / 7;
							int tempd = days - (weeks * 7);
							
							timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + weeks + "w" + tempd + "d" + temph + "h");
							
							if(weeks >= 4)
							{
								int months = weeks / 4;
								int tempw = weeks - (months * 4);
								
								timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + months + "m" + tempw + "w" + tempd + "d" + temph + "h");
							}
						}
					}
				}
				
				time.setItemMeta(timemeta);
				
				ItemStack potions = new ItemStack(Material.POTION, 1 , (byte) 0);
				ItemMeta potionsmeta = potions.getItemMeta();
				potionsmeta.setDisplayName(ChatColor.AQUA + "Potions Consumed " + ChatColor.GRAY + "» " + ChatColor.AQUA + pot.getPotions(target.getUniqueId()));
				potions.setItemMeta(potionsmeta);
				
				
				i.setItem(0, kills);
				i.setItem(2, kd);
				i.setItem(4, death);
				i.setItem(6, wins);
				i.setItem(8, gaps);
				i.setItem(18, heads);
				i.setItem(20, nether);
				i.setItem(22, ores);
				i.setItem(24, time);
				i.setItem(26, potions);
				
				if(!p.getInventory().getName().equals(ChatColor.AQUA + target.getDisplayName() + "'s Stats"))
				{
					cancel();
					return;
				}
			}
				
		}.runTaskTimer(plugin, 0, 20);
			
		p.openInventory(i);
	}
	
	public void statsOfflinePlayer(Player p, OfflinePlayer target)
	{
		Inventory i = plugin.getServer().createInventory(null, 27, ChatColor.AQUA + target.getName() + "'s Stats");
		DecimalFormat format = new DecimalFormat("0.00");
		
		new BukkitRunnable()
		{
			public void run()
			{
				ItemStack kills = new ItemStack(Material.DIAMOND_SWORD, 1 , (byte) 0);
				ItemMeta killsmeta = kills.getItemMeta();
				killsmeta.setDisplayName(ChatColor.AQUA + "Kills " + ChatColor.GRAY + "» " + ChatColor.AQUA +  k.getKills(target.getUniqueId()));
				kills.setItemMeta(killsmeta);
				
				ItemStack wins = new ItemStack(Material.NETHER_STAR, 1 , (byte) 0);
				ItemMeta winsmeta = wins.getItemMeta();
				winsmeta.setDisplayName(ChatColor.AQUA + "Wins " + ChatColor.GRAY + "» " + ChatColor.AQUA + w.getwins(target.getUniqueId()));
				wins.setItemMeta(winsmeta);
				
				ItemStack death = new ItemStack(Material.SKULL_ITEM, 1 , (byte) 0);
				ItemMeta deathmeta = death.getItemMeta();
				deathmeta.setDisplayName(ChatColor.AQUA + "Deaths " + ChatColor.GRAY + "» " + ChatColor.AQUA + d.getDeaths(target.getUniqueId()));
				death.setItemMeta(deathmeta);
				
				ItemStack gaps = new ItemStack(Material.GOLDEN_APPLE, 1 , (byte) 0);
				ItemMeta gapsmeta = gaps.getItemMeta();
				gapsmeta.setDisplayName(ChatColor.AQUA + "Golden Apples Eaten " + ChatColor.GRAY + "» " + ChatColor.AQUA + g.getGaps(target.getUniqueId()));
				gaps.setItemMeta(gapsmeta);
				
				ItemStack heads = new ItemStack(Material.GOLDEN_APPLE, 1 , (byte) 0);
				ItemMeta headsmeta = heads.getItemMeta();
				headsmeta.setDisplayName(ChatColor.AQUA + "Golden Heads Eaten " + ChatColor.GRAY + "» " + ChatColor.AQUA + h.getHeads(target.getUniqueId()));
				heads.setItemMeta(headsmeta);
				
				ItemStack kd = new ItemStack(Material.PAPER, 1 , (byte) 0);
				ItemMeta kdmeta = kd.getItemMeta();
				String KDR = format.format(kdr.getKdr(target.getUniqueId()));
				kdmeta.setDisplayName(ChatColor.AQUA + "KDR " + ChatColor.GRAY + "» " + ChatColor.AQUA + KDR);
				kd.setItemMeta(kdmeta);
				
				ItemStack nether = new ItemStack(Material.NETHERRACK, 1 , (byte) 0);
				ItemMeta nethermeta = nether.getItemMeta();
				nethermeta.setDisplayName(ChatColor.AQUA + "Nether Entries " + ChatColor.GRAY + "» " + ChatColor.AQUA + n.getEntries(target.getUniqueId()));
				nether.setItemMeta(nethermeta);
				
				ItemStack ores = new ItemStack(Material.DIAMOND_ORE, 1 , (byte) 0);
				ItemMeta oresmeta = ores.getItemMeta();
				oresmeta.setDisplayName(ChatColor.AQUA + "Ores Mined: ");
				oresmeta.setLore(Arrays.asList(ChatColor.AQUA + "Diamond " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getDiamond(target.getUniqueId()),ChatColor.AQUA + "Gold " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getGold(target.getUniqueId()),
						ChatColor.AQUA + "Iron Ore " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getIron(target.getUniqueId()),ChatColor.AQUA + "Coal " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getCoal(target.getUniqueId()),ChatColor.AQUA + "Lapis " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getLapis(target.getUniqueId()),
						ChatColor.AQUA + "Redstone " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getRedstone(target.getUniqueId()), ChatColor.AQUA + "Quartz " + ChatColor.GRAY + "» " + ChatColor.AQUA + ore.getQuartz(target.getUniqueId())));
				ores.setItemMeta(oresmeta);
				
				ItemStack time = new ItemStack(Material.WATCH, 1 , (byte) 0);
				ItemMeta timemeta = time.getItemMeta();
				
				int hours = t.getTime(p.getUniqueId()) / 3600;
				
				if(hours < 1)
				{
					timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + "<1h");
				}
				else if(hours >= 1)
				{
					timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + hours + "h");
					
					if(hours >= 24)
					{
						int days = hours / 24;
						int temph = hours - (days * 24);
						
						timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + days + "d" + temph + "h");
						
						if(days >= 7)
						{
							int weeks = days / 7;
							int tempd = days - (weeks * 7);
							
							timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + weeks + "w" + tempd + "d" + temph + "h");
							
							if(weeks >= 4)
							{
								int months = weeks / 4;
								int tempw = weeks - (months * 4);
								
								timemeta.setDisplayName(ChatColor.AQUA + "Time Played " + ChatColor.GRAY + "» " + ChatColor.AQUA + months + "m" + tempw + "w" + tempd + "d" + temph + "h");
							}
						}
					}
				}
				
				time.setItemMeta(timemeta);
				
				ItemStack potions = new ItemStack(Material.POTION, 1 , (byte) 0);
				ItemMeta potionsmeta = potions.getItemMeta();
				potionsmeta.setDisplayName(ChatColor.AQUA + "Potions Consumed " + ChatColor.GRAY + "» " + ChatColor.AQUA  + pot.getPotions(target.getUniqueId()));
				potions.setItemMeta(potionsmeta);
				
				
				i.setItem(0, kills);
				i.setItem(2, kd);
				i.setItem(4, death);
				i.setItem(6, wins);
				i.setItem(8, gaps);
				i.setItem(18, heads);
				i.setItem(20, nether);
				i.setItem(22, ores);
				i.setItem(24, time);
				i.setItem(26, potions);
				
				if(!p.getInventory().getName().equals(ChatColor.AQUA + target.getName() + "'s Stats"))
				{
					cancel();
					return;
				}
			}
				
		}.runTaskTimer(plugin, 0, 20);
			
		p.openInventory(i);
	}
	
	public void statsReset(Player p)
	{
		Inventory i = plugin.getServer().createInventory(null, 9, ChatColor.RED + "Stats Reset");
		
		ItemStack confirm =  new ItemStack(Material.EMERALD, 1 , (byte) 0);
		ItemMeta greenmeta = confirm.getItemMeta();
		greenmeta.setDisplayName(ChatColor.GREEN + "Confirm");
		confirm.setItemMeta(greenmeta);
				
		ItemStack cancel =  new ItemStack(Material.REDSTONE, 1 , (byte) 0);
		ItemMeta redmeta = cancel.getItemMeta();
		redmeta.setDisplayName(ChatColor.RED + "Cancel");
		cancel.setItemMeta(redmeta);		
				
		i.setItem(3, confirm);
		i.setItem(5, cancel);
		
		p.openInventory(i);
	}
}
