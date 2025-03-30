package phewitch.powersuits.common.networking.packets.server2client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.client.data.ClientData;
import phewitch.powersuits.common.item.suits.armorbase.pieces.SuitArmourChest;

import java.util.function.Supplier;

public class S2CSuitEnergySync {
    private final int energy;
    private final int energyCap;

    public S2CSuitEnergySync(int energy, int cap){
        this.energy = energy;
        this.energyCap = cap;
    }
    public S2CSuitEnergySync(FriendlyByteBuf buf){
        this.energy = buf.readInt();
        this.energyCap = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(energy);
        buf.writeInt(energyCap);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientData.suitPower = energy;
            ClientData.maxSuitPower = energyCap;
        });

        return true;
    }
}
