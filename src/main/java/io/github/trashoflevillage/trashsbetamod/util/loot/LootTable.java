package io.github.trashoflevillage.trashsbetamod.util.loot;

import net.minecraft.item.ItemStack;

import java.util.*;

public class LootTable implements Lootable {
    private final LootEntry[] entries;
    private final int expectedSize;
    public LootTable(int expectedSize, LootEntry... entries) {
        this.expectedSize = expectedSize;
        this.entries = entries;
    }

    public List<ItemStack> roll(Random random) {
        ArrayList<ItemStack> output = new ArrayList<>();
        HashMap<LootEntry, Integer> entryWeights = new HashMap<>();
        for (LootEntry e : entries) entryWeights.put(e, e.getWeight());
        for (int i = 0; i < expectedSize; i++) {
            TreeMap<Integer, LootEntry> entryTreeMap = new TreeMap<>();
            int maxWeightIndex = 0;
            for (LootEntry e : entries) {
                if (e != null && entryWeights.containsKey(e)) {
                    maxWeightIndex += entryWeights.get(e);
                    entryTreeMap.put(maxWeightIndex, e);
                }
            }
            int index = random.nextInt(0, maxWeightIndex + 1);
            Map.Entry<Integer, LootEntry> entry = entryTreeMap.ceilingEntry(index);
            if (entry != null) {
                output.addAll(entry.getValue().roll(random));
                int newWeight = entry.getValue().getWeight() - entry.getValue().getDiminishingWeight();
                if (newWeight > 0) entryWeights.put(entry.getValue(), (newWeight));
                else entryWeights.remove(entry.getValue());
            }
        }
        return output;
    }
}
