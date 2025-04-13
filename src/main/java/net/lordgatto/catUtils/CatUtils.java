package net.lordgatto.catUtils;


import com.samjakob.spigui.SpiGUI;
import net.lordgatto.catUtils.AdminChat.AdminChatHandler;
import net.lordgatto.catUtils.Special.listeners.SpecialItemListenerBlock;
import net.lordgatto.catUtils.Special.listeners.SpecialItemListenerEntyty;
import net.lordgatto.catUtils.commands.commands;
import net.lordgatto.catUtils.invincibility.invincibilityListener;
import net.lordgatto.catUtils.vanish.vanishHandler;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CatUtils extends JavaPlugin {

    public static CatUtils plugin;
    public static SpiGUI spiGUI;
    public static FileConfiguration config;
    public static Server server;
    public static Logger logger;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();

        spiGUI = new SpiGUI(this);
        config = getConfig();
        plugin = this;
        server = getServer();
        logger = getLogger();


        if (config.getBoolean("version_check")) {
            String latest = null;
            try {
                latest = new String(java.net.http.HttpClient.newHttpClient().send(java.net.http.HttpRequest.newBuilder(java.net.URI.create("https://lordgatto.github.io/CatUtils/latest.txt+")).GET().build(), java.net.http.HttpResponse.BodyHandlers.ofString()).body());

                if (!(getDescription().getVersion() == latest)) {
                    logger.warning("you are using an old version of CatUtils(current: " + getDescription().getVersion() + ")(latest: " + latest + ")");
                } else {
                    logger.info("you are using the latest version of CatUtils");
                }

            } catch (Exception e) {
                logger.warning("CatUtils wasen't able to fetch the latest version");
            }
        }

        this.getCommand("mode").setExecutor(new commands.GameModeCommand());
        this.getCommand("GUItest").setExecutor(new commands.TestGuiCommand());
        this.getCommand("admin").setExecutor(new commands.AdminMenuCommand());

        if (config.getBoolean("special_items_enabled")) {
            getServer().getPluginManager().registerEvents(new SpecialItemListenerEntyty(), this);
            getServer().getPluginManager().registerEvents(new SpecialItemListenerBlock(), this);
            this.getCommand("getSpecialItem").setExecutor(new commands.GetItemWithSpecialTag());
        }

        if (config.getBoolean("vanish_enabled")) {
            logger.info("vanish enabled");
            this.getCommand("vanish").setExecutor(new commands.vanishCommand());
            getServer().getPluginManager().registerEvents(new vanishHandler(), this);
        }

        if (config.getBoolean("invincibility_enabled")) {
            logger.info("invincibility enabled");
            this.getCommand("invincible").setExecutor(new commands.invincibilityCommand());
            getServer().getPluginManager().registerEvents(new invincibilityListener(), this);
        }

        if (config.getBoolean("AdminChat_enabled")) {
            logger.info("adminchat enabled");
            this.getCommand("adminChat").setExecutor(new commands.adminChatCommand());
            getServer().getPluginManager().registerEvents(new AdminChatHandler(), this);
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}