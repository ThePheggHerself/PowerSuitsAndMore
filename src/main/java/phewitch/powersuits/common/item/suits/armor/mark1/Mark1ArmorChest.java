package phewitch.powersuits.common.item.suits.armor.mark1;

import net.minecraft.world.item.ItemStack;
import phewitch.powersuits.common.item.suits.armor.FeatureList;
import phewitch.powersuits.common.item.suits.armorbase.enums.ArmorMaterials;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourChest;

public class Mark1ArmorChest extends SuitArmourChest {
        public Mark1ArmorChest(Type type, Properties properties) {
            super(ArmorMaterials.MARK1, type, properties, FeatureList.MARK1);
        }

        @Override
        public boolean isDamageable(ItemStack stack) {
            return true;
        }
    }
