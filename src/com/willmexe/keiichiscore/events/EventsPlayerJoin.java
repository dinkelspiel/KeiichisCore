package com.willmexe.keiichiscore.events;

import com.willmexe.keiichiscore.KeiichisCore;
import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import com.willmexe.keiichiscore.classes.ClassChatPalette;
import com.willmexe.keiichiscore.classes.ClassPronouns;
import com.willmexe.keiichiscore.commands.CommandChatPallete;
import com.willmexe.keiichiscore.commands.CommandChatTitle;
import com.willmexe.keiichiscore.commands.CommandPrefixSync;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class EventsPlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if(!player.hasPermission("keiichiscore.loadresources"))
            player.setResourcePack("https://github.com/shykeiichi/plugin-resourcepack/blob/main/release.zip?raw=true");

        CommandPrefixSync.setPlayerPrefix(player);
        CommandChatPallete.setChatPalette(player, GlobalVariables.group_palettes.get(CommandPrefixSync.getPlayerGroup(player)));
        ClassPronouns.setPronouns(player);

        if(GlobalVariables.player_chat_palettes.get(player.getUniqueId().toString()) == null) {
            GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette("§f", "§f", "§f"));
        }

        if(KeiichisCore.all_players == null) {
            KeiichisCore.all_players = new ArrayList<>();
            KeiichisCore.all_players.add(player.getUniqueId().toString());
            e.setJoinMessage(GlobalVariables.player_join.replace("<player>", GlobalVariables.player_chat_palettes.get(player.getUniqueId().toString()).name + player.getDisplayName()));
            return;
        }

        if(!KeiichisCore.all_players.contains(player.getUniqueId().toString())) {
            KeiichisCore.all_players.add(player.getUniqueId().toString());
            e.setJoinMessage("§e§l" + player.getDisplayName() + " §ejoined for the first time show them some love!");

            return;
        } else {
            e.setJoinMessage(GlobalVariables.player_join.replace("<player>", GlobalVariables.player_chat_palettes.get(player.getUniqueId().toString()).name + player.getDisplayName()));
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if(GlobalVariables.player_chat_palettes.get(player.getUniqueId().toString()) == null)  {
            GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette("§f", "§f", "§f"));
        }
        e.setQuitMessage(GlobalVariables.player_leave.replace("<player>", GlobalVariables.player_chat_palettes.get(player.getUniqueId().toString()).name + player.getDisplayName()));
    }
}
