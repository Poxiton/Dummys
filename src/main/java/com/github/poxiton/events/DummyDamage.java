package com.github.poxiton.events;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class DummyDamage implements Listener {

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

    PersistentDataContainer dummyData = entity.getPersistentDataContainer();

    int damage = (int) event.getDamage();
    int health = (int) entity.getHealth();
    int totalDamage = dummyData.get(new NamespacedKey(plugin, "totalDamage"), PersistentDataType.INTEGER) + damage;
    int taskId = dummyData.get(new NamespacedKey(plugin, "taskId"), PersistentDataType.INTEGER);

    if (config.getBoolean("TotalDamage")) {
      entity.setCustomName(String.format("§a%d§c❤", totalDamage));
      dummyData.set(new NamespacedKey(plugin, "totalDamage"), PersistentDataType.INTEGER, totalDamage);

      if (taskId != -1)
        Bukkit.getScheduler().cancelTask(taskId);

      dummyData.set(new NamespacedKey(plugin, "taskId"), PersistentDataType.INTEGER,
          Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            dummyData.set(new NamespacedKey(plugin, "totalDamage"), PersistentDataType.INTEGER, 0);
            entity.setCustomName("§a0§c❤");
          }, config.getInt("DummyRestartTime") * 20));
    } else {
      entity.setCustomName(String.format("§a%d§c❤", health));
    }

    if (damage > health)
      entity.setHealth(config.getInt("DummyHealth"));
  }

}
