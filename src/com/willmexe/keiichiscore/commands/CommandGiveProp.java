
package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import com.willmexe.keiichiscore.gui.GuiSelect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandGiveProp implements CommandExecutor {
    public static HashMap<String, Integer> props = new HashMap<>();

    public static void init() {
        props.put("chair", 1002);
        props.put("rock_1", 1003);
        props.put("paper_1", 1010);
        props.put("paper_2", 1011);
        props.put("planks_1", 1015);
        props.put("planks_2", 1016);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if((!(sender instanceof Player))) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must perform this command as player!");
            return true;
        }

        if(args.length < 2) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must supply atleast two argument!");
            sender.sendMessage(GlobalVariables.error_prefix + "Usage: /giveprop <player> <prop>");
            return true;
        }

        if(!KeiichisCore.getPlugin().getServer().getOnlinePlayers().contains(KeiichisCore.getPlugin().getServer().getPlayer(args[0]))) {
            sender.sendMessage(GlobalVariables.error_prefix + "First argument must be an online player!");
            return true;
        }

        if(!props.keySet().contains(args[1])) {
            sender.sendMessage(GlobalVariables.error_prefix + "Second argument is not a valid prop!");
            return true;
        }

        Player player = (Player) sender;

        boolean opbefore = player.isOp();

        player.chat("/give " + args[0] + " minecraft:firework_star{CustomModelData: " + props.get(args[1]) +", display: {Name:\"" + args[1] + "\"}}");
        if(!args[0].equalsIgnoreCase(player.getName())) {
            player.sendMessage(GlobalVariables.success_prefix + "You gave '" + args[0] + "' the prop '" + args[1] + "'!");
        } else {
            player.sendMessage(GlobalVariables.success_prefix + "You gave yourself the prop '" + args[1] + "'!");
        }

        return true;
    }
}
