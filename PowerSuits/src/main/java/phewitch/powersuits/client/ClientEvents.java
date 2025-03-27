package phewitch.powersuits.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.player.Abilities;
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
import phewitch.powersuits.common.item.suits.armorbase.SuitAbilitiesManager;
import phewitch.powersuits.common.item.suits.armorbase.SuitAbility;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourBase;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.item.suits.armorbase.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.ActiveAbilities;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.client2server.C2SSuitAbility;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

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

        public static Map<ActiveAbilities, Integer> CooldownList = new HashMap<ActiveAbilities, Integer>();

        public static boolean isOnCooldown(SuitAbility ability){
            if(CooldownList.containsKey(ability.AbilityType) && (System.currentTimeMillis() - CooldownList.get(ability.AbilityType)) < ability.Cooldown)
                return true;
            else return false;
        }

        @SubscribeEvent
        public static void ClientTickEvent(TickEvent.ClientTickEvent ev){
            var instance = Minecraft.getInstance();
            var player = instance.player;

            if (instance == null || instance.isPaused() || player == null || player.level() == null)
                return;

            var sAB = SuitAbilitiesManager.getSuitArmourBase(player);
            if(sAB != null && sAB.hasFullSet(player)){
                    if(KeyBinding.ABILITY_1.consumeClick() && 0 < sAB.features.activeA.size()){
                        var ability = sAB.features.activeA.get(0);
                        if(!sAB.features.hasPower(ability.Cost) || isOnCooldown(ability))
                            return;

                        System.out.println("ABILITY KEY 1 PRESSED");
                        sAB.features.removePower(ability.Cost);
                        ModMessages.sendToServer(new C2SSuitAbility(0, ability.AbilityType.getValue()));
                        return;
                    }
                    if(KeyBinding.ABILITY_2.consumeClick() && 1 < sAB.features.activeA.size()){
                        var ability = sAB.features.activeA.get(1);
                        if(!sAB.features.hasPower(ability.Cost) || isOnCooldown(ability))
                            return;

                        System.out.println("ABILITY KEY 2 PRESSED");
                        sAB.features.removePower(ability.Cost);
                        ModMessages.sendToServer(new C2SSuitAbility(1, ability.AbilityType.getValue()));
                        return;
                    }
                    if(KeyBinding.ABILITY_3.consumeClick() && 2 < sAB.features.activeA.size()){
                        var ability = sAB.features.activeA.get(2);
                        if(!sAB.features.hasPower(ability.Cost) || isOnCooldown(ability))
                            return;

                        System.out.println("ABILITY KEY 3 PRESSED");
                        sAB.features.removePower(ability.Cost);
                        ModMessages.sendToServer(new C2SSuitAbility(2, ability.AbilityType.getValue()));
                        return;
                    }
                    if(KeyBinding.ABILITY_4.consumeClick() && 3 < sAB.features.activeA.size()){
                        var ability = sAB.features.activeA.get(3);
                        if(!sAB.features.hasPower(ability.Cost) || isOnCooldown(ability))
                            return;

                        System.out.println("ABILITY KEY 4 PRESSED");
                        sAB.features.removePower(ability.Cost);
                        ModMessages.sendToServer(new C2SSuitAbility(3, ability.AbilityType.getValue()));
                        return;
                    }
            }
        }



        @SubscribeEvent
        public void onRenderGui(RenderGuiEvent event){
            GUIManager.renderHUDItems(event);
        }
    }
}
