package phewitch.powersuits.common.networking.packets.client2server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;
import phewitch.powersuits.common.capabilities.PlayerOSSProvider;
import phewitch.powersuits.common.entity.EntityManager;
import phewitch.powersuits.common.entity.OSSManager;
import phewitch.powersuits.common.entity.mobs.SuitSentry;

import java.nio.charset.StandardCharsets;
import java.util.List;
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

            plr.getCapability(PlayerOSSProvider.PLAYER_OSS).ifPresent(playerOSS -> {
                if(playerOSS.getSuits().contains(suit)){
                    playerOSS.removeSuit(suit);
                    OSSManager.ServerSummonSentry(plr, suit);
                }
            });
        });

        return true;
    }
}
