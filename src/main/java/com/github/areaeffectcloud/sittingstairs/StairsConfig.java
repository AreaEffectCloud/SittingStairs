package com.github.areaeffectcloud.sittingstairs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class StairsConfig {

    private FileConfiguration config = null;
    private final File configFile;
    private final String file;
    private final Plugin plugin;

    public StairsConfig(Plugin plugin) {
        this(plugin, "stairs.yml");
    }

    public StairsConfig(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.file = fileName;
        configFile = new File(plugin.getDataFolder(), file);
    }
}
