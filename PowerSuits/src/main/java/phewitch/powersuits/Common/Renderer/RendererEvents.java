package phewitch.powersuits.Common.Renderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Armor;
import phewitch.powersuits.Common.Items.Armor.Mk1.Mark1Renderer;
import phewitch.powersuits.Common.Items.Armor.Mk2.Mark2Armor;
import phewitch.powersuits.Common.Items.Armor.Mk2.Mark2Renderer;
import phewitch.powersuits.Common.Items.Armor.MK3.Mark3Armor;
import phewitch.powersuits.Common.Items.Armor.MK3.Mark3Renderer;
import phewitch.powersuits.PowerSuits;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = PowerSuits.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RendererEvents {


    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(Mark1Armor.class, new Mark1Renderer());
        GeoArmorRenderer.registerArmorRenderer(Mark2Armor.class, new Mark2Renderer());
        GeoArmorRenderer.registerArmorRenderer(Mark3Armor.class, new Mark3Renderer());
    }

}
