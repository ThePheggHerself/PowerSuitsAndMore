package phewitch.powersuits.Common.Items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.Common.Items.Armor.Suits;
import phewitch.powersuits.Common.Items.Materials.*;
import phewitch.powersuits.PowerSuits;

import java.util.function.Supplier;

public class ItemManager {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PowerSuits.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PowerSuits.MODID);

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        PowerSuits.LOGGER.info("Registering block: " + name);
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    public static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        PowerSuits.LOGGER.info("Registering item: " + name);
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        Suits.register(eventBus);
        Steel.register(eventBus);
        Titanium.register(eventBus);
        Palladium.register(eventBus);
        Circuits.register(eventBus);
        Misc.register(eventBus);


        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }
}
