package phewitch.powersuits.common.item.suits.armor;

import net.minecraft.world.effect.MobEffects;
import phewitch.powersuits.common.item.suits.armorbase.enums.ArmorMaterials;
import phewitch.powersuits.common.item.suits.armorbase.datatypes.SuitAbility;
import phewitch.powersuits.common.item.suits.armorbase.datatypes.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourBase;

import java.util.ArrayList;
import java.util.List;

public class Mark9Armor extends SuitArmourBase {
    public Mark9Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK9, type, properties, new SuitFeatures(
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
                "Mark 9"
        ));
    }
}
