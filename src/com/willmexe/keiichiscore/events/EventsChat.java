package com.willmexe.keiichiscore.events;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.classes.ClassChatPalette;
import com.willmexe.keiichiscore.classes.ClassHome;
import com.willmexe.keiichiscore.classes.ClassPronouns;
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
        } else if(GlobalVariables.player_chat_input.get(player.getUniqueId().toString()) == "custom_pronoun") {
            GlobalVariables.player_pronouns.put(player.getUniqueId().toString(), e.getMessage());
            player.sendMessage(GlobalVariables.success_prefix + "Your global pronouns are now '" + e.getMessage() + "'!");
            GlobalVariables.player_chat_input.put(player.getUniqueId().toString(), "");
            ClassPronouns.setPronouns(player);
            return;
        } else if(GlobalVariables.player_chat_input.get(player.getUniqueId().toString()) == "custom_pronoun_color"){
            GlobalVariables.player_color_pronouns.put(player.getUniqueId().toString(), e.getMessage());

            String color = "§" + e.getMessage();
            String pronoun = GlobalVariables.player_pronouns.get(player.getUniqueId().toString());

            String fixed_title = "\uEF73";
            for(var i = 0; i < pronoun.length(); i++) {
                fixed_title += "\uF801" + GlobalVariables.letter_lookup.get(String.valueOf(pronoun.charAt(i)));
                if(GlobalVariables.letter_lookup.get(String.valueOf(pronoun.charAt(i))) == null) {
                    player.sendMessage(GlobalVariables.error_prefix + "Invalid character '" + String.valueOf(pronoun.charAt(i)) + "' was used! Please remove this character and try again.");
                    return;
                }
            }
            fixed_title += "\uF801\uEF73";
            fixed_title += "\uF823";

            player.sendMessage(GlobalVariables.success_prefix + "Your global pronouns now looks like '" + color + fixed_title + "§a'!");
            GlobalVariables.player_chat_input.put(player.getUniqueId().toString(), "");
            ClassPronouns.setPronouns(player);
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
