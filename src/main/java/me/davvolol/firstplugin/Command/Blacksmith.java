package me.davvolol.firstplugin.Command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Blacksmith implements CommandExecutor, Listener {
    //Creare l'inventario in cui null è l'owner (cioe tutti) il size dei slot e il nome
    public static Inventory inventory1 = Bukkit.createInventory(null, 27, "Blacksmith");
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) {
            Player player = Bukkit.getPlayer(args[0]);

            //Creare un itemstack
            ItemStack panel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

            //ItemMeta serve per definire l'item
            ItemMeta panelmeta = panel.getItemMeta();
            panelmeta.setDisplayName(" ");
            panel.setItemMeta(panelmeta);
            inventory1.setItem(1, panel);
            inventory1.setItem(2, panel);
            inventory1.setItem(3, panel);
            inventory1.setItem(4, panel);
            inventory1.setItem(5, panel);
            inventory1.setItem(6, panel);
            inventory1.setItem(7, panel);
            inventory1.setItem(8, panel);
            inventory1.setItem(9, panel);
            inventory1.setItem(0, panel);
            inventory1.setItem(17, panel);
            inventory1.setItem(18, panel);
            inventory1.setItem(19, panel);
            inventory1.setItem(20, panel);
            inventory1.setItem(21, panel);
            inventory1.setItem(22, panel);
            inventory1.setItem(23, panel);
            inventory1.setItem(24, panel);
            inventory1.setItem(25, panel);
            inventory1.setItem(26, panel);

            ItemStack comingsoon = new ItemStack(Material.PAPER);
            ItemMeta csmeta = comingsoon.getItemMeta();
            csmeta.setDisplayName("Coming Soon");
            comingsoon.setItemMeta(csmeta);
            comingsoon.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 10);

            inventory1.setItem(10, comingsoon);
            inventory1.setItem(11, comingsoon);
            inventory1.setItem(12, comingsoon);
            inventory1.setItem(13, comingsoon);
            inventory1.setItem(14, comingsoon);
            inventory1.setItem(15, comingsoon);
            inventory1.setItem(16, comingsoon);

            //Apri l'inventario al Player
            player.openInventory(inventory1);
        }
        else {
            Player player = (Player) sender;
            if(player.hasPermission("pvpmania.blacksmith")) {
                //Creare un itemstack
                ItemStack panel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

                //ItemMeta serve per definire l'item
                ItemMeta panelmeta = panel.getItemMeta();
                panelmeta.setDisplayName(" ");
                panel.setItemMeta(panelmeta);
                inventory1.setItem(1, panel);
                inventory1.setItem(2, panel);
                inventory1.setItem(3, panel);
                inventory1.setItem(4, panel);
                inventory1.setItem(5, panel);
                inventory1.setItem(6, panel);
                inventory1.setItem(7, panel);
                inventory1.setItem(8, panel);
                inventory1.setItem(9, panel);
                inventory1.setItem(0, panel);
                inventory1.setItem(17, panel);
                inventory1.setItem(18, panel);
                inventory1.setItem(19, panel);
                inventory1.setItem(20, panel);
                inventory1.setItem(21, panel);
                inventory1.setItem(22, panel);
                inventory1.setItem(23, panel);
                inventory1.setItem(24, panel);
                inventory1.setItem(25, panel);
                inventory1.setItem(26, panel);

                ItemStack comingsoon = new ItemStack(Material.PAPER);
                ItemMeta csmeta = comingsoon.getItemMeta();
                csmeta.setDisplayName("Coming Soon");
                comingsoon.setItemMeta(csmeta);
                comingsoon.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 10);

                inventory1.setItem(10, comingsoon);
                inventory1.setItem(11, comingsoon);
                inventory1.setItem(12, comingsoon);
                inventory1.setItem(13, comingsoon);
                inventory1.setItem(14, comingsoon);
                inventory1.setItem(15, comingsoon);
                inventory1.setItem(16, comingsoon);

                //Apri l'inventario al Player
                player.openInventory(inventory1);
            }
            else {
                player.sendMessage(ChatColor.RED + " " + ChatColor.BOLD + "Sorry! " + ChatColor.WHITE + "You cant perform this command.");
            }
        }
        return true;
    }
}
