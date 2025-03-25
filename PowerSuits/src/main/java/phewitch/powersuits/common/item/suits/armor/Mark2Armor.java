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

public class Mark2Armor extends SuitArmourBase {
    public Mark2Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK2, type, properties, new SuitFeatures(
                50,
                0.6f,
                0.6f,
                9f,
                0.1f,
                0.8f,
                new ArrayList<>(List.of(SuitAbility.SHOOT_ARROWS_ABILITY)),
                new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT)),
                ChargeType.ON_GROUND,
                new ArrayList<>(),
                new ArrayList<>(List.of(MobEffects.NIGHT_VISION)),
                "Mark 2"
        ));
    }
}
