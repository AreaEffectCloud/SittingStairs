package com.github.areaeffectcloud.sittingstairs;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new SittingStairs(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
