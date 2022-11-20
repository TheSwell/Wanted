package ru.wanted.api;

import org.bukkit.entity.Player;
import ru.wanted.interfaces.WantedInterface;

import java.util.HashMap;

public class WantedAPI implements WantedInterface {
    public HashMap<String, Integer> wantedLevel = new HashMap<>();
    public int MAX_WANTED_LEVEL = 6;

    @Override
    public void addWanted(Player player, int level) {
        if (level + getWantedLevel(player) < MAX_WANTED_LEVEL)
            wantedLevel.put(player.getName(), getWantedLevel(player) + level);
        else wantedLevel.put(player.getName(), MAX_WANTED_LEVEL);
    }

    @Override
    public void removeWanted(Player player) {
        wantedLevel.remove(player.getName());
    }

    @Override
    public int getWantedLevel(Player player) {
        return wantedLevel.getOrDefault(player.getName(), 0);
    }

    @Override
    public int getWantedLevel(String name) {
        return wantedLevel.getOrDefault(name, 0);
    }

    @Override
    public void setWantedLevel(Player player, int level) {
        if (level < MAX_WANTED_LEVEL)
            wantedLevel.put(player.getName(), level);
        else wantedLevel.put(player.getName(), MAX_WANTED_LEVEL);
    }

    public boolean hasLevel(Player player) {
        return wantedLevel.containsKey(player.getName());
    }
}
