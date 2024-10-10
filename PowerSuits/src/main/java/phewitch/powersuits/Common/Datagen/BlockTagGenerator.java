package phewitch.powersuits.Common.Datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.Common.Items.Materials.Palladium;
import phewitch.powersuits.Common.Items.Materials.Titanium;
import phewitch.powersuits.PowerSuits;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider
{
    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PowerSuits.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Titanium.STONE_TITANIUM_ORE.get(),
                        Titanium.DEEPSLATE_TITANIUM_ORE.get(),
                        Palladium.STONE_PALLADIUM_ORE.get(),
                        Palladium.DEEPSLATE_PALLADIUM_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(Palladium.STONE_PALLADIUM_ORE.get(),
                        Palladium.DEEPSLATE_PALLADIUM_ORE.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(Titanium.STONE_TITANIUM_ORE.get(),
                        Titanium.DEEPSLATE_TITANIUM_ORE.get());

    }
}
