package me.davvolol.firstplugin.Command;

import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.command.CommandExecutor;
import java.util.Collections;
import java.util.Vector;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import javax.swing.text.Position;

public class Kit implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        World world = Bukkit.getWorld("ul_ffapvp");
        //Convertire il Player nel sender
        Player player = (Player) sender;
        if(sender instanceof Player) {
            if (player.getWorld().equals(world)) {
                    //Creare l'inventario in cui null è l'owner (cioe tutti) il size dei slot e il nome
                    Inventory inventory = Bukkit.createInventory(player, 27, ChatColor.RED + "Kits!");
                    //Creare un itemstack
                    ItemStack kit1 = new ItemStack(Material.LEATHER_CHESTPLATE);
                    //ItemMeta serve per definire l'item
                    ItemMeta kitmeta1 = kit1.getItemMeta();
                    kitmeta1.setDisplayName("§e§lNormal");
                    kit1.setItemMeta(kitmeta1);
                    inventory.setItem(10, kit1);

                    //Creare un itemstack
                    ItemStack kit2 = new ItemStack(Material.ARROW);
                    //ItemMeta serve per definire l'item
                    ItemMeta kitmeta2 = kit2.getItemMeta();
                    kitmeta2.setDisplayName("§b§lArcher");
                    kit2.setItemMeta(kitmeta2);
                    inventory.setItem(11, kit2);

                    //Creare un itemstack
                    ItemStack kit3 = new ItemStack(Material.PITCHER_PLANT);
                    //ItemMeta serve per definire l'item
                    ItemMeta kitmeta3 = kit3.getItemMeta();
                    kitmeta3.setDisplayName("§a§lWizard");
                    kit3.setItemMeta(kitmeta3);
                    inventory.setItem(12, kit3);

                    //Creare un itemstack
                    ItemStack kit4 = new ItemStack(Material.IRON_INGOT);
                    //ItemMeta serve per definire l'item
                    ItemMeta kitmeta4 = kit4.getItemMeta();
                    kitmeta4.setDisplayName("§d§lIron Sentinel");
                    kit4.setItemMeta(kitmeta4);
                    inventory.setItem(13, kit4);

                    //Creare un itemstack
                    ItemStack kit5 = new ItemStack(Material.FEATHER);
                    //ItemMeta serve per definire l'item
                    ItemMeta kitmeta5 = kit5.getItemMeta();
                    kitmeta5.setDisplayName("§c§lSwift Scout");
                    kit5.setItemMeta(kitmeta5);
                    inventory.setItem(14, kit5);

                    ItemStack panel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    ItemMeta panelmeta = panel.getItemMeta();
                    panelmeta.setDisplayName(" ");
                    panel.setItemMeta(panelmeta);
                    inventory.setItem(1, panel);
                    inventory.setItem(2, panel);
                    inventory.setItem(3, panel);
                    inventory.setItem(4, panel);
                    inventory.setItem(5, panel);
                    inventory.setItem(6, panel);
                    inventory.setItem(7, panel);
                    inventory.setItem(8, panel);
                    inventory.setItem(9, panel);
                    inventory.setItem(0, panel);
                    inventory.setItem(17, panel);
                    inventory.setItem(18, panel);
                    inventory.setItem(19, panel);
                    inventory.setItem(20, panel);
                    inventory.setItem(21, panel);
                    inventory.setItem(22, panel);
                    inventory.setItem(23, panel);
                    inventory.setItem(24, panel);
                    inventory.setItem(25, panel);
                    inventory.setItem(26, panel);

                    //Apri l'inventario al Player
                    player.openInventory(inventory);
                }
            }
            else{
                player.sendMessage("§b§l[!]§fSorry you cant perform this command here §b§l[!]");
            }
        if(!(sender instanceof Player)) {
            player.sendMessage("§b§l[!]§f Sorry you need to be a player to perform this command §b§l[!]" );
        }
        return true;
    }

}
