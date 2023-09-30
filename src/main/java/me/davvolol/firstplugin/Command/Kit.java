package me.davvolol.firstplugin.Command;

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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.command.CommandExecutor;
import java.util.Collections;

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
                ItemMeta kitmeta = kit1.getItemMeta();
                kitmeta.setDisplayName("Normal");
                kit1.setItemMeta(kitmeta);
                inventory.setItem(10, kit1);

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
            else{
                player.sendMessage(ChatColor.RED + " " + ChatColor.BOLD + "Sorry! " + ChatColor.WHITE + "You cant perform this command here." );
            }
        }
        if(!(sender instanceof Player)) {
            player.sendMessage(ChatColor.RED + " " + ChatColor.BOLD + "Sorry! " + ChatColor.WHITE + "You need to be a player to perform this command!" );
        }
        return true;
    }

}
