package io.cengiz.bukkit;

import java.util.concurrent.atomic.AtomicLong;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class Competition {

    private Plugin plugin;
    private Server server;
    private final AtomicLong elapsed = new AtomicLong(0L);
    
    public Competition(Plugin plugin) {
        this.plugin = plugin;
        this.server = plugin.getServer();
    }

    public void startTimer() {
        elapsed.incrementAndGet();
        server
            .getScheduler()
            .scheduleSyncRepeatingTask(
                plugin, 
                new Runnable() {
                    public void run() {
                        elapsed.incrementAndGet();
                    }
                }, 
                0,
                1L
            );
        server.broadcastMessage("Süre başladı");
    }

    public void stopTimer() {
        server.getScheduler().cancelTasks(plugin);
        server.broadcastMessage("Geçen tick: " + elapsed.toString());
        elapsed.set(0L);
    }

}
