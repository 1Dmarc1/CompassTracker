package me.dmarc.compasstracker.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public abstract class ConfigManager {
    private static FileConfiguration configuration;

    public static void init(Plugin plugin) {
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
        plugin.saveConfig();
        configuration = plugin.getConfig();
    }

    public static Object get(ConfigKeys key) {
        return configuration.get(key.getKey());
    }
}