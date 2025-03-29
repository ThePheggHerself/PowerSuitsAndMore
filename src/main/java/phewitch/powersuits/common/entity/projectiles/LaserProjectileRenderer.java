package phewitch.powersuits.common.entity.projectiles;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import phewitch.powersuits.PowerSuits;

public class LaserProjectileRenderer extends ArrowRenderer<LaserProjectile> {
    public LaserProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(LaserProjectile abstractArrow) {
        return new ResourceLocation(PowerSuits.MODID, "textures/entity/laser_projectile.png");
    }
}
