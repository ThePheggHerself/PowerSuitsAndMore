package phewitch.powersuits.Common.Items.Materials;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.Common.Items.Armor.ArmorMaterials;
import phewitch.powersuits.Common.Items.ItemManager;
import phewitch.powersuits.Common.Items.Tools.ToolTiers;
import phewitch.powersuits.PowerSuits;

public class Titanium {
    public static void register(IEventBus eventBus){

    }

    public static final RegistryObject<Block> STONE_TITANIUM_ORE = ItemManager.registerBlock("titanium_ore", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), PowerSuits.CreativeTab);
    public static final RegistryObject<Block> DEEPSLATE_TITANIUM_ORE = ItemManager.registerBlock("deepslate_titanium_ore", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), PowerSuits.CreativeTab);

    public static final RegistryObject<Item> RAW_TITANIUM = ItemManager.ITEMS.register("raw_titanium", () ->
            new Item(new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> TITANIUM_INGOT = ItemManager.ITEMS.register("titanium_ingot", () ->
            new Item(new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> TITANIUM_NUGGET = ItemManager.ITEMS.register("titanium_nugget", () ->
            new Item(new Item.Properties().tab(PowerSuits.CreativeTab)));

    public static final RegistryObject<Block> TITANIUM_BLOCK = ItemManager.registerBlock("titanium_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)), PowerSuits.CreativeTab);

    public static final RegistryObject<Item> TITANIUM_SWORD = ItemManager.ITEMS.register("titanium_sword", () ->
            new SwordItem(ToolTiers.Titanium, 2, 3, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> TITANIUM_SHOVEL = ItemManager.ITEMS.register("titanium_shovel", () ->
            new ShovelItem(ToolTiers.Titanium, 0, 0, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> TITANIUM_AXE = ItemManager.ITEMS.register("titanium_axe", () ->
            new AxeItem(ToolTiers.Titanium, 4, 0, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> TITANIUM_PICKAXE = ItemManager.ITEMS.register("titanium_pickaxe", () ->
            new PickaxeItem(ToolTiers.Titanium, 0, 0, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> TITANIUM_HOE = ItemManager.ITEMS.register("titanium_hoe", () ->
            new HoeItem(ToolTiers.Titanium, 0, 0, new Item.Properties().tab(PowerSuits.CreativeTab)));

    public static final RegistryObject<Item> TITANIUM_HELMET = ItemManager.ITEMS.register("titanium_helmet", () ->
            new ArmorItem(ArmorMaterials.TITANIUM, EquipmentSlot.HEAD, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> TITANIUM_CHESTPLATE = ItemManager.ITEMS.register("titanium_chestplate", () ->
            new ArmorItem(ArmorMaterials.TITANIUM, EquipmentSlot.CHEST, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> TITANIUM_LEGGINGS = ItemManager.ITEMS.register("titanium_leggings", () ->
            new ArmorItem(ArmorMaterials.TITANIUM, EquipmentSlot.LEGS, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> TITANIUM_BOOTS = ItemManager.ITEMS.register("titanium_boots", () ->
            new ArmorItem(ArmorMaterials.TITANIUM, EquipmentSlot.FEET, new Item.Properties().tab(PowerSuits.CreativeTab)));
}
