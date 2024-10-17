package phewitch.powersuits.common.items.suits;

import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark2Armor extends SuitArmourBase {
    public Mark2Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, Suits.MARK2_FEATURES);
    }
}
