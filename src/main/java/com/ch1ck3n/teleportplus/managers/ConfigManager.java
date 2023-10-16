package com.ch1ck3n.teleportplus.managers;

import com.ch1ck3n.teleportplus.TeleportPlus;
import com.ch1ck3n.teleportplus.utils.MathUtil;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Set;

public class ConfigManager {
    private TeleportPlus instance;
    public ConfigManager(TeleportPlus instance) {
        this.instance = instance;
    }

    public void addTPoint(String name, Player player) {
        instance.config.createSection("location." + name);
        instance.config.set("location." + name + ".x", MathUtil.getDouble3(player.getLocation().getX()));
        instance.config.set("location." + name + ".y", MathUtil.getDouble3(player.getLocation().getY()));
        instance.config.set("location." + name + ".z", MathUtil.getDouble3(player.getLocation().getZ()));
        instance.config.set("location." + name + ".yaw", MathUtil.getFloat1(player.getLocation().getYaw()));
        instance.config.set("location." + name + ".pitch", MathUtil.getFloat1(player.getLocation().getPitch()));
        instance.config.set("location." + name + ".world", player.getWorld().getName());

        save();
    }

    public void editTPoint(String name, Player player) {
        instance.config.set("location." + name + ".x", MathUtil.getDouble3(player.getLocation().getX()));
        instance.config.set("location." + name + ".y", MathUtil.getDouble3(player.getLocation().getY()));
        instance.config.set("location." + name + ".z", MathUtil.getDouble3(player.getLocation().getZ()));
        instance.config.set("location." + name + ".yaw", MathUtil.getFloat1(player.getLocation().getYaw()));
        instance.config.set("location." + name + ".pitch", MathUtil.getFloat1(player.getLocation().getPitch()));
        instance.config.set("location." + name + ".world", player.getWorld().getName());

        save();
    }

    public void removeTPoint(String name) {
        if ( instance.config.get("location." + name) != null ) {
            instance.config.set( "location." + name, null );

            save();
        }
    }

    public Location getTPoint(String name, Player player) {
        FileConfiguration config = instance.config;
        if ( config.contains("location." + name) ) {
            double x = config.getDouble("location." + name + ".x");
            double y = config.getDouble("location." + name + ".y");
            double z = config.getDouble("location." + name + ".z");
            float yaw = (float) config.getDouble("location." + name + ".yaw");
            float pitch = (float) config.getDouble("location." + name + ".pitch");
            String world = config.getString("location." + name + ".world");
            if( player.getWorld().getName().equalsIgnoreCase(world) ) {
                return new Location(player.getWorld(), x, y, z, yaw, pitch);
            }
            return null;
        }
        return null;
    }

    public Set<String> listTPoint() {
        Set<String> items = instance.config.getConfigurationSection("location.").getKeys(false);
        return items;
    }

    public void save() {
        instance.saveConfig();
        instance.reloadConfig();
        instance.config = instance.getConfig();
    }
}
