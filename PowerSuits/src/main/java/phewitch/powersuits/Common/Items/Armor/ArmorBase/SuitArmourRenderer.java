package phewitch.powersuits.Common.Items.Armor.ArmorBase;

import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class SuitArmourRenderer extends GeoArmorRenderer<SuitArmourBase> {
    public SuitArmourRenderer(String Name) {
        super(new SuitArmorModel(Name));
    }
}
