package com.willmexe.keiichiscore.events;

import com.willmexe.keiichiscore.items.ItemCraftBook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class EventsPlayerSpawn implements Listener {
    @EventHandler
    public  void onPlayerRespawn(PlayerRespawnEvent e) {
        e.getPlayer().getInventory().addItem(ItemCraftBook.craftBook);
    }
}
