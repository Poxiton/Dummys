package com.github.poxiton.entities;

import com.github.poxiton.Dummys;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Consumer;

public class DummySkeleton implements Consumer<Skeleton> {

  private Player player;
  private FileConfiguration config;
  private Dummys plugin;

  public DummySkeleton(Player player, FileConfiguration config, Dummys plugin) {
    this.player = player;
    this.config = config;
    this.plugin = plugin;
  }

  @Override
  public void accept(Skeleton skeleton) {
    skeleton.setRotation(player.getLocation().getYaw() + 180, 0);
    skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2048);
    skeleton.setHealth(config.getInt("DummyHealth"));
    skeleton.setAI(false);

    skeleton.getPersistentDataContainer().set(new NamespacedKey(plugin, "totalDamage"), PersistentDataType.INTEGER, 0);
    skeleton.getPersistentDataContainer().set(new NamespacedKey(plugin, "taskId"), PersistentDataType.INTEGER, -1);

    if (config.getBoolean("TotalDamage")) {
      skeleton.setCustomName("§a0§c❤");
    } else {
      skeleton.setCustomName(String.format("§a%d§c❤", (int) skeleton.getHealth()));
    }

    plugin.setItems(skeleton);
  }

}
