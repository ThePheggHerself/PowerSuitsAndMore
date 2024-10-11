package phewitch.powersuits.Common.Items.Materials;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.Common.Items.ItemManager;

public class Steel {

    public static void register(IEventBus eventBus){

    }

    public static final RegistryObject<Item> STEEL_INGOT = ItemManager.ITEMS.register("steel_ingot", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_PLATE = ItemManager.ITEMS.register("steel_plate", () ->
            new Item(new Item.Properties()));
//    public static final RegistryObject<Block> STEEL_BLOCK = ItemManager.registerBlock("steel_block", () ->
//            new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)), PowerSuits.CreativeTab);
}
