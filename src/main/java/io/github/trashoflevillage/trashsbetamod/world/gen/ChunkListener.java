package io.github.trashoflevillage.trashsbetamod.world.gen;

import io.github.trashoflevillage.trashsbetamod.blocks.ModBlocks;
import io.github.trashoflevillage.trashsbetamod.world.gen.features.MineshaftFeature;
import io.github.trashoflevillage.trashsbetamod.world.gen.features.PerlinCaveFeature;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.world.dimension.NetherDimension;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;
import net.modificationstation.stationapi.api.event.world.gen.WorldGenEvent;
import net.modificationstation.stationapi.api.worldgen.feature.VolumetricScatterFeature;

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
        PERLIN_CAVE.generate(event.world, event.random, event.x, event.random.nextInt(75), event.z);

        COPPER_ORE_SCATTERED.generate(event.world, event.random, event.x, event.random.nextInt(128), event.z);
        LIFE_QUARTZ_ORE_SCATTERED.generate(event.world, event.random, event.x, event.random.nextInt(30), event.z);

        MINESHAFT.generate(event.world, event.random, event.x, event.random.nextInt(50), event.z);
    }

    public static void populateNether(WorldGenEvent.ChunkDecoration event) {

    }
}
