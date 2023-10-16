package com.ch1ck3n.teleportplus;

import com.ch1ck3n.teleportplus.commands.TPPCmd;
import com.ch1ck3n.teleportplus.managers.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class TeleportPlus extends JavaPlugin {


    private String prefix = "";
    public String getPrefix(){
        return prefix;
    }
    private static TeleportPlus instance;
    public static TeleportPlus getInstance() {
        return instance;
    }
    private ConfigManager configManager;
    public ConfigManager getConfigManager(){
        return configManager;
    }
    public FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;
        prefix = "§8[§cTeleport+§8]§r";

        saveDefaultConfig();
        config = getConfig();

        configManager = new ConfigManager(getInstance());

        getCommand("tpp").setExecutor( new TPPCmd() );

    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
