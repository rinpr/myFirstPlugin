package com.rin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class repairCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (command.getName().equalsIgnoreCase("repair")) {
                    p.sendMessage(ChatColor.GREEN + "You have successfully repaired an item!");
                    repair(p.getInventory().getItemInMainHand());
                } else if (command.getName().equalsIgnoreCase("repairall")) {
                    p.sendMessage(ChatColor.GREEN + "You have successfully repaired all item in your inventory.");
                    ArrayList<ItemStack> inventory = new ArrayList<>();
                    for (int i = 0; i <= 35; i++) {
                        ItemStack itemStack = p.getInventory().getItem(i);
                        if (itemStack != null) {
                            inventory.add(itemStack);
                        }
                    }
                    for (ItemStack i: inventory) {
                        repair(i);
                    }
                }
            } else {
                Bukkit.getLogger().info("You have to be a player to do that!");
            }
        }
        return false;
    }

    private void repair(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        Damageable damagable = (Damageable) meta;
        assert damagable != null;
        damagable.setDamage(0);
        item.setItemMeta(damagable);
    }

}
