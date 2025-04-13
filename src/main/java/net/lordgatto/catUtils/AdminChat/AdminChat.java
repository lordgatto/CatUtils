package net.lordgatto.catUtils.AdminChat;

import net.lordgatto.catUtils.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class AdminChat {
    private static final HashSet<UUID> players = new HashSet<>();

    public static void add(UUID uuid) {
        players.add(uuid);
    }

    public static boolean isEnabledInFile(Player target) {
        try {
            return ConfigManager.getBoolean("adminChat." + target.getUniqueId().toString());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isEnabled(Player target) {
        return players.contains(target.getUniqueId());
    }

    public static void toggle(Player target) {
        if (isEnabled(target)) {
            ConfigManager.setConfig("adminChat." + target.getUniqueId().toString(), false);
            players.remove(target.getUniqueId());
        } else {
            ConfigManager.setConfig("adminChat." + target.getUniqueId().toString(), true);
            players.add(target.getUniqueId());
        }
    }

    public static void sendMessageToAdminChat(Player sender, String message) {
        List<UUID> players_list = new ArrayList<>(players);
        for (int i = 0; i<players.size(); i++) {
            Player player = Bukkit.getPlayer(players_list.get(i));
            if (player != null) {
                player.sendMessage(ChatColor.YELLOW + "[AdminChat]<" + sender.getDisplayName() + "> " + message);
            }
        }
    }
}
