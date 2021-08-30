package com.github.areaeffectcloud.sittingstairs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

public class SittingStairs implements Listener {

    public void spawnArmorStand(Block block, Player player) {

        synchronized (this) {
            Location blockLocation = block.getLocation();

            double stairsX = blockLocation.getBlockX() + 0.5;
            double stairsY = blockLocation.getBlockY() - 1.2;
            double stairsZ = blockLocation.getBlockZ() + 0.5;

            Location loc = new Location(block.getWorld(), stairsX, stairsY, stairsZ);

            Entity armorStand = loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            armorStand.addPassenger(player);
            armorStand.setInvulnerable(true);
            armorStand.setGravity(false);

        }
    }

    @EventHandler
    public void Sitting(PlayerInteractEvent e) {
        Action action = e.getAction();
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();

        if (action == Action.RIGHT_CLICK_BLOCK && player.getInventory().getItemInMainHand() == null) {
            //Only Planks
            if (block.getType().equals(Material.ACACIA_STAIRS)) {
                this.spawnArmorStand(e.getClickedBlock(), e.getPlayer());

            } else if (block.getType().equals(Material.BIRCH_STAIRS)) {
                this.spawnArmorStand(e.getClickedBlock(), e.getPlayer());

            } else if (block.getType().equals(Material.DARK_OAK_STAIRS)) {
                this.spawnArmorStand(e.getClickedBlock(), e.getPlayer());

            } else if (block.getType().equals(Material.JUNGLE_STAIRS)) {
                this.spawnArmorStand(e.getClickedBlock(), e.getPlayer());

            } else if (block.getType().equals(Material.OAK_STAIRS)) {
                this.spawnArmorStand(e.getClickedBlock(), e.getPlayer());

            } else if (block.getType().equals(Material.SPRUCE_STAIRS)) {
                this.spawnArmorStand(e.getClickedBlock(), e.getPlayer());

            } else if (block.getType().equals(Material.CRIMSON_STAIRS)) {
                this.spawnArmorStand(e.getClickedBlock(), e.getPlayer());

            } else if (block.getType().equals(Material.WARPED_STAIRS)) {
                this.spawnArmorStand(e.getClickedBlock(), e.getPlayer());

            }
        }
    }

    @EventHandler
    public void Remove(EntityDismountEvent e) {
        if (e.getDismounted().getType() == EntityType.ARMOR_STAND) {
            ArmorStand armorStand = (ArmorStand) e.getDismounted();
            armorStand.remove();
        }
    }
}
