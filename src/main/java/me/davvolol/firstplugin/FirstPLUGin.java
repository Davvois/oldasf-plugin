package me.davvolol.firstplugin;

import me.davvolol.firstplugin.Command.*;
import net.luckperms.api.LuckPerms;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import javax.lang.model.element.Name;
import javax.swing.text.html.parser.Entity;

import java.util.Random;

import static me.davvolol.firstplugin.Command.Blacksmith.inventory1;
import static me.davvolol.firstplugin.Command.Enchanter.inventory2;
import static me.davvolol.firstplugin.Command.Bazaar.inventory3;


public final class FirstPLUGin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[PVPManiaPlugin] The Plugin has started");
        getServer().getPluginManager().registerEvents(this, this);
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();
        }

        this.getCommand("pmac").setExecutor(new PMAC());
        this.getCommand("ffakit").setExecutor(new Kit());
        this.getCommand("enchanter").setExecutor(new Enchanter());
        this.getCommand("blacksmith").setExecutor(new Blacksmith());
        this.getCommand("bazaar").setExecutor(new Bazaar());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[PVPManiaPlugin] The Plugin has stopped");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        World world = Bukkit.getWorld("ul_lobby");
        Location location = new Location(world, 0.5, 1, 0.5);
        player.teleport(location);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Kits!")) {
            event.setCancelled(true);
            if (event.isLeftClick() || event.isRightClick()) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Normal")) {
                    ItemStack helmet = new ItemStack(Material.IRON_HELMET);
                    ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                    ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                    ItemStack boots = new ItemStack(Material.IRON_BOOTS);
                    ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);

                    player.getInventory().setItem(0, helmet);
                    player.getInventory().setItem(1, chestplate);
                    player.getInventory().setItem(2, leggings);
                    player.getInventory().setItem(3, boots);
                    player.getInventory().setItem(4, sword);
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3f, 1f);
                    player.sendMessage(ChatColor.BLUE + "[" + ChatColor.AQUA +"PVPManiaX" + ChatColor.BLUE + "]" + " Successfully Claimed Kit " + ChatColor.AQUA + "Normal!");
                }
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
    }
}
