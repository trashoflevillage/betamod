package io.github.trashoflevillage.trashsbetamod;

import io.github.trashoflevillage.trashsbetamod.blocks.ModBlockEntities;
import io.github.trashoflevillage.trashsbetamod.blocks.ModBlocks;
import io.github.trashoflevillage.trashsbetamod.items.ModItems;
import io.github.trashoflevillage.trashsbetamod.items.ModRecipes;
import io.github.trashoflevillage.trashsbetamod.world.gen.ChunkListener;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.world.gen.WorldGenEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.util.Namespace;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class BetaMod {
    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    @SuppressWarnings("UnstableApiUsage")
    public static final Namespace NAMESPACE = Namespace.resolve();

    public static final Logger LOGGER = NAMESPACE.getLogger();

    public static final int BEDROCK_LEVEL = -64;

    @EventListener
    private static void serverInit(InitEvent event) {

    }

    @EventListener
    private static void registerBlocks(BlockRegistryEvent event) {
        ModBlocks.registerAll();
    }

    @EventListener
    private static void registerItems(ItemRegistryEvent event) {
        ModItems.registerAll();
    }

    @EventListener
    private static void registerBlockEntities(BlockEntityRegisterEvent event) {
        ModBlockEntities.registerAll(event);
    }

    @EventListener
    public static void populateOverworld(WorldGenEvent.ChunkDecoration event) {
        ChunkListener.populate(event);
    }

    @EventListener
    public static void registerRecipes(RecipeRegisterEvent event) {
        ModRecipes.registerAll(event);
    }
}
