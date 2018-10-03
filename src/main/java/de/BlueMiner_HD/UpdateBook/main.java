package de.BlueMiner_HD.UpdateBook;

import de.BlueMiner_HD.UpdateBook.Events.JoinListener;
import de.BlueMiner_HD.UpdateBook.Methoden.config;
import de.BlueMiner_HD.UpdateBook.commands.UpdateBookCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private static main instance;

    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
        registerCommands();
        registerClasses();
    }

    private void registerCommands() {
        getCommand("updatebook").setExecutor(new UpdateBookCommand());
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
