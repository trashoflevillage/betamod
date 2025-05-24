package io.github.trashoflevillage.trashsbetamod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.datafixers.DataFixerUpper;
import io.github.trashoflevillage.trashsbetamod.access.EntityMixinAccess;
import io.github.trashoflevillage.trashsbetamod.access.LivingEntityMixinAccess;
import io.github.trashoflevillage.trashsbetamod.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.PigZombieEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.Random;

@Mixin(Entity.class)
public class EntityMixin implements EntityMixinAccess {
    @Unique private String textureOverride = null;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(World par1, CallbackInfo ci) {
        textureOverride = initTextureOverride();
    }

    @Unique
    private String initTextureOverride() {
        Entity entity = (Entity)(Object)this;
        if (entity.world != null) {
            Random rng = entity.world.random;
            if (entity instanceof ZombieEntity) {
                if (!(entity instanceof PigZombieEntity)) {
                    String[] textures = new String[] {
                            null,
                            "browncoat",
                            "mutilated",
                            "sports"
                    };
                    int index = (int)(rng.nextFloat() * textures.length);
                    String texture = textures[index];
                    if (texture == null) return null;
                    return "zombie/" + texture;
                }
            }
            if (entity instanceof SpiderEntity) {
                String[] textures = new String[] {
                        null,
                        "purple",
                        "brown"
                };
                int index = (int)(rng.nextFloat() * textures.length);
                String texture = textures[index];
                if (texture == null) return null;
                return "spider/" + texture;
            }
            if (entity instanceof CreeperEntity) {
                String[] textures = new String[] {
                        null,
                        "light",
                        "dark"
                };
                int index = (int)(rng.nextFloat() * textures.length);
                String texture = textures[index];
                if (texture == null) return null;
                return "creeper/" + texture;
            }
        }
        return null;
    }

    @Unique
    @Override
    public String getTextureOverride() {
        return textureOverride;
    }

    @Unique
    private static final String textureOverrideNbtKey = "TextureOverride";
    @Inject(method = "write", at = @At("TAIL"))
    public void write(NbtCompound nbt, CallbackInfo ci) {
        nbt.putString(textureOverrideNbtKey, Objects.requireNonNullElse(textureOverride, "none"));
    }

    @Inject(method = "read", at = @At("TAIL"))
    public void read(NbtCompound nbt, CallbackInfo ci) {
        if (!nbt.contains(textureOverrideNbtKey)) textureOverride = initTextureOverride();
        else {
            String override = nbt.getString(textureOverrideNbtKey);;
            if (!Objects.equals(override, "none")) textureOverride = override;
            else textureOverride = null;
        }
    }
}
