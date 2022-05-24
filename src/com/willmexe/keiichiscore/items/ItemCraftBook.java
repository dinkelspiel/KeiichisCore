package com.willmexe.keiichiscore.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ItemCraftBook {
    public static String name = "§fCrafter's Handbook";
    public static ItemStack craftBook = new ItemStack(Material.BOOK);

    public static void init() {
        ItemMeta craftBookMeta =  craftBook.getItemMeta();
        craftBookMeta.setDisplayName(ItemCraftBook.name);
        craftBookMeta.setLore(Collections.singletonList("Right-Click to open the craft menu!"));
        craftBook.setItemMeta(craftBookMeta);
    }
}
