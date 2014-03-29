package io.cengiz.bukkit;

import org.bukkit.Server;
import org.bukkit.scheduler.BukkitRunnable;

public class Competition {

    private BirazDahaEklenti plugin;
    private Server server;
    
    public Competition(BirazDahaEklenti plugin) {
        this.plugin = plugin;
        this.server = plugin.getServer();
    }

    private void startTimer() {
        BukkitRunnable runnable = new Counter(plugin);
        runnable.runTaskTimer(plugin, 0L, 1L);
        server.broadcastMessage("Süre başladı");
    }

    public void go() {
        if (plugin.maxAllowedSeconds < 1 || plugin.targetBlock == null) {
            server.broadcast("Önce ayarları yapmalısınız",
                    server.BROADCAST_CHANNEL_ADMINISTRATIVE);
            return;
        }
        
        if (plugin.players.size() < 2) {
            server.broadcast("En az 2 oyuncunun tura katılması gerekli",
                    server.BROADCAST_CHANNEL_ADMINISTRATIVE);
            return;
        }

        server.broadcastMessage("Yarışma başlıyor!");
        server.broadcastMessage("Süreniz: " + plugin.maxAllowedSeconds
                + " saniye");
        server.broadcastMessage("Hedefiniz: " + plugin.targetBlock.toString());

        for (String playerName : plugin.players) {
            server.getPlayer(playerName).sendMessage(
                    "İyi şanslar " + playerName);

        }
 
        startTimer();

    }

}
