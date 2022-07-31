package core.Scenarios;

import core.HostsMods.HostsMods;
import core.Kills.PlayerKills;
import core.Scatter.Scatter;
import core.ScenariosInventory.ScenariosInventory;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperheroesCMD implements Listener
{
    private Map<String, String> playerToSuperPower = new HashMap<String, String>();
    static Boolean isSuperHerosAssigned = false;

    public boolean setSuperPower(String playeName, String superPower)
    {
        Player p = Bukkit.getServer().getPlayer(playeName);
        Team t = p.getPlayer().getScoreboard().getPlayerTeam(p);

        if(superPower.equalsIgnoreCase("assign"))
        {
            //        String[] playerPower = {" ", " ", " ", " "};
            //        String[] superPowers = {"health", "strength", "resistance", "speed"};

            List<String> currentSuperPowers = new ArrayList<String>();
            currentSuperPowers.add("health");
            currentSuperPowers.add("strength");
            currentSuperPowers.add("resistance");
            currentSuperPowers.add("speed");

            for(Player iPlayer : Main.online.getOnlinePlayers())
            {
                if(!HostsMods.hosts.contains(iPlayer.getUniqueId()) && !HostsMods.mods.contains(iPlayer.getUniqueId()) && !PlayerKills.spectator.contains(iPlayer.getUniqueId()))
                {
                    String power = getSuperPower(iPlayer.getName());

                    if(power != null)
                    {
                        p.sendMessage("removing power " + power);
                        int delIndex = 0;
                        for(int i = 0; i < currentSuperPowers.size(); i++)
                        {
                            if(power.equalsIgnoreCase(currentSuperPowers.get(i)))
                            {
                                delIndex = i;
                                break;
                            }
                        }
                        currentSuperPowers.remove(delIndex);
                        //                    playerPower[index] = power;
                        //                    index++;
                    }
                }
            }

            int randNum = (int)Math.round(Math.random()*(currentSuperPowers.size() - 1));
            playerToSuperPower.put(p.getUniqueId().toString(), currentSuperPowers.get(randNum));
            p.sendMessage(Scatter.UHCprefix + ChatColor.GREEN + " You have been assigned the super power of " + ChatColor.YELLOW + ChatColor.BOLD + currentSuperPowers.get(randNum));
            InitializeSuperPower(p.getName(), currentSuperPowers.get(randNum));
            return true;
        }
        else
        {
            if(superPower.equalsIgnoreCase("health"))
            {
                playerToSuperPower.put(p.getUniqueId().toString(), "health");
                p.sendMessage(ChatColor.GREEN + "You have been assigned the super power of health");
                InitializeSuperPower(p.getName(), "health");

            }
            else if(superPower.equalsIgnoreCase("strength"))
            {
                playerToSuperPower.put(p.getUniqueId().toString(), "strength");
                p.sendMessage(ChatColor.GREEN + "You have been assigned the super power of strength");
                InitializeSuperPower(p.getName(), "strength");

            }
            else if(superPower.equalsIgnoreCase("resistance"))
            {
                playerToSuperPower.put(p.getUniqueId().toString(), "resistance");
                p.sendMessage(ChatColor.GREEN + "You have been assigned the super power of resistance");
                InitializeSuperPower(p.getName(), "resistance");
            }
            else if(superPower.equalsIgnoreCase("speed"))
            {
                playerToSuperPower.put(p.getUniqueId().toString(), "speed");
                p.sendMessage(ChatColor.GREEN + "You have been assigned the super power of speed");
                InitializeSuperPower(p.getName(), "speed");
            }
            else
            {
                return false;
            }
            return true;
        }

    }



    public String getSuperPower(String playerName)
    {
        Player p = Bukkit.getServer().getPlayer(playerName);

        if(playerToSuperPower.containsKey(p.getUniqueId().toString()))
        {
            try
            {
                String superPower = playerToSuperPower.get(p.getUniqueId().toString());
                return superPower;
            }
            catch(Exception e)
            {
                return null;
            }
        }
        return null;
    }

    public void InitializeSuperPower(String playerName, String superPower)
    {
        Player p = Bukkit.getServer().getPlayer(playerName);

        if(superPower.equalsIgnoreCase("speed"))
        {
            p.removePotionEffect(PotionEffectType.SPEED);
            p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            p.setMaxHealth(20);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 216000, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 216000, 2));
        }

        if(superPower.equalsIgnoreCase("health"))
        {
            p.removePotionEffect(PotionEffectType.SPEED);
            p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            p.setMaxHealth(40);
            p.setHealth(40);
        }

        if(superPower.equalsIgnoreCase("resistance"))
        {
            p.removePotionEffect(PotionEffectType.SPEED);
            p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 216000, 0));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 216000, 5));
            p.setMaxHealth(20);
        }

        if(superPower.equalsIgnoreCase("strength"))
        {
            p.removePotionEffect(PotionEffectType.SPEED);
            p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 216000, 0));
            p.setMaxHealth(20);
        }
    }
}
