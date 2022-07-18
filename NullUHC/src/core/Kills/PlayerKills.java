package core.Kills;

import core.Scatter.Scatter;
import core.Scenarios.DoScenario;
import core.Scenarios.Timebomb;
import core.ScenariosInventory.ScenariosInventory;
import core.mainPackage.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_7_R4.EntityPlayer;
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
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

        if(Scatter.started)
        {
            if(p.getWorld().getName().equals("uhc_world"))
            {
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
                    PlayerKills kill = new PlayerKills();
                    EntityPlayer player = ((CraftPlayer) p).getHandle();
                    GameProfile profile = player.getProfile();
                    Property property = profile.getProperties().get("textures").iterator().next();
                    String texture = property.getValue();

                    ItemStack pHead = kill.getSkull(texture);
                    SkullMeta pHeadMeta = (SkullMeta) pHead.getItemMeta();
                    pHeadMeta.setOwner(p.getName());
                    pHeadMeta.setDisplayName(net.md_5.bungee.api.ChatColor.GOLD + p.getDisplayName() + "'s Head");
                    pHead.setItemMeta(pHeadMeta);

                    chest.getInventory().addItem(pHead);

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
                               killer.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " seconds.");
                            }
                            else if (time == 20)
                            {
                                killer.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " seconds.");
                            }
                            else if (time == 15)
                            {
                                killer.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " seconds.");
                            }
                            else if (time == 10)
                            {
                                killer.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " seconds.");
                            }
                            else if(time <= 5 && time > 0)
                            {
                                if(time != 1)
                                {
                                    killer.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " seconds.");
                                }
                                else
                                {
                                    killer.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "TimeBomb" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " " + p.getDisplayName() + "'s" + ChatColor.RED + " Chest will explode in " + ChatColor.AQUA + time + " second.");
                                }
                            }

                            time--;
                        }
                    }.runTaskTimer(plugin, 0, 20);
                }

                PlayerKills.deathLocations.put(p.getUniqueId(), p.getLocation());
                Scatter.allPlayers.remove(p.getUniqueId());

                if(p.getKiller() != null)
                {
                    UUID uuid = p.getKiller().getUniqueId();
                    p.getKiller().setExp(p.getKiller().getExp() + (float) (p.getExp() / 2));

                    if(PlayerKills.numKills.containsKey(uuid))
                    {
                        PlayerKills.numKills.put(uuid, PlayerKills.numKills.get(uuid) + 1);
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
