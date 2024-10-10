package phewitch.powersuits.Common.Items.Armor.Mk2;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Armor;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib.model.GeoModel;

public class Mark2ArmorModel extends GeoModel<Mark2Armor> {
    @Override
    public ResourceLocation getModelResource(Mark2Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/mark2_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Mark2Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/models/armor/mark2_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Mark2Armor animatable) {
        return new ResourceLocation(PowerSuits.MODID, "animations/mark2_armor.json");
    }
}
