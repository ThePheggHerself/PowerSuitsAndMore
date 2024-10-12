package phewitch.powersuits.common.items.suits;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.common.items.ItemsManager;
import phewitch.powersuits.common.items.suits.ArmorBase.ArmorMaterials;

public class Suits {
    public static void register(IEventBus eventBus){

    }

    public static final RegistryObject<Item> MK1_BOOTS = ItemsManager.ITEMS.register("mark1_boots", () ->
            new Mark1Armor(ArmorMaterials.MARK1, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK1_LEGS = ItemsManager.ITEMS.register("mark1_leggings", () ->
            new Mark1Armor(ArmorMaterials.MARK1, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK1_CHEST = ItemsManager.ITEMS.register("mark1_chestplate", () ->
            new Mark1Armor(ArmorMaterials.MARK1, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK1_HELM = ItemsManager.ITEMS.register("mark1_helmet", () ->
            new Mark1Armor(ArmorMaterials.MARK1, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK2_BOOTS = ItemsManager.ITEMS.register("mark2_boots", () ->
            new Mark2Armor(ArmorMaterials.MARK2, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK2_LEGS = ItemsManager.ITEMS.register("mark2_leggings", () ->
            new Mark2Armor(ArmorMaterials.MARK2, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK2_CHEST = ItemsManager.ITEMS.register("mark2_chestplate", () ->
            new Mark2Armor(ArmorMaterials.MARK2, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK2_HELM = ItemsManager.ITEMS.register("mark2_helmet", () ->
            new Mark2Armor(ArmorMaterials.MARK2, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK3_BOOTS = ItemsManager.ITEMS.register("mark3_boots", () ->
            new Mark3Armor(ArmorMaterials.MARK3, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK3_LEGS = ItemsManager.ITEMS.register("mark3_leggings", () ->
            new Mark3Armor(ArmorMaterials.MARK3, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK3_CHEST = ItemsManager.ITEMS.register("mark3_chestplate", () ->
            new Mark3Armor(ArmorMaterials.MARK3, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK3_HELM = ItemsManager.ITEMS.register("mark3_helmet", () ->
            new Mark3Armor(ArmorMaterials.MARK3, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK4_BOOTS = ItemsManager.ITEMS.register("mark4_boots", () ->
            new Mark4Armor(ArmorMaterials.MARK4, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK4_LEGS = ItemsManager.ITEMS.register("mark4_leggings", () ->
            new Mark4Armor(ArmorMaterials.MARK4, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK4_CHEST = ItemsManager.ITEMS.register("mark4_chestplate", () ->
            new Mark4Armor(ArmorMaterials.MARK4, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK4_HELM = ItemsManager.ITEMS.register("mark4_helmet", () ->
            new Mark4Armor(ArmorMaterials.MARK4, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK5_BOOTS = ItemsManager.ITEMS.register("mark5_boots", () ->
            new Mark5Armor(ArmorMaterials.MARK5, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK5_LEGS = ItemsManager.ITEMS.register("mark5_leggings", () ->
            new Mark5Armor(ArmorMaterials.MARK5, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK5_CHEST = ItemsManager.ITEMS.register("mark5_chestplate", () ->
            new Mark5Armor(ArmorMaterials.MARK5, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK5_HELM = ItemsManager.ITEMS.register("mark5_helmet", () ->
            new Mark5Armor(ArmorMaterials.MARK5, ArmorItem.Type.HELMET, new Item.Properties()));
}
