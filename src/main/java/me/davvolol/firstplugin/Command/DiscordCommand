package me.davvolol.firstplugin.Command;

import com.google.common.util.concurrent.Service;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

public class DiscordCommand implements Listener, CommandExecutor{

    // Stabilisco una hashmap dove salvo valori come l'UUID dell'utente e il tempo che sta passando
    private final HashMap<UUID, Long> cooldown;

    //Non so cosa faccia D:
    public DiscordCommand() {
        this.cooldown = new HashMap<>();
    }

    public boolean onCommand (CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player) {
            Player player = (Player) sender;

            //Se l'hashmap non contiene ancora l'UUID dell'utente, allora verrà inserito
            if (!this.cooldown.containsKey(player.getUniqueId())){
                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());

                player.sendMessage("§b§l[!]§f Check out our discord §b§l[!]§f : https://discord.gg/TRsn8Prng5");
            }

            //Se esiste di già, inizia il comando
            else {

                // Il tempo che sta passando è uguale al tempo attuale in millisecondi meno il countdown stabilito precedentemente
                long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                long timeElapsedInSeconds = (20000 - timeElapsed) / 1000;

                //Se il tempo passato è maggiore o uguale a 20 secondi allora il comando può essere eseguito nuovamente
                if (timeElapsed >= 20000){
                    this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    player.sendMessage("§b§l[!]§f Check out our discord §b§l[!]§f : https://discord.gg/TRsn8Prng5");
                }

                //Se il tempo passato e minore di venti secondi non si può eseguire il comando
                else{
                    if (timeElapsed < 19000) {
                        player.sendMessage("§b§l[!]§fWait " + Math.round((float) (20000 - timeElapsed) / 1000) + " seconds before using this command again §b§l[!]");
                    }

                    else{
                        player.sendMessage("§b§l[!]§fWait " + Math.round((float) (20000 - timeElapsed) / 1000) + " second before using this command again §b§l[!]");
                    }
                }
            }
        }
        return true;
    }
}
