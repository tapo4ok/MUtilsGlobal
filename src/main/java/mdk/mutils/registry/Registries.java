package mdk.mutils.registry;

import mdk.mutils.Identifier;
import mdk.mutils.chat.IChat;

public class Registries {
    public static final Registry<IChat> CHAT_REGISTRY = new SimpleRegistry<IChat>(new Identifier("chat", "registry"));
}
