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
import phewitch.powersuits.Common.Items.BlocksManager;
import phewitch.powersuits.Common.Items.ItemsManager;

import java.util.ArrayList;
import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {
    public BlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(BlocksManager.STONE_TITANIUM_ORE.get());
        this.dropSelf(BlocksManager.DEEPSLATE_TITANIUM_ORE.get());
        this.dropSelf(BlocksManager.STONE_PALLADIUM_ORE.get());
        this.dropSelf(BlocksManager.DEEPSLATE_PALLADIUM_ORE.get());

        this.add(BlocksManager.STONE_TITANIUM_ORE.get(),
                block -> createSingleOreDrops(BlocksManager.STONE_TITANIUM_ORE.get(), ItemsManager.RAW_TITANIUM.get()));
        this.add(BlocksManager.DEEPSLATE_TITANIUM_ORE.get(),
                block -> createSingleOreDrops(BlocksManager.DEEPSLATE_TITANIUM_ORE.get(), ItemsManager.RAW_TITANIUM.get()));
        this.add(BlocksManager.STONE_PALLADIUM_ORE.get(),
                block -> createSingleOreDrops(BlocksManager.STONE_PALLADIUM_ORE.get(), ItemsManager.RAW_PALLADIUM.get()));
        this.add(BlocksManager.DEEPSLATE_PALLADIUM_ORE.get(),
                block -> createSingleOreDrops(BlocksManager.DEEPSLATE_PALLADIUM_ORE.get(), ItemsManager.RAW_PALLADIUM.get()));

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
        list.add(BlocksManager.STONE_TITANIUM_ORE.get());
        list.add(BlocksManager.DEEPSLATE_TITANIUM_ORE.get());
        list.add(BlocksManager.STONE_PALLADIUM_ORE.get());
        list.add(BlocksManager.DEEPSLATE_PALLADIUM_ORE.get());

        return list::iterator;
    }
}
