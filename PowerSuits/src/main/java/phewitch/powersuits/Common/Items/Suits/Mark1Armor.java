package phewitch.powersuits.Common.Items.Suits;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import phewitch.powersuits.Common.Items.Suits.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Suits.ArmorBase.SuitFeatures;

import java.util.ArrayList;
import java.util.List;

public class Mark1Armor extends SuitArmourBase {
    public Mark1Armor(ArmorMaterial materialIn, Type type, Properties properties) {
        super(materialIn, type, properties, "mark1",
                new SuitFeatures(30, 0.2f, 0.7f, 0.1f, 3, new ArrayList<SuitFeatures.ABILITIES>(
                    List.of(SuitFeatures.ABILITIES.LIMITED_FLIGHT))));
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }


}
