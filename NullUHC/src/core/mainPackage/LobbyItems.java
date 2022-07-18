package core.mainPackage;

import core.Scatter.Scatter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyItems implements Listener
{
    Main plugin = Main.getPlugin(Main.class);

    public void lobbyItems(Player p)
    {
        new BukkitRunnable()
        {
            public void run()
            {
                p.getInventory().clear();
                p.getInventory().setBoots(null);
                p.getInventory().setLeggings(null);
                p.getInventory().setChestplate(null);
                p.getInventory().setHelmet(null);

                ItemStack arena = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta arenameta = arena.getItemMeta();
                arenameta.setDisplayName(ChatColor.YELLOW + "Practice Arena");
                arena.setItemMeta(arenameta);

                ItemStack config = new ItemStack(Material.CHEST);
                ItemMeta configmeta = config.getItemMeta();
                configmeta.setDisplayName(ChatColor.YELLOW + "Config");
                config.setItemMeta(configmeta);

                ItemStack scenarios = new ItemStack(Material.DIAMOND);
                ItemMeta scenariosmeta = scenarios.getItemMeta();
                scenariosmeta.setDisplayName(ChatColor.YELLOW + "Scenarios");
                scenarios.setItemMeta(scenariosmeta);

                p.getInventory().setItem(0, arena);
                p.getInventory().setItem(4, config);
                p.getInventory().setItem(8, scenarios);

                cancel();
            }

        }.runTaskTimer(plugin, 0, 1);
    }
}
