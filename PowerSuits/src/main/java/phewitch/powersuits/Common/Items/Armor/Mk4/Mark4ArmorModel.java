package phewitch.powersuits.Common.Items.Armor.Mk4;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Armor;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib.model.GeoModel;

public class Mark4ArmorModel extends GeoModel<Mark4Armor> {
    @Override
    public ResourceLocation getModelResource(Mark4Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/mark4_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Mark4Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/models/armor/mark4_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Mark4Armor animatable) {
        return new ResourceLocation(PowerSuits.MODID, "animations/mark4_armor.json");
    }
}
