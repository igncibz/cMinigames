package cibz.cMinigames.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpawnItemListener implements Listener {

    @EventHandler
    public void spawn(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(p.getItemInHand().getType() == Material.EMERALD){
            if(e.getAction() == Action.RIGHT_CLICK_AIR || (e.getAction() == Action.RIGHT_CLICK_BLOCK)){
                if(p.getItemInHand().hasItemMeta()){
                    if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9Shop")){
                        p.performCommand("shop");
                        e.setCancelled(true);
                    }
                }
            }
        }

        if(p.getItemInHand().getType() == Material.NETHER_STAR){
            if(e.getAction() == Action.RIGHT_CLICK_AIR || (e.getAction() == Action.RIGHT_CLICK_BLOCK)){
                if(p.getItemInHand().hasItemMeta()){
                    if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9Events")){
                        p.performCommand("event");
                        e.setCancelled(true);
                    }
                }
            }
        }

        if(p.getItemInHand().getType() == Material.DIAMOND_SWORD){
            if(e.getAction() == Action.RIGHT_CLICK_AIR || (e.getAction() == Action.RIGHT_CLICK_BLOCK)){
                if(p.getItemInHand().hasItemMeta()){
                    if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9Arena")){
                        p.performCommand("arena");
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
