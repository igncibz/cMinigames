package cibz.cMinigames.Commands;

import Main.Main;
import cibz.cMinigames.Events.SumoEvent;
import cibz.cMinigames.Listeners.GUIs.EventGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(args.length == 0){
            EventGUIListener.eventGUI(p);
        }
        if(args.length == 1){
            if(!p.isOp()){
                p.sendMessage("§8§m---------------------------");
                p.sendMessage("§b§lEvent Help");
                p.sendMessage("§b§l* §f/event join §b<event>");
                p.sendMessage("§b§l* §f/event spectate §b<event>");
                p.sendMessage("§8§m---------------------------");
            } else {
                p.sendMessage("§8§m---------------------------");
                p.sendMessage("§b§lEvent Help");
                p.sendMessage("§b§l* §f/event join §b<event>");
                p.sendMessage("§b§l* §f/event spectate §b<event>");
                p.sendMessage("§b§l* §f/event kick §b<player>");
                p.sendMessage("§b§l* §f/event setloc1 §b<event>");
                p.sendMessage("§b§l* §f/event setloc2 §b<event>");
                p.sendMessage("§b§l* §f/event setlobby §b<event>");
                p.sendMessage("§8§m---------------------------");
            }
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("join")){
                if(args[1].equalsIgnoreCase("sumo")){
                    if(Main.inSpawn.contains(p.getName())){
                        if(SumoEvent.joinable){
                            SumoEvent.sendLobby(p);
                        } else {
                            p.sendMessage("§bYou can't join this event.");
                        }
                    } else {
                        p.sendMessage("§bPlease go to spawn first.");
                    }
                }
            }
            if(args[0].equalsIgnoreCase("spectate")){
                String event = args[1];
                if(!SumoEvent.players.contains(p.getUniqueId())){
                    if(Main.inSpawn.contains(p.getName())){
                        if(event.equalsIgnoreCase("sumo")){
                            World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Events." + "Sumo" + ".lobby.world"));
                            double x = Main.plugin.getConfig().getDouble("Events." + "Sumo" + ".lobby.x");
                            double y = Main.plugin.getConfig().getDouble("Events." + "Sumo" + ".lobby.y");
                            double z = Main.plugin.getConfig().getDouble("Events." + "Sumo" + ".lobby.z");

                            ItemStack sumo = new ItemStack(Material.REDSTONE);
                            ItemMeta smeta = sumo.getItemMeta();
                            smeta.setDisplayName("§bLeave event");
                            sumo.setItemMeta(smeta);

                            p.getInventory().setItem(8, sumo);
                            p.teleport(new Location(w, x, y, z));
                        }
                    } else {
                        p.sendMessage("§bPlease go to spawn first.");
                    }
                } else {
                    p.sendMessage("§bYou're already in the event.");
                }
            }
            if(args[0].equalsIgnoreCase("kick")){
                String t = args[1];
                if(p.isOp()){
                    if(t != null){
                        Player target = Bukkit.getServer().getPlayer(t);
                        if(target.isOnline()){
                            if(SumoEvent.players.contains(target.getUniqueId())){
                                if(!SumoEvent.fighting.contains(target.getUniqueId())){
                                    Main.plugin.tpSpawn(p);
                                    SumoEvent.players.remove(target.getUniqueId());
                                    target.sendMessage("§bYou have been kicked from the event.");
                                    p.sendMessage("§bSuccessfully kicked §b" + target.getName());
                                } else {
                                    p.sendMessage("§bThis player is currently in a fight.");
                                }
                            } else {
                                p.sendMessage("§bThat player is not in an event.");
                            }
                        } else {
                            p.sendMessage("§bThat player is not online.");
                        }
                    } else {
                        p.sendMessage("§bNot a player.");
                    }
                }
            }
            if(args[0].equalsIgnoreCase("setloc1")){
                String event = args[1];
                if(p.isOp()){
                    Main.plugin.getConfig().set("Events." + event + ".p1.world", p.getLocation().getWorld().getName());
                    Main.plugin.getConfig().set("Events." + event + ".p1.x", p.getLocation().getX());
                    Main.plugin.getConfig().set("Events." + event + ".p1.y", p.getLocation().getY());
                    Main.plugin.getConfig().set("Events." + event + ".p1.z", p.getLocation().getZ());
                    Main.plugin.getConfig().set("Events." + event + ".p1.yaw", p.getLocation().getYaw());
                    Main.plugin.getConfig().set("Events." + event + ".p1.pitch", p.getLocation().getPitch());
                    Main.plugin.saveFile();
                    p.sendMessage("§bLocation set for §f" + event + "§b.");
                }
            }
            if(args[0].equalsIgnoreCase("setloc2")){
                String event = args[1];
                if(p.isOp()){
                    Main.plugin.getConfig().set("Events." + event + ".p2.world", p.getLocation().getWorld().getName());
                    Main.plugin.getConfig().set("Events." + event + ".p2.x", p.getLocation().getX());
                    Main.plugin.getConfig().set("Events." + event + ".p2.y", p.getLocation().getY());
                    Main.plugin.getConfig().set("Events." + event + ".p2.z", p.getLocation().getZ());
                    Main.plugin.getConfig().set("Events." + event + ".p2.yaw", p.getLocation().getYaw());
                    Main.plugin.getConfig().set("Events." + event + ".p2.pitch", p.getLocation().getPitch());
                    Main.plugin.saveFile();
                    p.sendMessage("§bLocation set for §f" + event + "§b.");
                }
            }
            if(args[0].equalsIgnoreCase("setlobby")){
                String event = args[1];
                if(p.isOp()){
                    Main.plugin.getConfig().set("Events." + event + ".lobby.world", p.getLocation().getWorld().getName());
                    Main.plugin.getConfig().set("Events." + event + ".lobby.x", p.getLocation().getX());
                    Main.plugin.getConfig().set("Events." + event + ".lobby.y", p.getLocation().getY());
                    Main.plugin.getConfig().set("Events." + event + ".lobby.z", p.getLocation().getZ());
                    Main.plugin.saveFile();
                    p.sendMessage("§bLocation set for §f" + event + "§b.");
                }
            }
        }
        return true;
    }
}
