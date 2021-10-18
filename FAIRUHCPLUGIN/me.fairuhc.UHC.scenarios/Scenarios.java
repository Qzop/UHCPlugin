package me.fairuhc.UHC.scenarios;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import Events.scenariosadmin;
import me.fairuhc.UHC.Main;
import net.md_5.bungee.api.ChatColor;

public class Scenarios implements Listener
{
	public Plugin plugin = Main.getPlugin(Main.class);
	public scenariosadmin f = new scenariosadmin();
	
	@SuppressWarnings("static-access")
	public void showScen(Player p)
	{
		Inventory i = plugin.getServer().createInventory(null, 9, ChatColor.AQUA + "Scenarios");
		
		for(int j = 0; j < f.items.size(); j++)
		{
			i.setItem(j, f.items.get(j));
		}
		
		p.openInventory(i);
	}
	
	public void selectScen(Player p)
	{
		if(p.hasPermission("scenarios.edit"))
		{
			Inventory i = plugin.getServer().createInventory(null, 45, ChatColor.AQUA + "Scenarios Admin");
			
			ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1 , (byte) 15);
			ItemMeta emptyMeta = empty.getItemMeta();
			emptyMeta.setDisplayName("");
			empty.setItemMeta(emptyMeta);
		
			ItemStack CutClean = new ItemStack(Material.FURNACE, 1, (byte) 0);
			ItemMeta swordMeta = CutClean.getItemMeta();
			swordMeta.setDisplayName(ChatColor.AQUA + "CutClean");
			CutClean.setItemMeta(swordMeta);
			
			ItemStack Timber = new ItemStack(Material.DIAMOND_AXE, 1, (byte) 0);
			ItemMeta timberMeta = Timber.getItemMeta();
			timberMeta.setDisplayName(ChatColor.AQUA + "Timber");
			Timber.setItemMeta(timberMeta);
			
			ItemStack HasteyBoys = new ItemStack(Material.DIAMOND_PICKAXE, 1, (byte) 0);
			ItemMeta hbMeta = HasteyBoys.getItemMeta();
			hbMeta.setDisplayName(ChatColor.AQUA + "HasteyBoys");
			HasteyBoys.setItemMeta(hbMeta);
			
			ItemStack FireLess = new ItemStack(Material.FLINT_AND_STEEL, 1, (byte) 0);
			ItemMeta flMeta = FireLess.getItemMeta();
			flMeta.setDisplayName(ChatColor.AQUA + "Fireless");
			FireLess.setItemMeta(flMeta);
			
			ItemStack nofall = new ItemStack(Material.FEATHER, 1, (byte) 0);
			ItemMeta nfMeta = nofall.getItemMeta();
			nfMeta.setDisplayName(ChatColor.AQUA + "NoFall");
			nofall.setItemMeta(nfMeta);
			
			ItemStack timebomb = new ItemStack(Material.TNT, 1, (byte) 0);
			ItemMeta tbMeta = timebomb.getItemMeta();
			tbMeta.setDisplayName(ChatColor.AQUA + "TimeBomb");
			timebomb.setItemMeta(tbMeta);
			
			ItemStack diamondless = new ItemStack(Material.DIAMOND, 1, (byte) 0);
			ItemMeta dialess = diamondless.getItemMeta();
			dialess.setDisplayName(ChatColor.AQUA + "Diamondless");
			diamondless.setItemMeta(dialess);
			
			ItemStack goldless = new ItemStack(Material.GOLD_INGOT, 1, (byte) 0);
			ItemMeta gless = goldless.getItemMeta();
			gless.setDisplayName(ChatColor.AQUA + "Goldless");
			goldless.setItemMeta(gless);
			
			ItemStack veinminer = new ItemStack(Material.IRON_ORE, 1, (byte) 0);
			ItemMeta vmine = veinminer.getItemMeta();
			vmine.setDisplayName(ChatColor.AQUA + "Veinminer");
			veinminer.setItemMeta(vmine);
			
			ItemStack goldenretriever = new ItemStack(Material.GOLDEN_APPLE, 1, (byte) 0);
			ItemMeta gmeta = goldenretriever.getItemMeta();
			gmeta.setDisplayName(ChatColor.AQUA + "Golden Retriever");
			goldenretriever.setItemMeta(gmeta);
			
			ItemStack noclean = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 0);
			ItemMeta nometa = noclean.getItemMeta();
			nometa.setDisplayName(ChatColor.AQUA + "NoClean");
			noclean.setItemMeta(nometa);
			
