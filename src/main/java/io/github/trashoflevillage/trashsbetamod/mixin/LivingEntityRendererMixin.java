package io.github.trashoflevillage.trashsbetamod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.trashoflevillage.trashsbetamod.BetaMod;
import io.github.trashoflevillage.trashsbetamod.access.EntityMixinAccess;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    @ModifyExpressionValue(
            method = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;render(Lnet/minecraft/entity/LivingEntity;DDDFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getTexture()Ljava/lang/String;")
    )
    public String getTexture(String original, LivingEntity livingEntity) {
        String textureOverride = ((EntityMixinAccess)livingEntity).getTextureOverride();
        if (textureOverride != null) {
            return "/assets/" + BetaMod.NAMESPACE + "/stationapi/textures/mob/" + textureOverride + ".png";
        }
        return original;
    }
}
