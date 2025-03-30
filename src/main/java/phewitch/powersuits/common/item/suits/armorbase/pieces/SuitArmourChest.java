package phewitch.powersuits.common.item.suits.armorbase.pieces;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.LogicalSide;
import org.jetbrains.annotations.NotNull;
import phewitch.powersuits.common.capabilities.Capabilities;
import phewitch.powersuits.common.capabilities.data.SuitPowerCapability;
import phewitch.powersuits.common.item.suits.armorbase.SuitFeatures;
import phewitch.powersuits.common.item.suits.armorbase.enums.PassiveAbilities;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SuitArmourChest extends SuitArmourBase {
    private LazyOptional<SuitPowerCapability> capCache = LazyOptional.empty();

    public SuitArmourChest(ArmorMaterial materialIn, Type type, Properties properties, SuitFeatures features) {
        super(materialIn, type, properties, features);
    }

    public static Optional<SuitPowerCapability> getEnergy(ItemStack stack) {
        return stack.getCapability(Capabilities.SUIT_ENERGY).resolve();
    }

    private LazyOptional<SuitPowerCapability> getCap(ServerPlayer player) {
        if (capCache == null || !capCache.isPresent()) {
            capCache = player.getItemBySlot(EquipmentSlot.CHEST).getCapability(Capabilities.SUIT_ENERGY);
        }

        return capCache;
    }

    @Override
    public void onArmorPieceTick(TickEvent.PlayerTickEvent ev) {
        if (ev.side == LogicalSide.CLIENT) {
            if (hasBoots(ev.player)) {
                doLimitedFlight(ev);
            } else {
                if (features.passiveA.contains(PassiveAbilities.FULL_FLIGHT))
                    ev.player.getAbilities().mayfly = false;
            }
        }

        if (ev.side == LogicalSide.SERVER) {
            tryChargeArmour(ev);

            if(hasFullSet(ev.player)){
                doEffects(ev);

                if (features.passiveA.contains(PassiveAbilities.WATER_CONDUIT)) {
                    if (ev.player.getAirSupply() != ev.player.getMaxAirSupply())
                        ev.player.setAirSupply(ev.player.getMaxAirSupply());
                }
            }
        }
    }

    @Override
    public void onArmorEquipped(LivingEquipmentChangeEvent ev) {
        var player = ((Player) ev.getEntity());

        if (features.passiveA.contains(PassiveAbilities.FULL_FLIGHT)) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    @Override
    public void onArmorUnequipped(LivingEquipmentChangeEvent ev) {
        var player = ((Player) ev.getEntity());

        if (features.passiveA.contains(PassiveAbilities.FULL_FLIGHT)) {
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
            player.onUpdateAbilities();
        }
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!entity.level().isClientSide())
            entity.gameEvent(GameEvent.ELYTRA_GLIDE);

        return true;
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack pStack) {
        return true;
    }

    @Override
    public int getBarWidth(@NotNull ItemStack pStack) {
        var energy = getEnergy(pStack);
        return energy.map(suitPowerCapability -> Math.max(0, (suitPowerCapability.getEnergyStored() * 5) / (suitPowerCapability.getMaxEnergyStored() * 5))).orElse(10);
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return (entity instanceof Player plr) && hasBoots(plr);
    }

    public void tryChargeArmour(TickEvent.PlayerTickEvent ev) {
        getCap((ServerPlayer) ev.player).ifPresent(energy -> {
            if (energy.getEnergyStored() == energy.getMaxEnergyStored())
                return;

            switch (features.chargeType) {
                case ON_GROUND: {
                    if (ev.player.onGround()) {
                        chargeArmor(features.recharge, (ServerPlayer) ev.player);

                    }
                }
                case IN_FIRE:
                case IN_FIRE_OR_LAVA: {
                    if (ev.player.isOnFire() || ev.player.isInLava()) {
                        chargeArmor(features.recharge, (ServerPlayer) ev.player);

                    }
                }

                case IN_WATER: {
                    if (ev.player.isUnderWater() || (ev.player.isInWaterOrRain() && ev.player.onGround())) {
                        chargeArmor(features.recharge, (ServerPlayer) ev.player);

                    }
                }
            }
        });
    }

    public void chargeArmor(int amount, ServerPlayer player) {
        getCap(player).ifPresent(energyStorage -> energyStorage.charge(amount, player));
    }

    public boolean tryDischargeArmor(int amount, ServerPlayer player) {
        var canDischarge = new AtomicReference<>(false);

        getCap(player).ifPresent(energyStorage -> {
            System.out.println("aaaaa " + energyStorage.getEnergyStored());

            if (energyStorage.getEnergyStored() > amount) {
                energyStorage.discharge(amount, player);
                canDischarge.set(true);
            }
        });

        return canDischarge.get();
    }

    public void dischargeArmor(int amount, ServerPlayer player) {
        getCap(player).ifPresent(energyStorage -> energyStorage.discharge(amount, player));
    }

    public void setEnergy(int amount, ServerPlayer player) {
        getCap(player).ifPresent(energyStorage -> energyStorage.setEnergy(amount));
    }

    public int getEnergy(ServerPlayer player) {
        AtomicInteger energy = new AtomicInteger();

        getCap(player).ifPresent(energyStorage -> energy.set(energyStorage.getMaxEnergyStored()));

        return energy.get();
    }

    public int getMaxEnergy(ServerPlayer player) {
        AtomicInteger energy = new AtomicInteger();

        getCap(player).ifPresent(energyStorage -> energy.set(energyStorage.getMaxEnergyStored()));

        return energy.get();
    }
}
