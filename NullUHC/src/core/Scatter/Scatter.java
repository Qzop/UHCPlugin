package core.Scatter;

import java.util.*;

import core.Arena.ArenaKills;
import core.Arena.PracticeArena;
import core.Config.ConfigInventory;
import core.ConfigVariables.BedRockBorder;
import core.ConfigVariables.Horses;
import core.Events.Join;
import core.Events.NPCEvent;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Scenarios.SuperheroesCMD;
import core.ScenariosInventory.ScenariosInventory;
import core.Scoreboard.Time;
import core.Teams.TeamManager;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import core.mainPackage.Commands;
import core.mainPackage.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.util.Vector;

public class Scatter implements Listener
{
    private Horses horse = new Horses();
    private Time time = new Time();
    private PlayerKills kills = new PlayerKills();
    private BedRockBorder bord = new BedRockBorder();
    public static ArrayList<UUID> allPlayers = new ArrayList<UUID>();
    public static String UHCprefix = ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "NullUHC" + ChatColor.GRAY + "]";
    Main plugin = Main.getPlugin(Main.class);
    public static ArrayList<UUID> alreadyScattered = new ArrayList<UUID>();
    private static boolean scatDone = false;
    public static boolean started = false;
    public static boolean ended = false;
    private ArrayList<Player> ffa = new ArrayList<Player>();
    public static int ffaScattered = 0;
    public static int teamsScattered = 0;
    private static HashMap<UUID, Location> teamLocations = new HashMap<UUID, Location>();
    private static ArrayList<Location> ffaLocations = new ArrayList<Location>();
    public static HashMap<UUID, Integer> offlineDuringScat = new HashMap<UUID, Integer>();
    public static int numShrinks = 0;
    private TeamManager tm = new TeamManager();
    private Join join = new Join();

