package io.github.trashoflevillage.trashsbetamod.util.loot;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Random;

public interface Lootable {
    List<ItemStack> roll(Random random);
}
