package phewitch.powersuits.common.capabilities.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.energy.EnergyStorage;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.server2client.S2CSuitEnergySync;

public class SuitPowerCapability extends EnergyStorage {
    public SuitPowerCapability(int Capacity, int CurrentPower) {
        super(Capacity);
        this.capacity = Capacity;
        this.energy = CurrentPower;
    }

    public SuitPowerCapability(int Capacity) {
        super(Capacity);
    }

    public int charge(int maxExtract, @Nullable ServerPlayer player) {
        System.out.println("PRE CHARGE: " + this.energy);

        int extractedEnergy = super.receiveEnergy(maxExtract, false);

        System.out.println("POST CHARGE: " + this.energy);

        if(player != null)
            ModMessages.sendToClient(new S2CSuitEnergySync(this.getEnergyStored(), this.getMaxEnergyStored()), player);

        return extractedEnergy;
    }

    public int discharge(int maxReceive, ServerPlayer player) {
        System.out.println("PRE CHARGE: " + this.energy);

        int receivedEnergy = super.extractEnergy(maxReceive, false);

        System.out.println("PRE CHARGE: " + this.energy);

        ModMessages.sendToClient(new S2CSuitEnergySync(this.getEnergyStored(), this.getMaxEnergyStored()), player);
        return receivedEnergy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("eCap", getMaxEnergyStored());
        nbt.putInt("eCur", getEnergyStored());
    }

    public void loadNBTData(CompoundTag nbt) {
        this.capacity = nbt.getInt("eCap");
        this.charge(nbt.getInt("eCur"), null);

        System.out.println("ENERGY: " + this.energy);
    }
}
