package com.github.poxiton.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandCompletion implements TabCompleter {

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if (args.length == 1) {
      List<String> completors = Arrays.asList("create", "delete", "help");
      return completors;
    }

    return null;
  }

}
