package cibz.cMinigames.Commands;

import cibz.cMinigames.Listeners.GUIs.ShopGUIListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        ShopGUIListener.shopGUI(p);
        return true;
    }
}
