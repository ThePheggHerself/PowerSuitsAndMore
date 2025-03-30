package phewitch.powersuits.common.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import phewitch.powersuits.common.item.BlocksManager;
import phewitch.powersuits.PowerSuits;

import java.util.List;

public class ConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> TITANIUM_ORE_KEY = registerKey("titanium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALLADIUM_ORE_KEY = registerKey("palladium_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> titaniumOres = List.of(
                OreConfiguration.target(stoneReplaceable, BlocksManager.STONE_TITANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, BlocksManager.DEEPSLATE_TITANIUM_ORE.get().defaultBlockState()));
        register(context, TITANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(titaniumOres, 4));

        List<OreConfiguration.TargetBlockState> palladiumOres = List.of(
                OreConfiguration.target(stoneReplaceable, BlocksManager.STONE_PALLADIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, BlocksManager.DEEPSLATE_PALLADIUM_ORE.get().defaultBlockState()));
        register(context, PALLADIUM_ORE_KEY, Feature.ORE, new OreConfiguration(palladiumOres, 6));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(PowerSuits.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
