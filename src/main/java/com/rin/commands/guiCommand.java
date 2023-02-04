package com.rin.commands;

import com.rin.Main;
import com.rin.handlers.MakeGUI;
import com.rin.handlers.readConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class guiCommand implements CommandExecutor {

    private final Main main;
    public guiCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        readConfig config = new readConfig(main);
        String[] arg = config.getGuiIdList();
        MakeGUI gui = new MakeGUI(main);
        if (sender instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) sender;

                String gui_id = config.getMainKey("sample_gui.yml");

                YamlConfiguration configs = config.loadConfigFolder("sample_gui.yml");
                gui.createGUI(player,configs.getString(gui_id + ".name"),configs.getInt(gui_id + ".size"));

                // for debug purpose
//                player.sendMessage(config.getFile(0));
//                player.sendMessage(gui_id + ".name :" + configs.getString(gui_id + ".name"));
//                player.sendMessage(gui_id + ".slot :" + configs.getInt(gui_id + ".size"));
            }
            else {
                for (String string : arg) {
                    if (Objects.equals(args[0], string)) {
                        String gui_id = config.getMainKey(string + ".yml");
                        YamlConfiguration configs = config.loadConfigFolder(string + ".yml");
                        gui.createGUI((Player) sender,configs.getString(gui_id + ".name"),configs.getInt(gui_id + ".size"));
                        sender.sendMessage(Arrays.toString(config.getSlotMaterial(string)));
                    }
                }
            }
        }
        // This command (/gui console) is for debugging through console
        else if (Objects.equals(args[0], "console")) {
            ItemStack[] test = config.getSlotMaterial("test");
            Bukkit.getConsoleSender().sendMessage(Arrays.toString(config.getSlotID("test")));
            Bukkit.getConsoleSender().sendMessage(Arrays.toString(test));
            Bukkit.getConsoleSender().sendMessage(config.getPath("test"));
        }
        return false;
    }

}
