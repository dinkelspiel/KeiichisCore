package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
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
        String title = null;

        if(!(args.length > 0)) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(GlobalVariables.error_prefix + "You must be player to clear personal prefix.");
                return true;
            }
            sender.sendMessage(GlobalVariables.success_prefix + "Cleared personal prefix.");
            setChatTitleSender(sender, (Player)sender, title);
            return true;
        }

        if(args.length >= 2) {
            title = args[1];
        } else {
            if(!(sender instanceof Player)) {
                sender.sendMessage(GlobalVariables.error_prefix + "You must be player to set personal prefix.");
                return true;
            }
            setChatTitleSender(sender, (Player)sender, args[0]);
            return true;
        }

        Player player = KeiichisCore.getPlugin().getServer().getPlayer(args[0]);
        if(player == null) {
            sender.sendMessage(GlobalVariables.error_prefix + "Invalid player name supplied.");
            sender.sendMessage(GlobalVariables.error_prefix + "Usage: /chattitle <Player> <Title>");
            return true;
        }

        Player player_sender = (Player)sender;

        setChatTitleSender(player_sender, player, title);

        return true;
    }

    public static boolean setChatTitleSender(CommandSender sender, Player player, String title) {
        if(title == null) {
            GlobalVariables.player_titles.put(player.getUniqueId().toString(), "");
            return true;
        }

        String fixed_title = "\uEF73";
        for(var i = 0; i < title.length(); i++) {
            fixed_title += "\uF801" + GlobalVariables.letter_lookup.get(String.valueOf(title.charAt(i)));
            if(GlobalVariables.letter_lookup.get(String.valueOf(title.charAt(i))) == null) {
                sender.sendMessage(GlobalVariables.error_prefix + "Invalid character '" + String.valueOf(title.charAt(i)) + "' was used! Please remove this character and try again.");
                return false;
            }
        }
        fixed_title += "\uF801\uEF73";
        fixed_title += "\uF823";

        GlobalVariables.player_titles.put(player.getUniqueId().toString(), fixed_title);
        sender.sendMessage(GlobalVariables.success_prefix + "You have set the title of '" + player.getName() + "' to '" + title + "'!");
        return true;
    }

    public static boolean setChatTitle(Player player, String title) {
        if(title == null) {
            GlobalVariables.player_titles.put(player.getUniqueId().toString(), "");
            return true;
        }

        String fixed_title = "";
        for(var i = 0; i < title.length(); i++) {
            fixed_title += "\uF801" + GlobalVariables.letter_lookup.get(String.valueOf(title.charAt(i)));
            if(GlobalVariables.letter_lookup.get(String.valueOf(title.charAt(i))) == null) {
                return false;
            }
        }
        fixed_title += "\uF801\uEF73";
        fixed_title += "\uF823";

        GlobalVariables.player_titles.put(player.getUniqueId().toString(), "\uEF73" + fixed_title);
        return true;
    }
}
