package core.Scenarios;

import core.Kills.PlayerKills;
import core.ScenariosInventory.ScenariosInventory;
import core.mainPackage.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Timebomb implements Listener
{
    public static List<String> timebombLocation= new ArrayList<String>();
    public static ArrayList<NPC> npcs = new ArrayList<NPC>();
    DoScenario doScen = new DoScenario();

    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onTimeBombBreak(BlockBreakEvent e)
    {
        if(e.getBlock().getType().equals(Material.CHEST))
        {
            Location loc = e.getBlock().getLocation();
            if(timebombLocation.contains("" + loc.getBlockX() + loc.getBlockY() + loc.getBlockZ()) && ScenariosInventory.timebomb)
            {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "You cannot destroy a bomb are you insane??");
            }
        }
    }
}
