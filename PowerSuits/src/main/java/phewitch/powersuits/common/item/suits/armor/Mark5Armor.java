package phewitch.powersuits.common.item.suits.armor;

import phewitch.powersuits.common.item.suits.Suits;
import phewitch.powersuits.common.item.suits.armorbase.ArmorMaterials;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourBase;
import phewitch.powersuits.common.item.suits.armorbase.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.SuitTemplate;

public class Mark5Armor extends SuitArmourBase {
    public Mark5Armor(ArmorMaterials materials, Type type, Properties properties, SuitTemplate features) {
        super(materials, type, properties, features);
    }
    public Mark5Armor(Type type, Properties properties) {
        super(ArmorMaterials.MARK5, type, properties, Suits.MARK5_FEATURES);
    }
}
