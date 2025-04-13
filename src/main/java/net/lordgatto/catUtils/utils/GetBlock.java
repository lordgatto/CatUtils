package net.lordgatto.catUtils.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class GetBlock {
    public static Location GetBlock(int range, Player player, PlayerInteractEvent event) {
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection();

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock != null) {
            return clickedBlock.getLocation();
        }

        World world = eyeLocation.getWorld();

        for (int i = 1; i <= range; i++) {
            Vector offset = direction.clone().multiply(i);
            Location checkLocation = eyeLocation.clone().add(offset);
            Block block = world.getBlockAt(checkLocation);

            if (block.getType() != Material.AIR) {
                return block.getLocation();
            }
        }

        // Se non trova nulla, restituisce l'ultimo punto della traiettoria
        Location lastLocation = eyeLocation.clone().add(direction.clone().multiply(range));
        return world.getBlockAt(lastLocation).getLocation();
    }


    public static Location GetBlock(PlayerInteractEntityEvent interact_entity_event) {
        return interact_entity_event.getRightClicked().getLocation();
    }
}
