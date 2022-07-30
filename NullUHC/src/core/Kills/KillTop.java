package core.Kills;

import core.Scatter.Scatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import java.util.*;

public class KillTop implements Listener
{
    public void getKillTop(Player p)
    {
        if(PlayerKills.numKills.isEmpty())
        {
            p.sendMessage(Scatter.UHCprefix + ChatColor.RED + " No players have kills at the moment!");
        }
        else
        {
           List<Map.Entry<UUID, Integer>> temp = new LinkedList<Map.Entry<UUID, Integer>>(PlayerKills.numKills.entrySet());

           Collections.sort(temp, new Comparator<Map.Entry<UUID, Integer>>()
           {
               @Override
               public int compare(Map.Entry<UUID, Integer> o1, Map.Entry<UUID, Integer> o2)
               {
                   return o2.getValue().compareTo(o1.getValue());
               }
           });

           Map<UUID, Integer> sorted = new LinkedHashMap<UUID, Integer>();

           for(Map.Entry<UUID, Integer> entry : temp)
           {
               sorted.put(entry.getKey(), entry.getValue());
           }

           int count = 0;
           String message = "" + ChatColor.YELLOW + ChatColor.BOLD + "Top Kills: \n";

           for(Map.Entry<UUID, Integer> entry : sorted.entrySet())
           {
               if(count == 10)
               {
                   break;
               }
               else
               {
                   Player key = Bukkit.getPlayer(entry.getKey());

                   if(key == null)
                   {
                       OfflinePlayer offkey = Bukkit.getOfflinePlayer(entry.getKey());

                       if(!PlayerKills.spectator.contains(offkey.getUniqueId()))
                       {
                           message += ChatColor.AQUA + offkey.getName() + ChatColor.GRAY +  " - " + ChatColor.LIGHT_PURPLE + entry.getValue() + ChatColor.YELLOW + " Kill(s)" + "\n";
                       }
                       else
                       {
                           message += ChatColor.RED + offkey.getName() + ChatColor.GRAY +  " - " + ChatColor.LIGHT_PURPLE + entry.getValue() + ChatColor.YELLOW + " Kill(s)" + "\n";
                       }
                   }
                   else
                   {
                       if(!PlayerKills.spectator.contains(key.getUniqueId()))
                       {
                           message += ChatColor.AQUA + key.getDisplayName() + ChatColor.GRAY +  " - " + ChatColor.LIGHT_PURPLE + entry.getValue() + ChatColor.YELLOW + " Kill(s)" + "\n";
                       }
                       else
                       {
                           message += ChatColor.RED + key.getName() + ChatColor.GRAY +  " - " + ChatColor.LIGHT_PURPLE + entry.getValue() + ChatColor.YELLOW + " Kill(s)" + "\n";
                       }
                   }

                   count++;
               }
           }

           p.sendMessage(message);
        }
    }
}
