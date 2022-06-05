package com.willmexe.keiichiscore.events;

import com.willmexe.keiichiscore.GlobalVariables;
import com.willmexe.keiichiscore.classes.ClassHome;
import com.willmexe.keiichiscore.classes.ClassPronouns;
import com.willmexe.keiichiscore.gui.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EventsInventory implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null) {
            return;
        }

        Player player = (Player) e.getWhoClicked();
//
//        player.sendMessage(e.getView().getTitle());

        if(e.getView().getTitle() == GuiHome.gui_title) {
            e.setCancelled(true);

            player.closeInventory();

            if(e.getSlot() == 0 || e.getSlot() == 1 || e.getSlot() == 2 || e.getSlot() == 9 || e.getSlot() == 10 || e.getSlot() == 11 || e.getSlot() == 18 || e.getSlot() == 19 || e.getSlot() == 20) {
                //player.performCommand("gohome");
                ClassHome.goHome(player);
            }
            else if(e.getSlot() == 3 || e.getSlot() == 4 || e.getSlot() == 5 || e.getSlot() == 12 || e.getSlot() == 13 || e.getSlot() == 14 || e.getSlot() == 21 || e.getSlot() == 22 || e.getSlot() == 23) {
                //player.performCommand("sethome");
                ClassHome.setHomeInit(player);
            }
            else if(e.getSlot() == 6 || e.getSlot() == 7 || e.getSlot() == 8 || e.getSlot() == 15 || e.getSlot() == 16 || e.getSlot() == 17 || e.getSlot() == 24 || e.getSlot() == 25 || e.getSlot() == 26) {
                //player.performCommand("removehome");
                ClassHome.delHome(player);
            }
        } else if (e.getView().getTitle() == GuiGoHome.gui_title) {
            e.setCancelled(true);

            var inv = e.getClickedInventory();

            if(GlobalVariables.player_homes.get(player.getUniqueId().toString()) == null)
                return;

            if(e.getSlot() < GlobalVariables.player_homes.get(player.getUniqueId().toString()).size()) {
                var key = GlobalVariables.player_homes.get(player.getUniqueId().toString()).keySet().toArray()[e.getSlot()];
                player.teleport(GlobalVariables.player_homes.get(player.getUniqueId().toString()).get(key));

                player.closeInventory();
                player.sendMessage(GlobalVariables.success_prefix + "You teleported to your home '" + key + "'!");
            }
        } else if (e.getView().getTitle() == GuiDelHome.gui_title) {
            e.setCancelled(true);

            var inv = e.getClickedInventory();

            if(GlobalVariables.player_homes.get(player.getUniqueId().toString()) == null)
                return;

            if(e.getSlot() < GlobalVariables.player_homes.get(player.getUniqueId().toString()).size()) {
                var key = GlobalVariables.player_homes.get(player.getUniqueId().toString()).keySet().toArray()[e.getSlot()];
                GlobalVariables.player_homes.get(player.getUniqueId().toString()).remove(key);

                player.closeInventory();
                player.sendMessage(GlobalVariables.success_prefix + "You removed your home '" + key + "'!");
            }
        } else if (e.getView().getTitle() == GuiCraftBook.gui_title) {
            e.setCancelled(true);
        } else if(e.getView().getTitle() == GuiPronouns.gui_title) {
            e.setCancelled(true);
            if(e.getSlot() == 1) {
                player.openInventory(GuiSetPronouns.getInventory());
            } else if(e.getSlot() == 2) {
                GlobalVariables.player_chat_input.put(player.getUniqueId().toString(), "custom_pronoun_color");
                player.sendMessage(GlobalVariables.alert_prefix + "Type the new pronoun color in chat using the minecraft color system (§11, §22, §33, §ff, §cc, §dd§e). misuse of this command will result in a one week penalty:");
                player.closeInventory();
            }
        } else if(e.getView().getTitle() == GuiSetPronouns.gui_title) {
            e.setCancelled(true);
            String set_pronoun = null;
            if(e.getSlot() == 0) {
                set_pronoun = "He/Him";
            } else if(e.getSlot() == 1) {
                set_pronoun = "She/Her";
            } else if(e.getSlot() == 2) {
                set_pronoun = "They/Them";
            } else if(e.getSlot() == 3) {
                set_pronoun = "It/Its";
            } else if(e.getSlot() == 4) {
                set_pronoun = "Any";
            } else if(e.getSlot() == 8) {
                GlobalVariables.player_chat_input.put(player.getUniqueId().toString(), "custom_pronoun");
                player.sendMessage(GlobalVariables.alert_prefix + "Type the new pronoun in chat:");
                player.closeInventory();
            }
            if(set_pronoun != null) {
                GlobalVariables.player_pronouns.put(player.getUniqueId().toString(), set_pronoun);
                player.sendMessage(GlobalVariables.success_prefix + "Your global pronouns are now '" + set_pronoun + "'!");
                ClassPronouns.setPronouns(player);
                player.closeInventory();
            }
        }
    }
}
