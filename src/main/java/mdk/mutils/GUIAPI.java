package mdk.mutils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class GUIAPI {
    private final Map<Player, GUIPage> playerPages;

    public GUIAPI() {
        playerPages = new HashMap<>();
    }

    public void openGUI(Player player, GUIPage page) {
        playerPages.put(player, page);
        player.openInventory(page.getInventory());
    }

    public boolean handleGUIClick(Player player, int slot) {
        GUIPage currentPage = playerPages.get(player);
        if (currentPage != null) {
            return currentPage.handleClick(player, slot);
        }
        else {
            return false;
        }
    }

    public static class GUIPage {
        private final String title;
        private final int size;
        private final Inventory inventory;
        private final Map<Integer, OnClick<Boolean, Player>> actions;

        public GUIPage(String title, int size) {
            this.title = title;
            this.size = size;
            this.inventory = Bukkit.createInventory(null, size, title);
            this.actions = new HashMap<>();
        }

        public void setItem(int slot, ItemStack item, OnClick<Boolean, Player> action) {
            inventory.setItem(slot, item);
            actions.put(slot, action);
        }

        public Inventory getInventory() {
            return inventory;
        }

        public boolean handleClick(Player player, int slot) {
            OnClick<Boolean, Player> action = actions.get(slot);
            if (action != null) {
                return action.accept(player);
            }
            else {
                return false;
            }
        }
    }

    public static interface OnClick<R, I> {
        R accept(I i);
    }
}