package com.github.poxiton;

import java.util.HashMap;

import com.github.poxiton.commands.Dummy;
import com.github.poxiton.commands.DummyCompletion;
import com.github.poxiton.entities.DummyModel;
import com.github.poxiton.events.DummyDamage;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Dummys extends JavaPlugin {

    @Override
    public void onEnable() {
        HashMap<Location, DummyModel> dummies = new HashMap<Location, DummyModel>();

        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();

        getServer().getPluginManager().registerEvents(new DummyDamage(this, config, dummies), this);
        this.getCommand("dummy").setExecutor(new Dummy(this, config, dummies));
        this.getCommand("dummy").setTabCompleter(new DummyCompletion());
    }

}
