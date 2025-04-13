package net.lordgatto.catUtils.AdminChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class AdminChatHandler implements Listener {
    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (AdminChat.isEnabled(player)) {
            event.setCancelled(true);
            AdminChat.sendMessageToAdminChat(player, message);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (AdminChat.isEnabledInFile(event.getPlayer())) {
            AdminChat.add(event.getPlayer().getUniqueId());
            event.getPlayer().sendMessage(ChatColor.YELLOW + "[CatUtils] you are still in the admin chat " + event.getPlayer().getDisplayName());
        }
    }
}
