package core.Scoreboard;

import core.ChunkLoad.Chunks;
import core.Config.ConfigInventory;
import core.ConfigVariables.BedRockBorder;
import core.Events.NPCEvent;
import core.Events.Quit;
import core.Scatter.Scatter;
import core.Teams.TeamManager;
import core.mainPackage.Commands;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Time implements Listener
{
	private BedRockBorder bord = new BedRockBorder();
    private static int time = 0;
    public static int minutes = 0;
    public static int hours = 0;
    private static int lastShrink = 0;
    private boolean first = false;
    Main plugin = Main.getPlugin(Main.class);
    private Chunks chunks = new Chunks();
    
    
    public void setTime()
    {
        new BukkitRunnable()
        {
            public void run()
            {
				/*if(ConfigInventory.teamSize == 1)
				{
					if(Scatter.allPlayers.size() == 1)
					{
						Scatter.ended = true;
						announceWinners();
						cancel();
					}
				}
				else
				{
					if(TeamManager.teams.size() == 1)
					{
						Scatter.ended = true;
						announceWinners();
						cancel();
					}
				}*/
                
                time++;

				if(!NPCEvent.disconnected.isEmpty())
				{
					for(UUID k : NPCEvent.disconnected.keySet())
					{
						if(NPCEvent.disconnected.get(k) == 600)
						{
							NPCEvent npc = new NPCEvent();
							npc.setDisqualified(k);
						}
						else
						{
							NPCEvent.disconnected.put(k, NPCEvent.disconnected.get(k) + 1);
						}
					}
				}
                
                if(time % 60 == 0 && time != 0)
                {
                    time = 0;
                    minutes++;
                }

                if(minutes % 60 == 0 && minutes != 0)
                {
                    minutes = 0;
                    hours++;
                }
                
            	check();
            }

        }.runTaskTimer(plugin, 0, 20);
    }

    public String getTime()
    {
        String sTime = "";

        sTime += hours + "h " + minutes + "m " + time + "s";

        return sTime;
    }
    
    public void check()
    {
    	if(minutes == 5 && time == 0)
        {
        	Commands.chat = false;
        	Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.GREEN + " Chat is now enabled.");
        }
        
        if(minutes == ConfigInventory.finalHeal && time == 0)
        {
        	for(Player player : Main.online.getOnlinePlayers())
        	{
        		player.setHealth(player.getHealth() + (20.0 - player.getHealth()));
        	}
        	
        	Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.GREEN + " Final heal has been given." + ChatColor.RED + " DO NOT " + ChatColor.GREEN + "ask for another.");
        }

		if(minutes == ConfigInventory.gracePeriod && time == 0)
		{
			Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.YELLOW + " Grace Period has ended. Good luck.");
		}
        
        if(Scatter.numShrinks != 0)
        {
        	if(minutes == ConfigInventory.firstShrink - 5 && time == 0)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 5 minutes.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 4 && time == 0)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 4 minutes.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 3 && time == 0)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 3 minutes.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 2 && time == 0)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 2 minutes.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 0)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 1 minute.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 30)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 30 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 45)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 15 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 50)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 10 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 51)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 9 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 52)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 8 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 53)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 7 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 54)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 6 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 55)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 5 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 56)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 4 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 57)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 3 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 58)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 2 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink - 1 && time == 59)
        	{
        		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " First Shrink will occur in 1 seconds.");
        	}
        	else if(minutes == ConfigInventory.firstShrink && time == 0)
        	{
				bord.setUpShrink();
        		lastShrink = minutes;
        		Scatter.numShrinks--;
        		first = true;
        	}
        	
        	if(first)
        	{
        		if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 4 && time == 0)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 4 minutes.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 3 && time == 0)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 3 minutes.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 2 && time == 0)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 2 minutes.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 0)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 1 minute.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 30)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 30 seconds.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 45)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 15 seconds.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 50)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 10 seconds.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 51)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 9 seconds.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 52)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 8 seconds.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 53)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 7 seconds.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 54)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 6 seconds.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 55)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 5 seconds.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 56)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 4 seconds.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 57)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 3 seconds.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 58)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 2 seconds.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) - 1 && time == 59)
            	{
            		Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.AQUA + " Next Shrink will occur in 1 second.");
            	}
            	else if(minutes == (lastShrink + ConfigInventory.shrinkInterval) && time == 0)
            	{
					bord.setUpShrink();
            		lastShrink = minutes;
            		Scatter.numShrinks--;
            	}
        	}
        }
    }

	public void announceWinners()
	{
		String winners = "";

		if(ConfigInventory.teamSize == 1)
		{
			Player winner = Bukkit.getPlayer(Scatter.allPlayers.get(0));

			if(winner != null)
			{
				Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.GREEN + " Congratulations to " + ChatColor.AQUA + ChatColor.BOLD + winner.getDisplayName() + ChatColor.GREEN + " on winning this UHC!");
			}
			else
			{
				OfflinePlayer pl = Bukkit.getOfflinePlayer(Scatter.allPlayers.get(0));
				Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.GREEN + " Congratulations to " + ChatColor.AQUA + ChatColor.BOLD + pl.getName() + ChatColor.GREEN + " on winning this UHC!");
			}
		}
		else
		{
			UUID winner = UUID.randomUUID();

			for(UUID keys : TeamManager.teams.keySet())
			{
				winner = keys;
				break;
			}

			if(Bukkit.getPlayer(winner) != null)
			{
				winners = Bukkit.getPlayer(winner).getDisplayName();
			}
			else
			{
				winners = Bukkit.getOfflinePlayer(winner).getName();
			}

			for(int i = 0; i < TeamManager.teams.get(winner).size(); i++)
			{
				Player win = Bukkit.getPlayer(TeamManager.teams.get(winner).get(i));

				if(win != null)
				{
					if(i == TeamManager.teams.get(winner).size() - 1)
					{
						winners += "and " + win.getName();
					}
					else
					{
						winners += ", " + win.getName();
					}
				}
				else
				{
					OfflinePlayer winn = Bukkit.getOfflinePlayer(TeamManager.teams.get(winner).get(i));

					if(i == TeamManager.teams.get(winner).size() - 1)
					{
						winners += "and " + winn.getName();
					}
					else
					{
						winners += ", " + winn.getName();
					}
				}
			}

			Bukkit.broadcastMessage(Scatter.UHCprefix + ChatColor.GREEN + " Congratulations to " + ChatColor.AQUA + ChatColor.BOLD + winners + ChatColor.GREEN + " on winning this UHC!");
		}
	}
}
