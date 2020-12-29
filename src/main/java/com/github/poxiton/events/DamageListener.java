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

public class DamageListener implements Listener {

  private Plugin plugin;

  private FileConfiguration config;

  public DamageListener(Plugin plugin, FileConfiguration config) {
    this.plugin = plugin;
    this.config = config;
  }

  @EventHandler
  public void EntityDamageEvent(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof LivingEntity))
      return;

    LivingEntity entity = (LivingEntity) event.getEntity();

    PersistentDataContainer dummyData = entity.getPersistentDataContainer();

    if (!dummyData.has(new NamespacedKey(plugin, "totalDamage"), PersistentDataType.INTEGER))
      return;

    int totalDamage = dummyData.get(new NamespacedKey(plugin, "totalDamage"), PersistentDataType.INTEGER)
        + (int) event.getDamage();

    dummyData.set(new NamespacedKey(plugin, "totalDamage"), PersistentDataType.INTEGER, totalDamage);

    entity.setCustomName(String.format("§a%d§c❤", totalDamage));
    entity.setHealth(2048);

    if (config.getBoolean("DummyReset")) {
      int taskId = dummyData.get(new NamespacedKey(plugin, "taskId"), PersistentDataType.INTEGER);

      if (taskId != -1)
        Bukkit.getScheduler().cancelTask(taskId);

      dummyData.set(new NamespacedKey(plugin, "taskId"), PersistentDataType.INTEGER,
          Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            dummyData.set(new NamespacedKey(plugin, "totalDamage"), PersistentDataType.INTEGER,
                config.getInt("DummyDamage"));
            entity.setCustomName(String.format("§a%d§c❤", config.getInt("DummyDamage")));
          }, config.getInt("DummyRestartTime") * 20));
    }
  }

}
