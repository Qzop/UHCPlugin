package core.GoldenHead;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import core.mainPackage.Main;

public class GoldenHead implements Listener
{
	public static ItemStack head;
	Main plugin = Main.getPlugin(Main.class);
	
	@SuppressWarnings("deprecation")
	public void createGoldenHead()
	{
		head = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta meta = head.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Golden Head");
		head.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(head);
		recipe.shape("GGG", "GHG", "GGG");
		recipe.setIngredient('H', Material.SKULL_ITEM, 3);
		recipe.setIngredient('G', Material.GOLD_INGOT);
		plugin.getServer().addRecipe(recipe);
	}

	public void giveGoldenHead(Player player)
	{
		player.getInventory().addItem(head);
	}
	
	@EventHandler
	public void onConsumption(PlayerItemConsumeEvent e)
	{
		Player p = e.getPlayer();

		if(e.getItem().getItemMeta().getDisplayName() == null)
		{
			return;
		}
		else if(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Golden Head"))
		{
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
			p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1200, 0));
		}
	}
}
