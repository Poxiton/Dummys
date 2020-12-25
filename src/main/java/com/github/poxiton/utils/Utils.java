package com.github.poxiton.utils;

import com.github.poxiton.entities.DummySkeleton;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
  public static void createDummy(Player player) {
    Location loc = player.getTargetBlock(10).getLocation().add(0.5, 1, 0.5);
    Block targetBlock = player.getTargetBlock(10);

    if (player.getTargetBlockFace(10).toString().equals("UP") && !targetBlock.isLiquid()) {
      player.getWorld().spawn(loc, Skeleton.class, new DummySkeleton(player));
    } else {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot place that here!"));
    }
  }

  /**
   * Delete a dummy
   *
   * @param player Represents a player
   * @param target Represents a dummy
   */
  public static boolean deleteDummy(Player player, LivingEntity target) {
    if (target != null && !target.hasAI() && target instanceof Skeleton) {
      target.remove();
      return true;
    }
    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis is not a dummy!"));
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
