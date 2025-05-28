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
        if (random.nextFloat() <= 1/chance) { // temporarily disabled
//            WorldHelper.BlockStateProvider[] fluidProviders = new WorldHelper.BlockStateProvider[] {
//                    (fx, fy, fz, r) -> States.AIR.get(),
//                    (fx, fy, fz, r) -> Block.WATER.getDefaultState(),
//                    (fx, fy, fz, r) -> Block.LAVA.getDefaultState()
//            };

            BlockPos origin = new BlockPos(x, y, z);
            double caveScale = random.nextInt(10, 50);
            PerlinNoiseSampler perlin = new PerlinNoiseSampler(random);
            Set<BlockPos> visitedPositions = new HashSet<>();
            Deque<BlockPos> stack = new ArrayDeque<>();
            stack.push(new BlockPos(x, y, z));

            while (!stack.isEmpty()) {
                double threshold = carveThreshold;
                BlockPos pos = stack.pop();
                BlockState state = world.getBlockState(pos);

                if (world.isOutOfHeightLimit(pos) || !visitedPositions.add(pos)) continue;

                BlockPos[] neighbors = getNeighborPositions(pos);

                boolean fluidAdjacent = isStateFluid(state);
                if (!fluidAdjacent) {
                    for (BlockPos p : neighbors) {
                        BlockState s = world.getBlockState(p);
                        if (isStateFluid(s)) {
                            if (shouldFluidBeReplaced(world, p)) {
                                replaceFluids(world, p);
                                continue;
                            }
                            fluidAdjacent = true;
                            break;
                        }
                    }
                }
                if (fluidAdjacent) continue;

                int distance = Math.abs(pos.getX() - origin.getX())
                        + Math.abs(pos.getY() - origin.getY())
                        + Math.abs(pos.getZ() - origin.getZ());
                if (distance > 100) threshold -= (double)distance / 500;

                double noise = perlin.sample(pos.x / caveScale, pos.y / caveScale, pos.z / caveScale);
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
                        for (BlockPos p : neighbors) stack.push(p);
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean shouldFluidBeReplaced(World world, BlockPos pos) {
        if (pos.y > 50) return false;

        BlockPos[] neighbors = getNeighborPositions(pos);

        for (BlockPos p : neighbors) {
            BlockState s = world.getBlockState(p);
            if (isStateFluid(s, false)) {
                return false;
            }
        }
        return true;
    }

    private void replaceFluids(World world, BlockPos pos) {
        Set<BlockPos> cleanedFluid = new HashSet<>();
        Stack<BlockPos> fluidsToClean = new Stack<>();
        fluidsToClean.add(pos);
        while(!fluidsToClean.isEmpty()) {
            BlockPos p = fluidsToClean.pop();
            if (!cleanedFluid.add(p)) {
                for (BlockPos n : getNeighborPositions(p)) {
                    if (isStateFluid(world.getBlockState(n))) {
                        fluidsToClean.add(n);
                    }
                }
                world.setBlockState(p, Block.STONE.getDefaultState());
            }
        }
    }

    private boolean isStateFluid(BlockState state) {
        return isStateFluid(state, true);
    }

    private boolean isStateFluid(BlockState state, boolean includeFlowing) {
        if (includeFlowing) return state.isOf(Block.WATER) || state.isOf(Block.LAVA) || state.isOf(Block.FLOWING_WATER) || state.isOf(Block.FLOWING_LAVA);
        return state.isOf(Block.WATER) || state.isOf(Block.LAVA);
    }

    public BlockPos[] getNeighborPositions(BlockPos pos) {
        return new BlockPos[] {
                new BlockPos(pos.x + 1, pos.y, pos.z),
                new BlockPos(pos.x - 1, pos.y, pos.z),
                new BlockPos(pos.x, pos.y + 1, pos.z),
                new BlockPos(pos.x, pos.y - 1, pos.z),
                new BlockPos(pos.x, pos.y, pos.z + 1),
                new BlockPos(pos.x, pos.y, pos.z - 1)
        };
    }
}
