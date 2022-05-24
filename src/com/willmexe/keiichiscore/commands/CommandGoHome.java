
package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.gui.GuiGoHome;
import com.willmexe.keiichiscore.gui.GuiSelect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandGoHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must run this command as player!");
            return true;
        }

        Player player = (Player) sender;

        if(!(args.length >= 1)) {
            player.sendMessage(GlobalVariables.error_prefix + "You have not specified a home!");
            return true;
        }

        if(GlobalVariables.player_homes.get(player.getUniqueId().toString()).get(args[0]) == null) {
            player.sendMessage(GlobalVariables.error_prefix + "You have not specified a valid home!");
            return true;
        }

        player.teleport(GlobalVariables.player_homes.get(player.getUniqueId().toString()).get(args[0]));

        player.sendMessage(GlobalVariables.success_prefix + "You teleported to your home '" + args[0] + "'!");

        return true;
    }
}