			ItemStack bloodgold = new ItemStack(Material.GOLD_ORE, 1, (byte) 0);
			ItemMeta bgold = bloodgold.getItemMeta();
			bgold.setDisplayName(ChatColor.AQUA + "BloodGold");
			bloodgold.setItemMeta(bgold);
			
			ItemStack blooddiamond = new ItemStack(Material.DIAMOND_ORE, 1, (byte) 0);
			ItemMeta bdia = blooddiamond.getItemMeta();
			bdia.setDisplayName(ChatColor.AQUA + "BloodDiamonds");
			blooddiamond.setItemMeta(bdia);
			
			ItemStack chest = new ItemStack(Material.CHEST, 1, (byte) 0);
			ItemMeta chestmeta = chest.getItemMeta();
			chestmeta.setDisplayName(ChatColor.AQUA + "Chest");
			chest.setItemMeta(chestmeta);
			
			ItemStack safeloot = new ItemStack(Material.ENDER_CHEST, 1, (byte) 0);
			ItemMeta safemeta = safeloot.getItemMeta();
			safemeta.setDisplayName(ChatColor.AQUA + "Safeloot");
			safeloot.setItemMeta(safemeta);
			
			ItemStack triple = new ItemStack(Material.LAPIS_ORE, 1, (byte) 0);
			ItemMeta tores = triple.getItemMeta();
			tores.setDisplayName(ChatColor.AQUA + "Triple Ores");
			triple.setItemMeta(tores);
			
			ItemStack doubl = new ItemStack(Material.REDSTONE_ORE, 1, (byte) 0);
			ItemMeta dores = doubl.getItemMeta();
			dores.setDisplayName(ChatColor.AQUA + "Double Ores");
			doubl.setItemMeta(dores);
			
			ItemStack anonymous = new ItemStack(Material.SKULL_ITEM, 1, (byte) 0);
			ItemMeta anonmeta = anonymous.getItemMeta();
			anonmeta.setDisplayName(ChatColor.AQUA + "Anonymous");
			anonymous.setItemMeta(anonmeta);
			
			ItemStack rodless = new ItemStack(Material.FISHING_ROD, 1, (byte) 0);
			ItemMeta rodmeta = rodless.getItemMeta();
			rodmeta.setDisplayName(ChatColor.AQUA + "Rodless");
			rodless.setItemMeta(rodmeta);
			
			ItemStack bowless = new ItemStack(Material.BOW, 1, (byte) 0);
			ItemMeta bowmeta = bowless.getItemMeta();
			bowmeta.setDisplayName(ChatColor.AQUA + "Bowless");
			bowless.setItemMeta(bowmeta);
			
			ItemStack superheroes = new ItemStack(Material.POTION, 1, (byte) 0);
			ItemMeta supmeta = superheroes.getItemMeta();
			supmeta.setDisplayName(ChatColor.AQUA + "SuperHeroes");
			superheroes.setItemMeta(supmeta);
			
			i.setItem(0, CutClean);
			i.setItem(1, Timber);
			i.setItem(2, HasteyBoys);
			i.setItem(3, FireLess);
			i.setItem(4, nofall);
			i.setItem(5, timebomb);
			i.setItem(6, diamondless);
			i.setItem(7, goldless);
			i.setItem(8, veinminer);
			i.setItem(9, goldenretriever);
			i.setItem(10, noclean);
			i.setItem(11, bloodgold);
			i.setItem(12, blooddiamond);
			i.setItem(13, chest);
			i.setItem(14, safeloot);
			i.setItem(15, triple);
			i.setItem(16, doubl);
			i.setItem(17, anonymous);
			i.setItem(18, rodless);
			i.setItem(19, bowless);
			i.setItem(20, superheroes);
			i.setItem(21, empty);
			i.setItem(22, empty);
			i.setItem(23, empty);
			i.setItem(24, empty);
			i.setItem(25, empty);
			i.setItem(26, empty);
			i.setItem(27, empty);
			i.setItem(28, empty);
			i.setItem(29, empty);
			i.setItem(30, empty);
			i.setItem(31, empty);
			i.setItem(32, empty);
			i.setItem(33, empty);
			i.setItem(34, empty);
			i.setItem(35, empty);
			
			p.openInventory(i);
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You do not have permission.");
		}
	}
}