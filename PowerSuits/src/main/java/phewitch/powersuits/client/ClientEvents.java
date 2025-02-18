package phewitch.powersuits.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import phewitch.powersuits.client.gui.GUIManager;
import phewitch.powersuits.client.gui.hud.oss.OSSScreen;
import phewitch.powersuits.client.gui.hud.suit.SuitOverlay;
import phewitch.powersuits.client.gui.hud.workbench.WorkbenchScreen;
import phewitch.powersuits.common.entity.EntityManager;
import phewitch.powersuits.common.entity.mobs.SuitSentry;
import phewitch.powersuits.common.entity.mobs.SentryRenderer;
import phewitch.powersuits.common.entity.projectiles.ChestLaserProjectileRenderer;
import phewitch.powersuits.common.entity.projectiles.LaserProjectileRenderer;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourBase;
import phewitch.powersuits.PowerSuits;

public class ClientEvents {
    public static ClientEvents Instance;
    public ClientEvents(IEventBus eventBus){
        Instance = this;

        GUIManager.register(eventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = PowerSuits.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents{

        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(EntityManager.SENTRY.get(), SuitSentry.setAttributes());
        }

        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.ABILITY_1);
            event.register(KeyBinding.ABILITY_2);
            event.register(KeyBinding.ABILITY_3);
            event.register(KeyBinding.ABILITY_4);
        }

        @SubscribeEvent
        public static void setupClient(FMLClientSetupEvent event) {
            EntityRenderers.register(EntityManager.SENTRY.get(), context ->  new SentryRenderer(context));
            EntityRenderers.register(EntityManager.LASER_PROJECTILE.get(), context -> new LaserProjectileRenderer(context));
            EntityRenderers.register(EntityManager.CHEST_LASER_PROJECTILE.get(), context -> new ChestLaserProjectileRenderer(context));

            MenuScreens.register(GUIManager.OSS_MENU.get(), OSSScreen::new);
            MenuScreens.register(GUIManager.WORKBENCH_MENU.get(), WorkbenchScreen::new);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent ev){
            ev.registerAboveAll("power", SuitOverlay.HUD_Suit);
        }
    }

    @Mod.EventBusSubscriber(modid = PowerSuits.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents{
        @SubscribeEvent
        public static void ClientTickEvent(TickEvent.ClientTickEvent ev){
            var instance = Minecraft.getInstance();
            var player = instance.player;

            if (instance == null || instance.isPaused() || player == null || player.level() == null)
                return;

            if(player.getInventory().getArmor(0).getItem() instanceof SuitArmourBase sAB){
                sAB.clientArmourTick(ev, player);
            }
        }

        @SubscribeEvent
        public void onRenderGui(RenderGuiEvent event){
            GUIManager.renderHUDItems(event);
        }
    }
}
