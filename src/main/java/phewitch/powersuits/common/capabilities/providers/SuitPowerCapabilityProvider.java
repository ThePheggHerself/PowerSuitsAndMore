package phewitch.powersuits.common.capabilities.providers;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.common.capabilities.Capabilities;
import phewitch.powersuits.common.capabilities.data.SuitPowerCapability;

public class SuitPowerCapabilityProvider implements ICapabilitySerializable<CompoundTag> {

    private final SuitPowerCapability suitEnergy;
    private final LazyOptional<SuitPowerCapability> optional;

    public SuitPowerCapabilityProvider(SuitPowerCapability capability, LazyOptional<SuitPowerCapability> lazyOptional) {
        super();
        suitEnergy = capability;
        optional = lazyOptional;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == Capabilities.SUIT_ENERGY) {
            return optional.cast();
        } else {
            return LazyOptional.empty();
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        var tag = new CompoundTag();
        suitEnergy.saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        suitEnergy.loadNBTData(nbt);
    }
}
