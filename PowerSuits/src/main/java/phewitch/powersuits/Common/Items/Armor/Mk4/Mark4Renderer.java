package phewitch.powersuits.Common.Items.Armor.Mk4;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class Mark4Renderer extends GeoArmorRenderer<Mark4Armor> {
    public Mark4Renderer() {
        super(new Mark4ArmorModel());

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
