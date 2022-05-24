package com.willmexe.keiichiscore.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GuiSelect implements InventoryHolder {
    private Inventory inv;

    public GuiSelect() {
        inv = Bukkit.createInventory(null, 27, "\uF805" + ChatColor.WHITE + "\uEff0\uF80A\uF809§8Teleport");
    }

    private void init() {}

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
