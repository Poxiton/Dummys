package com.github.poxiton.entities;

import com.github.poxiton.utils.Utils;

import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.util.Consumer;

public class DummySkeleton implements Consumer<Skeleton> {

  private Player player;
  private FileConfiguration config;

  public DummySkeleton(Player player, FileConfiguration config) {
    this.player = player;
    this.config = config;
  }

  @Override
  public void accept(Skeleton skeleton) {
    skeleton.setRotation(player.getLocation().getYaw() + 180, 0);
    skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2048);
    skeleton.setHealth(config.getInt("DummyHealth"));
    skeleton.setAI(false);

    if (config.getBoolean("TotalDamage")) {
      skeleton.setCustomName("§a0§c❤");
    } else {
      skeleton.setCustomName(String.format("§a%d§c❤", (int) skeleton.getHealth()));
    }

    Utils.setItems(skeleton);
  }

}
