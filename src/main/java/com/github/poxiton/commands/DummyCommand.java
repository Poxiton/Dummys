package com.github.poxiton.commands;

import java.util.stream.Collectors;

import com.github.poxiton.DummyManager;
import com.github.poxiton.Dummys;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class DummyCommand implements CommandExecutor {

  private final Dummys plugin;
  private FileConfiguration config;
  private DummyManager manager;

  public DummyCommand(Dummys plugin, FileConfiguration config, DummyManager manager) {
    this.plugin = plugin;
    this.config = config;
    this.manager = manager;
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

        String message = config.getList("HelpMessage").stream().map(n -> String.valueOf(n))
            .collect(Collectors.joining(" "));

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

      } else if (args[0].equalsIgnoreCase("create")) {

        manager.createDummy(player, config);

      } else if (args[0].equalsIgnoreCase("delete")) {

<<<<<<< HEAD:src/main/java/com/github/poxiton/commands/Dummy.java
        Utils.deleteDummy(player, target, config);
=======
        manager.deleteDummy(player, target, config);
>>>>>>> optimalization:src/main/java/com/github/poxiton/commands/DummyCommand.java

      }

      return true;
    }

    return false;
  }

}
