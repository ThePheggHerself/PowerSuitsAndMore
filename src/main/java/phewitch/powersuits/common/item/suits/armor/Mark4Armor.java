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

public class Mark4Armor extends SuitArmourBase {
    public Mark4Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK4, type, properties, new SuitFeatures(
                150,
                1,
                0.0f,
                9f,
                0.1f,
                0.0f,
                new ArrayList<>(List.of(SuitAbility.SHOOT_ARROWS_ABILITY, SuitAbility.SHOOT_LASERS_ABILITY)),
                new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT)),
                ChargeType.ON_GROUND,
                new ArrayList<>(),
                new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.WATER_BREATHING)),
                "Mark 4"
        ));
    }
}
