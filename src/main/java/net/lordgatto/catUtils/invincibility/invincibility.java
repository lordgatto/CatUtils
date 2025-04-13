package net.lordgatto.catUtils.invincibility;

import net.lordgatto.catUtils.utils.ConfigManager;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

public class invincibility {
    private static final HashSet<UUID> players = new HashSet<>();

    public static void add(UUID uuid) {
        players.add(uuid);
    }

    public static void remove(UUID uuid) {
        players.remove(uuid);
    }

    public static boolean isEnabledInConfig(Player player) {
        return ConfigManager.getBoolean("invincible." + player.getUniqueId());
    }

    public static boolean isEnabled(Player player) {
        return players.contains(player.getUniqueId());
    }

    public static void toggle(Player player) {
        if (isEnabled(player)) {
            ConfigManager.setConfig("invincible." + player.getUniqueId(), false);
            remove(player.getUniqueId());
        } else {
            ConfigManager.setConfig("invincible." + player.getUniqueId(), true);
            add(player.getUniqueId());
        }
    }
}
