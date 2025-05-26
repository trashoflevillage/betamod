package io.github.trashoflevillage.trashsbetamod.world.gen.features;

import io.github.trashoflevillage.trashsbetamod.util.world.BlockPredicates;
import io.github.trashoflevillage.trashsbetamod.util.world.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.block.States;

import java.util.*;

public class PerlinCaveFeature extends Feature {
    private final float chance;
    private final double carveThreshold; // -1 to 1, higher value = bigger caves
    public PerlinCaveFeature(float chance, double carveThreshold) {
        this.chance = chance;
        this.carveThreshold = carveThreshold;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        if (random.nextFloat() <= 1/chance) {
            BlockPos origin = new BlockPos(x, y, z);
            double caveScale = random.nextInt(10, 50);
            PerlinNoiseSampler perlin = new PerlinNoiseSampler(random);
            Set<BlockPos> visitedPositions = new HashSet<>();
            Deque<BlockPos> stack = new ArrayDeque<>();
            stack.push(new BlockPos(x, y, z));

            while (!stack.isEmpty()) {
                double threshold = carveThreshold;
                BlockPos pos = stack.pop();

                if (world.isOutOfHeightLimit(pos) || !visitedPositions.add(pos)) continue;

                int distance = Math.abs(pos.getX() - origin.getX())
                        + Math.abs(pos.getY() - origin.getY())
                        + Math.abs(pos.getZ() - origin.getZ());
                if (distance > 100) threshold -= (double)distance /200;

                double noise = perlin.sample(pos.x / caveScale, pos.y / caveScale, pos.z / caveScale);
                BlockState state = world.getBlockState(pos);
                if (state.isOf(Block.WATER) || state.isOf(Block.LAVA) || state.isOf(Block.FLOWING_WATER) || state.isOf(Block.FLOWING_LAVA)) {
                    WorldHelper.setBlockStateWithPredicate(
                            (x1, y1, z1, r) -> Block.STONE.getDefaultState(),
                            world,
                            pos,
                            BlockPredicates.BEDROCK_SAFE
                    );
                    noise = 1;
                }
                if (noise <= threshold) {
                    boolean blockCarved = WorldHelper.setBlockStateWithPredicate(
                            (x1, y1, z1, r) -> States.AIR.get(),
                            world,
                            pos,
                            BlockPredicates.BEDROCK_SAFE
                    );

                    if (blockCarved) {
                        if (pos.x == origin.x && pos.y == origin.y && pos.z == origin.z) System.out.println(pos.x + ", " + pos.y + ", " + pos.z);
                        stack.push(new BlockPos(pos.x + 1, pos.y, pos.z));
                        stack.push(new BlockPos(pos.x - 1, pos.y, pos.z));
                        stack.push(new BlockPos(pos.x, pos.y + 1, pos.z));
                        stack.push(new BlockPos(pos.x, pos.y - 1, pos.z));
                        stack.push(new BlockPos(pos.x, pos.y, pos.z + 1));
                        stack.push(new BlockPos(pos.x, pos.y, pos.z - 1));
                    }
                }
            }
            return true;
        }
        return false;
    }
}
