package io.github.trashoflevillage.trashsbetamod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.PigZombieEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;

import java.util.Random;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Redirect(
            method = "tickLiving",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;findPath(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;F)Lnet/minecraft/entity/ai/pathing/Path;"
            )
    )
    public Path modifyEntityPathing(World instance, Entity entity, Entity target, float range) {
        if (entity instanceof ZombieEntity && !(entity instanceof PigZombieEntity)) {
            return getZombiePath(instance, entity, (int)target.x, (int)target.y, (int)target.z, range);
        }
        return entity.world.findPath(entity, target, range);
    }
    @Redirect(
            method = "pathingUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;findPath(Lnet/minecraft/entity/Entity;IIIF)Lnet/minecraft/entity/ai/pathing/Path;"
            )
    )
    public Path modifyEntityPathing(World instance, Entity entity, int x, int y, int z, float range) {
        if (entity instanceof ZombieEntity && !(entity instanceof PigZombieEntity)) {
            return getZombiePath(instance, entity, x, y, z, range);
        }
        return entity.world.findPath(entity, x, y, z, range);
    }

    @Unique
    public Path getZombiePath(World world, Entity zombie, int targetX, int targetY, int targetZ, float range) {
        if (zombie.getBrightnessAtEyes(1) >= 0.5f) {
            double distX = targetX - zombie.x;
            double distZ = targetZ - zombie.z;
            int newX = (int)(zombie.x - distX);
            int newZ = (int)(zombie.z - distZ);
            return world.findPath(
                    zombie,
                    newX,
                    targetY,
                    newZ,
                    range
            );
        }
        return world.findPath(zombie, targetX, targetY, targetZ, range);
    }
}
