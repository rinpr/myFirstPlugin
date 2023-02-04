package com.rin.handlers;

import com.rin.Main;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class readConfig {
    private static Main main;
    public readConfig(Main main) {
        readConfig.main = main;
    }
    // List sub key from main key
    public List<String> getSubKey(String key) {
        ConfigurationSection section = main.getConfig().getConfigurationSection(key);
        assert section != null;
        Set<String> collection = section.getKeys(false);
        List<String> sec = new ArrayList<String>();
        for (String i : collection) {
            sec.add(String.valueOf(i));
        }
        return sec;
    }

    public String getKey(String key) {
        return main.getConfig().getString(key);
    }

    // List file from directory
    private Set<String> listFiles(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public String getFile(Integer index) {
         Set<String> fileList = listFiles(getGuiPath());
         String[] files = new String[fileList.size()];
         int i = 0;
         for (String file : fileList) {
             files[i++] = file;
         }
         return files[index];
    }

    private Set<String> getMainKeyList(String file) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(getGuiPath() + file));
        return config.getKeys(false);
    }
    public String getMainKey(String file) {
        return getMainKeyList(file).iterator().next();
    }
    public YamlConfiguration loadConfigFolder(String file) {
        File configFile = new File(main.getDataFolder() + "/gui/" + file);
        YamlConfiguration configs = new YamlConfiguration();
        try {
            configs.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        return configs;
    }
    public String[] getGuiIdList() {
        Set<String> keyList = listFiles(getGuiPath());
        String[] kl = new String[keyList.size()];
        int i = 0;
;       for (String string : keyList) {
            kl[i++] = string;
        }
        for (int j = 0; j < kl.length; j++) {
            kl[j] = kl[j].replace(".yml","");
        }
        return kl;
    }
    public List<String> getGuiSlot(String guiId) {
        YamlConfiguration config = loadConfigFolder(guiId + ".yml");

        for (String slot : getSubKey(guiId + ".slot")) {
            Objects[] slotData = new Objects[getSubKey(guiId + ".slot").size()];
            Set<ItemStack> sd = new HashSet<>();

            ItemStack material = new ItemStack(Objects.requireNonNull(Material.matchMaterial(
                    Objects.requireNonNull(config.getString(guiId + ".slot." + slot + ".material")))));
            String name = config.getString(guiId + ".slot." + slot + "name");
            int customModelData = config.getInt(guiId + ".slot." + slot + "custom_model_data");
            int amount = config.getInt(guiId + ".slot." + slot + "amount");

            return null;
        }

        return getSubKey(guiId + ".slot");
    }
    public String[] getSlotID(String guiId) {
        YamlConfiguration config = loadConfigFolder(guiId + ".yml");
        ConfigurationSection section =  config.getConfigurationSection(guiId + ".slot");
        //ConfigurationSection section = main.getConfig().getConfigurationSection(guiId + ".slot");
        assert section != null;
        Set<String> collection = section.getKeys(false);
        String[] sec = new String[collection.size()];
        collection.toArray(sec);
        return sec;
    }
    public ItemStack[] getSlotMaterial(String guiId) {
        YamlConfiguration config = loadConfigFolder(guiId + ".yml");
        ConfigurationSection section = config.getConfigurationSection(guiId + ".slot");
        ItemStack[] slotMaterial = new ItemStack[getSubKey(guiId + ".slot").size()];
        
        for (String slot : getSubKey(guiId + ".slot")) {
            ItemStack material = new ItemStack(Objects.requireNonNull(Material.matchMaterial(
                    Objects.requireNonNull(config.getString(guiId + ".slot." + slot + ".material")))));
            slotMaterial[Integer.parseInt(slot)] = new ItemStack(material);
        }
        return slotMaterial;
    }
    public String getPath(String files) {
        File file = new File(main.getDataFolder() + "/gui/" + files);
        return String.valueOf(file);
    }

    private String getGuiPath() {
        return "plugins/Rin/gui/";
    }
}
