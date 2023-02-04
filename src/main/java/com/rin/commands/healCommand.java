package com.rin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class healCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                heal(player);
                player.sendMessage(ChatColor.GREEN + "Healed!");
            } else {
                Bukkit.getLogger().info("You have to be a player to do that!");
            }
        }    else {
            if (Bukkit.getPlayerExact(args[0]) !=null) {
                Player player = Bukkit.getPlayerExact(args[0]);
                assert player != null;
                heal(player);
                player.sendMessage(ChatColor.GREEN + "You've been healed!");
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage(ChatColor.GREEN + "Player \"" + args[0] + "\" was healed!");
                } else {
                    Bukkit.getLogger().info("Player \"" + args[0] + "\" was healed!");
                }
            } else {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.RED + "Player \"" + args[0] + "\" was not found!");
                } else {
                    Bukkit.getLogger().info("Player \"" + args[0] + "\" was not found!");
                }
            }
        }
        return true;
    }

    private void heal(Player player) {
        player.setHealth(20.0);
        player.setFoodLevel(20);
    }
}
