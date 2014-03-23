package io.cengiz.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Bu dosya Özgür Yazılım ve Linux Günleri 2014 için hazırlanmıştır.
 *
 * @author cengiz.can@kartaca.com
 */
public class BasitEklentiCommandExecutor implements CommandExecutor {

    private BasitEklenti plugin;

    public BasitEklentiCommandExecutor(BasitEklenti plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
        CommandSender sender,
        Command command,
        String label,
        String[] args
    ) {

        if (args.length < 1) {
            return false;
        }

        String targetPlayerName = String.valueOf(args[0]);

        if (targetPlayerName.length() < 1) {
            return false;
        }

        if (command.getName().equalsIgnoreCase("lanetle")) {

            plugin
                .addToCursed(targetPlayerName);

            plugin
                .getLogger()
                .info(String.format("%s lanetlendi!", targetPlayerName));

            Bukkit
                .getPlayer(targetPlayerName)
                .setDisplayName(ChatColor.RED + "Midas" + ChatColor.RESET);

            return true;
        }

        return false;
    }
}
