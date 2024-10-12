package phewitch.powersuits.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import phewitch.powersuits.Client.GUI.GUIManager;
import phewitch.powersuits.Common.Entity.EntityManager;
import phewitch.powersuits.Common.Entity.Projectiles.ChestLaserProjectileRenderer;
import phewitch.powersuits.Common.Entity.Projectiles.LaserProjectileRenderer;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitArmourBase;
import phewitch.powersuits.Common.Items.Armor.ArmorBase.SuitFeatures;
import phewitch.powersuits.Common.networking.ModMessages;
import phewitch.powersuits.Common.networking.Packets.C2SSuitShootArrow;
import phewitch.powersuits.Common.networking.Packets.C2SSuitShootLaser;
import phewitch.powersuits.PowerSuits;

public class ClientEvents {
    public static ClientEvents Instance;
    public ClientEvents(IEventBus eventBus){
        Instance = this;

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = PowerSuits.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents{
        @SubscribeEvent
        public static void setupClient(FMLClientSetupEvent event) {
            EntityRenderers.register(EntityManager.LASER_PROJECTILE.get(), context -> new LaserProjectileRenderer(context));
            EntityRenderers.register(EntityManager.CHEST_LASER_PROJECTILE.get(), context -> new ChestLaserProjectileRenderer(context));
        }
    }

    @Mod.EventBusSubscriber(modid = PowerSuits.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents{
        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.SHOOT_ARROW_KEY);
            event.register(KeyBinding.SHOOT_LASER_KEY);
        }

        @SubscribeEvent
        public static void ClientTickEvent(TickEvent.ClientTickEvent ev){
            var instance = Minecraft.getInstance();
            var player = instance.player;

            if (instance == null || instance.isPaused() || player == null || player.level() == null)
                return;

            if(player.getInventory().getArmor(0).getItem() instanceof SuitArmourBase sAB){
                sAB.handleClientFeatures(ev, player);
            }
        }

        @SubscribeEvent
        public void onRenderGui(RenderGuiEvent event){
            GUIManager.renderHUDItems(event);
        }
    }
}
