package phewitch.powersuits.common.item.suits.armorbase.pieces;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.eventbus.api.Event;
import phewitch.powersuits.common.item.suits.armorbase.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;

public class SuitArmourHelmet extends SuitArmourBase {
    public SuitArmourHelmet(ArmorMaterial materialIn, Type type, Properties properties, SuitFeatures features) {
        super(materialIn, type, properties, features);
    }

    public void handleEndermanAnger(EnderManAngerEvent ev) {
        if (features.passiveA.contains(PassiveAbilities.BLOCK_ENDERMAN_LOOK)) {
            ev.setResult(Event.Result.DENY);
            ev.setCanceled(true);
        }
    }
}
