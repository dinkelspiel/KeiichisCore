
package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.gui.GuiSelect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandSetHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must run this command as player!");
            return true;
        }

        Player player = (Player) sender;

        if(GlobalVariables.player_homes.get(player.getUniqueId().toString()) == null) {
            GlobalVariables.player_homes.put(player.getUniqueId().toString(), new HashMap<String, Location>());
        }
        GlobalVariables.player_homes.get(player.getUniqueId().toString()).put(args[0], player.getLocation());
        player.sendMessage(GlobalVariables.success_prefix + "You have set your home " + args[0]);
        return true;
    }
}
