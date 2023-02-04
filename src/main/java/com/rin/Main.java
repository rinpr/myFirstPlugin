package com.rin;

import com.rin.commands.*;
import com.rin.handlers.variousAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Starting up plugin!");
        loadclass();
        loadConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Plugin has stopped!");
    }

    private void loadclass() {
        PluginManager pl = Bukkit.getPluginManager();
        pl.registerEvents(new variousAction(this), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("heal")).setExecutor(new healCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("repair")).setExecutor(new repairCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("repairall")).setExecutor(new repairCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("fly")).setExecutor(new flyCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("config")).setExecutor(new configCommand(this));
        Objects.requireNonNull(Bukkit.getPluginCommand("gui")).setExecutor(new guiCommand(this));
    }


    private void loadConfig() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    private FileConfiguration getConfigFile() {
        return getConfig();
    }
    private void createCustomConfig() {
        File customConfigFile = new File(getDataFolder(), "/gui/sample_gui.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("/gui/sample_gui.yml", false);
        }

        FileConfiguration customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
