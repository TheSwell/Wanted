package ru.wanted.utils;

import me.lemonypancakes.bukkit.common.actionbarapi.ActionBarAPI;
import org.bukkit.entity.Player;
import ru.wanted.Wanted;


public class WantedActionBar {
    public void infinityWantedActionBar(Player player) {
        StringBuilder wantedLevel = new StringBuilder("✰✰✰✰✰✰");
        int level = 0;
        for (int i = 0; i < Wanted.getApi().getWantedLevel(player); i++) {
            wantedLevel.setCharAt(i, '✮');
        }
        ActionBarAPI.sendMessage(player, "§6" +wantedLevel);
    }
}
