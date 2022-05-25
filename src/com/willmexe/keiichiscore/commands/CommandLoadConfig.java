package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.classes.ClassHome;
import com.willmexe.keiichiscore.gui.GuiSelect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandLoadConfig implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ClassHome.loadHomes();
        GlobalVariables.init();
        sender.sendMessage(GlobalVariables.success_prefix + "Loaded the config!");
        return true;
    }
}
