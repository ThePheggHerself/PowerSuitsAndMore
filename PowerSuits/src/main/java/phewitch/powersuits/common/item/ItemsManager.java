package phewitch.powersuits.common.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.common.item.items.OSSRemote;
import phewitch.powersuits.common.item.suits.Suits;
import phewitch.powersuits.PowerSuits;

public class ItemsManager {
    public static void register(IEventBus eventBus){
        Suits.register(eventBus);

        BlocksManager.BLOCKS.register(eventBus);
        ItemsManager.ITEMS.register(eventBus);
        ArmorManager.ARMOR.register(eventBus);
        ToolsManager.TOOLS.register(eventBus);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PowerSuits.MODID);

    public static final RegistryObject<Item> RAW_PALLADIUM = ITEMS.register("raw_palladium", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> PALLADIUM_INGOT = ITEMS.register("palladium_ingot", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> PALLADIUM_POWER_CORE = ITEMS.register("palladium_power_core", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> ADVANCED_PALLADIUM_POWER_CORE = ITEMS.register("advanced_palladium_power_core", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_PLATE = ITEMS.register("titanium_plate", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> BASIC_CIRCUIT = ITEMS.register("basic_circuit", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADVANCED_CIRCUIT = ITEMS.register("advanced_circuit", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> MIXED_METAL_ALLOY = ITEMS.register("mixed_metal_alloy", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> MIXED_METAL_PLATE = ITEMS.register("mixed_metal_plate", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> ADVANCED_METAL_ALLOY = ITEMS.register("advanced_metal_alloy", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> ADVANCED_METAL_PLATE = ITEMS.register("advanced_metal_plate", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> FOCUSING_CRYSTAL = ITEMS.register("focusing_crystal", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> THRUSTER = ITEMS.register("thruster", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> PHOTONIC_BEAM_GENERATOR = ITEMS.register("photonic_beam_generator", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> MICRO_CANNON = ITEMS.register("micro_cannon", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> LASER_PROJECTILE = ITEMS.register("laser_projectile", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<OSSRemote> OSS_REMOTE = ITEMS.register("oss_remote", () ->
            new OSSRemote(new Item.Properties()));
}
