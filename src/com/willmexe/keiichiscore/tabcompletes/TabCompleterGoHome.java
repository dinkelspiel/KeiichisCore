package com.willmexe.keiichiscore.tabcompletes;

import com.willmexe.keiichiscore.GlobalVariables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabCompleterGoHome implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1) {

            List<String> arguments = new ArrayList<>();

            for(var i : GlobalVariables.player_homes.get(player.getUniqueId().toString()).entrySet()) {
                arguments.add(i.getKey());
            }

            return arguments;
        }

        return null;
    }
}
