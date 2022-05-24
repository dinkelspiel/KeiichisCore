package com.willmexe.keiichiscore.events;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.classes.ClassChatPalette;
import com.willmexe.keiichiscore.classes.ClassHome;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class EventsChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        e.setCancelled(true);
        Player player = e.getPlayer();

        if(GlobalVariables.player_chat_input.get(player.getUniqueId().toString()) == "sethome") {
            ClassHome.setHome(player, e.getMessage());
            GlobalVariables.player_chat_input.put(player.getUniqueId().toString(), "");
            return;
        }

        if(GlobalVariables.player_chat_palettes.get(player.getUniqueId().toString()) == null) {
            GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette("§7", "§7", "§f"));
        }

        ClassChatPalette color = GlobalVariables.player_chat_palettes.get(player.getUniqueId().toString());

        String message = e.getMessage();
        message = message.replace("<3", "\uEF74");
        message = message.replace(":skull:", "\uEF75");
        //Bukkit.broadcastMessage(message);

        if(GlobalVariables.player_titles.get(player.getUniqueId().toString()) != null)
            Bukkit.broadcastMessage(color.prefix + GlobalVariables.player_titles.get(player.getUniqueId().toString()) + color.name + player.getDisplayName() + " §7» " + color.chat + message);
        else
            Bukkit.broadcastMessage(color.name + player.getDisplayName() + " §7» " + color.chat + message);
    }
}
