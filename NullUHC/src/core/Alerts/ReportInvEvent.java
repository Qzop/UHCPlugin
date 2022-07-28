package core.Alerts;

import core.Config.ConfigInventory;
import core.mainPackage.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ReportInvEvent implements Listener
{
    private ReportInv rep = new ReportInv();

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        Inventory inv = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        ClickType click = e.getClick();
        Player p = (Player) e.getWhoClicked();

        if(inv == null)
        {
            return;
        }
        else if(inv.getName().equals(ChatColor.RED + "Report"))
        {
            e.setCancelled(true);

            if(item == null || !item.hasItemMeta())
            {
                return;
            }

            if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Xray/CaveFinder"))
            {
                Player target = Bukkit.getPlayer(ReportInv.report.get(p.getUniqueId()));

                for(Player player : Main.online.getOnlinePlayers())
                {
                    if(player.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(player.getUniqueId()) && !Alerts.reportalerts.contains(player.getUniqueId()))
                    {
                        if(target == null)
                        {
                            OfflinePlayer targ = Bukkit.getOfflinePlayer(ReportInv.report.get(p.getUniqueId()));
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + targ.getName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Xray/CaveFinder");
                        }
                        else
                        {
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + target.getDisplayName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Xray/CaveFinder");
                        }
                    }
                }

                p.sendMessage(ReportInv.reportPref + ChatColor.GREEN + " Report sent successfully.");
                rep.onWait(p);
                p.closeInventory();
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Tracers"))
            {
                Player target = Bukkit.getPlayer(ReportInv.report.get(p.getUniqueId()));

                for(Player player : Main.online.getOnlinePlayers())
                {
                    if(player.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(player.getUniqueId()) && !Alerts.reportalerts.contains(player.getUniqueId()))
                    {
                        if(target == null)
                        {
                            OfflinePlayer targ = Bukkit.getOfflinePlayer(ReportInv.report.get(p.getUniqueId()));
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + targ.getName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Tracers.");
                        }
                        else
                        {
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + target.getDisplayName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Tracers.");
                        }
                    }
                }

                p.sendMessage(ReportInv.reportPref + ChatColor.GREEN + " Report sent successfully.");
                rep.onWait(p);
                p.closeInventory();
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Combat Cheats"))
            {
                for(Player player : Main.online.getOnlinePlayers())
                {
                    Player target = Bukkit.getPlayer(ReportInv.report.get(p.getUniqueId()));

                    if(player.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(player.getUniqueId()) && !Alerts.reportalerts.contains(player.getUniqueId()))
                    {
                        if(target == null)
                        {
                            OfflinePlayer targ = Bukkit.getOfflinePlayer(ReportInv.report.get(p.getUniqueId()));
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + targ.getName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Combat Cheats.");
                        }
                        else
                        {
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + target.getDisplayName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Combat Cheats.");
                        }
                    }
                }

                p.sendMessage(ReportInv.reportPref + ChatColor.GREEN + " Report sent successfully.");
                rep.onWait(p);
                p.closeInventory();
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Harassment/Disrespectful Behavior"))
            {
                Player target = Bukkit.getPlayer(ReportInv.report.get(p.getUniqueId()));

                for(Player player : Main.online.getOnlinePlayers())
                {
                    if(player.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(player.getUniqueId()) && !Alerts.reportalerts.contains(player.getUniqueId()))
                    {
                        if(target == null)
                        {
                            OfflinePlayer targ = Bukkit.getOfflinePlayer(ReportInv.report.get(p.getUniqueId()));
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + targ.getName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Harassment/Disrespectful Behavior.");
                        }
                        else
                        {
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + target.getDisplayName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Harassment/Disrespectful Behavior.");
                        }                    }
                }

                p.sendMessage(ReportInv.reportPref + ChatColor.GREEN + " Report sent successfully.");
                rep.onWait(p);
                p.closeInventory();
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Bug Abuse"))
            {
                Player target = Bukkit.getPlayer(ReportInv.report.get(p.getUniqueId()));

                for(Player player : Main.online.getOnlinePlayers())
                {
                    if(player.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(player.getUniqueId()) && !Alerts.reportalerts.contains(player.getUniqueId()))
                    {
                        if(target == null)
                        {
                            OfflinePlayer targ = Bukkit.getOfflinePlayer(ReportInv.report.get(p.getUniqueId()));
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + targ.getName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Bug Abuse.");
                        }
                        else
                        {
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + target.getDisplayName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Bug Abuse.");
                        }
                    }
                }

                p.sendMessage(ReportInv.reportPref + ChatColor.GREEN + " Report sent successfully.");
                rep.onWait(p);
                p.closeInventory();
            }
            else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Spam"))
            {
                Player target = Bukkit.getPlayer(ReportInv.report.get(p.getUniqueId()));

                for(Player player : Main.online.getOnlinePlayers())
                {
                    if(player.hasPermission("uhc.alerts") && !Alerts.allalerts.contains(player.getUniqueId()) && !Alerts.reportalerts.contains(player.getUniqueId()))
                    {
                        if(target == null)
                        {
                            OfflinePlayer targ = Bukkit.getOfflinePlayer(ReportInv.report.get(p.getUniqueId()));
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + targ.getName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Spam.");
                        }
                        else
                        {
                            player.sendMessage(ReportInv.reportPref + ChatColor.AQUA + " " + p.getDisplayName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + target.getDisplayName() + ChatColor.YELLOW + " for " + ChatColor.GOLD + "Spam.");
                        }
                    }
                }

                p.sendMessage(ReportInv.reportPref + ChatColor.GREEN + " Report sent successfully.");
                rep.onWait(p);
                p.closeInventory();
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e)
    {
        Inventory inv = e.getInventory();

        if(inv.getName().equals(ChatColor.RED + "Report"))
        {
            if(ReportInv.report.containsKey(e.getPlayer().getUniqueId()) && !ReportInv.reportWait.contains(e.getPlayer().getUniqueId()))
            {
                ReportInv.report.remove(e.getPlayer().getUniqueId());
            }
        }
    }
}
