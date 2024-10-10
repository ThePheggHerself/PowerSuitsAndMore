package phewitch.powersuits.Common.Items.Materials;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.Common.Items.ItemManager;
import phewitch.powersuits.PowerSuits;

public class Palladium {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PowerSuits.MODID);

    public static void register(IEventBus eventBus){

    }
    public static final RegistryObject<Block> STONE_PALLADIUM_ORE = ItemManager.registerBlock("palladium_ore", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_PALLADIUM_ORE = ItemManager.registerBlock("deepslate_palladium_ore", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Item> RAW_PALLADIUM = ItemManager.ITEMS.register("raw_palladium", () ->
            new Item(new Item.Properties()));
    public static final RegistryObject<Item> PALLADIUM_INGOT = ItemManager.ITEMS.register("palladium_ingot", () ->
            new Item(new Item.Properties()));

    public static final RegistryObject<Item> PALLADIUM_POWER_CORE = ItemManager.ITEMS.register("palladium_power_core", () ->
            new Item(new Item.Properties()));

//    public static final RegistryObject<Item> ADVANCED_PALLADIUM_POWER_CORE = ItemManager.ITEMS.register("advanced_palladium_power_core", () ->
//            new Item(new Item.Properties()));
}
