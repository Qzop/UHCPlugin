package me.fairuhc.UHC;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.fairuhc.UHC.scenarios.ScenarioEvents;
import net.md_5.bungee.api.ChatColor;

public class GoldenHead implements Listener
{
	public static ItemStack head;
	public static ItemMeta meta;
	public static ArrayList<Player> eat = new ArrayList<>();
	public Plugin plugin = Main.getPlugin(Main.class);
	
	public GoldenHead(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	public void Recipe()
	{
		head = new ItemStack(Material.GOLDEN_APPLE);
		meta = head.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Golden Head");
		head.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(head);
		recipe.shape("GGG", "GHG", "GGG");
		recipe.setIngredient('H', Material.SKULL_ITEM, 3);
		recipe.setIngredient('G', Material.GOLD_INGOT);
		plugin.getServer().addRecipe(recipe);
	}
	
	@EventHandler
	public void onEat(PlayerItemConsumeEvent e)
	{
		Player p = e.getPlayer();
		
		String name = e.getItem().getItemMeta().getDisplayName();
		
		if(name == null)
		{
			return;
		}
		else
		{
			if(name.equals(ChatColor.GOLD + "Golden Head"))
			{
				givePotions(p);
			}
		}
	}
	
	public void givePotions(Player p)
	{
		p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1200, 0));
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		Player p = e.getEntity();
		
		ItemStack playerhead = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta m = (SkullMeta) playerhead.getItemMeta();
		m.setOwner(p.getName());
		m.setDisplayName(ChatColor.GOLD + p.getName() + "'s Head");
		playerhead.setItemMeta(m);
		
		if(ScenarioEvents.goldenretriever == false && ScenarioEvents.chest == false && Gamemanager.started == true)
		{
			e.getDrops().add(new ItemStack(playerhead));
		}
		else if(ScenarioEvents.chest == true && ScenarioEvents.goldenretriever == true && Gamemanager.started == true)
		{
			return;
		}
		else if(ScenarioEvents.goldenretriever == true && ScenarioEvents.chest == false && Gamemanager.started == true)
		{
			e.getDrops().add(head);
		}
	}
}
