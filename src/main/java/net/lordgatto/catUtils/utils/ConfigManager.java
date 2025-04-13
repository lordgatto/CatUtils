package net.lordgatto.catUtils.utils;

import net.lordgatto.catUtils.CatUtils;
import org.bukkit.Statistic;
import org.bukkit.entity.Cat;

public class ConfigManager {
    public static void setConfig(String key, String value) {
        CatUtils.config.set(key, value);
        CatUtils.plugin.saveConfig();
    }

    public static void setConfig(String key, boolean value) {
        CatUtils.config.set(key, value);
        CatUtils.plugin.saveConfig();
    }

    public static void setConfig(String key, int value) {
        CatUtils.config.set(key, value);
        CatUtils.plugin.saveConfig();
    }

    public static void setConfig(String key, float value) {
        CatUtils.config.set(key, value);
        CatUtils.plugin.saveConfig();
    }

    public static void setConfig(String key, double value) {
        CatUtils.config.set(key, value);
        CatUtils.plugin.saveConfig();
    }

    public static boolean getBoolean(String key) {
        return CatUtils.config.getBoolean(key);
    }

    public static String getString(String key) {
        return CatUtils.config.getString(key);
    }

    public static double getNumber(String key) {
        return (double) CatUtils.config.getDouble(key);
    }
}
