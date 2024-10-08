package phewitch.powersuits.Common.Items.Armor.Mk2;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Armor;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Mark2ArmorModel extends AnimatedGeoModel<Mark2Armor> {
    @Override
    public ResourceLocation getModelLocation(Mark2Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/mark2_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Mark2Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/models/armor/mark2_armor.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Mark2Armor animatable) {
        return new ResourceLocation(PowerSuits.MODID, "animations/mark2_armor.json");
    }
}
