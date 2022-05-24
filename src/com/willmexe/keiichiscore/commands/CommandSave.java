
package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.KeiichisCore;
import com.willmexe.keiichiscore.gui.GuiSelect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSave implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        KeiichisCore.Save();

        return true;
    }
}
