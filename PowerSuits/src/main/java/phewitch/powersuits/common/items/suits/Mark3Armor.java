package phewitch.powersuits.common.items.suits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark3Armor extends SuitArmourBase {
    public Mark3Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark3",
                new SuitFeatures(100, 1,
                        new ArrayList<SuitFeatures.ABILITIES>(
                                List.of(SuitFeatures.ABILITIES.LIMITED_FLIGHT,
                                        SuitFeatures.ABILITIES.SHOOT_ARROWS)),
                        new ArrayList<>(List.of(MobEffects.NIGHT_VISION))));
    }
}
