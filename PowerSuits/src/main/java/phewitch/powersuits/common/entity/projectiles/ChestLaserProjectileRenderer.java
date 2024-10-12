package phewitch.powersuits.common.entity.projectiles;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.PowerSuits;

public class ChestLaserProjectileRenderer extends ArrowRenderer<ChestLaserProjectile> {
    public ChestLaserProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(ChestLaserProjectile abstractArrow) {
        return new ResourceLocation(PowerSuits.MODID, "textures/entity/chest_laser_projectile.png");
    }
}
