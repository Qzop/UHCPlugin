package core.ScenariosInventory;

import core.mainPackage.Main;
import net.citizensnpcs.npc.ai.speech.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ScenariosInventory implements Listener
{
    public static boolean cutclean = false;
    public static boolean hastey = false;
    public static boolean lucky = false;
    public static boolean superheroes = false;
    public static boolean timber = false;
    public static boolean timebomb = false;
    public static boolean nofall = false;
    public static boolean fireless = false;
    public static boolean bloodgold = false;
    public static boolean blooddiamond = false;
    public static boolean goldenretriever = false;
    public static boolean diamondless = false;
    public static boolean goldless = false;
    public static boolean veinminer = false;
    public static boolean tripleores = false;
    public static boolean doubleores = false;

    public static ArrayList<ItemStack> enabledScenarios = new ArrayList<ItemStack>();

    Main plugin = Main.getPlugin(Main.class);

    public void scenarioList(Player p)
    {
        Inventory i = plugin.getServer().createInventory(null, 27, ChatColor.AQUA + "Scenarios List");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta fillermeta = filler.getItemMeta();
        fillermeta.setDisplayName(" ");
        filler.setItemMeta(fillermeta);

        int index = 10;

        for(int j = 0; j < enabledScenarios.size(); j++)
        {
            i.setItem(index, enabledScenarios.get(j));
            index++;
        }

        i.setItem(0, filler);
        i.setItem(1, filler);
        i.setItem(2, filler);
        i.setItem(3, filler);
        i.setItem(4, filler);
        i.setItem(5, filler);
        i.setItem(6, filler);
        i.setItem(7, filler);
        i.setItem(8, filler);
        i.setItem(9, filler);
        i.setItem(17, filler);
        i.setItem(18, filler);
        i.setItem(19, filler);
        i.setItem(20, filler);
        i.setItem(21, filler);
        i.setItem(22, filler);
        i.setItem(23, filler);
        i.setItem(24, filler);
        i.setItem(25, filler);
        i.setItem(26, filler);


        p.openInventory(i);
    }

    public void scenarioAdmin(Player p)
    {
        Inventory i = plugin.getServer().createInventory(null, 54, ChatColor.AQUA + "Scenarios Admin");

        new BukkitRunnable()
        {
            public void run()
            {
                ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE);
                ItemMeta fillermeta = filler.getItemMeta();
                fillermeta.setDisplayName(" ");
                filler.setItemMeta(fillermeta);

                ItemStack hasteyboys = new ItemStack(Material.DIAMOND_PICKAXE);
                hasteyboys.addEnchantment(Enchantment.DIG_SPEED, 3);
                ItemMeta hasteymeta = hasteyboys.getItemMeta();

                if(hastey)
                {
                    hasteymeta.setDisplayName(ChatColor.YELLOW + "Hastey Boys: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    hasteymeta.setDisplayName(ChatColor.YELLOW + "Hastey Boys: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                hasteyboys.setItemMeta(hasteymeta);

                Potion potion = new Potion(PotionType.STRENGTH);
                ItemStack superh = potion.toItemStack(1);
                ItemMeta superhmeta = superh.getItemMeta();

                if(superheroes)
                {
                    superhmeta.setDisplayName(ChatColor.YELLOW + "SuperHeroes: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    superhmeta.setDisplayName(ChatColor.YELLOW + "SuperHeroes: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                superh.setItemMeta(superhmeta);

                ItemStack cut = new ItemStack(Material.COOKED_BEEF);
                ItemMeta cutcleanmeta = cut.getItemMeta();

                if(cutclean)
                {
                    cutcleanmeta.setDisplayName(ChatColor.YELLOW + "CutClean: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    cutcleanmeta.setDisplayName(ChatColor.YELLOW + "CutClean: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                cut.setItemMeta(cutcleanmeta);

                ItemStack luck = new ItemStack(Material.TNT);
                ItemMeta luckmeta = luck.getItemMeta();

                if(lucky)
                {
                    luckmeta.setDisplayName(ChatColor.YELLOW + "Lucky Ores: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    luckmeta.setDisplayName(ChatColor.YELLOW + "Lucky Ores: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                luck.setItemMeta(luckmeta);

                ItemStack timb = new ItemStack(Material.LOG);
                ItemMeta timbmeta = timb.getItemMeta();

                if(timber)
                {
                    timbmeta.setDisplayName(ChatColor.YELLOW + "Timber: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    timbmeta.setDisplayName(ChatColor.YELLOW + "Timber: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                timb.setItemMeta(timbmeta);

                ItemStack time = new ItemStack(Material.CHEST);
                ItemMeta timemeta = time.getItemMeta();

                if(timebomb)
                {
                    timemeta.setDisplayName(ChatColor.YELLOW + "TimeBomb: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    timemeta.setDisplayName(ChatColor.YELLOW + "TimeBomb: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                time.setItemMeta(timemeta);

                ItemStack triple = new ItemStack(Material.DIAMOND);
                ItemMeta triplemeta = triple.getItemMeta();

                if(tripleores)
                {
                    triplemeta.setDisplayName(ChatColor.YELLOW + "TripleOres: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    triplemeta.setDisplayName(ChatColor.YELLOW + "TripleOres: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                triple.setItemMeta(triplemeta);

                ItemStack doubl = new ItemStack(Material.GOLD_INGOT);
                ItemMeta doublemeta = doubl.getItemMeta();

                if(doubleores)
                {
                    doublemeta.setDisplayName(ChatColor.YELLOW + "DoubleOres: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    doublemeta.setDisplayName(ChatColor.YELLOW + "DoubleOres: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                doubl.setItemMeta(doublemeta);

                ItemStack nogold = new ItemStack(Material.GOLD_ORE);
                ItemMeta nogoldmeta = nogold.getItemMeta();

                if(goldless)
                {
                    nogoldmeta.setDisplayName(ChatColor.YELLOW + "Goldless: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    nogoldmeta.setDisplayName(ChatColor.YELLOW + "Goldless: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                nogold.setItemMeta(nogoldmeta);

                ItemStack nodiamond = new ItemStack(Material.DIAMOND_ORE);
                ItemMeta nodiamondmeta = nodiamond.getItemMeta();

                if(diamondless)
                {
                    nodiamondmeta.setDisplayName(ChatColor.YELLOW + "Diamondless: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    nodiamondmeta.setDisplayName(ChatColor.YELLOW + "Diamondless: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                nodiamond.setItemMeta(nodiamondmeta);

                ItemStack vein = new ItemStack(Material.IRON_ORE);
                ItemMeta veinmeta = vein.getItemMeta();

                if(veinminer)
                {
                    veinmeta.setDisplayName(ChatColor.YELLOW + "VeinMiner: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    veinmeta.setDisplayName(ChatColor.YELLOW + "VeinMiner: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                vein.setItemMeta(veinmeta);

                ItemStack nofalldmg = new ItemStack(Material.FEATHER);
                ItemMeta nofallmeta = nofalldmg.getItemMeta();

                if(nofall)
                {
                    nofallmeta.setDisplayName(ChatColor.YELLOW + "NoFall: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    nofallmeta.setDisplayName(ChatColor.YELLOW + "NoFall: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                nofalldmg.setItemMeta(nofallmeta);

                ItemStack nofire = new ItemStack(Material.FLINT_AND_STEEL);
                ItemMeta nofiremeta = nofire.getItemMeta();

                if(fireless)
                {
                    nofiremeta.setDisplayName(ChatColor.YELLOW + "Fireless: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    nofiremeta.setDisplayName(ChatColor.YELLOW + "Fireless: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                nofire.setItemMeta(nofiremeta);

                i.setItem(0, filler);
                i.setItem(1, filler);
                i.setItem(2, filler);
                i.setItem(3, filler);
                i.setItem(4, filler);
                i.setItem(5, filler);
                i.setItem(6, filler);
                i.setItem(7, filler);
                i.setItem(8, filler);
                i.setItem(9, filler);

                i.setItem(10, hasteyboys);
                i.setItem(11, timb);
                i.setItem(12, time);
                i.setItem(13, cut);
                i.setItem(14, luck);
                i.setItem(15, superh);
                i.setItem(16, nofalldmg);

                i.setItem(17, filler);
                i.setItem(18, filler);

                i.setItem(19, nodiamond);
                i.setItem(20, nogold);
                i.setItem(21, triple);
                i.setItem(22, doubl);
                i.setItem(23, vein);
                i.setItem(24, nofire);

                i.setItem(26, filler);
                i.setItem(27, filler);

                i.setItem(35, filler);
                i.setItem(36, filler);

                i.setItem(44, filler);
                i.setItem(45, filler);
                i.setItem(46, filler);
                i.setItem(47, filler);
                i.setItem(48, filler);
                i.setItem(49, filler);
                i.setItem(50, filler);
                i.setItem(51, filler);
                i.setItem(52, filler);
                i.setItem(53, filler);

            }

        }.runTaskTimer(plugin, 0,  1);

        p.openInventory(i);
    }
}
