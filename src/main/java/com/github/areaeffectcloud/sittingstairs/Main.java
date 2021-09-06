package com.github.areaeffectcloud.sittingstairs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class Main extends JavaPlugin {

    public static Main mainclass;
    public static JavaPlugin plugin;

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new SittingStairs(), this);

        mainclass = this;
        saveDefaultConfig();
        String ymlFilePath = getDataFolder() + File.separator + "config.yml";

        try (Reader reader = new InputStreamReader(new FileInputStream(ymlFilePath))) {

            FileConfiguration yml = new YamlConfiguration();
            yml.load(reader);

        } catch (Exception e) {
            getLogger().warning(String.valueOf(e));
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
