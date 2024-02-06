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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Enchanter implements CommandExecutor, Listener {
    public static Inventory inventory2 = Bukkit.createInventory(null, 27, "Enchanter");
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) {
            Player player = Bukkit.getPlayer(args[0]);

            //Creare un itemstack
            ItemStack panel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

            //ItemMeta serve per definire l'item
            ItemMeta panelmeta = panel.getItemMeta();
            panelmeta.setDisplayName(" ");
            panel.setItemMeta(panelmeta);
            inventory2.setItem(1, panel);
            inventory2.setItem(2, panel);
            inventory2.setItem(3, panel);
            inventory2.setItem(4, panel);
            inventory2.setItem(5, panel);
            inventory2.setItem(6, panel);
            inventory2.setItem(7, panel);
            inventory2.setItem(8, panel);
            inventory2.setItem(9, panel);
            inventory2.setItem(0, panel);
            inventory2.setItem(17, panel);
            inventory2.setItem(18, panel);
            inventory2.setItem(19, panel);
            inventory2.setItem(20, panel);
            inventory2.setItem(21, panel);
            inventory2.setItem(22, panel);
            inventory2.setItem(23, panel);
            inventory2.setItem(24, panel);
            inventory2.setItem(25, panel);
            inventory2.setItem(26, panel);

            ItemStack comingsoon = new ItemStack(Material.PAPER);
            ItemMeta csmeta = comingsoon.getItemMeta();
            csmeta.setDisplayName("Coming Soon");
            comingsoon.setItemMeta(csmeta);
            comingsoon.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 10);

            inventory2.setItem(10, comingsoon);
            inventory2.setItem(11, comingsoon);
            inventory2.setItem(12, comingsoon);
            inventory2.setItem(13, comingsoon);
            inventory2.setItem(14, comingsoon);
            inventory2.setItem(15, comingsoon);
            inventory2.setItem(16, comingsoon);

            //Apri l'inventario al Player
            player.openInventory(inventory2);
        }
        else {
            Player player = (Player) sender;
            if(player.hasPermission("pvpmania.enchanter")) {

                //Creare un itemstack
                ItemStack panel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

                //ItemMeta serve per definire l'item
                ItemMeta panelmeta = panel.getItemMeta();
                panelmeta.setDisplayName(" ");
                panel.setItemMeta(panelmeta);
                inventory2.setItem(1, panel);
                inventory2.setItem(2, panel);
                inventory2.setItem(3, panel);
                inventory2.setItem(4, panel);
                inventory2.setItem(5, panel);
                inventory2.setItem(6, panel);
                inventory2.setItem(7, panel);
                inventory2.setItem(8, panel);
                inventory2.setItem(9, panel);
                inventory2.setItem(0, panel);
                inventory2.setItem(17, panel);
                inventory2.setItem(18, panel);
                inventory2.setItem(19, panel);
                inventory2.setItem(20, panel);
                inventory2.setItem(21, panel);
                inventory2.setItem(22, panel);
                inventory2.setItem(23, panel);
                inventory2.setItem(24, panel);
                inventory2.setItem(25, panel);
                inventory2.setItem(26, panel);

                ItemStack comingsoon = new ItemStack(Material.PAPER);
                ItemMeta csmeta = comingsoon.getItemMeta();
                csmeta.setDisplayName("Coming Soon");
                comingsoon.setItemMeta(csmeta);
                comingsoon.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 10);

                inventory2.setItem(10, comingsoon);
                inventory2.setItem(11, comingsoon);
                inventory2.setItem(12, comingsoon);
                inventory2.setItem(13, comingsoon);
                inventory2.setItem(14, comingsoon);
                inventory2.setItem(15, comingsoon);
                inventory2.setItem(16, comingsoon);

                //Apri l'inventario al Player
                player.openInventory(inventory2);
            }
            else {
                player.sendMessage("§b§l[!]§fSorry you cant perform this command §b§l[!]");
            }
        }
        return true;
    }
}
