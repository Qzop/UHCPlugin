package Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import me.fairuhc.UHC.Gamemanager;
import me.fairuhc.UHC.Host;
import me.fairuhc.UHC.Main;
import me.fairuhc.UHC.Mod;
import me.fairuhc.UHC.border;

public class Timer implements Listener
{
	public static int gametime = 0;
	public static int realtime = 0;
	public static int count = 0;
	public static String time = "";
	public border bord;
	public Main plugin;
	private String prefix = ChatColor.AQUA + "" + ChatColor.BOLD + "Fair" + ChatColor.WHITE + "" + ChatColor.BOLD + "UHC"  + ChatColor.GRAY + " » " ;
	private String chatprefix = ChatColor.GOLD + "" + ChatColor.BOLD + "Chat" + ChatColor.GRAY + " » " ;
	public static boolean fheal = false;
	public static boolean gperiod = false;
	public static boolean chat = true;
	
	public Timer(Main plugin)
	{
		this.plugin = plugin;
		bord = new border(plugin);
	}
	
	public void GameTime()
	{		
		if(Gamemanager.started == true)
		{
			new BukkitRunnable()
			{
				public void run()
				{	
					if(gametime < 60)
					{
						time = (count + "h" + gametime / 60 + "m" + gametime + "s");
					}      	
					else if(gametime >= 60 && gametime < 600)
					{
						realtime = gametime - ((gametime / 60) * 60);
				     				
						if(realtime < 60)
						{
							time = count + "h" + gametime / 60 + "m" + realtime + "s";
						}
				     			
						realtime = 0;
					}
					else if(gametime >= 600 && gametime < 3600)
					{
						realtime = gametime - ((gametime / 60) * 60);
				 				
						if(realtime < 60)
						{
							time = count + "h" + gametime / 60 + "m" + realtime + "s";
						}
				     			
						realtime = 0;
					}
					else if(gametime == 3600)
					{
						gametime = 0;
						count++;
					}
					
					if(Gamemanager.ended == true)
					{
						cancel();
						return;
					}
					
					bord.bordershrinkCheck();
					gametime++;
					
					if(Timer.gametime == (Gamemanager.finalheal * 60) - 300)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 5 minutes!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60) - 240)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 4 minutes!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60) - 180)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 3 minutes!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60) - 120)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 2 minutes!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60) - 60)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 1 minute!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60) - 30)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 30 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60) - 15)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 15 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60) - 5)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 5 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60) - 4)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 4 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60) - 3)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 3 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60) - 2)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 2 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60) - 1)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Final heal is in 1 second!");
					}
					else if(Timer.gametime == (Gamemanager.finalheal * 60))
					{
						for(Player p : Bukkit.getOnlinePlayers())
						{
							if(!(Host.host.contains(p.getUniqueId()) || Mod.mod.contains(p.getUniqueId())))
							{
								if(20.0 - p.getHealth() < 20.0)
								{
									p.setHealth(p.getHealth() + (20.0 - p.getHealth()));
								}
								
							}
						}
						
						chat = true;
						fheal = true;
						Bukkit.broadcastMessage(prefix + ChatColor.GREEN + " Final heal has been given! Do not ask for another.");
						Bukkit.broadcastMessage(chatprefix + ChatColor.GREEN + " Chat is now unmuted.");
					}
					
					if(Timer.gametime == (Gamemanager.graceperiod * 60) - 300)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is ending in 5 minutes!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60) - 240)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is ending in 4 minutes!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60) - 180)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is ending in 3 minutes!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60) - 120)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is ending in 2 minutes!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60) - 60)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is ending in 1 minute!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60) - 30)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is ending in 30 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60) - 15)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is ending in 15 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60) - 5)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is ending in 5 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60) - 4)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is ending in 4 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60) - 3)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is ending in 3 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60) - 2)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is endingin 2 seconds!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60) - 1)
					{
						Bukkit.broadcastMessage(prefix + ChatColor.AQUA + " Grace Period is ending in 1 second!");
					}
					else if(Timer.gametime == (Gamemanager.graceperiod * 60))
					{
						gperiod = true;
						Bukkit.broadcastMessage(prefix + ChatColor.GREEN + " Grace Period has ended. Good luck!");
					}
				}
				
			}.runTaskTimer(plugin, 0, 20);
		}
	}
}
