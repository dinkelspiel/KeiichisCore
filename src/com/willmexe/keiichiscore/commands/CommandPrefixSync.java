package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class CommandPrefixSync implements CommandExecutor {
    public static String getPlayerGroup(Player player) {
        Collection<String> possibleGroups = new ArrayList<>();
        possibleGroups.add("staff");
        possibleGroups.add("default");
        for (String group : possibleGroups) {
            if (player.hasPermission("group." + group)) {
                return group;
            }
        }
        return null;
    }

    public static void setPlayerPrefixSender(Player player, Player sender) {
        if(sender == null) {
            sender = player;
        }

        String group = getPlayerGroup(player);
        CommandChatTitle.setChatTitleSender(sender, player, GlobalVariables.group_prefixes.get(group));
    }

    public static void setPlayerPrefix(Player player) {
        String group = getPlayerGroup(player);
        CommandChatTitle.setChatTitle(player, GlobalVariables.group_prefixes.get(group));
    }

    public static void syncPrefixes() {
        for(Player player : KeiichisCore.getPlugin().getServer().getOnlinePlayers()) {
            setPlayerPrefix(player);
            CommandChatPallete.setChatPalette(player, GlobalVariables.group_palettes.get(getPlayerGroup(player)));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        syncPrefixes();
        sender.sendMessage(GlobalVariables.success_prefix + "Synced prefixes!");
        return true;
    }
}
