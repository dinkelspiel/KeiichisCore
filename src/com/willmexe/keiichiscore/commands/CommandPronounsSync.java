package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import com.willmexe.keiichiscore.classes.ClassPronouns;
import com.willmexe.keiichiscore.gui.GuiPronouns;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class CommandPronounsSync implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(GlobalVariables.success_prefix + "Syncing Pronouns.");
        for(Map.Entry<String, String> pronoun : GlobalVariables.player_pronouns.entrySet()) {
            Player player = KeiichisCore.getPlugin().getServer().getPlayer(UUID.fromString(pronoun.getKey()));
            if(player != null) {
                ClassPronouns.setPronouns(player);
            }
        }
        return true;
    }
}
