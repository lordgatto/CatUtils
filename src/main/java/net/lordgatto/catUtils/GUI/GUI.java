package net.lordgatto.catUtils.GUI;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;
import net.lordgatto.catUtils.CatUtils;
import net.lordgatto.catUtils.Special.SpecialItem;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUI {
    // 1 ROW = 9 SLOTS


    public static SGButton void_button = new SGButton(new ItemBuilder(Material.AIR).build());


    public static void TestGUI(Player player) {
        SGMenu menu = CatUtils.spiGUI.create("get item", 3);

        SGButton testItem = new SGButton(
                new ItemBuilder(Material.WOODEN_SWORD).build()
        ).withListener((InventoryClickEvent event) -> {
            event.getWhoClicked().getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
        });

        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);

        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);

        menu.addButton(testItem);

        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);

        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);

        player.openInventory(menu.getInventory());
    }

    public static void GamemodeGUI(Player player) {
        SGMenu menu = CatUtils.spiGUI.create("gamemode menu", 3);

        SGButton spectator = new SGButton(
                new ItemBuilder(Material.ENDER_EYE).name("Spectator").build()
        ).withListener((InventoryClickEvent event) -> {
            event.getWhoClicked().setGameMode(GameMode.SPECTATOR);
            event.getWhoClicked().closeInventory();
        });

        SGButton adventure = new SGButton(
                new ItemBuilder(Material.SPYGLASS).name("Adventure").build()
        ).withListener((InventoryClickEvent event) -> {
            event.getWhoClicked().setGameMode(GameMode.ADVENTURE);
            event.getWhoClicked().closeInventory();
        });

        SGButton survival = new SGButton(
                new ItemBuilder(Material.DIAMOND_SWORD).name("Survival").build()
        ).withListener((InventoryClickEvent event) -> {
            event.getWhoClicked().setGameMode(GameMode.SURVIVAL);
            event.getWhoClicked().closeInventory();
        });

        SGButton creative = new SGButton(
                new ItemBuilder(Material.COMMAND_BLOCK).name("Creative").build()
        ).withListener((InventoryClickEvent event) -> {
            event.getWhoClicked().setGameMode(GameMode.CREATIVE);
            event.getWhoClicked().closeInventory();
        });

        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);

        menu.addButton(spectator);

        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);

        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);


        menu.addButton(adventure);

        menu.addButton(GUI.void_button);

        menu.addButton(survival);

        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);

        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);

        menu.addButton(creative);

        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);
        menu.addButton(GUI.void_button);

        player.openInventory(menu.getInventory());
    }

    public static void SpecialItemsGUI(Player player) {
        SGMenu page_1 = CatUtils.spiGUI.create("special items tab", 3);

        SGButton lighting_sword = new SGButton(
                SpecialItem.make_special_display(Material.GOLDEN_SWORD, ChatColor.YELLOW, "lighting sword", "spawn lighting", ChatColor.YELLOW)
        ).withListener((InventoryClickEvent event) -> {
            event.getWhoClicked().getInventory().addItem(SpecialItem.make_special(Material.GOLDEN_SWORD, ChatColor.YELLOW, "lighting sword", "spawn lighting", ChatColor.YELLOW, SpecialItem.SpecialItems.LIGHTING));
            event.getWhoClicked().closeInventory();
        });

        SGButton fireball_stick = new SGButton(
                SpecialItem.make_special_display(Material.BLAZE_ROD, ChatColor.RED, "fireball stick", "spawn fireball on left-click", ChatColor.RED)
        ).withListener((InventoryClickEvent event) -> {
            event.getWhoClicked().getInventory().addItem(SpecialItem.make_special(Material.BLAZE_ROD, ChatColor.RED, "fireball stick", "spawn fireball on left-click", ChatColor.RED, SpecialItem.SpecialItems.FIRE_BALL));
            event.getWhoClicked().closeInventory();
        });

        SGButton poison_ball = new SGButton(
                SpecialItem.make_special_display(Material.SLIME_BALL, ChatColor.DARK_GREEN, "poisonus ball", "poison attacked entity", ChatColor.DARK_GREEN)
        ).withListener((InventoryClickEvent event) -> {
            event.getWhoClicked().getInventory().addItem(SpecialItem.make_special(Material.SLIME_BALL, ChatColor.DARK_GREEN, "poisonus ball", "poison attacked entity", ChatColor.DARK_GREEN, SpecialItem.SpecialItems.POISON));
            event.getWhoClicked().closeInventory();
        });

        page_1.addButton(lighting_sword);
        page_1.addButton(fireball_stick);
        page_1.addButton(poison_ball);

        player.openInventory(page_1.getInventory());
    }
}