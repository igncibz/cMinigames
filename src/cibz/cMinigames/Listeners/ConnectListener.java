package cibz.cMinigames.Listeners;

import cibz.cMinigames.Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Main.plugin.tpSpawn(p);
        e.setJoinMessage(null);
    }
}
