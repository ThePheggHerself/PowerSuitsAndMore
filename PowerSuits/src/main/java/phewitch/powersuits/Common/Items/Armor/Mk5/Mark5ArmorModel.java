package phewitch.powersuits.Common.Items.Armor.Mk5;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib.model.GeoModel;

public class Mark5ArmorModel extends GeoModel<Mark5Armor> {
    @Override
    public ResourceLocation getModelResource(Mark5Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/mark5_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Mark5Armor object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/models/armor/mark5_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Mark5Armor animatable) {
        return new ResourceLocation(PowerSuits.MODID, "animations/mark5_armor.json");
    }
}
