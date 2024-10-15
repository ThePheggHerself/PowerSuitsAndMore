package phewitch.powersuits.common.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerOSSProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerOSS> PLAYER_OSS = CapabilityManager.get(new CapabilityToken<PlayerOSS>() {});

    private PlayerOSS suits = null;
    private final LazyOptional<PlayerOSS> optional = LazyOptional.of(this::getSuits);

    private PlayerOSS getSuits(){
        if(this.suits == null){
            this.suits = new PlayerOSS();
        }
        return suits;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_OSS){
            return optional.cast();
        }
        else {
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
