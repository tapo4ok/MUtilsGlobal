package mdk.mutils.chat;

import mdk.mutils.Identifier;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.Collection;

public interface IChat {
    void send(String text);
    void send(String[] texts);
    void send(CommandSender sender, String text);
    void send(CommandSender sender, String[] texts);
    Permission getPermision();
    Collection<? extends Player> getPlayers();
    Identifier getId();
    void setformat(ChatFormater formater);
    ChatFormater getformat();
    String getName();
}
