package phewitch.powersuits.Common.Items.Armor.Mk1;

import software.bernie.geckolib.*;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class Mark1Renderer extends GeoArmorRenderer<Mark1Armor> {
    public Mark1Renderer() {
        super(new Mark1ArmorModel());
    }
}
