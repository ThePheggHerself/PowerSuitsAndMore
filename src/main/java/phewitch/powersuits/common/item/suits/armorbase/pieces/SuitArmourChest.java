package phewitch.powersuits.common.item.suits.armorbase.pieces;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.logging.log4j.core.jmx.Server;
import phewitch.powersuits.common.capabilities.Capabilities;
import phewitch.powersuits.common.capabilities.data.SuitPowerCapability;
import phewitch.powersuits.common.item.suits.armorbase.datatypes.SuitFeatures;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SuitArmourChest extends SuitArmourBase{
    public SuitArmourChest(ArmorMaterial materialIn, Type type, Properties properties, SuitFeatures features) {
        super(materialIn, type, properties, features);
    }

    private LazyOptional<SuitPowerCapability> capCache = LazyOptional.empty();
    private LazyOptional<SuitPowerCapability> getCap(ServerPlayer player){
        if(capCache == null || !capCache.isPresent()){
            capCache = player.getItemBySlot(EquipmentSlot.CHEST).getCapability(Capabilities.SUIT_ENERGY);
        }

        return capCache;
    }

    public void chargeArmor(int amount, ServerPlayer player){
        getCap(player).ifPresent(energyStorage -> energyStorage.charge(amount, player));
    }

    public boolean tryDischargeArmor(int amount, ServerPlayer player){
        var canDischarge = new AtomicReference<Boolean>(false);

        getCap(player).ifPresent(energyStorage -> {
            System.out.println("aaaaa " + energyStorage.getEnergyStored());

            if(energyStorage.getEnergyStored() > amount){
                energyStorage.discharge(amount, player);
                canDischarge.set(true);
            }
        });

        return canDischarge.get();
    }

    public void dischargeArmor(int amount, ServerPlayer player){
        getCap(player).ifPresent(energyStorage -> energyStorage.discharge(amount, player));
    }

    public void setEnergy(int amount, ServerPlayer player){
        getCap(player).ifPresent(energyStorage -> energyStorage.setEnergy(amount));
    }

    public int getEnergy(ServerPlayer player){
        AtomicInteger energy = new AtomicInteger();

        getCap(player).ifPresent(energyStorage -> {
            energy.set(energyStorage.getEnergyStored());
        } );

        return energy.get();
    }

    public int getMaxEnergy(ServerPlayer player){
        AtomicInteger energy = new AtomicInteger();

        getCap(player).ifPresent(energyStorage -> {
            energy.set(energyStorage.getMaxEnergyStored());
        } );

        return energy.get();
    }
}
