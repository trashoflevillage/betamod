package io.github.trashoflevillage.trashsbetamod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.trashoflevillage.trashsbetamod.access.LivingEntityMixinAccess;
import io.github.trashoflevillage.trashsbetamod.items.ModItems;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(World world, CallbackInfo ci) {
        ZombieEntity entity = (ZombieEntity)(Object)this;
        if (entity.world != null && world.random.nextFloat() < 0.2f) ((LivingEntityMixinAccess)entity).setHeldItem(new ItemStack(ModItems.LIFE_QUARTZ));
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 5))
    public int modifyAttackDamage(int constant) {
        return 7;
    }

    @ModifyConstant(method = "tickMovement", constant = @Constant(floatValue = 0.5F))
    public float disableDaylightBurning(float constant) {
        return 999F;
    }
}
