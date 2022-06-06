package core.HostsMods;

import core.Scatter.Scatter;

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
            hosts.add(p.getUniqueId());
            Location loc = new Location(Bukkit.getWorld("uhcworld"), 0, 100, 0);
            p.teleport(loc);
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
            mods.add(p.getUniqueId());
            Location loc = new Location(Bukkit.getWorld("uhcworld"), 0, 100, 0);
            p.teleport(loc);
            p.sendMessage(Scatter.UHCprefix + ChatColor.GREEN + " You are now a Mod.");
        }
    }

    public void removeHost(Player p)
    {
        if(hosts.contains(p.getUniqueId()))
        {
            hosts.remove(p.getUniqueId());
            Location loc = new Location(Bukkit.getWorld("world"), 0, 100, 0);
            p.teleport(loc);
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
            mods.remove(p.getUniqueId());
            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You are no longer Mod.");
        }
        else
        {
            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You are not currently a Mod!");
        }
    }
}
