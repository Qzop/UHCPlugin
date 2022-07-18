package core.Events;

import com.lunarclient.bukkitapi.LunarClientAPI;
import core.Config.ConfigInventory;
import core.HostsMods.HostModsItems;
import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Kills.Spectator;
import core.Scatter.Scatter;
import core.Scoreboard.*;
import core.mainPackage.Commands;
import core.mainPackage.LobbyItems;
import core.mainPackage.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Join implements Listener
{
    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        Game game = new Game();
        Scatter scat = new Scatter();
        Lobby lob = new Lobby();
        PlayerKills kills = new PlayerKills();
        ScoreboardTeams teams = new ScoreboardTeams();

        Main.online.addPlayer(p);

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                if(LunarClientAPI.getInstance().isRunningLunarClient(e.getPlayer().getUniqueId()))
                {
                    e.getPlayer().sendMessage("You are on lunar!");
                }
                else
                {
                    e.getPlayer().sendMessage("You are not on lunar :(...");

                }
            }
        }, 40L);

        if(p.hasPermission("chat.owner"))
        {
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.DARK_RED + ChatColor.BOLD +  " " + p.getDisplayName());
        }
        else if(p.hasPermission("chat.dev"))
        {
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.GOLD + ChatColor.BOLD + " " + p.getDisplayName());
        }
        else if(p.hasPermission("chat.admin"))
        {
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.RED + " " + p.getDisplayName());
        }
        else if(p.hasPermission("chat.srmod"))
        {
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.DARK_BLUE + " " + p.getDisplayName());
        }
        else if(p.hasPermission("chat.mod"))
        {
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.DARK_GREEN + " " + p.getDisplayName());
        }
        else if(p.hasPermission("chat.trial"))
        {
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.GREEN + " " + p.getDisplayName());
        }
        else
        {
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "]" + ChatColor.WHITE + " " + p.getDisplayName());
        }

        if(!Scatter.started && !Commands.scatter)
        {
            p.sendMessage(ChatColor.GRAY + "§m------------------------------------");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.WHITE + "Welcome to " + ChatColor.YELLOW + "NullUHC" + ChatColor.WHITE + "!");
            p.sendMessage(" ");
            p.sendMessage("\u272A" + ChatColor.YELLOW + " Discord: " + ChatColor.WHITE + "https://discord.gg/TBPbcJpHaX");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GRAY + "§m------------------------------------");

            World world = Bukkit.getWorld("world");
            removeAllPotions(p);
            p.teleport(world.getSpawnLocation());

            for(Player players : Main.online.getOnlinePlayers())
            {
                if(!HostsMods.hosts.contains(p.getUniqueId()) && !HostsMods.mods.contains(p.getUniqueId()))
                {
                    players.showPlayer(p);
                }

                if(!HostsMods.hosts.contains(players.getUniqueId()) && !HostsMods.mods.contains(players.getUniqueId()))
                {
                    p.showPlayer(players);
                }
            }

            if(p.hasPermission("uhc.mod") || p.hasPermission("uhc.host"))
            {
                HostModsItems items = new HostModsItems();
                items.staffLobbyItems(p);
            }
            else
            {
                LobbyItems lobbyitems = new LobbyItems();
                lobbyitems.lobbyItems(p);
            }

            if(Main.loading)
            {
                p.sendMessage(Scatter.UHCprefix + ChatColor.DARK_AQUA + " Chunks are loading! Please wait until chunks are finished loading.");
            }

            lob.setLobby(p);
        }
        else if(Commands.scatter)
        {
            p.kickPlayer(ChatColor.RED + "Scatter has started, you cannot join now.");
        }
        else if(Scatter.started)
        {
            if(PlayerKills.spectator.contains(p.getUniqueId()))
            {
                if(p.hasPermission("death.bypass"))
                {
                    World world = Bukkit.getWorld("world");
                    p.teleport(world.getSpawnLocation());
                }
                else
                {
                    p.kickPlayer(ChatColor.RED + "You died, unlucky :(");
                }
            }
            else
            {
                if(Scatter.allPlayers.contains(p.getUniqueId()))
                {
                    NPCEvent npc = new NPCEvent();

                    if(ConfigInventory.teamSize > 1)
                    {
                        game.setGameTeams(p);
                    }
                    else
                    {
                        game.setGameFFA(p);
                    }

                    if(NPCEvent.disconnected.containsKey(p.getUniqueId()))
                    {
                        npc.removeNPC(p);

                        if(Scatter.alreadyScattered.contains(p.getUniqueId()))
                        {
                            Scatter.alreadyScattered.remove(p.getUniqueId());
                            Scatter temp = new Scatter();

                            p.removePotionEffect(PotionEffectType.BLINDNESS);
                            p.removePotionEffect(PotionEffectType.SLOW);
                            p.removePotionEffect(PotionEffectType.JUMP);
                            p.getInventory().setItem(0, new ItemStack(Material.COOKED_BEEF, ConfigInventory.sFood, (byte) 0));
                        }
                    }

                    if(!PlayerKills.spectator.isEmpty())
                    {
                        for(int i = 0; i < PlayerKills.spectator.size(); i++)
                        {
                            p.hidePlayer(Bukkit.getPlayer(PlayerKills.spectator.get(i)));
                        }
                    }

                    if(!HostsMods.hosts.isEmpty())
                    {
                        p.hidePlayer(Bukkit.getPlayer(HostsMods.hosts.get(0)));
                    }

                    if(!HostsMods.mods.isEmpty())
                    {
                        for(int i = 0; i < HostsMods.mods.size(); i++)
                        {
                            p.hidePlayer(Bukkit.getPlayer(HostsMods.hosts.get(i)));
                        }
                    }
                }
                else if(HostsMods.hosts.contains(p.getUniqueId()) || HostsMods.mods.contains(p.getUniqueId()))
                {
                    for(Player player : Main.online.getOnlinePlayers())
                    {
                        player.hidePlayer(p);
                    }
                }
                else if(NPCEvent.disqualified.contains(p.getUniqueId()))
                {
                    p.kickPlayer(ChatColor.RED + "Sorry, you have been disqualified for being disconnected for more than 10 minutes.");
                }
                else
                {
                    if(Time.minutes < ConfigInventory.latescatter)
                    {
                        removeAllPotions(p);

                        if(ConfigInventory.teamSize > 1)
                        {
                            scat.lateScatterTeams(p);
                            game.setGameTeams(p);
                            kills.latePlayer(p);
                        }
                        else
                        {
                            scat.lateScatterFFA(p);
                            game.setGameFFA(p);
                            kills.latePlayer(p);
                        }

                        if(!PlayerKills.spectator.isEmpty())
                        {
                            for(int i = 0; i < PlayerKills.spectator.size(); i++)
                            {
                                p.hidePlayer(Bukkit.getPlayer(PlayerKills.spectator.get(i)));
                            }
                        }

                        if(!HostsMods.hosts.isEmpty())
                        {
                            p.hidePlayer(Bukkit.getPlayer(HostsMods.hosts.get(0)));
                        }

                        for(int i = 0; i < HostsMods.mods.size(); i++)
                        {
                            p.hidePlayer(Bukkit.getPlayer(HostsMods.hosts.get(i)));
                        }
                    }
                    else
                    {
                        if(p.hasPermission("latescatter.bypass"))
                        {
                            p.teleport(Bukkit.getWorld("world").getSpawnLocation());
                        }
                        else
                        {
                            Spectator spectator = new Spectator();
                            spectator.setSpectator(p);
                        }
                    }
                }
            }
        }
    }

    private void removeAllPotions(Player p)
    {
        p.removePotionEffect(PotionEffectType.BLINDNESS);
        p.removePotionEffect(PotionEffectType.JUMP);
        p.removePotionEffect(PotionEffectType.SPEED);
        p.removePotionEffect(PotionEffectType.SLOW);
        p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        p.removePotionEffect(PotionEffectType.ABSORPTION);
        p.removePotionEffect(PotionEffectType.CONFUSION);
        p.removePotionEffect(PotionEffectType.FAST_DIGGING);
        p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        p.removePotionEffect(PotionEffectType.HARM);
        p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
        p.removePotionEffect(PotionEffectType.HEAL);
        p.removePotionEffect(PotionEffectType.INVISIBILITY);
        p.removePotionEffect(PotionEffectType.POISON);
        p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        p.removePotionEffect(PotionEffectType.WATER_BREATHING);
        p.removePotionEffect(PotionEffectType.WEAKNESS);
        p.removePotionEffect(PotionEffectType.REGENERATION);
        p.removePotionEffect(PotionEffectType.HUNGER);
        p.removePotionEffect(PotionEffectType.WITHER);
        p.removePotionEffect(PotionEffectType.NIGHT_VISION);
        p.removePotionEffect(PotionEffectType.SATURATION);

        p.setHealth(p.getHealth() + (20.0 - p.getHealth()));
        p.setFoodLevel(20);
        p.setLevel(0);
        p.setExp(0);

        p.setGameMode(GameMode.SURVIVAL);
    }
}
