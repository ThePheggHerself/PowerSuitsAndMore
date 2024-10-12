package phewitch.powersuits.common.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.PowerSuits;

import java.util.function.Supplier;

public class BlocksManager {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PowerSuits.MODID);

    public static final RegistryObject<Block> STONE_PALLADIUM_ORE = registerBlock("palladium_ore", () ->
            new Block(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_PALLADIUM_ORE = registerBlock("deepslate_palladium_ore", () ->
            new Block(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.STONE)));

    public static final RegistryObject<Block> STONE_TITANIUM_ORE = registerBlock("titanium_ore", () ->
            new Block(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_TITANIUM_ORE = registerBlock("deepslate_titanium_ore", () ->
            new Block(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.STONE)));

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        PowerSuits.LOGGER.info("Registering block: " + name);
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    public static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        PowerSuits.LOGGER.info("Registering item: " + name);
        return ItemsManager.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

}
