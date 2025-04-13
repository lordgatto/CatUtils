package net.lordgatto.catUtils.Special.listeners;

import de.tr7zw.nbtapi.NBT;
import net.lordgatto.catUtils.Special.SpecialItem;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpecialItemListenerEntyty implements Listener {
    ItemStack empty = new ItemStack(Material.AIR);

    @EventHandler
    public void LIGHTING(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();

        if (player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getAmount() == 0) {
            return;
        }

        ItemStack weapon = player.getInventory().getItemInMainHand();
        Entity entity = event.getEntity();

        if (NBT.get(weapon, nbt -> {return nbt.getBoolean("special");})) {
            if (NBT.get(weapon, nbt -> {return nbt.getInteger("specialType");}) == SpecialItem.SpecialItems.LIGHTING) {
                player.getWorld().strikeLightning(entity.getLocation());
            }
        }
    }

    @EventHandler
    public void POISON(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();

        if (player.getInventory().getItemInMainHand() == empty || player.getInventory().getItemInMainHand() == null) {
            return;
        }

        ItemStack weapon = player.getInventory().getItemInMainHand();
        Entity entity = event.getEntity();

        if (NBT.get(weapon, nbt -> {return nbt.getBoolean("special");})) {
            if (NBT.get(weapon, nbt -> {return nbt.getInteger("specialType");}) == SpecialItem.SpecialItems.POISON) {
                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, 1));
                }
            }
        }
    }

    @EventHandler
    public void FIRE_BALL_FIXER(EntityDamageByEntityEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            if (event.getDamager() instanceof Fireball) {
                Fireball fireball = (Fireball) event.getDamager();
                if (event.getEntity() instanceof Player) {
                    Player target = (Player) event.getEntity();
                    if (target == fireball.getShooter()) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
