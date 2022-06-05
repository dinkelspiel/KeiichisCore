package com.willmexe.keiichiscore.events;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import com.willmexe.keiichiscore.gui.GuiCraftBook;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventsPlayerInteract implements Listener {
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player player = (Player) e.getPlayer();

        if (player.getItemInHand() == null) {
            return;
        }
        if(player.getItemInHand().getItemMeta() == null) {
            return;
        }

        if (player.getItemInHand().getItemMeta().getDisplayName().contains("Crafter's Handbook")) {
            player.openInventory(GuiCraftBook.inv);
        } else if(player.getItemInHand().getItemMeta().hasCustomModelData()){
            boolean opbefore = player.isOp();
            player.setOp(true);

            if(GlobalVariables.player_facing.get(player.getUniqueId().toString()) == null) {
                GlobalVariables.player_facing.put(player.getUniqueId().toString(), "2");
            }

            player.chat("/summon minecraft:item_frame " + player.getLocation().getX() + " " + player.getLocation().getY() + " " + player.getLocation().getZ() + " {Fixed: 1b, Facing:" + GlobalVariables.player_facing.get(player.getUniqueId().toString()) + ", Invisible: 1b, Item:{id:\"minecraft:firework_star\", Count: 1b, tag: {CustomModelData: " + player.getItemInHand().getItemMeta().getCustomModelData() + "}}}");
            player.setOp(opbefore);

            Integer placed_object_id = player.getItemInHand().getItemMeta().getCustomModelData();
            String placed_object = "";
            switch (placed_object_id) {
                case 1002:
                    placed_object = "Chair";
                    break;
                case 1003:
                    placed_object = "Rock 1";
                    break;
                case 1010:
                    placed_object = "Paper 1";
                    break;
                case 1011:
                    placed_object = "Paper 2";
                    break;
                case 1015:
                    placed_object = "Planks 1";
                    break;
                case 1016:
                    placed_object = "Planks 2";
                    break;
                default:
                    placed_object = "Undefined Object";
                    break;
            }

            player.sendMessage(GlobalVariables.success_prefix + "Placed prop '" + placed_object + "' at your location with rotation '" + GlobalVariables.player_facing.get(player.getUniqueId().toString()) + "'!");
        }
    }

}
