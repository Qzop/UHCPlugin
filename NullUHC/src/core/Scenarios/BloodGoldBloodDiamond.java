package core.Scenarios;

import core.Scatter.Scatter;
import core.ScenariosInventory.ScenariosInventory;
import core.mainPackage.Commands;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BloodGoldBloodDiamond implements Listener
{
    @EventHandler
    public void onMine(BlockBreakEvent e)
    {
        if(Commands.scatter || Scatter.started)
        {
            Player p = e.getPlayer();

            if(e.getBlock().getType() == Material.GOLD_ORE)
            {
                if(ScenariosInventory.bloodgold)
                {
                    p.damage(1.0);
                }
            }

            if(e.getBlock().getType() == Material.DIAMOND_ORE)
            {
                if(ScenariosInventory.blooddiamond)
                {
                    p.damage(1.0);
                }
            }
        }
    }
}
