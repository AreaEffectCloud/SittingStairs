package com.github.areaeffectcloud.sittingstairs;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new SittingStairs(), this);
        getServer().getPluginManager().registerEvents(this, this);

        List<String> list = new ArrayList<>();
        getConfig().set("stairs", list);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
