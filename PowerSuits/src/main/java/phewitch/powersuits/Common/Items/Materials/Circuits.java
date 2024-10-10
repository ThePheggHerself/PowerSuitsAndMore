package phewitch.powersuits.Common.Items.Materials;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.Common.Items.ItemManager;
import phewitch.powersuits.PowerSuits;

public class Circuits {
    public static void register(IEventBus eventBus){

    }
    public static final RegistryObject<Item> BASIC_CIRCUIT = ItemManager.ITEMS.register("basic_circuit", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADVANCED_CIRCUIT = ItemManager.ITEMS.register("advanced_circuit", () ->
            new Item(new Item.Properties()));
}
