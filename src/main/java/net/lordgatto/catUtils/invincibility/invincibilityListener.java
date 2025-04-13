package net.lordgatto.catUtils.invincibility;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class invincibilityListener implements Listener {
    @EventHandler
    public static void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (invincibility.isEnabledInConfig(player)) {
            if (!invincibility.isEnabled(player)) {
                invincibility.add(player.getUniqueId());
            }
            player.sendMessage("[CatUtils] you are still invincible");
        }
    }

    @EventHandler
    public static void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();

        if (invincibility.isEnabled(player)) {
            event.setCancelled(true);
        }
    }
}
