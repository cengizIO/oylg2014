package io.cengiz.bukkit;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Competition {

    private BirazDahaEklenti plugin;
    private Server server;
    private boolean running = false;
    private int maxAllowedSeconds;
    private Material targetBlock;
    public Set<String> players = new HashSet<>();
    private BukkitRunnable runnable;

    public Competition(BirazDahaEklenti plugin) {
        this.plugin = plugin;
        this.server = plugin.getServer();
        this.runnable = new Counter(plugin);
    }

    private void startTimer() {
        runnable.runTaskTimer(plugin, 0L, 1L);
        server.broadcastMessage("Süre başladı");
        this.running = true;
    }

    public void go() {
        if (maxAllowedSeconds < 1 || targetBlock == null) {
            server.broadcast("Önce ayarları yapmalısınız",
                    Server.BROADCAST_CHANNEL_ADMINISTRATIVE);
            return;
        }

        if (players.size() < 2) {
            server.broadcast("En az 2 oyuncunun tura katılması gerekli",
                    Server.BROADCAST_CHANNEL_ADMINISTRATIVE);
            return;
        }

        server.broadcastMessage("Yarışma başlıyor!");
        server.broadcastMessage("Süreniz: " + maxAllowedSeconds
                + " saniye");
        server.broadcastMessage("Hedefiniz: " + targetBlock.toString());

        for (String playerName : players) {
            server.getPlayer(playerName).sendMessage(
                    "İyi şanslar " + playerName);
        }

        startTimer();

    }

    public void iAmTheGreatest(Player player) {
        runnable.cancel();
        server.broadcastMessage(player.getName() + " oyunu kazandı! ");
        player.sendMessage(ChatColor.GOLD
                + " ((( TEBRİKLER SEN KAZANDIN! ))) "
                + ChatColor.RESET);
        PotionEffect muska = PotionEffectType.SPEED.createEffect(1200, 7);
        player.addPotionEffect(muska);
        this.maxAllowedSeconds = 0;
        this.players.clear();
        this.targetBlock = null;
    }

    public boolean isRunning() {
        return (running);
    }

    protected void setRunning(boolean running) {
        this.running = running;
    }

    public int getMaxAllowedSeconds() {
        return maxAllowedSeconds;
    }

    public void setMaxAllowedSeconds(int maxAllowedSeconds) {
        this.maxAllowedSeconds = maxAllowedSeconds;
    }

    public Material getTargetBlock() {
        return targetBlock;
    }

    public void setTargetBlock(Material targetBlock) {
        this.targetBlock = targetBlock;
    }

    private class Counter extends BukkitRunnable {

        private final BirazDahaEklenti plugin;
        private long counter = 0L;

        public Counter(BirazDahaEklenti plugin) {
            this.plugin = plugin;
        }

        @Override
        public void run() {
            long max = plugin.getCompetition().getMaxAllowedSeconds() * 20L;
            counter++;

            long delta = max - counter;

            if (delta <= 200 && delta % 20 == 0) {
                server.broadcastMessage(ChatColor.RED + "Son " + (delta / 20)
                        + " saniye!!!" + ChatColor.RESET);
            }

            if (counter >= max) {
                plugin.getServer().broadcastMessage("Bitti!");
                plugin.getCompetition().setRunning(false);
                plugin.getServer().getScheduler().cancelTasks(plugin);
                this.cancel();
            }
        }

    }

}
