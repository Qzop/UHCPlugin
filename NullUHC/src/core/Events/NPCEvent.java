package core.Events;

import core.Config.ConfigInventory;
import core.Scatter.Scatter;
import core.Scoreboard.Time;
import core.Teams.TeamManager;
import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import net.citizensnpcs.api.event.NPCDamageEvent;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class NPCEvent implements Listener
{
    public static HashMap<UUID, Integer> disconnected = new HashMap<UUID, Integer>();
    public static HashMap<UUID, NPC> npcList = new HashMap<UUID, NPC>();
    private static HashMap<UUID, ArrayList<ItemStack>> inventory = new HashMap<UUID, ArrayList<ItemStack>>();
    public static ArrayList<UUID> disqualified = new ArrayList<UUID>();

    public void spawnNPC(NPC npc, Player p)
    {
        ArrayList<ItemStack> tempInv = new ArrayList<ItemStack>();

        for(int i = 0; i < p.getInventory().getSize(); i++)
        {
            if(p.getInventory().getItem(i) != null)
            {
                tempInv.add(p.getInventory().getItem(i));
            }
        }

        for(int i = 0; i < p.getInventory().getArmorContents().length; i++)
        {
            if(p.getInventory().getArmorContents()[i] != null)
            {
                tempInv.add(p.getInventory().getArmorContents()[i]);
            }
        }

        inventory.put(p.getUniqueId(), tempInv);
        disconnected.put(p.getUniqueId(), 0);
        npcList.put(p.getUniqueId(), npc);
        npc.setProtected(false);
        npc.spawn(p.getLocation());
    }

    public void removeNPC(Player p)
    {
        npcList.get(p.getUniqueId()).destroy();
        npcList.remove(p.getUniqueId());
        inventory.remove(p.getUniqueId());
        disconnected.remove(p.getUniqueId());
    }

    @EventHandler
    public void onDamageByPlayer(NPCDamageByEntityEvent e)
    {
        if(Time.minutes < ConfigInventory.gracePeriod)
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamageEnvironment(NPCDamageEvent e)
    {
        if(e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(NPCDeathEvent e)
    {
        for(UUID keys : npcList.keySet())
        {
            if(npcList.get(keys).getUniqueId().equals(e.getNPC().getUniqueId()))
            {
                Bukkit.broadcastMessage("TEST");

                npcList.get(keys).destroy();

                for (int i = 0; i < inventory.get(keys).size(); i++) {
                    e.getNPC().getEntity().getWorld().dropItemNaturally(e.getNPC().getEntity().getLocation(), inventory.get(keys).get(i));
                }

                npcList.remove(keys);
                disconnected.remove(keys);
                inventory.remove(keys);

                if(ConfigInventory.teamSize == 1)
                {
                    Scatter.allPlayers.remove(keys);
                }
                else
                {
                    Scatter.allPlayers.remove(keys);

                    TeamManager tm = new TeamManager();
                    UUID cap = tm.getCaptainOffline(Bukkit.getOfflinePlayer(keys));
                    int count = 0;

                    for(UUID uuid : TeamManager.teams.get(cap))
                    {
                        if(!Scatter.allPlayers.contains(uuid))
                        {
                            count++;
                        }
                    }

                    if(count == ConfigInventory.teamSize)
                    {
                        TeamManager.teams.remove(cap);
                    }
                }
            }
        }
    }

    public void setDisqualified(UUID p)
    {
        if(Scatter.alreadyScattered.contains(p))
        {
            Scatter.alreadyScattered.remove(p);
        }

        for (int i = 0; i < inventory.get(p).size(); i++)
        {
            if(inventory.get(p).get(i).getType() != Material.AIR && inventory.get(p).get(i) != null)
            {
                npcList.get(p).getEntity().getWorld().dropItemNaturally(npcList.get(p).getEntity().getLocation(), inventory.get(p).get(i));
            }
        }

        disqualified.add(p);
        disconnected.remove(p);
        npcList.get(p).destroy();
        inventory.remove(p);

        if(ConfigInventory.teamSize == 1)
        {
            Scatter.allPlayers.remove(p);
        }
        else
        {
            TeamManager tm = new TeamManager();
            UUID cap = tm.getCaptainOffline(Bukkit.getOfflinePlayer(p));
            int count = 0;

            if(!Scatter.allPlayers.contains(cap))
            {
                count++;
            }

            for(UUID uuid : TeamManager.teams.get(cap))
            {
                if(!Scatter.allPlayers.contains(uuid))
                {
                    count++;
                }
            }

            if(count == TeamManager.teams.get(cap).size() + 1)
            {
                TeamManager.aliveTeams--;
            }
        }

        Bukkit.broadcastMessage(Scatter.UHCprefix + " " + ChatColor.GOLD + Bukkit.getOfflinePlayer(p).getName() + ChatColor.RED + " Has been disqualified for being disconnected for more than 10 minutes.");
    }
}
