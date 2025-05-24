package io.github.trashoflevillage.trashsbetamod.mixin;

import io.github.trashoflevillage.trashsbetamod.access.WorldMixinAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(World.class)
public abstract class WorldMixin implements WorldMixinAccess {
    @Shadow public abstract long getTime();

    @Shadow public Random random;
    @Shadow public List<PlayerEntity> players;
    @Unique
    public boolean bloodMoon = false;

    @Inject(method = "tick", at = @At("TAIL"))
    public void postTick(CallbackInfo ci) {
        if (getTime() % 13000 == 0) {
            bloodMoon = random.nextFloat() <= 0.1f;
            if (bloodMoon) {
                for (PlayerEntity entity : players) entity.sendMessage("§cA blood moon has risen!");
            }
        }
        if (getTime() % 24000 == 0) bloodMoon = false;
    }

    @Override
    public boolean isBloodMoon() {
        return bloodMoon;
    }
}
