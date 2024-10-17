package phewitch.powersuits.common.items.suits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.common.items.suits.ArmorBase.ArmorMaterials;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark3Armor extends SuitArmourBase {
    public Mark3Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK3, type, properties, Suits.MARK3_FEATURES);
    }
}
