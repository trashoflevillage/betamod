package io.github.trashoflevillage.trashsbetamod.mixin;

import io.github.trashoflevillage.trashsbetamod.BetaMod;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.OverworldDimension;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OverworldDimension.class)
public class OverworldDimensionMixin extends Dimension {
    @Override
    public int getBottomY() {
        return BetaMod.BEDROCK_LEVEL;
    }

    @Override
    public int getHeight() {
        return 384;
    }
}
