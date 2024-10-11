package phewitch.powersuits.Common.Datagen.Loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import phewitch.powersuits.Common.Items.Materials.Palladium;
import phewitch.powersuits.Common.Items.Materials.Titanium;

import java.util.ArrayList;
import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {
    public BlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(Titanium.STONE_TITANIUM_ORE.get());
        this.dropSelf(Titanium.DEEPSLATE_TITANIUM_ORE.get());
        this.dropSelf(Palladium.STONE_PALLADIUM_ORE.get());
        this.dropSelf(Palladium.DEEPSLATE_PALLADIUM_ORE.get());

        this.add(Titanium.STONE_TITANIUM_ORE.get(),
                block -> createSingleOreDrops(Titanium.STONE_TITANIUM_ORE.get(), Titanium.RAW_TITANIUM.get()));
        this.add(Titanium.DEEPSLATE_TITANIUM_ORE.get(),
                block -> createSingleOreDrops(Titanium.DEEPSLATE_TITANIUM_ORE.get(), Titanium.RAW_TITANIUM.get()));
        this.add(Palladium.STONE_PALLADIUM_ORE.get(),
                block -> createSingleOreDrops(Palladium.STONE_PALLADIUM_ORE.get(), Palladium.RAW_PALLADIUM.get()));
        this.add(Palladium.DEEPSLATE_PALLADIUM_ORE.get(),
                block -> createSingleOreDrops(Palladium.DEEPSLATE_PALLADIUM_ORE.get(), Palladium.RAW_PALLADIUM.get()));

    }

    protected LootTable.Builder createSingleOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        var list = new ArrayList<Block>();
        list.add(Titanium.STONE_TITANIUM_ORE.get());
        list.add(Titanium.DEEPSLATE_TITANIUM_ORE.get());
        list.add(Palladium.STONE_PALLADIUM_ORE.get());
        list.add(Palladium.DEEPSLATE_PALLADIUM_ORE.get());

        return list::iterator;
    }
}
