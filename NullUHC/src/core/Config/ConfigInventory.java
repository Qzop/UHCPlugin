package core.Config;

import core.mainPackage.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class ConfigInventory implements Listener
{
    Main plugin = Main.getPlugin(Main.class);

    public static int teamSize = 1;
    public static double applerate = 2.0;
    public static double flintRate = 50.0;
    public static boolean netherBoo = false;
    public static int borderSize = 1500;
    public static boolean sp1 = true;
    public static boolean sp2 = false;
    public static boolean strength1 = false;
    public static boolean strength2 = false;
    public static boolean teamDamage = false;
    public static boolean passiveMobs = true;
    public static int sFood = 20;
    public static int nBorderSize = 1000;
    public static boolean stalk = false;
    public static boolean skybasing = false;
    public static boolean stealing = false;
    public static boolean crossteaming = false;
    public static boolean runAtMeet = false;
    public static int absorption = 2;
    public static boolean bookshelves = true;
    public static boolean fireweapons = true;
    public static boolean gHead = true;
    public static boolean godApp = false;
    public static boolean horses = true;
    public static boolean pearlDmg = true;
    public static boolean stripMining = true;
    public static boolean pokeHoling = true;
    public static boolean rollerCoasting = true;
    public static int finalHeal = 10;
    public static int gracePeriod = 20;
    public static int meetUp = 45;
    public static int latescatter = 20;
    public static int firstShrink = 35;
    public static int shrinkInterval = 5;

    public void createConfig(Player p)
    {
        Inventory i = plugin.getServer().createInventory(null, 63, ChatColor.AQUA + "Config");

        new BukkitRunnable()
        {
            public void run()
            {
                ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0);
                ItemMeta fmeta = filler.getItemMeta();
                fmeta.setDisplayName(" ");
                filler.setItemMeta(fmeta);

                ItemStack apple = new ItemStack(Material.APPLE, 1, (byte) 0);
                ItemMeta ameta = apple.getItemMeta();
                ameta.setDisplayName(ChatColor.GOLD + "Apple Rate: " + ChatColor.AQUA + ChatColor.BOLD + applerate + "%");
                apple.setItemMeta(ameta);

                ItemStack teamsize = new ItemStack(Material.WORKBENCH, 1, (byte) 0);
                ItemMeta tmeta = teamsize.getItemMeta();

                if (teamSize == 0)
                {
                    tmeta.setDisplayName(ChatColor.GOLD + "Teamsize: " + ChatColor.AQUA + ChatColor.BOLD + "FFA");
                }
                else
                {
                    tmeta.setDisplayName(ChatColor.GOLD + "Teamsize: " + ChatColor.AQUA + ChatColor.BOLD + "Team of " + teamSize);
                }
                teamsize.setItemMeta(tmeta);

                ItemStack nether = new ItemStack(Material.NETHERRACK, 1, (byte) 0);
                ItemMeta nmeta = nether.getItemMeta();

                if (netherBoo)
                {
                    nmeta.setDisplayName(ChatColor.GOLD + "Nether: " + ChatColor.RED + ChatColor.BOLD + "On");
                }
                else
                {
                    nmeta.setDisplayName(ChatColor.GOLD + "Nether: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                nether.setItemMeta(nmeta);

                ItemStack border = new ItemStack(Material.BEDROCK, 1, (byte) 0);
                ItemMeta bmeta = border.getItemMeta();
                bmeta.setDisplayName(ChatColor.GOLD + "Border Size: " + ChatColor.AQUA + ChatColor.BOLD + borderSize);
                border.setItemMeta(bmeta);

                Potion sI = new Potion(PotionType.SPEED, 1);
                ItemStack speed1 = sI.toItemStack(1);
                ItemMeta s1meta = speed1.getItemMeta();

                if (sp1)
                {
                    s1meta.setDisplayName(ChatColor.GOLD + "Speed 1: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    s1meta.setDisplayName(ChatColor.GOLD + "Speed 1: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                speed1.setItemMeta(s1meta);

                Potion sII = new Potion(PotionType.SPEED, 2);
                ItemStack speed2 = sII.toItemStack(1);
                ItemMeta s2meta = speed2.getItemMeta();

                if (sp2)
                {
                    s2meta.setDisplayName(ChatColor.GOLD + "Speed 2: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    s2meta.setDisplayName(ChatColor.GOLD + "Speed 2: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                speed2.setItemMeta(s2meta);

                ItemStack teamDmg = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 0);
                ItemMeta tdmeta = teamDmg.getItemMeta();

                if (teamDamage)
                {
                    tdmeta.setDisplayName(ChatColor.GOLD + "Team Damage: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    tdmeta.setDisplayName(ChatColor.GOLD + "Team Damage: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                teamDmg.setItemMeta(tdmeta);

                ItemStack pMobs = new ItemStack(Material.BLAZE_POWDER, 1, (byte) 0);
                ItemMeta pmeta = pMobs.getItemMeta();

                if (passiveMobs)
                {
                    pmeta.setDisplayName(ChatColor.GOLD + "Passive Mobs: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    pmeta.setDisplayName(ChatColor.GOLD + "Passive Mobs: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }

                pMobs.setItemMeta(pmeta);

                ItemStack startfood = new ItemStack(Material.COOKED_BEEF, 1, (byte) 0);
                ItemMeta stmeta = startfood.getItemMeta();
                stmeta.setDisplayName(ChatColor.GOLD + "Starter Food: " + ChatColor.AQUA + ChatColor.BOLD + sFood + " Steak");
                startfood.setItemMeta(stmeta);

                ItemStack nborder = new ItemStack(Material.NETHER_BRICK, 1, (byte) 0);
                ItemMeta nbmeta = nborder.getItemMeta();
                nbmeta.setDisplayName(ChatColor.GOLD + "Nether Border Size: " + ChatColor.AQUA + ChatColor.BOLD + nBorderSize);
                nborder.setItemMeta(nbmeta);

                ItemStack stalking = new ItemStack(Material.BONE, 1, (byte) 0);
                ItemMeta stameta = stalking.getItemMeta();

                if (stalk)
                {
                    stameta.setDisplayName(ChatColor.GOLD + "Stalking during grace period: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    stameta.setDisplayName(ChatColor.GOLD + "Stalking during grace period: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                stalking.setItemMeta(stameta);

                ItemStack sky = new ItemStack(Material.BEACON, 1, (byte) 0);
                ItemMeta skymeta = sky.getItemMeta();

                if (skybasing)
                {
                    skymeta.setDisplayName(ChatColor.GOLD + "Skybasing within 100x100: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    skymeta.setDisplayName(ChatColor.GOLD + "Skybasing within 100x100: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                sky.setItemMeta(skymeta);

                ItemStack steal = new ItemStack(Material.FLINT_AND_STEEL, 1, (byte) 0);
                ItemMeta stealmeta = steal.getItemMeta();

                if (stealing)
                {
                    stealmeta.setDisplayName(ChatColor.GOLD + "Stealing during grace period: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    stealmeta.setDisplayName(ChatColor.GOLD + "Stealing during grace period: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                steal.setItemMeta(stealmeta);

                ItemStack run = new ItemStack(Material.DIAMOND_BOOTS, 1, (byte) 0);
                ItemMeta runmeta = run.getItemMeta();

                if (runAtMeet)
                {
                    runmeta.setDisplayName(ChatColor.GOLD + "Running at Meetup: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    runmeta.setDisplayName(ChatColor.GOLD + "Running at Meetup: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                run.setItemMeta(runmeta);

                ItemStack cross = new ItemStack(Material.ARROW, 1, (byte) 0);
                ItemMeta crmeta = cross.getItemMeta();

                if (crossteaming)
                {
                    crmeta.setDisplayName(ChatColor.GOLD + "CrossTeaming: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    crmeta.setDisplayName(ChatColor.GOLD + "CrossTeaming: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                cross.setItemMeta(crmeta);

                ItemStack absorp = new ItemStack(Material.DIAMOND_CHESTPLATE, 1, (byte) 0);
                ItemMeta absmeta = absorp.getItemMeta();
                absmeta.setDisplayName(ChatColor.GOLD + "Absorption: " + ChatColor.AQUA + ChatColor.BOLD + absorption + " Hearts");
                absorp.setItemMeta(absmeta);

                ItemStack book = new ItemStack(Material.BOOKSHELF, 1, (byte) 0);
                ItemMeta boometa = book.getItemMeta();

                if (bookshelves)
                {
                    boometa.setDisplayName(ChatColor.GOLD + "BookShelves: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    boometa.setDisplayName(ChatColor.GOLD + "BookShelves: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                book.setItemMeta(boometa);

                ItemStack fire = new ItemStack(Material.FIREBALL, 1, (byte) 0);
                ItemMeta firemeta = fire.getItemMeta();

                if (fireweapons)
                {
                    firemeta.setDisplayName(ChatColor.GOLD + "FireWeapons: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    firemeta.setDisplayName(ChatColor.GOLD + "FireWeapons: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                fire.setItemMeta(firemeta);

                ItemStack goldenHeads = new ItemStack(Material.GOLDEN_APPLE, 1, (byte) 0);
                ItemMeta goldmeta = goldenHeads.getItemMeta();

                if (gHead)
                {
                    goldmeta.setDisplayName(ChatColor.GOLD + "Golden Heads: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    goldmeta.setDisplayName(ChatColor.GOLD + "Golden Heads: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                goldenHeads.setItemMeta(goldmeta);

                ItemStack godApple = new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);
                ItemMeta godmeta = godApple.getItemMeta();

                if (godApp)
                {
                    godmeta.setDisplayName(ChatColor.GOLD + "God Apples: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    godmeta.setDisplayName(ChatColor.GOLD + "God Apples: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                godApple.setItemMeta(godmeta);

                ItemStack horse = new ItemStack(Material.SADDLE, 1, (byte) 0);
                ItemMeta horsemeta = horse.getItemMeta();

                if (horses)
                {
                    horsemeta.setDisplayName(ChatColor.GOLD + "Horses: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    horsemeta.setDisplayName(ChatColor.GOLD + "Horses: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                horse.setItemMeta(horsemeta);

                ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 1, (byte) 0);
                ItemMeta pearlmeta = pearl.getItemMeta();

                if (pearlDmg)
                {
                    pearlmeta.setDisplayName(ChatColor.GOLD + "Pearl Damage: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    pearlmeta.setDisplayName(ChatColor.GOLD + "Pearl Damage: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                pearl.setItemMeta(pearlmeta);

                ItemStack strip = new ItemStack(Material.DIAMOND_PICKAXE, 1, (byte) 0);
                ItemMeta stripmeta = strip.getItemMeta();

                if (stripMining)
                {
                    stripmeta.setDisplayName(ChatColor.GOLD + "Strip Mining: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    stripmeta.setDisplayName(ChatColor.GOLD + "Strip Mining: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                strip.setItemMeta(stripmeta);

                ItemStack poke = new ItemStack(Material.IRON_PICKAXE, 1, (byte) 0);
                ItemMeta pokemeta = poke.getItemMeta();

                if (pokeHoling)
                {
                    pokemeta.setDisplayName(ChatColor.GOLD + "Poke Holing: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    pokemeta.setDisplayName(ChatColor.GOLD + "Poke Holing: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                poke.setItemMeta(pokemeta);

                ItemStack roller = new ItemStack(Material.MINECART, 1, (byte) 0);
                ItemMeta rollermeta = roller.getItemMeta();

                if (rollerCoasting)
                {
                    rollermeta.setDisplayName(ChatColor.GOLD + "Roller-coasting: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    rollermeta.setDisplayName(ChatColor.GOLD + "Roller-coasting: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                roller.setItemMeta(rollermeta);

                ItemStack grace = new ItemStack(Material.BOW, 1, (byte) 0);
                ItemMeta gracemeta = grace.getItemMeta();
                gracemeta.setDisplayName(ChatColor.GOLD + "Grace Period: " + ChatColor.AQUA + ChatColor.BOLD + gracePeriod + " Minutes");
                grace.setItemMeta(gracemeta);

                ItemStack flint = new ItemStack(Material.FLINT, 1, (byte) 0);
                ItemMeta flintmeta = flint.getItemMeta();
                flintmeta.setDisplayName(ChatColor.GOLD + "Flint Rates: " + ChatColor.AQUA + ChatColor.BOLD + flintRate + "%");
                flint.setItemMeta(flintmeta);

                Potion heal = new Potion(PotionType.INSTANT_HEAL, 1);
                ItemStack finalheal = heal.toItemStack(1);
                ItemMeta finalmeta = finalheal.getItemMeta();
                finalmeta.setDisplayName(ChatColor.GOLD + "Final Heal: " + ChatColor.AQUA + ChatColor.BOLD + finalHeal + " Minutes");
                finalheal.setItemMeta(finalmeta);


                // make inventory bigger and add meetup and strength1 and strength2
                ItemStack meet = new ItemStack(Material.SKULL_ITEM, 1, (byte) 0);
                ItemMeta meetmeta = meet.getItemMeta();
                meetmeta.setDisplayName(ChatColor.GOLD + "Meetup: " + ChatColor.AQUA + ChatColor.BOLD + meetUp + " Minutes");
                meet.setItemMeta(meetmeta);

                Potion str1 = new Potion(PotionType.STRENGTH, 1);
                ItemStack strI = str1.toItemStack(1);
                ItemMeta strmeta = strI.getItemMeta();

                if (strength1)
                {
                    strmeta.setDisplayName(ChatColor.GOLD + "Strength 1: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    strmeta.setDisplayName(ChatColor.GOLD + "Strength 1: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                strI.setItemMeta(strmeta);

                Potion str2 = new Potion(PotionType.STRENGTH, 2);
                ItemStack strII = str2.toItemStack(1);
                ItemMeta str2meta = strII.getItemMeta();

                if (strength2)
                {
                    str2meta.setDisplayName(ChatColor.GOLD + "Strength 2: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    str2meta.setDisplayName(ChatColor.GOLD + "Strength 2: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                strII.setItemMeta(str2meta);

                ItemStack lateScat = new ItemStack(Material.WATCH, 1, (byte) 0);
                ItemMeta latemeta = lateScat.getItemMeta();
                latemeta.setDisplayName(ChatColor.GOLD + "LateScatter: " + ChatColor.AQUA + ChatColor.BOLD + latescatter + " Minutes");
                lateScat.setItemMeta(latemeta);

                ItemStack first = new ItemStack(Material.COMPASS, 1, (byte) 0);
                ItemMeta firstmeta = first.getItemMeta();
                firstmeta.setDisplayName(ChatColor.GOLD + "First Shrink: " + ChatColor.AQUA + ChatColor.BOLD + firstShrink + " Minutes");
                first.setItemMeta(firstmeta);

                ItemStack shrinkInt = new ItemStack(Material.ANVIL, 1, (byte) 0);
                ItemMeta shrinkmeta = shrinkInt.getItemMeta();
                shrinkmeta.setDisplayName(ChatColor.GOLD + "Shrink Interval: " + ChatColor.AQUA + ChatColor.BOLD + shrinkInterval + " Minutes");
                shrinkInt.setItemMeta(shrinkmeta);

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
                i.setItem(10, apple);
                i.setItem(11, startfood);
                i.setItem(12, flint);
                i.setItem(13, speed1);
                i.setItem(14, speed2);
                i.setItem(15, strI);
                i.setItem(16, strII);
                i.setItem(17, filler);
                i.setItem(18, filler);
                i.setItem(19, meet);
                i.setItem(20, grace);
                i.setItem(21, finalheal);
                i.setItem(22, border);
                i.setItem(23, nborder);
                i.setItem(24, first);
                i.setItem(25, shrinkInt);
                i.setItem(26, filler);
                i.setItem(27, filler);
                i.setItem(28, teamsize);
                i.setItem(29, teamDmg);
                i.setItem(30, cross);
                i.setItem(31, pearl);
                i.setItem(32, goldenHeads);
                i.setItem(33, godApple);
                i.setItem(34, horse);
                i.setItem(35, filler);
                i.setItem(36, filler);
                i.setItem(37, strip);
                i.setItem(38, poke);
                i.setItem(39, roller);
                i.setItem(40, stalking);
                i.setItem(41, sky);
                i.setItem(42, steal);
                i.setItem(43, run);
                i.setItem(44, filler);
                i.setItem(45, filler);
                i.setItem(46, nether);
                i.setItem(47, fire);
                i.setItem(48, book);
                i.setItem(49, absorp);
                i.setItem(50, pMobs);
                i.setItem(51, lateScat);
                i.setItem(53, filler);
                i.setItem(54, filler);
                i.setItem(55, filler);
                i.setItem(56, filler);
                i.setItem(57, filler);
                i.setItem(58, filler);
                i.setItem(59, filler);
                i.setItem(60, filler);
                i.setItem(61, filler);
                i.setItem(62, filler);

            }
        }.runTaskTimer(plugin, 0, 1);

        p.openInventory(i);
    }

    public void createConfigAdmin(Player p)
    {
        Inventory i = plugin.getServer().createInventory(null, 63, ChatColor.AQUA + "Config Admin");

        new BukkitRunnable()
        {
            public void run()
            {
                ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0);
                ItemMeta fmeta = filler.getItemMeta();
                fmeta.setDisplayName(" ");
                filler.setItemMeta(fmeta);

                ItemStack apple = new ItemStack(Material.APPLE, 1, (byte) 0);
                ItemMeta ameta = apple.getItemMeta();
                ameta.setDisplayName(ChatColor.GOLD + "Apple Rate: " + ChatColor.AQUA + ChatColor.BOLD + applerate + "%");
                List<String> alore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                ameta.setLore(alore);
                apple.setItemMeta(ameta);

                ItemStack teamsize = new ItemStack(Material.WORKBENCH, 1, (byte) 0);
                ItemMeta tmeta = teamsize.getItemMeta();

                if (teamSize == 1)
                {
                    tmeta.setDisplayName(ChatColor.GOLD + "Teamsize: " + ChatColor.AQUA + ChatColor.BOLD + "FFA");
                }
                else
                {
                    tmeta.setDisplayName(ChatColor.GOLD + "Teamsize: " + ChatColor.AQUA + ChatColor.BOLD + "Team of " + teamSize);
                }
                List<String> tlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                tmeta.setLore(tlore);
                teamsize.setItemMeta(tmeta);

                ItemStack nether = new ItemStack(Material.NETHERRACK, 1, (byte) 0);
                ItemMeta nmeta = nether.getItemMeta();

                if (netherBoo)
                {
                    nmeta.setDisplayName(ChatColor.GOLD + "Nether: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    nmeta.setDisplayName(ChatColor.GOLD + "Nether: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> nlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                nmeta.setLore(nlore);
                nether.setItemMeta(nmeta);

                ItemStack border = new ItemStack(Material.BEDROCK, 1, (byte) 0);
                ItemMeta bmeta = border.getItemMeta();
                bmeta.setDisplayName(ChatColor.GOLD + "Border Size: " + ChatColor.AQUA + ChatColor.BOLD + borderSize);
                List<String> blore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                bmeta.setLore(blore);
                border.setItemMeta(bmeta);

                Potion sI = new Potion(PotionType.SPEED, 1);
                ItemStack speed1 = sI.toItemStack(1);
                ItemMeta s1meta = speed1.getItemMeta();

                if (sp1)
                {
                    s1meta.setDisplayName(ChatColor.GOLD + "Speed 1: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    s1meta.setDisplayName(ChatColor.GOLD + "Speed 1: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> s1lore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                s1meta.setLore(s1lore);
                speed1.setItemMeta(s1meta);

                Potion sII = new Potion(PotionType.SPEED, 2);
                ItemStack speed2 = sII.toItemStack(1);
                ItemMeta s2meta = speed2.getItemMeta();

                if (sp2)
                {
                    s2meta.setDisplayName(ChatColor.GOLD + "Speed 2: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    s2meta.setDisplayName(ChatColor.GOLD + "Speed 2: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> s2lore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                s2meta.setLore(s2lore);
                speed2.setItemMeta(s2meta);

                ItemStack teamDmg = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 0);
                ItemMeta tdmeta = teamDmg.getItemMeta();

                if (teamDamage)
                {
                    tdmeta.setDisplayName(ChatColor.GOLD + "Team Damage: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    tdmeta.setDisplayName(ChatColor.GOLD + "Team Damage: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> tdlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                tdmeta.setLore(tdlore);
                teamDmg.setItemMeta(tdmeta);

                ItemStack pMobs = new ItemStack(Material.BLAZE_POWDER, 1, (byte) 0);
                ItemMeta pmeta = pMobs.getItemMeta();
                if (passiveMobs)
                {
                    pmeta.setDisplayName(ChatColor.GOLD + "Passive Mobs: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    pmeta.setDisplayName(ChatColor.GOLD + "Passive Mobs: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> plore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                pmeta.setLore(plore);
                pMobs.setItemMeta(pmeta);

                ItemStack startfood = new ItemStack(Material.COOKED_BEEF, 1, (byte) 0);
                ItemMeta stmeta = startfood.getItemMeta();
                stmeta.setDisplayName(ChatColor.GOLD + "Starter Food: " + ChatColor.AQUA + ChatColor.BOLD + sFood + " Steak");
                List<String> stlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                stmeta.setLore(stlore);
                startfood.setItemMeta(stmeta);

                ItemStack nborder = new ItemStack(Material.NETHER_BRICK, 1, (byte) 0);
                ItemMeta nbmeta = nborder.getItemMeta();
                nbmeta.setDisplayName(ChatColor.GOLD + "Nether Border Size: " + ChatColor.AQUA + ChatColor.BOLD + nBorderSize);
                List<String> nblore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                nbmeta.setLore(nblore);
                nborder.setItemMeta(nbmeta);

                ItemStack stalking = new ItemStack(Material.BONE, 1, (byte) 0);
                ItemMeta stameta = stalking.getItemMeta();

                if (stalk)
                {
                    stameta.setDisplayName(ChatColor.GOLD + "Stalking during grace period: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    stameta.setDisplayName(ChatColor.GOLD + "Stalking during grace period: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                List<String> stalore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                stameta.setLore(stalore);
                stalking.setItemMeta(stameta);

                ItemStack sky = new ItemStack(Material.BEACON, 1, (byte) 0);
                ItemMeta skymeta = sky.getItemMeta();

                if (skybasing)
                {
                    skymeta.setDisplayName(ChatColor.GOLD + "Skybasing within 100x100: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    skymeta.setDisplayName(ChatColor.GOLD + "Skybasing within 100x100: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                List<String> skylore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                skymeta.setLore(skylore);
                sky.setItemMeta(skymeta);

                ItemStack steal = new ItemStack(Material.FLINT_AND_STEEL, 1, (byte) 0);
                ItemMeta stealmeta = steal.getItemMeta();

                if (stealing)
                {
                    stealmeta.setDisplayName(ChatColor.GOLD + "Stealing during grace period: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    stealmeta.setDisplayName(ChatColor.GOLD + "Stealing during grace period: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                List<String> steallore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                stealmeta.setLore(steallore);
                steal.setItemMeta(stealmeta);

                ItemStack run = new ItemStack(Material.DIAMOND_BOOTS, 1, (byte) 0);
                ItemMeta runmeta = run.getItemMeta();

                if (runAtMeet)
                {
                    runmeta.setDisplayName(ChatColor.GOLD + "Running at Meetup: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    runmeta.setDisplayName(ChatColor.GOLD + "Running at Meetup: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                List<String> runlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                runmeta.setLore(runlore);
                run.setItemMeta(runmeta);

                ItemStack cross = new ItemStack(Material.ARROW, 1, (byte) 0);
                ItemMeta crmeta = cross.getItemMeta();

                if (crossteaming)
                {
                    crmeta.setDisplayName(ChatColor.GOLD + "CrossTeaming: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    crmeta.setDisplayName(ChatColor.GOLD + "CrossTeaming: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                List<String> crlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                crmeta.setLore(crlore);
                cross.setItemMeta(crmeta);

                ItemStack absorp = new ItemStack(Material.DIAMOND_CHESTPLATE, 1, (byte) 0);
                ItemMeta absmeta = absorp.getItemMeta();
                absmeta.setDisplayName(ChatColor.GOLD + "Absorption: " + ChatColor.AQUA + ChatColor.BOLD + absorption + " Hearts");
                absorp.setItemMeta(absmeta);

                ItemStack book = new ItemStack(Material.BOOKSHELF, 1, (byte) 0);
                ItemMeta boometa = book.getItemMeta();

                if (bookshelves)
                {
                    boometa.setDisplayName(ChatColor.GOLD + "BookShelves: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    boometa.setDisplayName(ChatColor.GOLD + "BookShelves: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> boolore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                boometa.setLore(boolore);
                book.setItemMeta(boometa);

                ItemStack fire = new ItemStack(Material.FIREBALL, 1, (byte) 0);
                ItemMeta firemeta = fire.getItemMeta();

                if (fireweapons)
                {
                    firemeta.setDisplayName(ChatColor.GOLD + "FireWeapons: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    firemeta.setDisplayName(ChatColor.GOLD + "FireWeapons: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> firelore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                firemeta.setLore(firelore);
                fire.setItemMeta(firemeta);

                ItemStack goldenHeads = new ItemStack(Material.GOLDEN_APPLE, 1, (byte) 0);
                ItemMeta goldmeta = goldenHeads.getItemMeta();

                if (gHead)
                {
                    goldmeta.setDisplayName(ChatColor.GOLD + "Golden Heads: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    goldmeta.setDisplayName(ChatColor.GOLD + "Golden Heads: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> goldlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                goldmeta.setLore(goldlore);
                goldenHeads.setItemMeta(goldmeta);

                ItemStack godApple = new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);
                ItemMeta godmeta = godApple.getItemMeta();

                if (godApp)
                {
                    godmeta.setDisplayName(ChatColor.GOLD + "God Apples: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    godmeta.setDisplayName(ChatColor.GOLD + "God Apples: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> godlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                godmeta.setLore(godlore);
                godApple.setItemMeta(godmeta);

                ItemStack horse = new ItemStack(Material.SADDLE, 1, (byte) 0);
                ItemMeta horsemeta = horse.getItemMeta();

                if (horses)
                {
                    horsemeta.setDisplayName(ChatColor.GOLD + "Horses: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    horsemeta.setDisplayName(ChatColor.GOLD + "Horses: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> horselore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                horsemeta.setLore(horselore);
                horse.setItemMeta(horsemeta);

                ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 1, (byte) 0);
                ItemMeta pearlmeta = pearl.getItemMeta();

                if (pearlDmg)
                {
                    pearlmeta.setDisplayName(ChatColor.GOLD + "Pearl Damage: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    pearlmeta.setDisplayName(ChatColor.GOLD + "Pearl Damage: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> pearllore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                pearlmeta.setLore(pearllore);
                pearl.setItemMeta(pearlmeta);

                ItemStack strip = new ItemStack(Material.DIAMOND_PICKAXE, 1, (byte) 0);
                ItemMeta stripmeta = strip.getItemMeta();

                if (stripMining)
                {
                    stripmeta.setDisplayName(ChatColor.GOLD + "Strip Mining: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    stripmeta.setDisplayName(ChatColor.GOLD + "Strip Mining: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                List<String> striplore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                stripmeta.setLore(striplore);
                strip.setItemMeta(stripmeta);

                ItemStack poke = new ItemStack(Material.IRON_PICKAXE, 1, (byte) 0);
                ItemMeta pokemeta = poke.getItemMeta();

                if (pokeHoling)
                {
                    pokemeta.setDisplayName(ChatColor.GOLD + "Poke Holing: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    pokemeta.setDisplayName(ChatColor.GOLD + "Poke Holing: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                List<String> pokelore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                pokemeta.setLore(pokelore);
                poke.setItemMeta(pokemeta);

                ItemStack roller = new ItemStack(Material.MINECART, 1, (byte) 0);
                ItemMeta rollermeta = roller.getItemMeta();

                if (rollerCoasting)
                {
                    rollermeta.setDisplayName(ChatColor.GOLD + "Roller-coasting: " + ChatColor.GREEN + ChatColor.BOLD + "Allowed");
                }
                else
                {
                    rollermeta.setDisplayName(ChatColor.GOLD + "Roller-coasting: " + ChatColor.RED + ChatColor.BOLD + "Not Allowed");
                }
                List<String> rollerlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                rollermeta.setLore(rollerlore);
                roller.setItemMeta(rollermeta);

                ItemStack grace = new ItemStack(Material.BOW, 1, (byte) 0);
                ItemMeta gracemeta = grace.getItemMeta();
                gracemeta.setDisplayName(ChatColor.GOLD + "Grace Period: " + ChatColor.AQUA + ChatColor.BOLD + gracePeriod + " Minutes");
                List<String> gracelore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                gracemeta.setLore(gracelore);
                grace.setItemMeta(gracemeta);

                ItemStack flint = new ItemStack(Material.FLINT, 1, (byte) 0);
                ItemMeta flintmeta = flint.getItemMeta();
                flintmeta.setDisplayName(ChatColor.GOLD + "Flint Rates: " + ChatColor.AQUA + ChatColor.BOLD + flintRate + "%");
                List<String> flintlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                flintmeta.setLore(flintlore);
                flint.setItemMeta(flintmeta);

                Potion heal = new Potion(PotionType.INSTANT_HEAL, 1);
                ItemStack finalheal = heal.toItemStack(1);
                ItemMeta finalmeta = finalheal.getItemMeta();
                finalmeta.setDisplayName(ChatColor.GOLD + "Final Heal: " + ChatColor.AQUA + ChatColor.BOLD + finalHeal + " Minutes");
                List<String> finallore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                finalmeta.setLore(finallore);
                finalheal.setItemMeta(finalmeta);


                // make inventory bigger and add meetup and strength1 and strength2
                ItemStack meet = new ItemStack(Material.SKULL_ITEM, 1, (byte) 0);
                ItemMeta meetmeta = meet.getItemMeta();
                meetmeta.setDisplayName(ChatColor.GOLD + "Meetup: " + ChatColor.AQUA + ChatColor.BOLD + meetUp + " Minutes");
                List<String> meetlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                meetmeta.setLore(meetlore);
                meet.setItemMeta(meetmeta);

                Potion str1 = new Potion(PotionType.STRENGTH, 1);
                ItemStack strI = str1.toItemStack(1);
                ItemMeta strmeta = strI.getItemMeta();

                if (strength1)
                {
                    strmeta.setDisplayName(ChatColor.GOLD + "Strength 1: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    strmeta.setDisplayName(ChatColor.GOLD + "Strength 1: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> strlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                strmeta.setLore(strlore);
                strI.setItemMeta(strmeta);

                Potion str2 = new Potion(PotionType.STRENGTH, 2);
                ItemStack strII = str2.toItemStack(1);
                ItemMeta str2meta = strII.getItemMeta();

                if (strength2)
                {
                    str2meta.setDisplayName(ChatColor.GOLD + "Strength 2: " + ChatColor.GREEN + ChatColor.BOLD + "On");
                }
                else
                {
                    str2meta.setDisplayName(ChatColor.GOLD + "Strength 2: " + ChatColor.RED + ChatColor.BOLD + "Off");
                }
                List<String> str2lore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to turn On or Off.");
                str2meta.setLore(str2lore);
                strII.setItemMeta(str2meta);

                ItemStack lateScat = new ItemStack(Material.WATCH, 1, (byte) 0);
                ItemMeta latemeta = lateScat.getItemMeta();
                latemeta.setDisplayName(ChatColor.GOLD + "LateScatter: " + ChatColor.AQUA + ChatColor.BOLD + latescatter + " Minutes");
                List<String> latelore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                latemeta.setLore(latelore);
                lateScat.setItemMeta(latemeta);

                ItemStack first = new ItemStack(Material.COMPASS, 1, (byte) 0);
                ItemMeta firstmeta = first.getItemMeta();
                firstmeta.setDisplayName(ChatColor.GOLD + "First Shrink: " + ChatColor.AQUA + ChatColor.BOLD + firstShrink + " Minutes");
                List<String> firstlore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                firstmeta.setLore(firstlore);
                first.setItemMeta(firstmeta);

                ItemStack shrinkInt = new ItemStack(Material.ANVIL, 1, (byte) 0);
                ItemMeta shrinkmeta = shrinkInt.getItemMeta();
                shrinkmeta.setDisplayName(ChatColor.GOLD + "Shrink Interval: " + ChatColor.AQUA + ChatColor.BOLD + shrinkInterval + " Minutes");
                List<String> shrinklore = Arrays.asList("" + ChatColor.AQUA + ChatColor.BOLD + "LeftClick to Increase, RightClick to Decrease.");
                shrinkmeta.setLore(shrinklore);
                shrinkInt.setItemMeta(shrinkmeta);

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
                i.setItem(10, apple);
                i.setItem(11, startfood);
                i.setItem(12, flint);
                i.setItem(13, speed1);
                i.setItem(14, speed2);
                i.setItem(15, strI);
                i.setItem(16, strII);
                i.setItem(17, filler);
                i.setItem(18, filler);
                i.setItem(19, meet);
                i.setItem(20, grace);
                i.setItem(21, finalheal);
                i.setItem(22, border);
                i.setItem(23, nborder);
                i.setItem(24, first);
                i.setItem(25, shrinkInt);
                i.setItem(26, filler);
                i.setItem(27, filler);
                i.setItem(28, teamsize);
                i.setItem(29, teamDmg);
                i.setItem(30, cross);
                i.setItem(31, pearl);
                i.setItem(32, goldenHeads);
                i.setItem(33, godApple);
                i.setItem(34, horse);
                i.setItem(35, filler);
                i.setItem(36, filler);
                i.setItem(37, strip);
                i.setItem(38, poke);
                i.setItem(39, roller);
                i.setItem(40, stalking);
                i.setItem(41, sky);
                i.setItem(42, steal);
                i.setItem(43, run);
                i.setItem(44, filler);
                i.setItem(45, filler);
                i.setItem(46, nether);
                i.setItem(47, fire);
                i.setItem(48, book);
                i.setItem(49, absorp);
                i.setItem(50, pMobs);
                i.setItem(51, lateScat);
                i.setItem(53, filler);
                i.setItem(54, filler);
                i.setItem(55, filler);
                i.setItem(56, filler);
                i.setItem(57, filler);
                i.setItem(58, filler);
                i.setItem(59, filler);
                i.setItem(60, filler);
                i.setItem(61, filler);
                i.setItem(62, filler);

            }

        }.runTaskTimer(plugin, 0, 1);

        p.openInventory(i);
    }
}