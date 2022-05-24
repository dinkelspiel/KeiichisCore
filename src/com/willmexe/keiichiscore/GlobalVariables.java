package com.willmexe.keiichiscore;

import com.willmexe.keiichiscore.classes.ClassChatPalette;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.*;


public class GlobalVariables {
    public static String success_prefix = "§a§l(✔) §a";
    public static String alert_prefix = "§e§l(!) §e";
    public static String error_prefix = "§c§l(X) §c";

    public static String player_join = "§8[§a+§8] <player>";
    public static String player_leave = "§8[§c-§8] <player>";

    public static String title = "[Cherry] ";

    public static boolean spawn_loaded = false;
    public static Location spawn_location;

    public static HashMap<String, String> player_titles = new HashMap<String, String>();
    public static HashMap<String, ClassChatPalette> player_chat_palettes = new HashMap<String, ClassChatPalette>();

    public static HashMap<String, String> player_chat_input = new HashMap<String, String>();

    public static HashMap<String, String> letter_lookup = new HashMap<String, String>();

    public static HashMap<String, HashMap<String, Location>> player_homes = new HashMap<String, HashMap<String, Location>>();
    public static HashMap<String, HashMap<String, String>> player_home_worlds = new HashMap<>();

    public static void init() {
        letter_lookup.put("a", "\uEf01");
        letter_lookup.put("b", "\uEf02");
        letter_lookup.put("c", "\uEf03");
        letter_lookup.put("d", "\uEf04");
        letter_lookup.put("e", "\uEf05");
        letter_lookup.put("f", "\uEf06");
        letter_lookup.put("g", "\uEf07");
        letter_lookup.put("h", "\uEf08");
        letter_lookup.put("i", "\uEf09");
        letter_lookup.put("j", "\uEf10");
        letter_lookup.put("k", "\uEf11");
        letter_lookup.put("l", "\uEf12");
        letter_lookup.put("m", "\uEf13");
        letter_lookup.put("n", "\uEf14");
        letter_lookup.put("o", "\uEf15");
        letter_lookup.put("p", "\uEf16");
        letter_lookup.put("q", "\uEf17");
        letter_lookup.put("r", "\uEf18");
        letter_lookup.put("s", "\uEf19");
        letter_lookup.put("t", "\uEf20");
        letter_lookup.put("u", "\uEf21");
        letter_lookup.put("v", "\uEf22");
        letter_lookup.put("w", "\uEf23");
        letter_lookup.put("x", "\uEf24");
        letter_lookup.put("y", "\uEf25");
        letter_lookup.put("z", "\uEf26");

        Bukkit.getConsoleSender().sendMessage(GlobalVariables.alert_prefix + "Loading Spawn...");
        World world = Bukkit.getWorld(KeiichisCore.getPlugin().getConfig().getString("Spawn.W"));
        double x = (double) KeiichisCore.getPlugin().getConfig().getInt("Spawn.X");
        double y = (double) KeiichisCore.getPlugin().getConfig().getInt("Spawn.Y");
        double z = (double) KeiichisCore.getPlugin().getConfig().getInt("Spawn.Z");
        double pitch = (double) KeiichisCore.getPlugin().getConfig().getInt("Spawn.Pitch");
        double yaw = (double) KeiichisCore.getPlugin().getConfig().getInt("Spawn.Yaw");
        if(world == null) {
            world = Bukkit.getWorld("world");
        }

        Location location = new Location(world, x, y, z);
        location.setPitch((float)pitch);
        location.setYaw((float)yaw);

        GlobalVariables.spawn_loaded = true;
        GlobalVariables.spawn_location = location;

        Bukkit.getConsoleSender().sendMessage(GlobalVariables.success_prefix + "Loaded Spawn!");
    }

    public static void save() {
        HashMap<String, Object> final_spawn_location = new HashMap<>();

        if(spawn_location != null) {
            final_spawn_location.put("W", spawn_location.getWorld().getName());
            final_spawn_location.put("X", spawn_location.getX());
            final_spawn_location.put("Y", spawn_location.getY());
            final_spawn_location.put("Z", spawn_location.getZ());
            final_spawn_location.put("Pitch", spawn_location.getPitch());
            final_spawn_location.put("Yaw", spawn_location.getYaw());
        } else {
            final_spawn_location.put("W", "world");
            final_spawn_location.put("X", 0);
            final_spawn_location.put("Y", 0);
            final_spawn_location.put("Z", 0);
            final_spawn_location.put("Pitch", 0);
            final_spawn_location.put("Yaw", 0);
        }

        KeiichisCore.getPlugin().getConfig().set("Spawn", final_spawn_location);
    }
}
