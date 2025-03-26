package phewitch.powersuits.common.networking.packets.server2client;

import com.google.gson.Gson;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.client.data.ClientData;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Supplier;

public class S2CSyncOSS {
    private List<String> suits;

    public S2CSyncOSS(List<String> suits){
        this.suits = suits;
    }
    public S2CSyncOSS(FriendlyByteBuf buf){
        this.suits = new Gson().fromJson(buf.readBytes(buf.readableBytes()).toString(StandardCharsets.UTF_8), List.class);
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeBytes(new Gson().toJson(suits).getBytes());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            ClientData.setOSSSuits(suits);

            System.out.println("Syncing Suit Data: " + suits.size());
        });

        return true;
    }
}
