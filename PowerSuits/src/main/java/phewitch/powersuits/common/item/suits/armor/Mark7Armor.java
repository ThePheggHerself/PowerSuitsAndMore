package phewitch.powersuits.common.item.suits.armor;

import net.minecraft.world.effect.MobEffects;
import phewitch.powersuits.common.item.suits.armorbase.enums.ArmorMaterials;
import phewitch.powersuits.common.item.suits.armorbase.SuitAbility;
import phewitch.powersuits.common.item.suits.armorbase.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;
import phewitch.powersuits.common.item.suits.armorbase.enums.Weakness;

import java.util.ArrayList;
import java.util.List;

public class Mark7Armor extends Mark5Armor {
    public Mark7Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK7, type, properties, new SuitFeatures(
                180,
                2,
                0.0f,
                9f,
                0.25f,
                0.0f,
                new ArrayList<>(List.of(SuitAbility.SHOOT_ENDER_SHOT_ABILITY, SuitAbility.TELEPORT_ABILITY, SuitAbility.SENTRY_MODE_ABILITY)),
                new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT, PassiveAbilities.FULL_FLIGHT, PassiveAbilities.BLOCK_ENDERMAN_LOOK)),
                ChargeType.ON_GROUND,
                new ArrayList<>(List.of(Weakness.WATER)),
                new ArrayList<>(List.of(MobEffects.NIGHT_VISION, MobEffects.MOVEMENT_SPEED)),
                "Mark 7"
        ));
    }
}
