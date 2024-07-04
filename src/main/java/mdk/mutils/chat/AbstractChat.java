package mdk.mutils.chat;

import mdk.mutils.Identifier;
import mdk.mutils.registry.Registreable;
import mdk.mutils.registry.Registry;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.Collection;

public abstract class AbstractChat implements IChat, Registreable<Registry<IChat>> {
    protected Identifier name;
    protected Permission permission;
    protected ChatFormater formater;

    public AbstractChat(Identifier name) {
        this(new Permission(name.getPath()), ChatFormater.DEFULD, name);
    }

    public AbstractChat(Permission permission, ChatFormater formater, Identifier name) {
        this.permission = permission;
        this.formater = formater;
        this.name = name;
        Bukkit.getServer().getPluginManager().addPermission(permission);
    }

    @Override
    public Permission getPermision() {
        return permission;
    }

    @Override
    public void send(String[] texts) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(permission)) {
                for (String text : texts) {
                    player.sendMessage(formater.format(this, text));
                }
            }
        }
    }

    @Override
    public void send(String text) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(permission)) {
                player.sendMessage(formater.format(this, text));
            }
        }
    }

    @Override
    public void send(CommandSender sender, String[] texts) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(permission)) {
                for (String text : texts) {
                    player.sendMessage(formater.format(this, text, sender));
                }
            }
        }
    }

    @Override
    public void send(CommandSender sender, String text) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(permission)) {
                player.sendMessage(formater.format(this, text, sender));
            }
        }
    }

    @Override
    public Collection<? extends Player> getPlayers() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        players.removeIf((E) -> !E.hasPermission(permission));
        return players;
    }

    @Override
    public ChatFormater getformat() {
        return this.formater;
    }

    @Override
    public void setformat(ChatFormater formater) {
        this.formater = formater;
    }

    @Override
    public Identifier getId() {
        return name;
    }

    @Override
    public String getName() {
        return name.getPath();
    }

    @Override
    public void register(Registry<IChat> REGISTRY) {
        Registry.register(REGISTRY, name, this);
    }
}
