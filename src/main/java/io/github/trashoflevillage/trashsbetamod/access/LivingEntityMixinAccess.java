package io.github.trashoflevillage.trashsbetamod.access;

import net.minecraft.item.ItemStack;

public interface LivingEntityMixinAccess {
    void setHeldItem(ItemStack item);
}
