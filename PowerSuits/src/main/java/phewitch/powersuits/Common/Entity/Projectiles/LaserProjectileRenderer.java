package phewitch.powersuits.Common.Entity.Projectiles;

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
        return new ResourceLocation(PowerSuits.MODID, "textures/item/laser_projectile.png");
    }
}
