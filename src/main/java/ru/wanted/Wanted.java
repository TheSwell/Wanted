package ru.wanted;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.wanted.api.WantedAPI;
import ru.wanted.executors.WantedExecutor;
import ru.wanted.listeners.ConnectionListeners;
import ru.wanted.utils.ConfigDefaultSettings;
import ru.wanted.utils.WantedActionBar;

import java.io.File;
import java.io.IOException;

public class Wanted extends JavaPlugin {

    private static Wanted instance;
    private static WantedAPI api;


    public WantedActionBar wantedActionBar;
    public File file;
    public FileConfiguration wantedCfg;

    @Override
    public void onLoad() {
        instance = this;
        file = new File(getDataFolder() + File.separator + "wanted.yml");
        wantedCfg = YamlConfiguration.loadConfiguration(file);
        try {
            wantedCfg.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileConfiguration langConfig = this.getConfig();
        ConfigDefaultSettings configDefaultSettings = new ConfigDefaultSettings();
        saveConfig();


    }

    @Override
    public void onEnable() {
        getServer().getLogger().info("Plugin starting...");
        api = new WantedAPI();
        Bukkit.getPluginManager().registerEvents(new ConnectionListeners(), this);

        wantedActionBar = new WantedActionBar();

        getCommand("Wanted").setExecutor(new WantedExecutor());

        for (Player onlinePlayer :
                Bukkit.getOnlinePlayers()) {
            if (Wanted.getInstance().wantedCfg.contains("wanted." + onlinePlayer.getName() + ".level")) {
                int wantedLevel = Wanted.getInstance().wantedCfg.getInt("wanted." + onlinePlayer.getName() + ".level");
                Wanted.getApi().setWantedLevel(onlinePlayer, wantedLevel);
            }
        }

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player onlinePlayer :
                    Bukkit.getOnlinePlayers())
                wantedActionBar.infinityWantedActionBar(onlinePlayer);
        }, 20, 20));
    }


    @Override
    public void onDisable() {
        for (String key :
                Wanted.api.wantedLevel.keySet()) {
            wantedCfg.set("wanted." + key + ".level", api.getWantedLevel(key));
            try {
                wantedCfg.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static WantedAPI getApi() {
        return api;
    }

    public static Wanted getInstance() {
        return instance;
    }
}
