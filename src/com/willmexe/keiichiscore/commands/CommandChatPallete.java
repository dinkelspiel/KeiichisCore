
package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import com.willmexe.keiichiscore.classes.ClassChatPalette;
import com.willmexe.keiichiscore.gui.GuiSelect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChatPallete implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(args.length > 1)) {
            setChatPaletteSender(sender, (Player)sender, null, null, null);
            return true;
        }
        if(!(args.length > 2)) {
            setChatPaletteSender(sender, KeiichisCore.getPlugin().getServer().getPlayer(args[0]), null, null, null);
            return true;
        }
        if(!(args.length > 3)) {
            sender.sendMessage(GlobalVariables.error_prefix + "Not enough colors supplied in format 'f'!");
            return true;
        }
            setChatPaletteSender(sender, KeiichisCore.getPlugin().getServer().getPlayer(args[0]), args[1], args[2], args[3]);
        return true;
    }

    public static void setChatPaletteSender(CommandSender sender, Player player, String first, String second, String third) {
        if(first == null) {
            GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette("§f", "§f", "§f"));
            sender.sendMessage(GlobalVariables.success_prefix + "Set palette of '" + player.getName() + "' to '&f &f &f'!");
            return;
        }

        GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette('§' + first, '§' + second, '§' + third));
        sender.sendMessage(GlobalVariables.success_prefix + "Set palette of '" + player.getDisplayName() + "' to '" + first + " " + second + " " + third + "'!");
    }

    public static void setChatPaletteSender(CommandSender sender, Player player, ClassChatPalette pallete) {
        String first = pallete.prefix;
        String second = pallete.name;
        String third = pallete.chat;

        if(first == null) {
            GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette("§f", "§f", "§f"));
            sender.sendMessage(GlobalVariables.success_prefix + "Set palette of '" + player.getName() + "' to '&f &f &f'!");
            return;
        }

        GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette('§' + first, '§' + second, '§' + third));
        sender.sendMessage(GlobalVariables.success_prefix + "Set palette of '" + player.getDisplayName() + "' to '" + first + " " + second + " " + third + "'!");
    }

    public static void setChatPaletteSender(Player player, String first, String second, String third) {
        if(first == null) {
            GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette("§f", "§f", "§f"));
            return;
        }

        GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette('§' + first, '§' + second, '§' + third));
    }

    public static void setChatPalette(Player player, ClassChatPalette palette) {
        String first = palette.prefix;
        String second = palette.name;
        String third = palette.chat;

        if(first == null) {
            GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette("§f", "§f", "§f"));
            return;
        }

        GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette('§' + first, '§' + second, '§' + third));
    }
}
