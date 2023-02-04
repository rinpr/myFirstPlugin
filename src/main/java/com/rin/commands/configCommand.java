package com.rin.commands;

import com.rin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class configCommand implements CommandExecutor {

    private Main main;
    public configCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;


            // Get key list from one config files
//            FileConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/Rin/gui/sample_gui.yml"));
//            Set<String> keys = config.getKeys(false);
//            for (String key : keys) {
//                player.sendMessage(key);
//            }

        }

        return false;
    }
}
