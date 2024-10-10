package phewitch.powersuits.Common.Datagen.Loot;

import com.sun.jna.platform.win32.COM.IStream;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.Common.Items.Materials.Palladium;
import phewitch.powersuits.Common.Items.Materials.Titanium;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

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
                block -> createCopperLikeOreDrops(Titanium.STONE_TITANIUM_ORE.get(), Titanium.RAW_TITANIUM.get()));
        this.add(Titanium.DEEPSLATE_TITANIUM_ORE.get(),
                block -> createCopperLikeOreDrops(Titanium.DEEPSLATE_TITANIUM_ORE.get(), Titanium.RAW_TITANIUM.get()));
        this.add(Palladium.STONE_PALLADIUM_ORE.get(),
                block -> createCopperLikeOreDrops(Palladium.STONE_PALLADIUM_ORE.get(), Palladium.RAW_PALLADIUM.get()));
        this.add(Palladium.DEEPSLATE_PALLADIUM_ORE.get(),
                block -> createCopperLikeOreDrops(Palladium.DEEPSLATE_PALLADIUM_ORE.get(), Palladium.RAW_PALLADIUM.get()));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
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
