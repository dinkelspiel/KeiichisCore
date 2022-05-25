package com.willmexe.keiichiscore;

import com.willmexe.keiichiscore.classes.ClassHome;
import com.willmexe.keiichiscore.commands.*;
import com.willmexe.keiichiscore.events.*;
import com.willmexe.keiichiscore.gui.GuiCraftBook;
import com.willmexe.keiichiscore.items.ItemCraftBook;
import com.willmexe.keiichiscore.tabcompletes.TabCompleterGoHome;
import com.willmexe.keiichiscore.tabcompletes.TabCompleterRemoveHome;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class KeiichisCore extends JavaPlugin {

    private static KeiichisCore instance;

    public static List<String> all_players = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        getCommand("test").setExecutor(new CommandTest());
        getCommand("chattitle").setExecutor(new CommandChatTitle());
        getCommand("chatpalette").setExecutor(new CommandChatPallete());

        getCommand("home").setExecutor(new CommandHome());

        getCommand("gohome").setExecutor(new CommandGoHome());
        getCommand("sethome").setExecutor(new CommandSetHome());
        getCommand("removehome").setExecutor(new CommandRemoveHome());

        getCommand("spawn").setExecutor(new CommandSpawn());
        getCommand("setspawn").setExecutor(new CommandSetSpawn());
        getCommand("loadconfig").setExecutor(new CommandLoadConfig());

        getCommand("save").setExecutor(new CommandSave());

        getCommand("craft").setExecutor(new CommandCraft());

        getCommand("gohome").setTabCompleter(new TabCompleterGoHome());
        getCommand("removehome").setTabCompleter(new TabCompleterRemoveHome());

        getServer().getPluginManager().registerEvents(new EventsInventory(), this);
        getServer().getPluginManager().registerEvents(new EventsChat(), this);
        getServer().getPluginManager().registerEvents(new EventsPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new EventsPlayerSpawn(), this);
        getServer().getPluginManager().registerEvents(new EventsPlayerPlaceBlock(), this);

        GlobalVariables.init();
        ItemCraftBook.init();
        GuiCraftBook.init();

        GuiCraftBook.add("§fBuilder's Wand", Material.NETHERITE_AXE, "\uEFD8");

        getServer().getConsoleSender().sendMessage(GlobalVariables.title + "Initialized");

        String path = null;
        try {
            path = new File(KeiichisCore.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        var patharr = path.split("\\\\");

        var new_patharr = Arrays.copyOf(patharr, patharr.length - 1);
        var pathstr = String.join("\\", new_patharr) + "\\";

        try {
            Bukkit.getConsoleSender().sendMessage(GlobalVariables.alert_prefix + "Loading Config...");
            getPlugin().getConfig().load(pathstr + "KeiichisCore\\config.yml");
            Bukkit.broadcastMessage(pathstr + "KeiichisCore\\config.yml");
            Bukkit.getConsoleSender().sendMessage(GlobalVariables.success_prefix + "Loaded Config!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        all_players = (List<String>) getPlugin().getConfig().getList("all_players");
        ClassHome.loadHomes();
    }

    @Override
    public void onDisable() {
        Save();
        getServer().getConsoleSender().sendMessage(GlobalVariables.title + "Disabled");
    }

    public static KeiichisCore getPlugin() {
        return instance;
    }

    public static void Save() {
        Bukkit.getConsoleSender().sendMessage(GlobalVariables.title + "Saving...");

        getPlugin().getConfig().set("all_players", all_players);

        ClassHome.saveHomes();
        GlobalVariables.save();

        getPlugin().saveConfig();
        Bukkit.getConsoleSender().sendMessage(GlobalVariables.title + "Saving Complete.");
    }
}
