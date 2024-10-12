package phewitch.powersuits.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.common.items.ArmorManager;
import phewitch.powersuits.common.items.ItemsManager;
import phewitch.powersuits.common.items.suits.Suits;
import phewitch.powersuits.common.items.ToolsManager;
import phewitch.powersuits.PowerSuits;

import java.util.LinkedHashMap;

public class ItemModelGenerator extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PowerSuits.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ItemsManager.RAW_TITANIUM);
        simpleItem(ItemsManager.TITANIUM_INGOT);
        simpleItem(ItemsManager.TITANIUM_PLATE);

        simpleItem(ItemsManager.RAW_PALLADIUM);
        simpleItem(ItemsManager.PALLADIUM_INGOT);
        simpleItem(ItemsManager.PALLADIUM_POWER_CORE);
        simpleItem(ItemsManager.ADVANCED_PALLADIUM_POWER_CORE);

        simpleItem(ItemsManager.STEEL_INGOT);
        simpleItem(ItemsManager.STEEL_PLATE);

        simpleItem(ItemsManager.BASIC_CIRCUIT);
        simpleItem(ItemsManager.ADVANCED_CIRCUIT);
        simpleItem(ItemsManager.LASER_PROJECTILE);

        simpleItem(ItemsManager.MIXED_METAL_ALLOY);
        simpleItem(ItemsManager.MIXED_METAL_PLATE);
        simpleItem(ItemsManager.ADVANCED_METAL_ALLOY);
        simpleItem(ItemsManager.ADVANCED_METAL_PLATE);
        simpleItem(ItemsManager.THRUSTER);
        simpleItem(ItemsManager.FOCUSING_CRYSTAL);
        simpleItem(ItemsManager.PHOTONIC_BEAM_GENERATOR);
        simpleItem(ItemsManager.MICRO_CANNON);

        handheldItem(ToolsManager.TITANIUM_SWORD);
        handheldItem(ToolsManager.TITANIUM_PICKAXE);
        handheldItem(ToolsManager.TITANIUM_AXE);
        handheldItem(ToolsManager.TITANIUM_SHOVEL);
        handheldItem(ToolsManager.TITANIUM_HOE);

        trimmedArmorItem(ArmorManager.TITANIUM_HELMET);
        trimmedArmorItem(ArmorManager.TITANIUM_CHESTPLATE);
        trimmedArmorItem(ArmorManager.TITANIUM_LEGGINGS);
        trimmedArmorItem(ArmorManager.TITANIUM_BOOTS);

        handheldItem(Suits.MK1_BOOTS);
        handheldItem(Suits.MK1_LEGS);
        handheldItem(Suits.MK1_CHEST);
        handheldItem(Suits.MK1_HELM);

        handheldItem(Suits.MK2_BOOTS);
        handheldItem(Suits.MK2_LEGS);
        handheldItem(Suits.MK2_CHEST);
        handheldItem(Suits.MK2_HELM);

        handheldItem(Suits.MK3_BOOTS);
        handheldItem(Suits.MK3_LEGS);
        handheldItem(Suits.MK3_CHEST);
        handheldItem(Suits.MK3_HELM);

        handheldItem(Suits.MK4_BOOTS);
        handheldItem(Suits.MK4_LEGS);
        handheldItem(Suits.MK4_CHEST);
        handheldItem(Suits.MK4_HELM);

        handheldItem(Suits.MK5_BOOTS);
        handheldItem(Suits.MK5_LEGS);
        handheldItem(Suits.MK5_CHEST);
        handheldItem(Suits.MK5_HELM);
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = PowerSuits.MODID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PowerSuits.MODID,"item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(PowerSuits.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(PowerSuits.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(PowerSuits.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(PowerSuits.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(PowerSuits.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PowerSuits.MODID,"item/" + item.getId().getPath()));
    }
}
