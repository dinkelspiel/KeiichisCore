package com.willmexe.keiichiscore.gui;

import com.willmexe.keiichiscore.classes.ClassHelpers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiSetPronouns {
    private static Inventory inv;
    public static String gui_title;

    public static void init() {
        gui_title = "§6Set Pronouns";
        inv = Bukkit.createInventory(null, 9, gui_title);
        inv.addItem(ClassHelpers.createItem("He/Him", Material.GREEN_CONCRETE, Collections.singletonList("§fClick to set He/Him as your pronouns.")));
        inv.addItem(ClassHelpers.createItem("She/Her", Material.GREEN_CONCRETE, Collections.singletonList("§fClick to set She/Her as your pronouns.")));
        inv.addItem(ClassHelpers.createItem("They/Them", Material.GREEN_CONCRETE, Collections.singletonList("§fClick to set They/Them as your pronouns.")));
        inv.addItem(ClassHelpers.createItem("It/Its", Material.GREEN_CONCRETE, Collections.singletonList("§fClick to set It/Its as your pronouns.")));
        inv.setItem(8, ClassHelpers.createItem("Custom", Material.FEATHER, Collections.singletonList("§fClick to set your custom pronouns.")));
    }

    public static Inventory getInventory() {
        return inv;
    }
}
