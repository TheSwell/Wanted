package ru.wanted.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.wanted.Wanted;
import ru.wanted.api.WantedAPI;
import ru.wanted.utils.WantedActionBar;

import java.sql.SQLException;

public class ConnectionListeners implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (Wanted.getInstance().wantedCfg.contains("wanted." + e.getPlayer().getName() + ".level")) {
            int wantedLevel = Wanted.getInstance().wantedCfg.getInt("wanted." + e.getPlayer().getName() + ".level");
            Wanted.getApi().setWantedLevel(e.getPlayer(), wantedLevel);
        }
    }
}
