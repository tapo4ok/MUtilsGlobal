package mdk.mutils.commnad;

import mdk.mutils.brigadier.Text;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface ISource {
    CommandSender getSender();
    boolean isPlayer();
    Player getPlayer();
    String[] getArgs();

    void sendFeedback(String feedback);
    void sendFeedback(String feedback, boolean broadcastToOps);
    void sendMessage(String message);
    void sendError(String message);


    void sendFeedback(Text feedback);
    void sendFeedback(Text feedback, boolean broadcastToOps);
    void sendMessage(Text message);
    void sendError(Text message);
}
