package notes.Inventories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import notes.Database.NotesTable;
import notes.Events.ReadNotes;
import notes.mainPackage.Main;

public class NotesInventory
{
	public static List<Inventory> inventories;
	private NotesTable notes = new NotesTable();
	Main plugin = Main.getPlugin(Main.class);
	
	public void notesInv(Player p)
	{
		ArrayList<UUID> uuids = new ArrayList<UUID>();
		uuids = notes.GetAllUUIDs();
		
		if(uuids.size() > 21)
		{
			inventories = new ArrayList<>();
			int invSize = 45;
			int numInventories;
			
			if(uuids.size() % 21 == 0)
			{
				numInventories = uuids.size() / 21;
			}
			else
			{
				numInventories = (uuids.size() / 21) + 1;
			}
			
			for(int i = 0; i < numInventories; i++)
			{
				Inventory inv = Bukkit.createInventory(null, invSize, ChatColor.RED + "Player Notes (Multiple Pages)");
				
				inventories.add(inv);
			}
			
			int count = 0;
			int index = 0;
			int currentInv = 0;
			
			for(int k = 0; k < inventories.size(); k++)
			{
				Inventory i = inventories.get(k);
				int countSlot = 10;
				
				ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0);
				ItemMeta fmeta = filler.getItemMeta();
				fmeta.setDisplayName(" ");
				filler.setItemMeta(fmeta);
				
				ItemStack previous = new ItemStack(Material.ARROW, 1, (byte) 0);
				ItemMeta pmeta = previous.getItemMeta();
				pmeta.setDisplayName(ChatColor.YELLOW + "Previous page");
				previous.setItemMeta(pmeta);
				
				ItemStack next = new ItemStack(Material.ARROW, 1, (byte) 0);
				ItemMeta nmeta = previous.getItemMeta();
				nmeta.setDisplayName(ChatColor.YELLOW + "Next page");
				next.setItemMeta(nmeta);
				
				if(currentInv == numInventories - 1)
				{
					i.setItem(0, filler);
					i.setItem(1, filler);
					i.setItem(2, filler);
					i.setItem(3, filler);
					i.setItem(4, filler);
					i.setItem(5, filler);
					i.setItem(6, filler);
					i.setItem(7, filler);
					i.setItem(8, filler);
					i.setItem(9, filler);
					i.setItem(17, filler);
					i.setItem(18, filler);
					i.setItem(26, filler);
					i.setItem(27, filler);
					i.setItem(35, filler);
					i.setItem(36, filler);
					i.setItem(37, filler);
					i.setItem(38, filler);
					i.setItem(39, filler);
					i.setItem(40, filler);
					i.setItem(41, filler);
					i.setItem(42, filler);
					i.setItem(43, filler);
					i.setItem(44, next);
				}
				else if(currentInv == 0)
				{
					i.setItem(0, filler);
					i.setItem(1, filler);
					i.setItem(2, filler);
					i.setItem(3, filler);
					i.setItem(4, filler);
					i.setItem(5, filler);
					i.setItem(6, filler);
					i.setItem(7, filler);
					i.setItem(8, filler);
					i.setItem(9, filler);
					i.setItem(17, filler);
					i.setItem(18, filler);
					i.setItem(26, filler);
					i.setItem(27, filler);
					i.setItem(35, filler);
					i.setItem(36, previous);
					i.setItem(37, filler);
					i.setItem(38, filler);
					i.setItem(39, filler);
					i.setItem(40, filler);
					i.setItem(41, filler);
					i.setItem(42, filler);
					i.setItem(43, filler);
					i.setItem(44, filler);
				}
				else
				{
					i.setItem(0, filler);
					i.setItem(1, filler);
					i.setItem(2, filler);
					i.setItem(3, filler);
					i.setItem(4, filler);
					i.setItem(5, filler);
					i.setItem(6, filler);
					i.setItem(7, filler);
					i.setItem(8, filler);
					i.setItem(9, filler);
					i.setItem(17, filler);
					i.setItem(18, filler);
					i.setItem(26, filler);
					i.setItem(27, filler);
					i.setItem(35, filler);
					i.setItem(36, previous);
					i.setItem(37, filler);
					i.setItem(38, filler);
					i.setItem(39, filler);
					i.setItem(40, filler);
					i.setItem(41, filler);
					i.setItem(42, filler);
					i.setItem(43, filler);
					i.setItem(44, next);
				}
				
				for(int j = index; j < uuids.size(); j++)
				{
					ItemStack player = new ItemStack(Material.SKULL_ITEM, 1, (byte) 0);
					SkullMeta meta = (SkullMeta) player.getItemMeta();
					
					Player target = Bukkit.getPlayer(uuids.get(j));
					String issuer = notes.getIssuerName(uuids.get(j));	
					
					if(target != null)
					{
						List<String> list = new ArrayList<String>();
						meta.setOwner(target.getName());
						list.add(ChatColor.YELLOW + "Issued by: " + ChatColor.AQUA + issuer);
						list.add(ChatColor.YELLOW + "Notes: " + ChatColor.AQUA + notes.GetNotes(uuids.get(j)));
						meta.setLore(list);
						meta.setDisplayName(ChatColor.YELLOW + "Player Name: " + ChatColor.AQUA +  target.getName());
						player.setItemMeta(meta);
						
						i.setItem(countSlot, player);
						
						if(countSlot == 16)
						{
							countSlot = 19;
						}
						else if(countSlot == 25)
						{
							countSlot = 28;
						}
						else 
						{
							countSlot++;
						}
					}
					else
					{
						String target1 = notes.getPlayerName(uuids.get(j));
						
						List<String> list = new ArrayList<String>();
						meta.setOwner(target1);
						list.add(ChatColor.YELLOW + "Issued by: " + ChatColor.AQUA + issuer);
						list.add(ChatColor.YELLOW + "Notes: " + ChatColor.AQUA + notes.GetNotes(uuids.get(j)));
						meta.setLore(list);
						meta.setDisplayName(ChatColor.YELLOW + "Player Name: " + ChatColor.AQUA +  target1);
						player.setItemMeta(meta);
						
						i.setItem(countSlot, player);
						
						if(countSlot == 16)
						{
							countSlot = 19;
						}
						else if(countSlot == 25)
						{
							countSlot = 28;
						}
						else 
						{
							countSlot++;
						}
					}
					
					if(count % 21 == 0)
					{
						count++;
						index = count;
						break;
					}
					
					count++;
				}
				
				inventories.set(k, i);
				
				currentInv++;
			}
			
			ReadNotes.currentPage = inventories.size() - 1;
			
			p.openInventory(inventories.get(inventories.size() - 1));
		}
		else
		{
			int count = 10;
			
			Inventory i = plugin.getServer().createInventory(null, 45, ChatColor.RED + "Player Notes");
			
			ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0);
			ItemMeta fmeta = filler.getItemMeta();
			fmeta.setDisplayName(" ");
			filler.setItemMeta(fmeta);
			
