package com.willmexe.keiichiscore.events;

import com.willmexe.keiichiscore.GlobalVariables;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class EventsPlayerResourcePack implements Listener {
    @EventHandler
    public void PlayerResourcePack(PlayerResourcePackStatusEvent e) {
        if(e.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {
            Player player = e.getPlayer();
            player.kickPlayer("This server requires that you accept this resource pack!");
        }
    }
}
