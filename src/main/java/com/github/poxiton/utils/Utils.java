package com.github.poxiton.utils;

import com.github.poxiton.entities.DummySkeleton;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Utils {

  /**
   * Create a dummy
   *
   * @param player Represents a player
   */
  public static void createDummy(Player player, FileConfiguration config) {
    Location loc = player.getTargetBlock(10).getLocation().add(0.5, 1, 0.5);
    Block targetBlock = player.getTargetBlock(10);

    if (loc.getNearbyEntitiesByType(Skeleton.class, 0, 1, 0).size() > 0) {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("NotHere")));
      return;
    }

    if (player.getTargetBlockFace(10).toString().equals("UP") && !targetBlock.isLiquid() && !targetBlock.isEmpty()) {
      player.getWorld().spawn(loc, Skeleton.class, new DummySkeleton(player, config));
    } else {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("NotHere")));
    }
  }

  /**
   * Delete a dummy
   *
   * @param player Represents a player
   * @param target Represents a dummy
   */
  public static boolean deleteDummy(Player player, LivingEntity target, FileConfiguration config) {
    if (target != null && !target.hasAI() && target instanceof Skeleton) {
      target.remove();
      return true;
    }
    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("NotDummy")));
    return true;
  }

  /**
   * Set items for dummy
   *
   * @param skeleton Represents a Skeleton (Dummy)
   */
  public static void setItems(Skeleton skeleton) {
    ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
    LeatherArmorMeta helmetmeta = (LeatherArmorMeta) helmet.getItemMeta();
    helmetmeta.setColor(Color.RED);
    helmetmeta.setUnbreakable(true);
    helmet.setItemMeta(helmetmeta);

    ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
    LeatherArmorMeta chestplatemeta = (LeatherArmorMeta) chestplate.getItemMeta();
    chestplatemeta.setColor(Color.ORANGE);
    chestplatemeta.setUnbreakable(true);
    chestplate.setItemMeta(chestplatemeta);

    skeleton.getEquipment().setHelmet(helmet);
    skeleton.getEquipment().setChestplate(chestplate);
    skeleton.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
  }
}
