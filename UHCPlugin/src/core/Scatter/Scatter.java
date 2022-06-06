package core.Scatter;

import java.util.*;

import core.Config.ConfigInventory;
import core.ConfigVariables.BedRockBorder;
import core.ConfigVariables.Horses;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Scoreboard.Time;
import core.Teams.TeamManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
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
    public static String UHCprefix = ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "UHC" + ChatColor.GRAY + "]";
    Main plugin = Main.getPlugin(Main.class);
    public static ArrayList<UUID> offlineDuringScat = new ArrayList<UUID>();
    private static boolean scatDone = false;
    public static boolean started = false;
    public static boolean ended = false;
    private ArrayList<Player> ffa = new ArrayList<Player>();
    public static int ffaScattered = 0;
    public static int teamsScattered = 0;
    private static ArrayList<Location> teamLocations = new ArrayList<Location>();

    public void onStart()
    {
        // Temporary variables

        // Give potion effects to players during scatter
        for(Player p : Bukkit.getOnlinePlayers())
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

        //Delay the Scatter

        new BukkitRunnable()
        {
            int seconds = 10;

            public void run()
            {
                if(seconds == 10)
                {
                	bord.setUpBorder();
                	
                    if (!ConfigInventory.horses)
                    {
                        horse.killCurrentHorses();
                    }
                }
                else if(seconds == 0)
                {
                    onScat();
                    cancel();
                }

                seconds--;
            }
        }.runTaskTimer(plugin, 0, 20);

        for(Player p : Bukkit.getOnlinePlayers())
        {
            if(ConfigInventory.teamSize == 1)
            {
                // Make sure to add if and else statements for hosts and mods.

                if(!HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
                {
                    if(!ffa.contains(p))
                    {
                        ffa.add(p);
                    }
                }
            }

            p.sendMessage(UHCprefix + ChatColor.AQUA + " Scatter is starting, DO NOT relog.");
        }

        ArrayList<String> allRules = new ArrayList<String>();

        allRules.add("\n" + ChatColor.LIGHT_PURPLE + "Rule #1: " + ChatColor.AQUA + "Hacked clients, Xray/Cavefinder, AutoClickers, or anything that gives an unfair advantage are not allowed.");
        allRules.add(ChatColor.LIGHT_PURPLE + "Rule #2: " + ChatColor.AQUA + "F5 abuse and 'F3 + a' abuse are not allowed.");
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
                    for(Player p : Bukkit.getOnlinePlayers())
                    {
                        if(!HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
                        {
                            p.removePotionEffect(PotionEffectType.BLINDNESS);
                            p.removePotionEffect(PotionEffectType.SLOW);
                            p.removePotionEffect(PotionEffectType.JUMP);
                            p.setVelocity(new Vector());
                        }
                    }

                    Bukkit.broadcastMessage(UHCprefix + ChatColor.GREEN + " The UHC has started, good luck! Use /helpop if you need help. You may relog now.");
                    kills.initializeKills(allPlayers);
                    started = true;
                    time.setTime();
                    cancel();
                }
                else
                {
                    Bukkit.broadcastMessage(allRules.get(rules));
                    rules++;
                }
            }

        }.runTaskTimer(plugin, 0 ,60);
    }

    public void onScat()
    {
        ArrayList<Location> locations = new ArrayList<Location>();
        World world = Bukkit.getWorld("uhcworld");

        if(ConfigInventory.teamSize == 1)
        {
            new BukkitRunnable()
            {
                int randomX;
                int randomZ;
                int index = 0;

                public void run()
                {
                    randomX = new Random().nextInt(ConfigInventory.borderSize - 1);
                    randomZ = new Random().nextInt(ConfigInventory.borderSize - 1);

                    if(index == ffa.size())
                    {
                        telePlayer(locations);
                        cancel();
                    }
                    else
                    {
                        Location teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 3, randomZ);
                        Location checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
                        Block block = checkloc.getBlock();

                        while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
                        {
                            randomX = new Random().nextInt(ConfigInventory.borderSize - 1);
                            randomZ = new Random().nextInt(ConfigInventory.borderSize - 1);

                            teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 1, randomZ);
                            checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
                            block = checkloc.getBlock();
                        }

                        world.loadChunk(randomX, randomZ);
                        locations.add(teleloc);
                    }

                    index++;
                }

            }.runTaskTimer(plugin, 0, 1);
        }
        else if(ConfigInventory.teamSize > 1)
        {
            new BukkitRunnable()
            {
                int randomX;
                int randomZ;
                int index = 0;

                public void run()
                {
                    randomX = new Random().nextInt(ConfigInventory.borderSize - 1);
                    randomZ = new Random().nextInt(ConfigInventory.borderSize - 1);

                    if(index == TeamManager.teams.size())
                    {
                        teamLocations = locations;
                        telePlayer(locations);
                        cancel();
                    }
                    else
                    {
                        Location teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 3, randomZ);
                        Location checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
                        Block block = checkloc.getBlock();

                        while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
                        {
                            randomX = new Random().nextInt(ConfigInventory.borderSize - 1);
                            randomZ = new Random().nextInt(ConfigInventory.borderSize - 1);

                            teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 1, randomZ);
                            checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
                            block = checkloc.getBlock();
                        }

                        world.loadChunk(randomX, randomZ);
                        locations.add(teleloc);
                    }

                    index++;
                }

            }.runTaskTimer(plugin, 0, 20);
        }
    }

    public void telePlayer(ArrayList<Location> locations)
    {
        if(ConfigInventory.teamSize == 1)
        {
            new BukkitRunnable()
            {
                int index = 0;

                public void run()
                {
                    if(index == ffa.size())
                    {
                        Commands.scatter = false;
                        scatDone = true;
                        cancel();
                    }
                    else
                    {
                        ffa.get(index).teleport(locations.get(index));
                        ffaScattered++;
                    }

                    index++;
                }

            }.runTaskTimer(plugin, 0, 40);
        }
        else
        {
            new BukkitRunnable()
            {
                int index = 0;

                public void run()
                {
                    if(index == TeamManager.teams.size())
                    {
                        Commands.scatter = false;
                        scatDone = true;
                        cancel();
                    }
                    else
                    {
                        Player owner = Bukkit.getPlayer(TeamManager.keys.get(index));

                        if(owner == null)
                        {
                            offlineDuringScat.add(TeamManager.keys.get(index));
                        }
                        else
                        {
                            owner.teleport(locations.get(index));
                        }

                        for(int i = 0; i < TeamManager.teams.get(TeamManager.keys.get(index)).size(); i++)
                        {
                            Player teammate = Bukkit.getPlayer(TeamManager.teams.get(TeamManager.keys.get(index)).get(i));

                            if(teammate == null)
                            {
                                offlineDuringScat.add(TeamManager.teams.get(TeamManager.keys.get(index)).get(i));
                            }
                            else
                            {
                                teammate.teleport(locations.get(index));
                            }

                            teamsScattered++;
                        }
                    }

                    index++;
                }

            }.runTaskTimer(plugin, 0, 40);
        }
    }

    public void lateScatterFFA(Player p)
    {
        World world = Bukkit.getWorld("uhcworld");
        int randomX;
        int randomZ;

        randomX = new Random().nextInt(ConfigInventory.borderSize - 1);
        randomZ = new Random().nextInt(ConfigInventory.borderSize - 1);

        Location teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 3, randomZ);
        Location checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
        Block block = checkloc.getBlock();

        while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
        {
            randomX = new Random().nextInt(ConfigInventory.borderSize - 1);
            randomZ = new Random().nextInt(ConfigInventory.borderSize - 1);

            teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 1, randomZ);
            checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
            block = checkloc.getBlock();
        }

        world.loadChunk(randomX, randomZ);

        p.teleport(teleloc);

        p.sendMessage(UHCprefix + ChatColor.GREEN + "You have been late scattered! Use /helpop if you need help.");
    }

    public void lateScatterTeams(Player p)
    {
        TeamManager t = new TeamManager();

        if(t.findTeam(p))
        {
            p.teleport(teamLocations.get(t.getCaptain(p)));
        }
        else
        {
            t.lateScatterCreateTeam(p);

            World world = Bukkit.getWorld("uhcworld");
            int randomX;
            int randomZ;

            randomX = new Random().nextInt(ConfigInventory.borderSize - 1);
            randomZ = new Random().nextInt(ConfigInventory.borderSize - 1);

            Location teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 3, randomZ);
            Location checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
            Block block = checkloc.getBlock();

            while(block.getType() == Material.LAVA || block.getType() == Material.WATER || block.getType() == Material.STATIONARY_LAVA || block.getType() == Material.STATIONARY_WATER)
            {
                randomX = new Random().nextInt(ConfigInventory.borderSize - 1);
                randomZ = new Random().nextInt(ConfigInventory.borderSize - 1);

                teleloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) + 1, randomZ);
                checkloc = new Location(world, randomX, world.getHighestBlockYAt(randomX, randomZ) - 1, randomZ);
                block = checkloc.getBlock();
            }

            world.loadChunk(randomX, randomZ);

            p.teleport(teleloc);

            p.sendMessage(UHCprefix + ChatColor.GREEN + "You have been late scattered! Use /helpop if you need help.");
        }
    }
}