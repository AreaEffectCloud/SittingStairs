package com.github.areaeffectcloud.sittingstairs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public final class Main extends JavaPlugin {

    public static Main mainclass;
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SittingStairs(), this);
        mainclass = this;

        plugin = this;
        File stairsYml = new File(plugin.getDataFolder() + "/stairs.yml");
        FileConfiguration stairsConfig = YamlConfiguration.loadConfiguration(stairsYml);
        saveStairsYml(stairsConfig, stairsYml);
    }

    public void saveStairsYml(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
    }
}
