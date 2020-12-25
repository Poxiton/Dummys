package com.github.poxiton.commands;

import com.github.poxiton.Dummys;
import com.github.poxiton.utils.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Dummy implements CommandExecutor {

  private final Dummys plugin;
  private FileConfiguration config;

  public Dummy(Dummys dummy, FileConfiguration config) {
    this.plugin = dummy;
    this.config = config;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      plugin.getLogger().warning("This command can't be used in console!");
      return true;
    }

    Player player = (Player) sender;
    LivingEntity target = (LivingEntity) player.getTargetEntity(10);

    if (args.length == 1) {
      if (args[0].equalsIgnoreCase("help")) {

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c====================================="));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/dummy create - Use to create a dummy"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/dummy delete - Use to delete a dummy"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c====================================="));

      } else if (args[0].equalsIgnoreCase("create")) {

        Utils.createDummy(player, config);

      } else if (args[0].equalsIgnoreCase("delete")) {

        Utils.deleteDummy(player, target);

      }

      return true;
    }

    return false;
  }

}
