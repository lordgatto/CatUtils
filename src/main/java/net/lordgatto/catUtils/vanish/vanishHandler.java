package net.lordgatto.catUtils.vanish;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class vanishHandler implements Listener {
    @EventHandler
    public static void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (vanish.isEnabledInConfig(player)) {
            vanish.add(player.getUniqueId());
            vanish.updateOnJoin(player);
            player.sendMessage(ChatColor.YELLOW + "[CatUtils] you are still in vanish");
            event.setJoinMessage(null);
        } else {
            event.setJoinMessage(player.getDisplayName() + " joined the game");
        }
    }

    @EventHandler
    public static void onLeft(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (vanish.isEnabled(player)) {
            vanish.remove(player.getUniqueId());
            event.setQuitMessage(null);
        } else {
            event.setQuitMessage(player.getDisplayName() + " left the game");
        }
    }
}
