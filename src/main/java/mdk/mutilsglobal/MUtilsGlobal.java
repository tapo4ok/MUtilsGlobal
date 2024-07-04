package mdk.mutilsglobal;

import mdk.mutils.Identifier;
import mdk.mutils.chat.IChat;
import mdk.mutils.registry.Registries;
import mdk.mutils.registry.Registry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MUtilsGlobal extends JavaPlugin {
    private static List<String> onTabComplete2(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        for (Identifier id: Registries.CHAT_REGISTRY.getKeys()) {
            list.add(id.toString());
        }
        return list;
    }

    @Override
    public void onEnable() {
        getCommand("P_send").setExecutor((sender, command, label, args) -> {
            Registry<IChat> REGISTRY = Registries.CHAT_REGISTRY;
            Identifier id = new Identifier(args[0]);

            if (REGISTRY.containsId(id)) {
                StringBuilder builder = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    builder.append(args[i]);
                }
                REGISTRY.get(id).send(sender, builder.toString());
            }
            else {
                sender.sendMessage("Invalid Chat");
            }
            return false;
        });

        getCommand("P_send").setTabCompleter(MUtilsGlobal::onTabComplete2);
    }
}
