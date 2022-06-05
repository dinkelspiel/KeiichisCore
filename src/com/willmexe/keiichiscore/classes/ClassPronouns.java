package com.willmexe.keiichiscore.classes;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.KeiichisCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ClassPronouns {
    public static void setPronouns(Player player) {
        String pronoun = GlobalVariables.player_pronouns.get(player.getUniqueId().toString());
        if(pronoun == null) {
            GlobalVariables.player_pronouns.put(player.getUniqueId().toString(), "");
            GlobalVariables.player_color_pronouns.put(player.getUniqueId().toString(), "7");
            pronoun = "";
        }

        String fixed_title = "\uEF73";
        for(var i = 0; i < pronoun.length(); i++) {
            fixed_title += "\uF801" + GlobalVariables.letter_lookup.get(String.valueOf(pronoun.charAt(i)));
            if(GlobalVariables.letter_lookup.get(String.valueOf(pronoun.charAt(i))) == null) {
                player.sendMessage(GlobalVariables.error_prefix + "Invalid character '" + String.valueOf(pronoun.charAt(i)) + "' was used! Please remove this character and try again.");
                return;
            }
        }
        fixed_title += "\uF801\uEF73";
        fixed_title += "\uF823";

        if(GlobalVariables.player_color_pronouns.get(player.getUniqueId().toString()) != null)
            player.setPlayerListName(player.getDisplayName() + " §" + GlobalVariables.player_color_pronouns.get(player.getUniqueId().toString()) + fixed_title);
        else
            player.setPlayerListName(player.getDisplayName() + " §f" + fixed_title);
    }

    public static void loadPronouns() {
        if(KeiichisCore.all_players == null) {
            return;
        }

        for(var i : KeiichisCore.all_players) {
            GlobalVariables.player_pronouns.put(i, KeiichisCore.getPlugin().getConfig().getString("Pronouns." + i + ".Pronoun"));
            GlobalVariables.player_color_pronouns.put(i, KeiichisCore.getPlugin().getConfig().getString("Pronouns." + i + ".Color"));

            if(KeiichisCore.getPlugin().getServer().getPlayer(UUID.fromString(i)) != null)
                ClassPronouns.setPronouns(KeiichisCore.getPlugin().getServer().getPlayer(UUID.fromString(i)));
        }
    }

    public static void savePronouns() {
        HashMap<String, HashMap<String, String>> final_ = new HashMap<>();
        for(var i : GlobalVariables.player_pronouns.entrySet()) {
            HashMap<String, String> current_player = new HashMap<>();
            current_player.put("Pronoun", i.getValue());
            current_player.put("Color", GlobalVariables.player_color_pronouns.get(i.getKey()));

            final_.put(i.getKey(), current_player);
        }

        KeiichisCore.getPlugin().getConfig().set("Pronouns", final_);
    }
}
