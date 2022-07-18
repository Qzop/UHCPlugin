package core.Scenarios;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class DoScenario implements Listener
{
    public boolean doScenario(Player p)
    {
        if(p.getWorld().getName().equals("uhc_world"))
        {
            if(p.getGameMode().equals(GameMode.SURVIVAL))
            {
                return true;
            }
        }

        return true;
    }
}
