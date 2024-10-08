package phewitch.powersuits.Common.World;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import phewitch.powersuits.Common.Items.Materials.Palladium;
import phewitch.powersuits.Common.Items.Materials.Titanium;

import java.util.List;

public class Ores {
    public static final List<OreConfiguration.TargetBlockState> TITANIUM_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Titanium.STONE_TITANIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Titanium.DEEPSLATE_TITANIUM_ORE.get().defaultBlockState())
    );
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TITANIUM_ORE = FeatureUtils.register("titanium_ore",
            Feature.ORE, new OreConfiguration(TITANIUM_ORES, //Ore to generate
                    6 //Vein size
            ));
    public static final Holder<PlacedFeature> PLACED_TITANIUM_ORE = PlacementUtils.register("titanium_ore_placed",
            TITANIUM_ORE, WorldGenerationHelper.commonOrePlacement(4,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(4), VerticalAnchor.aboveBottom(64))));


    public static final List<OreConfiguration.TargetBlockState> PALLADIUM_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Palladium.STONE_PALLADIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Palladium.DEEPSLATE_PALLADIUM_ORE.get().defaultBlockState())
    );
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PALLADIUM_ORE = FeatureUtils.register("palladium_ore",
            Feature.ORE, new OreConfiguration(PALLADIUM_ORES, //Ore to generate
                    2 //Vein size
            ));
    public static final Holder<PlacedFeature> PLACED_PALLADIUM_ORE = PlacementUtils.register("palladium_ore_placed",
            PALLADIUM_ORE, WorldGenerationHelper.commonOrePlacement(2,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(2), VerticalAnchor.aboveBottom(32))));
}
