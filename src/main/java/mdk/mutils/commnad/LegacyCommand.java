package mdk.mutils.commnad;

import mdk.mutils.Identifier;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class LegacyCommand extends Command {
    private final Identifier id;
    public LegacyCommand(Identifier id) {
        this(id, "", "/" + id.getPath(), new ArrayList<String>());
    }
    public LegacyCommand(Identifier id, String description, String usageMessage, List<String> aliases) {
        super(id.getPath(), description, usageMessage, aliases);
        this.id = id;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        CommnadSource source = new CommnadSource(sender, args);


        return execute(source);
    }

    public abstract boolean execute(CommnadSource source);

    public void register() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap map = (CommandMap)commandMapField.get(Bukkit.getServer());
            map.register(id.getNamespace(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
