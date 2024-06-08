package mdk.mutils.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import java.util.function.Consumer;

public class WorldUtils {
    public static World getWorld(String name) {
        return getWorld(name, null);
    }

    public static World getWorld(String name, ChunkGenerator generator) {
        World world = Bukkit.getWorld(name);
        if (world == null) {
            world = createWorld(name, null, generator);
        }
        return world;
    }

    public static World createWorld(String name, Consumer<WorldCreator> Lambd, ChunkGenerator generator) {
        WorldCreator worldCreator = new WorldCreator(name);
        if (generator!=null) {
            worldCreator.generator(generator);
        }

        worldCreator.generateStructures(false);
        if (Lambd!=null) {
            Lambd.accept(worldCreator);
        }
        return worldCreator.createWorld();
    }
    public static void TeleportPlayer(World world, Player player, double x, double y, double z) {
        player.teleport(new Location(world, x, y, z));
    }

    public static void TeleportPlayer(World world, Player player, Location location) {
        TeleportPlayer(world, player, location.getX(), location.getY(), location.getZ());
    }

}
