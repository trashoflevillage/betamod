package io.github.trashoflevillage.trashsbetamod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.trashoflevillage.trashsbetamod.access.LivingEntityMixinAccess;
import io.github.trashoflevillage.trashsbetamod.items.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.item.StationFlatteningItem;
import net.modificationstation.stationapi.api.item.StationItemNbt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements LivingEntityMixinAccess {
    @Unique
    private ItemStack heldItem;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(World par1, CallbackInfo ci) {

    }

    @ModifyReturnValue(method = "getHeldItem", at = @At("TAIL"))
    public ItemStack getHeldItem(ItemStack original) {
        return heldItem;
    }

    @Override
    public void setHeldItem(ItemStack item) {
        heldItem = item;
    }

    @Inject(method = "writeNbt", at = @At("TAIL"))
    public void writeNbt(NbtCompound nbt, CallbackInfo ci) {
        if (heldItem != null) {
            nbt.putInt("HeldItemId", heldItem.itemId);
            nbt.putInt("HeldItemCount", heldItem.count);
            nbt.putInt("HeldItemDamage", heldItem.getDamage());
        } else {
            nbt.putInt("HeldItemId", -1);
        }
    }

    @Inject(method = "readNbt", at = @At("TAIL"))
    public void readNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("HeldItemId") && nbt.contains("HeldItemCount") && nbt.contains("HeldItemDamage")) {
            heldItem = new ItemStack(nbt.getInt("HeldItemId"), nbt.getInt("HeldItemCount"), nbt.getInt("HeldItemDamage"));
        } else if (nbt.contains("HeldItemId") && nbt.getInt("HeldItemId") == -1) {
            heldItem = null;
        }
    }

    @Inject(method = "dropItems", at = @At("TAIL"))
    public void dropItems(CallbackInfo ci) {
        if (heldItem != null) {
            LivingEntity entity = (LivingEntity) (Object) this;
            if (entity.world != null && entity.world.random.nextFloat() <= getStackDropChance(entity.getHeldItem())) {
                entity.dropItem(entity.getHeldItem(), 0);
            }
        }
    }

    @Unique
    private float getStackDropChance(ItemStack stack) {
        if (stack == null) return 0f;
        if (stack.isOf(ModItems.LIFE_QUARTZ)) return 0.5f;
        return 0f;
    }
}
