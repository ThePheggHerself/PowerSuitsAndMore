package phewitch.powersuits.common.item.suits.armorbase.pieces;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import phewitch.powersuits.common.item.suits.armorbase.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;

public class SuitArmourBoots extends SuitArmourBase {
    public SuitArmourBoots(ArmorMaterial materialIn, Type type, Properties properties, SuitFeatures features) {
        super(materialIn, type, properties, features);
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
        return this.features.passiveA.contains(PassiveAbilities.WALK_POWDERED_SNOW);
    }

    public void handleFallDamage(LivingFallEvent ev) {

        if (ev.getDistance() > features.fallDmgCancDist)
            ev.setDistance(ev.getDistance() - features.fallDmgCancDist);
        else
            ev.setDistance(0);

        ev.setDamageMultiplier(features.fallDmgMult);
    }
}
