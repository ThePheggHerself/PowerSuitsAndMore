package phewitch.powersuits.common.items.suits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.common.items.suits.ArmorBase.ArmorMaterials;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark4Armor extends SuitArmourBase {
    public Mark4Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK4, type, properties, Suits.MARK4_FEATURES);
    }
}