			i.setItem(0, filler);
			i.setItem(1, filler);
			i.setItem(2, filler);
			i.setItem(3, filler);
			i.setItem(4, filler);
			i.setItem(5, filler);
			i.setItem(6, filler);
			i.setItem(7, filler);
			i.setItem(8, filler);
			i.setItem(9, filler);
			i.setItem(17, filler);
			i.setItem(18, filler);
			i.setItem(26, filler);
			i.setItem(27, filler);
			i.setItem(35, filler);
			i.setItem(36, filler);
			i.setItem(37, filler);
			i.setItem(38, filler);
			i.setItem(39, filler);
			i.setItem(40, filler);
			i.setItem(41, filler);
			i.setItem(42, filler);
			i.setItem(43, filler);
			i.setItem(44, filler);
			
			for(int j = 0; j < uuids.size(); j++)
			{
				ItemStack player = new ItemStack(Material.SKULL_ITEM, 1, (byte) 0);
				SkullMeta meta = (SkullMeta) player.getItemMeta();
				
				Player target = Bukkit.getPlayer(uuids.get(j));
				String issuer = notes.getIssuerName(uuids.get(j));
				
				if(target != null)
				{
					List<String> list = new ArrayList<String>();
					meta.setOwner(target.getName());
					list.add(ChatColor.YELLOW + "Issued by: " + ChatColor.AQUA + issuer);
					list.add(ChatColor.YELLOW + "Notes: " + ChatColor.AQUA + notes.GetNotes(uuids.get(j)));
					meta.setLore(list);
					meta.setDisplayName(ChatColor.YELLOW + "Player Name: " + ChatColor.AQUA +  target.getName());
					player.setItemMeta(meta);
					
					i.setItem(count,player);
					
					if(count == 16)
					{
						count = 19;
					}
					else if(count == 25)
					{
						count = 28;
					}
					else 
					{
						count++;
					}
				}
				else
				{
					String target1 = notes.getPlayerName(uuids.get(j));
					
					List<String> list = new ArrayList<String>();
					meta.setOwner(target1);
					list.add(ChatColor.YELLOW + "Issued by: " + ChatColor.AQUA + issuer);
					list.add(ChatColor.YELLOW + "Notes: " + ChatColor.AQUA + notes.GetNotes(uuids.get(j)));
					meta.setLore(list);
					meta.setDisplayName(ChatColor.YELLOW + "Player Name: " + ChatColor.AQUA +  target1);
					player.setItemMeta(meta);
					
					i.setItem(count, player);
					
					if(count == 16)
					{
						count = 19;
					}
					else if(count == 25)
					{
						count = 28;
					}
					else 
					{
						count++;
					}
				}
			}
			
