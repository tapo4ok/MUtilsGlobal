package mdk.mutils.utils.generators;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;

import java.util.Random;

public class FL extends ChunkGenerator {
    public int[][] laurs = new int[][] {
            {1, Material.BEDROCK.getId()},
            {4, Material.DIRT.getId()},
            {1, Material.GRASS.getId()}
    };

    public FL(int[][] l) {
        this.laurs = l;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        int currentHeight = 0;
        for (int[] layer : laurs) {
            int height = layer[0];
            Material material = Material.getMaterial(layer[1]);
            for (int y = currentHeight; y < currentHeight + height; y++) {
                for (int bx = 0; bx < 16; bx++) {
                    for (int bz = 0; bz < 16; bz++) {
                        chunkData.setBlock(bx, y, bz, material);
                    }
                }
            }
            currentHeight += height;
        }

        return chunkData;
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0, 64, 0);
    }
}