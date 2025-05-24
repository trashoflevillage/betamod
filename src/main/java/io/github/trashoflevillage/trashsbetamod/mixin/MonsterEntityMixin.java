package io.github.trashoflevillage.trashsbetamod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.mob.PigZombieEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MonsterEntity.class)
public class MonsterEntityMixin {
    @ModifyReturnValue(method = "getTargetInRange", at = @At("TAIL"))
    public Entity getTargetInRange(Entity original) {
        MonsterEntity monster = (MonsterEntity)(Object)this;
        if (monster instanceof ZombieEntity && !(monster instanceof PigZombieEntity)) {
            if (monster.getBrightnessAtEyes(1) >= 0.5f) {
                return null;
            }
        }
        return original;
    }
}
