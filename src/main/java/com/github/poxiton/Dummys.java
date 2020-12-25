package com.github.poxiton;

import com.github.poxiton.commands.Dummy;
import com.github.poxiton.commands.DummyCompletion;
import com.github.poxiton.events.DummyDamage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Dummys extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();

        getServer().getPluginManager().registerEvents(new DummyDamage(this, config), this);
        this.getCommand("dummy").setExecutor(new Dummy(this, config));
        this.getCommand("dummy").setTabCompleter(new DummyCompletion());
    }

}
