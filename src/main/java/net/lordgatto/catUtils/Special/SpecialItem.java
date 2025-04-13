package net.lordgatto.catUtils.Special;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SpecialItem {
    public static class SpecialItems {
        public static Integer LIGHTING = 0;
        public static Integer FIRE_BALL = 1;
        public static Integer POISON = 2;
    }

    public static ItemStack make_special_display(Material item, ChatColor namecolor, String name, String tooltip, ChatColor tooltip_color) {
        ItemStack ITEM = new ItemStack(item, 1);
        ItemMeta META = ITEM.getItemMeta();

        META.setDisplayName(namecolor+name);
        META.setLore(Arrays.asList(tooltip_color+tooltip));

        ITEM.setItemMeta(META);

        return ITEM;
    }

    public static ItemStack make_special(Material item, ChatColor namecolor, String name, String tooltip, ChatColor tooltip_color, Integer type) {
        ItemStack ITEM = new ItemStack(item);
        ItemMeta META = ITEM.getItemMeta();

        META.setDisplayName(namecolor+name);
        META.setLore(Arrays.asList(tooltip_color+tooltip));

        ITEM.setItemMeta(META);

        NBT.modify(ITEM, nbt -> {
            nbt.setBoolean("special", true);
            nbt.setInteger("specialType", type);
        });

        return ITEM;
    }
}
