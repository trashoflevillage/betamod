package io.github.trashoflevillage.trashsbetamod.blocks;

import io.github.trashoflevillage.trashsbetamod.blocks.custom.MonsterBoxBlock;
import io.github.trashoflevillage.trashsbetamod.blocks.custom.TrashBlock;
import io.github.trashoflevillage.trashsbetamod.BetaMod;
import io.github.trashoflevillage.trashsbetamod.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateStairsBlock;

import java.util.ArrayList;
import java.util.function.Function;

public class ModBlocks {
    public static ArrayList<Block> blocksWithItems = new ArrayList<>();

    public static final Block COPPER_ORE = registerBlock(
            "copper_ore",
            (n) -> {
                TrashBlock b = new TrashBlock(
                        BetaMod.NAMESPACE.id(n), Material.STONE
                );
                b.setHardness(3);
                b.setLootCountProvider((random -> 1));
                b.setLootIdProvider(((metadata, random) -> b.id));
                return b;
            }
    );

    public static final Block LIFE_QUARTZ_ORE = registerBlock(
            "life_quartz_ore",
            (n) -> {
                TrashBlock b = new TrashBlock(
                        BetaMod.NAMESPACE.id(n), Material.STONE
                );
                b.setHardness(3);
                b.setLootCountProvider((random -> (int)(random.nextFloat() * 3) + 1));
                b.setLootIdProvider(((metadata, random) -> ModItems.LIFE_QUARTZ.id));
                return b;
            }
    );

    public static final Block MONSTER_BOX = registerBlock(
            "monster_box",
            (n) -> {
                MonsterBoxBlock b = new MonsterBoxBlock(
                        BetaMod.NAMESPACE.id(n)
                );
                b.setHardness(100);
                b.setLootCountProvider((random -> (int)(random.nextFloat() * 3) + 1));
                return b;
            }
    );

    public static final BlockSet WHITE_PLANKS = registerColoredPlanks("white");
    public static final BlockSet ORANGE_PLANKS = registerColoredPlanks("orange");
    public static final BlockSet MAGENTA_PLANKS = registerColoredPlanks("magenta");
    public static final BlockSet LIGHT_BLUE_PLANKS = registerColoredPlanks("light_blue");
    public static final BlockSet YELLOW_PLANKS = registerColoredPlanks("yellow");
    public static final BlockSet LIME_PLANKS = registerColoredPlanks("lime");
    public static final BlockSet PINK_PLANKS = registerColoredPlanks("pink");
    public static final BlockSet GRAY_PLANKS = registerColoredPlanks("gray");
    public static final BlockSet LIGHT_GRAY_PLANKS = registerColoredPlanks("light_gray");
    public static final BlockSet CYAN_PLANKS = registerColoredPlanks("cyan");
    public static final BlockSet PURPLE_PLANKS = registerColoredPlanks("purple");
    public static final BlockSet BLUE_PLANKS = registerColoredPlanks("blue");
    public static final BlockSet BROWN_PLANKS = registerColoredPlanks("brown");
    public static final BlockSet GREEN_PLANKS = registerColoredPlanks("green");
    public static final BlockSet RED_PLANKS = registerColoredPlanks("red");
    public static final BlockSet BLACK_PLANKS = registerColoredPlanks("black");

    private static BlockSet registerColoredPlanks(String color) {
        Block block = registerBlock(color + "_planks", (n) -> {
            TrashBlock b = new TrashBlock(
                    BetaMod.NAMESPACE.id(n), Material.WOOD
            );
            b.setHardness(2);
            b.setResistance(3);
            b.setLootCountProvider((random) -> 1);
            b.setLootIdProvider((metadata, random) -> b.id);
            b.setSoundGroup(Block.WOOD_SOUND_GROUP);
            b.setFlammable(5, 20);
            return b;
        });
        StairsBlock stairs = (StairsBlock)registerBlock(color + "_planks_stairs",
                (n) -> new TemplateStairsBlock(BetaMod.NAMESPACE.id(n), block)
        );
        return new BlockSet(block, stairs);
    }

    private static Block registerBlock(String name, Function<String, Block> blockProvider) {
        Block block = blockProvider.apply(name);
        return block.setTranslationKey(BetaMod.NAMESPACE, name);
    }

    public static void registerAll() {
        System.out.println("Added " + BetaMod.NAMESPACE.getName() + " blocks to the registry.");
        Block.REDSTONE_ORE.setHardness(3);
    }

    public static class BlockSet {
        public final Block block;
        public final StairsBlock stairs;
        BlockSet(Block block, StairsBlock stairs) {
            this.block = block;
            this.stairs = stairs;
        }
    }
}
