package core.Kills;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Spectator implements Listener
{
    public void setSpectator(Player p)
    {
        Location loc = new Location(Bukkit.getWorld("uhcworld"), 0, 100, 0);

        p.spigot().respawn();
        p.teleport(loc);
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
            if(p.getWorld().equals("uhcworld") && PlayerKills.dead.contains(p.getUniqueId()))
            {
                if(p.getLocation().getX() >= 101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhcworld"), 100, p.getLocation().getY(), p.getLocation().getZ());
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
                else if(p.getLocation().getZ() >= 101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhcworld"), p.getLocation().getX(), p.getLocation().getY(), 100);
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
                else if(p.getLocation().getX() <= -101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhcworld"), -100, p.getLocation().getY(), p.getLocation().getZ());
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
                else if(p.getLocation().getY() <= -101)
                {
                    Location temp = new Location(Bukkit.getWorld("uhcworld"), p.getLocation().getX(), p.getLocation().getY(), -100);
                    p.teleport(temp);
                    p.sendMessage(ChatColor.RED + "You cannot spectate outside of 100x100!");
                }
            }
        }
    }
}
