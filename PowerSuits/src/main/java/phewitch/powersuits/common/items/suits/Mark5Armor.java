package phewitch.powersuits.common.items.suits;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark5Armor extends SuitArmourBase {
    public Mark5Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, Suits.MARK5_FEATURES);
    }
}
