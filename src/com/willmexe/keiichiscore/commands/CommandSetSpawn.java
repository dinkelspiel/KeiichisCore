
package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.gui.GuiSelect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandSetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must run this command as player!");
            return true;
        }

        Player player = (Player) sender;
        if(player.isOp()) {
            GlobalVariables.spawn_location = player.getLocation();
            GlobalVariables.spawn_loaded = true;
            player.sendMessage(GlobalVariables.success_prefix + "You have set the server spawn.");
        } else {
            player.sendMessage(GlobalVariables.error_prefix + "You do not have the required permissions. Contact a server administrator if this is a mistake.");
        }

        return true;
    }
}
