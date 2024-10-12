package phewitch.powersuits.common.items.suits;

import net.minecraft.world.item.ArmorMaterial;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.common.items.suits.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark2Armor extends SuitArmourBase {
    public Mark2Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark2",
                new SuitFeatures(50, 0.6f, 0.6f, 9, 0.15f, 1f,
                        new ArrayList<SuitFeatures.ABILITIES>(
                                List.of(SuitFeatures.ABILITIES.LIMITED_FLIGHT,
                                        SuitFeatures.ABILITIES.SHOOT_ARROWS))));
    }
}
