package io.github.trashoflevillage.trashsbetamod.util.world;

import io.github.trashoflevillage.trashsbetamod.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.block.AbstractBlockState;
import net.modificationstation.stationapi.api.block.BlockState;

public class BlockPredicates {
    public static Predicate BEDROCK_SAFE = (state) -> !state.isOf(Block.BEDROCK);
    public static Predicate MONSTER_BOX_AND_CHEST_SAFE = (state) -> !state.isOf(Block.BEDROCK) && !state.isOf(ModBlocks.MONSTER_BOX) && !state.isOf(Block.CHEST);
    public static Predicate REPLACE_STONE = (state) -> state.isOf(Block.STONE);
    public static Predicate REPLACE_AIR = AbstractBlockState::isAir;
    public static Predicate ALWAYS_TRUE = (state) -> true;

    @FunctionalInterface
    public interface Predicate {
        boolean isValidForSetting(BlockState state);
    }
}
