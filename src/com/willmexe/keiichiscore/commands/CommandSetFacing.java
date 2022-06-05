
package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import com.willmexe.keiichiscore.gui.GuiSelect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetFacing implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must run this command as player!");
            return true;
        }

        Player player = (Player) sender;

        if(args.length < 1) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must supply atleast one argument!");
            return true;
        }

        GlobalVariables.player_facing.put(player.getUniqueId().toString(), args[0]);
        sender.sendMessage(GlobalVariables.success_prefix + "Set your prop facing variable to '" + args[0] + "'!");

        return true;
    }
}
