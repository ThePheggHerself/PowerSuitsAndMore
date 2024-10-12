package phewitch.powersuits.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import phewitch.powersuits.common.datagen.loot.BlockLootTables;

import java.util.List;
import java.util.Set;

public class LootTableGenerator {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}
