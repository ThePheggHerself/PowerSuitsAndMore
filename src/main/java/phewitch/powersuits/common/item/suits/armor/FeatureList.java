package phewitch.powersuits.common.item.suits.armor;

import net.minecraft.world.effect.MobEffects;
import phewitch.powersuits.common.item.suits.armorbase.datatypes.SuitAbility;
import phewitch.powersuits.common.item.suits.armorbase.datatypes.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;

import java.util.ArrayList;
import java.util.List;

public class FeatureList {

    public static SuitFeatures MARK1_SUIT_FEATURES = new SuitFeatures(
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

    public static SuitFeatures MARK5_SUIT_FEATURES = new SuitFeatures(
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
            "Mark 5");
}
