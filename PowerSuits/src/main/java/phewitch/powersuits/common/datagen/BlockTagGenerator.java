package phewitch.powersuits.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.common.items.BlocksManager;
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
                .add(BlocksManager.STONE_TITANIUM_ORE.get(),
                        BlocksManager.DEEPSLATE_TITANIUM_ORE.get(),
                        BlocksManager.STONE_PALLADIUM_ORE.get(),
                        BlocksManager.DEEPSLATE_PALLADIUM_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BlocksManager.STONE_PALLADIUM_ORE.get(),
                        BlocksManager.DEEPSLATE_PALLADIUM_ORE.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlocksManager.STONE_TITANIUM_ORE.get(),
                        BlocksManager.DEEPSLATE_TITANIUM_ORE.get());

    }
}
