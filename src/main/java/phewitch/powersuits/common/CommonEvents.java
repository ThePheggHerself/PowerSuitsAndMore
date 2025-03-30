package phewitch.powersuits.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import phewitch.powersuits.PowerSuits;
import phewitch.powersuits.common.capabilities.Capabilities;
import phewitch.powersuits.common.capabilities.data.PlayerOSSCapabilityData;
import phewitch.powersuits.common.capabilities.data.SuitPowerCapability;
import phewitch.powersuits.common.capabilities.providers.PlayerOSSCapabilityProvider;
import phewitch.powersuits.common.capabilities.providers.SuitPowerCapabilityProvider;
import phewitch.powersuits.common.entity.EntityManager;
import phewitch.powersuits.common.entity.mobs.SuitSentry;
import phewitch.powersuits.common.item.ItemsManager;
import phewitch.powersuits.common.item.suits.armorbase.pieces.*;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.server2client.S2CSuitEnergySync;
import phewitch.powersuits.common.networking.packets.server2client.S2CSyncOSS;
import phewitch.powersuits.common.sound.ModSounds;
import phewitch.powersuits.utils.CommandManager;

@Mod.EventBusSubscriber(modid = PowerSuits.MODID)
public class CommonEvents {
    public static CommonEvents Instance;

    public CommonEvents(IEventBus eventBus) {
        Instance = this;

        ItemsManager.register(eventBus);
        EntityManager.register(eventBus);
        ModSounds.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide() && event.getEntity() instanceof ServerPlayer player) {
            player.getCapability(Capabilities.PLAYER_OSS).ifPresent(playerOSS -> {
                ModMessages.sendToClient(new S2CSyncOSS(playerOSS.getSuits()), player);
            });

            var chestplate = SuitArmourChest.getChestplate(player);

            if(chestplate != null){
                ModMessages.sendToClient(new S2CSuitEnergySync(chestplate.getEnergy(player), chestplate.getMaxEnergy(player)), player);
            }
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesItem(AttachCapabilitiesEvent<ItemStack> event) {
        if(!(event.getObject().getItem() instanceof SuitArmourChest sAB))
            return;

        var backend = new SuitPowerCapability(sAB.features.maxPower);
        var optionalStorage = LazyOptional.of(() -> backend);
        event.addCapability(new ResourceLocation(PowerSuits.MODID, "suit_power"), new SuitPowerCapabilityProvider(backend, optionalStorage));
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(Capabilities.PLAYER_OSS).isPresent()) {
                event.addCapability(new ResourceLocation(PowerSuits.MODID, "oss_properties"), new PlayerOSSCapabilityProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(Capabilities.PLAYER_OSS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(Capabilities.PLAYER_OSS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerOSSCapabilityData.class);
        event.register(SuitPowerCapability.class);
    }

    @SubscribeEvent
    public void PlayerFallEvent(LivingFallEvent ev) {
        if (ev.getEntity() instanceof Player plr) {
            var sAB = SuitArmourBoots.getBoots(plr);
            if (sAB != null) {
                sAB.handleFallDamage(ev);
            }
        }
    }

    @SubscribeEvent
    public void EntityHurtEvent(LivingHurtEvent ev) {
        if (ev.getEntity() instanceof Player plr) {
            var sAB = SuitArmourBase.getAny(plr);
            if (sAB != null)
                sAB.handleWearerHurt(ev);
        }
        if (ev.getSource().getEntity() instanceof Player plr) {
            var sAB = SuitArmourBase.getAny(plr);
            if (sAB != null)
                sAB.handleDamagedEntity(ev);
        }
    }

    @SubscribeEvent
    public void TickEvent(TickEvent.PlayerTickEvent ev) {
        var sAB = SuitArmourBase.getAny(ev.player);

        if (sAB != null)
            sAB.armourTick(ev);
    }

    @SubscribeEvent
    public void EndermanAngerEvent(EnderManAngerEvent ev) {
        var sAB = SuitArmourHelmet.getHelmet(ev.getPlayer());
        if (sAB != null)
            sAB.handleEndermanAnger(ev);

    }

    @Mod.EventBusSubscriber(modid = PowerSuits.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(EntityManager.SENTRY.get(), SuitSentry.setAttributes());
        }
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandManager.registerALL(event.getDispatcher());
    }
}
