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
import phewitch.powersuits.common.item.suits.armor.mark2.Mark2ArmorBoots;
import phewitch.powersuits.common.item.suits.armor.mark2.Mark2ArmorChest;
import phewitch.powersuits.common.item.suits.armor.mark2.Mark2ArmorHelmet;
import phewitch.powersuits.common.item.suits.armor.mark2.Mark2ArmorLegs;
import phewitch.powersuits.common.item.suits.armor.mark3.Mark3ArmorBoots;
import phewitch.powersuits.common.item.suits.armor.mark3.Mark3ArmorChest;
import phewitch.powersuits.common.item.suits.armor.mark3.Mark3ArmorHelmet;
import phewitch.powersuits.common.item.suits.armor.mark3.Mark3ArmorLegs;
import phewitch.powersuits.common.item.suits.armor.mark4.Mark4ArmorBoots;
import phewitch.powersuits.common.item.suits.armor.mark4.Mark4ArmorChest;
import phewitch.powersuits.common.item.suits.armor.mark4.Mark4ArmorHelmet;
import phewitch.powersuits.common.item.suits.armor.mark4.Mark4ArmorLegs;
import phewitch.powersuits.common.item.suits.armor.mark5.*;
import phewitch.powersuits.common.item.suits.armor.mark5.a.Mark5aArmorBoots;
import phewitch.powersuits.common.item.suits.armor.mark5.a.Mark5aArmorChest;
import phewitch.powersuits.common.item.suits.armor.mark5.a.Mark5aArmorHelmet;
import phewitch.powersuits.common.item.suits.armor.mark5.a.Mark5aArmorLegs;
import phewitch.powersuits.common.item.suits.armor.mark5.b.Mark5bArmorBoots;
import phewitch.powersuits.common.item.suits.armor.mark5.b.Mark5bArmorChest;
import phewitch.powersuits.common.item.suits.armor.mark5.b.Mark5bArmorHelmet;
import phewitch.powersuits.common.item.suits.armor.mark5.b.Mark5bArmorLegs;
import phewitch.powersuits.common.item.suits.armor.mark5.c.Mark5cArmorBoots;
import phewitch.powersuits.common.item.suits.armor.mark5.c.Mark5cArmorChest;
import phewitch.powersuits.common.item.suits.armor.mark5.c.Mark5cArmorHelmet;
import phewitch.powersuits.common.item.suits.armor.mark5.c.Mark5cArmorLegs;
import phewitch.powersuits.common.item.suits.armor.mark5.d.Mark5dArmorBoots;
import phewitch.powersuits.common.item.suits.armor.mark5.d.Mark5dArmorChest;
import phewitch.powersuits.common.item.suits.armor.mark5.d.Mark5dArmorHelmet;
import phewitch.powersuits.common.item.suits.armor.mark5.d.Mark5dArmorLegs;

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
            new Mark2ArmorBoots(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK2_LEGS = ItemsManager.ITEMS.register("mark2_leggings", () ->
            new Mark2ArmorLegs(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK2_CHEST = ItemsManager.ITEMS.register("mark2_chestplate", () ->
            new Mark2ArmorChest(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK2_HELM = ItemsManager.ITEMS.register("mark2_helmet", () ->
            new Mark2ArmorHelmet(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK3_BOOTS = ItemsManager.ITEMS.register("mark3_boots", () ->
            new Mark3ArmorBoots(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK3_LEGS = ItemsManager.ITEMS.register("mark3_leggings", () ->
            new Mark3ArmorLegs(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK3_CHEST = ItemsManager.ITEMS.register("mark3_chestplate", () ->
            new Mark3ArmorChest(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK3_HELM = ItemsManager.ITEMS.register("mark3_helmet", () ->
            new Mark3ArmorHelmet(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK4_BOOTS = ItemsManager.ITEMS.register("mark4_boots", () ->
            new Mark4ArmorBoots(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK4_LEGS = ItemsManager.ITEMS.register("mark4_leggings", () ->
            new Mark4ArmorLegs(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK4_CHEST = ItemsManager.ITEMS.register("mark4_chestplate", () ->
            new Mark4ArmorChest(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK4_HELM = ItemsManager.ITEMS.register("mark4_helmet", () ->
            new Mark4ArmorHelmet(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK5_BOOTS = ItemsManager.ITEMS.register("mark5_boots", () ->
            new Mark5ArmorBoots(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK5_LEGS = ItemsManager.ITEMS.register("mark5_leggings", () ->
            new Mark5ArmorLegs(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK5_CHEST = ItemsManager.ITEMS.register("mark5_chestplate", () ->
            new Mark5ArmorChest(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK5_HELM = ItemsManager.ITEMS.register("mark5_helmet", () ->
            new Mark5ArmorHelmet(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK5a_BOOTS = ItemsManager.ITEMS.register("mark5a_boots", () ->
            new Mark5aArmorBoots(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK5a_LEGS = ItemsManager.ITEMS.register("mark5a_leggings", () ->
            new Mark5aArmorLegs(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK5a_CHEST = ItemsManager.ITEMS.register("mark5a_chestplate", () ->
            new Mark5aArmorChest(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK5a_HELM = ItemsManager.ITEMS.register("mark5a_helmet", () ->
            new Mark5aArmorHelmet(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK5b_BOOTS = ItemsManager.ITEMS.register("mark5b_boots", () ->
            new Mark5bArmorBoots(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK5b_LEGS = ItemsManager.ITEMS.register("mark5b_leggings", () ->
            new Mark5bArmorLegs(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK5b_CHEST = ItemsManager.ITEMS.register("mark5b_chestplate", () ->
            new Mark5bArmorChest(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK5b_HELM = ItemsManager.ITEMS.register("mark5b_helmet", () ->
            new Mark5bArmorHelmet(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK5c_BOOTS = ItemsManager.ITEMS.register("mark5c_boots", () ->
            new Mark5cArmorBoots(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK5c_LEGS = ItemsManager.ITEMS.register("mark5c_leggings", () ->
            new Mark5cArmorLegs(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK5c_CHEST = ItemsManager.ITEMS.register("mark5c_chestplate", () ->
            new Mark5cArmorChest(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK5c_HELM = ItemsManager.ITEMS.register("mark5c_helmet", () ->
            new Mark5cArmorHelmet(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MK5d_BOOTS = ItemsManager.ITEMS.register("mark5d_boots", () ->
            new Mark5dArmorBoots(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK5d_LEGS = ItemsManager.ITEMS.register("mark5d_leggings", () ->
            new Mark5dArmorLegs(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK5d_CHEST = ItemsManager.ITEMS.register("mark5d_chestplate", () ->
            new Mark5dArmorChest(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK5d_HELM = ItemsManager.ITEMS.register("mark5d_helmet", () ->
            new Mark5dArmorHelmet(ArmorItem.Type.HELMET, new Item.Properties()));
}
