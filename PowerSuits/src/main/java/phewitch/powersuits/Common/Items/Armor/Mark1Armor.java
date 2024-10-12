package phewitch.powersuits.Common.Items.Armor;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitFeatures;

public class Mark1Armor extends SuitArmourBase {
    public Mark1Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark1");
        this.fallDamageMultiplier = 0.6f;

        features.add(SuitFeatures.LIMITED_FLIGHT);
        lfMaxfuel = 30f;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }


}
