package io.github.trashoflevillage.trashsbetamod.mixin;

import io.github.trashoflevillage.trashsbetamod.access.WorldMixinAccess;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow private World world;

    @ModifyConstant(method = "renderSky", constant = @Constant(stringValue = "/terrain/moon.png"))
    public String renderSky(String constant) {
        if (((WorldMixinAccess)world).isBloodMoon()) return "trashsbetamod:stationapi/textures/terrain/blood_moon.png";
        return constant;
    }
}
