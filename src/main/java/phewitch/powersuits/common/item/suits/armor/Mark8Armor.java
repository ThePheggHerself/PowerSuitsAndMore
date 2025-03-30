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

public class Mark8Armor extends SuitArmourBase {
    public Mark8Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK8, type, properties, new SuitFeatures(
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
                "Mark 8"
        ));
    }
}
