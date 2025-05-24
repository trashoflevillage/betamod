package io.github.trashoflevillage.trashsbetamod.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipeManager;
import net.minecraft.recipe.ToolRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ToolRecipes.class)
public class ToolRecipesMixin {
    @WrapWithCondition(method = "add", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/CraftingRecipeManager;addShapedRecipe(Lnet/minecraft/item/ItemStack;[Ljava/lang/Object;)V"))
    public boolean removeToolRecipes(CraftingRecipeManager instance, ItemStack output, Object[] objects) {
        Item[] remove = new Item[] {
                Item.STONE_PICKAXE,
                Item.STONE_AXE,
                Item.STONE_SHOVEL,
                Item.STONE_HOE
        };
        for (Item i : remove)
            if (output.isOf(i)) return false;
        return true;
    }
}
