package phewitch.powersuits.Common.World;

import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import phewitch.powersuits.PowerSuits;

import java.util.List;
@Mod.EventBusSubscriber(modid = PowerSuits.MODID)
public class WorldGenerationHelper {

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }
    public static List<PlacementModifier> commonOrePlacement(int veinsPerChunk, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(veinsPerChunk), placementModifier);
    }
    public static List<PlacementModifier> rareOrePlacement(int veinsPerChunk, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(veinsPerChunk), placementModifier);
    }

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event){
        var base = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

       base.add(Ores.PLACED_TITANIUM_ORE);
       base.add(Ores.PLACED_PALLADIUM_ORE);
    }
}
