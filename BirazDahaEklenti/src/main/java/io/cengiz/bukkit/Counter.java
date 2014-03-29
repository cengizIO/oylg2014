package io.cengiz.bukkit;

import org.bukkit.scheduler.BukkitRunnable;

public class Counter extends BukkitRunnable {

    private final BirazDahaEklenti plugin;
    private long counter;

    public Counter(BirazDahaEklenti plugin) {
        this.plugin = plugin;
        this.counter = plugin.maxAllowedSeconds * 20L; // 20 "tick" = 1 saniye
    }

    @Override
    public void run() {

        // What you want to schedule goes here
        --counter;

        if (counter <= 0) {
            plugin.getServer().broadcastMessage("Bitti!");
            this.cancel();
        }
    }

}
