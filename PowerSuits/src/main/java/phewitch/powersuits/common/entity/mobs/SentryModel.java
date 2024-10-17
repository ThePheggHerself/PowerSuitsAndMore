package phewitch.powersuits.common.entity.mobs;

import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib.model.GeoModel;

public class SentryModel extends GeoModel<SuitSentry> {

    @Override
    public ResourceLocation getModelResource(SuitSentry object) {
        return new ResourceLocation(PowerSuits.MODID, "geo/entity/" + object.name + "_sentry.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SuitSentry object) {
        return new ResourceLocation(PowerSuits.MODID, "textures/entity/" + object.name + "_sentry.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SuitSentry animatable) {
        return new ResourceLocation(PowerSuits.MODID, "animations/sentry.animation.json");
    }
}
