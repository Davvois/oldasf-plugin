package me.davvolol.firstplugin.Command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PMAC implements CommandExecutor {

    boolean command = false;

    public PMAC(){
        super();
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if(sender.hasPermission("pvpmania.pmac")) {
            if (command == false) {
                command = true;
                sender.sendMessage("ciao");
            } else {
                command = false;
                sender.sendMessage("addio");
            }
        }
        else {
            sender.sendMessage(ChatColor.RED + " " + ChatColor.BOLD + "Sorry! " + ChatColor.WHITE + "You cant perform this command.");
        }
        return true;
    }

}
