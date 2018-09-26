package de.BlueMiner_HD.UpdateBook.Methoden;

import de.BlueMiner_HD.UpdateBook.API.BlueAPI;
import de.BlueMiner_HD.UpdateBook.API.UTFConfig;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class config {

    static UTFConfig config;
    static File file;
    static YamlConfiguration configyml;

    public static void loadconfig() {
        file = BlueAPI.getFile("config.yml");
        configyml = BlueAPI.getConfiguration(getFile());


        if (!file.exists()) {
            configyml.set("title", "Willkommen");
            configyml.set("author", "Spyrex");
            List<String> l = new ArrayList<>();
            l.add("&1Seite 1");
            l.add("&1Seite 2");
            l.add("&1Seite 3");
            configyml.set("Pages", l);
            try {
                configyml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = new UTFConfig(file);
    }

    public static String getTitle() {
        return ChatColor.translateAlternateColorCodes('&', config.getString("title"));
    }

    public static String getAuthor() {
        return ChatColor.translateAlternateColorCodes('&', config.getString("author"));
    }

    public static List<String> getPages() {
        List<String> l = config.getStringList("Pages");

        List<String> list = new ArrayList<>();

        for (String string : l) {
            list.add(ChatColor.translateAlternateColorCodes('&', string));
        }

        return list;
    }

    public static UTFConfig getConfig() {
        return config;
    }

    public static File getFile() {
        return file;
    }

    public static YamlConfiguration getConfigyml() {
        return config;
    }

    public static void saveYml() {
        try {
            config.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
