package phewitch.powersuits.common.item.suits.armorbase;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourBase;
import software.bernie.geckolib.model.GeoModel;

public class SuitArmorModel extends GeoModel<SuitArmourBase> {

    @Override
    public ResourceLocation getModelResource(SuitArmourBase object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/suits/"+ object.features.getModelName() +"_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SuitArmourBase object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/models/armor/"+ object.features.getModelName() +"_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SuitArmourBase object) {
        return new ResourceLocation(PowerSuits.MODID, "animations/"+ object.features.getModelName() +"_armor.json");
    }
}
