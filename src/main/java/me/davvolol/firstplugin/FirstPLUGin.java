package me.davvolol.firstplugin;

import com.google.common.math.Stats;
import com.ibm.icu.text.CaseMap;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flags;
import me.davvolol.firstplugin.Command.*;
import net.luckperms.api.LuckPerms;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.lang.model.element.Name;
import javax.swing.text.html.parser.Entity;

import java.util.*;

import static me.davvolol.firstplugin.Command.Blacksmith.inventory1;
import static me.davvolol.firstplugin.Command.Enchanter.inventory2;
import static me.davvolol.firstplugin.Command.Bazaar.inventory3;

public final class FirstPLUGin extends JavaPlugin implements Listener {

    /* Cooldown for kits */
    private final HashMap<UUID, Long> cooldown;

    public FirstPLUGin() {
        this.cooldown = new HashMap<>();
    }
    /* Cooldown for kits */

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[PVPManiaPlugin] The Plugin has started");
        getServer().getPluginManager().registerEvents(this, this);
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();
        }
        WorldGuard.getInstance();

        this.getCommand("pmac").setExecutor(new PMAC());
        this.getCommand("ffakit").setExecutor(new Kit());
        this.getCommand("enchanter").setExecutor(new Enchanter());
        this.getCommand("blacksmith").setExecutor(new Blacksmith());
        this.getCommand("bazaar").setExecutor(new Bazaar());
        this.getCommand("discord").setExecutor(new DiscordCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[PVPManiaPlugin] The Plugin has stopped");
    }


    // Evento di entrata del giocatore (es. reset della vita, della fame e aggiunta al file .yml del server)
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String pUUID = player.getUniqueId().toString();
        player.getInventory().clear();
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        World world = Bukkit.getWorld("ul_lobby");
        Location location = new Location(world, 0.5, 1, 0.5);
        player.teleport(location);
        player.sendTitle("§x§0§0§f§b§e§4§lW§x§0§d§e§d§e§5§le§x§1§b§d§f§e§7§ll§x§2§8§d§1§e§8§lc§x§3§5§c§3§e§a§lo§x§4§3§b§5§e§b§lm§x§5§0§a§7§e§c§le §x§5§d§9§9§e§e§lt§x§6§b§8§b§e§f§lo §x§7§8§7§e§f§1§lP§x§8§5§7§0§f§2§lV§x§9§3§6§2§f§3§lP§x§a§0§5§4§f§5§lM§x§a§d§4§6§f§6§la§x§b§b§3§8§f§7§ln§x§c§8§2§a§f§9§li§x§d§5§1§c§f§a§la§x§e§3§0§e§f§c§lX§x§f§0§0§0§f§d§l!", "§x§0§0§f§b§e§4§lE§x§1§2§e§8§e§6§ln§x§2§5§d§4§e§8§lj§x§3§7§c§1§e§a§lo§x§4§a§a§e§e§c§ly §x§5§c§9§a§e§e§ly§x§6§f§8§7§f§0§lo§x§8§1§7§4§f§1§lu§x§9§4§6§1§f§3§lr §x§a§6§4§d§f§5§ls§x§b§9§3§a§f§7§lt§x§c§b§2§7§f§9§la§x§d§e§1§3§f§b§ly§x§f§0§0§0§f§d§l!", 10, 150, 10);
        player.playSound(player.getLocation(), Sound.ENTITY_CAT_AMBIENT, 3.0F, 0.5F);

        if (!(player.hasPlayedBefore())){
            EntityType firework = EntityType.FIREWORK;
            world.spawnEntity(player.getLocation(), firework);
        }

        if (!getConfig().contains("Player." + player.getUniqueId().toString())){
            getConfig().set("Player." + pUUID + ".Kills", 0);
            getConfig().set("Player." + pUUID + ".Deaths", 0);
            getConfig().set("Player." + pUUID + ".Coins", 0);
            getConfig().set("Player." + pUUID + ".KD", 0);
            saveConfig();
        }
    }

    // Casino totale, kit e roba varia
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.isLeftClick() || event.isRightClick()) {
            if (event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Kits!")) {
                event.setCancelled(true);
                if (player.getLocation().getY() >= -25 || player.hasPermission("pvpmania.ffakit.bypass")) {
                    //Se l'hashmap non contiene ancora l'UUID dell'utente, allora verrà inserito
                    if (!this.cooldown.containsKey(player.getUniqueId())) {
                        this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§lNormal")) {
                            ItemStack helmet = new ItemStack(Material.IRON_HELMET);
                            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                            ItemStack boots = new ItemStack(Material.IRON_BOOTS);
                            ItemStack sword = new ItemStack(Material.WOODEN_SWORD);

                            //Enchants and meta
                            sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);

                            player.getInventory().clear();
                            player.getInventory().setItem(0, helmet);
                            player.getInventory().setItem(1, chestplate);
                            player.getInventory().setItem(2, leggings);
                            player.getInventory().setItem(3, boots);
                            player.getInventory().setItem(4, sword);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3f, 1f);
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "PVPManiaX" + ChatColor.BLUE + "]" + " Successfully Claimed Kit " + ChatColor.AQUA + "Normal!");
                        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lWizard")) {
                            ItemStack helmet = new ItemStack(Material.IRON_HELMET);
                            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                            ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                            ItemStack staff = new ItemStack(Material.STICK);

                            //Enchants and meta
                            ItemMeta staffMeta = staff.getItemMeta();
                            staff.setItemMeta(staffMeta);
                            staffMeta.setDisplayName("Wizard's Staff");
                            staff.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
                            sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                            chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

                            player.getInventory().clear();
                            player.getInventory().setItem(0, helmet);
                            player.getInventory().setItem(1, chestplate);
                            player.getInventory().setItem(2, leggings);
                            player.getInventory().setItem(3, boots);
                            player.getInventory().setItem(4, sword);
                            player.getInventory().setItem(5, staff);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3f, 1f);
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "PVPManiaX" + ChatColor.BLUE + "]" + " Successfully Claimed Kit " + ChatColor.AQUA + "Wizard!");
                        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§d§lIron Sentinel")) {
                            ItemStack helmet = new ItemStack(Material.IRON_HELMET);
                            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                            ItemStack boots = new ItemStack(Material.IRON_BOOTS);
                            ItemStack sword = new ItemStack(Material.IRON_SWORD);

                            //Enchants and meta
                            sword.addEnchantment(Enchantment.KNOCKBACK, 1);

                            player.getInventory().clear();
                            player.getInventory().setItem(0, helmet);
                            player.getInventory().setItem(1, chestplate);
                            player.getInventory().setItem(2, leggings);
                            player.getInventory().setItem(3, boots);
                            player.getInventory().setItem(4, sword);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3f, 1f);
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "PVPManiaX" + ChatColor.BLUE + "]" + " Successfully Claimed Kit " + ChatColor.AQUA + "Iron Sentinel!");
                        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lSwift Scout")) {
                            ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                            ItemStack chestplate = new ItemStack(Material.GOLDEN_CHESTPLATE);
                            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                            ItemStack boots = new ItemStack(Material.GOLDEN_BOOTS);
                            ItemStack sword = new ItemStack(Material.STONE_SWORD);
                            ItemStack feather = new ItemStack(Material.FEATHER);

                            //Enchants and meta
                            sword.addEnchantment(Enchantment.DURABILITY, 1);
                            sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                            feather.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
                            ItemMeta featherMeta = feather.getItemMeta();
                            featherMeta.setDisplayName("Scout's Feather");
                            feather.setItemMeta(featherMeta);

                            player.getInventory().clear();
                            player.getInventory().setItem(0, helmet);
                            player.getInventory().setItem(1, chestplate);
                            player.getInventory().setItem(2, leggings);
                            player.getInventory().setItem(3, boots);
                            player.getInventory().setItem(4, sword);
                            player.getInventory().setItem(5, feather);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3f, 1f);
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "PVPManiaX" + ChatColor.BLUE + "]" + " Successfully Claimed Kit " + ChatColor.AQUA + "Swift Scout!");
                        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lArcher")) {
                            ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                            ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                            ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                            ItemStack bow = new ItemStack(Material.BOW);
                            ItemStack arrows = new ItemStack(Material.ARROW, 12);

                            //Enchants and meta
                            chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                            leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

                            player.getInventory().clear();
                            player.getInventory().setItem(0, helmet);
                            player.getInventory().setItem(1, chestplate);
                            player.getInventory().setItem(2, leggings);
                            player.getInventory().setItem(3, boots);
                            player.getInventory().setItem(4, sword);
                            player.getInventory().setItem(5, bow);
                            player.getInventory().setItem(6, arrows);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3f, 1f);
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "PVPManiaX" + ChatColor.BLUE + "]" + " Successfully Claimed Kit " + ChatColor.AQUA + "Swift Scout!");
                        }
                    } else {
                        // Il tempo che sta passando è uguale al tempo attuale in millisecondi meno il countdown stabilito precedentemente
                        long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                        //Se il tempo passato è maggiore o uguale a 20 secondi allora il comando può essere eseguito nuovamente
                        if (timeElapsed >= 20000) {
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§lNormal")) {
                                ItemStack helmet = new ItemStack(Material.IRON_HELMET);
                                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                                ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                                ItemStack boots = new ItemStack(Material.IRON_BOOTS);
                                ItemStack sword = new ItemStack(Material.WOODEN_SWORD);

                                //Enchants and meta
                                sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);

                                player.getInventory().clear();
                                player.getInventory().setItem(0, helmet);
                                player.getInventory().setItem(1, chestplate);
                                player.getInventory().setItem(2, leggings);
                                player.getInventory().setItem(3, boots);
                                player.getInventory().setItem(4, sword);
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3f, 1f);
                                player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "PVPManiaX" + ChatColor.BLUE + "]" + " Successfully Claimed Kit " + ChatColor.AQUA + "Normal!");
                            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lWizard")) {
                                ItemStack helmet = new ItemStack(Material.IRON_HELMET);
                                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                                ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                                ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                                ItemStack staff = new ItemStack(Material.STICK);

                                //Enchants and meta
                                ItemMeta staffMeta = staff.getItemMeta();
                                staff.setItemMeta(staffMeta);
                                staffMeta.setDisplayName("Wizard's Staff");
                                staff.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
                                sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                                chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

                                player.getInventory().clear();
                                player.getInventory().setItem(0, helmet);
                                player.getInventory().setItem(1, chestplate);
                                player.getInventory().setItem(2, leggings);
                                player.getInventory().setItem(3, boots);
                                player.getInventory().setItem(4, sword);
                                player.getInventory().setItem(5, staff);
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3f, 1f);
                                player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "PVPManiaX" + ChatColor.BLUE + "]" + " Successfully Claimed Kit " + ChatColor.AQUA + "Wizard!");
                            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§d§lIron Sentinel")) {
                                ItemStack helmet = new ItemStack(Material.IRON_HELMET);
                                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                                ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                                ItemStack boots = new ItemStack(Material.IRON_BOOTS);
                                ItemStack sword = new ItemStack(Material.IRON_SWORD);

                                //Enchants and meta
                                sword.addEnchantment(Enchantment.KNOCKBACK, 1);

                                player.getInventory().clear();
                                player.getInventory().setItem(0, helmet);
                                player.getInventory().setItem(1, chestplate);
                                player.getInventory().setItem(2, leggings);
                                player.getInventory().setItem(3, boots);
                                player.getInventory().setItem(4, sword);
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3f, 1f);
                                player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "PVPManiaX" + ChatColor.BLUE + "]" + " Successfully Claimed Kit " + ChatColor.AQUA + "Iron Sentinel!");
                            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lSwift Scout")) {
                                ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                                ItemStack chestplate = new ItemStack(Material.GOLDEN_CHESTPLATE);
                                ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                                ItemStack boots = new ItemStack(Material.GOLDEN_BOOTS);
                                ItemStack sword = new ItemStack(Material.STONE_SWORD);
                                ItemStack feather = new ItemStack(Material.FEATHER);

                                //Enchants and meta
                                sword.addEnchantment(Enchantment.DURABILITY, 1);
                                sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                                feather.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
                                ItemMeta featherMeta = feather.getItemMeta();
                                featherMeta.setDisplayName("Scout's Feather");
                                feather.setItemMeta(featherMeta);

                                player.getInventory().clear();
                                player.getInventory().setItem(0, helmet);
                                player.getInventory().setItem(1, chestplate);
                                player.getInventory().setItem(2, leggings);
                                player.getInventory().setItem(3, boots);
                                player.getInventory().setItem(4, sword);
                                player.getInventory().setItem(5, feather);
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3f, 1f);
                                player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "PVPManiaX" + ChatColor.BLUE + "]" + " Successfully Claimed Kit " + ChatColor.AQUA + "Swift Scout!");
                            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lArcher")) {
                                ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                                ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                                ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                                ItemStack bow = new ItemStack(Material.BOW);
                                ItemStack arrows = new ItemStack(Material.ARROW, 12);

                                //Enchants and meta
                                chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                                leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

                                player.getInventory().clear();
                                player.getInventory().setItem(0, helmet);
                                player.getInventory().setItem(1, chestplate);
                                player.getInventory().setItem(2, leggings);
                                player.getInventory().setItem(3, boots);
                                player.getInventory().setItem(4, sword);
                                player.getInventory().setItem(5, bow);
                                player.getInventory().setItem(6, arrows);
                                player.closeInventory();
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3f, 1f);
                                player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA + "PVPManiaX" + ChatColor.BLUE + "]" + " Successfully Claimed Kit " + ChatColor.AQUA + "Swift Scout!");
                            }
                        }


                        //Se il tempo passato e minore di venti secondi non si può eseguire il comando
                        else {
                            if (timeElapsed < 19000) {
                                player.sendMessage("§b§l[!]§fWait " + Math.round((float) (20000 - timeElapsed) / 1000) + " seconds §b§l[!]");
                            } else {
                                player.sendMessage("§b§l[!]§fWait " + Math.round((float) (20000 - timeElapsed) / 1000) + " second before claiming this kit again §b§l[!]");
                            }
                        }
                    }
                }
                else {
                    player.sendMessage("§b§l[!]§f You need to be at spawn §b§l[!]");
                }
            }

            if (event.getInventory().equals(inventory1)) {
                event.setCancelled(true);
            }

            if (event.getInventory().equals(inventory2)) {
                event.setCancelled(true);
            }

            if (event.getInventory().equals(inventory3)) {
                event.setCancelled(true);
            }

            if (event.getView().getTitle().equalsIgnoreCase( "Stats")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getType().equals(Material.BARRIER)) {
                    player.closeInventory();
                }
            }
        }
    }

    // Per il kit swift scout
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String world = player.getWorld().getName();

        if (world.equalsIgnoreCase("ul_ffapvp")) {
            if (event.getAction().name().contains("RIGHT")) {
                if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Scout's Feather")) {
                    if (player.hasPotionEffect(PotionEffectType.SPEED)) {
                        return;
                    }
                    player.addPotionEffect(PotionEffectType.SPEED.createEffect(150, 1));
                }
            }
        }
    }

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event){
        Player deadPlayer = event.getEntity();
        String world = deadPlayer.getWorld().getName();

        if (world.equalsIgnoreCase("ul_ffapvp")){
            deadPlayer.getInventory().clear();

            if (deadPlayer.getPlayer() instanceof Player){
                Player killerPlayer = deadPlayer.getKiller();
                if (killerPlayer instanceof Player) {

                    //Get the player's UUID
                    String dUUID = deadPlayer.getUniqueId().toString();
                    String kUUID = killerPlayer.getUniqueId().toString();

                    int kills = getConfig().getInt("Player." + kUUID + ".Kills");
                    int deaths = getConfig().getInt("Player." + dUUID + ".Deaths");
                    int coins = getConfig().getInt("Player." + kUUID + ".Kills") * 25 + 25;
                    float kd = (float) kills / deaths;


                    getConfig().set("Player." + kUUID + ".Kills", kills + 1);
                    getConfig().set("Player." + dUUID + ".Deaths", deaths + 1);
                    getConfig().set("Player." + kUUID + ".Coins", coins);

                    getConfig().set("Player." + kUUID + ".KD", kd);
                    getConfig().set("Player." + dUUID + ".KD", kd);
                    saveConfig();
                }

                else{
                    String dUUID = deadPlayer.getUniqueId().toString();
                    int deaths = getConfig().getInt("Player." + dUUID + ".Deaths");
                    getConfig().set("Player." + dUUID + ".Deaths", deaths + 1);
                }
            }
        }
    }

    // Comando "/stats"
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Main variables
        Player player = (Player) sender;
        String world = player.getWorld().getName();
        String UUID = player.getUniqueId().toString();
        Inventory inventoryStats = Bukkit.createInventory(null, 36, "Stats");

        int killsInt = getConfig().getInt("Player." + UUID + ".Kills");
        int deathsInt = getConfig().getInt("Player." + UUID + ".Deaths");
        int coinsInt = getConfig().getInt("Player." + UUID + ".Coins");
        float kdFloat = getConfig().getInt("Player." + UUID + ".KD");

        if (world.equalsIgnoreCase("ul_ffapvp") && args.length == 0){
            ItemStack panel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta panelMeta = panel.getItemMeta();
            panelMeta.setDisplayName(" ");
            panel.setItemMeta(panelMeta);

            ItemStack kills = new ItemStack(Material.NETHER_WART);

            ItemStack deaths = new ItemStack(Material.BONE);

            ItemStack coins = new ItemStack(Material.GOLD_NUGGET);

            ItemStack kd = new ItemStack(Material.IRON_SWORD);

            // Imposta l'ItemMeta per ogni ItemStack
            ItemMeta killsMeta = kills.getItemMeta();
            killsMeta.setDisplayName("§aKills : §f" + killsInt);
            kills.setItemMeta(killsMeta);

            ItemMeta deathsMeta = deaths.getItemMeta();
            deathsMeta.setDisplayName("§cDeaths : §f" + deathsInt);
            deaths.setItemMeta(deathsMeta);

            ItemMeta coinsMeta = coins.getItemMeta();
            coinsMeta.setDisplayName("§6Coins : §f" + coinsInt);
            coins.setItemMeta(coinsMeta);

            ItemMeta kdMeta = kd.getItemMeta();
            kdMeta.setDisplayName("§bK/D Ratio : §f" + kdFloat);
            kd.setItemMeta(kdMeta);

            // Aggiungi gli ItemStack all'inventario
            inventoryStats.setItem(10, kills);
            inventoryStats.setItem(12, deaths);
            inventoryStats.setItem(14, coins);
            inventoryStats.setItem(16, kd);

            ItemStack exitButton = new ItemStack(Material.BARRIER);
            ItemMeta exitButtonMeta = exitButton.getItemMeta();
            exitButtonMeta.setDisplayName("§a§lExit");
            exitButton.setItemMeta(exitButtonMeta);

            //Panel setting
            inventoryStats.setItem(0, panel);
            inventoryStats.setItem(1, panel);
            inventoryStats.setItem(2, panel);
            inventoryStats.setItem(3, panel);
            inventoryStats.setItem(4, panel);
            inventoryStats.setItem(5, panel);
            inventoryStats.setItem(6, panel);
            inventoryStats.setItem(7, panel);
            inventoryStats.setItem(8, panel);
            inventoryStats.setItem(9, panel);
            inventoryStats.setItem(10, kills);
            inventoryStats.setItem(12, deaths);
            inventoryStats.setItem(14, coins);
            inventoryStats.setItem(16, kd);
            inventoryStats.setItem(17, panel);
            inventoryStats.setItem(18, panel);
            inventoryStats.setItem(26, panel);
            inventoryStats.setItem(27, panel);
            inventoryStats.setItem(28, panel);
            inventoryStats.setItem(29, panel);
            inventoryStats.setItem(30, panel);
            inventoryStats.setItem(31, exitButton);
            inventoryStats.setItem(32, panel);
            inventoryStats.setItem(33, panel);
            inventoryStats.setItem(34, panel);
            inventoryStats.setItem(35, panel);

            player.openInventory(inventoryStats);
        }
        else if (args.length > 1 && world.equalsIgnoreCase("ul_ffapvp")){
            if (player.hasPermission("pvpmania.stats.clear")) {
                if (args[0] == "clear") {
                    Player playerArgs = Bukkit.getPlayer(args[1]);
                    String playerArgsUUID = playerArgs.getUniqueId().toString();

                    getConfig().set("Player." + playerArgsUUID + ".Kills", 0);
                    getConfig().set("Player." + playerArgsUUID + ".Deaths", 0);
                    getConfig().set("Player." + playerArgsUUID + ".Coins", 0);
                    getConfig().set("Player." + playerArgsUUID + ".KD", 0);
                    saveConfig();
                }
            }
        }
        else {
            player.sendMessage(ChatColor.RED + " " + ChatColor.BOLD + "Sorry! " + ChatColor.WHITE + "You cant perform this command here.");
        }
        return true;
    }
}
