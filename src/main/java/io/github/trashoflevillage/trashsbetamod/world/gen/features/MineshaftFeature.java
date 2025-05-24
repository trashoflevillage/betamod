package io.github.trashoflevillage.trashsbetamod.world.gen.features;

import io.github.trashoflevillage.trashsbetamod.blocks.ModBlocks;
import io.github.trashoflevillage.trashsbetamod.blocks.custom.entity.MonsterBoxBlockEntity;
import io.github.trashoflevillage.trashsbetamod.util.loot.LootTables;
import io.github.trashoflevillage.trashsbetamod.util.world.BlockPredicates;
import io.github.trashoflevillage.trashsbetamod.util.world.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.modificationstation.stationapi.api.block.States;
import net.modificationstation.stationapi.api.util.math.Direction;

import java.util.List;
import java.util.Random;

public class MineshaftFeature extends Feature {
    private final float chance;
    public MineshaftFeature(float chance) {
        this.chance = chance;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        if (random.nextFloat() <= 1/chance) {
            Block[] possiblePlanks = new Block[] {
                    Block.PLANKS,
                    ModBlocks.WHITE_PLANKS.block,
                    ModBlocks.ORANGE_PLANKS.block,
                    ModBlocks.MAGENTA_PLANKS.block,
                    ModBlocks.LIGHT_BLUE_PLANKS.block,
                    ModBlocks.YELLOW_PLANKS.block,
                    ModBlocks.LIME_PLANKS.block,
                    ModBlocks.PINK_PLANKS.block,
                    ModBlocks.GRAY_PLANKS.block,
                    ModBlocks.LIGHT_GRAY_PLANKS.block,
                    ModBlocks.CYAN_PLANKS.block,
                    ModBlocks.PURPLE_PLANKS.block,
                    ModBlocks.BLUE_PLANKS.block,
                    ModBlocks.BROWN_PLANKS.block,
                    ModBlocks.GREEN_PLANKS.block,
                    ModBlocks.RED_PLANKS.block,
                    ModBlocks.BLACK_PLANKS.block
            };
            Block plankFloors = possiblePlanks[random.nextInt(possiblePlanks.length)];
            generateMainRoom(world, random, x, y, z, plankFloors);

            PerlinNoiseSampler perlin = new PerlinNoiseSampler(random);
            Direction dir = null;
            while (dir == null || dir == Direction.UP || dir == Direction.DOWN) dir = Direction.random(random);
            generateDownPaths(world, random, x, y, z, dir, perlin, plankFloors);

            Direction dir2 = null;
            while (dir2 == null || dir2 == Direction.UP || dir2 == Direction.DOWN || dir2.equals(dir)) dir2 = Direction.random(random);
            generateDownPaths(world, random, x, y, z, dir2, perlin, plankFloors);

            int chestX = 0;
            int chestZ = 0;
            while (chestX == 0 && chestZ == 0) {
                chestX = (int)(world.random.nextFloat() * 3) - 1;
                chestZ = (int)(world.random.nextFloat() * 3) - 1;
            }
            BlockPos centerPos = new BlockPos(x + 5, y, z + 5);
            BlockPos chestPos = centerPos.add(chestX * 5, 0, chestZ * 5);
            if (!world.getBlockState(chestPos).isAir()) chestPos.add(0, 1, 0);
            generateMineshaftChest(world, random, chestPos);

            return true;
        }
        return false;
    }

    private void generateMainRoom(World world, Random random, int x, int y, int z, Block planks) {
        Block[] possibleMossStones = new Block[] {
                Block.MOSSY_COBBLESTONE,
                ModBlocks.BLUE_MOSSY_COBBLESTONE,
                ModBlocks.PINK_MOSSY_COBBLESTONE
        };
        Block mossStone = possibleMossStones[random.nextInt(possibleMossStones.length)];

        WorldHelper.setCuboid((dx, dy, dz, r) -> {
            return States.AIR.get();
        }, world, new BlockPos(x, y, z), 11, 10, 11);
        WorldHelper.setCuboid((dx, dy, dz, r) -> {
            return planks.getDefaultState();
        }, world, new BlockPos(x, y - 1, z), 11, 1, 11);
        WorldHelper.setCuboid((dx, dy, dz, r) -> {
            return random.nextBoolean() ? Block.COBBLESTONE.getDefaultState() : mossStone.getDefaultState();
        }, world, new BlockPos(x - 1, y, z - 1), 13, 11, 13, BlockPredicates.REPLACE_STONE);
    }

