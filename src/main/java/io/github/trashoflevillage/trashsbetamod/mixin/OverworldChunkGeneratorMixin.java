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
