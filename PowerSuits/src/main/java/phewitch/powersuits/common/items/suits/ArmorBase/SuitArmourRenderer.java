package phewitch.powersuits.common.items.suits.ArmorBase;

import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class SuitArmourRenderer extends GeoArmorRenderer<SuitArmourBase> {
    public SuitArmourRenderer(String Name) {
        super(new SuitArmorModel(Name));
    }
}
