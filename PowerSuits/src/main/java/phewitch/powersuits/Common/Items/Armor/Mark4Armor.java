package phewitch.powersuits.Common.Items.Armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;

public class Mark4Armor extends SuitArmourBase {
    public Mark4Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark4");
        this.fallDamageMultiplier = 0;
        projectileDamage = 5;
        shootsLasers = false;
        shootsArrows = true;

        this.fullArmourEffects.add(new MobEffectInstance(MobEffects.NIGHT_VISION, 300));
        this.fullArmourEffects.add(new MobEffectInstance(MobEffects.WATER_BREATHING, 300));
    }
}
