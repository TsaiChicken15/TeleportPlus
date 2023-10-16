package com.ch1ck3n.teleportplus.commands;

import com.ch1ck3n.teleportplus.TeleportPlus;
import com.ch1ck3n.teleportplus.utils.MathUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPPCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        TeleportPlus instance = TeleportPlus.getInstance();
        if( !(sender instanceof Player) ) return true;

        if( args.length == 1 ) {
            if( args[0].equalsIgnoreCase("list") ) {
                TPPListCmd.onCommand(sender, command, s, args);
            }else {
                TPPTpCmd.onCommand(sender, command, s, args);
            }
        }else if( args.length == 2 ) {
            if( args[0].equalsIgnoreCase("add") ) {
                TPPAddCmd.onCommand(sender, command, s, args);
            }else if( args[0].equalsIgnoreCase("edit") ) {
                TPPEditCmd.onCommand(sender, command, s, args);
            }else if( args[0].equalsIgnoreCase("remove") ) {
                TPPRemoveCmd.onCommand(sender, command, s, args);
            }else {
                help(sender, instance);
            }
        }else {
            help(sender, instance);
        }
        return true;
    }

    public void help(CommandSender sender, TeleportPlus instance) {
        sender.sendMessage(
                "\n§e§m-§e§l▶◀§e§m§l-------§r §f" + instance.getPrefix() + "§7 " + instance.getDescription().getVersion() + " §e§m§l-------§e§l▶◀§e§m-§r\n " +
                        "\n§6/tpp §7- §fShow helps." +
                        "\n§6/tpp <name> §7- §fTeleport to a TPoint." +
                        "\n§6/tpp add <name> §7- §fAdd a TPoint at current position." +
                        "\n§6/tpp edit <name> §7- §fMove a TPoint to current position." +
                        "\n§6/tpp list <name> §7- §fList all TPoint of this server." +
                        "\n§6/tpp remove <name> §7- §fRemove a TPoint." +
                        "\n \n§e§m-§e§l▶◀§e§m§l--------------------------§e§l▶◀§e§m-");
    }

    public static String getLocationString(Location location) {
        return "XYZ: " + MathUtil.getDouble3(location.getX()) + " / " + MathUtil.getDouble3(location.getY()) + " / " + MathUtil.getDouble3(location.getZ()) +
                "\nFacing: (" + MathUtil.getFloat1(location.getYaw()) + " / " + MathUtil.getFloat1(location.getPitch()) + ")";
    }
}
