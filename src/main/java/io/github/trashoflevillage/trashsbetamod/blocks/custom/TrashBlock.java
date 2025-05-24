package io.github.trashoflevillage.trashsbetamod.blocks.custom;

import io.github.trashoflevillage.trashsbetamod.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TrashBlock extends TemplateBlock {
    public static ArrayList<TrashBlock> flammableBlocks = new ArrayList<>();
    private Function<Random, Integer> lootCountProvider = (r) -> 0;
    private BiFunction<Integer, Random, Integer> lootIdProvider = (m, r) -> 0;
    private int burnChance = 0;
    private int fireSpreadChance = 0;

    public TrashBlock(Identifier identifier, Material material) {
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

    public void setFlammable(int burnChance, int spreadChance) {
        this.burnChance = burnChance;
        fireSpreadChance = spreadChance;
        flammableBlocks.add(this);
    }

    public int getBurnChance() {
        return burnChance;
    }

    public int getFireSpreadChance() {
        return fireSpreadChance;
    }
}
