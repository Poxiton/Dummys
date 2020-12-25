package com.github.poxiton.events;

import java.util.HashMap;

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

  HashMap<Location, Integer> timers = new HashMap<Location, Integer>();

  private Plugin plugin;

  private FileConfiguration config;

  public DummyDamage(Plugin plugin, FileConfiguration config) {
    this.plugin = plugin;
    this.config = config;
  }

  @EventHandler
  public void EntityDamageEvent(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof LivingEntity))
      return;

    LivingEntity entity = (LivingEntity) event.getEntity();

    if (entity.hasAI() || !(entity instanceof Skeleton))
      return;

    int damage = (int) event.getDamage();
    int health = (int) entity.getHealth();
    int totalDamage = Integer.parseInt(entity.getCustomName().substring(2, entity.getCustomName().length() - 3));
    Location entityLocation = entity.getLocation();

    entity.setCustomName(String.format("§a%d§c❤", totalDamage + damage));

    if (timers.containsKey(entityLocation))
      Bukkit.getScheduler().cancelTask(timers.get(entityLocation));

    timers.put(entityLocation, Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
      entity.setCustomName("§a0§c❤");
    }, config.getInt("DummyRestartTime") * 20));

    if (damage > health)
      entity.setHealth(2048);
  }

}
