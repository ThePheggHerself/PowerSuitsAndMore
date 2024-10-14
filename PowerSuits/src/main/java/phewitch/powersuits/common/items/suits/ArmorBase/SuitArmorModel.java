package phewitch.powersuits.common.items.suits.ArmorBase;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib.model.GeoModel;

public class SuitArmorModel extends GeoModel<SuitArmourBase> {

    public String name;

    public SuitArmorModel(String Name){
        super();
        name = Name;
    }

    @Override
    public ResourceLocation getModelResource(SuitArmourBase object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/suits/"+ name +"_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SuitArmourBase object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/models/armor/"+ name +"_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SuitArmourBase animatable) {
        return new ResourceLocation(PowerSuits.MODID, "animations/"+ name +"_armor.json");
    }
}
