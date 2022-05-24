
package com.willmexe.keiichiscore.commands;

        import com.willmexe.keiichiscore.gui.GuiSelect;
        import org.bukkit.command.Command;
        import org.bukkit.command.CommandExecutor;
        import org.bukkit.command.CommandSender;
        import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;

            player.sendMessage("You commanded a poggers.");

            GuiSelect gui = new GuiSelect();

            player.openInventory(gui.getInventory());
            player.sendMessage("pog");
        }

        return true;
    }
}
