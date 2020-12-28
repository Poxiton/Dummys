package com.github.poxiton;

import java.util.logging.Logger;

import com.github.poxiton.commands.DummyCommand;
import com.github.poxiton.events.DamageListener;
import com.github.poxiton.utils.CommandCompletion;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Dummys extends JavaPlugin {

    DummyManager manager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();

        Logger logger = this.getLogger();

        new UpdateChecker(this, 87188).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info(ChatColor.GREEN + "There is no updates available.");
            } else {
                logger.info(ChatColor.RED + "There is an update available.");
            }
        });

        manager = new DummyManager(this);

        getServer().getPluginManager().registerEvents(new DamageListener(this, config), this);
        this.getCommand("dummy").setExecutor(new DummyCommand(this, config, manager));
        this.getCommand("dummy").setTabCompleter(new CommandCompletion());
    }

}
