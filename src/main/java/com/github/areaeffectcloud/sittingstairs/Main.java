package com.github.areaeffectcloud.sittingstairs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public final class Main extends JavaPlugin {

    public static Main mainclass;
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        PluginManager PM = getServer().getPluginManager();
        PM.registerEvents(new SittingStairs(), this);
        PM.registerEvents(new HideOtherPlayers(), this);

        mainclass = this;
        plugin = this;

        saveResource("stairs.yml", false);
        File stairsYml = new File(plugin.getDataFolder() + File.separator + "stairs.yml");
        FileConfiguration stairsConfig = YamlConfiguration.loadConfiguration(stairsYml);

        try  {
            stairsConfig.save(stairsYml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {}
}
