package com.rin.handlers;

import com.rin.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class MakeGUI {
    private Main main;
    public MakeGUI(Main main) {
        this.main = main;
    }

    public void createGUI(Player player, String guiName, Integer size) {
        Inventory inv = Bukkit.createInventory(player,size,guiName);
        player.openInventory(inv);
    }

//    public void setMeta(String name) {
//        ItemMeta meta =
//        assert meta != null;
//        meta.setDisplayName(name);
//    }
}
