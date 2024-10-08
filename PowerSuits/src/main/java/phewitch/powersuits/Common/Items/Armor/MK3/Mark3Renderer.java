package phewitch.powersuits.Common.Items.Armor.MK3;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class Mark3Renderer extends GeoArmorRenderer<Mark3Armor> {
    public Mark3Renderer() {
        super(new Mark3ArmorModel());

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
