package phewitch.powersuits.Common.Items.Materials;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.Common.Items.ItemManager;
import phewitch.powersuits.PowerSuits;

public class Misc {
    public static void register(IEventBus eventBus){

    }
    public static final RegistryObject<Item> MIXED_METAL_ALLOY = ItemManager.ITEMS.register("mixed_metal_alloy", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> MIXED_METAL_PLATE = ItemManager.ITEMS.register("mixed_metal_plate", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> ADVANCED_METAL_ALLOY = ItemManager.ITEMS.register("advanced_metal_alloy", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> ADVANCED_METAL_PLATE = ItemManager.ITEMS.register("advanced_metal_plate", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> FOCUSING_CRYSTAL = ItemManager.ITEMS.register("focusing_crystal", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> THRUSTER = ItemManager.ITEMS.register("thruster", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> PHOTONIC_BEAM_GENERATOR = ItemManager.ITEMS.register("photonic_beam_generator", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> MICRO_CANNON = ItemManager.ITEMS.register("micro_cannon", () ->
            new Item(new Item.Properties()));

}
