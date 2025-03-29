package phewitch.powersuits.common.item.suits.armor;

import net.minecraft.world.effect.MobEffects;
import phewitch.powersuits.common.item.suits.armorbase.enums.ArmorMaterials;
import phewitch.powersuits.common.item.suits.armorbase.SuitAbility;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourBase;
import phewitch.powersuits.common.item.suits.armorbase.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;

import java.util.ArrayList;
import java.util.List;

public class Mark5Armor extends SuitArmourBase {
    public Mark5Armor(ArmorMaterials materials, Type type, Properties properties, SuitFeatures features) {
        super(materials, type, properties, features);
    }
    public Mark5Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK5, type, properties, new SuitFeatures(
                250,
                1,
                0.0f,
                9f,
                0.25f,
                0.0f,
                new ArrayList<>(List.of(SuitAbility.SHOOT_ARROWS_ABILITY, SuitAbility.SHOOT_LASERS_ABILITY, SuitAbility.SENTRY_MODE_ABILITY, SuitAbility.SHOOT_CHEST_LASER_ABILITY)),
                new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT)),
                ChargeType.ON_GROUND,
                new ArrayList<>(),
                new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.WATER_BREATHING, MobEffects.MOVEMENT_SPEED)),
                "Mark 5"
        ));
    }
}
