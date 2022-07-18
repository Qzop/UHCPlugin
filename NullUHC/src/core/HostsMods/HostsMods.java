package core.HostsMods;

import core.Scatter.Scatter;

import core.mainPackage.Commands;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class HostsMods
{
    public static ArrayList<UUID> hosts = new ArrayList<UUID>();
    public static ArrayList<UUID> mods = new ArrayList<UUID>();
    private HostModsItems items = new HostModsItems();

    public void setHost(Player p)
    {
        if((hosts.contains(p.getUniqueId()) || mods.contains(p.getUniqueId())) && hosts.isEmpty())
        {
            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You are already a Host/Mod!");
        }
        else if(!hosts.isEmpty())
        {
            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " There can only be 1 Host!");
        }
        else
        {
        	for(Player players : Main.online.getOnlinePlayers())
        	{
        		players.hidePlayer(p);
        	}

            HostModsItems.vanished.add(p.getUniqueId());
        	
        	p.setAllowFlight(true);
			p.setFlying(true);
        	
            hosts.add(p.getUniqueId());
            Location loc = new Location(Bukkit.getWorld("uhc_world"), 0, 100, 0);
            p.teleport(loc);
            items.hostmodItemsInGame(p);
            p.sendMessage(Scatter.UHCprefix + ChatColor.GREEN + " You are now a Host.");
        }
    }

    public void setMod(Player p)
    {
        if((hosts.contains(p.getUniqueId()) || mods.contains(p.getUniqueId())))
        {
            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You are already a Host/Mod!");
        }
        else
        {
        	for(Player players : Main.online.getOnlinePlayers())
        	{
        		players.hidePlayer(p);
        	}

            HostModsItems.vanished.add(p.getUniqueId());
        	
        	p.setAllowFlight(true);
			p.setFlying(true);
        	
            mods.add(p.getUniqueId());
            Location loc = new Location(Bukkit.getWorld("uhc_world"), 0, 100, 0);
            p.teleport(loc);
            items.hostmodItemsInGame(p);
            p.sendMessage(Scatter.UHCprefix + ChatColor.GREEN + " You are now a Mod.");
        }
    }

    public void removeHost(Player p)
    {
        if(hosts.contains(p.getUniqueId()))
        {
        	p.getInventory().clear();
        	
        	for(Player players : Main.online.getOnlinePlayers())
        	{
        		players.showPlayer(p);
        	}

            if(HostModsItems.vanished.contains(p.getUniqueId()))
            {
                HostModsItems.vanished.remove(p.getUniqueId());
            }


        	p.setAllowFlight(false);
			p.setFlying(false);

            hosts.remove(p.getUniqueId());

            if(!Scatter.started && !Commands.scatter)
            {
                HostModsItems items = new HostModsItems();
                items.staffLobbyItems(p);
            }

            p.teleport(Bukkit.getWorld("world").getSpawnLocation());
            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You are no longer Host.");
        }
        else
        {
            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You are not currently a Host!");
        }
    }

    public void removeMod(Player p)
    {
        if(mods.contains(p.getUniqueId()))
        {
        	p.getInventory().clear();
        	
        	for(Player players : Main.online.getOnlinePlayers())
        	{
        		players.showPlayer(p);
        	}

            if(HostModsItems.vanished.contains(p.getUniqueId()))
            {
                HostModsItems.vanished.remove(p.getUniqueId());
            }

            if(!Scatter.started && !Commands.scatter)
            {
                HostModsItems items = new HostModsItems();
                items.staffLobbyItems(p);
            }
        	
        	p.setAllowFlight(false);
			p.setFlying(false);
        	
            mods.remove(p.getUniqueId());
            p.teleport(Bukkit.getWorld("world").getSpawnLocation());
            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You are no longer Mod.");
        }
        else
        {
            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You are not currently a Mod!");
        }
    }
}
