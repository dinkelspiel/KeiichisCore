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

public class GuiCraftBook implements InventoryHolder {
    public static Inventory inv;
    public static String gui_title;

    public static List<String> addLoreRecipe(String unicode) {
        List<String> list = new ArrayList<>();
        list.add(unicode);
        for(var i = 0; i < 5 ; i ++) {
            list.add("");
        }
        return list;
    }

    public static void init() {
        gui_title = "Crafter's Handbook";
        inv = Bukkit.createInventory(null, 27, gui_title);
    }

    public static void add(String name, Material item, String recipe) {
        inv.addItem(ClassHelpers.createItem(name, item, addLoreRecipe("§f" + recipe)));
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
