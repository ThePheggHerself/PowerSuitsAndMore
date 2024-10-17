package phewitch.powersuits.common.items.suits;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.common.items.ItemsManager;
import phewitch.powersuits.common.items.suits.ArmorBase.ArmorMaterials;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitFeatures;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitTemplate;

import java.util.ArrayList;
import java.util.List;

public class Suits {
    public static void register(IEventBus eventBus){ }

    public static final SuitTemplate MARK1_FEATURES =
            new SuitTemplate("Mark 1", 30, 0.2f, 0.7f, 4f, 0.1f, 1,
                    new ArrayList<>(List.of(
                            SuitFeatures.ABILITIES.LIMITED_FLIGHT)));
    public static final RegistryObject<Item> MK1_BOOTS = ItemsManager.ITEMS.register("mark1_boots", () ->
            new Mark1Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK1_LEGS = ItemsManager.ITEMS.register("mark1_leggings", () ->
            new Mark1Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK1_CHEST = ItemsManager.ITEMS.register("mark1_chestplate", () ->
            new Mark1Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK1_HELM = ItemsManager.ITEMS.register("mark1_helmet", () ->
            new Mark1Armor(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final SuitTemplate MARK2_FEATURES =
            new SuitTemplate("Mark 2", 50, 0.6f, 0.6f, 0.15f, 0.8f,
                    new ArrayList<>(
                            List.of(SuitFeatures.ABILITIES.LIMITED_FLIGHT,
                                    SuitFeatures.ABILITIES.SHOOT_ARROWS)));

    public static final RegistryObject<Item> MK2_BOOTS = ItemsManager.ITEMS.register("mark2_boots", () ->
            new Mark2Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK2_LEGS = ItemsManager.ITEMS.register("mark2_leggings", () ->
            new Mark2Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK2_CHEST = ItemsManager.ITEMS.register("mark2_chestplate", () ->
            new Mark2Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK2_HELM = ItemsManager.ITEMS.register("mark2_helmet", () ->
            new Mark2Armor(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final SuitTemplate MARK3_FEATURES =
            new SuitTemplate("Mark 3", 100, 1,
                    new ArrayList<>(
                            List.of(SuitFeatures.ABILITIES.LIMITED_FLIGHT,
                                    SuitFeatures.ABILITIES.SHOOT_ARROWS)),
                    new ArrayList<>(List.of(MobEffects.NIGHT_VISION)));
    public static final RegistryObject<Item> MK3_BOOTS = ItemsManager.ITEMS.register("mark3_boots", () ->
            new Mark3Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK3_LEGS = ItemsManager.ITEMS.register("mark3_leggings", () ->
            new Mark3Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK3_CHEST = ItemsManager.ITEMS.register("mark3_chestplate", () ->
            new Mark3Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK3_HELM = ItemsManager.ITEMS.register("mark3_helmet", () ->
            new Mark3Armor(ArmorItem.Type.HELMET, new Item.Properties()));


    public static final SuitTemplate MARK4_FEATURES =
            new SuitTemplate("Mark 4", 150, 1,
                    new ArrayList<>(
                            List.of(SuitFeatures.ABILITIES.LIMITED_FLIGHT,
                                    SuitFeatures.ABILITIES.FULL_FLIGHT,
                                    SuitFeatures.ABILITIES.SHOOT_ARROWS,
                                    SuitFeatures.ABILITIES.SHOOT_LASERS)),
                    new ArrayList<>(
                            List.of(MobEffects.NIGHT_VISION,
                                    MobEffects.WATER_BREATHING)));
    public static final RegistryObject<Item> MK4_BOOTS = ItemsManager.ITEMS.register("mark4_boots", () ->
            new Mark4Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK4_LEGS = ItemsManager.ITEMS.register("mark4_leggings", () ->
            new Mark4Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK4_CHEST = ItemsManager.ITEMS.register("mark4_chestplate", () ->
            new Mark4Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK4_HELM = ItemsManager.ITEMS.register("mark4_helmet", () ->
            new Mark4Armor(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final SuitTemplate MARK5_FEATURES =
            new SuitTemplate("Mark 5", 250, 5,
                    new ArrayList<>(
                            List.of(SuitFeatures.ABILITIES.LIMITED_FLIGHT,
                                    SuitFeatures.ABILITIES.FULL_FLIGHT,
                                    SuitFeatures.ABILITIES.SHOOT_ARROWS,
                                    SuitFeatures.ABILITIES.SHOOT_LASERS,
                                    SuitFeatures.ABILITIES.SHOOT_CHEST_LASER,
                                    SuitFeatures.ABILITIES.SENTRY_MODE)),
                    new ArrayList<>(
                            List.of(MobEffects.NIGHT_VISION,
                                    MobEffects.WATER_BREATHING,
                                    MobEffects.DAMAGE_BOOST,
                                    MobEffects.MOVEMENT_SPEED)));
    public static final RegistryObject<Item> MK5_BOOTS = ItemsManager.ITEMS.register("mark5_boots", () ->
            new Mark5Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK5_LEGS = ItemsManager.ITEMS.register("mark5_leggings", () ->
            new Mark5Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK5_CHEST = ItemsManager.ITEMS.register("mark5_chestplate", () ->
            new Mark5Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK5_HELM = ItemsManager.ITEMS.register("mark5_helmet", () ->
            new Mark5Armor(ArmorItem.Type.HELMET, new Item.Properties()));

    public static final SuitTemplate MARK6_FEATURES =
            new SuitTemplate("Mark 6", 200, 9,
                    new ArrayList<>(
                            List.of(SuitFeatures.ABILITIES.LIMITED_FLIGHT,
                                    SuitFeatures.ABILITIES.FULL_FLIGHT,
                                    SuitFeatures.ABILITIES.SHOOT_ARROWS,
                                    SuitFeatures.ABILITIES.SHOOT_FIRE_ARROWS,
                                    SuitFeatures.ABILITIES.SHOOT_FLAMETHROWER,
                                    SuitFeatures.ABILITIES.SENTRY_MODE)),
                    new ArrayList<>(
                            List.of(MobEffects.NIGHT_VISION,
                                    MobEffects.FIRE_RESISTANCE,
                                    MobEffects.DAMAGE_BOOST,
                                    MobEffects.MOVEMENT_SPEED)));

    public static final RegistryObject<Item> MK6_BOOTS = ItemsManager.ITEMS.register("mark6_boots", () ->
            new Mark6Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK6_LEGS = ItemsManager.ITEMS.register("mark6_leggings", () ->
            new Mark6Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK6_CHEST = ItemsManager.ITEMS.register("mark6_chestplate", () ->
            new Mark6Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK6_HELM = ItemsManager.ITEMS.register("mark6_helmet", () ->
            new Mark6Armor(ArmorItem.Type.HELMET, new Item.Properties()));
}
