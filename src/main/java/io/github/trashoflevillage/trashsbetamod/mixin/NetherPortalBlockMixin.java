package io.github.trashoflevillage.trashsbetamod.mixin;

import net.minecraft.block.NetherPortalBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {
    @Redirect(method = "create", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlock(IIII)Z"))
    public boolean create(World instance, int y, int z, int blockId, int i) {
        return false;
    }
}
