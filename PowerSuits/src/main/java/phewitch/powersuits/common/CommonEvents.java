package phewitch.powersuits.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.capabilities.PlayerOSS;
import phewitch.powersuits.common.capabilities.PlayerOSSProvider;
import phewitch.powersuits.common.entity.EntityManager;
import phewitch.powersuits.common.item.ItemsManager;
import phewitch.powersuits.common.item.suits.armorbase.SuitArmourBase;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.server2client.S2CSuitPowerSync;
import phewitch.powersuits.common.sound.ModSounds;

@Mod.EventBusSubscriber(modid = PowerSuits.MODID)
public class CommonEvents {
    public static CommonEvents Instance;

    public CommonEvents(IEventBus eventBus){
        Instance = this;

        ItemsManager.register(eventBus);
        EntityManager.register(eventBus);
        ModSounds.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void PlayerFallEvent(LivingFallEvent ev){
        if(ev.getEntity() instanceof Player plr){
            var sAB = SuitArmourBase.isWearingAnyPiece(plr);
            if(sAB != null){
                sAB.handleFallDamage(ev);
            }
        }
    }
    @SubscribeEvent
    public void EntityHurtEvent(LivingHurtEvent ev){
        if(ev.getEntity() instanceof Player plr){
            var sAB = SuitArmourBase.isWearingAnyPiece(plr);
            if(sAB != null)
                sAB.handleWearerHurt(ev);
        }
        if (ev.getSource().getEntity() instanceof Player plr) {
            var sAB = SuitArmourBase.isWearingAnyPiece(plr);
            if(sAB != null)
                sAB.handleDamagedEntity(ev);
        }
    }

    @SubscribeEvent
    public void TickEvent(TickEvent.PlayerTickEvent ev){
        var sAB = SuitArmourBase.isWearingAnyPiece(ev.player);
        if(sAB != null)
            sAB.armourTick(ev);
    }
    @SubscribeEvent
    public void EndermanAngerEvent(EnderManAngerEvent ev){
        var sAB = SuitArmourBase.isWearingAnyPiece(ev.getPlayer());
        if(sAB != null)
            sAB.handleEndermanAnger(ev);

    }



    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerOSSProvider.PLAYER_OSS).isPresent()) {
                event.addCapability(new ResourceLocation(PowerSuits.MODID, "properties"), new PlayerOSSProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerOSSProvider.PLAYER_OSS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerOSSProvider.PLAYER_OSS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerOSS.class);
    }
}
