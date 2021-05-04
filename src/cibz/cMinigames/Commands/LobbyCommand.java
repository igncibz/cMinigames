package cibz.cMinigames.Commands;

import cibz.cMinigames.Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("setlobby")) {
            if (p.isOp()) {
                Main.plugin.getConfig().set("spawn.world", p.getLocation().getWorld().getName());
                Main.plugin.getConfig().set("spawn.x", p.getLocation().getX());
                Main.plugin.getConfig().set("spawn.y", p.getLocation().getY());
                Main.plugin.getConfig().set("spawn.z", p.getLocation().getZ());
                Main.plugin.getConfig().set("spawn.yaw", p.getLocation().getYaw());
                Main.plugin.getConfig().set("spawn.pitch", p.getLocation().getPitch());
                Main.plugin.saveConfig();
                p.sendMessage("§bThis worlds spawn has been set to your location.");
                return true;
            } else {
                return true;
            }
        }
        
        if(Main.inSpawn.contains(p.getName())){
            Main.plugin.tpSpawn(p);
        } else {

        }

        return true;
    }
}
