package com.github.poxiton;

import com.github.poxiton.commands.Dummy;
import com.github.poxiton.commands.DummyCompletion;
import com.github.poxiton.events.DummyDamage;

import org.bukkit.plugin.java.JavaPlugin;

public class Dummys extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DummyDamage(this), this);
        this.getCommand("dummy").setExecutor(new Dummy(this));
        this.getCommand("dummy").setTabCompleter(new DummyCompletion());
    }

}
