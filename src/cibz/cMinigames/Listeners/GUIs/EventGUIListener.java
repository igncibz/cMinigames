package cibz.cMinigames.Listeners.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventGUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() != null) {
            if (e.getInventory().getTitle().equals("§9Events")) {
                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bSumo")) {
                            p.closeInventory();
                            e.setCancelled(true);
                            p.sendMessage("§dComing soon.");
/*                            if(SumoEvent.joinable = true){
                                if(SumoEvent.players.isEmpty()){
                                    SumoEvent.countdownSumo();
                                    p.sendMessage("§dEvent started.");
                                } else {
                                    p.sendMessage("§dThere's currently an event running.");
                                }
                            } else {
                                p.sendMessage("§dThere's currently an event running.");
                            }*/
                        }
                    }
                }
            }
        }
    }

    public static void eventGUI(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, "§9Events");

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glassmeta = glass.getItemMeta();
        glassmeta.setDisplayName(" ");
        glass.setItemMeta(glassmeta);

        ItemStack sumo = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta sumometa = sumo.getItemMeta();
        sumometa.setDisplayName("§bSumo");
        sumo.setItemMeta(sumometa);

        inv.setItem(0, glass);
        inv.setItem(1, glass);
        inv.setItem(2, glass);
        inv.setItem(3, glass);
        inv.setItem(4, glass);
        inv.setItem(5, glass);
        inv.setItem(6, glass);
        inv.setItem(7, glass);
        inv.setItem(8, glass);
        inv.setItem(9, glass);
        inv.setItem(10, sumo);
        inv.setItem(17, glass);
        inv.setItem(18, glass);
        inv.setItem(19, glass);
        inv.setItem(20, glass);
        inv.setItem(21, glass);
        inv.setItem(22, glass);
        inv.setItem(23, glass);
        inv.setItem(24, glass);
        inv.setItem(25, glass);
        inv.setItem(26, glass);
        p.openInventory(inv);
    }
}
