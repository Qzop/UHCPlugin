package core.Kills;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Respawn implements Listener
{
	public void onRespawn(Player p, Player target)
	{
		Spectator spec = new Spectator();

		ArrayList<Material> helmets = new ArrayList<Material>();
		ArrayList<Material> chestplates = new ArrayList<Material>();
		ArrayList<Material> leggings = new ArrayList<Material>();
		ArrayList<Material> boots = new ArrayList<Material>();

		helmets.add(Material.DIAMOND_HELMET);
		helmets.add(Material.IRON_HELMET);
		helmets.add(Material.GOLD_HELMET);
		helmets.add(Material.CHAINMAIL_HELMET);
		helmets.add(Material.LEATHER_HELMET);

		chestplates.add(Material.DIAMOND_CHESTPLATE);
		chestplates.add(Material.IRON_CHESTPLATE);
		chestplates.add(Material.GOLD_CHESTPLATE);
		chestplates.add(Material.CHAINMAIL_CHESTPLATE);
		chestplates.add(Material.LEATHER_CHESTPLATE);

		leggings.add(Material.DIAMOND_LEGGINGS);
		leggings.add(Material.IRON_LEGGINGS);
		leggings.add(Material.GOLD_LEGGINGS);
		leggings.add(Material.CHAINMAIL_LEGGINGS);
		leggings.add(Material.LEATHER_LEGGINGS);

		boots.add(Material.DIAMOND_BOOTS);
		boots.add(Material.IRON_BOOTS);
		boots.add(Material.GOLD_BOOTS);
		boots.add(Material.CHAINMAIL_BOOTS);
		boots.add(Material.LEATHER_BOOTS);
		
		if(PlayerKills.spectator.contains(target.getUniqueId()))
		{
			if(PlayerKills.deathLocations.containsKey(target.getUniqueId()))
			{
				Location loc = new Location(PlayerKills.deathLocations.get(target.getUniqueId()).getWorld(), PlayerKills.deathLocations.get(target.getUniqueId()).getBlockX(), PlayerKills.deathLocations.get(target.getUniqueId()).getBlockY() + 2, PlayerKills.deathLocations.get(target.getUniqueId()).getBlockZ());
				target.teleport(loc);

				if (PlayerKills.inventory.containsKey(target.getUniqueId()))
				{
					for(ItemStack item : PlayerKills.inventory.get(target.getUniqueId()))
					{
						if(item != null && item.getType() != Material.AIR)
						{
							target.getInventory().addItem(item);
						}
					}

					PlayerKills.inventory.remove(target.getUniqueId());
				}

				if (PlayerKills.armorinv.containsKey(target.getUniqueId()))
				{
					for(ItemStack item : PlayerKills.armorinv.get(target.getUniqueId()))
					{
						if(item != null && item.getType() != Material.AIR)
						{
							if(helmets.contains(item.getType()))
							{
								target.getInventory().setHelmet(item);
							}
							else if(chestplates.contains(item.getType()))
							{
								target.getInventory().setChestplate(item);
							}
							else if(leggings.contains(item.getType()))
							{
								target.getInventory().setLeggings(item);
							}
							else if(boots.contains(item.getType()))
							{
								target.getInventory().setBoots(item);
							}
						}
					}

					PlayerKills.armorinv.remove(target.getUniqueId());
				}

				spec.removeSpectator(target);
				
				p.sendMessage(ChatColor.GREEN + target.getDisplayName() + " has been respawned!");
				target.sendMessage(ChatColor.GREEN + "You have been respawned.");

				Scatter.allPlayers.add(target.getUniqueId());
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You cannot respawn this player!");
		}
	}
}
