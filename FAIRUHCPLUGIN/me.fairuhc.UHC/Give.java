package me.fairuhc.UHC;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class Give implements Listener
{
	public void onGive(Player p, Player target, String item, int amount)
	{
		ItemStack speed1 = new ItemStack(Material.POTION);
			
		PotionMeta speed1meta = (PotionMeta) speed1.getItemMeta();
		speed1meta.setMainEffect(PotionEffectType.SPEED);
		PotionEffect sp = new PotionEffect(PotionEffectType.SPEED, 3600, 0);
		speed1meta.addCustomEffect(sp, true);
		speed1meta.setDisplayName("Speed 1");
		speed1.setItemMeta(speed1meta);	
		
		ItemStack speed2 = new ItemStack(Material.POTION);
		
		PotionMeta speed2meta = (PotionMeta) speed2.getItemMeta();
		speed2meta.setMainEffect(PotionEffectType.SPEED);
		PotionEffect sp2 = new PotionEffect(PotionEffectType.SPEED, 1800, 1);
		speed2meta.addCustomEffect(sp2, true);
		speed2meta.setDisplayName("Speed 2");
		speed2.setItemMeta(speed2meta);	
		
		ItemStack strength1 = new ItemStack(Material.POTION);
		
		PotionMeta strength1meta = (PotionMeta) strength1.getItemMeta();
		strength1meta.setMainEffect(PotionEffectType.INCREASE_DAMAGE);
		PotionEffect str1 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 0);
		strength1meta.addCustomEffect(str1, true);
		strength1meta.setDisplayName("Strength 1");
		strength1.setItemMeta(strength1meta);	
		
		ItemStack strength2 = new ItemStack(Material.POTION);
		
		PotionMeta strength2meta = (PotionMeta) strength2.getItemMeta();
		strength2meta.setMainEffect(PotionEffectType.INCREASE_DAMAGE);
		PotionEffect str2 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1800, 1);
		strength2meta.addCustomEffect(str2, true);
		strength2meta.setDisplayName("Strength 2");
		strength2.setItemMeta(strength2meta);	
		
		if(item.equals("diamond"))
		{
			target.getInventory().addItem(new ItemStack(Material.DIAMOND, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " diamond");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " diamond to " + target.getDisplayName());
		}
		else if(item.equals("iron"))
		{
			target.getInventory().addItem(new ItemStack(Material.IRON_INGOT, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " iron");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " iron to " + target.getDisplayName());
		}
		else if(item.equals("gold"))
		{
			target.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " gold");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " gold to " + target.getDisplayName());
		}
		else if(item.equals("sugarcane"))
		{
			target.getInventory().addItem(new ItemStack(Material.SUGAR_CANE, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " sugar cane");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " sugar cane to " + target.getDisplayName());
		}
		else if(item.equals("cobblestone"))
		{
			target.getInventory().addItem(new ItemStack(Material.COBBLESTONE, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " cobblestone");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " cobblestone to " + target.getDisplayName());
		}
		else if(item.equals("stone"))
		{
			target.getInventory().addItem(new ItemStack(Material.STONE, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " stone");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " stone to " + target.getDisplayName());
		}
		else if(item.equals("obsidian"))
		{
			target.getInventory().addItem(new ItemStack(Material.OBSIDIAN, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " obsidian");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " obsidian to " + target.getDisplayName());
		}
		else if(item.equals("diamondblock"))
		{
			target.getInventory().addItem(new ItemStack(Material.DIAMOND_BLOCK, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " diamond block(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " diamond block(s) to " + target.getDisplayName());
		}
		else if(item.equals("ironblock"))
		{
			target.getInventory().addItem(new ItemStack(Material.IRON_BLOCK, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " iron block(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " iron block(s) to " + target.getDisplayName());
		}
		else if(item.equals("goldblock"))
		{
			target.getInventory().addItem(new ItemStack(Material.GOLD_BLOCK, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " gold block(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " gold block(s) to " + target.getDisplayName());
		}
		else if(item.equals("book"))
		{
			target.getInventory().addItem(new ItemStack(Material.BOOK, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " book(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " book(s) to " + target.getDisplayName());
		}
		else if(item.equals("bookshelf"))
		{
			target.getInventory().addItem(new ItemStack(Material.BOOKSHELF, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " bookshelve(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " bookshelve(s) to " + target.getDisplayName());
		}
		else if(item.equals("enchantingtable"))
		{
			target.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " enchantment table(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " enchantment table(s) to " + target.getDisplayName());
		}
		else if(item.equals("speed1"))
		{
			target.getInventory().addItem(speed1);
			target.sendMessage(ChatColor.GREEN + "You have been given 1 speed I potion");
			p.sendMessage(ChatColor.GREEN + "You have given 1 speed I potion to " + target.getDisplayName());
		}
		else if(item.equals("speed2"))
		{
			target.getInventory().addItem(speed2);
			target.sendMessage(ChatColor.GREEN + "You have been given 1 speed II potion");
			p.sendMessage(ChatColor.GREEN + "You have given 1 speed II potion to " + target.getDisplayName());
		}
		else if(item.equals("strength1"))
		{
			target.getInventory().addItem(strength1);
			target.sendMessage(ChatColor.GREEN + "You have been given 1 strength I potion");
			p.sendMessage(ChatColor.GREEN + "You have given 1 strength I potion to " + target.getDisplayName());
		}
		else if(item.equals("strength2"))
		{
			target.getInventory().addItem(strength2);
			target.sendMessage(ChatColor.GREEN + "You have been given 1 strength II potion");
			p.sendMessage(ChatColor.GREEN + "You have given 1 strength II potion to " + target.getDisplayName());
		}
		else if(item.equals("oak"))
		{
			target.getInventory().addItem(new ItemStack(Material.LOG, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " oak log(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " oak log(s) to " + target.getDisplayName());
		}
		else if(item.equals("enderpearl"))
		{
			target.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " ender pearl(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " ender pearl(s) to " + target.getDisplayName());
		}
		else if(item.equals("waterbucket"))
		{
			target.getInventory().addItem(new ItemStack(Material.WATER_BUCKET, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " water bucket(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " water bucket(s) to " + target.getDisplayName());
		}
		else if(item.equals("lavabucket"))
		{
			target.getInventory().addItem(new ItemStack(Material.LAVA_BUCKET, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " lava bucket(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " lava bucket(s) to " + target.getDisplayName());
		}
		else if(item.equals("flintnsteel"))
		{
			target.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " flint and steel");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " flint and steel to " + target.getDisplayName());
		}
		else if(item.equals("bow"))
		{
			target.getInventory().addItem(new ItemStack(Material.BOW, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " bow(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " bow(s) to " + target.getDisplayName());
		}
		else if(item.equals("rod"))
		{
			target.getInventory().addItem(new ItemStack(Material.FISHING_ROD, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " fishing rod(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " fishing rod(s) to " + target.getDisplayName());
		}
		else if(item.equals("diamondsword"))
		{
			target.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + "diamond sword(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " diamond sword(s) to " + target.getDisplayName());
		}
		else if(item.equals("ironsword"))
		{
			target.getInventory().addItem(new ItemStack(Material.IRON_SWORD, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " iron sword(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " iron sword(s) to " + target.getDisplayName());
		}
		else if(item.equals("goldsword"))
		{
			target.getInventory().addItem(new ItemStack(Material.GOLD_SWORD, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " gold sword(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " gold sword(s) to " + target.getDisplayName());
		}
		else if(item.equals("stonesword"))
		{
			target.getInventory().addItem(new ItemStack(Material.STONE_SWORD, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " stone sword(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " stone sword(s) to " + target.getDisplayName());
		}
		else if(item.equals("woodsword"))
		{
			target.getInventory().addItem(new ItemStack(Material.WOOD_SWORD, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " wooden sword(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " wooden sword(s) to " + target.getDisplayName());
		}
		else if(item.equals("diamondpickaxe"))
		{
			target.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " diamond pickaxe(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " diamond pickaxe(s) to " + target.getDisplayName());
		}
		else if(item.equals("ironpickaxe"))
		{
			target.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " iron pickaxe(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " iron pickaxe(s) to " + target.getDisplayName());
		}
		else if(item.equals("goldpickaxe"))
		{
			target.getInventory().addItem(new ItemStack(Material.GOLD_PICKAXE, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " gold pickaxe(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " gold pickaxe(s) to " + target.getDisplayName());
		}
		else if(item.equals("stonepickaxe"))
		{
			target.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " stone pickaxe(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " stone pickaxe(s) to " + target.getDisplayName());
		}
		else if(item.equals("woodpickaxe"))
		{
			target.getInventory().addItem(new ItemStack(Material.WOOD_PICKAXE, amount));
			target.sendMessage(ChatColor.GREEN + "You have been given " + amount + " wooden pickaxe(s)");
			p.sendMessage(ChatColor.GREEN + "You have given " + amount + " wooden pickaxe(s) to " + target.getDisplayName());
		}
		else
		{
			p.sendMessage(ChatColor.RED + "That item does not exist.");
		}
	}
}
