package phewitch.powersuits.Common.Items.Armor.Mk1;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class Mark1Renderer extends GeoArmorRenderer<Mark1Armor> {
    public Mark1Renderer() {
        super(new Mark1ArmorModel());

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
