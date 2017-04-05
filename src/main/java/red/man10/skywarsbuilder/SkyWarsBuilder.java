package red.man10.skywarsbuilder;

import jdk.nashorn.internal.ir.Block;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class SkyWarsBuilder extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("skycircle")) {
                try {
                    Player p = (Player) sender;
                    if(args.length == 0){
                        p.sendMessage("§e§lCreated By Sho0");
                        return true;
                    }
                    if(!p.hasPermission("man10.skywars.skycircle")){
                        p.sendMessage("§lあなたには権限はありません");
                        return true;
                    }
                    if (args.length != 2) {
                        p.sendMessage("§l引数が足りません");
                        p.sendMessage("§l/skycilcle <半径> <数>");

                        return true;
                    }
                    for(Location l : getCircle(p.getLocation(), Integer.parseInt(args[0]), Integer.parseInt(args[1]))){
                        l.getBlock().setType(Material.STONE);
                    }

                }catch (NumberFormatException e){
                    Player p = (Player) sender;
                    p.sendMessage("§l範囲等は数字のみです");
                    return true;
                }
            }
            return true;
        }
        return true;
    }

    public ArrayList<Location> getCircle(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        for(int i = 0;i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }

}
