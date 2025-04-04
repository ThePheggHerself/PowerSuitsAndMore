package phewitch.powersuits.common.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.common.item.items.OSSRemote;
import phewitch.powersuits.common.item.suits.Suits;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.util.ModTags;
import phewitch.powersuits.common.util.ModItem;

public class ItemsManager {
    public static void register(IEventBus eventBus){
        Suits.register(eventBus);

        BlocksManager.BLOCKS.register(eventBus);
        BlockEntityManager.BLOCK_ENTITIES.register(eventBus);
        ItemsManager.ITEMS.register(eventBus);
        ArmorManager.ARMOR.register(eventBus);
        ToolsManager.TOOLS.register(eventBus);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PowerSuits.MODID);

    public static final RegistryObject<ModItem> RAW_PALLADIUM = ITEMS.register("raw_palladium", () ->
            new ModItem("palladium", ModTags.Items.ORE, new Item.Properties()));
    public static final RegistryObject<ModItem> PALLADIUM_INGOT = ITEMS.register("palladium_ingot", () ->
            new ModItem("palladium", ModTags.Items.INGOT, new Item.Properties()));

    public static final RegistryObject<ModItem> PALLADIUM_POWER_CORE = ITEMS.register("palladium_power_core", () ->
            new ModItem("palladium_power_core", ModTags.Items.COMP_POWER, new Item.Properties()));

    public static final RegistryObject<ModItem> ADVANCED_PALLADIUM_POWER_CORE = ITEMS.register("advanced_palladium_power_core", () ->
            new ModItem("advanced_palladium_power_core", ModTags.Items.COMP_POWER, new Item.Properties()));

    public static final RegistryObject<ModItem> RAW_TITANIUM = ITEMS.register("raw_titanium", () ->
            new ModItem("titanium", ModTags.Items.ORE, new Item.Properties()));
    public static final RegistryObject<ModItem> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () ->
            new ModItem("titanium", ModTags.Items.INGOT, new Item.Properties()));
    public static final RegistryObject<ModItem> TITANIUM_PLATE = ITEMS.register("titanium_plate", () ->
            new ModItem("titanium", ModTags.Items.PLATES, new Item.Properties()));

    public static final RegistryObject<ModItem> BASIC_CIRCUIT = ITEMS.register("basic_circuit", () ->
            new ModItem("basic_circuit", ModTags.Items.COMP, new Item.Properties()));
    public static final RegistryObject<ModItem> ADVANCED_CIRCUIT = ITEMS.register("advanced_circuit", () ->
            new ModItem("advanced_circuit", ModTags.Items.COMP, new Item.Properties()));

    public static final RegistryObject<ModItem> MIXED_METAL_ALLOY = ITEMS.register("mixed_metal_alloy", () ->
            new ModItem("mixed_metal", ModTags.Items.INGOT, new Item.Properties()));
    public static final RegistryObject<ModItem> MIXED_METAL_PLATE = ITEMS.register("mixed_metal_plate", () ->
            new ModItem("mixed_metal", ModTags.Items.PLATES, new Item.Properties()));

    public static final RegistryObject<ModItem> ADVANCED_METAL_ALLOY = ITEMS.register("advanced_metal_alloy", () ->
            new ModItem("advanced_metal", ModTags.Items.INGOT, new Item.Properties()));
    public static final RegistryObject<ModItem> ADVANCED_METAL_PLATE = ITEMS.register("advanced_metal_plate", () ->
            new ModItem("advanced_metal", ModTags.Items.PLATES, new Item.Properties()));

    public static final RegistryObject<ModItem> FOCUSING_CRYSTAL = ITEMS.register("focusing_crystal", () ->
            new ModItem("focus_crystal", ModTags.Items.COMP, new Item.Properties()));
    public static final RegistryObject<ModItem> THRUSTER = ITEMS.register("thruster", () ->
            new ModItem("thruster", ModTags.Items.COMP_FLIGHT, new Item.Properties()));

    public static final RegistryObject<ModItem> PHOTONIC_BEAM_GENERATOR = ITEMS.register("photonic_beam_generator", () ->
            new ModItem("beam_generator", ModTags.Items.COMP, new Item.Properties()));

    public static final RegistryObject<ModItem> MICRO_CANNON = ITEMS.register("micro_cannon", () ->
            new ModItem("micro_cannon", ModTags.Items.COMP_WEAPONS, new Item.Properties()));

//    public static final RegistryObject<Item> LASER_PROJECTILE = ITEMS.register("laser_projectile", () ->
//            new Item(new Item.Properties()));

    public static final RegistryObject<ModItem> STEEL_INGOT = ITEMS.register("steel_ingot", () ->
            new ModItem("steel", ModTags.Items.INGOT, new Item.Properties()));
    public static final RegistryObject<ModItem> STEEL_PLATE = ITEMS.register("steel_plate", () ->
            new ModItem("steel", ModTags.Items.PLATES, new Item.Properties()));

    public static final RegistryObject<ModItem> BASE_HELMET = ITEMS.register("base_helmet", () ->
            new ModItem("helmet", ModTags.Items.COMP_BASE, new Item.Properties()));
    public static final RegistryObject<ModItem> BASE_CHESTPLATE = ITEMS.register("base_chestplate", () ->
            new ModItem("chestplate", ModTags.Items.COMP_BASE, new Item.Properties()));
    public static final RegistryObject<ModItem> BASE_LEGGINGS = ITEMS.register("base_leggings", () ->
            new ModItem("leggings", ModTags.Items.COMP_BASE, new Item.Properties()));
    public static final RegistryObject<ModItem> BASE_BOOTS = ITEMS.register("base_boots", () ->
            new ModItem("boots", ModTags.Items.COMP_BASE, new Item.Properties()));

    public static final RegistryObject<OSSRemote> OSS_REMOTE = ITEMS.register("oss_remote", () ->
            new OSSRemote(new Item.Properties()));
}
