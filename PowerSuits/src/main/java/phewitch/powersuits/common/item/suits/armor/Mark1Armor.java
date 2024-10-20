package phewitch.powersuits.common.item.suits.armor;

import net.minecraft.world.item.ItemStack;
import phewitch.powersuits.common.item.suits.Suits;
import phewitch.powersuits.common.item.suits.armorbase.ArmorMaterials;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourBase;

public class Mark1Armor extends SuitArmourBase {
    public Mark1Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK1, type, properties, Suits.MARK1_FEATURES);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }


}
