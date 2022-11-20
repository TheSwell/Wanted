package ru.wanted;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;

public class BlockBreak implements Listener {

    @EventHandler
    public void block(BlockBreakEvent e) throws SQLException {
        Player player = e.getPlayer();

    }
}
