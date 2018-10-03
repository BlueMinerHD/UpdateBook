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
            List<List<String>> l = new ArrayList<>();
            List<String> page1 = new ArrayList<>();
            page1.add("&1hallo");
            page1.add("&1hallo2");
            page1.add("&1hallo3");
            page1.add("&1hallo4");
            page1.add("&1hallo5");

            l.add(page1);
            List<String> page2 = new ArrayList<>();
            page2.add("&1hallo6");
            page2.add("&1hallo7");
            page2.add("&1hallo8");
            page2.add("&1hallo9");
            page2.add("&1hallo10");


            l.add(page2);

            List<String> page3 = new ArrayList<>();
            page3.add("&1hallo11");
            page3.add("&1hallo12");
            page3.add("&1hallo13");
            page3.add("&1hallo14");
            page3.add("&1hallo15");
            l.add(page3);
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

    public static List<List<String>> getPages() {
        List<List<String>> l = (List<List<String>>) config.get("Pages");

        List<List<String>> toreturn = new ArrayList<>();


        for(List<String> li : l) {
            List<String> list = new ArrayList<>();
            for (String string : li) {
                list.add(ChatColor.translateAlternateColorCodes('&', string));
            }
            toreturn.add(list);

        }
        return toreturn;
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
