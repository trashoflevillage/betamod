package io.github.trashoflevillage.trashsbetamod.items;

import io.github.trashoflevillage.trashsbetamod.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class ModRecipes {
    public static void registerAll(RecipeRegisterEvent event) {
        RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);

        ItemStack INK_SAC = new ItemStack(Item.DYE); INK_SAC.setDamage(0);
        ItemStack ROSE_RED = new ItemStack(Item.DYE); ROSE_RED.setDamage(1);
        ItemStack CACTUS_GREEN = new ItemStack(Item.DYE); CACTUS_GREEN.setDamage(2);
        ItemStack COCOA_BEANS = new ItemStack(Item.DYE); COCOA_BEANS.setDamage(3);
        ItemStack LAPIS_LAZULI = new ItemStack(Item.DYE); LAPIS_LAZULI.setDamage(4);
        ItemStack PURPLE_DYE = new ItemStack(Item.DYE); PURPLE_DYE.setDamage(5);
        ItemStack CYAN_DYE = new ItemStack(Item.DYE); CYAN_DYE.setDamage(6);
        ItemStack LIGHT_GRAY_DYE = new ItemStack(Item.DYE); LIGHT_GRAY_DYE.setDamage(7);
        ItemStack GRAY_DYE = new ItemStack(Item.DYE); GRAY_DYE.setDamage(8);
        ItemStack PINK_DYE = new ItemStack(Item.DYE); PINK_DYE.setDamage(9);
        ItemStack LIME_DYE = new ItemStack(Item.DYE); LIME_DYE.setDamage(10);
        ItemStack DANDELION_YELLOW = new ItemStack(Item.DYE); DANDELION_YELLOW.setDamage(11);
        ItemStack LIGHT_BLUE_DYE = new ItemStack(Item.DYE); LIGHT_BLUE_DYE.setDamage(12);
        ItemStack MAGENTA_DYE = new ItemStack(Item.DYE); MAGENTA_DYE.setDamage(13);
        ItemStack ORANGE_DYE = new ItemStack(Item.DYE); ORANGE_DYE.setDamage(14);
        ItemStack BONE_MEAL = new ItemStack(Item.DYE); BONE_MEAL.setDamage(15);

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED) {
            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.BLACK_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', INK_SAC
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.RED_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', ROSE_RED
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.GREEN_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', CACTUS_GREEN
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.BROWN_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', COCOA_BEANS
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.BLUE_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', LAPIS_LAZULI
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.PURPLE_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', PURPLE_DYE
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.CYAN_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', CYAN_DYE
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.LIGHT_GRAY_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', LIGHT_GRAY_DYE
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.GRAY_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', GRAY_DYE
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.PINK_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', PINK_DYE
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.LIME_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', LIME_DYE
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.YELLOW_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', DANDELION_YELLOW
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.LIGHT_BLUE_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', LIGHT_BLUE_DYE
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.MAGENTA_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', MAGENTA_DYE
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.ORANGE_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', ORANGE_DYE
            );

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(ModBlocks.WHITE_PLANKS.block, 8),
                    "ppp", "pdp", "ppp",
                    'p', new ItemStack(Block.PLANKS),
                    'd', BONE_MEAL
            );

            addStairRecipe(ModBlocks.WHITE_PLANKS.stairs.asItem(), ModBlocks.WHITE_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.ORANGE_PLANKS.stairs.asItem(), ModBlocks.ORANGE_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.MAGENTA_PLANKS.stairs.asItem(), ModBlocks.MAGENTA_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.LIGHT_BLUE_PLANKS.stairs.asItem(), ModBlocks.LIGHT_BLUE_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.YELLOW_PLANKS.stairs.asItem(), ModBlocks.YELLOW_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.LIME_PLANKS.stairs.asItem(), ModBlocks.LIME_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.PINK_PLANKS.stairs.asItem(), ModBlocks.PINK_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.GRAY_PLANKS.stairs.asItem(), ModBlocks.GRAY_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.LIGHT_GRAY_PLANKS.stairs.asItem(), ModBlocks.LIGHT_GRAY_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.CYAN_PLANKS.stairs.asItem(), ModBlocks.CYAN_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.PURPLE_PLANKS.stairs.asItem(), ModBlocks.PURPLE_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.BLUE_PLANKS.stairs.asItem(), ModBlocks.BLUE_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.BROWN_PLANKS.stairs.asItem(), ModBlocks.BROWN_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.GREEN_PLANKS.stairs.asItem(), ModBlocks.GREEN_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.RED_PLANKS.stairs.asItem(), ModBlocks.RED_PLANKS.block.asItem());
            addStairRecipe(ModBlocks.BLACK_PLANKS.stairs.asItem(), ModBlocks.BLACK_PLANKS.block.asItem());
        }
    }

    public static void addStairRecipe(Item output, Item input) {
        CraftingRegistry.addShapedRecipe(
                new ItemStack(output, 4),
                "p  ", "pp ", "ppp",
                'p', input
        );
    }
}
