package phewitch.powersuits.common.item.suits.armor.mark1;

import net.minecraft.world.item.ItemStack;
import phewitch.powersuits.common.item.suits.armor.FeatureList;
import phewitch.powersuits.common.item.suits.armorbase.enums.ArmorMaterials;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourHelmet;

public class Mark1ArmorHelmet extends SuitArmourHelmet {
    public Mark1ArmorHelmet(Type type, Properties properties) {
        super(ArmorMaterials.MARK1, type, properties, FeatureList.MARK1_SUIT_FEATURES);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }
}
