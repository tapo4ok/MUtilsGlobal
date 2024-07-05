package mdk.mutils.brigadier;

import mdk.mutils.lang.ILang;
import org.bukkit.ChatColor;

public interface Text extends Message {
    Text append(Text text);
    ChatColor color();
    Text setText(String text);
    Text setColor(ChatColor color);

    static MutableText literal(String string) {
        return MutableText.of(string);
    }

    static TranslatableText translatable(String string, ILang lang) {
        return TranslatableText.of(string, lang);
    }
}
