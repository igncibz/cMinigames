package cibz.cMinigames.Events;

import cibz.cMinigames.Main.Economy;
import cibz.cMinigames.Main.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SumoEvent implements Listener {

    public static Player p1;
    public static Player p2;
    public static boolean joinable = true;
    public static ArrayList<UUID> delay = new ArrayList<>();
    public static ArrayList<UUID> players = new ArrayList<>();
    public static ArrayList<UUID> fighting = new ArrayList<>();

    public static void startSumo(List<UUID> players){
        int size = players.size();
        int random = new Random().nextInt(size);
        int random2 = new Random().nextInt(size);
        UUID chosen = players.get(random);
        UUID chosen2 = players.get(random2);
        p1 = Bukkit.getServer().getPlayer(chosen);
        p2 = Bukkit.getServer().getPlayer(chosen2);

        if(p1 != p2){
            sendChosen(p1, p2);
        } else {
            startSumo(players);
        }
    }

    public static void countdownSumo(){

        new BukkitRunnable() {
            int counter = 60;
            public void run() {
                counter--;
                if(counter == 59){
                    for(Player online : Bukkit.getServer().getOnlinePlayers()){
                        startingJson(online);
                    }
                    joinable = true;
                }
                if (counter == 30){
                    for(Player online : Bukkit.getServer().getOnlinePlayers()){
                        thirtySecsJson(online);
                    }
                }
                if (counter == 5){
                    for(Player online : Bukkit.getServer().getOnlinePlayers()){
                        fiveSecsJson(online);
                    }
                }
                if (counter == 4){
                    for(Player online : Bukkit.getServer().getOnlinePlayers()){
                        fourSecsJson(online);
                    }
                }
                if (counter == 3){
                    for(Player online : Bukkit.getServer().getOnlinePlayers()){
                        threeSecsJson(online);
                    }
                }
                if (counter == 2){
                    for(Player online : Bukkit.getServer().getOnlinePlayers()){
                        twoSecsJson(online);
                    }
                }
                if (counter == 1){
                    for(Player online : Bukkit.getServer().getOnlinePlayers()){
                        oneSecsJson(online);
                    }
                }
                if(counter == 0){
                    if(players.size() >= 2){
                        joinable = false;
                        startSumo(players);
                        Bukkit.broadcastMessage("§bThe sumo event has started, you will no longer be able to join!");
                    } else if(players.size() == 1){
                        for (UUID joined : players){
                            Player only = Bukkit.getServer().getPlayer(joined);
                            players.clear();
                            joinable = false;
                            Main.plugin.tpSpawn(only);
                            Bukkit.broadcastMessage("§bThere wasn't enough players to start the event.");
                        }
                    } else {
                        Bukkit.broadcastMessage("§bThere wasn't enough players to start the event.");
                        joinable = false;
                        players.clear();
                    }
                }
            }
        }.runTaskTimer(Main.plugin, 0, 20);
    }

    //LOCATIONS
    public static void sendChosen(Player p1, Player p2){
        delay.add(p1.getUniqueId());
        delay.add(p2.getUniqueId());
        fighting.add(p1.getUniqueId());
        fighting.add(p2.getUniqueId());

        World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Events." + "sumo" + ".lobby.world"));
        double x = Main.plugin.getConfig().getDouble("Events." + "sumo" + ".p1.x");
        double y = Main.plugin.getConfig().getDouble("Events." + "sumo" + ".p1.y");
        double z = Main.plugin.getConfig().getDouble("Events." + "sumo" + ".p1.z");
        float yaw = Main.plugin.getConfig().getInt("Events." + "sumo" + ".p1.yaw");
        float pitch = Main.plugin.getConfig().getInt("Events." + "sumo" + ".p1.yaw");

        World w2 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Events." + "sumo" + ".lobby.world"));
        double x2 = Main.plugin.getConfig().getDouble("Events." + "sumo" + ".p2.x");
        double y2 = Main.plugin.getConfig().getDouble("Events." + "sumo" + ".p2.y");
        double z2 = Main.plugin.getConfig().getDouble("Events." + "sumo" + ".p2.z");
        float yaw2 = Main.plugin.getConfig().getInt("Events." + "sumo" + ".p2.yaw");
        float pitch2 = Main.plugin.getConfig().getInt("Events." + "sumo" + ".p2.pitch");

        p1.teleport(new Location(w, x, y, z, yaw, pitch));
        p2.teleport(new Location(w2, x2, y2, z2, yaw2, pitch2));

        new BukkitRunnable() {
            int counter = 4;
            public void run() {
                counter--;
                if(counter == 3){
                    p1.sendMessage("§b3");
                    p2.sendMessage("§b3");
                }
                if(counter == 2){
                    p1.sendMessage("§b2");
                    p2.sendMessage("§b2");
                }
                if(counter == 1){
                    p1.sendMessage("§b1");
                    p2.sendMessage("§b1");
                }
                if(counter == 0){
                    p1.sendMessage("§9Fight!");
                    p2.sendMessage("§9Fight!");
                    delay.remove(p1.getUniqueId());
                    delay.remove(p2.getUniqueId());
                }
            }
        }.runTaskTimer(Main.plugin, 0, 20);
    }

    public static void sendLobby(Player p){
        World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Events." + "sumo" + ".lobby.world"));
        double x = Main.plugin.getConfig().getDouble("Events." + "sumo" + ".lobby.x");
        double y = Main.plugin.getConfig().getDouble("Events." + "sumo" + ".lobby.y");
        double z = Main.plugin.getConfig().getDouble("Events." + "sumo" + ".lobby.z");

        ItemStack sumo = new ItemStack(Material.REDSTONE);
        ItemMeta smeta = sumo.getItemMeta();
        smeta.setDisplayName("§bLeave event");
        sumo.setItemMeta(smeta);

        players.add(p.getUniqueId());
        p.getInventory().setItem(8, sumo);
        p.teleport(new Location(w, x, y, z));

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            if (players.contains(online.getUniqueId())) {
                int total = players.size();
                online.sendMessage("§9" + p.getName() + " §bhas joined. " + "§8[§9" + total + "§8]");
            }
        }
    }

    //LISTENERS
    @EventHandler
    public void sumoLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (players.size() != 0) {
            if (p == p1) {
                sendLobby(p2);
                fighting.remove(p2.getUniqueId());
                if (players.size() == 1) {
                    Player winner = Bukkit.getServer().getPlayer(players.get(0));
                    winner.sendMessage("§aYou won the sumo tournament!");

                    for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                        online.sendMessage("§8§m" + "--------------------------");
                        online.sendMessage("§a" + winner.getName() + " §ahas won sumo!");
                        online.sendMessage("§8§m" + "--------------------------");
                    }

                    Main.plugin.tpSpawn(p1);
                    Main.plugin.tpSpawn(p2);
                    players.remove(winner.getUniqueId());
                    Economy.addToBalance(winner, 500);
                    winner.sendMessage("§bYou have earned 500 tokens from winning sumo!");
                } else {
                    startSumo(players);
                }

            } else {
                if (p == p2) {
                    sendLobby(p1);
                    fighting.remove(p1.getUniqueId());
                    if (players.size() == 1) {
                        Player winner = Bukkit.getServer().getPlayer(players.get(0));
                        winner.sendMessage("§aYou won the sumo tournament!");

                        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                            online.sendMessage("§8§m" + "--------------------------");
                            online.sendMessage("§a" + winner.getName() + " §ahas won sumo!");
                            online.sendMessage("§8§m" + "--------------------------");
                        }

                        Main.plugin.tpSpawn(p1);
                        Main.plugin.tpSpawn(p2);
                        players.remove(winner.getUniqueId());
                        Economy.addToBalance(winner, 500);
                        winner.sendMessage("§bYou have earned 500 tokens from winning sumo!");
                    } else {
                        startSumo(players);
                    }
                }
            }
        }
    }

    @EventHandler
    public void sumoMove(PlayerMoveEvent e) {
        Material m = e.getPlayer().getLocation().getBlock().getType();
        Player p = e.getPlayer();
        if (players.size() != 0) {
            if(players.contains(p.getUniqueId())) {
                if (m == Material.STATIONARY_WATER || m == Material.WATER) {
                    if(fighting.contains(p.getUniqueId())){
                        if (p == p1) {
                            sendLobby(p1);
                            sendLobby(p2);
                            fighting.remove(p1.getUniqueId());
                            fighting.remove(p2.getUniqueId());
                            players.remove(p.getUniqueId());
                            p1.sendMessage("§cYou have been eliminated by §f" + p2.getName() + "§c.");

                            if (players.size() == 1) {
                                Player winner = Bukkit.getServer().getPlayer(players.get(0));
                                winner.sendMessage("§aYou won the sumo tournament!");

                                for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                                    online.sendMessage("§8§m" + "--------------------------");
                                    online.sendMessage("§a" + winner.getName() + " §ahas won sumo!");
                                    online.sendMessage("§8§m" + "--------------------------");
                                }

                                Main.plugin.tpSpawn(p1);
                                Main.plugin.tpSpawn(p2);
                                players.remove(winner.getUniqueId());
                                Economy.addToBalance(winner, 500);
                                winner.sendMessage("§bYou have earned 500 tokens from winning sumo!");
                            } else {
                                startSumo(players);
                            }

                        } else {
                            if (p == p2) {
                                sendLobby(p1);
                                sendLobby(p2);
                                fighting.remove(p1.getUniqueId());
                                fighting.remove(p2.getUniqueId());
                                players.remove(p.getUniqueId());
                                p2.sendMessage("§cYou have been eliminated by §f" + p1.getName() + "§c.");

                                if (players.size() == 1) {
                                    Player winner = Bukkit.getServer().getPlayer(players.get(0));
                                    winner.sendMessage("§aYou won the sumo tournament!");

                                    for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                                        online.sendMessage("§8§m" + "--------------------------");
                                        online.sendMessage("§a" + winner.getName() + " §ahas won sumo!");
                                        online.sendMessage("§8§m" + "--------------------------");
                                    }

                                    Main.plugin.tpSpawn(p1);
                                    Main.plugin.tpSpawn(p2);
                                    players.remove(winner.getUniqueId());
                                    Economy.addToBalance(winner, 500);
                                    winner.sendMessage("§bYou have earned 500 tokens from winning sumo!");
                                } else {
                                    startSumo(players);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void sumoDMG(EntityDamageByEntityEvent e) {
        if ((e.getDamager() instanceof Player) && ((e.getEntity() instanceof Player))) {
            final Player p = (Player) e.getEntity();
            if(p.getGameMode() != GameMode.CREATIVE) {
                if (fighting.contains(p.getUniqueId())) {
                    e.setDamage(0.00);
                }
            }
        }
    }

    @EventHandler
    public void delayMove(PlayerMoveEvent e) {
        if (e.getTo().getBlockX() == e.getFrom().getBlockX() && e.getTo().getBlockY() == e.getFrom().getBlockY() && e.getTo().getBlockZ() == e.getFrom().getBlockZ()) return; //The player hasn't moved
        if (delay.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void Items(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.REDSTONE) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§bLeave event")){
                    if(players.contains(p.getUniqueId())) {
                        if(!fighting.contains(p.getUniqueId())){
                            players.remove(p.getUniqueId());
                            Main.plugin.tpSpawn(p);
                            p.sendMessage("§bYou left the event.");
                        } else {
                            p.sendMessage("§7You can't leave while in a fight.");
                        }
                    }
                }
            }
        }
    }

    //MESSAGES
    public static void startingJson(Player p) {
        TextComponent message = new TextComponent("§bA sumo event is starting, Click to join!");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/event join sumo"));
        p.spigot().sendMessage( message );
    }
    public static void thirtySecsJson(Player p) {
        TextComponent message = new TextComponent("§bA sumo event is starting in 30 seconds, Click to join!");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/event join sumo"));
        p.spigot().sendMessage( message );
    }
    public static void fiveSecsJson(Player p) {
        TextComponent message = new TextComponent("§bA sumo event is starting in 5 seconds, Click to join!");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/event join sumo"));
        p.spigot().sendMessage( message );
    }
    public static void fourSecsJson(Player p) {
        TextComponent message = new TextComponent("§bA sumo event is starting in 4 seconds, Click to join!");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/event join sumo"));
        p.spigot().sendMessage( message );
    }
    public static void threeSecsJson(Player p) {
        TextComponent message = new TextComponent("§bA sumo event is starting in 3 seconds, Click to join!");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/event join sumo"));
        p.spigot().sendMessage( message );
    }
    public static void twoSecsJson(Player p) {
        TextComponent message = new TextComponent("§bA sumo event is starting in 2 seconds, Click to join!");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/event join sumo"));
        p.spigot().sendMessage( message );
    }
    public static void oneSecsJson(Player p) {
        TextComponent message = new TextComponent("§bA sumo event is starting in 1 second, Click to join!");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/event join sumo"));
        p.spigot().sendMessage( message );
    }
}
