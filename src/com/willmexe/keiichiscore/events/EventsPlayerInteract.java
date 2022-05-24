package com.willmexe.keiichiscore.events;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.gui.GuiCraftBook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventsPlayerInteract implements Listener {
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player player = (Player) e.getPlayer();

        if (player.getItemInHand().getItemMeta() == null) {
            return;
        }
        if (player.getItemInHand().getItemMeta().getDisplayName().contains("Crafter's Handbook")) {

            player.openInventory(GuiCraftBook.inv);
        }
    }

}
