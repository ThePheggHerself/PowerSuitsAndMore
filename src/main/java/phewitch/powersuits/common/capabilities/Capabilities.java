package phewitch.powersuits.common.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import phewitch.powersuits.common.capabilities.data.PlayerOSSCapabilityData;
import phewitch.powersuits.common.capabilities.data.SuitPowerCapability;

public class Capabilities {
    public static Capability<SuitPowerCapability> SUIT_ENERGY = CapabilityManager.get(new CapabilityToken<>() {});

    public static Capability<PlayerOSSCapabilityData> PLAYER_OSS = CapabilityManager.get(new CapabilityToken<>() {});
}
