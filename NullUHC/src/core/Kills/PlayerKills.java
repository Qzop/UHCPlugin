package core.Kills;

import core.Chat.ChatEvent;
import core.Config.ConfigInventory;
import core.GoldenHead.GoldenHead;
import core.Scatter.Scatter;
import core.Scenarios.DoScenario;
import core.Scenarios.Timebomb;
import core.ScenariosInventory.ScenariosInventory;
import core.Teams.TeamManager;
import core.mainPackage.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.RecipesArmor;
import net.minecraft.server.v1_7_R4.TileEntitySkull;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.properties.Property;
import net.minecraft.util.org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Skull;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerKills implements Listener
{
    public static HashMap<UUID, Integer> numKills = new HashMap<UUID, Integer>();
    private Spectator spec = new Spectator();
    public static HashMap<UUID, Location> deathLocations = new HashMap<UUID, Location>();
    public static ArrayList<UUID> spectator = new ArrayList<UUID>();
    public static HashMap<UUID, ArrayList<ItemStack>> inventory = new HashMap<UUID, ArrayList<ItemStack>>();
    public static HashMap<UUID, ArrayList<ItemStack>> armorinv = new HashMap<UUID, ArrayList<ItemStack>>();

    Main plugin = Main.getPlugin(Main.class);

    public void initializeKills(ArrayList<UUID> players)
    {
        for (UUID player : players)
        {
            numKills.put(player, 0);
        }
    }

    public void latePlayer(Player p)
    {
        numKills.put(p.getUniqueId(), 0);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
        Player p = e.getEntity();

        ArrayList<Material> helmets = new ArrayList<Material>();
        ArrayList<Material> chestplates = new ArrayList<Material>();
        ArrayList<Material> leggings = new ArrayList<Material>();
        ArrayList<Material> boots = new ArrayList<Material>();

        helmets.add(Material.DIAMOND_HELMET);
        helmets.add(Material.IRON_HELMET);
        helmets.add(Material.GOLD_HELMET);
        helmets.add(Material.CHAINMAIL_HELMET);
        helmets.add(Material.LEATHER_HELMET);

        chestplates.add(Material.DIAMOND_CHESTPLATE);
        chestplates.add(Material.IRON_CHESTPLATE);
        chestplates.add(Material.GOLD_CHESTPLATE);
        chestplates.add(Material.CHAINMAIL_CHESTPLATE);
        chestplates.add(Material.LEATHER_CHESTPLATE);

        leggings.add(Material.DIAMOND_LEGGINGS);
        leggings.add(Material.IRON_LEGGINGS);
        leggings.add(Material.GOLD_LEGGINGS);
        leggings.add(Material.CHAINMAIL_LEGGINGS);
        leggings.add(Material.LEATHER_LEGGINGS);

        boots.add(Material.DIAMOND_BOOTS);
        boots.add(Material.IRON_BOOTS);
        boots.add(Material.GOLD_BOOTS);
        boots.add(Material.CHAINMAIL_BOOTS);
        boots.add(Material.LEATHER_BOOTS);

        if(Scatter.started)
        {
            if(p.getWorld().getName().equals("uhc_world"))
            {
                ArrayList<ItemStack> items = new ArrayList<ItemStack>();
                ArrayList<ItemStack> armor = new ArrayList<ItemStack>();

                for(int i = 0; i < e.getDrops().size(); i++)
                {
                    if(e.getDrops().get(i) != null && e.getDrops().get(i).getType() != Material.AIR)
                    {
                        if(i >= e.getDrops().size() - 4)
                        {
                            if(boots.contains(e.getDrops().get(i).getType()))
                            {
                                armor.add(e.getDrops().get(i));
                            }
                            else if(leggings.contains(e.getDrops().get(i).getType()))
                            {
                                armor.add(e.getDrops().get(i));
                            }
                            else if(chestplates.contains(e.getDrops().get(i).getType()))
                            {
                                armor.add(e.getDrops().get(i));
                            }
                            else if(helmets.contains(e.getDrops().get(i).getType()))
                            {
                                armor.add(e.getDrops().get(i));
                            }
                            else
                            {
                                items.add(e.getDrops().get(i));
                            }
                        }
                        else
                        {
                            items.add(e.getDrops().get(i));
                        }
                    }
                }

                inventory.put(p.getUniqueId(), items);
                armorinv.put(p.getUniqueId(), armor);

                if(!ScenariosInventory.timebomb)
                {
                    for(int i = 0; i < e.getDrops().size(); i++)
                    {
                        p.getWorld().dropItemNaturally(p.getLocation(), e.getDrops().get(i));
                    }

                    setUpPlayerHead(p);
                    e.getDrops().clear();
                }
                else
                {
                    Player killer = p.getKiller();
                    Location loc = new Location(p.getWorld(), p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
                    Location dubLoc = new Location(loc.getWorld(), (loc.getBlockX() + 1), loc.getBlockY(), loc.getBlockZ()-1);  //tbh i have no idea why the z needs minus 1 but it fixed my issue
                    Block block =  loc.getBlock();
                    block = block.getRelative(BlockFace.NORTH);
                    block.setType(Material.CHEST);
                    Chest chest = (Chest) block.getState();
                    Timebomb.timebombLocation.add("" + loc.getBlockX() + loc.getBlockY() + (loc.getBlockZ()-1));

                    int numItems = 0;

                    for (ItemStack item : e.getDrops())
                    {
                        if (item == null || item.getType() == Material.AIR)
                        {
                            continue;
                        }

                        Block dubBlock = dubLoc.getBlock();

                        if(numItems == 27)
                        {
                            dubBlock.setType(Material.CHEST);
                            Timebomb.timebombLocation.add("" + dubLoc.getBlockX() + dubLoc.getBlockY() + dubLoc.getBlockZ());
                        }

                        numItems++;

                        if(numItems > 27)
                        {
                            Chest dubChest = (Chest) dubLoc.getBlock().getState();
                            dubChest.getInventory().addItem(item);
                        }
                        else
                        {
                            chest.getInventory().addItem(item);
                        }
                    }

                    chest.getInventory().addItem(GoldenHead.head);

                    e.getDrops().clear();
                    double xAdder = 0.5;

                    if(numItems > 27) xAdder = 1;

                    new BukkitRunnable()
                    {
                        int time = 30;

                        public void run()
                        {
                            if (time == 0)
                            {
                                Bukkit.broadcastMessage(Scatter.UHCprefix + " " + ChatColor.YELLOW + p.getName() + "'s §ccorpse has exploded!");

                                loc.getBlock().setType(Material.AIR);

                                if(dubLoc.getBlock().getType().equals(Material.CHEST))
                                {
                                    dubLoc.getBlock().setType(Material.AIR);
                                    dubLoc.getWorld().strikeLightning(dubLoc);
                                }

                                loc.getWorld().createExplosion(loc.getBlockX() + 0.5, loc.getBlockY() + 0.5, loc.getBlockZ() + 0.5, 10, false, true);
                                loc.getWorld().strikeLightning(loc);

                                for(int i = 0; i < Timebomb.timebombLocation.size(); i++)
                                {
                                    if(Timebomb.timebombLocation.get(i).equals("" + loc.getBlockX() + loc.getBlockY() + loc.getBlockZ()) || Timebomb.timebombLocation.get(i).equals("" + (loc.getBlockX() - 1) + loc.getBlockY() + loc.getBlockZ()))
                                    {
                                        Timebomb.timebombLocation.remove(i);
                                    }
                                }
                                cancel();
                                return;
                            }
                            else if (time == 30)
                            {
                               Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " seconds.");
                            }
                            else if (time == 20)
                            {
                                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " seconds.");
                            }
                            else if (time == 15)
                            {
                                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " seconds.");
                            }
                            else if (time == 10)
                            {
                                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " seconds.");
                            }
                            else if(time <= 5 && time > 0)
                            {
                                if(time != 1)
                                {
                                    Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " seconds.");
                                }
                                else
                                {
                                    Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " second.");
                                }
                            }

                            time--;
                        }
                    }.runTaskTimer(plugin, 0, 20);
                }

                if(ConfigInventory.teamSize > 1)
                {
                    TeamManager tm = new TeamManager();
                    UUID cap = tm.getCaptain(p);
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

                PlayerKills.deathLocations.put(p.getUniqueId(), p.getLocation());

                if(p.getKiller() != null)
                {
                    UUID uuid = p.getKiller().getUniqueId();
                    p.getKiller().setExp(p.getKiller().getExp() + (float) (p.getExp() / 2));

                    if(PlayerKills.numKills.containsKey(uuid))
                    {
                        PlayerKills.numKills.put(uuid, PlayerKills.numKills.get(uuid) + 1);
                    }

                    if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "was slain by " + ChatColor.YELLOW + p.getKiller().getDisplayName() + ChatColor.GRAY + "[" + numKills.get(p.getKiller().getUniqueId()) + ChatColor.GRAY + "]");
                    }
                    else if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.PROJECTILE)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "was shot to death by " + ChatColor.YELLOW + p.getKiller().getDisplayName() + ChatColor.GRAY + "[" + numKills.get(p.getKiller().getUniqueId()) + ChatColor.GRAY + "]");
                    }
                }
                else
                {
                    if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FIRE || p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.LAVA)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "was burnt to death!");
                    }
                    else if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "got blown the fuck up!");
                    }
                    else if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.DROWNING)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "drank too much water!");
                    }
                    else if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "fell to their death!");
                    }
                    else if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "was crushed to death!");
                    }
                    else if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.LIGHTNING)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "was electrocuted to death!");
                    }
                    else if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.POISON)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "was poisoned to death!");
                    }
                    else if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.SUICIDE)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "committed the unthinkable!");
                    }
                    else if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.STARVATION)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "starved to death!");
                    }
                    else if(p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.SUFFOCATION)
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "suffocated to death!");
                    }
                    else
                    {
                        e.setDeathMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + "[" + ChatColor.WHITE + numKills.get(p.getUniqueId()) + ChatColor.GRAY + "] " + ChatColor.RED + "died!");
                    }
                }

                spec.setSpectator(p);
            }
        }
    }

    public void setUpPlayerHead(Player p)
    {
        EntityPlayer player = ((CraftPlayer) p).getHandle();
        GameProfile profile = player.getProfile();
        Property property = profile.getProperties().get("textures").iterator().next();

        String texture = property.getValue();

        ItemStack pHead = getSkull(texture);
        SkullMeta pHeadMeta = (SkullMeta) pHead.getItemMeta();
        pHeadMeta.setOwner(p.getName());
        pHeadMeta.setDisplayName(ChatColor.GOLD + p.getDisplayName() + "'s Head");
        pHead.setItemMeta(pHeadMeta);

        Block b = p.getLocation().getBlock();
        b.setType(Material.FENCE);

        Block skull = b.getWorld().getBlockAt(b.getLocation().getBlockX(), b.getLocation().getBlockY() + 1, b.getLocation().getBlockZ());
        skull.setType(Material.SKULL);
        Skull skulldata = (Skull) skull.getState();
        skulldata.setSkullType(SkullType.PLAYER);

        TileEntitySkull skulltile = (TileEntitySkull) ((CraftWorld) skull.getWorld()).getHandle().getTileEntity(skull.getX(), skull.getY(), skull.getZ());
        skulltile.setGameProfile(profile);
        skull.getDrops().clear();
        skull.getDrops().add(pHead);
        skull.getState().update(true);
    }

    public ItemStack getSkull(String url)
    {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

        if (url == null || url.isEmpty())
        {
            return skull;
        }

        ItemMeta skullMeta = skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;

        try
        {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        }
        catch (NoSuchFieldException | SecurityException e)
        {
            e.printStackTrace();
        }

        profileField.setAccessible(true);

        try
        {
            profileField.set(skullMeta, profile);
        }
        catch (IllegalArgumentException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        skull.setItemMeta(skullMeta);
        return skull;
    }
}
