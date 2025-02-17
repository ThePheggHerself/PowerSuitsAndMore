package phewitch.powersuits.common.item.suits;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import phewitch.powersuits.common.item.ItemsManager;
import phewitch.powersuits.common.item.suits.armor.*;
import phewitch.powersuits.common.item.suits.armorbase.SuitTemplate;
import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;

import java.util.ArrayList;
import java.util.List;

public class Suits {
    public static void register(IEventBus eventBus){ }

    public static final RegistryObject<Item> MK1_BOOTS = ItemsManager.ITEMS.register("mark1_boots", () ->
            new Mark1Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK1_LEGS = ItemsManager.ITEMS.register("mark1_leggings", () ->
            new Mark1Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK1_CHEST = ItemsManager.ITEMS.register("mark1_chestplate", () ->
            new Mark1Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK1_HELM = ItemsManager.ITEMS.register("mark1_helmet", () ->
            new Mark1Armor(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final SuitTemplate MARK1_FEATURES =
            new SuitTemplate("Mark 1", 30, 0.2f, 0.7f, 4f, 0.1f, 1,
                    new ArrayList<>(),
                    new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT)),
                    ChargeType.ON_GROUND,
                    new ArrayList<>(),
                    new ArrayList<>());


    public static final RegistryObject<Item> MK2_BOOTS = ItemsManager.ITEMS.register("mark2_boots", () ->
            new Mark2Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK2_LEGS = ItemsManager.ITEMS.register("mark2_leggings", () ->
            new Mark2Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK2_CHEST = ItemsManager.ITEMS.register("mark2_chestplate", () ->
            new Mark2Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK2_HELM = ItemsManager.ITEMS.register("mark2_helmet", () ->
            new Mark2Armor(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final SuitTemplate MARK2_FEATURES =
            new SuitTemplate("Mark 2", 50, 0.6f, 0.6f, 9f, 0.1f, 0.8f,
                            new ArrayList<>(List.of(ActiveAbilities.SHOOT_ARROWS)),
                            new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT)),
                            ChargeType.ON_GROUND,
                            new ArrayList<>(),
                            new ArrayList<>(List.of(MobEffects.NIGHT_VISION)));


    public static final RegistryObject<Item> MK3_BOOTS = ItemsManager.ITEMS.register("mark3_boots", () ->
            new Mark3Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK3_LEGS = ItemsManager.ITEMS.register("mark3_leggings", () ->
            new Mark3Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK3_CHEST = ItemsManager.ITEMS.register("mark3_chestplate", () ->
            new Mark3Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK3_HELM = ItemsManager.ITEMS.register("mark3_helmet", () ->
            new Mark3Armor(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final SuitTemplate MARK3_FEATURES =
            new SuitTemplate("Mark 3", 100, 1, 0.0f, 9f, 0.1f, 0.0f,
                    new ArrayList<>(List.of(ActiveAbilities.SHOOT_ARROWS)),
                    new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT)),
                    ChargeType.ON_GROUND,
                    new ArrayList<>(),
                    new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.WATER_BREATHING)));


    public static final RegistryObject<Item> MK4_BOOTS = ItemsManager.ITEMS.register("mark4_boots", () ->
            new Mark4Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK4_LEGS = ItemsManager.ITEMS.register("mark4_leggings", () ->
            new Mark4Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK4_CHEST = ItemsManager.ITEMS.register("mark4_chestplate", () ->
            new Mark4Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK4_HELM = ItemsManager.ITEMS.register("mark4_helmet", () ->
            new Mark4Armor(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final SuitTemplate MARK4_FEATURES =
            new SuitTemplate("Mark 4", 150, 1, 0.0f, 9f, 0.1f, 0.0f,
                    new ArrayList<>(List.of(ActiveAbilities.SHOOT_ARROWS, ActiveAbilities.SHOOT_LASERS)),
                    new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT)),
                    ChargeType.ON_GROUND,
                    new ArrayList<>(),
                    new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.WATER_BREATHING)));


    public static final RegistryObject<Item> MK5_BOOTS = ItemsManager.ITEMS.register("mark5_boots", () ->
            new Mark5Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK5_LEGS = ItemsManager.ITEMS.register("mark5_leggings", () ->
            new Mark5Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK5_CHEST = ItemsManager.ITEMS.register("mark5_chestplate", () ->
            new Mark5Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK5_HELM = ItemsManager.ITEMS.register("mark5_helmet", () ->
            new Mark5Armor(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final SuitTemplate MARK5_FEATURES =
            new SuitTemplate("Mark 5", 250, 1, 0.0f, 9f, 0.25f, 0.0f,
                    new ArrayList<>(List.of(ActiveAbilities.SHOOT_ARROWS, ActiveAbilities.SHOOT_LASERS, ActiveAbilities.SENTRY_MODE, ActiveAbilities.SHOOT_CHEST_LASER)),
                    new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT)),
                    ChargeType.ON_GROUND,
                    new ArrayList<>(),
                    new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.WATER_BREATHING, MobEffects.MOVEMENT_SPEED)));


    public static final RegistryObject<Item> MK6_BOOTS = ItemsManager.ITEMS.register("mark6_boots", () ->
            new Mark6Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK6_LEGS = ItemsManager.ITEMS.register("mark6_leggings", () ->
            new Mark6Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK6_CHEST = ItemsManager.ITEMS.register("mark6_chestplate", () ->
            new Mark6Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK6_HELM = ItemsManager.ITEMS.register("mark6_helmet", () ->
            new Mark6Armor(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final SuitTemplate MARK6_FEATURES =
            new SuitTemplate("Mark 6", 180, 2, 0.0f, 9f, 0.25f, 0.0f,
                    new ArrayList<>(List.of(ActiveAbilities.SHOOT_FIRE_ARROWS, ActiveAbilities.SHOOT_FLAMETHROWER, ActiveAbilities.SENTRY_MODE)),
                    new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT)),
                    ChargeType.IN_FIRE_OR_LAVA,
                    new ArrayList<>(List.of(Weakness.WATER)),
                    new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.MOVEMENT_SPEED, MobEffects.FIRE_RESISTANCE)));


    public static final RegistryObject<Item> MK7_BOOTS = ItemsManager.ITEMS.register("mark7_boots", () ->
            new Mark7Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK7_LEGS = ItemsManager.ITEMS.register("mark7_leggings", () ->
            new Mark7Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK7_CHEST = ItemsManager.ITEMS.register("mark7_chestplate", () ->
            new Mark7Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK7_HELM = ItemsManager.ITEMS.register("mark7_helmet", () ->
            new Mark7Armor(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final SuitTemplate MARK7_FEATURES =
            new SuitTemplate("Mark 7", 180, 2, 0.0f, 9f, 0.25f, 0.0f,
                    new ArrayList<>(List.of(ActiveAbilities.SHOOT_ENDER_SHOT, ActiveAbilities.TELEPORT, ActiveAbilities.SENTRY_MODE)),
                    new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT, PassiveAbilities.BLOCK_ENDERMAN_LOOK)),
                    ChargeType.ON_GROUND,
                    new ArrayList<>(List.of(Weakness.WATER)),
                    new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.MOVEMENT_SPEED)));
    public static final RegistryObject<Item> MK8_BOOTS = ItemsManager.ITEMS.register("mark8_boots", () ->
            new Mark8Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK8_LEGS = ItemsManager.ITEMS.register("mark8_leggings", () ->
            new Mark8Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK8_CHEST = ItemsManager.ITEMS.register("mark8_chestplate", () ->
            new Mark8Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK8_HELM = ItemsManager.ITEMS.register("mark8_helmet", () ->
            new Mark8Armor(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final SuitTemplate MARK8_FEATURES =
            new SuitTemplate("Mark 8", 180, 2, 0.0f, 9f, 0.25f, 0.0f,
                    new ArrayList<>(List.of(ActiveAbilities.SHOOT_ARROWS, ActiveAbilities.SHOOT_WITHER_SKULLS, ActiveAbilities.SENTRY_MODE)),
                    new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT, PassiveAbilities.WITHER_RESISTANCE, PassiveAbilities.WITHER_RESISTANCE)),
                    ChargeType.LIFE_DRAIN,
                    new ArrayList<>(List.of(Weakness.FIRE)),
                    new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.MOVEMENT_SPEED, MobEffects.DIG_SPEED)));


    public static final RegistryObject<Item> MK9_BOOTS = ItemsManager.ITEMS.register("mark9_boots", () ->
            new Mark9Armor(ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MK9_LEGS = ItemsManager.ITEMS.register("mark9_leggings", () ->
            new Mark9Armor(ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MK9_CHEST = ItemsManager.ITEMS.register("mark9_chestplate", () ->
            new Mark9Armor(ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MK9_HELM = ItemsManager.ITEMS.register("mark9_helmet", () ->
            new Mark9Armor(ArmorItem.Type.HELMET, new Item.Properties()));
    public static final SuitTemplate MARK9_FEATURES =
            new SuitTemplate("Mark 9", 180, 2, 0.0f, 9f, 0.25f, 0.0f,
                    new ArrayList<>(List.of(ActiveAbilities.SONIC_BOOM, ActiveAbilities.WATER_DASH, ActiveAbilities.SENTRY_MODE)),
                    new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT, PassiveAbilities.WATER_CONDUIT, PassiveAbilities.WATER_SPEED)),
                    ChargeType.IN_WATER,
                    new ArrayList<>(List.of(Weakness.FIRE)),
                    new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.CONDUIT_POWER, MobEffects.DOLPHINS_GRACE)));
}
