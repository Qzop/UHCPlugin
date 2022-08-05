package core.Events;

import core.Arena.PracticeArena;
import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.mainPackage.Commands;
import core.mainPackage.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener
{
    @EventHandler
    public void onLeave(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        NPCEvent onNPC = new NPCEvent();

        Main.online.removePlayer(p);

        if(p.hasPermission("chat.owner"))
        {
            e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.DARK_RED + ChatColor.BOLD +  " " + p.getDisplayName());
        }
        else if(p.hasPermission("chat.dev"))
        {
            e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.GOLD + ChatColor.BOLD + " " + p.getDisplayName());
        }
        else if(p.hasPermission("chat.admin"))
        {
            e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.RED + " " + p.getDisplayName());
        }
        else if(p.hasPermission("chat.srmod"))
        {
            e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.DARK_BLUE + " " + p.getDisplayName());
        }
        else if(p.hasPermission("chat.mod"))
        {
            e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.DARK_GREEN + " " + p.getDisplayName());
        }
        else if(p.hasPermission("chat.trial"))
        {
            e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.GREEN + " " + p.getDisplayName());
        }
        else
        {
            e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "]" + ChatColor.WHITE + " " + p.getDisplayName());
        }

        if(Commands.scatter)
        {
            if(Scatter.allPlayers.contains(p.getUniqueId()) && p.getWorld().getName().equals("uhc_world"))
            {
                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.ZOMBIE, "" + ChatColor.BOLD + ChatColor.RED + "[Disconnected] " + p.getDisplayName());
                onNPC.spawnNPC(npc, p);
            }
        }
        else if(Scatter.started)
        {
            if(Scatter.allPlayers.contains(p.getUniqueId()) && !Scatter.offlineDuringScat.containsKey(p.getUniqueId()))
            {
                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.ZOMBIE, "" + ChatColor.BOLD + ChatColor.RED + "[Disconnected] " + p.getDisplayName());
                onNPC.spawnNPC(npc, p);
            }
        }
        else
        {
            if(PracticeArena.playersInArena.contains(p.getUniqueId()))
            {
                PracticeArena.playersInArena.remove(p.getUniqueId());
            }
        }
    }
}
