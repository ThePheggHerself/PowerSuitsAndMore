package phewitch.powersuits.common.networking.packets.client2server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.common.OSS.OSSManager;
import phewitch.powersuits.common.capabilities.Capabilities;

import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class C2SSummonOSSSuit {
    private String suit;

    public C2SSummonOSSSuit(String name){
        suit = name;
    }
    public C2SSummonOSSSuit(FriendlyByteBuf buf){
        this.suit = buf.readBytes(buf.readableBytes()).toString(StandardCharsets.UTF_8);
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeBytes(suit.getBytes());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {
            var plr = context.getSender();

            plr.getCapability(Capabilities.PLAYER_OSS).ifPresent(playerOSS -> {
                if(playerOSS.getSuits().contains(suit)){
                    OSSManager.RemoveSuitFromPlayer(suit, plr);
                    OSSManager.ServerSummonSentry(plr, suit);
                }
            });
        });

        return true;
    }
}
