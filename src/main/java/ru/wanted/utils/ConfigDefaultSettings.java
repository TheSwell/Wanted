package ru.wanted.utils;

import org.bukkit.configuration.file.FileConfiguration;
import ru.wanted.Wanted;

import java.util.HashMap;
import java.util.Map;

public class ConfigDefaultSettings {
    private static final FileConfiguration config = Wanted.getInstance().getConfig();
    private static final Map<String, Object> message = new HashMap<>();

    static {
        message.put("givenStars", "§6Вы выдали %wanted_level% уровень розыска игроку %targetPlayer%");
        message.put("removeStars", "Вы сняли розыск с игрока %targetPlayer%");
        message.put("setStars", "Вы установили %wanted_level% уровень розыска игроку %targetPlayer%");
        message.put("wantedInfo", "У игрока %targetPlayer% %wanted_level% уровень розыска");

        message.put("getStars", "Игрок %player% выдал вам %wanted_level% уровень розыска");
        message.put("removeYourStars", "Игрок %player% снял с вас уровень розыска");
        message.put("setYourStars", "Игрок %player% установил вам %wanted_level% с вас уровень розыска");


        message.put("wrongCommand", "Вы ввели не правильную команду /wanted help.");
        message.put("numberException", "Не правильный уровень розыска.");
        message.put("notFindPlayer", "Такого игрока нет на сервере.");


        message.put("helpMessageAdd", "Добавить уровень розыска игроку.");
        message.put("helpMessageRemove", "Снять уровень розыска с игрока.");
        message.put("helpMessageInfo", "Узнать информацию розыска игрока.");
        message.put("helpMessageSet", "Установить уровень розыска.");

        config.addDefaults(message);
        config.options().copyDefaults(true);
        Wanted.getInstance().saveConfig();
    }
}
