package io.github.trashoflevillage.trashsbetamod.util.loot;

import io.github.trashoflevillage.trashsbetamod.items.ModItems;
import net.minecraft.item.Item;

public class LootTables {
    public static final LootTable MINESHAFT = new LootTable(
            8,
            new LootEntry(
                    new LootTable(
                            1,
                            new LootEntry(new LootItem(Item.DIAMOND_PICKAXE, 0, 1, 1), 1),
                            new LootEntry(new LootItem(Item.IRON_PICKAXE, 0, 1, 1), 3)
                    ), 2, 2
            ),
            new LootEntry(
                    new LootTable(
                            1,
                            new LootEntry(new LootItem(Item.IRON_INGOT, 0, 1, 4), 3),
                            new LootEntry(new LootItem(Item.GOLD_INGOT, 0, 1, 4), 3),
                            new LootEntry(new LootItem(ModItems.COPPER_INGOT, 0, 1, 4), 4),
                            new LootEntry(new LootItem(Item.DIAMOND, 0, 1, 4), 1)
                    ), 4
            ),
            new LootEntry(new LootItem(Item.COOKED_PORKCHOP, 0, 1, 1), 2)
    );
}
