package com.rin.handlers;

import com.rin.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class variousAction implements Listener {
    public variousAction(Main main) {

    }
    @EventHandler
    public void onMove(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Did you just press F key?");
    }
    @EventHandler
    public void onBreak(BlockBreakEvent events) {
        Player player = events.getPlayer();
        Block block = events.getBlock();
        Material m = block.getType();
        ItemStack drop = new ItemStack(Material.DIAMOND);
        World world = player.getWorld();
        if (m == Material.DIRT) {
            player.sendMessage("Free Diamonds!");
            player.getWorld().dropItemNaturally(block.getLocation(), drop);
        }
    }
    @EventHandler
    public void breakDirtGetDiamond(BlockBreakEvent event) {
        Block blockBroken = event.getBlock();

        if (blockBroken.getType() == Material.DIAMOND_ORE) {
            event.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack petch = new ItemStack(Material.DIAMOND, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), petch);

        }
        if (blockBroken.getType() == Material.DEEPSLATE_DIAMOND_ORE) {
            event.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack petch = new ItemStack(Material.DIAMOND, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), petch);
        }
    }
    @EventHandler
    public void onTorchPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (!event.getPlayer().hasPermission("torch.diamond")) {
            return;
        }
        if (block.getType() == Material.TORCH) {
            player.sendMessage(ChatColor.GREEN + "A torch has placed!");
        } else if (block.getType() == Material.REDSTONE_TORCH) {
            event.getBlock().setType(Material.DIAMOND_ORE);
            player.sendMessage(ChatColor.AQUA + "A torch has placed but it magically turns into DIAMONDS!");
            player.playSound(player.getLocation(), Sound.BLOCK_BEEHIVE_EXIT, 1.0f, 0.8f);
        } else if (block.getType() == Material.SOUL_TORCH || block.getType() == Material.SOUL_WALL_TORCH) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        assert block != null;
        if(event.getClickedBlock() == null || event.getClickedBlock().getType() == Material.AIR) return; //It will check if it's null first, and then if it is air. if any of the cases matches it will return.
        if (block.getType() == Material.FURNACE) {
            player.playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
        } else if (block.getType() == Material.CRAFTING_TABLE) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_WORK_SHEPHERD, 2.0f, 1.0f);
        } else if (block.getType() == Material.SMOKER) {
            player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 1.0f);
        } else if (block.getType() == Material.SMITHING_TABLE) {
            player.playSound(player.getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, 1.0f, 1.0f);
        } else if (block.getType() == Material.BLAST_FURNACE) {
            player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 1.0f);
        } else if (block.getType() == Material.ANVIL) {
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack itemDrop = event.getItemDrop().getItemStack();
        if (itemDrop.getType() == Material.ANVIL) {
            player.sendMessage(ChatColor.GRAY + "Anvil dropped!");
            // player.sendMessage(String.valueOf(itemDrop));
        }
    }

//    @EventHandler
//    public void inventoryClick(InventoryClickEvent event) {
//        Bukkit.getServer().broadcastMessage(String.valueOf(event.getSlot()));
//    }

    @EventHandler
    public void test(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        player.sendMessage(Objects.requireNonNull(meta.getLore()).toString());
    }
}
