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

public class Bazaar implements CommandExecutor, Listener {
    public static Inventory inventory3 = Bukkit.createInventory(null, 27, "Bazaar");
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) {
            Player player = Bukkit.getPlayer(args[0]);

            //Creare l'inventario in cui null è l'owner (cioe tutti) il size dei slot e il nome


            //Creare un itemstack
            ItemStack panel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

            //ItemMeta serve per definire l'item
            ItemMeta panelmeta = panel.getItemMeta();
            panelmeta.setDisplayName(" ");
            panel.setItemMeta(panelmeta);
            inventory3.setItem(1, panel);
            inventory3.setItem(2, panel);
            inventory3.setItem(3, panel);
            inventory3.setItem(4, panel);
            inventory3.setItem(5, panel);
            inventory3.setItem(6, panel);
            inventory3.setItem(7, panel);
            inventory3.setItem(8, panel);
            inventory3.setItem(9, panel);
            inventory3.setItem(0, panel);
            inventory3.setItem(17, panel);
            inventory3.setItem(18, panel);
            inventory3.setItem(19, panel);
            inventory3.setItem(20, panel);
            inventory3.setItem(21, panel);
            inventory3.setItem(22, panel);
            inventory3.setItem(23, panel);
            inventory3.setItem(24, panel);
            inventory3.setItem(25, panel);
            inventory3.setItem(26, panel);

            ItemStack comingsoon = new ItemStack(Material.PAPER);
            ItemMeta csmeta = comingsoon.getItemMeta();
            csmeta.setDisplayName("Coming Soon");
            comingsoon.setItemMeta(csmeta);
            comingsoon.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 10);

            inventory3.setItem(10, comingsoon);
            inventory3.setItem(11, comingsoon);
            inventory3.setItem(12, comingsoon);
            inventory3.setItem(13, comingsoon);
            inventory3.setItem(14, comingsoon);
            inventory3.setItem(15, comingsoon);
            inventory3.setItem(16, comingsoon);


            //Apri l'inventario al Player
            player.openInventory(inventory3);
        }
        else {
            Player player = (Player) sender;
            if(player.hasPermission("pvpmania.bazaar")) {

                //Creare un itemstack
                ItemStack panel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

                //ItemMeta serve per definire l'item
                ItemMeta panelmeta = panel.getItemMeta();
                panelmeta.setDisplayName(" ");
                panel.setItemMeta(panelmeta);
                inventory3.setItem(1, panel);
                inventory3.setItem(2, panel);
                inventory3.setItem(3, panel);
                inventory3.setItem(4, panel);
                inventory3.setItem(5, panel);
                inventory3.setItem(6, panel);
                inventory3.setItem(7, panel);
                inventory3.setItem(8, panel);
                inventory3.setItem(9, panel);
                inventory3.setItem(0, panel);
                inventory3.setItem(17, panel);
                inventory3.setItem(18, panel);
                inventory3.setItem(19, panel);
                inventory3.setItem(20, panel);
                inventory3.setItem(21, panel);
                inventory3.setItem(22, panel);
                inventory3.setItem(23, panel);
                inventory3.setItem(24, panel);
                inventory3.setItem(25, panel);
                inventory3.setItem(26, panel);

                ItemStack comingsoon = new ItemStack(Material.PAPER);
                ItemMeta csmeta = comingsoon.getItemMeta();
                csmeta.setDisplayName("Coming Soon");
                comingsoon.setItemMeta(csmeta);
                comingsoon.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 10);

                inventory3.setItem(10, comingsoon);
                inventory3.setItem(11, comingsoon);
                inventory3.setItem(12, comingsoon);
                inventory3.setItem(13, comingsoon);
                inventory3.setItem(14, comingsoon);
                inventory3.setItem(15, comingsoon);
                inventory3.setItem(16, comingsoon);

                //Apri l'inventario al Player
                player.openInventory(inventory3);
            }
            else {
                player.sendMessage(ChatColor.RED + " " + ChatColor.BOLD + "Sorry! " + ChatColor.WHITE + "You cant perform this command.");
            }
        }
        return true;
    }
}
