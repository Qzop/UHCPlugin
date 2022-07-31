package core.Kills;

import core.Chat.ChatEvent;
import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.Teams.TeamManager;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import core.ConfigVariables.BedRockBorder;
import core.HostsMods.HostsMods;

import java.util.UUID;

public class Spectator implements Listener
{
    public void setSpectator(Player p)
    {
        Location loc = new Location(Bukkit.getWorld("uhc_world"), 0, 100, 0);
        PlayerKills.spectator.add(p.getUniqueId());

        if(!ChatEvent.specchat.contains(p.getUniqueId()))
        {
            ChatEvent.specchat.add(p.getUniqueId());
        }

        p.spigot().respawn();
        p.teleport(loc);
        p.setAllowFlight(true);
        p.setFlying(true);

        for(Player players : Main.online.getOnlinePlayers())
        {
            players.hidePlayer(p);
        }

        p.sendMessage(Scatter.UHCprefix + ChatColor.GREEN + " You are now a spectator.");
    }
    
    public void removeSpectator(Player p)
    {
    	PlayerKills.spectator.remove(p.getUniqueId());
    	PlayerKills.deathLocations.remove(p.getUniqueId());

        if(ChatEvent.specchat.contains(p.getUniqueId()))
        {
            ChatEvent.specchat.remove(p.getUniqueId());
        }
    	
    	p.setAllowFlight(false);
    	p.setFlying(false);
    	
    	for(Player players : Main.online.getOnlinePlayers())
    	{
    		players.showPlayer(p);
    	}

        p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " You are no longer a spectator.");
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();

        if(!p.hasPermission("spec.bypass"))
        {
            if(p.getWorld().getName().equals("uhc_world") && PlayerKills.spectator.contains(p.getUniqueId()))
            {
                if(p.getLocation().getBlockX() >= 101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhc_world"), 100, p.getLocation().getY(), p.getLocation().getZ());
                    temp.setYaw(p.getLocation().getYaw());
                    temp.setPitch(p.getLocation().getPitch());
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
                else if(p.getLocation().getBlockZ() >= 101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhc_world"), p.getLocation().getX(), p.getLocation().getY(), 100);
                    temp.setYaw(p.getLocation().getYaw());
                    temp.setPitch(p.getLocation().getPitch());
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
                else if(p.getLocation().getBlockX() <= -101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhc_world"), -100, p.getLocation().getY(), p.getLocation().getZ());
                    temp.setYaw(p.getLocation().getYaw());
                    temp.setPitch(p.getLocation().getPitch());
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
                else if(p.getLocation().getBlockZ() <= -101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhc_world"), p.getLocation().getX(), p.getLocation().getY(), -100);
                    temp.setYaw(p.getLocation().getYaw());
                    temp.setPitch(p.getLocation().getPitch());
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
            }
        }
        
        if(p.getWorld().getName().equals("uhc_world") && !PlayerKills.spectator.contains(p.getUniqueId()) && !HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
        {
            double border = BedRockBorder.currentBorderSize;

        	if(p.getLocation().getBlockX() >= border)
        	{
        		Location temp = new Location(Bukkit.getWorld("uhc_world"), border - 1, p.getLocation().getBlockY(), p.getLocation().getBlockZ());
        		temp.setYaw(p.getLocation().getYaw());
                temp.setPitch(p.getLocation().getPitch());
                p.teleport(temp);
                p.sendMessage(ChatColor.RED + "You cannot go outside of the border!");
        	}
        	else if(p.getLocation().getBlockX() <= -border)
        	{
        		Location temp = new Location(Bukkit.getWorld("uhc_world"), -border + 2, p.getLocation().getBlockY(), p.getLocation().getBlockZ());
        		temp.setYaw(p.getLocation().getYaw());
                temp.setPitch(p.getLocation().getPitch());
                p.teleport(temp);
                p.sendMessage(ChatColor.RED + "You cannot go outside of the border!");
        	}
        	else if(p.getLocation().getBlockZ() >= border)
        	{
        		Location temp = new Location(Bukkit.getWorld("uhc_world"), p.getLocation().getBlockX(), p.getLocation().getBlockY(), border - 1);
        		temp.setYaw(p.getLocation().getYaw());
                temp.setPitch(p.getLocation().getPitch());
                p.teleport(temp);
                p.sendMessage(ChatColor.RED + "You cannot go outside of the border!");
        	}
        	else if(p.getLocation().getBlockZ() <= -border)
        	{
        		Location temp = new Location(Bukkit.getWorld("uhc_world"), p.getLocation().getBlockX(), p.getLocation().getBlockY(), -border + 2);
        		temp.setYaw(p.getLocation().getYaw());
                temp.setPitch(p.getLocation().getPitch());
                p.teleport(temp);
                p.sendMessage(ChatColor.RED + "You cannot go outside of the border!");
        	}
        }

        if(p.getWorld().getName().equals("world"))
        {
            if(p.getLocation().getY() <= 50)
            {
                p.performCommand("spawn");
            }
        }
    }
}
