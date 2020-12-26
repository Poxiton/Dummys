package com.github.poxiton;

import com.github.poxiton.entities.DummySkeleton;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class DummyManager {

  private Plugin plugin;

  public DummyManager(Plugin plugin) {
    this.plugin = plugin;
  }

  /**
   * Create a dummy
   *
   * @param player Represents a player
   */
  public void createDummy(Player player, FileConfiguration config) {
    Location loc = player.getTargetBlock(10).getLocation().add(0.5, 1, 0.5);
    Block targetBlock = player.getTargetBlock(10);

    if (loc.getNearbyEntitiesByType(Skeleton.class, 0, 1, 0).size() > 0) {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot place that here!"));
      return;
    }

    if (player.getTargetBlockFace(10).toString().equals("UP") && !targetBlock.isLiquid() && !targetBlock.isEmpty()) {
      player.getWorld().spawn(loc, Skeleton.class, new DummySkeleton(player, plugin, this));
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
  public boolean deleteDummy(Player player, LivingEntity target) {
    if (target == null || !target.getPersistentDataContainer().has(new NamespacedKey(plugin, "totalDamage"),
        PersistentDataType.INTEGER)) {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis is not a dummy!"));
      return true;
    }
    target.remove();
    return true;
  }

  /**
   * Set items for dummy
   *
   * @param skeleton Represents a Skeleton (Dummy)
   */
  public void setItems(Skeleton skeleton) {
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
