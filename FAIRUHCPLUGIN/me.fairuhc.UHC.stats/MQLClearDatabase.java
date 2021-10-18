package me.fairuhc.UHC.stats;

import org.bukkit.event.Listener;

import me.fairuhc.UHC.Main;

public class MQLClearDatabase implements Listener
{
	public MySQLKills kills;
	public MySQLDeaths deaths;
	public MySQLGaps gaps;
	public MySQLkdr kdr;
	public MySQLGoldenHeads heads;
	public MySQLNether nether;
	public MySQLOresMined ores;
	public MySQLPlayTime time;
	public MySQLPotions pot;
	public MySQLWins win;
	public Main plugin;
	
	public MQLClearDatabase()
	{
		kills = new MySQLKills();
		deaths = new MySQLDeaths();
		gaps = new MySQLGaps();
		kdr = new MySQLkdr();
		heads = new MySQLGoldenHeads();
		nether = new MySQLNether();
		ores = new MySQLOresMined();
		time = new MySQLPlayTime();
		pot = new MySQLPotions();
		win = new MySQLWins();
	}
	
	public void clearAll()
	{
		kills.emptytable();
		deaths.emptytable();
		gaps.emptytable();
		kdr.emptytable();
		heads.emptytable();
		nether.emptytable();
		ores.emptytable();
		time.emptytable();
		pot.emptytable();
		win.emptytable();
	}
}