			p.openInventory(i);
		}
	}
	
	public void getNotesInv(Player p, UUID uuid)
	{	
		Inventory i = plugin.getServer().createInventory(null, 27, ChatColor.RED + "Notes");
		
		ItemStack player = new ItemStack(Material.SKULL_ITEM, 1, (byte) 0);
		SkullMeta meta = (SkullMeta) player.getItemMeta();
		
		ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0);
		ItemMeta fmeta = filler.getItemMeta();
		fmeta.setDisplayName(" ");
		filler.setItemMeta(fmeta);
			
		Player target = Bukkit.getPlayer(uuid);
			
		if(target != null)
		{
			List<String> list = new ArrayList<String>();
			meta.setOwner(target.getName());
			list.add(ChatColor.YELLOW + "Issued by: " + ChatColor.AQUA + p.getDisplayName());
			list.add(ChatColor.YELLOW + "Notes: " + ChatColor.AQUA + notes.GetNotes(uuid));
			meta.setLore(list);
			meta.setDisplayName(ChatColor.YELLOW + "Player Name: " + ChatColor.AQUA +  target.getName());
			player.setItemMeta(meta);
				
			i.addItem(player);
		}
		else
		{
			OfflinePlayer target1 = Bukkit.getOfflinePlayer(uuid);
					
			List<String> list = new ArrayList<String>();
			meta.setOwner(target1.getName());
			list.add(ChatColor.YELLOW + "Issued by: " + ChatColor.AQUA + p.getDisplayName());
			list.add(ChatColor.YELLOW + "Notes: " + ChatColor.AQUA + notes.GetNotes(uuid));
			meta.setLore(list);
			meta.setDisplayName(ChatColor.YELLOW + "Player Name: " + ChatColor.AQUA +  target1.getName());
			player.setItemMeta(meta);
				
			i.addItem(player);
		}
		
		i.setItem(0, filler);
		i.setItem(1, filler);
		i.setItem(2, filler);
		i.setItem(3, filler);
		i.setItem(4, filler);
		i.setItem(5, filler);
		i.setItem(6, filler);
		i.setItem(7, filler);
		i.setItem(8, filler);
		i.setItem(9, filler);
		i.setItem(10, filler);
		i.setItem(11, filler);
		i.setItem(12, filler);

		i.setItem(13, player);
		
		i.setItem(14, filler);
		i.setItem(15, filler);
		i.setItem(16, filler);
		i.setItem(17, filler);
		i.setItem(18, filler);
		i.setItem(19, filler);
		i.setItem(20, filler);
		i.setItem(21, filler);
		i.setItem(22, filler);
		i.setItem(23, filler);
		i.setItem(24, filler);
		i.setItem(25, filler);
		i.setItem(26, filler);
		
		
		p.openInventory(i);
	}
	
	public void addNotesInv(Player p)
	{
		Inventory i = plugin.getServer().createInventory(null, 36, ChatColor.YELLOW + "Add Notes");
		
		ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0);
		ItemMeta fmeta = filler.getItemMeta();
		fmeta.setDisplayName(" ");
		filler.setItemMeta(fmeta);
		
		ItemStack xray = new ItemStack(Material.DIAMOND_ORE, 1, (byte) 0);
		ItemMeta xmeta = xray.getItemMeta();
		xmeta.setDisplayName(ChatColor.AQUA + "Possible Xray/CaveFinder.");
		xray.setItemMeta(xmeta);
		
		ItemStack bugabuse = new ItemStack(Material.ARROW, 1, (byte) 0);
		ItemMeta bmeta = bugabuse.getItemMeta();
		bmeta.setDisplayName(ChatColor.AQUA + "Possibly abusing a bug.");
		bugabuse.setItemMeta(bmeta);
		
		ItemStack reach = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 0);
		ItemMeta rmeta = reach.getItemMeta();
		rmeta.setDisplayName(ChatColor.AQUA + "Possibly using reach.");
		reach.setItemMeta(rmeta);
		
		ItemStack tracers = new ItemStack(Material.BOOK, 1, (byte) 0);
		ItemMeta tmeta = tracers.getItemMeta();
		tmeta.setDisplayName(ChatColor.AQUA + "Possibly using tracers.");
		tracers.setItemMeta(tmeta);
		
		ItemStack autoclick = new ItemStack(Material.LEVER, 1, (byte) 0);
		ItemMeta ameta = autoclick.getItemMeta();
		ameta.setDisplayName(ChatColor.AQUA + "Possibly using an autoclicker.");
		autoclick.setItemMeta(ameta);
		
		ItemStack cancel = new ItemStack(Material.REDSTONE_BLOCK, 1, (byte) 0);
		ItemMeta cmeta = cancel.getItemMeta();
		cmeta.setDisplayName(ChatColor.RED + "Cancel notes");
		cancel.setItemMeta(cmeta);
		
		ItemStack write = new ItemStack(Material.BOOK_AND_QUILL, 1, (byte) 0);
		ItemMeta wmeta = write.getItemMeta();
		wmeta.setDisplayName(ChatColor.YELLOW + "Write your own notes");
		write.setItemMeta(wmeta);
		
		i.setItem(0, filler);
		i.setItem(1, filler);
		i.setItem(2, filler);
		i.setItem(3, filler);
		i.setItem(4, filler);
		i.setItem(5, filler);
		i.setItem(6, filler);
		i.setItem(7, filler);
		i.setItem(8, filler);
		i.setItem(9, filler);
		
		i.setItem(11, xray);
		i.setItem(12, bugabuse);
		i.setItem(13, reach);
		i.setItem(14, tracers);
		i.setItem(15, autoclick);
		
		i.setItem(21, cancel);
		i.setItem(23, write);
		
		i.setItem(17, filler);
		i.setItem(18, filler);
		i.setItem(26, filler);
		i.setItem(27, filler);
		i.setItem(28, filler);
		i.setItem(29, filler);
		i.setItem(30, filler);
		i.setItem(31, filler);
		i.setItem(32, filler);
		i.setItem(33, filler);
		i.setItem(34, filler);
		i.setItem(35, filler);
		
		p.openInventory(i);
	}
}