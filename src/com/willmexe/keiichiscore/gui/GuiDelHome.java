package com.willmexe.keiichiscore.gui;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.classes.ClassHelpers;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GuiDelHome implements InventoryHolder {
    private Inventory inv;
    public static String gui_title;

    public GuiDelHome() {
        gui_title = "Remove Homes";
        inv = Bukkit.createInventory(null, 27, gui_title);
    }

    private void init() {}

    public void updateInventory(Player player) {
        if(GlobalVariables.player_homes.get(player.getUniqueId().toString()) == null) {
            return;
        }

        for (Map.Entry<String, Location> location : GlobalVariables.player_homes.get(player.getUniqueId().toString()).entrySet()) {
            List<String> lore = new ArrayList<String>();
            lore.add("§e§l(W) §e" + location.getValue().getWorld().getName());
            lore.add("§e§l(X) §e" + (int)location.getValue().getX());
            lore.add("§e§l(Y) §e" + (int)location.getValue().getY());
            lore.add("§e§l(Z) §e" + (int)location.getValue().getZ());

            ItemStack item = ClassHelpers.createItem("§6" + location.getKey(), Material.GREEN_CONCRETE, lore);

            inv.addItem(item);
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
