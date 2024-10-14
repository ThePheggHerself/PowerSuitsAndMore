package phewitch.powersuits.common.items.suits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark5Armor extends SuitArmourBase {
    public Mark5Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark5",
                new SuitFeatures(250, 5,
                        new ArrayList<SuitFeatures.ABILITIES>(
                                List.of(SuitFeatures.ABILITIES.LIMITED_FLIGHT,
                                        SuitFeatures.ABILITIES.FULL_FLIGHT,
                                        SuitFeatures.ABILITIES.SHOOT_ARROWS,
                                        SuitFeatures.ABILITIES.SHOOT_LASERS,
                                        SuitFeatures.ABILITIES.SHOOT_CHEST_LASER,
                                        SuitFeatures.ABILITIES.SENTRY_MODE)),
                        new ArrayList<>(
                                List.of(new MobEffectInstance(MobEffects.NIGHT_VISION, 600),
                                        new MobEffectInstance(MobEffects.WATER_BREATHING, 600),
                                        new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600),
                                        new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600)))));
    }
}
