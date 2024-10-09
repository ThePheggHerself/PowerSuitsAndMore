package phewitch.powersuits.Common.Items.Armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Armor;
import phewitch.powersuits.Common.Items.Armor.Mk2.Mark2Armor;
import phewitch.powersuits.Common.Items.Armor.MK3.Mark3Armor;
import phewitch.powersuits.Common.Items.Armor.Mk4.Mark4Armor;
import phewitch.powersuits.Common.Items.ItemManager;
import phewitch.powersuits.PowerSuits;

public class Suits {
    public static void register(IEventBus eventBus){

    }

    public static final RegistryObject<Item> MK1_BOOTS = ItemManager.ITEMS.register("mk1_boots", () ->
            new Mark1Armor(ArmorMaterials.MARK1, EquipmentSlot.FEET, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK1_LEGS = ItemManager.ITEMS.register("mk1_leggings", () ->
            new Mark1Armor(ArmorMaterials.MARK1, EquipmentSlot.LEGS, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK1_CHEST = ItemManager.ITEMS.register("mk1_chestplate", () ->
            new Mark1Armor(ArmorMaterials.MARK1, EquipmentSlot.CHEST, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK1_HELM = ItemManager.ITEMS.register("mk1_helmet", () ->
            new Mark1Armor(ArmorMaterials.MARK1, EquipmentSlot.HEAD, new Item.Properties().tab(PowerSuits.CreativeTab)));

    public static final RegistryObject<Item> MK2_BOOTS = ItemManager.ITEMS.register("mk2_boots", () ->
            new Mark2Armor(ArmorMaterials.MARK2, EquipmentSlot.FEET, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK2_LEGS = ItemManager.ITEMS.register("mk2_leggings", () ->
            new Mark2Armor(ArmorMaterials.MARK2, EquipmentSlot.LEGS, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK2_CHEST = ItemManager.ITEMS.register("mk2_chestplate", () ->
            new Mark2Armor(ArmorMaterials.MARK2, EquipmentSlot.CHEST, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK2_HELM = ItemManager.ITEMS.register("mk2_helmet", () ->
            new Mark2Armor(ArmorMaterials.MARK2, EquipmentSlot.HEAD, new Item.Properties().tab(PowerSuits.CreativeTab)));

    public static final RegistryObject<Item> MK3_BOOTS = ItemManager.ITEMS.register("mk3_boots", () ->
            new Mark3Armor(ArmorMaterials.MARK3, EquipmentSlot.FEET, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK3_LEGS = ItemManager.ITEMS.register("mk3_leggings", () ->
            new Mark3Armor(ArmorMaterials.MARK3, EquipmentSlot.LEGS, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK3_CHEST = ItemManager.ITEMS.register("mk3_chestplate", () ->
            new Mark3Armor(ArmorMaterials.MARK3, EquipmentSlot.CHEST, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK3_HELM = ItemManager.ITEMS.register("mk3_helmet", () ->
            new Mark3Armor(ArmorMaterials.MARK3, EquipmentSlot.HEAD, new Item.Properties().tab(PowerSuits.CreativeTab)));

    public static final RegistryObject<Item> MK4_BOOTS = ItemManager.ITEMS.register("mk4_boots", () ->
            new Mark4Armor(ArmorMaterials.MARK4, EquipmentSlot.FEET, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK4_LEGS = ItemManager.ITEMS.register("mk4_leggings", () ->
            new Mark4Armor(ArmorMaterials.MARK4, EquipmentSlot.LEGS, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK4_CHEST = ItemManager.ITEMS.register("mk4_chestplate", () ->
            new Mark4Armor(ArmorMaterials.MARK4, EquipmentSlot.CHEST, new Item.Properties().tab(PowerSuits.CreativeTab)));
    public static final RegistryObject<Item> MK4_HELM = ItemManager.ITEMS.register("mk4_helmet", () ->
            new Mark4Armor(ArmorMaterials.MARK4, EquipmentSlot.HEAD, new Item.Properties().tab(PowerSuits.CreativeTab)));
}
