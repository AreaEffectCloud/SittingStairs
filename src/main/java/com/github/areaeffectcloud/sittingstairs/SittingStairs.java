package com.github.areaeffectcloud.sittingstairs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import org.spigotmc.event.entity.EntityDismountEvent;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.bukkit.entity.EntityType.ARMOR_STAND;

public class SittingStairs implements Listener {

    public void spawnArmorStand(Block block, Player player, double yaw) {

        synchronized (this) {
            Location blockLocation = block.getLocation();

            double stairsX = blockLocation.getBlockX() + 0.5;
            double stairsY = blockLocation.getBlockY() - 1.2;
            double stairsZ = blockLocation.getBlockZ() + 0.5;

            Location loc = new Location(block.getWorld(), stairsX, stairsY, stairsZ);
            loc.setYaw((float) yaw);

            Entity armorstand = Objects.requireNonNull(loc.getWorld()).spawnEntity(loc, ARMOR_STAND);
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
        File stairsYml = new File(Main.plugin.getDataFolder() + File.separator + "stairs.yml");
        FileConfiguration stairsConfig = YamlConfiguration.loadConfiguration(stairsYml);
        List<String> list = stairsConfig.getStringList("stairs");

        if (action == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType().isAir()) {
                for (String key : list) {
                    if (Objects.requireNonNull(block).getType() == Material.matchMaterial(key)) {
                        Stairs stairs = (Stairs) block.getBlockData();
                        if (stairs.getHalf() == Bisected.Half.BOTTOM) {
                            Collection<Entity> armorstand = block.getWorld().getNearbyEntities(block.getLocation(), 0.5, 1, 0.5, (entity) -> entity.getType() == ARMOR_STAND);
                            if (armorstand.isEmpty()){
                                if (stairs.getFacing() == BlockFace.NORTH) {
                                    double yaw = 0;
                                    this.spawnArmorStand(block, player, yaw);
                                } else if (stairs.getFacing() == BlockFace.EAST) {
                                    double yaw = 90;
                                    this.spawnArmorStand(block,player, yaw);
                                } else if (stairs.getFacing() == BlockFace.SOUTH) {
                                    double yaw = 180;
                                    this.spawnArmorStand(block, player, yaw);
                                } else if (stairs.getFacing() == BlockFace.WEST) {
                                    double yaw = 270;
                                    this.spawnArmorStand(block, player, yaw);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void Remove(EntityDismountEvent e) {
        if (e.getDismounted().getType() == ARMOR_STAND) {
            ArmorStand armorStand = (ArmorStand) e.getDismounted();
            armorStand.remove();

            Player player = (Player) e.getEntity();
            Location loc = player.getLocation();
            player.teleport(loc);
        }
    }
}
