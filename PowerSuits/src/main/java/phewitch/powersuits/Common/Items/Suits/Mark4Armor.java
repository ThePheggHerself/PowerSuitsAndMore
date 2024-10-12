package phewitch.powersuits.Common.Items.Suits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.Common.Items.Suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Suits.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark4Armor extends SuitArmourBase {
    public Mark4Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark4",
                new SuitFeatures(150, 1,
                        new ArrayList<SuitFeatures.ABILITIES>(
                                List.of(SuitFeatures.ABILITIES.LIMITED_FLIGHT,
                                        SuitFeatures.ABILITIES.FULL_FLIGHT,
                                        SuitFeatures.ABILITIES.SHOOT_ARROWS,
                                        SuitFeatures.ABILITIES.SHOOT_LASERS)),
                        new ArrayList<>(
                                List.of(new MobEffectInstance(MobEffects.NIGHT_VISION, 600),
                                        new MobEffectInstance(MobEffects.WATER_BREATHING, 600)))));
    }
}
