package cibz.cMinigames.Events;

import cibz.cMinigames.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TntTagEvent implements Listener {

    public static ArrayList<Player> inTag = new ArrayList<>();
    public static ArrayList<Player> IT = new ArrayList<>();

    public static void playerJoin(Player p){
        tpLobby(p);
        for (Player joined : inTag){
            joined.sendMessage("§9" + p.getName() + " §bhas joined.");
        }
    }

    public static void startTag(){
        int size = inTag.size();
        int random = new Random().nextInt(size);
        Player chosen = inTag.get(random);
        IT.add(chosen);
        sendPlayers();
        itItems(chosen);
    }

    public static void sendPlayers(){
        for (Player p : inTag){
            tpGame(p);
        }
    }

    public static void itItems(Player p){
        ItemStack tagged = new ItemStack(Material.EMERALD);
        ItemMeta taggedmeta = tagged.getItemMeta();
        taggedmeta.setDisplayName("§cYOU ARE IT!");
        tagged.setItemMeta(taggedmeta);

        p.getInventory().setHelmet(tagged);
        p.getInventory().setItem(0, tagged);
    }

    public static void tpLobby(Player p){
        World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Events." + "tag" + ".lobby.world"));
        double x = Main.plugin.getConfig().getDouble("Events." + "tag" + ".lobby.x");
        double y = Main.plugin.getConfig().getDouble("Events." + "tag" + ".lobby.y");
        double z = Main.plugin.getConfig().getDouble("Events." + "tag" + ".lobby.z");
        p.teleport(new Location(w, x, y, z));

    }
    public static void tpGame(Player p){
        World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Events." + "tag" + ".game.world"));
        double x = Main.plugin.getConfig().getDouble("Events." + "tag" + ".game.x");
        double y = Main.plugin.getConfig().getDouble("Events." + "tag" + ".game.y");
        double z = Main.plugin.getConfig().getDouble("Events." + "tag" + ".game.z");
        p.teleport(new Location(w, x, y, z));
    }
}
