package cibz.cMinigames.Commands;

import Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(Main.inSpawn.contains(p.getName())){
            Main.plugin.tpSpawn(p);
        } else {

        }
        return true;
    }
}
