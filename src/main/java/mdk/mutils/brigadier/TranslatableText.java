package mdk.mutils.brigadier;

import mdk.mutils.lang.ILang;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collection;

public class TranslatableText implements Text {
    private String text;
    private ChatColor color;
    private final Collection<Text> child;
    private final ILang lang;
    private TranslatableText(String text, ChatColor color, ILang lang) {
        this.text = text;
        this.color = color;
        this.child = new ArrayList<>();
        this.lang = lang;
    }
    public static TranslatableText of(String string, ILang lang) {
        return new TranslatableText(string, null, lang);
    }

    public Text setColor(ChatColor color) {
        this.color = color;
        return this;
    }

    public Text setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public Text append(Text text) {
        child.add(text);
        return this;
    }

    @Override
    public ChatColor color() {
        return color;
    }

    @Override
    public String getString() {
        StringBuilder builder = new StringBuilder();
        getString(builder);
        for (Text text1 : child) {
            text1.getString(builder);
        }

        return builder.toString();
    }

    @Override
    public void getString(StringBuilder builder) {
        if (color != null) {
            builder.append(color);
        }
        builder.append(lang.get(text));
    }
}
