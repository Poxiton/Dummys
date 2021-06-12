package com.github.poxiton.entities;

import com.github.poxiton.DummyManager;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Consumer;

public class DummySkeleton implements Consumer<Skeleton> {

  private Player player;
  private DummyManager manager;
  private Plugin plugin;

  public DummySkeleton(Player player, Plugin plugin, DummyManager manager) {
    this.player = player;
    this.plugin = plugin;
    this.manager = manager;
  }

  @Override
  public void accept(Skeleton skeleton) {
    manager.setItems(skeleton);

    skeleton.setRotation(player.getLocation().getYaw() + 180, 0);
    skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2048);
    skeleton.setHealth(2048);
    skeleton.setCustomName(String.format("§a%d§c❤", plugin.getConfig().getInt("DummyDamage")));
    skeleton.setAI(false);
    skeleton.setRemoveWhenFarAway(false);
    skeleton.getPersistentDataContainer().set(new NamespacedKey(plugin, "totalDamage"), PersistentDataType.INTEGER,
        plugin.getConfig().getInt("DummyDamage"));
    skeleton.getPersistentDataContainer().set(new NamespacedKey(plugin, "taskId"), PersistentDataType.INTEGER, -1);
  }

}
