package phewitch.powersuits.common.capabilities.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.energy.EnergyStorage;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.common.networking.ModMessages;
import phewitch.powersuits.common.networking.packets.server2client.S2CSuitEnergySync;

public class SuitPowerCapability extends EnergyStorage {
    public SuitPowerCapability(int Capacity) {
        super(Capacity);
    }

    public void charge(int maxExtract, @Nullable ServerPlayer player) {
        int extractedEnergy = super.receiveEnergy(maxExtract, false);
        if(player != null)
            ModMessages.sendToClient(new S2CSuitEnergySync(this.getEnergyStored(), this.getMaxEnergyStored()), player);

    }

    public void discharge(int maxReceive, ServerPlayer player) {
        int receivedEnergy = super.extractEnergy(maxReceive, false);
        ModMessages.sendToClient(new S2CSuitEnergySync(this.getEnergyStored(), this.getMaxEnergyStored()), player);
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
    }
}
