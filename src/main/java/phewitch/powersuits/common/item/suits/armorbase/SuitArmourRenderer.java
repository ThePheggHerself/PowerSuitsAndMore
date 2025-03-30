package phewitch.powersuits.common.item.suits.armorbase;

import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourBase;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class SuitArmourRenderer extends GeoArmorRenderer<SuitArmourBase> {
    public SuitArmourRenderer() {
        super(new SuitArmorModel());
    }
}
