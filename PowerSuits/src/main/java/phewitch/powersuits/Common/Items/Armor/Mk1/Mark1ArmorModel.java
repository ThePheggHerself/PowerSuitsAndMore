package phewitch.powersuits.Common.Items.Armor.Mk1;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib.model.GeoModel;

public class Mark1ArmorModel extends GeoModel<Mark1Armor> {
    @Override
    public ResourceLocation getModelResource(Mark1Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/mark1_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Mark1Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/models/armor/mark1_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Mark1Armor animatable) {
        return new ResourceLocation(PowerSuits.MODID, "animations/mark1_armor.json");
    }
}
