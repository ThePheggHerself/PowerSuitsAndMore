package phewitch.powersuits.common.networking.packets.server2client;

import com.google.gson.Gson;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.common.entity.OSSManager;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Supplier;

public class S2CGetOSSSuits {
    private List<String> suits;

    public S2CGetOSSSuits(List<String> suits){
        this.suits = suits;
    }
    public S2CGetOSSSuits(FriendlyByteBuf buf){
        this.suits = new Gson().fromJson(buf.readBytes(buf.readableBytes()).toString(StandardCharsets.UTF_8), List.class);
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeBytes(new Gson().toJson(suits).getBytes());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            var plr = context.getSender();

            OSSManager.setSuits(suits);
        });

        return true;
    }
}
