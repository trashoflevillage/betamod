package io.github.trashoflevillage.trashsbetamod.util.world;

import io.github.trashoflevillage.trashsbetamod.util.loot.LootTable;
import io.github.trashoflevillage.trashsbetamod.util.loot.LootTables;
import net.minecraft.block.Block;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;

import java.util.List;
import java.util.Random;

public class WorldHelper {
    @FunctionalInterface
    public interface BlockStateProvider {
        BlockState get(int x, int y, int z, Random random);
    }

    public static void setCuboid(BlockStateProvider provider, World world, BlockPos pos, int sizeX, int sizeY, int sizeZ, BlockPredicates.Predicate predicate) {
        for (int dx = 0; dx < sizeX; dx++) {
            for (int dy = 0; dy < sizeY; dy++) {
                for (int dz = 0; dz < sizeZ; dz++) {
                    setBlockStateWithPredicate(provider, world, new BlockPos(pos.x + dx, pos.y + dy, pos.z + dz), predicate);
                }
            }
        }
    }

    public static void setCuboid(BlockStateProvider provider, World world, BlockPos pos, int sizeX, int sizeY, int sizeZ) {
        setCuboid(provider, world, pos, sizeX, sizeY, sizeZ, BlockPredicates.BEDROCK_SAFE);
    }

    public static boolean setBlockStateWithPredicate(BlockStateProvider provider, World world, BlockPos pos, BlockPredicates.Predicate predicate) {
        if (predicate.isValidForSetting(world.getBlockState(pos))) {
            world.setBlockState(
                    pos,
                    provider.get(pos.x, pos.y, pos.z, world.random)
            );
            return true;
        }
        return false;
    }

    public static boolean generateLootChest(LootTable lootTable, World world, BlockPos chestPos, BlockPredicates.Predicate predicate) {
        return generateLootChest(lootTable, world, world.random, chestPos, predicate);
    }

    public static boolean generateLootChest(LootTable lootTable, World world, Random random, BlockPos chestPos, BlockPredicates.Predicate predicate) {
        WorldHelper.setBlockStateWithPredicate(
                (x, y, z, r) -> Block.CHEST.getDefaultState(),
                world,
                chestPos,
                predicate
        );
        ChestBlockEntity blockEntity = (ChestBlockEntity)world.getBlockEntity(chestPos.x, chestPos.y, chestPos.z);
        if (blockEntity != null) {
            List<ItemStack> items = lootTable.roll(random);
            for (int i = 0; i < 8; i++) {
                Integer slot = null;
                int attempts = 0;
                while (slot == null || blockEntity.getStack(slot) != null) {
                    slot = random.nextInt(blockEntity.size());
                    attempts++;
                    if (attempts > 5) break;
                }
                blockEntity.setStack(slot, items.get(i));
            }
            return true;
        }
        return false;
    }
}
