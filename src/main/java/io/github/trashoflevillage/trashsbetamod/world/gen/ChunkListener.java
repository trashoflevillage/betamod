package io.github.trashoflevillage.trashsbetamod.world.gen;

import io.github.trashoflevillage.trashsbetamod.BetaMod;
import io.github.trashoflevillage.trashsbetamod.blocks.ModBlocks;
import io.github.trashoflevillage.trashsbetamod.world.gen.features.MineshaftFeature;
import io.github.trashoflevillage.trashsbetamod.world.gen.features.PerlinCaveFeature;
import net.minecraft.block.Block;
import net.minecraft.world.dimension.NetherDimension;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.feature.*;
import net.modificationstation.stationapi.api.event.world.gen.WorldGenEvent;
import net.modificationstation.stationapi.api.worldgen.feature.VolumetricScatterFeature;

import java.util.Random;

public class ChunkListener {
    public static final Feature COPPER_ORE = new OreFeature(ModBlocks.COPPER_ORE.id, 12);
    public static final Feature COPPER_ORE_SCATTERED = new VolumetricScatterFeature(COPPER_ORE, 20, 128);
    public static final Feature LIFE_QUARTZ_ORE = new OreFeature(ModBlocks.LIFE_QUARTZ_ORE.id, 1);
    public static final Feature LIFE_QUARTZ_ORE_SCATTERED = new VolumetricScatterFeature(LIFE_QUARTZ_ORE, 40, 30);

    public static final Feature MINESHAFT = new MineshaftFeature(200);

    public static final Feature PERLIN_CAVE = new PerlinCaveFeature(100, -0.2);

    public static void populate(WorldGenEvent.ChunkDecoration event) {
        if (event.world.dimension instanceof OverworldDimension) populateOverworld(event);
        if (event.world.dimension instanceof NetherDimension) populateNether(event);
    }

    public static void populateOverworld(WorldGenEvent.ChunkDecoration event) {
        PERLIN_CAVE.generate(event.world, event.random, event.x, getRandomYFromBedrock(event.random, 75), event.z);

        //doubleVanillaFeatures(event);
        COPPER_ORE_SCATTERED.generate(event.world, event.random, event.x, getRandomYFromBedrock(event.random, 128), event.z);
        LIFE_QUARTZ_ORE_SCATTERED.generate(event.world, event.random, event.x, getRandomYFromBedrock(event.random, 30), event.z);

        MINESHAFT.generate(event.world, event.random, event.x, getRandomYFromBedrock(event.random, 50), event.z);
    }

    public static void populateNether(WorldGenEvent.ChunkDecoration event) {

    }

    public static int getRandomYFromBedrock(Random random, int max) {
        return random.nextInt(max - BetaMod.BEDROCK_LEVEL) + BetaMod.BEDROCK_LEVEL;
    }

    // To compensate for the extra world height, more features should be spawned.
    // This is because there's more places for the features to spawn, and therefore being harder to find.
    private static void doubleVanillaFeatures(WorldGenEvent.ChunkDecoration event) {
        int var4 = event.x * 16;
        int var5 = event.z * 16;
        if (event.random.nextInt(4) == 0) {
            int var13 = var4 + event.random.nextInt(16) + 8;
            int var14 = getRandomYFromBedrock(event.random, 128);
            int var15 = var5 + event.random.nextInt(16) + 8;
            new LakeFeature(Block.WATER.id).generate(event.world, event.random, var13, var14, var15);
        }

        if (event.random.nextInt(8) == 0) {
            int var26 = var4 + event.random.nextInt(16) + 8;
            int var38 = getRandomYFromBedrock(event.random, event.random.nextInt(120) + 8);
            int var50 = var5 + event.random.nextInt(16) + 8;
            if (var38 < 64 || event.random.nextInt(10) == 0) {
                new LakeFeature(Block.LAVA.id).generate(event.world, event.random, var26, var38, var50);
            }
        }

        for (int var27 = 0; var27 < 8; var27++) {
            int var39 = var4 + event.random.nextInt(16) + 8;
            int var51 = getRandomYFromBedrock(event.random, 128);
            int var16 = var5 + event.random.nextInt(16) + 8;
            new DungeonFeature().generate(event.world, event.random, var39, var51, var16);
        }

        for (int var29 = 0; var29 < 20; var29++) {
            int var41 = var4 + event.random.nextInt(16);
            int var53 = getRandomYFromBedrock(event.random, 128);
            int var64 = var5 + event.random.nextInt(16);
            new OreFeature(Block.DIRT.id, 32).generate(event.world, event.random, var41, var53, var64);
        }

        for (int var30 = 0; var30 < 10; var30++) {
            int var42 = var4 + event.random.nextInt(16);
            int var54 = getRandomYFromBedrock(event.random, 128);
            int var65 = var5 + event.random.nextInt(16);
            new OreFeature(Block.GRAVEL.id, 32).generate(event.world, event.random, var42, var54, var65);
        }

        for (int var31 = 0; var31 < 20; var31++) {
            int var43 = var4 + event.random.nextInt(16);
            int var55 = getRandomYFromBedrock(event.random, 128);
            int var66 = var5 + event.random.nextInt(16);
            new OreFeature(Block.COAL_ORE.id, 16).generate(event.world, event.random, var43, var55, var66);
        }

        for (int var32 = 0; var32 < 20; var32++) {
            int var44 = var4 + event.random.nextInt(16);
            int var56 = getRandomYFromBedrock(event.random, 64);
            int var67 = var5 + event.random.nextInt(16);
            new OreFeature(Block.IRON_ORE.id, 8).generate(event.world, event.random, var44, var56, var67);
        }

        for (int var33 = 0; var33 < 2; var33++) {
            int var45 = var4 + event.random.nextInt(16);
            int var57 = getRandomYFromBedrock(event.random, 32);
            int var68 = var5 + event.random.nextInt(16);
            new OreFeature(Block.GOLD_ORE.id, 8).generate(event.world, event.random, var45, var57, var68);
        }

        for (int var34 = 0; var34 < 8; var34++) {
            int var46 = var4 + event.random.nextInt(16);
            int var58 = getRandomYFromBedrock(event.random, 16);
            int var69 = var5 + event.random.nextInt(16);
            new OreFeature(Block.REDSTONE_ORE.id, 7).generate(event.world, event.random, var46, var58, var69);
        }

        for (int var35 = 0; var35 < 1; var35++) {
            int var47 = var4 + event.random.nextInt(16);
            int var59 = getRandomYFromBedrock(event.random, 16);
            int var70 = var5 + event.random.nextInt(16);
            new OreFeature(Block.DIAMOND_ORE.id, 7).generate(event.world, event.random, var47, var59, var70);
        }

        for (int var36 = 0; var36 < 1; var36++) {
            int var48 = var4 + event.random.nextInt(16);
            int var60 = getRandomYFromBedrock(event.random, 16) + event.random.nextInt(16);
            int var71 = var5 + event.random.nextInt(16);
            new OreFeature(Block.LAPIS_ORE.id, 6).generate(event.world, event.random, var48, var60, var71);
        }
    }
}
