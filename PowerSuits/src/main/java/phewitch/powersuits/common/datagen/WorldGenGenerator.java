package phewitch.powersuits.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import phewitch.powersuits.common.world.BiomeModifiers;
import phewitch.powersuits.common.world.ConfiguredFeatures;
import phewitch.powersuits.common.world.PlacedFeatures;
import phewitch.powersuits.PowerSuits;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldGenGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModifiers::bootstrap);

    public WorldGenGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(PowerSuits.MODID));
    }
}
