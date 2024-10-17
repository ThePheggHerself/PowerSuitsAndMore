package phewitch.powersuits.common.items.suits;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark1Armor extends SuitArmourBase {
    public Mark1Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, Suits.MARK1_FEATURES);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }


}
