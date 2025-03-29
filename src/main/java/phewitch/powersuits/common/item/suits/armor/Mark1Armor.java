package phewitch.powersuits.common.item.suits.armor;

import net.minecraft.world.item.ItemStack;
import phewitch.powersuits.common.item.suits.armorbase.enums.ArmorMaterials;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourBase;
import phewitch.powersuits.common.item.suits.armorbase.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.ChargeType;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;

import java.util.ArrayList;
import java.util.List;

public class Mark1Armor extends SuitArmourBase {
    public Mark1Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK1, type, properties,
                new SuitFeatures(
                        30,
                        0.2f,
                        0.7f,
                        4f,
                        0.1f,
                        1f,
                        new ArrayList<>(),
                        new ArrayList<>(List.of(PassiveAbilities.LIMITED_FLIGHT)),
                        ChargeType.ON_GROUND,
                        new ArrayList<>(),
                        new ArrayList<>(),
                        "Mark 1"));
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }


}
