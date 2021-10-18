package me.fairuhc.UHC;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Report implements Listener
{
	public Main plugin;
	public static Player target;
	
	public Report(Main plugin)
	{
		this.plugin = plugin;
	}
	
	public void report(Player p, Player t)
	{
		Inventory i = plugin.getServer().createInventory(null, 9, ChatColor.AQUA + "Report");
		
		target = t;
		
		ItemStack combat = new ItemStack(Material.DIAMOND_SWORD, 1 , (byte) 0);
		ItemMeta combatmeta = combat.getItemMeta();
		combatmeta.setDisplayName(ChatColor.AQUA + "Combat Hacks");
		combat.setItemMeta(combatmeta);
			
		ItemStack xray = new ItemStack(Material.DIAMOND_ORE, 1 , (byte) 15);
		ItemMeta xraymeta = xray.getItemMeta();
		xraymeta.setDisplayName(ChatColor.AQUA + "X-ray/Cavefinder");
		xray.setItemMeta(xraymeta);
			
		ItemStack exploit = new ItemStack(Material.TNT, 1 , (byte) 15);
		ItemMeta exploitmeta = exploit.getItemMeta();
		exploitmeta.setDisplayName(ChatColor.AQUA + "Exploiting bugs");
		exploit.setItemMeta(exploitmeta);
		
		ItemStack fly = new ItemStack(Material.FEATHER, 1 , (byte) 15);
		ItemMeta flymeta = fly.getItemMeta();
		flymeta.setDisplayName(ChatColor.AQUA + "Fly Hacks");
		fly.setItemMeta(flymeta);
		
		ItemStack f5 = new ItemStack(Material.REDSTONE_TORCH_ON, 1 , (byte) 15);
		ItemMeta f5meta = f5.getItemMeta();
		f5meta.setDisplayName(ChatColor.AQUA + "F5 Abuse");
		f5.setItemMeta(f5meta);
		
		ItemStack teaming = new ItemStack(Material.SADDLE, 1 , (byte) 15);
		ItemMeta teamingmeta = teaming.getItemMeta();
		teamingmeta.setDisplayName(ChatColor.AQUA + "Teaming in FFA");
		teaming.setItemMeta(teamingmeta);
		
		ItemStack tracers = new ItemStack(Material.EYE_OF_ENDER, 1 , (byte) 15);
		ItemMeta tracersmeta = tracers.getItemMeta();
		tracersmeta.setDisplayName(ChatColor.AQUA + "Tracers");
		tracers.setItemMeta(tracersmeta);
			
		i.setItem(0, combat);
		i.setItem(1, xray);
		i.setItem(2, exploit);
		i.setItem(3, fly);
		i.setItem(4, f5);
		i.setItem(5, teaming);
		i.setItem(6, tracers);
		
		
		p.openInventory(i);
	}
}
