package com.github.areaeffectcloud.sittingstairs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HideOtherPlayers implements Listener {


    //Hide Other Online Players
    private void Hide(Player player) {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (onlinePlayers == null) {
                return;
            } else {
                player.hidePlayer(Main.mainclass, onlinePlayers);
            }
        }
    }

    //Show Other Online Players
    private void Show(Player player) {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (onlinePlayers == null) {
                return;
            } else {
                player.showPlayer(Main.mainclass, onlinePlayers);
            }
        }
    }

    @EventHandler
    public void ClickToSwitch(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        List<String> lore = new ArrayList<>();

        //Hide
        ItemStack hide = new ItemStack(Material.GREEN_DYE);
        ItemMeta meta_hide = hide.getItemMeta();
        Objects.requireNonNull(meta_hide).setDisplayName(ChatColor.DARK_GREEN + "Hide Other PLayers");
        lore.add(ChatColor.GRAY + "Click to");
        lore.add(ChatColor.GRAY + "  Hide Other PLayers");
        meta_hide.setLore(lore);
        hide.setItemMeta(meta_hide);
        lore.clear();

        //Show
        ItemStack show = new ItemStack(Material.LIGHT_GRAY_DYE);
        ItemMeta meta_show = show.getItemMeta();
        Objects.requireNonNull(meta_show).setDisplayName(ChatColor.DARK_GRAY + "Show Other PLayers");
        lore.add(ChatColor.GRAY + "Clock to");
        lore.add(ChatColor.GRAY + " Show Other PLayers");
        meta_show.setLore(lore);
        show.setItemMeta(meta_show);

        if (player.getInventory().getItemInMainHand() == hide) {
            Hide(player);

        } else if (player.getInventory().getItemInMainHand() == show) {
            Show(player);

        }
    }
}