    public void onStart()
    {
        for(Player p : Main.online.getOnlinePlayers())
        {
            p.setHealth(p.getHealth() + (20.0 - p.getHealth()));
            p.setFoodLevel(20);
            p.setLevel(0);
            p.setExp(0);

            if(p != null  && !HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
            {
                World world = Bukkit.getWorld("world");

                if(PracticeArena.playersInArena.contains(p.getUniqueId()))
                {
                    ArenaKills.arenaKills.remove(p.getUniqueId());
                    PracticeArena.playersInArena.remove(p.getUniqueId());
                    join.clearInventory(p);
                    p.teleport(world.getSpawnLocation());
                    p.sendMessage(ChatColor.RED + "You have left the arena.");
                }
                else
                {
                    join.clearInventory(p);
                }
            }
        }

    	Bukkit.broadcastMessage(UHCprefix + ChatColor.AQUA + " Scatter is starting, DO NOT relog.");
    	
    	if(ConfigInventory.borderSize == 1500)
    	{
    		numShrinks = 6;
    	}
    	else if(ConfigInventory.borderSize == 1000)
    	{
    		numShrinks = 5;
    	}
    	else if(ConfigInventory.borderSize == 500)
    	{
    		numShrinks = 4;
    	}
    	else if(ConfigInventory.borderSize == 500)
    	{
    		numShrinks = 4;
    	}
    	else if(ConfigInventory.borderSize == 250)
    	{
    		numShrinks = 3;
    	}
    	else if(ConfigInventory.borderSize == 100)
    	{
    		numShrinks = 2;
    	}
    	else if(ConfigInventory.borderSize == 50)
    	{
    		numShrinks = 1;
    	}

        getLocations();
    }

    public void getLocations()
    {
        World world = Bukkit.getWorld("uhc_world");
        Bukkit.broadcastMessage(UHCprefix + ChatColor.DARK_AQUA + " Loading scatter locations...");

        if(ConfigInventory.teamSize == 1)
        {
            for(Player p : Main.online.getOnlinePlayers())
            {
                if(!HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
                {
                    if(!ffa.contains(p))
                    {
                        ffa.add(p);
                    }
                }
            }

            new BukkitRunnable()
            {
                int randomX, randomZ;
                Location teleloc, checkloc;
                int index = 0;

                public void run()
                {
                    randomX = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;
                    randomZ = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;

                    if(index == ffa.size())
                    {
                        potionEffects();
                        cancel();
                    }
                    else
                    {
                        teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
                        checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
                        Block block = checkloc.getBlock();

                        while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
                        {
                            randomX = new Random().nextInt(ConfigInventory.borderSize - 1) - ConfigInventory.borderSize;
                            randomZ = new Random().nextInt(ConfigInventory.borderSize - 1) - ConfigInventory.borderSize;

                            teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
                            checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
                            block = checkloc.getBlock();
                        }

                        ffaLocations.add(teleloc);
                        world.getChunkAt(teleloc).load(true);
                        world.getChunkAt(randomX - 16, randomZ - 16).load(true);
                        world.getChunkAt(randomX + 16, randomZ + 16).load(true);
                    }

                    index++;
                }

            }.runTaskTimer(plugin, 0, 10);
        }
        else
        {
            for(Player p : Main.online.getOnlinePlayers())
            {
                if(!HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()) && !PlayerKills.spectator.contains(p.getUniqueId()))
                {
                    if(!tm.findTeam(p))
                    {
                        tm.lateScatterCreateTeam(p);
                    }
                }
            }

            ArrayList<UUID> tempKeys = new ArrayList<UUID>(TeamManager.teams.keySet());

            new BukkitRunnable()
            {
                int randomX;
                int randomZ;
                int index = 0;

                public void run()
                {
                    randomX = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;
                    randomZ = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;

                    if(index == TeamManager.teams.size())
                    {
                        potionEffects();
                        cancel();
                    }
                    else
                    {
                        Location teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
                        Location checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
                        Block block = checkloc.getBlock();

                        while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
                        {
                            randomX = new Random().nextInt(ConfigInventory.borderSize - 1) - ConfigInventory.borderSize;
                            randomZ = new Random().nextInt(ConfigInventory.borderSize - 1) - ConfigInventory.borderSize;

                            teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
                            checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
                            block = checkloc.getBlock();
                        }

                        teamLocations.put(tempKeys.get(index), teleloc);
                        world.getChunkAt(teleloc).load(true);
                        world.getChunkAt(randomX - 16, randomZ - 16).load(true);
                        world.getChunkAt(randomX + 16, randomZ + 16).load(true);
                    }

                    index++;
                }

            }.runTaskTimer(plugin, 0, 10);
        }
    }

    public void potionEffects()
    {
        // Give potion effects to players during scatter

        if(ConfigInventory.teamSize == 1)
        {
            for(Player p : Main.online.getOnlinePlayers())
            {
                if(!HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
                {
                    allPlayers.add(p.getUniqueId());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 200), true);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 200), true);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 200), true);
                    p.setVelocity(new Vector().zero());
                }
            }
        }
        else
        {
            for(Player p : Main.online.getOnlinePlayers())
            {
                if(!HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
                {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 200), true);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 200), true);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 200), true);
                    p.setVelocity(new Vector().zero());
                }
            }

            for(UUID k : TeamManager.teams.keySet())
            {
                allPlayers.add(k);

                if(!TeamManager.teams.get(k).isEmpty())
                {
                    for(int i = 0; i < TeamManager.teams.get(k).size(); i++)
                    {
                        allPlayers.add(TeamManager.teams.get(k).get(i));
                    }
                }
            }
        }

        //Delay the Scatter

        new BukkitRunnable()
        {
            int seconds = 10;

            public void run()
            {
                if(seconds == 10)
                {
                    if (!ConfigInventory.horses)
                    {
                        horse.killCurrentHorses();
                    }
                }
                else if(seconds == 5)
                {
                    readRules();
                }
                else if(seconds == 0)
                {
                    telePlayer();
                    cancel();
                }

                seconds--;
            }
        }.runTaskTimer(plugin, 0, 20);
    }
    
    public void readRules()
    {
        ArrayList<String> allRules = new ArrayList<String>();

        allRules.add("\n" + ChatColor.LIGHT_PURPLE + "Rule #1: " + ChatColor.AQUA + "Hacked clients, Xray/Cavefinder, AutoClickers, or anything that gives an unfair advantage are not allowed.");
        allRules.add(ChatColor.LIGHT_PURPLE + "Rule #2: " + ChatColor.AQUA + "F5 abuse is allowed.");
        allRules.add(ChatColor.LIGHT_PURPLE + "Rule #3: " + ChatColor.AQUA + "Threatening players, trash talk, or any disrespectful behavior is prohibited.");
        allRules.add(ChatColor.LIGHT_PURPLE + "Rule #4: " + ChatColor.AQUA + "Intentionally dying to benefit another player is prohibited.");
        allRules.add(ChatColor.LIGHT_PURPLE + "Rule #5: " + ChatColor.AQUA + "Abusing Helpop, by sending unnecessary messages, is prohibited.");
        allRules.add(ChatColor.LIGHT_PURPLE + "Rule #6: " + ChatColor.AQUA + "Targeting players is not allowed.");
        allRules.add(ChatColor.LIGHT_PURPLE + "Rule #7: " + ChatColor.AQUA + "Bug abuse is not allowed, if you find a bug you must report it using /bugreport.");
        allRules.add(ChatColor.LIGHT_PURPLE + "Rule #8: " + ChatColor.AQUA + "Calling players a cheater is not allowed, you are encouraged to use /report if you believe a player is cheating.");
        allRules.add(ChatColor.LIGHT_PURPLE + "Rule #9: " + ChatColor.AQUA + "Trucing, especially in FFA, is not allowed.");
        allRules.add(ChatColor.LIGHT_PURPLE + "Rule #10: " + ChatColor.AQUA + "Cross-teaming is not allowed.");
        allRules.add("\n" + ChatColor.RED + ChatColor.BOLD + "NOTE " + ChatColor.RED + "if you are caught not following these rules, you will be punished.");

        //Rules
        new BukkitRunnable()
        {
            int rules = 0;

            public void run()
            {
                if(rules == allRules.size() && scatDone)
                {
                    for(Player p : Main.online.getOnlinePlayers())
                    {
                        if(!HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
                        {
                            p.removePotionEffect(PotionEffectType.BLINDNESS);
                            p.removePotionEffect(PotionEffectType.SLOW);
                            p.removePotionEffect(PotionEffectType.JUMP);
                            p.setVelocity(new Vector());
                        }
                    }

                    if(ScenariosInventory.superheroes && ConfigInventory.teamSize > 1)
                    {
                        SuperheroesCMD cmd = new SuperheroesCMD();

                        for(Player p : Main.online.getOnlinePlayers())
                        {
                            if(!HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()) && !PlayerKills.spectator.contains(p.getUniqueId()))
                            {
                                cmd.setSuperPower(p.getDisplayName(), "assign");
                            }
                        }
                    }
                    else
                    {
                        ScenariosInventory.superheroes = false;

                        for(ItemStack item : ScenariosInventory.enabledScenarios)
                        {
                            if(item.hasItemMeta())
                            {
                                if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "SuperHeroes"))
                                {
                                    ScenariosInventory.enabledScenarios.remove(item);
                                    break;
                                }
                            }
                        }
                    }

                    Bukkit.broadcastMessage(UHCprefix + ChatColor.GREEN + " The UHC has started, good luck! Use /helpop if you need help. You may relog now.");
                    
                    for(int i = 0; i < allPlayers.size(); i++)
                    {
                        if(Bukkit.getPlayer(allPlayers.get(i)) != null)
                        {
                            Bukkit.getPlayer(allPlayers.get(i)).getInventory().setItem(0, new ItemStack(Material.COOKED_BEEF, ConfigInventory.sFood, (byte) 0));
                        }
                    }

                    started = true;
                    Commands.scatter = false;
                    scatDone = false;
                    time.setTime();
                    cancel();
                }
                else
                {
                    if(allRules.get(rules) != null)
                    {
                        Bukkit.broadcastMessage(allRules.get(rules));
                        rules++;
                    }
                }
            }

        }.runTaskTimer(plugin, 0 ,60);
    }

    public void telePlayer()
    {	
    	World world = Bukkit.getWorld("uhc_world");
    	
        if(ConfigInventory.teamSize == 1)
        {
            new BukkitRunnable()
            {
                int index = 0;

                public void run()
                {
                    if(index == ffa.size())
                    {
                        kills.initializeKills(allPlayers);
                        scatDone = true;
                        cancel();
                    }
                    else
                    {
                        if(Bukkit.getPlayer(ffa.get(index).getUniqueId()) == null)
                        {
                            allPlayers.remove(ffa.get(index).getUniqueId());
                        }
                        else
                        {
                            world.getChunkAt(ffaLocations.get(index)).load(true);
                            ffa.get(index).teleport(ffaLocations.get(index));
                            alreadyScattered.add(ffa.get(index).getUniqueId());
                            ffa.get(index).setVelocity(new Vector().zero());
                            ffaScattered++;
                        }
                    }

                    index++;
                }

            }.runTaskTimer(plugin, 0, 40);
        }
        else
        {
            ArrayList<UUID> tempKeys = new ArrayList<UUID>(TeamManager.teams.keySet());

            new BukkitRunnable()
            {
                int index = 0;

                public void run()
                {
                    if(index == TeamManager.teams.size())
                    {
                        kills.initializeKills(allPlayers);
                        scatDone = true;
                        cancel();
                    }
                    else
                    {
                        Player owner = Bukkit.getPlayer(tempKeys.get(index));

                        if(owner == null)
                        {
                            offlineDuringScat.put(tempKeys.get(index), 0);
                        }
                        else
                        {
                            owner.teleport(teamLocations.get(tempKeys.get(index)));
                            alreadyScattered.add(owner.getUniqueId());
                            world.getChunkAt(teamLocations.get(tempKeys.get(index))).load(true);
                        }

                        for(int i = 0; i < TeamManager.teams.get(tempKeys.get(index)).size(); i++)
                        {
                            Player teammate = Bukkit.getPlayer(TeamManager.teams.get(tempKeys.get(index)).get(i));

                            if(teammate == null)
                            {
                                offlineDuringScat.put(TeamManager.teams.get(tempKeys.get(index)).get(i), 0);
                            }
                            else
                            {
                                teammate.teleport(teamLocations.get(tempKeys.get(index)));
                                world.getChunkAt(teamLocations.get(tempKeys.get(index))).load(true);
                                alreadyScattered.add(teammate.getUniqueId());
                            }
                        }

                        teamsScattered++;
                    }

                    index++;
                }

            }.runTaskTimer(plugin, 0, 40);
        }
    }

    public void lateScatterFFA(Player p)
    {
        World world = Bukkit.getWorld("uhc_world");
        int randomX;
        int randomZ;

        randomX = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;
        randomZ = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;

        Location teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
        Location checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
        Block block = checkloc.getBlock();

        if(!allPlayers.contains(p.getUniqueId()))
        {
            allPlayers.add(p.getUniqueId());
        }
        
        while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
        {
            randomX = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;
            randomZ = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;

            teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
            checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
            block = checkloc.getBlock();
        }
        
        p.teleport(teleloc);
        world.getChunkAt(randomX, randomZ).load(true);

        p.removePotionEffect(PotionEffectType.BLINDNESS);
        p.removePotionEffect(PotionEffectType.SLOW);
        p.removePotionEffect(PotionEffectType.JUMP);
        p.setVelocity(new Vector());

        p.getInventory().setItem(0, new ItemStack(Material.COOKED_BEEF, ConfigInventory.sFood, (byte) 0));
        p.sendMessage(UHCprefix + ChatColor.GREEN + " You have been late scattered! Use /helpop if you need help.");
    }

    public void lateScatterTeams(Player p)
    {
        SuperheroesCMD cmd = new SuperheroesCMD();
        TeamManager t = new TeamManager();

        if(t.findTeam(p))
        {
            p.teleport(teamLocations.get(t.getCaptain(p)));
        }
        else
        {
            t.lateScatterCreateTeam(p);

            World world = Bukkit.getWorld("uhc_world");
            int randomX;
            int randomZ;

            randomX = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;
            randomZ = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;

            Location teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
            Location checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
            Block block = checkloc.getBlock();

            if(!allPlayers.contains(p.getUniqueId()))
            {
                allPlayers.add(p.getUniqueId());
            }

            while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
            {
                randomX = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;
                randomZ = new Random().nextInt(ConfigInventory.borderSize + ConfigInventory.borderSize) - ConfigInventory.borderSize;

                teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 2, randomZ);
                checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
                block = checkloc.getBlock();
            }

            world.loadChunk(randomX, randomZ);

            p.teleport(teleloc);

            p.removePotionEffect(PotionEffectType.BLINDNESS);
            p.removePotionEffect(PotionEffectType.SLOW);
            p.removePotionEffect(PotionEffectType.JUMP);
            p.setVelocity(new Vector());

            p.getInventory().setItem(0, new ItemStack(Material.COOKED_BEEF, ConfigInventory.sFood, (byte) 0));
            p.sendMessage(UHCprefix + ChatColor.GREEN + " You have been late scattered! Use /helpop if you need help.");
        }

        if(ScenariosInventory.superheroes)
        {
            if(cmd.getSuperPower(p.getDisplayName()) == null)
            {
                cmd.setSuperPower(p.getDisplayName(), "assign");
            }
            else
            {
                cmd.InitializeSuperPower(p.getDisplayName(), cmd.getSuperPower(p.getDisplayName()));
            }
        }
    }
}