package phewitch.powersuits.common.networking.packets.server2client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.client.data.ClientData;

import java.util.function.Supplier;

public class S2CSuitPowerSync {
    private int suitPower;
    private int suitMaxPower;

    public S2CSuitPowerSync(float minPower, float maxPower){
        this.suitPower = (int)minPower;
        this.suitMaxPower = (int)maxPower;
    }
    public S2CSuitPowerSync(FriendlyByteBuf buf){
        this.suitPower = buf.readInt();
        this.suitMaxPower = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(suitPower);
        buf.writeInt(suitMaxPower);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            ClientData.setPower(suitPower);
            ClientData.setMaxPower(suitMaxPower);
        });

        return true;
    }
}
