package phewitch.powersuits.common.capabilities.providers;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import phewitch.powersuits.common.capabilities.Capabilities;
import phewitch.powersuits.common.capabilities.data.PlayerOSSCapabilityData;

public class PlayerOSSCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    private PlayerOSSCapabilityData suits = null;
    private final LazyOptional<PlayerOSSCapabilityData> optional = LazyOptional.of(this::getSuits);

    private PlayerOSSCapabilityData getSuits() {
        if (this.suits == null) {
            this.suits = new PlayerOSSCapabilityData();
        }
        return suits;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == Capabilities.PLAYER_OSS) {
            return optional.cast();
        } else {
            return LazyOptional.empty();
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        var tag = new CompoundTag();
        getSuits().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        getSuits().loadNBTData(nbt);
    }
}

