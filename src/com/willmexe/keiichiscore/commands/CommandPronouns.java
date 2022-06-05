package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import com.willmexe.keiichiscore.gui.GuiPronouns;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

public class CommandPronouns implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must be a player to execute this command!");
            return true;
        }

        Player player = (Player) sender;
        GuiPronouns gui = new GuiPronouns();
        gui.init(player);
        player.openInventory(gui.getInventory());

        return true;
    }
}
