package phewitch.powersuits.Common.Items.Armor.MK3;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Mark3ArmorModel extends AnimatedGeoModel<Mark3Armor> {
    @Override
    public ResourceLocation getModelLocation(Mark3Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/mark3_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Mark3Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/models/armor/mark3_armor.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Mark3Armor animatable) {
        return new ResourceLocation(PowerSuits.MODID, "animations/mark3_armor.json");
    }
}
