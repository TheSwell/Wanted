package ru.wanted.executors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.wanted.Wanted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WantedExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        List<String> help = Arrays.asList("***********", "/wanted add [nick] [level] - " + getKeyFromConfig("helpMessageAdd"),
                "/wanted remove [nick] [level] - " + getKeyFromConfig("helpMessageRemove")
                , "/wanted info - " + getKeyFromConfig("helpMessageInfo"),
                "/wanted set - " + getKeyFromConfig("helpMessageSet"),
                " ", "***********");

        if (label.equalsIgnoreCase("wanted")) {
            try {
                if (args.length != 0) {
                    if (args[0].equalsIgnoreCase("add")) {
                        if (player.hasPermission("wanted.add")) {
                            Player targetPlayer = Bukkit.getPlayer(args[1]);
                            if (targetPlayer != null) {
                                try {
                                    int level = Integer.parseInt(args[2]);
                                    Wanted.getApi().addWanted(targetPlayer, level);
                                    player.sendMessage(getKeyFromConfig("givenStars", player, targetPlayer, level) + "");
                                    targetPlayer.sendMessage(getKeyFromConfig("getStars", player, targetPlayer, level));
                                } catch (NumberFormatException e) {
                                    player.sendMessage(getKeyFromConfig("numberException"));
                                    return false;
                                }


                            } else player.sendMessage(getKeyFromConfig("notFindPlayer"));
                        } else player.sendMessage(getKeyFromConfig("haventPerms"));
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        if (player.hasPermission("wanted.remove")) {
                            Player targetPlayer = Bukkit.getPlayer(args[1]);
                            if (targetPlayer != null) {
                                Wanted.getApi().removeWanted(player);
                                player.sendMessage(getKeyFromConfig("removeStars", player, targetPlayer, Wanted.getApi().getWantedLevel(targetPlayer)));
                                targetPlayer.sendMessage(getKeyFromConfig("removeYourStars", player, targetPlayer, Wanted.getApi().getWantedLevel(targetPlayer)));
                            } else player.sendMessage(getKeyFromConfig("notFindPlayer"));
                        } else player.sendMessage(getKeyFromConfig("haventPerms"));
                    } else if (args[0].equalsIgnoreCase("help")) {
                        for (String s :
                                help) {
                            player.sendMessage("§6" + s);
                        }
                    }
                    if (args[0].equalsIgnoreCase("set")) {
                        if (player.hasPermission("wanted.set")) {
                            Player targetPlayer = Bukkit.getPlayer(args[1]);
                            if (targetPlayer != null) {
                                try {
                                    int level = Integer.parseInt(args[2]);
                                    Wanted.getApi().setWantedLevel(targetPlayer, level);
                                    player.sendMessage(getKeyFromConfig("setStars", player, targetPlayer, level));
                                    targetPlayer.sendMessage(getKeyFromConfig("setYourStars", player, targetPlayer, level));
                                } catch (NumberFormatException e) {
                                    player.sendMessage(getKeyFromConfig("numberException"));
                                    return false;
                                }

                            }

                        } else player.sendMessage(getKeyFromConfig("haventPerms"));
                    }
                    if (args[0].equalsIgnoreCase("info")) {
                        if (player.hasPermission("wanted.info")) {
                            Player targetPlayer = Bukkit.getPlayer(args[1]);
                            if (targetPlayer != null)
                                player.sendMessage(getKeyFromConfig("wantedInfo", player, targetPlayer, Wanted.getApi().getWantedLevel(targetPlayer)));
                        } else player.sendMessage(getKeyFromConfig("haventPerms"));
                    }
                }
            } catch (Exception e) {
                player.sendMessage(getKeyFromConfig("wrongCommand"));
            }
        }

        return false;
    }

    public String getKeyFromConfig(String key, Player player, Player targetPlayer, int wantedLevel) {
        String message;
        if (wantedLevel <= Wanted.getApi().MAX_WANTED_LEVEL) {
            message = Wanted.getInstance().getConfig().getString(key).replaceAll("%player%", player.getName()).
                    replaceAll("%targetPlayer%", targetPlayer.getName()).replaceAll("%wanted_level%", String.valueOf(wantedLevel));
        } else {
            message = Wanted.getInstance().getConfig().getString(key).replaceAll("%player%", player.getName()).
                    replaceAll("%targetPlayer%", targetPlayer.getName()).replaceAll("%wanted_level%", String.valueOf(Wanted.getApi().MAX_WANTED_LEVEL));
        }
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }


    public String getKeyFromConfig(String key) {
        String message = Wanted.getInstance().getConfig().getString(key);
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }


}
