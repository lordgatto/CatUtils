package net.lordgatto.catUtils.vanish;


import net.lordgatto.catUtils.CatUtils;
import net.lordgatto.catUtils.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

public class vanish {
    private static final HashSet<UUID> players = new HashSet<>();

    public static void add(UUID uuid) {
        players.add(uuid);
    }

    public static void remove(UUID uuid) {
        players.remove(uuid);
    }

    public static boolean isEnabledInConfig(Player player) {
        try {
            return ConfigManager.getBoolean("vanish." + player.getUniqueId());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isEnabled(Player player) {
        return players.contains(player.getUniqueId());
    }

    public static void toggle(Player player) {
        if (isEnabled(player)) {
            players.remove(player.getUniqueId());
            ConfigManager.setConfig("vanish." + player.getUniqueId(), false);
        } else {
            players.add(player.getUniqueId());  // Add player to the list when toggling on
            ConfigManager.setConfig("vanish." + player.getUniqueId(), true);  // Set the config to true
        }
    }

    public static void HideFromPlayer(Player target) {
        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {  // Make sure the player is online before hiding them
                target.hidePlayer(CatUtils.plugin, player);
            }
        }
    }

    public static void ShowFromPlayer(Player target, Player show) {
        if (!isEnabled(show)) {  // Show the player only if they're not vanished
            target.showPlayer(CatUtils.plugin, show);
        }
    }

    public static void update(Player updated) {
        if (isEnabled(updated)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!isEnabled(player)) {
                    HideFromPlayer(player);  // Hide the vanished player from others
                }
            }
            CatUtils.server.broadcastMessage(ChatColor.YELLOW + updated.getDisplayName() + " left the game");
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!isEnabled(player)) {
                    ShowFromPlayer(player, updated);  // Show the player to others when they're not vanished
                }
            }
            CatUtils.server.broadcastMessage(ChatColor.YELLOW + updated.getDisplayName() + " joined the game");
        }
    }

    public static void updateOnJoin(Player updated) {
        if (isEnabled(updated)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!isEnabled(player)) {
                    HideFromPlayer(player);  // Hide the vanished player from others
                }
            }
        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!isEnabled(player)) {
                    ShowFromPlayer(player, updated);  // Show the player to others when they're not vanished
                }
            }
        }
    }
}
