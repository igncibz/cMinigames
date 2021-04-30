package cibz.cMinigames.Listeners.GUIs;

import Main.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopGUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() != null) {
            if (e.getInventory().getTitle().equals("§9Shop")) {
                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bHats")) {
                            p.closeInventory();
                            hatGUI(p);
                            e.setCancelled(true);
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bTrails")) {
                            p.closeInventory();
                            trailGUI(p);
                            e.setCancelled(true);
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bCoins")) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
            if(e.getInventory().getTitle().equals("Hats")){
                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bCoins")) {
                            e.setCancelled(true);
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bBack")) {
                            p.closeInventory();
                            shopGUI(p);
                            e.setCancelled(true);
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bDiamond Helmet")) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
            if(e.getInventory().getTitle().equals("Trails")){
                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bCoins")) {
                            e.setCancelled(true);
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bBack")) {
                            p.closeInventory();
                            shopGUI(p);
                            e.setCancelled(true);
                        }
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bFirework Trail")) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    public static void shopGUI(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, "§9Shop");

        ItemStack hat = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta hatmeta = hat.getItemMeta();
        hatmeta.setDisplayName("§9Hats");
        hat.setItemMeta(hatmeta);

        ItemStack trails = new ItemStack(Material.FIREWORK);
        ItemMeta trailsmeta = trails.getItemMeta();
        trailsmeta.setDisplayName("§9Trails");
        trails.setItemMeta(trailsmeta);

        ItemStack coin = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta coinmeta = coin.getItemMeta();
        coinmeta.setDisplayName("§9Coins");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§f" + Economy.getBalance(p) + "§acoins.");
        coinmeta.setLore(lore);
        coin.setItemMeta(coinmeta);

        inv.setItem(4, coin);
        inv.setItem(20, hat);
        inv.setItem(24, trails);
        p.openInventory(inv);
    }

    public static void hatGUI(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, "§9Hats");

        ItemStack coin = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta coinmeta = coin.getItemMeta();
        coinmeta.setDisplayName("§bCoins");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§f" + Economy.getBalance(p) + "§acoins.");
        coinmeta.setLore(lore);
        coin.setItemMeta(coinmeta);

        ItemStack hat = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta hatmeta = hat.getItemMeta();
        hatmeta.setDisplayName("§bDiamond Helmet");
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("§aComing soon.");
        coinmeta.setLore(lore2);
        hat.setItemMeta(hatmeta);

        ItemStack back = new ItemStack(Material.REDSTONE_TORCH_ON);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName("§bBack");
        back.setItemMeta(backmeta);

        inv.setItem(4, coin);
        inv.setItem(22, hat);
        inv.setItem(44, back);
        p.openInventory(inv);
    }

    public static void trailGUI(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, "§9Hats");

        ItemStack coin = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta coinmeta = coin.getItemMeta();
        coinmeta.setDisplayName("§bCoins");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§f" + Economy.getBalance(p) + "§acoins.");
        coinmeta.setLore(lore);
        coin.setItemMeta(coinmeta);

        ItemStack trail = new ItemStack(Material.FIREWORK);
        ItemMeta trailmeta = trail.getItemMeta();
        trailmeta.setDisplayName("§bFirework Trail");
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("§aComing Soon.");
        coinmeta.setLore(lore2);
        trail.setItemMeta(trailmeta);

        ItemStack back = new ItemStack(Material.REDSTONE_TORCH_ON);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName("§bBack");
        back.setItemMeta(backmeta);

        inv.setItem(4, coin);
        inv.setItem(22, trail);
        inv.setItem(44, back);
        p.openInventory(inv);
    }
}
