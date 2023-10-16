package com.ch1ck3n.teleportplus.commands;

import com.ch1ck3n.teleportplus.TeleportPlus;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPPEditCmd {

    public static boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        TeleportPlus instance = TeleportPlus.getInstance();
        Player player = ((Player) sender).getPlayer();
        Location location1 = player.getLocation();
        if( !(sender instanceof Player) ) return true;
        if( !sender.hasPermission("tpp.add" ) ) {
            sender.sendMessage( instance.getPrefix() + " §c You don't have permission to do this!" );
            return true;
        }

        Location location2 = instance.getConfigManager().getTPoint(args[1], player);
        if( location2 != null ) {
            String message = instance.getPrefix() + " §f" + args[1] + " §aEdited";
            TextComponent textComponent = new TextComponent( message );
            textComponent.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT ,
                    new ComponentBuilder( "New: \n" + TPPCmd.getLocationString(location1) + "\n\nOriginal: \n" + TPPCmd.getLocationString(location2) ).create() ) );
            player.spigot().sendMessage( textComponent );
            instance.getConfigManager().editTPoint(args[1], player);
        }else {
            sender.sendMessage(instance.getPrefix() + " §f" + args[1] + " §cis not exist");
        }

        return true;
    }

}
