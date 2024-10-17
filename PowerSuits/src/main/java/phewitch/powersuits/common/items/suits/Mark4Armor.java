package phewitch.powersuits.common.items.suits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark4Armor extends SuitArmourBase {
    public Mark4Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, Suits.MARK4_FEATURES);
    }
}
