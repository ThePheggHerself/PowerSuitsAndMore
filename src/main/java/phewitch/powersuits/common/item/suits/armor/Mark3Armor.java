package phewitch.powersuits.common.item.suits.armor;

import net.minecraft.world.effect.MobEffects;
import phewitch.powersuits.common.item.suits.armorbase.enums.ArmorMaterials;
import phewitch.powersuits.common.item.suits.armorbase.datatypes.SuitAbility;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourBase;
import phewitch.powersuits.common.item.suits.armorbase.datatypes.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;

import java.util.ArrayList;
import java.util.List;

public class Mark3Armor extends SuitArmourBase {
    public Mark3Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK3, type, properties, new SuitFeatures(
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
        ));
    }
}
