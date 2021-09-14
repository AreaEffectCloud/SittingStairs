package com.github.areaeffectcloud.sittingstairs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Stairs;
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
            double stairsY = blockLocation.getBlockY() - 1.0;
            double stairsZ = blockLocation.getBlockZ() + 0.5;

            Location loc = new Location(block.getWorld(), stairsX, stairsY, stairsZ);

            Entity armorstand = loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            ArmorStand as = (ArmorStand) armorstand;
            as.setInvisible(true);
            armorstand.addPassenger(player);
            armorstand.setGravity(false);
            armorstand.setInvulnerable(true);

        }
    }

    @EventHandler
    public void Sitting(PlayerInteractEvent e) {
        Action action = e.getAction();
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();

        if (action == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType().isAir()) {
                //Not doing
                if (block.getType() == Material.matchMaterial(Main.mainclass.getConfig().getString("stairs"))) {

                    Stairs stairs = (Stairs) block.getBlockData();
                    if (stairs.getHalf() == Bisected.Half.BOTTOM) {
                        this.spawnArmorStand(block, player);
                    }
                }
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
