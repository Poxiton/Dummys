package com.github.poxiton.events;

import java.util.HashMap;

import com.github.poxiton.entities.DummyModel;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class DummyDamage implements Listener {

  private Plugin plugin;

  private FileConfiguration config;

  private HashMap<Location, DummyModel> dummies;

  public DummyDamage(Plugin plugin, FileConfiguration config, HashMap<Location, DummyModel> dummies) {
    this.plugin = plugin;
    this.config = config;
    this.dummies = dummies;
  }

  @EventHandler
  public void EntityDamageEvent(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof LivingEntity))
      return;

    LivingEntity entity = (LivingEntity) event.getEntity();

    if (entity.hasAI() || !(entity instanceof Skeleton))
      return;

    System.out.println(dummies);

    int damage = (int) event.getDamage();
    int health = (int) entity.getHealth();
    Location entityLocation = entity.getLocation();

    System.out.println(entityLocation);
    DummyModel dummy = dummies.get(entityLocation);
    dummy.totalDamage += damage;

    if (config.getBoolean("TotalDamage")) {
      entity.setCustomName(String.format("§a%d§c❤", dummy.totalDamage));

      if (dummies.get(entityLocation).taskId != -1)
        Bukkit.getScheduler().cancelTask(dummies.get(entityLocation).taskId);

      dummies.get(entityLocation).taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
        dummy.totalDamage = 0;
        entity.setCustomName(String.format("§a%d§c❤", dummy.totalDamage));
      }, config.getInt("DummyRestartTime") * 20);
    } else {
      entity.setCustomName(String.format("§a%d§c❤", health));
    }

    if (damage > health)
      entity.setHealth(config.getInt("DummyHealth"));
  }

}
