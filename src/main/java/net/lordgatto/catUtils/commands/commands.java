package net.lordgatto.catUtils.commands;

import net.lordgatto.catUtils.AdminChat.AdminChat;
import net.lordgatto.catUtils.CatUtils;
import net.lordgatto.catUtils.GUI.GUI;
import net.lordgatto.catUtils.admin.menu;
import net.lordgatto.catUtils.invincibility.invincibility;
import net.lordgatto.catUtils.vanish.vanish;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commands {
    public static class TestGuiCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                GUI.TestGUI((Player) sender);
            } else {
                sender.sendMessage("command can be used only by players");
            }

            return true;
        }
    }

    public static class GetItemWithSpecialTag implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (sender instanceof Player) {
                GUI.SpecialItemsGUI((Player) sender);
            } else {
                sender.sendMessage("command can be used only by players");
            }

            return true;
        }
    }

    public static class GameModeCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
            if (sender instanceof Player) {
                GUI.GamemodeGUI((Player) sender);
            } else {
                sender.sendMessage("command can be used only by players");
            }

            return true;
        }
    }

    public static class AdminMenuCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                menu.AdminMenu((Player) sender);
            } else {
                sender.sendMessage("command can be used only by players");
            }

            return true;
        }
    }

    public static class adminChatCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("command can be used only by players");
            }

            Player player = (Player) sender;

            AdminChat.toggle(player);
            if (AdminChat.isEnabled(player)) {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned adminChat on");
                player.sendMessage(ChatColor.BOLD + "AdminChat: " + ChatColor.GREEN + "on");
            } else {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned adminChat off");
                player.sendMessage(ChatColor.BOLD + "AdminChat: " + ChatColor.RED + "off");
            }
            player.closeInventory();
            return true;
        }
    }

    public static class invincibilityCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("command can be used only by players");
            }

            Player player = (Player) sender;

            invincibility.toggle(player);
            if (invincibility.isEnabled(player)) {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned invincibility on");
                player.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "on");
            } else {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned invincibility off");
                player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "off");
            }

            return true;
        }
    }

    public static class vanishCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("command can be used only by players");
            }

            Player player = (Player) sender;

            vanish.toggle(player);
            vanish.update(player);
            if (vanish.isEnabled(player)) {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned vanish on");
                player.sendMessage(ChatColor.BOLD + "vanish: " + ChatColor.GREEN + "on");
            } else {
                CatUtils.logger.info(player.getDisplayName() + "(" + player.getUniqueId() + ") turned vanish off");
                player.sendMessage(ChatColor.BOLD + "vanish: " + ChatColor.RED + "off");
            }
            player.closeInventory();

            return true;
        }
    }
}