    private void generateMineshaftChest(World world, Random random, BlockPos chestPos) {
        WorldHelper.generateLootChest(LootTables.MINESHAFT, world, random, chestPos, BlockPredicates.MONSTER_BOX_AND_CHEST_SAFE);
    }

    private void generateDownPaths(World world, Random random, int x, int y, int z, Direction dir, PerlinNoiseSampler perlin, Block planks) {
        BlockPos currentPos = new BlockPos(x + 5, y, z + 5);
        int len = (int)(random.nextFloat() * 100) + 50;
        for (int i = 0; i < len; i++) {
            currentPos = currentPos.add(dir.getVector());
            if (i > 10) currentPos = currentPos.add(0, perlin.sample(currentPos.x, currentPos.z), 0);

            WorldHelper.setCuboid((dx, dy, dz, r) -> {
                return States.AIR.get();
            }, world, currentPos.add(-1, 1, -1), 3, 3, 3, BlockPredicates.MONSTER_BOX_AND_CHEST_SAFE);

            WorldHelper.setCuboid((dx, dy, dz, r) -> {
                return planks.getDefaultState();
            }, world, currentPos.add(-1, 0, -1), 3, 1, 3, BlockPredicates.MONSTER_BOX_AND_CHEST_SAFE);

            Direction pathDir = Direction.fromRotation(dir.asRotation() + 90);
            if (random.nextBoolean()) pathDir = pathDir.getOpposite();
            if (i % 10 == 0) generatePath(world, random, currentPos.x, currentPos.y, currentPos.z, pathDir, planks);
        }
    }

    private void generatePath(World world, Random random, int x, int y, int z, Direction dir, Block planks) {
        BlockPos currentPos = new BlockPos(x, y, z);
        int len = (int)(random.nextFloat() * 30) + 10;
        for (int i = 0; i < len; i++) {
            currentPos = currentPos.add(dir.getVector());

            WorldHelper.setCuboid((dx, dy, dz, r) -> {
                return States.AIR.get();
            }, world, currentPos.add(-1, 1, -1), 3, 3, 3, BlockPredicates.MONSTER_BOX_AND_CHEST_SAFE);

            WorldHelper.setCuboid((dx, dy, dz, r) -> {
                return planks.getDefaultState();
            }, world, currentPos.add(-1, 0, -1), 3, 1, 3, BlockPredicates.MONSTER_BOX_AND_CHEST_SAFE);

            // monster boxes and chests
            if (random.nextFloat() < 0.03f) {
                Integer dx = null;
                Integer dy = null;
                Integer dz = null;
                int attempts = 0;
                while (dx == null || !world.getBlockState(dx, dy, dz).isAir() || world.getBlockState(dx, dy - 1, dz).isAir()) {
                    if (attempts > 5) return;
                    dx = x + random.nextInt(-2, 3);
                    dy = y + random.nextInt(-2, 3);
                    dz = z + random.nextInt(-2, 3);
                    attempts++;
                }
                BlockPos decorPos = new BlockPos(dx, dy, dz);
                if (!world.getBlockState(decorPos).isAir()) decorPos.add(0, 1, 0);
                if (random.nextBoolean()) generateMonsterBox(world, random, decorPos);
                else generateMineshaftChest(world, random, decorPos);
            }
        }
    }

    private void generateMonsterBox(World world, Random random, BlockPos pos) {
        WorldHelper.setBlockStateWithPredicate(
                ((x1, y1, z1, random1) -> ModBlocks.MONSTER_BOX.getDefaultState()),
                world,
                pos,
                BlockPredicates.REPLACE_AIR
        );
        MonsterBoxBlockEntity blockEntity = (MonsterBoxBlockEntity)world.getBlockEntity(pos.x, pos.y, pos.z);
        if (blockEntity != null) {
            blockEntity.setSpawnedEntityId(getRandomEntity(random));
        }
    }

    private String getRandomEntity(Random random) {
        int var2 = random.nextInt(4);
        if (var2 == 0) {
            return "Skeleton";
        } else if (var2 == 1) {
            return "Zombie";
        } else if (var2 == 2) {
            return "Zombie";
        } else {
            return "Spider";
        }
    }
}
