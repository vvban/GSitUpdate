package com.bannanguy.vasylykcraft.Main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.bannanguy.vasylykcraft.Events.BasicEvent;

public class GSitUpdate extends JavaPlugin {
	
	@Override
    public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new BasicEvent((Plugin) this), (Plugin) this);
    }

    @Override
    public void onDisable() {}
}
