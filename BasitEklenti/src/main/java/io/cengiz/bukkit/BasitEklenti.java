package io.cengiz.bukkit;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Bu dosya Özgür Yazılım ve Linux Günleri 2014 için hazırlanmıştır.
 *
 * @author cengiz.can@kartaca.com
 */
public class BasitEklenti extends JavaPlugin {

    private final BasitEklentiCommandExecutor commandExecutor =
        new BasitEklentiCommandExecutor(this);
    private final BasitEklentiEventListener eventListener =
        new BasitEklentiEventListener(this);
    private final Set<String> cursedNames = new HashSet<String>();

    public void onDisable() {
        // Şimdilik boş
    }

    public void onEnable() {
        PluginManager pm = this.getServer().getPluginManager();
        getCommand("lanetle").setExecutor(commandExecutor);
        pm.registerEvents(eventListener, this);
    }

    public boolean isCursed(String name) {
        return cursedNames.contains(name);
    }

    public void addToCursed(String name) {
        cursedNames.add(name);
    }

    public void tellBadNewsToPlayer(Player player, String message) {
        player.sendMessage(ChatColor.RED + message + ChatColor.RESET);
    }
}
