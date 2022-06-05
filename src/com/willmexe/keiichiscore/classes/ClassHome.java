package com.willmexe.keiichiscore.classes;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import com.willmexe.keiichiscore.gui.GuiDelHome;
import com.willmexe.keiichiscore.gui.GuiGoHome;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class ClassHome {
    public static void goHome(Player player)  {
        GuiGoHome gui = new GuiGoHome();
        gui.updateInventory(player);

        player.openInventory(gui.getInventory());
    }

    public static void setHomeInit(Player player) {
        GlobalVariables.player_chat_input.put(player.getUniqueId().toString(), "sethome");
        player.sendMessage(GlobalVariables.alert_prefix + "Type what you want your home to be called in chat:");
    }

    public static void setHome(Player player, String home) {
        if(GlobalVariables.player_homes.get(player.getUniqueId().toString()) == null) {
            GlobalVariables.player_homes.put(player.getUniqueId().toString(), new HashMap<String, Location>());
        }
        GlobalVariables.player_homes.get(player.getUniqueId().toString()).put(home, player.getLocation());
        player.sendMessage(GlobalVariables.success_prefix + "You have set your home " + home);
    }
    public static void delHome(Player player) {
        GuiDelHome gui = new GuiDelHome();
        gui.updateInventory(player);

        player.openInventory(gui.getInventory());
    }

    public static void saveHomes() {
        HashMap<String, HashMap<String, HashMap<String, Object>>> final_ = new HashMap<String, HashMap<String, HashMap<String, Object>>>();
        for(var i : GlobalVariables.player_homes.entrySet()) {
            KeiichisCore.getPlugin().getConfig().set("Homes_register." + i.getKey(), new ArrayList<String>(i.getValue().keySet()));

            HashMap<String, HashMap<String, Object>> current_player = new HashMap<>();
            for(var j : i.getValue().entrySet()) {
                HashMap<String, Object> current_home = new HashMap<>();
                current_home.put("W", j.getValue().getWorld().getName());
                current_home.put("X", j.getValue().getX());
                current_home.put("Y", j.getValue().getY());
                current_home.put("Z", j.getValue().getZ());
                current_home.put("Pitch", j.getValue().getPitch());
                current_home.put("Yaw", j.getValue().getYaw());

                current_player.put(j.getKey(), current_home);
            }

            final_.put(i.getKey(), current_player);
        }

        KeiichisCore.getPlugin().getConfig().set("Homes", final_);
    }

    public static void loadHomes() {
        if(KeiichisCore.all_players == null) {
            return;
        }

        for(var i : KeiichisCore.all_players) {
            GlobalVariables.player_homes.put(i, new HashMap<String, Location>());
            if(KeiichisCore.getPlugin().getConfig().getList("Homes_register." + i) == null) {
                continue;
            }
            for(var j : KeiichisCore.getPlugin().getConfig().getList("Homes_register." + i)) {
                World world = Bukkit.getWorld(KeiichisCore.getPlugin().getConfig().getString("Homes." + i + "." + j + ".W"));
                double x = (double) KeiichisCore.getPlugin().getConfig().getInt("Homes." + i + "." + j + ".X");
                double y = (double) KeiichisCore.getPlugin().getConfig().getInt("Homes." + i + "." + j + ".Y");
                double z = (double) KeiichisCore.getPlugin().getConfig().getInt("Homes." + i + "." + j + ".Z");
                double pitch = (double) KeiichisCore.getPlugin().getConfig().getInt("Homes." + i + "." + j + ".Pitch");
                double yaw = (double) KeiichisCore.getPlugin().getConfig().getInt("Homes." + i + "." + j + ".Yaw");
                if(world == null) {
                    world = Bukkit.getWorld("world");
                }

                Location location = new Location(world, x, y, z);
                location.setPitch((float)pitch);
                location.setYaw((float)yaw);

                GlobalVariables.player_homes.get(i).put((String) j, location);

                // KeiichisCore.getPlugin().getServer().broadcastMessage(GlobalVariables.success_prefix + "Loaded home " + j + "!");
            }
        }
    }
}
