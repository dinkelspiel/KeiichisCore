package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.gui.GuiSelect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Dictionary;
import java.util.Map;

public class CommandChatTitle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must run this command as player!");
            return true;
        }

        if(!(args.length > 0)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must specify a title!");
            return true;
        }

        Player player = (Player)sender;

        String fixed_title = "";
        for(var i = 0; i < args[0].length(); i++) {
            fixed_title += "\uF801" + GlobalVariables.letter_lookup.get(String.valueOf(args[0].charAt(i)));
            if(GlobalVariables.letter_lookup.get(String.valueOf(args[0].charAt(i))) == null) {
                player.sendMessage(GlobalVariables.error_prefix + "Invalid character '" + String.valueOf(args[0].charAt(i)) + "' was used! Please remove this character and try again.");
                return true;
            }
        }
        fixed_title += "\uF823";

        GlobalVariables.player_titles.put(player.getUniqueId().toString(), fixed_title);

        player.sendMessage(GlobalVariables.success_prefix + "You have set your title to '" + args[0] + "'!");

        return true;
    }
}
