package io.github.trashoflevillage.trashsbetamod.blocks;

import io.github.trashoflevillage.trashsbetamod.BetaMod;
import io.github.trashoflevillage.trashsbetamod.blocks.custom.entity.MonsterBoxBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.function.Function;

public class ModBlockEntities {
    public static void registerAll(BlockEntityRegisterEvent event) {
        register(event, "monster_box", MonsterBoxBlockEntity.class);
    }

    private static void register(BlockEntityRegisterEvent event, String name, Class<? extends BlockEntity> blockEntityClass) {
        event.register(blockEntityClass, BetaMod.NAMESPACE.id(name).toString());
    }
}
