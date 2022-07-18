package core.Scenarios;

import core.ScenariosInventory.ScenariosInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Timber implements Listener
{
    DoScenario doScen = new DoScenario();

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event)
    {
        Block block = event.getBlock();
        ItemStack pickInHand = event.getPlayer().getInventory().getItemInHand();
        Player p = event.getPlayer();

        if(block.getType().equals(Material.LOG) && ScenariosInventory.timber && doScen.doScenario(p))
        {
            List<Integer> logsToBreakX=new ArrayList<Integer>();
            List<Integer> logsToBreakY=new ArrayList<Integer>();
            List<Integer> logsToBreakZ=new ArrayList<Integer>();

            Location origBlockLoc = block.getLocation();
            logsToBreakX.add(origBlockLoc.getBlockX());
            logsToBreakY.add(origBlockLoc.getBlockY());
            logsToBreakZ.add(origBlockLoc.getBlockZ());

            int loopBreaker = 1;

            for(int index = 0; ((index < logsToBreakX.size()) && loopBreaker <= 80) || loopBreaker == 1; index++)
            {
                Location currentBlokLoc =  new Location(origBlockLoc.getWorld(), logsToBreakX.get(index), logsToBreakY.get(index), logsToBreakZ.get(index));

                for(int x = -1; x <= 1; x++)
                {
                    for(int y = -1; y <= 1; y++)
                    {
                        for(int z = -1; z <= 1; z++)
                        {

                            Location testLocation = new Location(currentBlokLoc.getWorld(), currentBlokLoc.getBlockX() + x, currentBlokLoc.getBlockY() + y, currentBlokLoc.getBlockZ() + z);

                            if(testLocation.getBlock().getType().equals(Material.LOG))
                            {
                                //(!(logsToBreakX.contains(testLocation.getBlockX())) || !(logsToBreakY.contains(testLocation.getBlockY())) || !(logsToBreakZ.contains(testLocation.getBlockZ()))
                                logsToBreakX.add(testLocation.getBlockX());
                                logsToBreakY.add(testLocation.getBlockY());
                                logsToBreakZ.add(testLocation.getBlockZ());
                            }
                        }
                    }
                }

                if(currentBlokLoc.getBlock().getType().equals(Material.LOG))
                {
                    currentBlokLoc.getBlock().breakNaturally();
                    loopBreaker++;
                }
            }

        }
        if(block.getType().equals(Material.LOG_2) && ScenariosInventory.timber)
        {
            List<Integer> logsToBreakX=new ArrayList<Integer>();
            List<Integer> logsToBreakY=new ArrayList<Integer>();
            List<Integer> logsToBreakZ=new ArrayList<Integer>();

            Location origBlockLoc = block.getLocation();
            logsToBreakX.add(origBlockLoc.getBlockX());
            logsToBreakY.add(origBlockLoc.getBlockY());
            logsToBreakZ.add(origBlockLoc.getBlockZ());

            int loopBreaker = 1;

            for(int index = 0; ((index < logsToBreakX.size()) && loopBreaker < 120) || loopBreaker == 1; index++)
            {
                Location currentBlokLoc =  new Location(origBlockLoc.getWorld(), logsToBreakX.get(index), logsToBreakY.get(index), logsToBreakZ.get(index));

                for(int x = -1; x <= 1; x++)
                {
                    for(int y = -1; y <= 1; y++)
                    {
                        for(int z = -1; z <= 1; z++)
                        {

                            Location testLocation = new Location(currentBlokLoc.getWorld(), currentBlokLoc.getBlockX() + x, currentBlokLoc.getBlockY() + y, currentBlokLoc.getBlockZ() + z);

                            if(testLocation.getBlock().getType().equals(Material.LOG_2))
                            {
                                //(!(logsToBreakX.contains(testLocation.getBlockX())) || !(logsToBreakY.contains(testLocation.getBlockY())) || !(logsToBreakZ.contains(testLocation.getBlockZ()))
                                logsToBreakX.add(testLocation.getBlockX());
                                logsToBreakY.add(testLocation.getBlockY());
                                logsToBreakZ.add(testLocation.getBlockZ());
                            }
                        }
                    }
                }

                currentBlokLoc.getBlock().breakNaturally();
                loopBreaker++;
            }
        }
    }
}
