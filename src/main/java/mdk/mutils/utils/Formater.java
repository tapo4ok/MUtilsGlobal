package mdk.mutils.utils;

import org.bukkit.ChatColor;

public class Formater {
    public static String color(String c) {
        return ChatColor.translateAlternateColorCodes('&', c);
    }
}
