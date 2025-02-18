package phewitch.powersuits.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.item.ItemsManager;
import phewitch.powersuits.common.util.ModItem;
import phewitch.powersuits.common.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
    public ItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                            CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, PowerSuits.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        addCoreTag(new RegistryObject[]{
                ItemsManager.MIXED_METAL_ALLOY,
                ItemsManager.MIXED_METAL_PLATE,
                ItemsManager.ADVANCED_METAL_ALLOY,
                ItemsManager.ADVANCED_METAL_PLATE,
                ItemsManager.STEEL_INGOT,
                ItemsManager.STEEL_PLATE,
                ItemsManager.RAW_PALLADIUM,
                ItemsManager.PALLADIUM_INGOT,
                ItemsManager.RAW_TITANIUM,
                ItemsManager.TITANIUM_INGOT,
                ItemsManager.TITANIUM_PLATE
        });

        addToTag(new RegistryObject[]{
                        ItemsManager.PALLADIUM_POWER_CORE,
                        ItemsManager.ADVANCED_PALLADIUM_POWER_CORE,
                        ItemsManager.BASIC_CIRCUIT,
                        ItemsManager.ADVANCED_CIRCUIT,
                        ItemsManager.FOCUSING_CRYSTAL,
                        ItemsManager.THRUSTER,
                        ItemsManager.PHOTONIC_BEAM_GENERATOR,
                        ItemsManager.MICRO_CANNON
                }, ModTags.Items.COMP);


    }

    private void addCoreTag(ModItem item) {
        if(item.tag.toString().startsWith(PowerSuits.MODID))
            this.tag(item.tag).add(item);
        else {
            this.tag(commonTag(item.tag.location().toString(), item.registrySuffix)).add(item);
        }
    }

    private void addCoreTag(RegistryObject<ModItem>[] items) {
        for (var ro : items) {

            var item = ro.get();
            if (item.tag.toString().startsWith(PowerSuits.MODID))
                this.tag(item.tag).add(item);
            else {
                this.tag(commonTag(item.tag.location().toString(), item.registrySuffix)).add(item);
            }
        }
    }

    private void addToTag(RegistryObject<ModItem>[] items, TagKey<Item> tag) {
        for (var item : items){
            addCoreTag(item.get());

            this.tag(tag).add(item.get());
        }
    }

    private void addToExtraTag(RegistryObject<ModItem>[] items, TagKey<Item> tag) {
        for (var item : items){
            addCoreTag(item.get());

            this.tag(tag).add(item.get());
        }
    }

    private void addToTag(RegistryObject<ModItem> item, TagKey<Item> tag) {
        this.tag(tag).add(item.get());
    }

    public static TagKey<Item> commonTag(String name, String location) {
        return ItemTags.create(new ResourceLocation(name + "/" + location));
    }
}
