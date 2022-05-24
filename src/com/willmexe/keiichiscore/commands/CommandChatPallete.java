
package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.classes.ClassChatPalette;
import com.willmexe.keiichiscore.gui.GuiSelect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChatPallete implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must execute this command as player!");
            return true;
        }

        if(!(args.length > 2)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must specify three colorvalues!");
            return true;
        }

        Player player = (Player) sender;

        GlobalVariables.player_chat_palettes.put(player.getUniqueId().toString(), new ClassChatPalette(args[0].replace('&', '§'), args[1].replace('&', '§'), args[2].replace('&', '§')));

        player.sendMessage(GlobalVariables.success_prefix + "You updated your chat palette!");

        return true;
    }
}
