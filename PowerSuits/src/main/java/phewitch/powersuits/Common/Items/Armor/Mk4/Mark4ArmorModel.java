package phewitch.powersuits.Common.Items.Armor.Mk4;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Mark4ArmorModel extends AnimatedGeoModel<Mark4Armor> {
    @Override
    public ResourceLocation getModelLocation(Mark4Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/mark4_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Mark4Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/models/armor/mark4_armor.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Mark4Armor animatable) {
        return new ResourceLocation(PowerSuits.MODID, "animations/mark4_armor.json");
    }
}
