package com.willmexe.keiichiscore.tabcompletes;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import com.willmexe.keiichiscore.commands.CommandGiveProp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabCompleterGiveProp implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1) {
            List<String> arguments = new ArrayList<>();

            for(var i : KeiichisCore.getPlugin().getServer().getOnlinePlayers()) {
                arguments.add(i.getName());
            }

            return arguments;
        } else if(args.length == 2) {
            return new ArrayList<>(CommandGiveProp.props.keySet());
        }

        return null;
    }
}
