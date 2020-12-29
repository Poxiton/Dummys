package com.github.poxiton.commands;

import java.util.stream.Collectors;

import com.github.poxiton.DummyManager;
import com.github.poxiton.Dummys;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class DummyCommand implements CommandExecutor {

  private final Dummys plugin;
  private DummyManager manager;

  public DummyCommand(Dummys plugin, DummyManager manager) {
    this.plugin = plugin;
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

        String message = plugin.getConfig().getList("HelpMessage").stream().map(n -> String.valueOf(n))
            .collect(Collectors.joining(" "));

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

      } else if (args[0].equalsIgnoreCase("create")) {

        manager.createDummy(player);

      } else if (args[0].equalsIgnoreCase("delete")) {

        manager.deleteDummy(player, target);

      } else if (args[0].equalsIgnoreCase("reload")) {

        plugin.reloadConfig();
        player.sendMessage(plugin.getConfig().getString("DummyReload"));

      }

      return true;
    }

    return false;
  }

}
