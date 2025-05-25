package io.github.trashoflevillage.trashsbetamod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.trashoflevillage.trashsbetamod.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.DungeonFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(DungeonFeature.class)
public class DungeonFeatureMixin {
    @Unique
    private static final ThreadLocal<Block> mossStone = new ThreadLocal<>();

    @Inject(method = "generate", at = @At("HEAD"))
    public void preGenerate(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        Block[] possibleMossStones = new Block[] {
                Block.MOSSY_COBBLESTONE,
                ModBlocks.BLUE_MOSSY_COBBLESTONE,
                ModBlocks.PINK_MOSSY_COBBLESTONE
        };
        mossStone.set(possibleMossStones[random.nextInt(possibleMossStones.length)]);
    }

    @Inject(method = "generate", at = @At("TAIL"))
    public void postGenerate(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        mossStone.remove();
    }

    @ModifyArg(
            method = "generate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;setBlock(IIII)Z",
                    ordinal = 1
            ),
            index = 3
    )
    public int replaceMossStone(int x, @Local(argsOnly = true) Random random) {
        return mossStone.get().id;
    }
}
