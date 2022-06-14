package core.Kills;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import core.ConfigVariables.BedRockBorder;
import core.HostsMods.HostsMods;

public class Spectator implements Listener
{
    public void setSpectator(Player p)
    {
        Location loc = new Location(Bukkit.getWorld("uhcworld"), 0, 100, 0);

        p.spigot().respawn();
        p.teleport(loc);
        p.setAllowFlight(true);
        p.setFlying(true);

        for(Player players : Bukkit.getOnlinePlayers())
        {
            players.hidePlayer(p);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();

        if(!p.hasPermission("spec.bypass"))
        {
            if(p.getWorld().getName().equals("uhc_world") && PlayerKills.dead.contains(p.getUniqueId()))
            {
                if(p.getLocation().getX() >= 101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhc_world"), 100, p.getLocation().getY(), p.getLocation().getZ());
                    temp.setYaw(p.getLocation().getYaw());
                    temp.setPitch(p.getLocation().getPitch());
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
                else if(p.getLocation().getZ() >= 101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhc_world"), p.getLocation().getX(), p.getLocation().getY(), 100);
                    temp.setYaw(p.getLocation().getYaw());
                    temp.setPitch(p.getLocation().getPitch());
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
                else if(p.getLocation().getX() <= -101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhc_world"), -100, p.getLocation().getY(), p.getLocation().getZ());
                    temp.setYaw(p.getLocation().getYaw());
                    temp.setPitch(p.getLocation().getPitch());
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
                else if(p.getLocation().getZ() <= -101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhc_world"), p.getLocation().getX(), p.getLocation().getY(), -100);
                    temp.setYaw(p.getLocation().getYaw());
                    temp.setPitch(p.getLocation().getPitch());
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
            }
        }
        
        if(p.getWorld().getName().equals("uhc_world") && !PlayerKills.dead.contains(p.getUniqueId()) && !HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
        {
        	if(p.getLocation().getX() >= BedRockBorder.currentBorderSize)
        	{
        		Location temp = new Location(Bukkit.getWorld("uhc_world"), BedRockBorder.currentBorderSize - 1, p.getLocation().getY(), p.getLocation().getZ());
        		temp.setYaw(p.getLocation().getYaw());
                temp.setPitch(p.getLocation().getPitch());
                p.teleport(temp);
                p.sendMessage(ChatColor.RED + "You cannot go outside of the border!");
        	}
        	else if(p.getLocation().getX() <= -BedRockBorder.currentBorderSize)
        	{
        		Location temp = new Location(Bukkit.getWorld("uhc_world"), -(BedRockBorder.currentBorderSize) + 1, p.getLocation().getY(), p.getLocation().getZ());
        		temp.setYaw(p.getLocation().getYaw());
                temp.setPitch(p.getLocation().getPitch());
                p.teleport(temp);
                p.sendMessage(ChatColor.RED + "You cannot go outside of the border!");
        	}
        	else if(p.getLocation().getZ() >= BedRockBorder.currentBorderSize)
        	{
        		Location temp = new Location(Bukkit.getWorld("uhc_world"), p.getLocation().getX(), p.getLocation().getY(), BedRockBorder.currentBorderSize - 1);
        		temp.setYaw(p.getLocation().getYaw());
                temp.setPitch(p.getLocation().getPitch());
                p.teleport(temp);
                p.sendMessage(ChatColor.RED + "You cannot go outside of the border!");
        	}
        	else if(p.getLocation().getZ() <= -BedRockBorder.currentBorderSize)
        	{
        		Location temp = new Location(Bukkit.getWorld("uhc_world"), p.getLocation().getX(), p.getLocation().getY(), -(BedRockBorder.currentBorderSize) + 1);
        		temp.setYaw(p.getLocation().getYaw());
                temp.setPitch(p.getLocation().getPitch());
                p.teleport(temp);
                p.sendMessage(ChatColor.RED + "You cannot go outside of the border!");
        	}
        }   
    }
}
