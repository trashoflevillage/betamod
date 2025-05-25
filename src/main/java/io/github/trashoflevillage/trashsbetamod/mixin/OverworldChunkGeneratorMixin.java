package io.github.trashoflevillage.trashsbetamod.mixin;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(OverworldChunkGenerator.class)
public class OverworldChunkGeneratorMixin {
//    @ModifyConstant(method = "buildSurfaces", constant = @Constant(
//            intValue = 0, expandZeroConditions = Constant.Condition.LESS_THAN_OR_EQUAL_TO_ZERO, ordinal = 2
//    ))
//    public int modifyBedrockHeight(int constant) {
//        return -64;
//    }

    @Redirect(
            method = "buildSurfaces",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I"
            )
    )
    private int redirectRandomNextInt(Random random, int i) {
        int original = random.nextInt(i);
        return original - 64;
    }
}
