package core.Arena;

import core.Config.ConfigInventory;
import core.HostsMods.HostModsItems;
import core.HostsMods.HostsMods;
import core.Scatter.Scatter;
import core.mainPackage.Commands;
import core.mainPackage.LobbyItems;
import core.mainPackage.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class PracticeArena implements Listener
{
    public static ArrayList<UUID> playersInArena = new ArrayList<UUID>();
    Main plugin = Main.getPlugin(Main.class);
    private ArenaKit kit = new ArenaKit();
    public static boolean arena = true;

    public void onArenaJoin(Player p)
    {
        if(arena)
        {
            if(!Commands.scatter && !Scatter.started)
            {
                int randomX = new Random().nextInt(45 - 1) - 45;
                int randomZ = new Random().nextInt(45 - 1) - 45;

                new BukkitRunnable()
                {
                    public void run()
                    {
                        if (HostsMods.hosts.contains(p.getUniqueId()))
                        {
                            HostsMods.hosts.remove(p.getUniqueId());
                            p.setFlying(false);
                            p.setAllowFlight(false);
                            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You are no longer host.");
                        }
                        else if(HostsMods.mods.contains(p.getUniqueId()))
                        {
                            HostsMods.mods.remove(p.getUniqueId());
                            p.setFlying(false);
                            p.setAllowFlight(false);
                            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You are no longer mod.");
                        }

                        p.getInventory().clear();
                        p.getInventory().setBoots(null);
                        p.getInventory().setLeggings(null);
                        p.getInventory().setChestplate(null);
                        p.getInventory().setHelmet(null);

                        Location loc = new Location(Bukkit.getWorld("Arena"), randomX, 50 ,randomZ);
                        ArenaKills.arenaKills.put(p.getUniqueId(), 0);
                        playersInArena.add(p.getUniqueId());
                        kit.setArenaKit(p);
                        p.teleport(loc);
                        p.sendMessage(ChatColor.GREEN + "You are now in the arena.");
                        cancel();
                    }

                }.runTaskTimer(plugin, 0, 1);
            }
            else
            {
                p.sendMessage(ChatColor.RED + "You may not use this command now!");
            }
        }
        else
        {
            p.sendMessage(ChatColor.RED + "Practice arena has been turned off.");
        }
    }

    public void onArenaLeave(Player p)
    {
        if(!Commands.scatter && !Scatter.started)
        {
        	if(playersInArena.contains(p.getUniqueId()))
        	{
        		World world = Bukkit.getWorld("world");
                ArenaKills.arenaKills.remove(p.getUniqueId());
                playersInArena.remove(p.getUniqueId());

                new BukkitRunnable()
                {
                    public void run()
                    {
                        p.getInventory().clear();
                        p.getInventory().setBoots(null);
                        p.getInventory().setLeggings(null);
                        p.getInventory().setChestplate(null);
                        p.getInventory().setHelmet(null);

                        if(p.hasPermission("uhc.mod") || p.hasPermission("uhc.host"))
                        {
                            HostModsItems items = new HostModsItems();
                            items.staffLobbyItems(p);
                        }
                        else
                        {
                            LobbyItems lobbyitems = new LobbyItems();
                            lobbyitems.lobbyItems(p);
                        }

                        p.setHealth(p.getHealth() + (20.0 - p.getHealth()));
                        p.setFoodLevel(20);
                        p.setLevel(0);
                        p.setExp(0);
                        p.teleport(world.getSpawnLocation());
                        p.sendMessage(ChatColor.RED + "You have left the arena.");

                        cancel();
                    }

                }.runTaskTimer(plugin, 0, 1);
        	}
        	else
        	{
        		p.sendMessage(ChatColor.RED + "You are not in the arena!");
        	}
        }
        else
        {
            p.sendMessage(ChatColor.RED + "You may not use this command now!");
        }
    }
}
