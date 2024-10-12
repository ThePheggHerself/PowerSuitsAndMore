package phewitch.powersuits.Common.Items.Armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark3Armor extends SuitArmourBase {
    public Mark3Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark3",
                new SuitFeatures(100, 1,
                        new ArrayList<SuitFeatures.ABILITIES>(
                                List.of(SuitFeatures.ABILITIES.LIMITED_FLIGHT,
                                        SuitFeatures.ABILITIES.SHOOT_ARROWS)),
                        new ArrayList<>(List.of(new MobEffectInstance(MobEffects.NIGHT_VISION, 600)))));
    }
}
