package cibz.cMinigames.Main;

import cibz.cMinigames.Commands.*;
import cibz.cMinigames.Listeners.*;
import cibz.cMinigames.Listeners.GUIs.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static ArrayList<String> inSpawn = new ArrayList<String>();
    File stats = new File(this.getDataFolder(), "/stats.yml");
    FileConfiguration statsConfig = YamlConfiguration.loadConfiguration(stats);
    File economy = new File(this.getDataFolder(), "/economy.yml");
    FileConfiguration economyConfig = YamlConfiguration.loadConfiguration(economy);

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("lobby").setExecutor(new LobbyCommand());
        getCommand("setlobby").setExecutor(new LobbyCommand());
        getCommand("shop").setExecutor(new ShopCommand());
        getCommand("event").setExecutor(new EventCommand());
        getCommand("arena").setExecutor(new ArenaCommand());

        getServer().getPluginManager().registerEvents(new ConnectListener(), this);
        getServer().getPluginManager().registerEvents(new SpawnItemListener(), this);
        getServer().getPluginManager().registerEvents(new ShopGUIListener(), this);
        getServer().getPluginManager().registerEvents(new EventGUIListener(), this);
    }

    @Override
    public void onDisable() {
        saveConfig();
        saveFile();
    }

    public FileConfiguration getEconomyFile() {
        return economyConfig;
    }
    public FileConfiguration getStatsFile() {
        return statsConfig;
    }
    public void saveFile(){
        try {
            this.economyConfig.save(economy);
            this.statsConfig.save(stats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tpSpawn(Player p){
        if (Main.plugin.getConfig().getConfigurationSection("spawn") != null) {
            inSpawn.add(p.getName());
            p.setHealth(20.00);
            p.getActivePotionEffects().clear();

            World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("spawn.world"));
            double a = Main.plugin.getConfig().getDouble("spawn.x");
            double y = Main.plugin.getConfig().getDouble("spawn.y");
            double z = Main.plugin.getConfig().getDouble("spawn.z");
            float yaw = (float) Main.plugin.getConfig().getDouble("spawn.yaw");
            float pitch = (float) Main.plugin.getConfig().getDouble("spawn.pitch");
            Location spawn = new Location(w, a, y, z, yaw, pitch);

            ItemStack shop = new ItemStack(Material.EMERALD);
            ItemMeta shopmeta = shop.getItemMeta();
            shopmeta.setDisplayName("§9Shop");
            shop.setItemMeta(shopmeta);

            ItemStack join = new ItemStack(Material.NETHER_STAR);
            ItemMeta joinmeta = join.getItemMeta();
            joinmeta.setDisplayName("§9Events");
            join.setItemMeta(joinmeta);

            ItemStack selector = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta selectormeta = selector.getItemMeta();
            selectormeta.setDisplayName("§9Arena");
            selector.setItemMeta(selectormeta);

            p.teleport(spawn);
            p.getInventory().clear();
            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);

            p.getInventory().setItem(0, shop);
            p.getInventory().setItem(4, join);
            p.getInventory().setItem(8, selector);
        } else {
            p.sendMessage("§9Spawn has not been set.");
        }
    }
}
