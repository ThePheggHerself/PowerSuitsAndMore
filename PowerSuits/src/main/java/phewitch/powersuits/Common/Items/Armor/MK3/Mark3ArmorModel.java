package phewitch.powersuits.Common.Items.Armor.MK3;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Armor;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib.model.GeoModel;

public class Mark3ArmorModel extends GeoModel<Mark3Armor> {
    @Override
    public ResourceLocation getModelResource(Mark3Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/mark3_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Mark3Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/models/armor/mark3_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Mark3Armor animatable) {
        return new ResourceLocation(PowerSuits.MODID, "animations/mark3_armor.json");
    }
}
