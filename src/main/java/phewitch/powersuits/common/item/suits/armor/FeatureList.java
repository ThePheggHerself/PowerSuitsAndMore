package phewitch.powersuits.common.item.suits.armor;

import net.minecraft.world.effect.MobEffects;
import phewitch.powersuits.common.item.suits.armorbase.SuitAbility;
import phewitch.powersuits.common.item.suits.armorbase.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;

import java.util.ArrayList;
import java.util.List;

public class FeatureList {

    public static SuitFeatures MARK1 = new SuitFeatures(
            200,
            1,
            0.7f,
            4f,
            0.1f,
            4,
            new ArrayList<>(),
            new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT)),
            ChargeType.ON_GROUND,
            new ArrayList<>(),
            new ArrayList<>(),
            "Mark 1");

    public static SuitFeatures MARK2 = new SuitFeatures(
            600,
            4,
            0.6f,
            9f,
            0.1f,
            1,
            new ArrayList<>(List.of(SuitAbility.SHOOT_ARROWS_ABILITY)),
            new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT)),
            ChargeType.ON_GROUND,
            new ArrayList<>(),
            new ArrayList<>(List.of(MobEffects.NIGHT_VISION)),
            "Mark 2"
    );

    public static SuitFeatures MARK3 = new SuitFeatures(
            1200,
            10,
            0.0f,
            9f,
            0.1f,
            0,
            new ArrayList<>(List.of(SuitAbility.SHOOT_ARROWS_ABILITY)),
            new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT)),
            ChargeType.ON_GROUND,
            new ArrayList<>(),
            new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.WATER_BREATHING)),
            "Mark 3"
    );

    public static SuitFeatures MARK4 = new SuitFeatures(
            2400,
            30,
            0.0f,
            9f,
            0.1f,
            0,
            new ArrayList<>(List.of(SuitAbility.SHOOT_ARROWS_ABILITY, SuitAbility.SHOOT_LASERS_ABILITY)),
            new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT)),
            ChargeType.ON_GROUND,
            new ArrayList<>(),
            new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.WATER_BREATHING)),
            "Mark 4"
    );

    public static SuitFeatures MARK5 = new SuitFeatures(
            4000,
            80,
            0.0f,
            9f,
            0.25f,
            0,
            new ArrayList<>(List.of(SuitAbility.SHOOT_ARROWS_ABILITY, SuitAbility.SHOOT_LASERS_ABILITY, SuitAbility.SENTRY_MODE_ABILITY, SuitAbility.SHOOT_CHEST_LASER_ABILITY)),
            new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT)),
            ChargeType.ON_GROUND,
            new ArrayList<>(),
            new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.WATER_BREATHING, MobEffects.MOVEMENT_SPEED)),
            "Mark 5"
    );

    public static SuitFeatures MARK5a = new SuitFeatures(
            4000,
            80,
            0.0f,
            9f,
            0.25f,
            0,
            new ArrayList<>(List.of(SuitAbility.SHOOT_FIRE_ARROWS_ABILITY, SuitAbility.SHOOT_FLAMETHROWER_ABILITY, SuitAbility.SENTRY_MODE_ABILITY)),
            new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT)),
            ChargeType.IN_FIRE_OR_LAVA,
            new ArrayList<>(List.of(Weakness.WATER)),
            new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.MOVEMENT_SPEED, MobEffects.FIRE_RESISTANCE)),
            "Mark 5a"
    );

    public static SuitFeatures MARK5b = new SuitFeatures(
            4000,
            80,
            0.0f,
            9f,
            0.25f,
            0,
            new ArrayList<>(List.of(SuitAbility.SHOOT_ENDER_SHOT_ABILITY, SuitAbility.TELEPORT_ABILITY, SuitAbility.SENTRY_MODE_ABILITY)),
            new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT, PassiveAbilities.BLOCK_ENDERMAN_LOOK)),
            ChargeType.ON_GROUND,
            new ArrayList<>(List.of(Weakness.WATER)),
            new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.MOVEMENT_SPEED)),
            "Mark 5b"
    );

    public static SuitFeatures MARK5c = new SuitFeatures(
            4000,
            80,
            0.0f,
            9f,
            0.25f,
            0,
            new ArrayList<>(List.of(SuitAbility.SHOOT_ARROWS_ABILITY, SuitAbility.SHOOT_WITHER_SKULLS_ABILITY, SuitAbility.SENTRY_MODE_ABILITY)),
            new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT, PassiveAbilities.WITHER_RESISTANCE, PassiveAbilities.WITHER_RESISTANCE)),
            ChargeType.LIFE_DRAIN,
            new ArrayList<>(List.of(Weakness.FIRE)),
            new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.MOVEMENT_SPEED, MobEffects.DIG_SPEED)),
            "Mark 5c"
    );

    public static SuitFeatures MARK5d = new SuitFeatures(
            4000,
            80,
            0.0f,
            9f,
            0.25f,
            0,
            new ArrayList<>(List.of(SuitAbility.SONIC_BOOM_ABILITY, SuitAbility.WATER_DASH_ABILITY, SuitAbility.SENTRY_MODE_ABILITY)),
            new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT, PassiveAbilities.WATER_CONDUIT, PassiveAbilities.WATER_SPEED)),
            ChargeType.IN_WATER,
            new ArrayList<>(List.of(Weakness.FIRE)),
            new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.CONDUIT_POWER, MobEffects.DOLPHINS_GRACE, MobEffects.WATER_BREATHING)),
            "Mark 5d"
    );
}
