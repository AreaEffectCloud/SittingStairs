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

        saveResource("stairs.yml", false);
        File stairsYml = new File(plugin.getDataFolder() + "/stairs.yml");
        FileConfiguration stairsConfig = YamlConfiguration.loadConfiguration(stairsYml);

        try  {
            stairsConfig.save(stairsYml);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
    }
}
