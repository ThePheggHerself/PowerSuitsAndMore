package phewitch.powersuits.common.item.suits.armor.mark1;

import net.minecraft.world.item.ItemStack;
import phewitch.powersuits.common.item.suits.armor.FeatureList;
import phewitch.powersuits.common.item.suits.armorbase.enums.ArmorMaterials;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourBoots;

public class Mark1ArmorBoots extends SuitArmourBoots {
        public Mark1ArmorBoots(Type type, Properties properties) {
            super(ArmorMaterials.MARK1, type, properties, FeatureList.MARK1_SUIT_FEATURES);
        }

        @Override
        public boolean isDamageable(ItemStack stack) {
            return true;
        }
    }
