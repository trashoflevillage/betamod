package io.github.trashoflevillage.trashsbetamod.util.loot;

import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Random;

public class LootEntry implements Lootable {
    private final Lootable lootable;
    private final int weight;
    private final int diminishingWeight;
    public LootEntry(Lootable lootable, int weight, int diminishingWeight) {
        this.lootable = lootable;
        this.weight = weight;
        this.diminishingWeight = diminishingWeight;
    }

    public LootEntry(Lootable lootable, int weight) {
        this.lootable = lootable;
        this.weight = weight;
        this.diminishingWeight = 0;
    }

    public int getWeight() {
        return weight;
    }

    public int getDiminishingWeight() {
        return diminishingWeight;
    }

    @Override
    public List<ItemStack> roll(Random random) {
        return lootable.roll(random);
    }
}
