package mdk.mutils.brigadier;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collection;

public class MutableText implements Text {
    private String text;
    private ChatColor color;
    private final Collection<Text> child;
    private MutableText(String text, ChatColor color) {
        this.text = text;
        this.color = color;
        this.child = new ArrayList<>();
    }
    public static MutableText of(String string) {
        return new MutableText(string, null);
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
        return builder.toString();
    }

    @Override
    public void getString(StringBuilder builder) {
        if (color != null) {
            builder.append(color);
        }
        builder.append(text);

        for (Text text1 : child) {
            text1.getString(builder);
        }
    }
}
