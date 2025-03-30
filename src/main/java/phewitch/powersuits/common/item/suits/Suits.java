package phewitch.powersuits.common.item.suits;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.common.item.ItemsManager;
import phewitch.powersuits.common.item.suits.armor.*;
import phewitch.powersuits.common.item.suits.armor.mark1.Mark1ArmorBoots;
import phewitch.powersuits.common.item.suits.armor.mark1.Mark1ArmorChest;
import phewitch.powersuits.common.item.suits.armor.mark1.Mark1ArmorHelmet;
import phewitch.powersuits.common.item.suits.armor.mark1.Mark1ArmorLegs;
import phewitch.powersuits.common.item.suits.armor.mark5.*;

public class Suits {
    public static void register(IEventBus eventBus){ }

    public static final RegistryObject<Item> MK1_BOOTS = ItemsManager.ITEMS.register("mark1_boots", () ->
            new Mark1ArmorBoots(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK1_LEGS = ItemsManager.ITEMS.register("mark1_leggings", () ->
            new Mark1ArmorLegs(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK1_CHEST = ItemsManager.ITEMS.register("mark1_chestplate", () ->
            new Mark1ArmorChest(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK1_HELM = ItemsManager.ITEMS.register("mark1_helmet", () ->
            new Mark1ArmorHelmet(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK2_BOOTS = ItemsManager.ITEMS.register("mark2_boots", () ->
            new Mark2Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK2_LEGS = ItemsManager.ITEMS.register("mark2_leggings", () ->
            new Mark2Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK2_CHEST = ItemsManager.ITEMS.register("mark2_chestplate", () ->
            new Mark2Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK2_HELM = ItemsManager.ITEMS.register("mark2_helmet", () ->
            new Mark2Armor(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK3_BOOTS = ItemsManager.ITEMS.register("mark3_boots", () ->
            new Mark3Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK3_LEGS = ItemsManager.ITEMS.register("mark3_leggings", () ->
            new Mark3Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK3_CHEST = ItemsManager.ITEMS.register("mark3_chestplate", () ->
            new Mark3Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK3_HELM = ItemsManager.ITEMS.register("mark3_helmet", () ->
            new Mark3Armor(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK4_BOOTS = ItemsManager.ITEMS.register("mark4_boots", () ->
            new Mark4Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK4_LEGS = ItemsManager.ITEMS.register("mark4_leggings", () ->
            new Mark4Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK4_CHEST = ItemsManager.ITEMS.register("mark4_chestplate", () ->
            new Mark4Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK4_HELM = ItemsManager.ITEMS.register("mark4_helmet", () ->
            new Mark4Armor(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK5_BOOTS = ItemsManager.ITEMS.register("mark5_boots", () ->
            new Mark5ArmorBoots(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK5_LEGS = ItemsManager.ITEMS.register("mark5_leggings", () ->
            new Mark5ArmorLegs(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK5_CHEST = ItemsManager.ITEMS.register("mark5_chestplate", () ->
            new Mark5ArmorChest(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK5_HELM = ItemsManager.ITEMS.register("mark5_helmet", () ->
            new Mark5ArmorHelmet(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK6_BOOTS = ItemsManager.ITEMS.register("mark6_boots", () ->
            new Mark6Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK6_LEGS = ItemsManager.ITEMS.register("mark6_leggings", () ->
            new Mark6Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK6_CHEST = ItemsManager.ITEMS.register("mark6_chestplate", () ->
            new Mark6Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK6_HELM = ItemsManager.ITEMS.register("mark6_helmet", () ->
            new Mark6Armor(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> MK7_BOOTS = ItemsManager.ITEMS.register("mark7_boots", () ->
            new Mark7Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK7_LEGS = ItemsManager.ITEMS.register("mark7_leggings", () ->
            new Mark7Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK7_CHEST = ItemsManager.ITEMS.register("mark7_chestplate", () ->
            new Mark7Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK7_HELM = ItemsManager.ITEMS.register("mark7_helmet", () ->
            new Mark7Armor(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> MK8_BOOTS = ItemsManager.ITEMS.register("mark8_boots", () ->
            new Mark8Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK8_LEGS = ItemsManager.ITEMS.register("mark8_leggings", () ->
            new Mark8Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK8_CHEST = ItemsManager.ITEMS.register("mark8_chestplate", () ->
            new Mark8Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK8_HELM = ItemsManager.ITEMS.register("mark8_helmet", () ->
            new Mark8Armor(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK9_BOOTS = ItemsManager.ITEMS.register("mark9_boots", () ->
            new Mark9Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK9_LEGS = ItemsManager.ITEMS.register("mark9_leggings", () ->
            new Mark9Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK9_CHEST = ItemsManager.ITEMS.register("mark9_chestplate", () ->
            new Mark9Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK9_HELM = ItemsManager.ITEMS.register("mark9_helmet", () ->
            new Mark9Armor(ArmorItem.Type.HELMET, new Item.Properties()));
}
