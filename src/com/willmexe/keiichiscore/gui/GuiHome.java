package com.willmexe.keiichiscore.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiHome implements InventoryHolder {
    private Inventory inv;
    public static String gui_title;

    public GuiHome() {
        gui_title = "\uF805" + ChatColor.WHITE + "\uEff1\uF80A\uF806\uF80C\uF822§0Home \uF82A\uF802Sethome \uF828\uF827Remove";
        inv = Bukkit.createInventory(null, 27, gui_title);
    }

    public void init() {

        //inv.addItem(createItem("Item", Material.IRON_SWORD, new ArrayList<>()));
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
