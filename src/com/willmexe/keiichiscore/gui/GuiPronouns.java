package com.willmexe.keiichiscore.gui;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.classes.ClassHelpers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiPronouns {
    private Inventory inv;
    public static String gui_title;

    public GuiPronouns() {
        gui_title = "§6Pronouns";
    }

    public void init(Player player) {
        inv = Bukkit.createInventory(null, 9, gui_title);
        if(GlobalVariables.player_pronouns.get(player.getUniqueId().toString()) != "") {
            inv.setItem(0, ClassHelpers.createItem(GlobalVariables.player_pronouns.get(player.getUniqueId().toString()), Material.GREEN_CONCRETE, null));
        } else {
            inv.setItem(0, ClassHelpers.createItem("Pronouns not set", Material.BLACK_CONCRETE, null));
        }
        inv.setItem(1, ClassHelpers.createItem("Set Pronouns", Material.GREEN_CONCRETE, Collections.singletonList("§fClick to set your pronouns.")));
        inv.setItem(2, ClassHelpers.createItem("Set Pronouns Color", Material.LIGHT_BLUE_DYE , Collections.singletonList("§fClick to set your pronouns color.")));
    }

    public Inventory getInventory() {
        return inv;
    }
}
