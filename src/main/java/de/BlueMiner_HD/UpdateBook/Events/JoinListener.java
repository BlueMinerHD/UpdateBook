package de.BlueMiner_HD.UpdateBook.Events;

import de.BlueMiner_HD.UpdateBook.Methoden.Methoden;
import de.BlueMiner_HD.UpdateBook.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoinListener(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        Bukkit.getScheduler().runTaskLater(main.getInstance(), new Runnable() {
            @Override
            public void run() {
                Methoden.open(p);
            }
        }, 15);

    }
}
