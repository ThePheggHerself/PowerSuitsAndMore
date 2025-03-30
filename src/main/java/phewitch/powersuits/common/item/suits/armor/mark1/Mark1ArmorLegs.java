package phewitch.powersuits.common.item.suits.armor.mark1;

import net.minecraft.world.item.ItemStack;
import phewitch.powersuits.common.item.suits.armor.FeatureList;
import phewitch.powersuits.common.item.suits.armorbase.enums.ArmorMaterials;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourLegs;

public class Mark1ArmorLegs extends SuitArmourLegs {
        public Mark1ArmorLegs(Type type, Properties properties) {
            super(ArmorMaterials.MARK1, type, properties, FeatureList.MARK1_SUIT_FEATURES);
        }

        @Override
        public boolean isDamageable(ItemStack stack) {
            return true;
        }
    }
