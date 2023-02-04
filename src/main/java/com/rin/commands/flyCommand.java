package com.rin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class flyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Player target;
        if (player.hasPermission("rin.command.fly")) {
            if (args.length == 0 && command.getName().equalsIgnoreCase("fly")) {
                if (player.getGameMode() != GameMode.CREATIVE) {
                    if (player.getAllowFlight()) {
                        player.setAllowFlight(false);
                        player.sendMessage(ChatColor.RED + "Flight mode has disabled!");
                    } else {
                        player.setAllowFlight(true);
                        player.sendMessage(ChatColor.GREEN + "Flight mode has enabled!");
                    }
                } else if (player.getGameMode() == GameMode.CREATIVE) {
                    player.sendMessage(ChatColor.RED + "You already able to fly no need for this.");
                }
            } else if (args.length == 1 && command.getName().equalsIgnoreCase("fly")) {
                target = Bukkit.getPlayer(args[0]);
                if (target != sender && Objects.requireNonNull(target).getGameMode() != GameMode.CREATIVE) {
                    if (target.getAllowFlight()) {
                        target.setAllowFlight(false);
                        target.sendMessage("Flight mode disabled by " + sender.getName());
                        sender.sendMessage("You have disabled flight mode for " + target.getName());
                    } else {
                        target.setAllowFlight(true);
                        target.sendMessage("Flight mode enabled by " + sender.getName());
                        sender.sendMessage("You have enabled flight mode for " + target.getName());
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Somethings wrong...");
                }
            }
        } else {
            player.sendMessage(ChatColor.RED + "You don't have permission for this command!");
        }
        return false;
    }
}
