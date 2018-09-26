package de.BlueMiner_HD.UpdateBook;

import de.BlueMiner_HD.UpdateBook.Events.JoinListener;
import de.BlueMiner_HD.UpdateBook.Methoden.config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private static main instance;

    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
        registerClasses();
    }

    private void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
    }

    private void registerClasses(){
        config.loadconfig();
    }

    public static main getInstance() {
        return instance;
    }
}
