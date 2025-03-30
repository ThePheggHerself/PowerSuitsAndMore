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

public class Mark2Armor extends SuitArmourBase {
    public Mark2Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK2, type, properties, new SuitFeatures(
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
        ));
    }
}
