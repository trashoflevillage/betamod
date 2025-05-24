package io.github.trashoflevillage.trashsbetamod.mixin;

import io.github.trashoflevillage.trashsbetamod.blocks.custom.TrashBlock;
import net.minecraft.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBlock.class)
public abstract class FireBlockMixin {
    @Shadow protected abstract void registerFlammableBlock(int block, int burnChance, int spreadChance);

    @Inject(method = "init", at = @At("TAIL"))
    public void initFlammables(CallbackInfo ci) {
        for (TrashBlock b : TrashBlock.flammableBlocks)
            registerFlammableBlock(b.id, b.getBurnChance(), b.getFireSpreadChance());
    }
}
