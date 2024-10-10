package phewitch.powersuits.Common.World;

import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.fml.common.Mod;
import phewitch.powersuits.PowerSuits;

import java.util.List;
@Mod.EventBusSubscriber(modid = PowerSuits.MODID)
public class OrePlacement {

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}
