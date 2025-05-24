package io.github.trashoflevillage.trashsbetamod.util.loot;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Random;

public class LootItem implements Lootable {
    private final Item item;
    private final int damage;
    private final int minCount;
    private final int maxCount;

    public LootItem(Item item, int damage, int minCount, int maxCount) {
        this.item = item;
        this.damage = damage;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    public List<ItemStack> roll(Random random) {
        return List.of(getItemStack(random));
    }

    public ItemStack getItemStack(Random random) {
        ItemStack stack = new ItemStack(item);
        stack.setDamage(damage);
        stack.count = random.nextInt(minCount, maxCount + 1);
        return stack;
    }
}
