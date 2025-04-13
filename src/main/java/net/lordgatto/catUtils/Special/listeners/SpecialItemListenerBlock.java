package net.lordgatto.catUtils.Special.listeners;

import de.tr7zw.nbtapi.NBT;
import net.lordgatto.catUtils.Special.SpecialItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

//
//    @EventHandler
//    public void onPlayerAttack(EntityDamageByEntityEvent event) {
//        if (event.getDamager() instanceof Player) {
//            Player player = (Player) event.getDamager();
//            // You can get the entity being attacked like this:
//            var target = event.getEntity();
//
//            player.sendMessage("You attacked something: " + target.getType());
//        }
//    }

public class SpecialItemListenerBlock implements Listener {

    ItemStack empty = new ItemStack(Material.AIR);


    @EventHandler
    public void LIGHTING(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Location target;

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (event.getItem() == empty || event.getItem() == null) {
            return;
        }

        if (block == null) {
            target = player.getLocation().add(player.getLocation().getDirection().normalize().multiply(8));
            target.setZ(target.getZ() + 1);
        } else {
            target = block.getLocation();
        }

        if (NBT.get(event.getItem(), nbt -> {return nbt.getBoolean("special");})) {
            if (NBT.get(event.getItem(), nbt -> {return nbt.getInteger("specialType");}) == SpecialItem.SpecialItems.LIGHTING) {
                player.getWorld().strikeLightning(target);
            }
        }
    }

    @EventHandler
    public void FIRE_BALL(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack weapon = event.getItem();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (weapon == empty || weapon == null) {
            return;
        }

        if (NBT.get(weapon, nbt -> {return nbt.getBoolean("special");})) {
            if (NBT.get(weapon, nbt -> {return nbt.getInteger("specialType");}) == SpecialItem.SpecialItems.FIRE_BALL) {
                Fireball fireball = player.getWorld().spawn(player.getEyeLocation(), Fireball.class);
                fireball.setShooter(player);
                fireball.setDirection(player.getEyeLocation().getDirection());
            }
        }

    }
}