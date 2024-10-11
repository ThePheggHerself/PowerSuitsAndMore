package phewitch.powersuits.Common.Items.Armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;

public class Mark3Armor extends SuitArmourBase {
    public Mark3Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark3");
        fallDamageMultiplier = 0;

        projectileDamage = 5;
        shootsLasers = false;
        shootsArrows = true;

        fullFlightOnFullArmour = false;

        this.fullArmourEffects.add(new MobEffectInstance(MobEffects.NIGHT_VISION, 300));
    }
}
