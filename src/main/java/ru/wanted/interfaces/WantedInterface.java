package ru.wanted.interfaces;

import org.bukkit.entity.Player;

public interface WantedInterface {
    void addWanted(Player player, int level);

    void removeWanted(Player player);

    int getWantedLevel(Player player);
    int getWantedLevel(String player);

    void setWantedLevel(Player player, int level);
}
