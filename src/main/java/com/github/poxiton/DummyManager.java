package com.github.poxiton;

import com.github.poxiton.entities.DummySkeleton;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
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
  public void createDummy(Player player) {
    Location loc = player.getTargetBlock(10).getLocation().add(0.5, 1, 0.5);
    Block targetBlock = player.getTargetBlock(10);

    if (loc.getNearbyEntitiesByType(Skeleton.class, 0, 1, 0).size() > 0) {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("NotHere")));
      return;
    }

    if (player.getTargetBlockFace(10).toString().equals("UP") && !targetBlock.isLiquid() && !targetBlock.isEmpty()) {
      player.getWorld().spawn(loc, Skeleton.class, new DummySkeleton(player, plugin, this));
    } else {
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("NotHere")));
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
      player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("NotDummy")));
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
    skeleton.getEquipment().setHelmet(createItem(Material.LEATHER_HELMET, Color.RED, true));
    skeleton.getEquipment().setChestplate(createItem(Material.LEATHER_CHESTPLATE, Color.ORANGE, true));
    skeleton.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
  }

  /**
   * Create item
   *
   * @param material Represents material (item)
   * @param color Represents color for item
   * @param unbreak Represents the item is unbreakable
   */
  private ItemStack createItem(Material material, Color color, Boolean unbreak) {
    ItemStack item = new ItemStack(material);
    LeatherArmorMeta itemmeta = (LeatherArmorMeta) item.getItemMeta();
    itemmeta.setColor(color);
    itemmeta.setUnbreakable(unbreak);
    item.setItemMeta(itemmeta);

    return item;
  }

}
