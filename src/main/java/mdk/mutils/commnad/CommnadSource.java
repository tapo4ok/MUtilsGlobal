package mdk.mutils.commnad;

import mdk.mutils.brigadier.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommnadSource implements ISource {
    private final CommandSender sender;
    private final String[] args;
    public CommnadSource(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
    }
    @Override
    public boolean isPlayer() {
        return sender instanceof Player;
    }

    @Override
    public CommandSender getSender() {
        return sender;
    }

    @Override
    public Player getPlayer() {
        return (Player) sender;
    }

    public String[] getArgs() {
        return args;
    }


    @Override
    public void sendMessage(String message) {
        sender.sendMessage(message);
    }

    @Override
    public void sendFeedback(String feedback) {
        sendFeedback(feedback, false);
    }

    @Override
    public void sendFeedback(String feedback, boolean broadcastToOps) {
        sender.sendMessage(feedback);
        if (broadcastToOps) {
            String str = ChatColor.GRAY+""+ChatColor.ITALIC+String.format("[%s: %s]", sender.getName(), feedback);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player != sender) {
                    player.sendMessage(str);
                }
            }
        }
    }

    @Override
    public void sendError(String message) {
        sender.sendMessage(ChatColor.RED+message);
    }

    @Override
    public void sendFeedback(Text feedback) {
        sendFeedback(feedback, false);
    }

    @Override
    public void sendFeedback(Text feedback, boolean broadcastToOps) {
        String stg = feedback.getString();
        sender.sendMessage(stg);
        if (broadcastToOps) {
            String str = ChatColor.GRAY+""+ChatColor.ITALIC+String.format("[%s: %s]", sender.getName(), stg);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player != sender) {
                    player.sendMessage(str);
                }
            }
        }
    }

    @Override
    public void sendMessage(Text message) {
        sender.sendMessage(message.getString());
    }

    @Override
    public void sendError(Text message) {
        message.setColor(ChatColor.RED);
        sender.sendMessage(message.getString());
    }
}
