package phewitch.powersuits.common.entity.mobs;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SentryRenderer extends GeoEntityRenderer<SuitSentry> {
    public SentryRenderer(EntityRendererProvider.Context context) {
        super(context, new SentryModel());
    }

    @Override
    public ResourceLocation getTextureLocation(SuitSentry object) {
        return this.model.getTextureResource(this.animatable);
    }

    @Override
    public void render(SuitSentry entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
