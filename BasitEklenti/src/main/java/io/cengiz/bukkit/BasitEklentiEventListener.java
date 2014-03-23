package io.cengiz.bukkit;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Bu dosya Özgür Yazılım ve Linux Günleri 2014 için hazırlanmıştır.
 *
 * @author cengiz.can@kartaca.com
 */
public class BasitEklentiEventListener implements Listener {

    private BasitEklenti plugin;

    public BasitEklentiEventListener(BasitEklenti plugin) {
        this.plugin = plugin;
    }

    private void notifyAboutCurse(Player player) {
        player
            .getWorld()
            .playSound(player.getLocation(), Sound.BLAZE_DEATH, 1, 0);

        player.sendMessage("Laneti yaşıyorsun!");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (!(e instanceof EntityDamageByEntityEvent)) {
            return;
        }

        EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;

        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();
        Entity entity = event.getEntity();

        if (!(entity instanceof LivingEntity)) {
            return;
        }

        LivingEntity livingEntity = (LivingEntity) entity;
        Location location = livingEntity.getLocation();

        World world = player.getWorld();
        world.strikeLightning(location);
        livingEntity.damage(Double.MAX_VALUE);
        world.getBlockAt(location).setType(Material.GOLD_BLOCK);
        notifyAboutCurse(player);
    }

    @EventHandler
    public void onDeneme(PlayerItemHeldEvent event) {
        // Bukkit.broadcastMessage(event.getEventName());
    }

    @EventHandler
    public void onDeneme(PlayerPickupItemEvent event) {
        // Bukkit.broadcastMessage(event.getEventName());
    }

    @EventHandler
    public void onDeneme(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!plugin.isCursed(player.getName())) {
            return;
        }

        Block block = event.getClickedBlock();

        if (block == null || block.getType() == Material.AIR
            || block.getType() == Material.GOLD_BLOCK) {
            return;
        }

        block.setType(Material.GOLD_BLOCK);
        notifyAboutCurse(player);
    }

}
