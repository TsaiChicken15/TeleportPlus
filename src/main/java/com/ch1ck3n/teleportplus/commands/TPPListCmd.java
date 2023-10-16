package com.ch1ck3n.teleportplus.commands;

import com.ch1ck3n.teleportplus.TeleportPlus;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class TPPListCmd {

    public static boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        TeleportPlus instance = TeleportPlus.getInstance();
        Player player = ((Player) sender).getPlayer();
        Location location1 = player.getLocation();
        if( !(sender instanceof Player) ) return true;
        if( !sender.hasPermission("tpp.list" ) ) {
            sender.sendMessage( instance.getPrefix() + " §c You don't have permission to do this!" );
            return true;
        }

        Set<String> items = instance.getConfigManager().listTPoint();
        if( !items.isEmpty() ) {
            sender.sendMessage( instance.getPrefix() + " §aAll TPoints:\n" );
            for (String item : items) {
                Location location2 = instance.getConfigManager().getTPoint(item, player);
                String message = " §f+ §a" + item;
                TextComponent textComponent = new TextComponent(message);
                textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("Position: \n" + TPPCmd.getLocationString(location2) + "\n\n§7[Click To Teleport]").create()));
                textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpp " + item));
                player.spigot().sendMessage(textComponent);
            }
        }else {
            sender.sendMessage(instance.getPrefix() + " §cThere's no TPoints");
        }

        return true;
    }

}
