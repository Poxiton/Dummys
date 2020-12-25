package com.github.poxiton.entities;

import com.github.poxiton.utils.Utils;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.util.Consumer;

public class DummySkeleton implements Consumer<Skeleton> {

  private Player player;

  public DummySkeleton(Player player) {
    this.player = player;
  }

  @Override
  public void accept(Skeleton skeleton) {
    skeleton.setRotation(player.getLocation().getYaw() + 180, 0);
    skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2048);
    skeleton.setHealth(2048);
    skeleton.setCustomName("§a0§c❤");
    skeleton.setAI(false);

    Utils.setItems(skeleton);
  }

}
