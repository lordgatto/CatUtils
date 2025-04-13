package net.lordgatto.catUtils.admin;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;
import net.lordgatto.catUtils.AdminChat.AdminChat;
import net.lordgatto.catUtils.invincibility.invincibility;
import net.lordgatto.catUtils.CatUtils;
import net.lordgatto.catUtils.utils.ConfigManager;
import net.lordgatto.catUtils.vanish.vanish;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

class buttons {
    public static SGButton make_vanish_button(Player player) {

        String text;
        String ChatMessage;
        if (vanish.isEnabled(player)) {
            text = ChatColor.BOLD + "" + ChatColor.GREEN + "on";
            ChatMessage = ChatColor.BOLD + "vanish: " + ChatColor.RED + "off";
        } else {
            text = ChatColor.BOLD + "" + ChatColor.RED + "off";
            ChatMessage = ChatColor.BOLD + "vanish: " + ChatColor.GREEN + "on";
        }

        SGButton button = new SGButton(
                new ItemBuilder(Material.SPYGLASS).name(ChatColor.BOLD + "vanish: " + text).build()
        ).withListener(e -> {
            vanish.toggle(player);
            vanish.update(player);
            if (vanish.isEnabled(player)) {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned vanish on");
            } else {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned vanish off");
            }
            player.sendMessage(ChatMessage);
            player.closeInventory();
        });

        return button;
    }

    public static SGButton make_adminchat_button(Player player) {

        String text;
        String ChatMessage;

        if (AdminChat.isEnabled(player)) {
            text = ChatColor.BOLD + "" + ChatColor.GREEN + "on";
            ChatMessage = ChatColor.BOLD + "AdminChat: " + ChatColor.RED + "off";
        } else {
            text = ChatColor.BOLD + "" + ChatColor.RED + "off";
            ChatMessage = ChatColor.BOLD + "AdminChat: " + ChatColor.GREEN + "on";
        }

        SGButton button = new SGButton(
                new ItemBuilder(Material.COMMAND_BLOCK).name(ChatColor.BOLD + "AdminChat: " + text).build()
        ).withListener(e -> {
            AdminChat.toggle(player);
            if (AdminChat.isEnabled(player)) {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned adminChat on");
            } else {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned adminChat off");
            }
            player.sendMessage(ChatMessage);
            player.closeInventory();
        });

        return button;
    }

    public static SGButton make_invincibility_button(Player player) {

        String text;
        String ChatMessage;

        if (invincibility.isEnabled(player)) {
            text = ChatColor.BOLD + "" + ChatColor.GREEN + "on";
            ChatMessage = "invincible: " + ChatColor.BOLD + "" + ChatColor.RED + "off";
        } else {
            text = ChatColor.BOLD + "" + ChatColor.RED + "off";
            ChatMessage = "invincible: " + ChatColor.BOLD + "" + ChatColor.GREEN + "On";
        }

        SGButton button = new SGButton(
                new ItemBuilder(Material.DIAMOND_SWORD).name(ChatColor.BOLD + "invincibility: " + text).build()
        ).withListener(e -> {
            invincibility.toggle(player);
            if (invincibility.isEnabled(player)) {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned invincibility on");
            } else {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned invincibility on");
            }
            player.sendMessage(ChatMessage);
            player.closeInventory();
        });

        return button;
    }

    public static SGButton make_stop(Player player) {
        SGButton button = new SGButton(
                new ItemBuilder(Material.BARRIER).name(ChatColor.RED + "stop server").build()
        ).withListener(e -> {
            menu.AreYouSureShutdown(player);
        });

        return button;
    }
}

public class menu {
    public static SGButton void_button = new SGButton(new ItemBuilder(Material.AIR).build());

    private static final String name = ChatColor.RED + "A" + ChatColor.YELLOW + "D" + ChatColor.GREEN + "M" + ChatColor.BLUE + "I" + ChatColor.DARK_BLUE + "N" + ChatColor.RESET + " menu";

    public static void AdminMenu(Player player) {
        SGMenu page_1 = CatUtils.spiGUI.create(name, 3);

        if (ConfigManager.getBoolean("invincibility_enabled")) {
            if (player.hasPermission("CatUtils.admin.invincible")) {
                page_1.addButton(buttons.make_invincibility_button(player));
            }
        }
        if (ConfigManager.getBoolean("vanish_enabled")) {
            if (player.hasPermission("CatUtils.admin.vanish")) {
                page_1.addButton(buttons.make_vanish_button(player));
            }
        }
        if (ConfigManager.getBoolean("AdminChat_enabled")) {
            if (player.hasPermission("CatUtils.admin.chat")) {
                page_1.addButton(buttons.make_adminchat_button(player));
            }
        }
        page_1.addButton(buttons.make_stop(player));

        player.openInventory(page_1.getInventory());
    }

    public static void AreYouSureShutdown(Player player) {
        SGMenu menu = CatUtils.spiGUI.create("Are you sure you want to shut down the server?", 1);

        SGButton yes = new SGButton(
                new ItemBuilder(Material.GREEN_CONCRETE).name(ChatColor.BOLD + "" + ChatColor.GREEN + "yes").build()
        ).withListener(e -> {
            CatUtils.server.shutdown();
        });

        SGButton no = new SGButton(
                new ItemBuilder(Material.RED_CONCRETE).name(ChatColor.BOLD + "" + ChatColor.RED + "no").build()
        ).withListener(e -> {
            player.closeInventory();
            AdminMenu(player);
        });

        menu.addButton(void_button);
        menu.addButton(void_button);
        menu.addButton(void_button);

        menu.addButton(no);

        menu.addButton(void_button);

        menu.addButton(yes);

        player.openInventory(menu.getInventory());
    }
}