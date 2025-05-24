package io.github.trashoflevillage.trashsbetamod.blocks.custom;

import io.github.trashoflevillage.trashsbetamod.blocks.custom.entity.MonsterBoxBlockEntity;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class MonsterBoxBlock extends TrashBlockWithEntity {
    public MonsterBoxBlock(Identifier identifier) {
        super(identifier, Material.STONE);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new MonsterBoxBlockEntity();
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return 0;
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return 0;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
}
