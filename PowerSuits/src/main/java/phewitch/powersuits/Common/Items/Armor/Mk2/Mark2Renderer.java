package phewitch.powersuits.Common.Items.Armor.Mk2;

import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Armor;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1ArmorModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class Mark2Renderer extends GeoArmorRenderer<Mark2Armor> {
    public Mark2Renderer() {
        super(new Mark2ArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}
