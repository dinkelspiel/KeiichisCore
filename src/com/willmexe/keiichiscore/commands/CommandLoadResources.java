package com.willmexe.keiichiscore.commands;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.items.ItemCraftBook;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

import static com.willmexe.keiichiscore.items.ItemCraftBook.craftBook;

public class CommandLoadResources implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalVariables.error_prefix + "You must run this command as player!");
            return true;
        }

        Player player = (Player) sender;

        player.setResourcePack("https://github.com/shykeiichi/plugin-resourcepack/blob/main/release.zip?raw=true");
        player.sendMessage(GlobalVariables.success_prefix + "Loaded the server resourcepack!");

        return true;
    }
}
