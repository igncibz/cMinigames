package Main;

import org.bukkit.entity.Player;

public class Economy {
    public static int getBalance(Player player){
        return Main.plugin.getEconomyFile().getInt("Tokens." + player.getUniqueId().toString() + ".balance", 0);
    }
    public static void setBalance(Player player, int amount)  {
        Main.plugin.getEconomyFile().set("Tokens." + player.getUniqueId().toString() + ".balance", amount);
        Main.plugin.saveFile();
    }
    public static void addToBalance(Player player, int amount){
        Main.plugin.getEconomyFile().set("Tokens." + player.getUniqueId().toString() + ".balance", getBalance(player) + amount);
        Main.plugin.saveFile();
    }
    public static void removeBalance(Player player, int amount){
        Main.plugin.getEconomyFile().set("Tokens." + player.getUniqueId().toString() + ".balance", getBalance(player) - amount);
        Main.plugin.saveFile();
    }

    public static int getBounty(Player player){
        return Main.plugin.getEconomyFile().getInt("Bounty." + player.getUniqueId().toString() + ".bounty", 0);
    }
    public static void setBounty(Player player, int amount){
        Main.plugin.getEconomyFile().set("Bounty." + player.getUniqueId().toString() + ".bounty", amount);
        Main.plugin.saveFile();
    }
    public static void addToBounty(Player player, int amount){
        Main.plugin.getEconomyFile().set("Bounty." + player.getUniqueId().toString() + ".bounty", getBounty(player) + amount);
        Main.plugin.saveFile();
    }
}
