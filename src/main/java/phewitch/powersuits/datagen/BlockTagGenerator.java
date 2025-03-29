package phewitch.powersuits.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.common.item.BlocksManager;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.util.ModBlock;
import phewitch.powersuits.common.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider
{
    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PowerSuits.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addToTag(
                new ModBlock[] { BlocksManager.STONE_PALLADIUM_ORE.get(), BlocksManager.DEEPSLATE_PALLADIUM_ORE.get()},
                new TagKey[] { BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL });

        addToTag(new ModBlock[] { BlocksManager.STONE_TITANIUM_ORE.get(), BlocksManager.DEEPSLATE_TITANIUM_ORE.get()},
                new TagKey[] { BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_DIAMOND_TOOL });
    }

    private void addCoreTag(ModBlock item) {
        if(item.tag.toString().startsWith(PowerSuits.MODID))
            this.tag(item.tag).add(item);
        else {
            this.tag(commonTag(item.tag.location(), item.registrySuffix)).add(item);
        }
    }

    private void addToTag(ModBlock[] blocks, TagKey<Block> tag) {
        for (var block : blocks){
            addCoreTag(block);

            this.tag(tag).add(block);
        }
    }

    private void addToTag(ModBlock[] blocks, TagKey<Block>[] tags) {
        for (var block : blocks){
            addCoreTag(block);

            for (var tag : tags) {
                this.tag(tag).add(block);
            }
        }
    }

    public static TagKey<Block> commonTag(ResourceLocation name, String location) {
        return BlockTags.create(new ResourceLocation(name + "/" + location));
    }
}
