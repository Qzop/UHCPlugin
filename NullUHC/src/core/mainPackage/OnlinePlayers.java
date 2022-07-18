package core.mainPackage;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class OnlinePlayers implements Listener
{
    private ArrayList<Player> onlinePlayers = new ArrayList<Player>();

    public void addPlayer(Player player)
    {
        onlinePlayers.add(player);
    }

    public void removePlayer(Player player)
    {
        onlinePlayers.remove(player);
    }

    public ArrayList<Player> getOnlinePlayers()
    {
        return onlinePlayers;
    }
}
