
package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.gui.GuiSelect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must run this command as player!");
            return true;
        }

        Player player = (Player) sender;

        if(GlobalVariables.spawn_loaded) {
            player.teleport(GlobalVariables.spawn_location);
            player.sendMessage(GlobalVariables.success_prefix + "Teleported you to spawn!");
        } else {
            player.sendMessage(GlobalVariables.error_prefix + "Spawn is not set on this server! Please contact a server administrator.");
        }

        return true;
    }
}
