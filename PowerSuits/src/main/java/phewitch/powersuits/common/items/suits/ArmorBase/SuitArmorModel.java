package phewitch.powersuits.common.items.suits.ArmorBase;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib.model.GeoModel;

public class SuitArmorModel extends GeoModel<SuitArmourBase> {

    @Override
    public ResourceLocation getModelResource(SuitArmourBase object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/suits/"+ object.name +"_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SuitArmourBase object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/models/armor/"+ object.name +"_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SuitArmourBase animatable) {
        return new ResourceLocation(PowerSuits.MODID, "animations/"+ animatable.name +"_armor.json");
    }
}
