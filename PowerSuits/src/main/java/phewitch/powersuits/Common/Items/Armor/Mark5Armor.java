package phewitch.powersuits.Common.Items.Armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitFeatures;

public class Mark5Armor extends SuitArmourBase {
    public Mark5Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark5");

        features.add(SuitFeatures.LIMITED_FLIGHT);
        features.add(SuitFeatures.FULL_FLIGHT);
        features.add(SuitFeatures.SHOOT_ARROWS);
        features.add(SuitFeatures.SHOOT_LASERS);
        features.add(SuitFeatures.SHOOT_CHEST_LASER);

        this.fallDamageMultiplier = 0;
        projectileDamage = 5;

        this.fullArmourEffects.add(new MobEffectInstance(MobEffects.NIGHT_VISION, 300));
        this.fullArmourEffects.add(new MobEffectInstance(MobEffects.WATER_BREATHING, 300));
        this.fullArmourEffects.add(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300));
        this.fullArmourEffects.add(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300));
    }
}
