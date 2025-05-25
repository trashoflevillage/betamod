package io.github.trashoflevillage.trashsbetamod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.trashoflevillage.trashsbetamod.BetaMod;
import io.github.trashoflevillage.trashsbetamod.world.gen.ChunkListener;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

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
    private int fixBedrockLevel(Random random, int i) {
        int original = random.nextInt(i);
        return original + BetaMod.BEDROCK_LEVEL;
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 2
            )
    )
    private int redirectLakeY(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 7
            )
    )
    private int redirectLavaLakeY(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 11
            )
    )
    private int redirectDungeonY(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 14
            )
    )
    private int redirectClayY(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 17
            )
    )
    private int redirectDirtY(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 20
            )
    )
    private int redirectGravelY(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 23
            )
    )
    private int redirectCoalY(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 26
            )
    )
    private int redirectIronY(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 29
            )
    )
    private int redirectGoldY(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 32
            )
    )
    private int redirectRedstoneY(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 35
            )
    )
    private int redirectDiamondY(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 38
            )
    )
    private int redirectLapis1Y(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

    @Redirect(
            method = "decorate",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 39
            )
    )
    private int redirectLapis2Y(Random random, int bound) {
        return ChunkListener.getRandomYFromBedrock(random, bound);
    }

}
