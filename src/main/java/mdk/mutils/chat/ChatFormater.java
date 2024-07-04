package mdk.mutils.chat;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatFormater {
    public static final ChatFormater DEFULD = new ChatFormater();

    public String format(IChat chat, String string) {
        return String.format("[%s]: %s", chat.getName(), ChatColor.translateAlternateColorCodes('&', string));
    }

    public String format(IChat chat, String string, CommandSender sender) {
        return String.format("[%s][%s]: %s", chat.getName(), sender.getName(), ChatColor.translateAlternateColorCodes('&', string));
    }
}
