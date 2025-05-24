package io.github.trashoflevillage.trashsbetamod.items;

import io.github.trashoflevillage.trashsbetamod.BetaMod;
import io.github.trashoflevillage.trashsbetamod.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.item.BlockItemForm;
import net.modificationstation.stationapi.api.template.item.*;

import java.util.function.Function;

public class ModItems {
    public static final Item COPPER_INGOT = registerItem(
            "copper_ingot",
            (n) -> new TemplateItem(
                BetaMod.NAMESPACE.id(n)
            )
    );
    public static final Item COPPER_SWORD = registerItem(
            "copper_sword",
            (n) -> new TemplateSwordItem(
                BetaMod.NAMESPACE.id(n), ToolMaterial.STONE
            )
    );
    public static final Item COPPER_PICKAXE = registerItem(
            "copper_pickaxe",
            (n) -> new TemplatePickaxeItem(
                    BetaMod.NAMESPACE.id(n), ToolMaterial.STONE
            )
    );
    public static final Item COPPER_AXE = registerItem(
            "copper_axe",
            (n) -> new TemplateAxeItem(
                    BetaMod.NAMESPACE.id(n), ToolMaterial.STONE
            )
    );
    public static final Item COPPER_SHOVEL = registerItem(
            "copper_shovel",
            (n) -> new TemplateShovelItem(
                    BetaMod.NAMESPACE.id(n), ToolMaterial.STONE
            )
    );
    public static final Item COPPER_HOE = registerItem(
            "copper_hoe",
            (n) -> new TemplateHoeItem(
                    BetaMod.NAMESPACE.id(n), ToolMaterial.STONE
            )
    );

    public static final Item LIFE_QUARTZ = registerItem(
            "life_quartz",
            (n) -> new TemplateItem(
                    BetaMod.NAMESPACE.id(n)
            )
    );

    private static Item registerItem(String name, Function<String, Item> itemProvider) {
        Item item = itemProvider.apply(name);
        return item.setTranslationKey(BetaMod.NAMESPACE, name);
    }

    public static void registerAll() {
        System.out.println("Added " + BetaMod.NAMESPACE.getName() + " items to the registry.");
    }
}
