package io.github.trashoflevillage.trashsbetamod.blocks.custom;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TrashBlockWithEntity extends TemplateBlockWithEntity {
    private Function<Random, Integer> lootCountProvider = (r) -> 0;
    private BiFunction<Integer, Random, Integer> lootIdProvider = (m, r) -> 0;

    public TrashBlockWithEntity(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return lootCountProvider.apply(random);
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return lootIdProvider.apply(blockMeta, random);
    }

    public Function<Random, Integer> getLootCountProvider() {
        return lootCountProvider;
    }

    public void setLootCountProvider(Function<Random, Integer> lootCountProvider) {
        this.lootCountProvider = lootCountProvider;
    }

    public BiFunction<Integer, Random, Integer> getLootIdProvider() {
        return lootIdProvider;
    }

    public void setLootIdProvider(BiFunction<Integer, Random, Integer> lootIdProvider) {
        this.lootIdProvider = lootIdProvider;
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return null;
    }
}
