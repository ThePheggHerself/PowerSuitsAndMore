package phewitch.powersuits.Common.Items.Armor;

import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitFeatures;

public class Mark2Armor extends SuitArmourBase {
    public Mark2Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark2");

        features.add(SuitFeatures.LIMITED_FLIGHT);
        features.add(SuitFeatures.SHOOT_ARROWS);
        fallDamageMultiplier = 0.3f;
        projectileDamage = 3;
        lfMaxfuel = 200f;
        lfVelocity = 0.15d;
    }
}
